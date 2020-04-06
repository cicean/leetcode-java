/**
 * 1300. Sum of Mutated Array Closest to Target
 * Medium
 *
 * 123
 *
 * 16
 *
 * Add to List
 *
 * Share
 * Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.
 *
 * In case of a tie, return the minimum such integer.
 *
 * Notice that the answer is not neccesarilly a number from arr.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [4,9,3], target = 10
 * Output: 3
 * Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
 * Example 2:
 *
 * Input: arr = [2,3,5], target = 10
 * Output: 5
 * Example 3:
 *
 * Input: arr = [60864,25176,27249,21296,20204], target = 56803
 * Output: 11361
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 * Accepted
 * 5,198
 * Submissions
 * 11,340
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * LeetCode
 * If you draw a graph with the value on one axis and the absolute difference between the target and the array sum, what will you get?
 * That graph is uni-modal.
 * Use ternary search on that graph to find the best value.
 */
public class SumofMutatedArrayClosesttoTarget {
    class Solution {
        /**
         * Binary search solution
         * See also a sorting solution below.
         *
         * The value we are looking for is somewhere between 1 and maxValue (m).
         * Now use Binary search to speed up the process.
         *
         * go up if the sum is too small
         * go down if the sum is too big
         * When we are done with binary search, l and r are equal, but it might happen that we have not exactly reached the target.
         * Check if l-1 (should get us below the target) leads to the sum closer to the target.
         *
         * Java, 4ms:
         * @param arr
         * @param target
         * @return
         */
        public int findBestValue(int[] arr, int target) {
            int l, r, mi, s=0, m=-1;
            for(int v:arr) { s += v; m=Math.max(m,v); }

            if(s<=target) return m; // return the max value since we will keep all nums as is

            for(l=1,r=m;l<r;) {
                mi=(l+r)/2;
                s=0;
                for(int v:arr) s += (v>mi) ? mi:v;
                if(s>=target) r=mi;
                else          l=mi+1;
            }
            // check if we are 1 step off the target
            int s1=0,s2=0;
            for(int v:arr) {
                s1 += (v>l)?(l):v;
                s2 += (v>l-1)?(l-1):v;
            }

            return (Math.abs(s2-target) <= Math.abs(s1-target)) ? l-1 : l;
        }
    }


    class Solution_1 {
        // https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/discuss/482387/Binary-Search-JAVA-solution!
        public int findBestValue(int[] arr, int target) {
            int left = 0;
            int right = target;

            while(left < right){
                int mid = (left + right)/2;
                int sum = getSum(arr, mid);
                if(sum < target){
                    left = mid+1;
                }else{
                    right = mid;
                }
            }

            if( Math.abs(target - getSum(arr, left)) >= Math.abs(target - getSum(arr, left-1))){
                return left-1;
            }else{
                return left;
            }
        }

        public int getSum(int[] arr, int value){
            int sum = 0;
            for(int ele: arr){
                sum += (ele>value)?value: ele;
            }
            return sum;
        }
    }
}
