
/*
 * 231	Power of Two	30.4%	Easy
 * Given an integer, write a function to determine 
 * if it is a power of two.
 * 
 * 如果一个整数是2的幂，那么它的二进制形式最高位为1，其余各位为0
 * 等价于：n & (n - 1) = 0，且n > 0
 * 
 */

public class PowerofTwo {

	//1. Bit Manipulation -- 2 ms beats 21.88%
	public class Solution {
		public boolean isPowerOfTwo(int n) {
			return n>0 && (n&(n-1))==0;
		}
	}

	public boolean isPowerOfTwo(int n) {

		if(n<=0){
			return false;
		}
		int i = 0;
		int countbit = 0;
		while(i < 32) {
			if ((n & (1 << i)) != 0) {
				countbit++;
			}
			i++;
		}

		return countbit != 1 ? false : true;
	}

	//2. Iteration -- 2 ms beats 21.88%
	public class Solution2 {
		public boolean isPowerOfTwo(int n) {
			if (n > 1)
				while (n % 2 == 0) n /= 2;
			return n == 1;
		}
	}




	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2;
		PowerofTwo slt = new PowerofTwo();
		System.out.print(slt.isPowerOfTwo(n));
	}

}
