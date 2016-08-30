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
     * ����
     �����ľ�����������ά������Heap, һ��MaxHeap, һ��MinHeap��ά�����ǵ�size�����籣֤

     MaxHeap.size() - MinHeap.size() >= 1
     ��Ȼ�����������Щfollow up, ���磺

     ����heap��������ʲô����������
     BST���ԣ����������Node������һ��size��ʾ����children��������findMedianʱ�临�ӶȻ����ӵ�log(n);
     ������ұ��紦��1/10th��Ԫ�أ���ô�ң�
     ͬ������������heap����ά��һ����size����һ��size��1/9���ɡ�
     ���Ӷ�
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

            // ά������heap��size
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
            // ��ǰ����Ԫ���ȼ������
            minheap.offer(Math.max(nums[0], nums[1]));
            maxheap.offer(Math.min(nums[0], nums[1]));
            res[0] = res[1] = Math.min(nums[0], nums[1]);
            for(int i = 2; i < nums.length; i++){
                int mintop = minheap.peek();
                int maxtop = maxheap.peek();
                int curr = nums[i];
                // �����ڽ�С��һ����
                if (curr < maxtop){
                    if (maxheap.size() <= minheap.size()){
                        maxheap.offer(curr);
                    } else {
                        minheap.offer(maxheap.poll());
                        maxheap.offer(curr);
                    }
                    // �������м�
                } else if (curr >= maxtop && curr <= mintop){
                    if (maxheap.size() <= minheap.size()){
                        maxheap.offer(curr);
                    } else {
                        minheap.offer(curr);
                    }
                    // �����ڽϴ��һ����
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
