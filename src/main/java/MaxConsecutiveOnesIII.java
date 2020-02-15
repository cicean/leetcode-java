/**
 * 1004. Max Consecutive Ones III
 * Medium
 *
 * 615
 *
 * 14
 *
 * Add to List
 *
 * Share
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 *
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * Output: 6
 * Explanation:
 * [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 * Example 2:
 *
 * Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * Output: 10
 * Explanation:
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] is 0 or 1
 * Accepted
 * 32,697
 * Submissions
 * 57,973
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 9
 *
 * Microsoft
 * |
 * 3
 *
 * Yandex
 * |
 * 2
 * Longest Substring with At Most K Distinct Characters
 * Hard
 * Longest Repeating Character Replacement
 * Medium
 * Max Consecutive Ones
 * Easy
 * Max Consecutive Ones II
 * Medium
 * One thing's for sure, we will only flip a zero if it extends an existing window of 1s. Otherwise, there's no point in doing it, right? Think Sliding Window!
 * Since we know this problem can be solved using the sliding window construct, we might as well focus in that direction for hints. Basically, in a given window, we can never have > K zeros, right?
 * We don't have a fixed size window in this case. The window size can grow and shrink depending upon the number of zeros we have (we don't actually have to flip the zeros here!).
 * The way to shrink or expand a window would be based on the number of zeros that can still be flipped and so on.
 */
public class MaxConsecutiveOnesIII {

    /**
     * Solution
     * The problem has asked for longest contiguous subarray that contains only 1s. What makes this problem a little trickier is the K flips allowed from 0 --> 1. This means a contiguous subarray of 1's might not just contain 1's but also may contain some 0's. The number of 0's allowed in a given subarray is given by K.
     *
     *
     *
     * The above diagram shows the `flip` which helped us to get the longest contiguous subarray if only one flip was allowed.
     * Approach: Sliding Window
     * Intuition
     *
     * To find the longest subarray with contiguous 1's we might need to find all the subarrays first. But do we really need to do that? If we find all the subarrays we are essentially finding out so many unnecessary overlapping subarrays too.
     *
     * We can use a simple sliding window approach to solve this problem.
     *
     * In any sliding window based problem we have two pointers. One right pointer whose job is to expand the current window and then we have the left pointer whose job is to contract a given window. At any point in time only one of these pointers move and the other one remains fixed.
     *
     * The solution is pretty intuitive. We keep expanding the window by moving the right pointer. When the window has reached the limit of 0's allowed, we contract (if possible) and save the longest window till now.
     *
     * The answer is the longest desirable window.
     *
     * Algorithm
     *
     * Initialize two pointers. The two pointers help us to mark the left and right end of the window/subarray with contiguous 1's.
     *
     * left = 0, right = 0, window_size = 0
     *
     * We use the right pointer to expand the window until the window/subarray is desirable. i.e. number of 0's in the window are in the allowed range of [0, K].
     *
     * Once we have a window which has more than the allowed number of 0's, we can move the left pointer ahead one by one until we encounter 0 on the left too. This step ensures we are throwing out the extra zero.
     *
     *
     *
     * Note: As suggested in the discussion forum. We can solve this problem a little efficiently. Since we have to find the MAXIMUM window, we never reduce the size of the window. We either increase the size of the window or remain same but never reduce the size.
     *
     *
     *
     * Observe we don't contract the window if it's not needed and thus save on some computation.
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the number of elements in the array. In worst case we might end up visiting every element of array twice, once by left pointer and once by right pointer.
     *
     * Space Complexity: O(1)O(1). We do not use any extra space.
     */
    class Solution {
        public int longestOnes(int[] A, int K) {
            int left = 0, right;
            for (right = 0; right < A.length; right++) {
                // If we included a zero in the window we reduce the value of K.
                // Since K is the maximum zeros allowed in a window.
                if (A[right] == 0) K--;
                // A negative K denotes we have consumed all allowed flips and window has
                // more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
                if (K < 0) {
                    // If the left element to be thrown out is zero we increase K.
                    if (A[left] == 0) K++;
                    left++;
                }
            }
            return right - left;
        }
    }

    class Solution_2 {
        public int longestOnes(int[] A, int K) {
            int zeroCount=0,start=0,res=0;
            for(int end=0;end<A.length;end++){
                if(A[end] == 0) zeroCount++;
                while(zeroCount > K){
                    if(A[start] == 0) zeroCount--;
                    start++;
                }
                res=Math.max(res,end-start+1);
            }
            return res;
        }
    }
}
