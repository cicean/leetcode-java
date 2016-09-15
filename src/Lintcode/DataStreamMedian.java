package Lintcode;

import Comparator;

import java.util.PriorityQueue;

/**
 * /**
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
 * @author cicean
 *
 */

public class DataStreamMedian {

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
