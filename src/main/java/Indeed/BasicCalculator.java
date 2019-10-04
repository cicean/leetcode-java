package Indeed;

import java.util.Stack;

/**
 * Created by cicean on 9/28/2018.
 */
public class BasicCalculator {
    public int calculate_3(String s) {
        Stack<Integer> sign = new Stack<Integer>();
        sign.push(1);
        int lastOp = 1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case ' ':
                    break;
                case '+':
                    lastOp = 1;
                    break;
                case '-':
                    lastOp = -1;
                    break;
                case '(':
                    sign.push(lastOp * sign.peek());
                    lastOp = 1;
                    break;
                case ')':
                    sign.pop();
                    break;
                default:
                    int num = 0;
                    while (i < s.length() && Character.isDigit(s.charAt(i)))
                        num = num * 10 + s.charAt(i++) - '0';
                    i--;
                    res += lastOp * num * sign.peek();
            }
        }
        return res;
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case ' ':
                    break;
                case '+':
                    stack.push(num);
                    sign = c;
                    num = 0;
                    break;
                case '-':
                    stack.push(-num);
                    sign = c;
                    num = 0;
                    break;
                case '*':
                    stack.push(stack.pop() * num);
                    sign = c;
                    num = 0;
                    break;
                case '/':
                    stack.push(stack.pop() / num);
                    sign = c;
                    num = 0;
                    break;
                default:
                    num = num * 10 + s.charAt(i) - '0';
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
