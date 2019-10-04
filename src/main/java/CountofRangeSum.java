/**
 * Created by cicean on 8/30/2016.
 * 327. Count of Range Sum  QuestionEditorial Solution  My Submissions
 Total Accepted: 9755
 Total Submissions: 35601
 Difficulty: Hard
 Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i �� j), inclusive.

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

 ����һ��������ɵ����飬�����������������������[lower, upper] �ĸ�����

 ����������[-2, 5, -1]�У�����������ĺ���[-2,2]֮�� [0, 0], [2, 2], [0, 2]

 */
public class CountofRangeSum {

    /**
     * �������������ص�O(n^2)�㷨����������ͣ�Ȼ��ö�����䷶Χ��
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
     * ��ĿҪ���Ч�ʺ���O(n^2)���㷨����ôҪ��ô�����أ�

     ���ǵ� Count of Smaller Numbers After Self  ô��

     ��ʱ��������Fenwick�������߶���������ɢ����Ȼ���������ɨ�裬ÿɨ��һ��������С��������͡�Ȼ����¡�..

     ����Ҳ��࣬��Ҫ���������� lower �� sum[j] �C sum[i �C 1] �� upper ��Ҳ����lower + sum[i �C 1] �� sum[j] �� upper + sum[i �C 1]

     ����ͬ��������ͣ�Ȼ����ɢ�������Ŵ�������ɨ�裬��ÿ��i ��ѯ������[ lower + sum[i �C 1], upper + sum[i �C 1] ]��Χ�ڵĸ��������߶�������Fenwick Tree��

     �������ӶȾ���O(n log n)
     */

    //�߶��� segmentTree
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

    // Fenwick��
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
