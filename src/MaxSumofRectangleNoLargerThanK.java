import java.util.TreeSet;

/**
 * Created by cicean on 9/1/2016.
 * 363. Max Sum of Rectangle No Larger Than K  QuestionEditorial Solution  My Submissions
 Total Accepted: 4693 Total Submissions: 15562 Difficulty: Hard
 Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

 Example:
 Given matrix = [
 [1,  0, 1],
 [0, -2, 3]
 ]
 k = 2
 The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

 Note:
 The rectangle inside the matrix must have an area > 0.
 What if the number of rows is much larger than the number of columns?
 Credits:
 Special thanks to @fujiaozhu for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Binary Search Dynamic Programming Queue

 *
 * 题意：给定一个矩阵，求子矩阵中的和不超过K的最大值
 */
public class MaxSumofRectangleNoLargerThanK {

    /**
     * 思路：

     朴素的思想为，枚举起始行，枚举结束行，枚举起始列，枚举终止列。。。。。O(m^2 * n^2)

     这里用到一个技巧就是，进行求和时，我们可以把二维的合并成一维，然后就变为求一维的解。

     比如对于矩阵：

     [1, 0, 1],
     [0, -2, 3]

     进行起始行为0，终止行为1时，可以进行列的求和，即[1, -2, 4]中不超过k的最大值。

     求和的问题解决完，还有一个是不超过k. 这里我参考了 https://leetcode.com/discuss/109705/java-binary-search-solution-time-complexity-min-max-log-max 的方法

     使用了二分搜索。对于当前的和为sum，我们只需要找到一个最小的数x，使得 sum – k <=x，这样可以保证sum – x <=k。

     这里需要注意，当行远大于列的时候怎么办呢？转换成列的枚举 即可。

     在代码实现上，我们只需要让 m 永远小于 n即可。这样复杂度总是为O(m^2*n*log n)
     */
    //Java Binary Search solution time complexity min(m,n)^2*max(m,n)*log(max(m,n))
    /* first  consider the situation matrix is 1D
    we can save every sum of 0~i(0<=i<len) and binary search previous sum to find
    possible result for every index, time complexity is O(NlogN).
    so in 2D matrix, we can sum up all values from row i to row j and create a 1D array
    to use 1D array solution.
    If col number is less than row number, we can sum up all values from col i to col j
    then use 1D array solution.
*/
    public int maxSumSubmatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row==0)return 0;
        int col = matrix[0].length;
        int m = Math.min(row,col);
        int n = Math.max(row,col);
        //indicating sum up in every row or every column
        boolean colIsBig = col>row;
        int res = Integer.MIN_VALUE;
        for(int i = 0;i<m;i++){
            int[] array = new int[n];
            // sum from row j to row i
            for(int j = i;j>=0;j--){
                int val = 0;
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(0);
                //traverse every column/row and sum up
                for(int k = 0;k<n;k++){
                    array[k]=array[k]+(colIsBig?matrix[j][k]:matrix[k][j]);
                    val = val + array[k];
                    //use  TreeMap to binary search previous sum to get possible result
                    Integer subres = set.ceiling(val-target);
                    if(null!=subres){
                        res=Math.max(res,val-subres);
                    }
                    set.add(val);
                }
            }
        }
        return res;
    }

}
