import java.util.*;

/**
 * 456. 132 Pattern
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

 Note: n will be less than 15,000.

 Example 1:
 Input: [1, 2, 3, 4]

 Output: False

 Explanation: There is no 132 pattern in the sequence.
 Example 2:
 Input: [3, 1, 4, 2]

 Output: True

 Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 Example 3:
 Input: [-1, 3, 2, 0]

 Output: True

 Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

 */

public class OneThreeTwoPattern {

  /**
   * Approach #1 Brute Force [Time Limit Exceeded]

   The simplest solution is to consider every triplet (i, j, k)(i,j,k) and check if the corresponding numbers satisfy the 132 criteria. If any such triplet is found, we can return a True value. If no such triplet is found, we need to return a False value.

   Java

   Complexity Analysis

   Time complexity : O(n^3). Three loops are used to consider every possible triplet. Here, nn refers to the size of numsnums array.

   Space complexity : O(1)O(1). Constant extra space is used.


   */

  public class Solution {
    public boolean find132pattern(int[] nums) {
      for (int i = 0; i < nums.length - 2; i++) {
        for (int j = i + 1; j < nums.length - 1; j++) {
          for (int k = j + 1; k < nums.length; k++) {
            if (nums[k] > nums[i] && nums[j] > nums[k])
              return true;
          }
        }
      }
      return false;
    }
  }

//  Approach #2 Better Brute Force [Accepted]
//
//  Algorithm
//
//  We can improve the last approach to some extent, if we make use of some observations. We can note that for a particular number nums[j]nums[j] chosen as 2nd element in the 132 pattern, if we don't consider nums[k]nums[k](the 3rd element) for the time being, our job is to find out the first element, nums[i]nums[i](ii) which is lesser than nums[j]nums[j].
//
//  Now, assume that we have somehow found a nums[i],nums[j]nums[i],nums[j] pair. Our task now reduces to finding out a nums[k]nums[k](Kk>j>i)Kk>j>i), which falls in the range (nums[i], nums[j])(nums[i],nums[j]). Now, to maximize the likelihood of a nums[k]nums[k] falling in this range, we need to increase this range as much as possible.
//
//      Since, we started off by fixing a nums[j]nums[j], the only option in our hand is to choose a minimum value of nums[i]nums[i] given a particular nums[j]nums[j]. Once, this pair nums[i],nums[j]nums[i],nums[j], has been found out, we simply need to traverse beyond the index jj to find if a nums[k]nums[k] exists for this pair satisfying the 132 criteria.
//
//  Based on the above observations, while traversing over the numsnums array choosing various values of nums[j]nums[j], we simultaneously keep a track of the minimum element found so far(excluding nums[j]nums[j]). This minimum element always serves as the nums[i]nums[i] for the current nums[j]nums[j]. Thus, we only need to traverse beyond the j^{th}j
//​th
//​​  index to check the nums[k]nums[k]'s to determine if any of them satisfies the 132 criteria.
//
//  Java

  /**
   *   Complexity Analysis

   Time complexity : O(n^2). Two loops are used to find the nums[j],nums[k]nums[j],nums[k] pairs. Here, nn refers to the size of numsnums array.

   Space complexity : O(1)O(1). Constant extra space is used.

   Approach #3 Searching Intervals [Accepted]
   */

  public class Solution2 {
    public boolean find132pattern(int[] nums) {
      int min_i = Integer.MAX_VALUE;
      for (int j = 0; j < nums.length - 1; j++) {
        min_i = Math.min(min_i, nums[j]);
        for (int k = j + 1; k < nums.length; k++) {
          if (nums[k] < nums[j] && min_i < nums[k])
            return true;
        }
      }
      return false;
    }
  }

//
//  Algorithm
//
//  As discussed in the last approach, once we've fixed a nums[i],nums[j]nums[i],nums[j] pair, we just need to determine a nums[k]nums[k] which falls in the range (nums[i],nums[j])(nums[i],nums[j]). Further, to maximize the likelihood of any arbitrary nums[k]nums[k] falling in this range, we need to try to keep this range as much as possible. But, in the last approach, we tried to work only on nums[i]nums[i]. But, it'll be a better choice, if we can somehow work out on nums[j]nums[j] as well.
//
//  To do so, we can look at the given numsnums array in the form of a graph, as shown below:
//
//  Graph
//
//  From the above graph, which consists of rising and falling slopes, we know, the best qualifiers to act as the nums[i],nums[j]nums[i],nums[j] pair, as discussed above, to maximize the range nums[i], nums[j]nums[i],nums[j], at any instant, while traversing the numsnums array, will be the points at the endpoints of a local rising slope. Thus, once we've found such points, we can traverse over the numsnums array to find a nums[k]nums[k] satisfying the given 132 criteria.
//
//  To find these points at the ends of a local rising slope, we can traverse over the given numsnums array. While traversing, we can keep a track of the minimum point found after the last peak(nums[s]nums[s]).
//
//  Now, whenever we encounter a falling slope, say, at index ii, we know, that nums[i-1]nums[i−1] was the endpoint of the last rising slope found. Thus, we can scan over the kk indices(k>i), to find a 132 pattern.
//
//      But, instead of traversing over numsnums to find a kk satisfying the 132 pattern for every such rising slope, we can store this range (nums[s], nums[i-1])(nums[s],nums[i−1])(acting as (nums[i], nums[j])(nums[i],nums[j])) in, say an intervalsintervals array.
//
//  While traversing over the numsnums array to check the rising/falling slopes, whenever we find any rising slope, we can keep adding the endpoint pairs to this intervalsintervals array. At the same time, we can also check if the current element falls in any of the ranges found so far. If so, this element satisfies the 132 criteria for that range.
//
//  If no such element is found till the end, we need to return a False value.
//
//      Java
//
//  Complexity Analysis
//
//  Time complexity : O(n^2). We traverse over the numsnums array of size nn once to find the slopes. But for every element, we also need to traverse over the intervalsintervals to check if any element falls in any range found so far. This array can contain atmost (n/2)(n/2) pairs, in the case of an alternate increasing-decreasing sequence(worst case e.g.[5 6 4 7 3 8 2 9]).
//
//  Space complexity : O(n)O(n). intervalsintervals array can contain atmost n/2n/2 pairs, in the worst case(alternate increasing-decreasing sequence).
//

  public class Solution3 {
    public boolean find132pattern(int[] nums) {
      List < int[] > intervals = new ArrayList < > ();
      int i = 1, s = 0;
      while (i < nums.length) {
        if (nums[i] <= nums[i - 1]) {
          if (s < i - 1)
            intervals.add(new int[] {nums[s], nums[i - 1]});
          s = i;
        }
        for (int[] a: intervals)
          if (nums[i] > a[0] && nums[i] < a[1])
            return true;
        i++;
      }
      return false;
    }
  }

//  Approach #4 Using Stack [Accepted]:
//
//  Algorithm
//
//  In Approach 2, we found out nums[i]nums[i] corresponding to a particular nums[j]nums[j] directly without having to consider every pair possible in numsnums to find this nums[i],nums[j]nums[i],nums[j] pair. If we do some preprocessing, we can make the process of finding a nums[k]nums[k] corresponding to this nums[i],nums[j]nums[i],nums[j] pair also easy.
//
//  The preprocessing required is to just find the best nums[i]nums[i] value corresponding to every nums[j]nums[j] value. This is done in the same manner as in the second approach i.e. we find the minimum element found till the j^{th}j
//​th
//​​  element which acts as the nums[i]nums[i] for the current nums[j]nums[j]. We maintain thes values in a minmin array. Thus, min[j]min[j] now refers to the best nums[i]nums[i] value for a particular nums[j]nums[j].
//
//  Now, we traverse back from the end of the numsnums array to find the nums[k]nums[k]'s. Suppose, we keep a track of the nums[k]nums[k] values which can potentially satisfy the 132 criteria for the current nums[j]nums[j]. We know, one of the conditions to be satisfied by such a nums[k]nums[k] is that it must be greater than nums[i]nums[i]. Or in other words, we can also say that it must be greater than min[j]min[j] for a particular nums[j]nums[j] chosen.
//
//  Once it is ensured that the elements left for competing for the nums[k]nums[k] are all greater than min[j]min[j](or nums[i]nums[i]), our only task is to ensure that it should be lesser than nums[j]nums[j]. Now, the best element from among the competitors, for satisfying this condition will be the minimum one from out of these elements.
//
//      If this element, nums[min]nums[min] satisfies nums[min] < nums[j]nums[min]<nums[j], we've found a 132 pattern. If not, no other element will satisfy this criteria, since they are all greater than or equal to nums[min]nums[min]$ and thus greater than or equal to nums[j]nums[j] as well.
//
//  To keep a track of these potential nums[k]nums[k] values for a particular nums[i],nums[j]nums[i],nums[j] considered currently, we maintain a stackstack on which these potential nums[k]nums[k]'s satisfying the 132 criteria lie in a descending order(minimum element on the top). We need not sort these elements on the stackstack, but they'll be sorted automatically as we'll discuss along with the process.
//
//  After creating a minmin array, we start traversing the nums[j]nums[j] array in a backward manner. Let's say, we are currently at the j^{th}j
//      ​th
//​​ element and let's also assume that the stackstack is sorted right now. Now, firstly, we check if nums[j] > min[j]nums[j]>min[j]. If not, we continue with the (j-1)^{th}(j−1)
//      ​th
//​​  element and the stackstack remains sorted. If not, we keep on popping the elements from the top of the stackstack till we find an element, stack[top]stack[top] such that, stack[top] > min[j]stack[top]>min[j](or stack[top] > nums[i]stack[top]>nums[i]).
//
//  Once the popping is done, we're sure that all the elements pending on the stackstack are greater than nums[i]nums[i] and are thus, the potential candidates for nums[k]nums[k] satisfying the 132 criteria. We can also note that the elements which have been popped from the stackstack, all satisfy stack[top]≤min[j]stack[top]≤min[j].
//
//  Since, in the minmin array, min[p]≤min[q]min[p]≤min[q], for every p > qp>q, these popped elements also satisfy stack[top]≤min[k]stack[top]≤min[k], for all 0≤k<j0≤k<j. Thus, they are not the potential nums[k]nums[k] candidates for even the preceding elements. Even after doing the popping, the stackstack remains sorted.
//
//  After the popping is done, we've got the minimum element from amongst all the potential nums[k]nums[k]'s on the top of the stackstack(as per the assumption). We can check if it is greater than nums[j]nums[j] to satisfy the 132 criteria(we've already checked stack[top] > nums[i]stack[top]>nums[i]). If this element satisfies the 132 criteria, we can return a True value. If not, we know that for the current jj, nums[j] > min[j]nums[j]>min[j]. Thus, the element nums[j]nums[j] could be a potential nums[k]nums[k] value, for the preceding nums[i]'snums[i]
//      ​′
//      ​​ s.
//
//      Thus, we push it over the stackstack. We can note that, we need to push this element nums[j]nums[j] on the stackstack only when it didn't satisfy stack[top]stack[top]. Thus, nums[j]≤stack[top]nums[j]≤stack[top]. Thus, even after pushing this element on the stackstack, the stackstack remains sorted. Thus, we've seen by induction, that the stackstack always remains sorted.
//
//      Also, note that in case nums[j]≤min[j]nums[j]≤min[j], we don't push nums[j]nums[j] onto the stackstack. This is because this nums[j]nums[j] isn't greater than even the minimum element lying towards its left and thus can't act as nums[k]nums[k] in the future.
//
//      If no element is found satisfying the 132 criteria till reaching the first element, we return a False value.
//
//      The following animation better illustrates the process.
//
//      1 / 10

//      Java

//  Complexity Analysis
//
//  Time complexity : O(n)O(n). We travesre over the numsnums array of size nn once to fill the minmin array. After this, we traverse over numsnums to find the nums[k]nums[k]. During this process, we also push and pop the elements on the stackstack. But, we can note that atmost nn elements can be pushed and popped off the stackstack in total. Thus, the second traversal requires only O(n)O(n) time.
//
//  Space complexity : O(n)O(n). The stackstack can grow upto a maximum depth of nn. Furhter, minmin array of size nn is used.


  public class Solution4 {
    public boolean find132pattern(int[] nums) {
      if (nums.length < 3)
        return false;
      Stack < Integer > stack = new Stack < > ();
      int[] min = new int[nums.length];
      min[0] = nums[0];
      for (int i = 1; i < nums.length; i++)
        min[i] = Math.min(min[i - 1], nums[i]);
      for (int j = nums.length - 1; j >= 0; j--) {
        if (nums[j] > min[j]) {
          while (!stack.isEmpty() && stack.peek() <= min[j])
            stack.pop();
          if (!stack.isEmpty() && stack.peek() < nums[j])
            return true;
          stack.push(nums[j]);
        }
      }
      return false;
    }
  }
//
//      Approach #5 Using Binary Search [Accepted]:
//
//  Algorithm
//
//  In the last approach, we've made use of a separate stackstack to push and pop the nums[k]nums[k]'s. But, we can also note that when we reach the index jj while scanning backwards for finding nums[k]nums[k], the stackstack can contain atmost n-j-1n−j−1 elements. Here, nn refers to the number of elements in numsnums array.
//
//  We can also note that this is the same number of elements which lie beyond the j^{th}j
//​th
//​​  index in numsnums array. We also know that these elements lying beyond the j^{th}j
//​th
//​​  index won't be needed in the future ever again. Thus, we can make use of this space in numsnums array instead of using a separate stackstack. The rest of the process can be carried on in the same manner as discussed in the last approach.
//
//  We can try to go for another optimization here. Since, we've got an array for storing the potential nums[k]nums[k] values now, we need not do the popping process for a min[j]min[j] to find an element just larger than min[j]min[j] from amongst these potential values.
//
//  Instead, we can make use of Binary Search to directly find an element, which is just larger than min[j]min[j] in the required interval, if it exists. If such an element is found, we can compare it with nums[j]nums[j] to check the 132 criteria. Otherwise, we continue the process as in the last approach.
//
//      Java
//
//  Complexity Analysis
//
//  Time complexity : O\big(nlog(n)\big)O(nlog(n)). Filling minmin array requires O(n)O(n) time. The second traversal is done over the whole numsnums array of length nn. For every current nums[j]nums[j] we need to do the Binary Search, which requires O\big(log(n)\big)O(log(n)). In the worst case, this Binary Search will be done for all the nn elements, and the required element won't be found in any case, leading to a complexity of O\big(nlog(n)\big)O(nlog(n)).
//
//  Space complexity : O(n)O(n). minmin array of size nn is used.

  public class Solution5 {
    public boolean find132pattern(int[] nums) {
      if (nums.length < 3)
        return false;
      int[] min = new int[nums.length];
      min[0] = nums[0];
      for (int i = 1; i < nums.length; i++)
        min[i] = Math.min(min[i - 1], nums[i]);
      for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
        if (nums[j] > min[j]) {
          k = Arrays.binarySearch(nums, k, nums.length, min[j] + 1);
          if (k < 0)
            k = -1 - k;
          if (k < nums.length && nums[k] < nums[j])
            return true;
          nums[--k] = nums[j];
        }
      }
      return false;
    }
  }


//      Approach #6 Using Array as a stack[Accepted]:
//
//  Algorithm
//
//  In the last approach, we've seen that in the worst case, the required element won't be found for all the nn elements and thus Binary Search is done at every step increasing the time complexity.
//
//  To remove this problem, we can follow the same steps as in Approach 4 i.e. We can remove those elements(update the index kk) which aren't greater than nums[i]nums[i](min[j]min[j]). Thus, in case no element is larger than min[j]min[j] the index kk reaches the last element.
//
//  Now, at every step, only nums[j]nums[j] will be added and removed from consideration in the next step, improving the time complexity in the worst case. The rest of the method remains the same as in Approach 4.
//
//  This approach is inspired by @fun4leetcode
//
//      Java
//
//  Complexity Analysis
//
//  Time complexity : O(n)O(n). We travesre over the numsnums array of size nn once to fill the minmin array. After this, we traverse over numsnums to find the nums[k]nums[k]. Atmost nn elements can be put in and out of the numsnums array in total. Thus, the second traversal requires only O(n)O(n) time.
//
//  Space complexity : O(n)O(n). minmin array of size nn is used.
//
//      Analysis w

  public class Solution6 {
    public boolean find132pattern(int[] nums) {
      if (nums.length < 3)
        return false;
      int[] min = new int[nums.length];
      min[0] = nums[0];
      for (int i = 1; i < nums.length; i++)
        min[i] = Math.min(min[i - 1], nums[i]);
      for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
        if (nums[j] > min[j]) {
          while (k < nums.length && nums[k] <= min[j])
            k++;
          if (k < nums.length && nums[k] < nums[j])
            return true;
          nums[--k] = nums[j];
        }
      }
      return false;
    }
  }


}
