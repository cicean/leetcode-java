package AlgorithmTemplate;

public class BinarySearch {

    // version 1: with jiuzhang template
    class Solution_jiuzhang {
        /**
         * @param nums: The integer array.
         * @param target: Target to find.
         * @return: The first position of target. Position starts from 0.
         */
        public int binarySearch(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0, end = nums.length - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    end = mid;
                } else if (nums[mid] < target) {
                    start = mid;
                    // or start = mid + 1
                } else {
                    end = mid;
                    // or end = mid - 1
                }
            }

            if (nums[start] == target) {
                return start;
            }
            if (nums[end] == target) {
                return end;
            }
            return -1;
        }
    }


    // version 2: without jiuzhang template
    class Solution {
        /**
         * @param nums: The integer array.
         * @param target: Target to find.
         * @return: The first position of target. Position starts from 0.
         */
        public int binarySearch(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int start = 0, end = nums.length - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    end = mid;
                } else if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            if (nums[start] == target) {
                return start;
            }

            return -1;
        }
    }

}
