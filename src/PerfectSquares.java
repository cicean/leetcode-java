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
 * ���Ӷ�

ʱ�� O(N^2) �ռ� O(N)

˼·

���һ����x���Ա�ʾΪһ��������a����һ��ƽ����b��b��Ҳ����x=a+b��b��
��ô����������x���ٵ�ƽ�������������������a���ٵ�ƽ������������1����Ϊb*b�Ѿ���ƽ�����ˣ���
 */
public class PerfectSquares {

	//DP
	public int numSquares(int n) {
		 int[] dp = new int[n+1];
	        // �����з�ƽ�����Ľ������󣬱�֤֮��Ƚϵ�ʱ�򲻱�ѡ��
	        Arrays.fill(dp, Integer.MAX_VALUE);
	        // ������ƽ�����Ľ����1
	        for(int i = 0; i * i <= n; i++){
	            dp[i * i] = 1;
	        }
	        // ��С������������a
	        for(int a = 0; a <= n; a++){
	            // ��С������ƽ����b��b
	            for(int b = 0; a + b * b <= n; b++){
	                // ��Ϊa+b*b���ܱ������ƽ��������������Ҫȡ�����н�С��
	                dp[a + b * b] = Math.min(dp[a] + 1, dp[a + b * b]);
	            }
	        }
	        return dp[n];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
