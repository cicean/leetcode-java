/**
 * Created by cicean on 8/29/2016.
 *
 * 307. Range Sum Query - Mutable  QuestionEditorial Solution  My Submissions
 Total Accepted: 17783 Total Submissions: 96112 Difficulty: Medium
 Given an integer array nums, find the sum of the elements between indices i and j (i �� j), inclusive.

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
     * �������֮ǰ�ǵ�Range Sum Query - Immutable ����ͼ��� - ���ɱ�����죬֮ǰ�ǵ���������������ݲ���ı䣬��������ֻ��Ҫ����һ���ۼ�����Ϳ���֧�ֿ��ٵļ�������ֵ�ˣ��������˵��������ݻ�ı䣬������ǻ�����֮ǰ�ķ��������ۼƺ����飬��ôÿ�ı�һ�����֣�֮������λ�õ����ֶ�Ҫ�ı䣬��������кܶ���²����Ļ����ͻ�ʮ�ֲ���Ч�����������Ҫʹ��һ���µ����ݽṹ��������״����Binary Indexed Tree������һ�ֲ�ѯ���޸ĸ��ӶȾ�ΪO(logn)�����ݽṹ�������״����Ƚ�����˼�����е�����λ�õ����ֺ�ԭ�����Ӧλ�õ���ͬ��ż��λ����ԭ��������λ��֮�ͣ�����ԭ����A(a1, a2, a3, a4 ...)�������Ӧ����״����C(c1, c2, c3, c4 ...)�����¹�ϵ��



     C1 = A1
     C2 = A1 + A2
     C3 = A3
     C4 = A1 + A2 + A3 + A4
     C5 = A5
     C6 = A5 + A6
     C7 = A7
     C8 = A1 + A2 + A3 + A4 + A5 + A6 + A7 + A8
     ...
     ��ô�����ȷ��ĳ��λ�õ������м�������ɵ��أ�ԭ���Ǹ�����������λLow Bit�������ģ���ν�����λ�����Ƕ������������ұߵ�һ��1��ʼ���Ӻ�����0(����еĻ�)��ɵ����֣�����1��8�����λ��������ʾ��

     ����          ������          ���λ

     1               0001          1

     2               0010          2

     3               0011          1

     4               0100          4

     5               0101          1

     6               0110          2

     7               0111          1

     8               1000          8

     ...

     ���λ�ļ��㷽�������֣�һ����x&(x^(x�C1))����һ�������ò�������x&-x��

     ����������ȸ��ݸ����������齨��һ����״����bit��Ȼ�����ĳһλ����ʱ��
     �������λ��ֵ�����º��溬����һλ���ֵĵط���
     һ��ֻ��Ҫ���²���ż��λ�õ�ֵ���ɣ��ڼ���ĳһλ�õ�ǰ׺��ʱ��
     ������״���������Ҳ�ܸ�Ч�ĺܿ���������μ��������£�
     *
     * https://www.hrwhisper.me/leetcode-range-sum-query-mutable/
     */

    //Java using Binary Indexed Tree with clear explanation
     public class NumArray {

        // ʹ����״����ʵ�ֵĴ���AC�����Ӷ�ΪO(logn)
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
     * �ⷨI���߶�����Segment Tree���Ļ���Ӧ��
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
