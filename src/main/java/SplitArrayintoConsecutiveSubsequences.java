import java.util.*;

/**
 * 659. Split Array into Consecutive Subsequences
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

 Example 1:
 Input: [1,2,3,3,4,5]
 Output: True
 Explanation:
 You can split them into two consecutive subsequences :
 1, 2, 3
 3, 4, 5
 Example 2:
 Input: [1,2,3,3,4,4,5,5]
 Output: True
 Explanation:
 You can split them into two consecutive subsequences :
 1, 2, 3, 4, 5
 3, 4, 5
 Example 3:
 Input: [1,2,3,4,4,5]
 Output: False
 Note:
 The length of the input is in range of [1, 10000]
 */

public class SplitArrayintoConsecutiveSubsequences {

  /**
   * Time O(n) Space O(1)
   * The basic idea is that, for each distinct element ele in the input array, we only need to
   * maintain three pieces of information: the number of consecutive subsequences ending at ele
   * with length of 1, length of 2 and length >= 3.

   The input array will be scanned linearly from left to right. Let cur be the element currently
   being examined and cnt as its number of appearance. pre is the element processed immediately
   before cur. The number of consecutive subsequences ending at pre with length of 1, length of 2
   and length >= 3 are denoted as p1, p2 and p3, respectively. There are two cases in general:

   cur != pre + 1: for this case, cur cannot be added to any consecutive subsequences ending at pre,
   therefore, we must have p1 == 0 && p2 == 0; otherwise the input array cannot be split into
   consecutive subsequences of length >= 3. Now let c1, c2, c3 be the number of consecutive
   subsequences ending at cur with length of 1, length of 2 and length >= 3, respectively, we will
   have c1 = cnt, c2 = 0, c3 = 0, which means we only have consecutive subsequence ending at cur
   with length of 1 and its number given by cnt.

   cur == pre + 1: for this case, cur can be added to consecutive subsequences ending at pre and
   thus extend those subsequences. But priorities should be given to those with length of 1 first,
   then length of 2 and lastly length >= 3. Also we must have cnt >= p1 + p2; otherwise the input
   array cannot be split into consecutive subsequences of length >= 3. Again let c1, c2, c3 be the
   number of consecutive subsequences ending at cur with length of 1, length of 2 and length >= 3,
   respectively, we will have: c2 = p1, c3 = p2 + min(p3, cnt - (p1 + p2)),
   c1 = max(cnt - (p1 + p2 + p3), 0). The meaning is as follows: first adding cur to the end of
   subsequences of length 1 will make them subsequences of length 2, and we have p1 such
   subsequences, therefore c2 = p1. Then adding cur to the end of subsequences of length 2 will
   make them subsequences of length 3, and we have p2 such subsequences, therefore c3 is at least
   p2. If cnt > p1 + p2, we can add the remaining cur to the end of subsequences of length >= 3 to
   make them even longer subsequences. The number of such subsequences is the smaller one of p3 and
   cnt - (p1 + p2). In total, c3 = p2 + min(p3, cnt - (p1 + p2)). If cnt > p1 + p2 + p3,
   then we still have remaining cur that cannot be added to any subsequences. These residue cur
   will form subsequences of length 1, hence c1 = max(cnt - (p1 + p2 + p3), 0).

   For either case, we need to update: pre = cur, p1 = c1, p2 = c2, p3 = c3 after processing the
   current element. When all the elements are done, we check the values of p1 and p2. The input
   array can be split into consecutive subsequences of length >= 3 if and only if p1 == 0 && p2 == 0.

   Here is the O(n) time and O(1) space Java solution:
   * @param nums
   * @return
   */

  public boolean isPossible(int[] nums) {
    int pre = Integer.MIN_VALUE, p1 = 0, p2 = 0, p3 = 0;
    int cur = 0, cnt = 0, c1 = 0, c2 = 0, c3 = 0;

    for (int i = 0; i < nums.length; pre = cur, p1 = c1, p2 = c2, p3 = c3) {
      for (cur = nums[i], cnt = 0; i < nums.length && cur == nums[i]; cnt++, i++);

      if (cur != pre + 1) {
        if (p1 != 0 || p2 != 0) return false;
        c1 = cnt; c2 = 0; c3 = 0;

      } else {
        if (cnt < p1 + p2) return false;
        c1 = Math.max(0, cnt - (p1 + p2 + p3));
        c2 = p1;
        c3 = p2 + Math.min(p3, cnt - (p1 + p2));
      }
    }

    return p1 == 0 && p2 == 0;
  }

  /**
   * Java O(n) Time O(n) Space
   * We iterate through the array once to get the frequency of all the elements in the array
   We iterate through the array once more and for each element we either see if it can be appended
   to a previously constructed consecutive sequence or if it can be the start of a new consecutive
   sequence. If neither are true, then we return false.
   * @param nums
   * @return
   */

  public boolean isPossible_2(int[] nums) {
    Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
    for (int i : nums) freq.put(i, freq.getOrDefault(i,0) + 1);
    for (int i : nums) {
      if (freq.get(i) == 0) continue;
      else if (appendfreq.getOrDefault(i,0) > 0) {
        appendfreq.put(i, appendfreq.get(i) - 1);
        appendfreq.put(i+1, appendfreq.getOrDefault(i+1,0) + 1);
      }
      else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
        freq.put(i+1, freq.get(i+1) - 1);
        freq.put(i+2, freq.get(i+2) - 1);
        appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);
      }
      else return false;
      freq.put(i, freq.get(i) - 1);
    }
    return true;
  }

}
