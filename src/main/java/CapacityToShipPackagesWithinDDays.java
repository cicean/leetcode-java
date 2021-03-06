/**
 * 1011. Capacity To Ship Packages Within D Days
 * Medium
 *
 * 433
 *
 * 12
 *
 * Favorite
 *
 * Share
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 *
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 *
 *
 *
 * Example 1:
 *
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation:
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 *
 * Input: weights = [3,2,2,4,1,4], D = 3
 * Output: 6
 * Explanation:
 * A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 *
 * Input: weights = [1,2,3,1,1], D = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 *
 *
 * Note:
 *
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 * Accepted
 * 16,499
 * Submissions
 * 30,232
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * gusmt66
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 7
 *
 * Uber
 * |
 * 2
 * Binary search on the answer. We need a function possible(capacity) which returns true if and only if we can do the task in D days.
 */
public class CapacityToShipPackagesWithinDDays {

    class Solution {
        public int shipWithinDays(int[] weights, int D) {
            int left = 0, right = 0;
            for (int w: weights) {
                left = Math.max(left, w);
                right += w;
            }
            while (left < right) {
                int mid = (left + right) / 2, need = 1, cur = 0;
                for (int w: weights) {
                    if (cur + w > mid) {
                        need += 1;
                        cur = 0;
                    }
                    cur += w;
                }
                if (need > D) left = mid + 1;
                else right = mid;
            }
            return left;
        }
    }

    class Solution_1 {
        public int shipWithinDays(int[] nums, int D) {
            int low = Integer.MIN_VALUE, high = 0;
            for(int val : nums){
                low = Math.max(low, val);
                high = high + val;
            }
            //int ans = high;
            high = low * nums.length / D + 1;;
            while(low <= high){
                int mid = low + (high - low) / 2;
                int val = solve(nums, mid);

                if(val > D){
                    low = mid + 1;
                }
                else{
                    high = mid - 1;
                }
            }
            return low;
        }

        int solve(int[] nums, int target){
            int cuts = 1;
            int sum = 0;
            for(int val: nums){
                sum = sum + val;
                if(sum > target){
                    cuts++;
                    sum = val;
                }
            }
            return cuts;
        }
    }

}