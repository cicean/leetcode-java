/*
 * 233	Number of Digit One	18.9%	Medium
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
 * 通过上面的列举我们可以发现，100以内的数字，除了10-19之间有11个‘1’之外，其余都只有1个。
 * 如果我们不考虑[10, 19]区间上那多出来的10个‘1’的话，那么我们在对任意一个两位数，十位数上的数字(加1)就代表1出现的个数，
 * 这时候我们再把多出的10个加上即可。比如56就有(5+1)+10=16个。
 * 如何知道是否要加上多出的10个呢，我们就要看十位上的数字是否大于等于2，是的话就要加上多余的10个'1'。
 * 那么我们就可以用(x+8)/10来判断一个数是否大于等于2。对于三位数也是一样，除了[110, 119]之间多出的10个数之外，其余的每10个数的区间都只有11个‘1’，
 * 那么还是可以用相同的方法来判断并累加1的个数，
 * 以上三种情况可以用 一个公式概括:
 * (a + 8) / 10 * m + (a % 10 == 1) * (b + 1); 
 */
public class NumberofDigitOne {

	public int countDigitOne(int n) {  
        int ones = 0;  
        for (long m = 1; m <= n; m *= 10) {  
            long a = n/m, b = n%m;  
            ones += (a + 8) / 10 * m;  
            if(a % 10 == 1) ones += b + 1;  
        }  
        return ones;  
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumberofDigitOne slt = new NumberofDigitOne();
		System.out.print(slt.countDigitOne(13));
	}

}
