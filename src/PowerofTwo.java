
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

	public boolean isPowerOfTwo(int n) {
		if(n<=0){
			return false;
					}
        int i = 0;
        int countBit = 0;
        while(i<32){
        	if( (n&(1<<i)) !=0){
        		countBit++;
        		i++;
        	}
        }
        
        if(countBit != 1){
        	return false;
        }
        
        return true;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2;
		PowerofTwo slt = new PowerofTwo();
		System.out.print(slt.isPowerOfTwo(n));
	}

}
