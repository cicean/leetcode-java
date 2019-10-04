import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cicean on 8/29/2016.
 * 315. Count of Smaller Numbers After Self  QuestionEditorial Solution  My Submissions
 Total Accepted: 16572 Total Submissions: 51376 Difficulty: Hard
 You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:

 Given nums = [5, 2, 6, 1]

 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 Return the array [2, 1, 1, 0].

 Hide Company Tags Google
 Hide Tags Divide and Conquer Binary Indexed Tree Segment Tree Binary Search Tree
 Hide Similar Problems (H) Count of Range Sum
 题意：

 给定nums数组，求数组中每个元素i的右边比其小的数

 思路：

 简单的说就是求逆序数。

 使用逆序数有经典的解法为合并排序。
 用Fenwick树  关于Fenwick 树介绍 Binary indexed tree (Fenwick tree)
 简单说就是看当前数在nums中排第几，然后对小于它的数求个数和
 具体的做法是先离散化，确定每个数载nums中排到第几 （去重和排序）
 然后从右向左扫描，每次统计比其小于1的个数（就是求和），然后把当前的数加入Fenwick中。
 */
public class CountofSmallerNumbersAfterSelf {

    /**
     * JAVA solution using merge sort with explanation
     *
     * The basic idea is to do merge sort to nums[]. To record the result, we need to keep the index of each number in the original array. So instead of sort the number in nums, we sort the indexes of each number.
     Example: nums = [5,2,6,1], indexes = [0,1,2,3]
     After sort: indexes = [3,1,0,2]

     While doing the merge part, say that we are merging left[] and right[], left[] and right[] are already sorted.

     We keep a rightcount to record how many numbers from right[] we have added and keep an array count[] to record the result.

     When we move a number from right[] into the new sorted array, we increase rightcount by 1.

     When we move a number from left[] into the new sorted array, we increase count[ index of the number ] by rightcount.
     */

    public class Solution {

        int[] count;
        public List<Integer> countSmaller(int[] nums) {
            List<Integer> res = new ArrayList<Integer>();

            count = new int[nums.length];
            int[] indexes = new int[nums.length];
            for(int i = 0; i < nums.length; i++){
                indexes[i] = i;
            }
            mergesort(nums, indexes, 0, nums.length - 1);
            for(int i = 0; i < count.length; i++){
                res.add(count[i]);
            }
            return res;
        }
        private void mergesort(int[] nums, int[] indexes, int start, int end){
            if(end <= start){
                return;
            }
            int mid = (start + end) / 2;
            mergesort(nums, indexes, start, mid);
            mergesort(nums, indexes, mid + 1, end);

            merge(nums, indexes, start, end);
        }
        private void merge(int[] nums, int[] indexes, int start, int end){
            int mid = (start + end) / 2;
            int left_index = start;
            int right_index = mid+1;
            int rightcount = 0;
            int[] new_indexes = new int[end - start + 1];

            int sort_index = 0;
            while(left_index <= mid && right_index <= end){
                if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                    new_indexes[sort_index] = indexes[right_index];
                    rightcount++;
                    right_index++;
                }else{
                    new_indexes[sort_index] = indexes[left_index];
                    count[indexes[left_index]] += rightcount;
                    left_index++;
                }
                sort_index++;
            }
            while(left_index <= mid){
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightcount;
                left_index++;
                sort_index++;
            }
            while(right_index <= end){
                new_indexes[sort_index++] = indexes[right_index++];
            }
            for(int i = start; i <= end; i++){
                indexes[i] = new_indexes[i - start];
            }
        }
    }



    /**
     * Binary indexed tree (Fenwick tree)
     * This is the Binary Index Tree.

     Here is a very good explanation.

     What is Binary Index Tree

     The basic idea is:

     1, we should build an array with the length equals to the max element of the nums array as BIT.
     2, To avoid minus value in the array, we should first add the (min+1) for every elements
     (It may be out of range, where we can use long to build another array. But no such case in the test cases so far.)
     3, Using standard BIT operation to solve it.
     */

    public class Solution2 {
        public List<Integer> countSmaller(int[] nums) {
            List<Integer> res = new LinkedList<Integer>();
            if (nums == null || nums.length == 0) {
                return res;
            }
            // find min value and minus min by each elements, plus 1 to avoid 0 element
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                min = (nums[i] < min) ? nums[i]:min;
            }
            int[] nums2 = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                nums2[i] = nums[i] - min + 1;
                max = Math.max(nums2[i],max);
            }
            int[] tree = new int[max+1];
            for (int i = nums2.length-1; i >= 0; i--) {
                res.add(0,get(nums2[i]-1,tree));
                update(nums2[i],tree);
            }
            return res;
        }
        private int get(int i, int[] tree) {
            int num = 0;
            while (i > 0) {
                num +=tree[i];
                i -= i&(-i);
            }
            return num;
        }
        private void update(int i, int[] tree) {
            while (i < tree.length) {
                tree[i] ++;
                i += i & (-i);
            }
        }
    }


    /**
     * Use Segment TreeNode
     * Complexity

     Time: O(nlgn)

     Space: o(n)
     */
    public class Solution3 {
        /**
         * @param A: An integer array
         * @return: Count the number of element before this element 'ai' is
         *          smaller than it and return count number array
         */
        class SegmentTreeNode {
            public int start, end;
            public int count;
            public SegmentTreeNode left, right;
            public SegmentTreeNode(int start, int end, int count) {
                this.start = start;
                this.end = end;
                this.count = count;
                this.left = this.right = null;
            }
        }
        SegmentTreeNode root;
        public SegmentTreeNode build(int start, int end) {
            // write your code here
            if(start > end) {  // check core case
                return null;
            }

            SegmentTreeNode root = new SegmentTreeNode(start, end, 0);

            if(start != end) {
                int mid = (start + end) / 2;
                root.left = build(start, mid);
                root.right = build(mid+1, end);
            } else {
                root.count =  0;
            }
            return root;
        }
        public int querySegmentTree(SegmentTreeNode root, int start, int end) {
            // write your code here
            if(start == root.start && root.end == end) { // 相等
                return root.count;
            }


            int mid = (root.start + root.end)/2;
            int leftcount = 0, rightcount = 0;
            // 左子区
            if(start <= mid) {
                if( mid < end) { // 分裂
                    leftcount =  querySegmentTree(root.left, start, mid);
                } else { // 包含
                    leftcount = querySegmentTree(root.left, start, end);
                }
            }
            // 右子区
            if(mid < end) { // 分裂 3
                if(start <= mid) {
                    rightcount = querySegmentTree(root.right, mid+1, end);
                } else { //  包含
                    rightcount = querySegmentTree(root.right, start, end);
                }
            }
            // else 就是不相交
            return leftcount + rightcount;
        }
        public void modifySegmentTree(SegmentTreeNode root, int index, int value) {
            // write your code here
            if(root.start == index && root.end == index) { // 查找到
                root.count += value;
                return;
            }

            // 查询
            int mid = (root.start + root.end) / 2;
            if(root.start <= index && index <=mid) {
                modifySegmentTree(root.left, index, value);
            }

            if(mid < index && index <= root.end) {
                modifySegmentTree(root.right, index, value);
            }
            //更新
            root.count = root.left.count + root.right.count;
        }
        public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
            // write your code here
            root = build(0, 10000);
            ArrayList<Integer> ans = new ArrayList<Integer>();
            int res;
            for(int i = 0; i < A.length; i++) {
                res = 0;
                if(A[i] > 0) {
                    res = querySegmentTree(root, 0, A[i]-1);
                }
                modifySegmentTree(root, A[i], 1);
                ans.add(res);
            }
            return ans;
        }
    }
}
