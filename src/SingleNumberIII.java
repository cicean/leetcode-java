/**
 * 260	Single Number III	36.6%	Medium
 * @author cicean
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Bit Manipulation
Hide Similar Problems (M) Single Number (M) Single Number II

 */

public class SingleNumberIII {
	
	 public int[] singleNumber(int[] nums) {
		 
		 int n = 0;
		 for (int num : nums) {
			 n ^= num;
		 }
		 n = n & ~(n - 1);
		 int[]  res = new int[2];
		 for (int num : nums) {
			 if ((num & n) == 0) {
				 res[0] ^= num;
			 } else {
				 res[1] ^= num;
			 }
		 }
		 return res;
	        
	    }
	 
	 public int[] singleNumber1(int[] nums) {  
	        int A = 0;  
	        int B = 0;  
	        int AXORB = 0;  
	        for(int i = 0; i<nums.length; i++){  
	            AXORB ^= nums[i];  
	        }  
	          
	        AXORB = (AXORB & (AXORB - 1)) ^ AXORB; //find the different bit  
	        for(int i = 0; i<nums.length; i++){  
	            if((AXORB & nums[i]) == 0)  
	                A ^= nums[i];  
	            else  
	                B ^= nums[i];  
	        }  
	        return new int[]{A, B};  
	    }

	/**
	 * 在一个数组中除两个数字只出现1次外，其它数字都出现了2次， 要求尽快找出这两个数字。

	 考虑下这个题目的简化版——数组中除一个数字只出现1次外，其它数字都成对出现，要求尽快找出这个数字。这个题目在之前的《位操作基础篇之位操作全面总结》中的“位操作趣味应用”中就已经给出解答了。根据异或运算的特点，直接异或一次就可以找出这个数字。

	 现在数组中有两个数字只出现1次，直接异或一次只能得到这两个数字的异或结果，但光从这个结果肯定无法得到这个两个数字。因此我们来分析下简化版中“异或”解法的关键点，这个关键点也相当明显——数组只能有一个数字出现1次。

	 设题目中这两个只出现1次的数字分别为A和B，如果能将A，B分开到二个数组中，那显然符合“异或”解法的关键点了。因此这个题目的关键点就是将A，B分开到二个数组中。由于A，B肯定是不相等的，因此在二进制上必定有一位是不同的。根据这一位是0还是1可以将A，B分开到A组和B组。而这个数组中其它数字要么就属于A组，要么就属于B组。再对A组和B组分别执行“异或”解法就可以得到A，B了。而要判断A，B在哪一位上不相同，只要根据A异或B的结果就可以知道了，这个结果在二进制上为1的位都说明A，B在这一位上是不相同的。

	 比如int a[] = {1, 1, 3, 5, 2, 2}

	 整个数组异或的结果为3^5即 0x0011 ^ 0x0101 = 0x0110

	 对0x0110，第1位（由低向高，从0开始）就是1。因此整个数组根据第1位是0还是1分成两组。

	 a[0] =1  0x0001  第一组

	 a[1] =1  0x0001  第一组

	 a[2] =3  0x0011  第二组

	 a[3] =5  0x0101  第一组

	 a[4] =2  0x0010  第二组

	 a[5] =2  0x0010  第二组

	 第一组有{1, 1, 5}，第二组有{3, 2, 3}，明显对这二组分别执行“异或”解法就可以得到5和3了。
	 * @param nums
	 * @return
     */
	public int[] singleNumber_(int[] nums) {
		int num1 = 0, num2 = 0, temp = 0;

		for (int num : nums) {
			temp ^= num;
		}
		int index;
		for (index = 0; index < nums.length * 8; index++) {
			if (((temp >> index) & 1) == 1) break;
		}

		for (int i =0; i < nums.length; i++) {
			if (((nums[i] >> index) & 1) == 0) {
				num1 ^= nums[i];
			} else {
				num2 ^= nums[i];
			}
		}


		return new int[]{num1, num2};
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingleNumberIII slt = new SingleNumberIII();
		int[] nums = {1, 2, 1, 3, 2, 5};
		int[] res= slt.singleNumber(nums);
		for (int i = 0; i < res.length; i++){
		System.out.print(res[i] + " ");
		}
	}

}
