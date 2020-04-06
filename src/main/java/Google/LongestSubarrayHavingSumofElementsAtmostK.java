package Google;

/**
 * Longest Subarray having sum of elements atmost ‘k’
 * Given an array of integers, our goal is to find the length of largest subarray having sum of its elements atmost ‘k’ where k>0.
 *
 * Examples:
 *
 * Input : arr[] = {1, 2, 1, 0, 1, 1, 0},
 *            k = 4
 * Output : 5
 * Explanation:
 *  {1, 2, 1} => sum = 4, length = 3
 *  {1, 2, 1, 0}, {2, 1, 0, 1} => sum = 4, length = 4
 *  {1, 0, 1, 1, 0} =>5 sum = 3, length = 5
 *
 *
 *
 *
 * Recommended: Please try your approach on {IDE} first, before moving on to the solution.
 * Method 1 (Brute Force)
 * Find all the subarrays whose sum is less than or equal to k and return the one with largest length.
 * Time Complexity : O(n^2)
 *
 * Method 2 (Efficient):
 * An efficient approach is to use sliding window technique.
 *
 * Traverse the array and check if on adding the current element its sum is less than or equal to k.
 * If it’s less than k then add it to sum and increase the count.
 * Else
 * Remove the first element of subarray and decrease the count.
 * Again check if on adding the current element its sum is less than or equal to k.
 * If it’s less than k then add it to sum and increase the count.
 * Keep track of Maximum count.
 *
 */

public class LongestSubarrayHavingSumofElementsAtmostK {
    // Java program to find longest subarray with
// sum of elements at-least k.

    class GFG {

        // function to find the length of largest
        // subarray having sum atmost k.
        public int atMostSum(int arr[], int n,
                                    int k)
        {
            int sum = 0;
            int cnt = 0, maxcnt = 0;

            for (int i = 0; i < n; i++) {

                // If adding current element doesn't
                // cross limit add it to current window
                if ((sum + arr[i]) <= k) {
                    sum += arr[i];
                    cnt++;
                }

                // Else, remove first element of current
                // window.
                else if(sum!=0)
                {
                    sum = sum - arr[i - cnt] + arr[i];
                }

                // keep track of max length.
                maxcnt = Math.max(cnt, maxcnt);
            }
            return maxcnt;
        }

        /* Driver program to test above function */
        public void main(String[] args)
        {
            int arr[] = { 1, 2, 1, 0, 1, 1, 0 };
            int n = arr.length;
            int k = 4;

            System.out.print(atMostSum(arr, n, k));

        }
    }
// This code is contributed by Arnav Kr. Mandal.

}
