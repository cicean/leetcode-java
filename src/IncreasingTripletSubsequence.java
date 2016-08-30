/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 �� i < j < k �� n-1 else return false.
 

Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.

Credits:
Special thanks to @DjangoUnchained for adding this problem and creating all test cases.

 

�������������һ�������������Ƿ����������������ǵ�����ϵ�ģ��������ദ�ķ�������һ��dp���飬
dp[i]��ʾ��iλ��֮ǰС�ڵ���nums[i]�����ֵĸ���(�����䱾��)�����ǳ�ʼ��dp���鶼Ϊ1��
Ȼ�����ǿ�ʼ����ԭ���飬�Ե�ǰ����nums[i]��
���Ǳ�����֮ǰ���������֣����֮ǰĳ������nums[j]С��nums[i]����ô���Ǹ���dp[i] = max(dp[i], dp[j] + 1)�������ʱdp[i]��3�ˣ�
�򷵻�true����������ɣ��򷵻�false���μ��������£�

������Ŀ��Ҫ������O(n)��ʱ�临�ӶȺ�O(1)�Ŀռ临�Ӷȣ���������ַ���һ����û���㣬���԰�д�ˡ�
��������������������ķ��������˼·�ǣ����Ǳ������飬ά��һ����Сֵ���͵����ڶ�Сֵ������ԭ�����ʱ��
�����ǰ����С�ڵ�����Сֵ��������Сֵ�����С�ڵ��ڵ����ڶ�Сֵ�����µ����ڶ�Сֵ��
�����ǰ���ֱ���Сֵ�͵����ڶ�Сֵ����˵����ʱ�������������������ˣ�ֱ�ӷ���ture�����������������false���μ��������£�

 * @author cicean
 *
 */
public class IncreasingTripletSubsequence {
	
	//use dp
	public boolean increasingTriplet(int[] nums) {
		if (nums == null || nums.length < 3) return false;
		int[] dp = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					if (dp[i] >= 3) return true;
				}
			}
		}
        return false;
    }
	
	public boolean increasingTriplet_1(int[] nums) {
		if (nums == null || nums.length < 3) return false;
		int s = Integer.MAX_VALUE, m = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > s && nums[i] > m) {
				return true;
			}
			if (s >= nums[i]) s = nums[i];
			else if (m > nums[i]) m = nums[i];			
		}
		
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			IncreasingTripletSubsequence slTripletSubsequence = new IncreasingTripletSubsequence();
			int a[] = {2,1,5,0,4,6};
			System.out.println(slTripletSubsequence.increasingTriplet(a));
	}

}
