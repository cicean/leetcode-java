/**
 * Created by cicean on 8/29/2016.
 *
 * 307. Range Sum Query - Mutable  QuestionEditorial Solution  My Submissions
 Total Accepted: 17783 Total Submissions: 96112 Difficulty: Medium
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 The update(i, val) function modifies nums by updating the element at index i to val.
 Example:
 Given nums = [1, 3, 5]

 sumRange(0, 2) -> 9
 update(1, 2)
 sumRange(0, 2) -> 8
 Note:
 The array is only modifiable by the update function.
 You may assume the number of calls to update and sumRange function is distributed evenly.
 Hide Tags Segment Tree Binary Indexed Tree
 Hide Similar Problems (E) Range Sum Query - Immutable (H) Range Sum Query 2D - Mutable

 */
public class RangeSumQueryMutable {

    /**
     * 这道题是之前那道Range Sum Query - Immutable 区域和检索 - 不可变的延伸，之前那道题由于数组的内容不会改变，所以我们只需要建立一个累计数组就可以支持快速的计算区间值了，而这道题说数组的内容会改变，如果我们还是用之前的方法建立累计和数组，那么每改变一个数字，之后所有位置的数字都要改变，这样如果有很多更新操作的话，就会十分不高效。这道题我们要使用一种新的数据结构，叫做树状数组Binary Indexed Tree，这是一种查询和修改复杂度均为O(logn)的数据结构。这个树状数组比较有意思，所有的奇数位置的数字和原数组对应位置的相同，偶数位置是原数组若干位置之和，假如原数组A(a1, a2, a3, a4 ...)，和其对应的树状数组C(c1, c2, c3, c4 ...)有如下关系：



     C1 = A1
     C2 = A1 + A2
     C3 = A3
     C4 = A1 + A2 + A3 + A4
     C5 = A5
     C6 = A5 + A6
     C7 = A7
     C8 = A1 + A2 + A3 + A4 + A5 + A6 + A7 + A8
     ...
     那么是如何确定某个位置到底是有几个数组成的呢，原来是根据坐标的最低位Low Bit来决定的，所谓的最低位，就是二进制数的最右边的一个1开始，加厚后面的0(如果有的话)组成的数字，例如1到8的最低位如下面所示：

     坐标          二进制          最低位

     1               0001          1

     2               0010          2

     3               0011          1

     4               0100          4

     5               0101          1

     6               0110          2

     7               0111          1

     8               1000          8

     ...

     最低位的计算方法有两种，一种是x&(x^(x–1))，另一种是利用补码特性x&-x。

     这道题我们先根据给定输入数组建立一个树状数组bit，然后更新某一位数字时，
     根据最低位的值来更新后面含有这一位数字的地方，
     一般只需要更新部分偶数位置的值即可，在计算某一位置的前缀和时，
     利用树状数组的性质也能高效的很快算出来，参见代码如下：
     *
     * https://www.hrwhisper.me/leetcode-range-sum-query-mutable/
     */

    //Java using Binary Indexed Tree with clear explanation
     public class NumArray {

        // 使用树状数组实现的代码AC，复杂度为O(logn)
        /**
         * Binary Indexed Trees (BIT or Fenwick tree):
         * https://www.topcoder.com/community/data-science/data-science-
         * tutorials/binary-indexed-trees/
         *
         * Example: given an array a[0]...a[7], we use a array BIT[9] to
         * represent a tree, where index [2] is the parent of [1] and [3], [6]
         * is the parent of [5] and [7], [4] is the parent of [2] and [6], and
         * [8] is the parent of [4]. I.e.,
         *
         * BIT[] as a binary tree:
         *            ______________*
         *            ______*
         *            __*     __*
         *            *   *   *   *
         * indices: 0 1 2 3 4 5 6 7 8
         *
         * BIT[i] = ([i] is a left child) ? the partial sum from its left most
         * descendant to itself : the partial sum from its parent (exclusive) to
         * itself. (check the range of "__").
         *
         * Eg. BIT[1]=a[0], BIT[2]=a[1]+BIT[1]=a[1]+a[0], BIT[3]=a[2],
         * BIT[4]=a[3]+BIT[3]+BIT[2]=a[3]+a[2]+a[1]+a[0],
         * BIT[6]=a[5]+BIT[5]=a[5]+a[4],
         * BIT[8]=a[7]+BIT[7]+BIT[6]+BIT[4]=a[7]+a[6]+...+a[0], ...
         *
         * Thus, to update a[1]=BIT[2], we shall update BIT[2], BIT[4], BIT[8],
         * i.e., for current [i], the next update [j] is j=i+(i&-i) //double the
         * last 1-bit from [i].
         *
         * Similarly, to get the partial sum up to a[6]=BIT[7], we shall get the
         * sum of BIT[7], BIT[6], BIT[4], i.e., for current [i], the next
         * summand [j] is j=i-(i&-i) // delete the last 1-bit from [i].
         *
         * To obtain the original value of a[7] (corresponding to index [8] of
         * BIT), we have to subtract BIT[7], BIT[6], BIT[4] from BIT[8], i.e.,
         * starting from [idx-1], for current [i], the next subtrahend [j] is
         * j=i-(i&-i), up to j==idx-(idx&-idx) exclusive. (However, a quicker
         * way but using extra space is to store the original array.)
         */

        int[] nums;
        int[] BIT;
        int n;

        public NumArray(int[] nums) {
            this.nums = nums;

            n = nums.length;
            BIT = new int[n + 1];
            for (int i = 0; i < n; i++)
                init(i, nums[i]);
        }

        public void init(int i, int val) {
            i++;
            while (i <= n) {
                BIT[i] += val;
                i += (i & -i);
            }
        }

        void update(int i, int val) {
            int diff = val - nums[i];
            nums[i] = val;
            init(i, diff);
        }

        public int getSum(int i) {
            int sum = 0;
            i++;
            while (i > 0) {
                sum += BIT[i];
                i -= (i & -i);
            }
            return sum;
        }

        public int sumRange(int i, int j) {
            return getSum(j) - getSum(i - 1);
        }
    }


    /**
     *Java Solution 1 - Segment Tree
     * 解法I：线段树（Segment Tree）的基础应用
     * http://bookshadow.com/weblog/2015/08/13/segment-tree-set-1-sum-of-given-range/
     *
     */

    class TreeNode{
        int start;
        int end;
        int sum;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int left, int right, int sum){
            this.start=left;
            this.end=right;
            this.sum=sum;
        }
        public TreeNode(int left, int right){
            this.start=left;
            this.end=right;
            this.sum=0;
        }
    }

    public class NumArray_st {
        TreeNode root = null;

        public NumArray(int[] nums) {
            if(nums==null || nums.length==0)
                return;

            this.root = buildTree(nums, 0, nums.length-1);
        }

        void update(int i, int val) {
            updateHelper(root, i, val);
        }

        void updateHelper(TreeNode root, int i, int val){
            if(root==null)
                return;



            int mid = root.start + (root.end-root.start)/2;
            if(i<=mid){
                updateHelper(root.leftChild, i, val);
            }else{
                updateHelper(root.rightChild, i, val);
            }

            if(root.start==root.end&& root.start==i){
                root.sum=val;
                return;
            }

            root.sum=root.leftChild.sum+root.rightChild.sum;
        }

        public int sumRange(int i, int j) {
            return sumRangeHelper(root, i, j);
        }

        public int sumRangeHelper(TreeNode root, int i, int j){
            if(root==null || j<root.start || i > root.end ||i>j )
                return 0;

            if(i<=root.start && j>=root.end){
                return root.sum;
            }
            int mid = root.start + (root.end-root.start)/2;
            int result = sumRangeHelper(root.leftChild, i, Math.min(mid, j))
                    +sumRangeHelper(root.rightChild, Math.max(mid+1, i), j);

            return result;
        }

        public TreeNode buildTree(int[] nums, int i, int j){
            if(nums==null || nums.length==0|| i>j)
                return null;

            if(i==j){
                return new TreeNode(i, j, nums[i]);
            }

            TreeNode current = new TreeNode(i, j);

            int mid = i + (j-i)/2;

            current.leftChild = buildTree(nums, i, mid);
            current.rightChild = buildTree(nums, mid+1, j);

            current.sum = current.leftChild.sum+current.rightChild.sum;

            return current;
        }
    }






}
