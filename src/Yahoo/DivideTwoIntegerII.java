package Yahoo;

/**
 * Created by cicean on 10/16/2016.
 * 题目给定两个整数，返回相除结果的string表达式，如果结果有循环部分，在循环部分加上{}。
 比如(10, 2) --> "5", (10, 4) --> "2.5", (10, 3) --> "3.{3}".
 */
public class DivideTwoIntegerII {

    public String divideTwo(int dividend, int divisor) {
        StringBuilder sb = new StringBuilder();
        int gcd = gcd(dividend, divisor);
        dividend = dividend / gcd;
        divisor = divisor / gcd;
        sb.append(dividend / divisor);
        int r = dividend % divisor;

        if (r == 0) {
            return sb.toString();
        }
        int aa, bb = 0, tmp = divisor;
        sb.append(".");

        if (tmp % 2 == 0 || tmp % 5 == 0 || tmp % 10 == 0) {
            while (r != 0) {
                sb.append(r * 10 / divisor);
                r = r * 10 % divisor;
            }
        } else {
            aa = (int) (Math.log((tmp & (-tmp))) / Math.log(2)); // count 2 number
            while (tmp % 5 == 0) {
                bb++;
                tmp /= 5;
            }
            aa = aa > bb ? aa : bb; // compare the 2 an 5 which is much
            while (aa != 0) {
                sb.append(r * 10 / divisor);
                r = r * 10 % divisor;
                aa--;
            }
            bb = r;
            if (r != 0) {
                sb.append("{");
                sb.append(r * 10 / divisor);
                r = r * 10 % divisor;
                while (r != bb) {
                    sb.append(r * 10 / divisor);
                    r = r * 10 % divisor;
                }

                sb.append("}");
            }

        }


      return sb.toString();
    }

    private int gcd(int a, int b) {
        int num = 0;
        for (int i = 1; i <= a; i++) {
            if (b % i == 0 && a % i == 0) {
                num = i;
            }
        }
        return num;
    }
    
    public static void main(String[] args) {
		DivideTwoIntegerII slt = new DivideTwoIntegerII();
		System.out.println(slt.divideTwo(10, 8));
	}

    
}