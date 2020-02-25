import java.util.ArrayList;
import java.util.Stack;

/*
 * 224	Basic Calculator	15.9%	Medium
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), 
 * the plus + or minus sign -, non-negative integers and empty spaces .
 * You may assume that the given expression is always valid.

 Some examples:
 "1 + 1" = 2
 " 2-1 + 2 " = 3
 "(1+(4+5+2)-3)+(6+8)" = 23
 Note: Do not use the eval built-in library function.

 * Solution:
 * This problem can be solved by using a stack. 
 * We keep pushing element to the stack, when ')" is met, 
 * calculate the expression up to the first "(".
 * 
 * ���ʽ��ֵ���Էֽ�Ϊ����������ɣ�
 * 1. ��׺���ʽתΪ��׺���ʽ���沨�����ʽ��
 * 2. ��׺���ʽ��ֵ
 * �����������԰ٶȰٿƣ�����׺���ʽ������
 * ��׺���ʽתΪ��׺���ʽ��Ҫ�㣺
 * ��ʼɨ�裻
 * ����ʱ�������׺���ʽ��
 * �������
 * 	a. ��Ϊ '('����ջ��
 * 	b. ��Ϊ ')'�������ΰ�ջ�еĵ�����������׺���ʽ�У�ֱ������'('����ջ��ɾ��'(' ��
 * 	c. ��Ϊ �������������������� �������ȼ����ڳ�'('�����ջ�������ʱ��ֱ����ջ�������ջ����ʼ�����ε����ȵ�ǰ�������������ȼ��ߺ����ȼ���ȵ��������ֱ��һ���������ȼ��͵Ļ���������һ��������Ϊֹ��
 * 	����ɨ�����׺���ʽ����ʱ��ջ�еĵ������������ջ��
 * ��׺���ʽ��ֵ�Ĺ��̼�����
 * ����һ��ջS
 * �����Ҷ����ʽ����������������ͽ���ѹ��ջS��
 * �������nԪ�����(����Ҫ��������Ϊn�������)��ȡ����ջ�����µ�n����������㣬�ٽ�����Ľ������ԭջ����n�ѹ��ջS��
 * �����׺���ʽδ���꣬���ظ�������̣�������ջ������ֵ��Ϊ������
 */

public class BasicCalculator {

	public int calculate_1(String s) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);

		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				int num = c - '0';
				int j = i + 1;
				while (j < s.length() && Character.isDigit(s.charAt(j))) {
					num = 10 * num + (s.charAt(j) - '0');
					j++;
				}
				res += stack.pop() * num;
				i = j - 1;
			} else if (c == '+' || c == '(') {
				stack.push(stack.peek());
			} else if (c == '-') {
				stack.push(-1 * stack.peek());
			} else if (c == ')') {
				stack.pop();
			}
		}
		return res;
	}

	public int calculate_2(String s) {
		// delte white spaces
		s = s.replaceAll(" ", "");

		Stack<String> stack = new Stack<String>();
		char[] arr = s.toCharArray();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ' ')
				continue;

			if (arr[i] >= '0' && arr[i] <= '9') {
				sb.append(arr[i]);

				if (i == arr.length - 1) {
					stack.push(sb.toString());
				}
			} else {
				if (sb.length() > 0) {
					stack.push(sb.toString());
					sb = new StringBuilder();
				}

				if (arr[i] != ')') {
					stack.push(new String(new char[] { arr[i] }));
				} else {
					// when meet ')', pop and calculate
					ArrayList<String> t = new ArrayList<String>();
					while (!stack.isEmpty()) {
						String top = stack.pop();
						if (top.equals("(")) {
							break;
						} else {
							t.add(0, top);
						}
					}

					int temp = 0;
					if (t.size() == 1) {
						temp = Integer.valueOf(t.get(0));
					} else {
						for (int j = t.size() - 1; j > 0; j = j - 2) {
							if (t.get(j - 1).equals("-")) {
								temp += 0 - Integer.valueOf(t.get(j));
							} else {
								temp += Integer.valueOf(t.get(j));
							}
						}
						temp += Integer.valueOf(t.get(0));
					}
					stack.push(String.valueOf(temp));
				}
			}
		}

		ArrayList<String> t = new ArrayList<String>();
		while (!stack.isEmpty()) {
			String elem = stack.pop();
			t.add(0, elem);
		}

		int temp = 0;
		for (int i = t.size() - 1; i > 0; i = i - 2) {
			if (t.get(i - 1).equals("-")) {
				temp += 0 - Integer.valueOf(t.get(i));
			} else {
				temp += Integer.valueOf(t.get(i));
			}
		}
		temp += Integer.valueOf(t.get(0));

		return temp;
	}

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicCalculator slt = new BasicCalculator();

		System.out.println(slt.calculate_1("1 + 1"));
		System.out.println(slt.calculate_2(" 2-1 + 2 "));
		System.out.println(slt.calculate_3("(1+(4+5+2)-3)+(6+8)"));
	}

}
