import java.util.*;

/**
 * 772. Basic Calculator III
 * DescriptionHintsSubmissionsDiscussSolution
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Some examples:
 *
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 *
 *
 * Note: Do not use the eval built-in library function.
 */

public class BasicCalculatorIII {

    public int calculate(String s) {
        // Write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }

        LinkedList<Integer> buff = new LinkedList<>();
        int num = 0;
        char sign = '+';

        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char cur = s.charAt(i);

            if (cur >= '0' && cur <= '9') {
                num = 10 * num + (int)(cur - '0');
            } else if (cur == '(') {
                int j = i + 1; int cnt = 1;
                for (; j < n; ++j) {
                    if (s.charAt(j) == '(') ++cnt;
                    if (s.charAt(j) == ')') --cnt;
                    if (cnt == 0) break;
                }

                num = calculate(s.substring(i + 1, j));
                i = j;
            }

            if (cur == '+' || cur == '-' || cur == '*' || cur == '/' || i == n - 1) {
                switch (sign) {
                    case '+':
                        buff.addFirst(num);
                        break;
                    case '-':
                        buff.addFirst(-num);
                        break;
                    case '*':
                        int tmp = buff.removeFirst() * num;
                        buff.addFirst(tmp);
                        break;
                    case '/':
                        int tmp2 = buff.removeFirst() / num;
                        buff.addFirst(tmp2);
                        break;
                }
                num = 0;
                sign = cur;
            }
        }

        int res = 0;
        for (int i : buff) {
            res += i;
        }

        return res;
    }

}
