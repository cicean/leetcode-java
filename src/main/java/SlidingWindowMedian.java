import java.util.*;

/**
 * 480. Sliding Window Median
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 Examples:
 [2,3,4] , the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Median
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
 Therefore, return the median sliding window as [1,-1,-1,3,5,6].

 Note:
 You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 */

public class SlidingWindowMedian {

  /**
   * Solution

   Approach #1 Simple Sorting [Time Limit Exceeded / Barely Accepted]

   Intuition

   Do what the question says.

   Algorithm

   Store the numbers in a window container of size kk. The following operations must take place:

   Inserting the incoming element.
   Deleting the outgoing element.
   Sorting the window to find the medians.
   One primitive approach is to copy kk consecutive elements from the input to the window and keep sorting these every time. This constitutes duplication of effort.

   We can do a bit better if we instead insert and delete one element per window shift. The challenge then is to maintain the window as sorted, before and after the insert and delete operations.

   Complexity Analysis

   Time complexity: O(n \cdot k \cdot log(k))O(n⋅k⋅log(k)) to O(n \cdot k)O(n⋅k).

   Copying elements into the container takes about O(k)O(k) time each. This happens about (n-k)(n−k) times.
   Sorting for each of the (n-k)(n−k) sliding window instances takes about O(k \cdot log(k))O(k⋅log(k)) time each.

   Bisected insertion or deletion takes about O(log(k))O(log(k)) for searching and O(k)O(k) for actual shifting of elements. This takes place about nn times.

   Space complexity: O(k)O(k) extra linear space for the window container.

   C++ [Time Limit Exceeded]
   * @param nums
   * @param k
   * @return
   */
  public double[] medianSlidingWindow(int[] nums, int k) {
    List<Double> medians = new ArrayList<>();

    for (int i = 0; i + k <= nums.length; i++) {
      int[] window = Arrays.copyOfRange(nums, i, i + k);
      Arrays.sort(window);

      if (k % 2 == 1) {
        medians.add(new Double(window[k / 2]));
      } else {
        medians.add(new Double((window[k / 2 - 1] + window[k / 2])/ 2));
      }

    }

    return medians.stream().mapToDouble(Number::doubleValue).toArray();
  }

  /**
   * Approach #2 Two Heaps! (Lazy Removal) [Accepted]

   Intuition

   The idea is the same as Approach #3 from 295. Find Median From Data Stream. The only additional requirement is removing the outgoing elements from the window.

   Since the window elements are stored in heaps, deleting elements that are not at the top of the heaps is a pain.

   Some languages (like Java) provide implementations of the PriorityQueue class that allow for removing arbitrarily placed elements. Generally, using such features is not efficient nor is their portability assured.

   Assuming that only the tops of heaps (and by extension the PriorityQueue class) are accessible, we need to find a way to efficiently invalidate and remove elements that are moving out of the sliding window.

   At this point, an important thing to notice is the fact that if the two heaps are balanced, only the top of the heaps are actually needed to find the medians. This means that as long as we can somehow keep the heaps balanced, we could also keep some extraneous elements.

   Thus, we can use hash-tables to keep track of invalidated elements. Once they reach the heap tops, we remove them from the heaps. This is the lazy removal technique.

   An immediate challenge at this point is balancing the heaps while keeping extraneous elements. This is done by actually moving some elements to the heap which has extraneous elements, from the other heap. This cancels out the effect of having extraneous elements and maintains the invariant that the heaps are balanced.

   NOTE: When we talk about keeping the heaps balanced, we are not referring to the actual heap sizes. We are only concerned with valid elements and hence when we talk about balancing heaps, we are referring to count of such elements.

   Algorithm

   Two priority queues:

   A max-heap lo to store the smaller half of the numbers
   A min-heap hi to store the larger half of the numbers
   A hash-map or hash-table hash_table for keeping track of invalid numbers. It holds the count of the occurrences of all such numbers that have been invalidated and yet remain in the heaps.

   The max-heap lo is allowed to store, at worst, one more element more than the min-heap hi. Hence if we have processed kk elements:

   If k = 2*n + 1 \quad (\forall \, n \in \mathbb{Z})k=2∗n+1(∀n∈Z), then lo is allowed to hold n+1n+1 elements, while hi can hold nn elements.
   If k = 2*n \quad (\forall \, n \in \mathbb{Z})k=2∗n(∀n∈Z), then both heaps are balanced and hold nn elements each.
   This gives us the nice property that when the heaps are perfectly balanced, the median can be derived from the tops of both heaps. Otherwise, the top of the max-heap lo holds the legitimate median.

   NOTE: As mentioned before, when we are talking about keeping the heaps balanced, the actual sizes of the heaps are irrelevant. Only the count of valid elements in both heaps matter.

   Keep a balance factor. It indicates three situations:

   balance = 0=0: Both heaps are balanced or nearly balanced.
   balance < 0<0: lo needs more valid elements. Elements from hi are moved to lo.
   balance > 0>0: hi needs more valid elements. Elements from lo are moved to hi.
   Inserting an incoming number in_num:

   If in_num is less than or equal to the top element of lo, then it can be inserted to lo. However this unbalances hi (hi has lesser valid elements now). Hence balance is incremented.

   Otherwise, in_num must be added to hi. Obviously, now lo is unbalanced. Hence balance is decremented.

   Lazy removal of an outgoing number out_num:

   If out_num is present in lo, then invalidating this occurrence will unbalance lo itself. Hence balance must be decremented.
   If out_num is present in hi, then invalidating this occurrence will unbalance hi itself. Hence balance must be incremented.

   We increment the count of this element in the hash_table table.

   Once an invalid element reaches either of the heap tops, we remove them and decrement their counts in the hash_table table.
   Complexity Analysis

   Time complexity: O(2 * n * log(k)) + O(n-k) \approx O(n \cdot log(k))O(2∗n∗log(k))+O(n−k)≈O(n⋅log(k)).

   Either (or sometimes both) of the heaps gets every element inserted into it at least once. Collectively each of those takes about O(log(k))O(log(k)) time. That is nn such insertions.
   About (n-k)(n−k) removals from the top of the heaps take place (the number of sliding window instances). Each of those takes about O(log(k))O(log(k)) time.
   Hash table operations are assumed to take O(1)O(1) time each. This happens roughly the same number of times as removals from heaps take place.
   Space complexity: O(k) + O(n) \approx O(n)O(k)+O(n)≈O(n) extra linear space.

   The heaps collectively require O(k)O(k) space.
   The hash table needs about O(n-k)O(n−k) space.
   */

  class Solution_2_PQ {
    public double[] medianSlidingWindow(int[] nums, int k) {
      PriorityQueue<Integer> maxH = new PriorityQueue<Integer>(Collections.reverseOrder());
      PriorityQueue<Integer> minH = new PriorityQueue<Integer>();
      HashMap<Integer, Integer> invalid = new HashMap<Integer, Integer>();
      int leftValidCount = 0;
      int rightValidCount = 0;


      double[] results = new double[nums.length - k + 1];

      for (int i = 0; i < nums.length; i++)
      {
        // Add new num to the heap pair
        if (maxH.isEmpty() || nums[i] <= maxH.peek())
        {
          maxH.add(nums[i]);
          leftValidCount++;
        }
        else
        {
          minH.add(nums[i]);
          rightValidCount++;
        }

        // Remove num from heap pair when it is necessary
        if (i >= k)
        {
          if (nums[i - k] <= maxH.peek())
          {
            leftValidCount--;
            int count = invalid.getOrDefault(nums[i - k], 0);
            invalid.put(nums[i - k], count+1);
          }
          else
          {
            rightValidCount--;
            int count = invalid.getOrDefault(nums[i - k], 0);
            invalid.put(nums[i - k], count+1);
          }
        }

        // Rebalance the heap pair
        while(leftValidCount > rightValidCount + 1 ||
                invalid.getOrDefault(maxH.peek(), 0) > 0)
        {
          int value = maxH.poll();
          int invalidCount = invalid.getOrDefault(value, 0);

          if (invalidCount > 0)
          {
            invalid.put(value, invalidCount-1);
          }
          else
          {
            minH.add(value);
            leftValidCount--;
            rightValidCount++;
          }
        }

        while (rightValidCount > leftValidCount ||
                invalid.getOrDefault(minH.peek(), 0) > 0)
        {
          int value = minH.poll();
          int invalidCount = invalid.getOrDefault(value, 0);

          if (invalidCount > 0)
          {
            invalid.put(value, invalidCount-1);
          }
          else
          {
            maxH.add(value);
            leftValidCount++;
            rightValidCount--;
          }
        }

        // Get medium when necessary
        if (i >= k-1)
        {
          if (k % 2 == 0)
          {
            results[i-k+1] = ((double)minH.peek() + (double)maxH.peek()) / 2.0;
          }
          else
          {
            results[i-k+1] = (double) maxH.peek();
          }
        }
      }

      return results;
    }
  }



  class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
      if(nums.length==0 || k < 1)
        return new double[]{};
      List<Long> windows = new ArrayList<>();
      for(int i : Arrays.copyOfRange(nums, 0, k)){
        windows.add(Long.valueOf(i));
      }
      Collections.sort(windows);
      boolean isOdd = (k&1) == 1;
      double[] res = new double[nums.length + 1 - k];
      for(int i = 0; i < res.length; i++){
        maintainWindows(windows, nums, i, k);
        if(isOdd){
          res[i] = windows.get(k/2);
        } else {
          res[i] = (windows.get(k/2)+ windows.get(k/2-1))/2d;
        }
      }
      return res;
    }
    private void maintainWindows(List<Long> windows, int[] nums, int i, int k){
      if(i==0)
        return;
      //remove prev
      long oldElem = nums[i-1], newElem = nums[i + k - 1];
      windows.remove(Collections.binarySearch(windows, oldElem));
      int idx = Collections.binarySearch(windows, newElem);
      if(idx < 0 )
        idx = - idx - 1;
      windows.add(idx,newElem);
    }

    //Nklongk
//     public double[] medianSlidingWindow(int[] nums, int k) {
//         int N = nums.length;
//         double[] res = new double[nums.length - k +1];
//         List<Long> windowlist = new ArrayList<>();
//         Deque<Integer> queue = new LinkedList<>();
//         int idx = 0;

//         for(int i = 0; i< nums.length; i++) {
//             if(!queue.isEmpty() && queue.peekFirst() == i-k) {
//                 maintainList(windowlist, Long.valueOf(nums[queue.peekFirst()]));
//                 queue.pollFirst();
//             }
//             queue.addLast(i);
//             windowlist.add(Long.valueOf(nums[i]));

//             if(i - k +1>= 0) {
//                 res[idx++] = getMin(windowlist, k);
//             }
//         }

//         return res;
//     }

//     private void maintainList(List<Long> winList, Long target) {
//         int idx = Collections.binarySearch(winList, target);
//         if(idx < 0) {
//             idx = - idx -1;
//         }
//         winList.remove(idx);
//     }

//     private double getMin(List<Long> list, int k) {
//         Collections.sort(list);
//         double ans = 0;
//         if(k % 2 == 0) {
//             ans = (list.get(k/2-1) + list.get(k/2))/2d;
//         }
//         else {
//             ans = list.get(k/2);
//         }

//         return ans;
//     }
  }


}
