import java.util.*;

/**
 * 969. Pancake Sorting
 * Medium
 *
 * 274
 *
 * 345
 *
 * Add to List
 *
 * Share
 * Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.
 *
 * Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation:
 * We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 * Starting state: A = [3, 2, 4, 1]
 * After 1st flip (k=4): A = [1, 4, 2, 3]
 * After 2nd flip (k=2): A = [4, 1, 2, 3]
 * After 3rd flip (k=4): A = [3, 2, 1, 4]
 * After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
 * Example 2:
 *
 * Input: [1,2,3]
 * Output: []
 * Explanation: The input is already sorted, so there is no need to flip anything.
 * Note that other answers, such as [3, 3], would also be accepted.
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100
 * A[i] is a permutation of [1, 2, ..., A.length]
 * Accepted
 * 21,164
 * Submissions
 * 32,710
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
 * 3
 *
 * Microsoft
 * |
 * 2
 */
public class PancakeSorting {

    /**
     * Approach 1: Sort Largest to Smallest
     * Intuition
     *
     * We can place the largest element (in location i, 1-indexed) by flipping i to move the element to the first position, then A.length to move it to the last position. Afterwards, the largest element is in the correct position, so we no longer need to pancake-flip by A.length or more.
     *
     * We can repeat this process until the array is sorted. Each move will use 2 flips per element.
     *
     * Algorithm
     *
     * First, sort the locations from largest value of A to smallest value of A.
     *
     * For each element (from largest to smallest) with location i, we will first simulate where this element actually is, based on the pancake flips we have done. For a pancake flip f, if i <= f, then the element has moved from location i to f+1 - i.
     *
     * After, we flip by i then N-- to put this element in the correct position.
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the length of A.
     *
     * Space Complexity: O(N)O(N).
     */
    class Solution {
        public List<Integer> pancakeSort(int[] A) {
            List<Integer> ans = new ArrayList();
            int N = A.length;

            Integer[] B = new Integer[N];
            for (int i = 0; i < N; ++i)
                B[i] = i+1;
            Arrays.sort(B, (i, j) -> A[j-1] - A[i-1]);

            for (int i: B) {
                for (int f: ans)
                    if (i <= f)
                        i = f+1 - i;
                ans.add(i);
                ans.add(N--);
            }

            return ans;
        }
    }

    class Solution_reverse {
        public List<Integer> pancakeSort(int[] A) {
            List<Integer> res = new ArrayList<>();
            for (int x = A.length, i; x > 0; --x) {
                for (i = 0; A[i] != x; ++i);
                reverse(A, i + 1);
                res.add(i + 1);
                reverse(A, x);
                res.add(x);
            }
            return res;
        }

        public void reverse(int[] A, int k) {
            for (int i = 0, j = k - 1; i < j; i++, j--) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
    }

    class Solution_jiuzhang {
        /**
         * @param array: an integer array
         * @return: nothing
         */
        public void pancakeSort(int[] array) {
            // Write your code here
            int n = array.length;
            for(int i = n - 1; i > 0; i--) {
                // 执行n-1次，因为最后剩一个最小的在第一个，不用处理。
                int Max = 0;
                for(int j = 1; j <= i; j++ ) {
                    if(array[j] > array[Max]){
                        Max = j;
                    }
                }
                if(Max != 0 && Max != i) {
                    FlipTool.flip(array, Max);
                    FlipTool.flip(array, i);
                } else if(Max == 0) {
                    FlipTool.flip(array, i);
                }
            }
        }
    }
}
