/**
 * Created by cicean on 9/20/2016.
 * 400. Nth Digit  QuestionEditorial Solution  My Submissions
 * Total Accepted: 2212
 * Total Submissions: 7013
 * Difficulty: Easy
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * <p>
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 3
 * <p>
 * Output:
 * 3
 * Example 2:
 * <p>
 * Input:
 * 11
 * <p>
 * Output:
 * 0
 * <p>
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 * Hide Company Tags Google
 * Hide Tags Math
 */
public class NthDigit {

    public int findNthDigit(int n) {
        int digits = 1;
        long total = 0;
        long power = 1;
        while (true) {
            total += 9 * digits * power;
            if (total >= n) break;
            digits++;
            power *= 10;
        }
        total -= 9 * digits * power;
        long num = power + (n - total - 1) / digits;
        String s = "" + num;
        return s.charAt((int) ((n - total - 1) % digits)) - '0';
    }

    public int findNthDigit_1(int n) {

        int i;
        for (i = 0; i < 9; i++) {
            int d = 9 * (int) Math.pow(10, i);
            if (n <= d * (i + 1)) break;
            n -= d * (i + 1);
            System.out.println(i);
        }
        n -= 1;
        System.out.println("i = " + i + ", n = " + n);
        int x = n / (i + 1) + (int)Math.pow(10, i);
        String a = String.valueOf((int)Math.pow(10, i) + n / (i + 1));
        System.out.println(x);
        return a.charAt(n % (i + 1)) - '0';
    }
    
    public static void main(String[] args) {
		NthDigit slt = new NthDigit();
		System.out.println(slt.findNthDigit(1000000000));
		System.out.println(slt.findNthDigit_1(1000000000));
	}


}
