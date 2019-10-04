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
	 * ��һ�������г���������ֻ����1���⣬�������ֶ�������2�Σ� Ҫ�󾡿��ҳ����������֡�

	 �����������Ŀ�ļ򻯰桪�������г�һ������ֻ����1���⣬�������ֶ��ɶԳ��֣�Ҫ�󾡿��ҳ�������֡������Ŀ��֮ǰ�ġ�λ��������ƪ֮λ����ȫ���ܽᡷ�еġ�λ����ȤζӦ�á��о��Ѿ���������ˡ��������������ص㣬ֱ�����һ�ξͿ����ҳ�������֡�

	 ��������������������ֻ����1�Σ�ֱ�����һ��ֻ�ܵõ����������ֵ��������������������϶��޷��õ�����������֡���������������¼򻯰��С���򡱽ⷨ�Ĺؼ��㣬����ؼ���Ҳ�൱���ԡ�������ֻ����һ�����ֳ���1�Ρ�

	 ����Ŀ��������ֻ����1�ε����ֱַ�ΪA��B������ܽ�A��B�ֿ������������У�����Ȼ���ϡ���򡱽ⷨ�Ĺؼ����ˡ���������Ŀ�Ĺؼ�����ǽ�A��B�ֿ������������С�����A��B�϶��ǲ���ȵģ�����ڶ������ϱض���һλ�ǲ�ͬ�ġ�������һλ��0����1���Խ�A��B�ֿ���A���B�顣�������������������Ҫô������A�飬Ҫô������B�顣�ٶ�A���B��ֱ�ִ�С���򡱽ⷨ�Ϳ��Եõ�A��B�ˡ���Ҫ�ж�A��B����һλ�ϲ���ͬ��ֻҪ����A���B�Ľ���Ϳ���֪���ˣ��������ڶ�������Ϊ1��λ��˵��A��B����һλ���ǲ���ͬ�ġ�

	 ����int a[] = {1, 1, 3, 5, 2, 2}

	 �����������Ľ��Ϊ3^5�� 0x0011 ^ 0x0101 = 0x0110

	 ��0x0110����1λ���ɵ���ߣ���0��ʼ������1���������������ݵ�1λ��0����1�ֳ����顣

	 a[0] =1  0x0001  ��һ��

	 a[1] =1  0x0001  ��һ��

	 a[2] =3  0x0011  �ڶ���

	 a[3] =5  0x0101  ��һ��

	 a[4] =2  0x0010  �ڶ���

	 a[5] =2  0x0010  �ڶ���

	 ��һ����{1, 1, 5}���ڶ�����{3, 2, 3}�����Զ������ֱ�ִ�С���򡱽ⷨ�Ϳ��Եõ�5��3�ˡ�
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
