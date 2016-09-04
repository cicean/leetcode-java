/**
 * 	191	Number of 1 Bits	37.4%	Easy
 *
 * 0.Problem:
 * Write a function that takes an unsigned integer and returns 
 * the number of ’1' bits it has (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 
 * 00000000000000000000000000001011, so the function should return 3.
 * 
 * 1.Refer.:
 * n = n & (n - 1)
 * 
 * 1.Recursive 
 * 2.operation  n = n & (n - 1)
 */

public class Numberof1Bits {

	public static int hammingWeight_1(int n) {  
	       if(n==0) return 0;  
	       if(n==1) return 1;  
	       int total=1;  
	       int index=1;  
	       while(n-total*2>=0){  
	           total=total*2;  
	       }  
	       n=n-total;  
	       index=index+hammingWeight_1(n);  
	       return index;  
	  }

	/**
	 * 减一相与法
	 复杂度
	 时间 O(1) 空间 O(1)

	 思路
	 该方法又叫Brian Kernighan方法。当原数不为0时，
	 将原数与上原数减一的值赋给原数。
	 因为每次减一再相与实际上是将最左边的1给消去了，
	 所以消去几次就有几个1。比如110，减去1得101，相与得100，消去了最左边的1。
	 * @param n
	 * @return
     */
	public static int hammingWeight_2(int n) {  
	     if(n==0) return 0;  
	     int i=0;  
	     while(n!=0){  
	         n=n & (n-1);  
	         i++;  
	     }  
	     return i;  
	  }

	/**
	 * 时间 O(1) 空间 O(1)
	 * 移位法
	 * 思路
	 通过与运算符判断最低位/最高位是否是1，然后再右移/左移。重复此步骤直到原数归零。

	 注意
	 右移运算符是算术右移，如果符号位是1的话最高位将补1，符号位是0的话最高位补0。
	 在C/C++中可以先将原数转换成无符号整数再处理，
	 而在Java中可以使用无符号右移算术符>>>。当然，左移的解法就没有这个问题了。
	 *
     */
	public class Solution {
		// you need to treat n as an unsigned value
		public int hammingWeight(int n) {
			int mark = 0b1, count = 0;
			while(n != 0b0){
				if((n & mark)==0b1){
					count++;
				}
				n = n >>> 1;
			}
			return count;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hammingWeight_2(1011));
	}

}
