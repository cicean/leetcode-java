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
 * ��Ŀ���⣺ ��д�����ж�һ�������������Ƿ�Ϊ����ª���� ugly number
 * 
 * ��ª����ָֻ����������2, 3, 5�������������磬6, 8�ǳ�ª����14���ǣ���Ϊ�����������������7
 * 
 * ע�⣬����1Ҳ����Ϊ��ª��
 * 
 * ����˼·�� ���������ظ���2, 3, 5���жϵ����Ƿ�Ϊ1����
 * 
 * ʱ�临�Ӷȣ�
 * 
 * ��num = 2^a * 3^b * 5^c * t������ִ�д���Ϊ a + b + c������֮������ΪO(log num)
 * 
 * @author cicean
 *
 */
public class UglyNumber {

	
	/**
     * �ж������Ƿ�Ϊ����
     * @param num ���ж�����
     * @return true��������false���ǳ���
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
