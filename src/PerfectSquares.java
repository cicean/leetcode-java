import java.util.Arrays;

/**
 *
 * 279. Perfect Squares  QuestionEditorial Solution  My Submissions
 Total Accepted: 51250
 Total Submissions: 149182
 Difficulty: Medium
 Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Dynamic Programming Breadth-first Search Math
 Hide Similar Problems (E) Count Primes (M) Ugly Number II

 * 
 * @author cicean
 * 复杂度

时间 O(N^2) 空间 O(N)

思路

如果一个数x可以表示为一个任意数a加上一个平方数bｘb，也就是x=a+bｘb，
那么能组成这个数x最少的平方数个数，就是能组成a最少的平方数个数加上1（因为b*b已经是平方数了）。
 */
public class PerfectSquares {

	public int numSquares(int n) {
		 int[] dp = new int[n+1];
	        // 将所有非平方数的结果置最大，保证之后比较的时候不被选中
	        Arrays.fill(dp, Integer.MAX_VALUE);
	        // 将所有平方数的结果置1
	        for(int i = 0; i * i <= n; i++){
	            dp[i * i] = 1;
	        }
	        // 从小到大找任意数a
	        for(int a = 0; a <= n; a++){
	            // 从小到大找平方数bｘb
	            for(int b = 0; a + b * b <= n; b++){
	                // 因为a+b*b可能本身就是平方数，所以我们要取两个中较小的
	                dp[a + b * b] = Math.min(dp[a] + 1, dp[a + b * b]);
	            }
	        }
	        return dp[n];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
