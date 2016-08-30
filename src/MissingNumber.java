/**
 * 268 Missing Number 33.7% Medium 
 * Given an array containing n distinct numbers
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

�����ȱ����������ݵȲ�������͹�ʽ�����е���������Ӧ�õ���(1 + n) * n / 2��������������ĳ����x����ʱ��͵Ľ��Ӧ�ñ�(1 + n) * n / 2������x��

ע�⣬��n�ܴ��ʱ��ֱ�Ӽ���(1 + n) * n / 2�ᵼ�������������ǲ���һ�߼�һ�߼��ķ�ʽ��
 * 
 * @author cicean
 *
 */
public class MissingNumber {

	public int missingNumber(int[] nums) {
		int temp = nums.length;
		for (int i = 0; i < nums.length; i++) {
			temp = temp + i - nums[i];
		}
		return temp;
	}
	
	/**
	 * ���е���������Ӧ�õ���(1 + n) * n / 2��������������ĳ����x����ʱ��͵Ľ��Ӧ�ñ�(1 + n) * n / 2������x��
	 * ע�⣬��n�ܴ��ʱ��ֱ�Ӽ���(1 + n) * n / 2�ᵼ�������������ǲ���һ�߼�һ�߼��ķ�ʽ��
	 * @param nums
	 * @return
	 */
	
	public int missingNumber_1(int[] nums) {
        int sum = 0;
        for(int num: nums)
            sum += num;

        return (nums.length * (nums.length + 1) )/ 2 - sum;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a ={0,1,3};
		MissingNumber slt = new MissingNumber();
		System.out.println(slt.missingNumber(a));
	}

}
