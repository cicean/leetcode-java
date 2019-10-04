/**
 * 201	Bitwise AND of Numbers Range	23.7%	Medium
 *
 * 0.Problem:
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the 
 * bitwise AND of all numbers in this range, inclusive.
 * For example, given the range [5, 7], you should return 4.
 * 
 * 1.Refer.: 
 * 1.1 The key to solve this problem is bitwise AND consecutive numbers. 
 *      You can use the following example to walk through the code.
 * 2.1 ��������˼�ǣ������m��n�ķǸ������İ�λ��ֵ��̫ţ���ˣ������˺þö��Ǽ��㳬ʱ�������ʵ����m��n����ͷ����
 *     ������5�Ķ�����Ϊ101,7�Ķ�����λ111���乫��ͷ��Ϊ100��
 *      ���磬������3��5�İ�λ��3�Ķ�����λ11��5�Ķ�����λ101��û�й���ͷ��������0��
 */

public class BitwiseANDofNumbersRange {


		  public  int rangeBitwiseAnd_1(int m, int n) {
		    while (n > m) {
		      n = n & (n - 1);
		    }
		    return n;
		  }
		  
		  public int rangeBitwiseAnd_2(int m, int n) {  
		        int left = 0;  
		        while(m != n) {  
		            m >>= 1;  
		            n >>= 1;  
		            left ++;  
		        }             
		        return m << left;  
		    }  
		  
		  public static void main(String[] args) {
		    // TODO Auto-generated method stub
		    System.out.println(Integer.MAX_VALUE);
		    int m = 5;
		    int n = 7;
		    BitwiseANDofNumbersRange slt = new BitwiseANDofNumbersRange();
		    int res = slt.rangeBitwiseAnd_2(m, n);
		    System.out.print(res);
		  }
	
	

}
