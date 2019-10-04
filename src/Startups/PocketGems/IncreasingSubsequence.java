package PocketGems;

import datastructure.PrintList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 9/22/2016.
 * <p>
 * 给?一个array，找到其中三个index依次增加的数，并且数值也是依次增加。
 * 要求O(n) time O(1) extra space。
 */
public class IncreasingSubsequence {
    public List<Integer> increasingSubsequence(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;

        int waitinglist = nums[0], low = nums[0], median = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > low && nums[i] > median) {
                res.add(low);
                res.add(median);
                res.add(nums[i]);
                break;
            } else if (nums[i] < median && nums[i] > low) {
                median = nums[i];
            } else if (nums[i] < low) {
                if (nums[i] < waitinglist) {
                    waitinglist = nums[i];
                } else if (nums[i] > waitinglist){
                    low = waitinglist;
                    median = nums[i];
                }
            }
        }

        return res.size() < 3 ? new ArrayList<>() : res;

    }
    
    public static void main(String[] args) {
		IncreasingSubsequence slt = new IncreasingSubsequence();
		PrintList<Integer> res = new PrintList<>();
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {1,1,1, 1, 2,2,2,8,1,1,1};
		res.printList(slt.increasingSubsequence(nums2));
	}

}
