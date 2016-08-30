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
 * 表达式求值可以分解为下列两步完成：
 * 1. 中缀表达式转为后缀表达式（逆波兰表达式）
 * 2. 后缀表达式求值
 * 以下内容来自百度百科：“后缀表达式”词条
 * 中缀表达式转为后缀表达式的要点：
 * 开始扫描；
 * 数字时，加入后缀表达式；
 * 运算符：
 * 	a. 若为 '('，入栈；
 * 	b. 若为 ')'，则依次把栈中的的运算符加入后缀表达式中，直到出现'('，从栈中删除'(' ；
 * 	c. 若为 除括号外的其他运算符， 当其优先级高于除'('以外的栈顶运算符时，直接入栈。否则从栈顶开始，依次弹出比当前处理的运算符优先级高和优先级相等的运算符，直到一个比它优先级低的或者遇到了一个左括号为止。
 * 	·当扫描的中缀表达式结束时，栈中的的所有运算符出栈；
 * 后缀表达式求值的过程简述：
 * 建立一个栈S
 * 从左到右读表达式，如果读到操作数就将它压入栈S中
 * 如果读到n元运算符(即需要参数个数为n的运算符)则取出由栈顶向下的n项按操作符运算，再将运算的结果代替原栈顶的n项，压入栈S中
 * 如果后缀表达式未读完，则重复上面过程，最后输出栈顶的数值则为结束。
 */

public class BasicCalculator {

	public int calculate_1(String s) {  
        Stack<Integer> stack = new Stack<>();  
        stack.push(1);  
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
	            sign.push(lastOp*sign.peek());
	            lastOp=1;
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
