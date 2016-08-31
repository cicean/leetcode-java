/**
 * 	191	Number of 1 Bits	37.4%	Easy
 *
 * 0.Problem:
 * Write a function that takes an unsigned integer and returns 
 * the number of ��1' bits it has (also known as the Hamming weight).
 * For example, the 32-bit integer ��11' has binary representation 
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
	 * ��һ���뷨
	 ���Ӷ�
	 ʱ�� O(1) �ռ� O(1)

	 ˼·
	 �÷����ֽ�Brian Kernighan��������ԭ����Ϊ0ʱ��
	 ��ԭ������ԭ����һ��ֵ����ԭ����
	 ��Ϊÿ�μ�һ������ʵ�����ǽ�����ߵ�1����ȥ�ˣ�
	 ������ȥ���ξ��м���1������110����ȥ1��101�������100����ȥ������ߵ�1��
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
	 * ʱ�� O(1) �ռ� O(1)
	 * ��λ��
	 * ˼·
	 ͨ����������ж����λ/���λ�Ƿ���1��Ȼ��������/���ơ��ظ��˲���ֱ��ԭ�����㡣

	 ע��
	 ������������������ƣ��������λ��1�Ļ����λ����1������λ��0�Ļ����λ��0��
	 ��C/C++�п����Ƚ�ԭ��ת�����޷��������ٴ���
	 ����Java�п���ʹ���޷�������������>>>����Ȼ�����ƵĽⷨ��û����������ˡ�
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
