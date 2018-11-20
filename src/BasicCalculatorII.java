import java.util.*;

/*
 * 	227	Basic Calculator II	17.5%	Medium
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.

	Some examples:
	"3+2*2" = 7
	" 3/2 " = 1
	" 3+5 / 2 " = 5
	Note: Do not use the eval built-in library function.
 * 
 * Solution:
 * 1.1 pass����, ��һ��, �Ƚ���˳�, �ڶ���, ���Ӽ�.
 * 
 * 1.2
 * ��1��������������Ҫ���д���
 * ��2���˳���Ҫ�ں�һ��������ջ����
 * ��3�����ջ��ʣ��Ϊ�Ӽ�����ֻ��Ҫһ�����������ɣ���Ҫ��סһ��ֻ����һ�����ֺͷ��ţ�
 *     ���������������㣬��I����˵���������Ļ����Ե��->  Basic Calculator I
 * ��4����Ҫ�����һ�����ֽ������⴦����Ϊǰ��ķ���ֻ�м�⵽�з��ŵ�ʱ��ŻὫ������ջ��
��
 * 
 */
public class BasicCalculatorII {

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (c != ' ' && !Character.isDigit(c) || i + 1 == s.length()) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                sign = c;
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
	
	//pocket gem
	/**
	 * ֻ�С������͡���������ʵ����һ���ġ�
	 * �һ�д��һ��Сbug������ÿ�μ���sign֮��֮ǰ��numberҪ���㣬
	 * ��������һ������ţ����ȷʵ���ҡ�����
	 * Ȼ����Ҫ֧�֡�������ע���ⲻһ���ˣ�Ҫ����2+3*��4+5֮���������ò����׸㶨�ˣ���˵Ҫ�������š�����
	 * @param s
	 */
	
	public int calculate_pg(String s) {
		int res = 0;
        if (s == null) return res;
        int i = 0;
        int last = 0, last_result = 1;
        char last_operater = '+';
        int sign = 1;
        char[] arr = s.toCharArray();
        while (i < s.length()) {
            if (arr[i] == ' ') {
                i++;
                continue;
            }
            if (arr[i] == '+' || arr[i] == '-') {
                res += last_result * sign;
                sign = arr[i++] == '+' ? 1 : -1;
                last_result = 1;
                last_operater = '+';
            } else if (arr[i] == '/' || arr[i] == '*') {
                last_operater = arr[i];
                i++;
            }
            if (Character.isDigit(arr[i])) {
                last = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    last = last * 10 + arr[i++] - '0';
                }

                if (last_operater == '*') last_result *= last;
                else if (last_operater == '/') last_result /= last;
                else last_result = last;
            }
        }
        res += last_result * sign;
        return res;

	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicCalculatorII slt = new BasicCalculatorII();
		
		System.out.println(slt.calculate_1("3+2*2"));
		System.out.println(slt.calculate_1(" 3/2 "));
		System.out.println(slt.calculate_pg(" 3+5 / 2 "));
	}

}
