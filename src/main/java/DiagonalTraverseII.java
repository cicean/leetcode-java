/**
 * 1424. Diagonal Traverse II
 * Medium
 *
 * 178
 *
 * 25
 *
 * Add to List
 *
 * Share
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 * Example 2:
 *
 *
 *
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 * Example 3:
 *
 * Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * Output: [1,4,2,5,3,8,6,9,7,10,11]
 * Example 4:
 *
 * Input: nums = [[1,2,3,4,5,6]]
 * Output: [1,2,3,4,5,6]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i].length <= 10^5
 * 1 <= nums[i][j] <= 10^9
 * There at most 10^5 elements in nums.
 * Accepted
 * 9,371
 * Submissions
 * 24,029
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
 * 2
 * Notice that numbers with equal sums of row and column indexes belong to the same diagonal.
 * Store them in tuples (sum, row, val), sort them, and then regroup the answer.
 */
public class DiagonalTraverseII {

    class Solution {
        public int[] findDiagonalOrder(List<List<Integer>> nums) {
            // find the largest sum of indices
            int max = 0;
            for (int i = 0; i < nums.size(); i++) {
                List<Integer> numList = nums.get(i);
                for (int j = 0; j < numList.size(); j++) {
                    int idSum = i + j;
                    max = Math.max(idSum, max);
                }
            }
            // counting sort
            int[] count = new int[max+1]; // Need to use long/double depending on data size
            for (int i = 0; i < nums.size(); i++) {
                List<Integer> numList = nums.get(i);
                for (int j = 0; j < numList.size(); j++) {
                    count[i+j]++;
                }
            }
            for (int i = 1; i < count.length; i++) {
                count[i] = count[i-1] + count[i];
            }

            int[] res = new int[count[max]];
            // count[i] is the count of elements with index sum <= i
            for (int i = 0; i < nums.size(); i++) {
                List<Integer> numList = nums.get(i);
                for (int j = 0; j < numList.size(); j++) {
                    int idSum = i + j;
                    res[--count[idSum]] = numList.get(j);
                }
            }
            return res;

        }

        class Solution {
            public int[] findDiagonalOrder(List<List<Integer>> nums) {
                int n = 0, i = 0, maxKey = 0;
                Map<Integer, List<Integer>> map = new HashMap<>();
                for (int r = nums.size() - 1; r >= 0; --r) { // The values from the bottom rows are the starting values of the diagonals.
                    for (int c = 0; c < nums.get(r).size(); ++c) {
                        map.putIfAbsent(r + c, new ArrayList<>());
                        map.get(r + c).add(nums.get(r).get(c));
                        maxKey = Math.max(maxKey, r + c);
                        n++;
                    }
                }
                int[] ans = new int[n];
                for (int key = 0; key <= maxKey; ++key) {
                    List<Integer> value = map.get(key);
                    if (value == null) continue;
                    for (int v : value) ans[i++] = v;
                }
                return ans;
            }
        }

    }

}
