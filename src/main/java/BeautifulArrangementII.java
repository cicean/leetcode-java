import java.util.*;

/**
 * 667. Beautiful Arrangement II
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:
 Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.

 If there are multiple answers, print any of them.

 Example 1:
 Input: n = 3, k = 1
 Output: [1, 2, 3]
 Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
 Example 2:
 Input: n = 3, k = 2
 Output: [1, 3, 2]
 Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
 Note:
 The n and k are in the range 1 <= k < n <= 104.

 */

public class BeautifulArrangementII {

  /*
  Approach #1: Brute Force [Time Limit Exceeded]

Intuition

For each permutation of \text{[1, 2, ..., n]}[1, 2, ..., n], let's look at the set of differences of the adjacent elements.

Algorithm

For each permutation, we find the number of unique differences of adjacent elements. If it is the desired number, we'll return that permutation.

To enumerate each permutation without using library functions, we use a recursive algorithm,
where permute is responsible for permuting the indexes of \text{nums}nums in the interval \text{[start, nums.length)}[start, nums.length).

Complexity Analysis

Time Complexity: O(n!)O(n!) to generate every permutation in the outer loop, then O(n)O(n) work to check differences. In total taking O(n* n!)O(n∗n!) time.

Space Complexity: O(n)O(n). We use \text{seen}seen to store whether we've seen the differences, and each generated permutation has a length equal to \text{n}n.

   */


  private ArrayList<ArrayList<Integer>> permutations(int[] nums) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
    permute(ans, nums, 0);
    return ans;
  }

  private void permute(ArrayList<ArrayList<Integer>> ans, int[] nums, int start) {
    if (start >= nums.length) {
      ArrayList<Integer> cur = new ArrayList<Integer>();
      for (int x : nums) cur.add(x);
      ans.add(cur);
    } else {
      for (int i = start; i < nums.length; i++) {
        swap(nums, start, i);
        permute(ans, nums, start+1);
        swap(nums, start, i);
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private int numUniqueDiffs(ArrayList<Integer> arr) {
    boolean[] seen = new boolean[arr.size()];
    int ans = 0;

    for (int i = 0; i < arr.size() - 1; i++) {
      int delta = Math.abs(arr.get(i) - arr.get(i+1));
      if (!seen[delta]) {
        ans++;
        seen[delta] = true;
      }
    }
    return ans;
  }

  public int[] constructArray(int n, int k) {
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = i+1;
    }
    for (ArrayList<Integer> cand : permutations(nums)) {
      if (numUniqueDiffs(cand) == k) {
        int[] ans = new int[n];
        int i = 0;
        for (int x : cand) ans[i++] = x;
        return ans;
      }
    }
    return null;
  }

  /*
  Approach #2: Construction [Accepted]

Intuition

When \text{k = n-1}k = n-1, a valid construction is \text{[1, n, 2, n-1, 3, n-2, ....]}[1, n, 2, n-1, 3, n-2, ....]. One way to see this is, we need to have a difference of \text{n-1}n-1, which means we need \text{1}1 and \text{n}n adjacent; then, we need a difference of \text{n-2}n-2, etc.

Also, when \text{k = 1}k = 1, a valid construction is \text{[1, 2, 3, ..., n]}[1, 2, 3, ..., n]. So we have a construction when \text{n-k}n-k is tiny, and when it is large. This leads to the idea that we can stitch together these two constructions: we can put \text{[1, 2, ..., n-k-1]}[1, 2, ..., n-k-1] first so that \text{n}n is effectively \text{k+1}k+1, and then finish the construction with the first \text{"k = n-1"}"k = n-1" method.

For example, when \text{n = 6}n = 6 and \text{k = 3}k = 3, we will construct the array as \text{[1, 2, 3, 6, 4, 5]}[1, 2, 3, 6, 4, 5]. This consists of two parts: a construction of \text{[1, 2]}[1, 2] and a construction of \text{[1, 4, 2, 3]}[1, 4, 2, 3] where every element had \text{2}2 added to it (i.e. \text{[3, 6, 4, 5]}[3, 6, 4, 5]).

Algorithm

As before, write \text{[1, 2, ..., n-k-1]}[1, 2, ..., n-k-1] first. The remaining \text{k+1}k+1 elements to be written are \text{[n-k, n-k+1, ..., n]}[n-k, n-k+1, ..., n], and we'll write them in alternating head and tail order.

When we are writing the i^{th}i
​th
​​  element from the remaining \text{k+1}k+1, every even ii is going to be chosen from the head, and will have value \text{n-k + i//2}n-k + i//2. Every odd ii is going to be chosen from the tail, and will have value \text{n - i//2}n - i//2.

Complexity Analysis

Time Complexity: O(n)O(n). We are making a list of size \text{n}n.

Space Complexity: O(n)O(n). Our answer has a length equal to \text{n}n.

   */


  public int[] constructArray_2(int n, int k) {
    int[] ans = new int[n];
    int c = 0;
    for (int v = 1; v < n-k; v++) {
      ans[c++] = v;
    }
    for (int i = 0; i <= k; i++) {
      ans[c++] = (i%2 == 0) ? (n-k + i/2) : (n - i/2);
    }
    return ans;
  }

}
