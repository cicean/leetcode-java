
/*
 * 231	Power of Two	30.4%	Easy
 * Given an integer, write a function to determine 
 * if it is a power of two.
 * 
 * ���һ��������2���ݣ���ô���Ķ�������ʽ���λΪ1�������λΪ0
 * �ȼ��ڣ�n & (n - 1) = 0����n > 0
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
