/**
 * 	191	Number of 1 Bits	37.4%	Easy
 *
 * 0.Problem:
 * Write a function that takes an unsigned integer and returns 
 * the number of ¡¯1' bits it has (also known as the Hamming weight).
 * For example, the 32-bit integer ¡¯11' has binary representation 
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
	
	public static int hammingWeight_2(int n) {  
	     if(n==0) return 0;  
	     int i=0;  
	     while(n!=0){  
	         n=n & (n-1);  
	         i++;  
	     }  
	     return i;  
	  }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hammingWeight_2(1011));
	}

}
