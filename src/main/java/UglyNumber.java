/**
 * 263 Ugly Number 32.1% Easy 
 * Write a program to check whether a given number is
 * an ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another
 * prime factor 7.
 * 
 * Note that 1 is typically treated as an ugly number.
 * 
 * Credits: Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 * 
 * Hide Tags Math Hide Similar Problems (E) Happy Number (E) Count Primes (M)
 * Ugly Number II
 * 
 * 
 * 题目大意： 编写程序判断一个给定的数字是否为“丑陋数” ugly number
 * 
 * 丑陋数是指只包含质因子2, 3, 5的正整数。例如，6, 8是丑陋数而14不是，因为它包含额外的质因子7
 * 
 * 注意，数字1也被视为丑陋数
 * 
 * 解题思路： 将输入数重复除2, 3, 5，判断得数是否为1即可
 * 
 * 时间复杂度：
 * 
 * 记num = 2^a * 3^b * 5^c * t，程序执行次数为 a + b + c，换言之，最坏情况为O(log num)
 * 
 * @author cicean
 *
 */
public class UglyNumber {

	
	/**
     * 判断数字是否为丑数
     * @param num 被判断数字
     * @return true：丑数，false：非丑数
     */
    public boolean isUgly(int num) {
         
        if (num <= 0) {
            return false;
        }
         
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
         
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UglyNumber slt = new UglyNumber();
		int num = 14;
		System.out.println(slt.isUgly(num));
	}

}
