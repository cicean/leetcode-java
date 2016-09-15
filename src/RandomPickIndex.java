import java.util.*;

/**
 * Created by cicean on 9/12/2016.
 * 398. Random Pick Index  QuestionEditorial Solution  My Submissions
 Total Accepted: 956
 Total Submissions: 3855
 Difficulty: Medium
 Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

 Note:
 The array size can be very large. Solution that uses too much extra space will not pass the judge.

 Example:

 int[] nums = new int[] {1,2,3,3,3};
 Solution solution = new Solution(nums);

 // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 solution.pick(3);

 // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 solution.pick(1);
 Hide Company Tags Facebook
 Hide Tags Reservoir Sampling
 Hide Similar Problems (M) Linked List Random Node

 */
public class RandomPickIndex {

    //out of memory limited
     Map<Integer, List<Integer>> map = new HashMap<>();
    public RandomPickIndex(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) map.get(nums[i]).add(i);
            else {
                List<Integer> value =  new ArrayList<>();
                value.add(i);
                map.put(nums[i], value);
            }
        }
    }

    public int pick(int target) {
        int res = -1;
        if (map.containsKey(target)) {
            List<Integer> indexes = map.get(target);
            int i = (int)(Math.random() * indexes.size());
            res = indexes.get(i);
        }

        return res;
    }

    //

    public class Solution {

        int[] nums;

        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            int index = -1;
            int count = 0;
            Random random = new Random();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    count++;
                    int r = random.nextInt(count) + 1;

                    if (r == count) {
                        index = i;
                    }
                }
            }

            return index;
        }
    }
}
