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
 * 已知一个数组包含了0到n的所有整数，除了一个数不在里面。求这个缺失的数。

如果不缺这个数，根据等差数列求和公式，所有的数加起来应该等于(1 + n) * n / 2。但是现在少了某个数x，此时求和的结果应该比(1 + n) * n / 2正好少x。

注意，当n很大的时候直接计算(1 + n) * n / 2会导致溢出，因此我们采用一边加一边减的方式。
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
	 * 所有的数加起来应该等于(1 + n) * n / 2。但是现在少了某个数x，此时求和的结果应该比(1 + n) * n / 2正好少x。
	 * 注意，当n很大的时候直接计算(1 + n) * n / 2会导致溢出，因此我们采用一边加一边减的方式。
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
