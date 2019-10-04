import java.util.Arrays;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

/**
 * 268 Missing Number 33.7% Medium Given an array containing n distinct numbers
 * taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * 
 * For example, Given nums = [0, 1, 3] return 2.
 * 
 * Note: Your algorithm should run in linear runtime complexity. Could you
 * implement it using only constant extra space complexity?
 * 
 * Credits: Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 * 
 * Hide Tags Array Math Bit Manipulation Hide Similar Problems (H) First Missing
 * Positive (M) Single Number
 * 
 * ��֪һ�����������0��n����������������һ�����������档�����ȱʧ������
 * 
 * �����ȱ����������ݵȲ�������͹�ʽ�����е���������Ӧ�õ���(1 + n) * n / 2��������������ĳ����x����ʱ��͵Ľ��Ӧ�ñ�(1 + n) *
 * n / 2������x��
 * 
 * ע�⣬��n�ܴ��ʱ��ֱ�Ӽ���(1 + n) * n / 2�ᵼ�������������ǲ���һ�߼�һ�߼��ķ�ʽ��
 * 
 * @author cicean
 *
 */
public class MissingNumber {

	// XOR
	public int missingNumber_XOR(int[] nums) { // xor
		int res = nums.length;
		for (int i = 0; i < nums.length; i++) {
			res ^= i;
			res ^= nums[i];
		}
		return res;
	}

	// sum
	public int missingNumber_SUM(int[] nums) {
		int temp = nums.length;
		for (int i = 0; i < nums.length; i++) {
			temp = temp + i - nums[i];
		}
		return temp;
	}

	// 3.Binary Search
	public int missingNumber_BS(int[] nums) { // binary search
		Arrays.sort(nums);
		int left = 0, right = nums.length, mid = (left + right) / 2;
		while (left < right) {
			mid = (left + right) / 2;
			if (nums[mid] > mid)
				right = mid;
			else
				left = mid + 1;
		}
		return left;
	}

	//Google followup
	//if more than one missing number, find the ith one.
	public int missingNumber_GG(int[] nums, int n , int k) {
		int tmp1 = 0;
		int tmp2 = 0;
		int count = 0;
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			tmp1 +=  i;
			tmp2 += nums[i];
			if (tmp1 - tmp2 != 0) {
				count++
				res = 
			}
			if (count >= k) return 
	
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 0, 1, 3 };
		MissingNumber slt = new MissingNumber();
		System.out.println(slt.missingNumber_SUM(a));
	}

}
