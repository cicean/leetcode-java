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
 * 2.1 这道题的意思是，计算从m到n的非负整数的按位与值。太牛逼了，我想了好久都是计算超时。结果其实就是m和n公共头部。
 *     例子中5的二进制为101,7的二进制位111，其公共头部为100。
 *      再如，若计算3到5的按位或，3的二进制位11，5的二进制位101，没有公共头部，返回0。
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
