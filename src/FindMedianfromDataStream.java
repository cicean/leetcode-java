import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 * Created by cicean on 8/29/2016.
 * Total Accepted: 22193 Total Submissions: 97460 Difficulty: Hard
 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 Examples:
 [2,3,4] , the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.
 For example:

 add(1)
 add(2)
 findMedian() -> 1.5
 add(3)
 findMedian() -> 2
 Credits:
 Special thanks to @Louis1992 for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Heap Design

 */
public class FindMedianfromDataStream {

    /**
     * 分析
     这道题的经典做法就是维护两个Heap, 一个MaxHeap, 一个MinHeap，维护他们的size，比如保证

     MaxHeap.size() - MinHeap.size() >= 1
     当然这种题可以有些follow up, 比如：

     除了heap做，还有什么其他方法？
     BST可以，比如可以在Node里增加一个size表示整个children的数量，findMedian时间复杂度会增加到log(n);
     如果是找比如处在1/10th的元素，怎么找？
     同样可以用两个heap做，维护一个的size是另一个size的1/9即可。
     复杂度
     time: addNum -> O(log(n)), findMedian -> O(1)
     space: O(n)
     */
    class MedianFinder {

        // Adds a number into the data structure.
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        public void addNum(int num) {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());

            // 维护两个heap的size
            if (minHeap.size() > maxHeap.size())
                maxHeap.add(minHeap.remove());
        }

        // Returns the median of current data stream
        public double findMedian() {
            if (maxHeap.size() == minHeap.size())
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            else
                return maxHeap.peek();
        }
    };

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();

    // LintCode

    /**
     * Clarification
     What's the definition of Median?
     - Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

     Example
     For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

     For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

     For numbers coming list: [2, 20, 100], return [2, 2, 20].

     Challenge
     Total run time in O(nlogn).

     Tags
     LintCode Copyright Heap Priority Queue Google
     */

    public class Solution {
        public int[] medianII(int[] nums) {
            // write your code here
            if(nums.length <= 1) return nums;
            int[] res = new int[nums.length];
            PriorityQueue<Integer> minheap = new PriorityQueue<Integer>();
            PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(11, new Comparator<Integer>(){
                public int compare(Integer arg0, Integer arg1) {
                    return arg1 - arg0;
                }
            });
            // 将前两个元素先加入堆中
            minheap.offer(Math.max(nums[0], nums[1]));
            maxheap.offer(Math.min(nums[0], nums[1]));
            res[0] = res[1] = Math.min(nums[0], nums[1]);
            for(int i = 2; i < nums.length; i++){
                int mintop = minheap.peek();
                int maxtop = maxheap.peek();
                int curr = nums[i];
                // 新数在较小的一半中
                if (curr < maxtop){
                    if (maxheap.size() <= minheap.size()){
                        maxheap.offer(curr);
                    } else {
                        minheap.offer(maxheap.poll());
                        maxheap.offer(curr);
                    }
                    // 新数在中间
                } else if (curr >= maxtop && curr <= mintop){
                    if (maxheap.size() <= minheap.size()){
                        maxheap.offer(curr);
                    } else {
                        minheap.offer(curr);
                    }
                    // 新数在较大的一半中
                } else {
                    if(minheap.size() <= maxheap.size()){
                        minheap.offer(curr);
                    } else {
                        maxheap.offer(minheap.poll());
                        minheap.offer(curr);
                    }
                }
                if (maxheap.size() == minheap.size()){
                    res[i] = (maxheap.peek() + minheap.peek()) / 2;
                } else if (maxheap.size() > minheap.size()){
                    res[i] = maxheap.peek();
                } else {
                    res[i] = minheap.peek();
                }
            }
            return res;
        }
    }


}
