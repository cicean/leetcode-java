package PocketGems;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by cicean on 9/23/2016.
 */
public class SlidingWindowMinimum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int[] result = new int[nums.length - k + 1];

        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0, j = 0; j < result.length; i++) {
            while (deque.size() != 0 && nums[deque.peekFirst()] > nums[i]) {
                deque.removeFirst();
            }
            deque.addFirst(i);
            if (i < k - 1) continue;
            if (deque.peekLast() <= i - k) deque.removeLast();
            result[j++] = nums[deque.peekLast()];
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SlidingWindowMinimum slt = new SlidingWindowMinimum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = slt.maxSlidingWindow(nums, k);

    }
}