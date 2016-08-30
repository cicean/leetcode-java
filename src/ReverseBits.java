/**
 * 190	Reverse Bits	28.3%	Easy
 *
 * 0.Problem:
 * Reverse bits of a given 32 bits unsigned integer.
 * For example, given input 43261596 (represented in binary as 
 * 00000010100101000001111010011100), return 964176192 (represented 
 * in binary as 00111001011110000010100101000000).
 * Follow up:
 * If this function is called many times, how would you optimize it?
 * Related problem: Reverse Integer
 * 
 * 1.Refer.:
 * 1.1计算位数，前后位数对调
 * 1.2
 */

public class ReverseBits {

	
		  public static int reverseBits_1(int n) {
		    for (int i = 0; i < 16; i++) {
		      int j = 31 - i;
		      int a = n & (1 << i);
		      int b = n & (1 << j);
		      if ((0 == a && 0 == b) || (0 != a && 0 != b)) continue;
		      n = n ^ (1 << i);
		      n = n ^ (1 << j);
		    }
		      return n;        
		  }
		  
	public int reverseBits_2(int n) {
		for (int i = 0; i < 16; i++) {
			n = swapBits(n, i, 32 - i - 1);
		}
	 
		return n;
	}
	 
	public int swapBits(int n, int i, int j) {
		int a = (n >> i) & 1;
		int b = (n >> j) & 1;
	 
		if ((a ^ b) != 0) {
			return n ^= (1 << i) | (1 << j);
		}
	 
		return n;
	}
	
	public static int reverseBits_3(int n) {
        int res= n & 1;
        for(int i=1;i<=31;i++)
        {
            n=n>>1; //不断向右移动
            res=res<<1; //不断向左移动
            res=res | (n & 1);
        }
        return res;

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(reverseBits_3(43261596));
	}

}
