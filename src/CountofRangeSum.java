/**
 * Created by cicean on 8/30/2016.
 * 327. Count of Range Sum  QuestionEditorial Solution  My Submissions
 Total Accepted: 9755
 Total Submissions: 35601
 Difficulty: Hard
 Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

 Note:
 A naive algorithm of O(n2) is trivial. You MUST do better than that.

 Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2,
 Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Divide and Conquer Binary Search Tree
 Hide Similar Problems (H) Count of Smaller Numbers After Self

 给定一个整数组成的数组，求它的所有子区间和坐落于[lower, upper] 的个数。

 比如样例中[-2, 5, -1]中，这三个区间的和在[-2,2]之间 [0, 0], [2, 2], [0, 2]

 */
public class CountofRangeSum {

    /**
     * 先来看看最朴素的O(n^2)算法，首先算出和，然后枚举区间范围。
     */
    public class Solution {
        public int countRangeSum(int[] nums, int lower, int upper) {
            if(nums.length == 0) return 0;
            long[] sum = new long[nums.length + 1];
            for (int i = 0; i < nums.length; i++)
                sum[i + 1] = sum[i] + nums[i];

            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j <= nums.length; j++) {
                    if(lower  <= sum[j] - sum[i] && sum[j] - sum[i] <= upper)
                        ans++;
                }
            }
            return ans;
        }
    }

    /**
     * 题目要求的效率好于O(n^2)的算法，那么要怎么加速呢？

     还记得 Count of Smaller Numbers After Self  么？

     那时候，我们用Fenwick树或者线段树，先离散化，然后从右向左扫描，每扫描一个数，对小于它的求和。然后更新…..

     这题也差不多，需要找满足条件 lower ≤ sum[j] – sum[i – 1] ≤ upper ，也就是lower + sum[i – 1] ≤ sum[j] ≤ upper + sum[i – 1]

     我们同样的求出和，然后离散化，接着从右向左扫描，对每个i 查询满足在[ lower + sum[i – 1], upper + sum[i – 1] ]范围内的个数（用线段树或者Fenwick Tree）

     这样复杂度就是O(n log n)
     */

    //线段树 segmentTree
    public class Solution {
        class SegmentTreeNode {
            SegmentTreeNode left;
            SegmentTreeNode right;
            int count;
            long min;
            long max;
            public SegmentTreeNode(long min, long max) {
                this.min = min;
                this.max = max;
            }
        }
        private SegmentTreeNode buildSegmentTree(Long[] valArr, int low, int high) {
            if(low > high) return null;
            SegmentTreeNode stn = new SegmentTreeNode(valArr[low], valArr[high]);
            if(low == high) return stn;
            int mid = (low + high)/2;
            stn.left = buildSegmentTree(valArr, low, mid);
            stn.right = buildSegmentTree(valArr, mid+1, high);
            return stn;
        }
        private void updateSegmentTree(SegmentTreeNode stn, Long val) {
            if(stn == null) return;
            if(val >= stn.min && val <= stn.max) {
                stn.count++;
                updateSegmentTree(stn.left, val);
                updateSegmentTree(stn.right, val);
            }
        }
        private int getCount(SegmentTreeNode stn, long min, long max) {
            if(stn == null) return 0;
            if(min > stn.max || max < stn.min) return 0;
            if(min <= stn.min && max >= stn.max) return stn.count;
            return getCount(stn.left, min, max) + getCount(stn.right, min, max);
        }

        public int countRangeSum(int[] nums, int lower, int upper) {

            if(nums == null || nums.length == 0) return 0;
            int ans = 0;
            Set<Long> valSet = new HashSet<Long>();
            long sum = 0;
            for(int i = 0; i < nums.length; i++) {
                sum += (long) nums[i];
                valSet.add(sum);
            }

            Long[] valArr = valSet.toArray(new Long[0]);

            Arrays.sort(valArr);
            SegmentTreeNode root = buildSegmentTree(valArr, 0, valArr.length-1);

            for(int i = nums.length-1; i >=0; i--) {
                updateSegmentTree(root, sum);
                sum -= (long) nums[i];
                ans += getCount(root, (long)lower+sum, (long)upper+sum);
            }
            return ans;
        }

    }

    // Fenwick树
    public class Solution2 {
        public int countRangeSum(int[] nums, int lower, int upper) {
            List<Long> cand = new ArrayList<>();
            cand.add(Long.MIN_VALUE); // make sure no number gets a 0-index.
            cand.add(0L);
            long[] sum = new long[nums.length + 1];
            for (int i = 1; i < sum.length; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
                cand.add(sum[i]);
                cand.add(lower + sum[i - 1] - 1);
                cand.add(upper + sum[i - 1]);
            }
            Collections.sort(cand); // finish discretization

            int[] bit = new int[cand.size()];
            for (int i = 0; i < sum.length; i++) plus(bit, Collections.binarySearch(cand, sum[i]), 1);
            int ans = 0;
            for (int i = 1; i < sum.length; i++) {
                plus(bit, Collections.binarySearch(cand, sum[i - 1]), -1);
                ans += query(bit, Collections.binarySearch(cand, upper + sum[i - 1]));
                ans -= query(bit, Collections.binarySearch(cand, lower + sum[i - 1] - 1));
            }
            return ans;
        }

        private void plus(int[] bit, int i, int delta) {
            for (; i < bit.length; i += i & -i) bit[i] += delta;
        }

        private int query(int[] bit, int i) {
            int sum = 0;
            for (; i > 0; i -= i & -i) sum += bit[i];
            return sum;
        }
    }
}
