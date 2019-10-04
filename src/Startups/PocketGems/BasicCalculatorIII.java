package PocketGems;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cicean on 9/22/2016. 2£©given you a string contians "0-9+-* /()",
 * like "(1 + 3) * 4 / 2" return the result(integer).
 */
public class BasicCalculatorIII {
	public int calculate(String s) {
		int res = 0;
		if (s == null) return res; 
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(1);
		char last_operater = '+';
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			 if (c == ' ') {
	                i++;
	                continue;
	            }
			 
			if (Character.isDigit(c)) {
				int num = c - '0';
				int j = i + 1;
				while (j < s.length() && Character.isDigit(s.charAt(j))) {
					num = 10 * num + (s.charAt(j) - '0');
					j++;
				}
				 if (last_operater == '*') res +=  stack.pop() * num;
	             else if (last_operater == '/') res += stack.pop() / num;
	             else res += stack.pop() * num;
				 i = j -1;
			} else {
				if (c == '+' || c == '(') {
					stack.push(stack.peek());
					last_operater = '+';
				}
				if (c == '-') {
					stack.push(-1 * stack.peek());
				}
				if (c == '*' || c == '/') {
					last_operater = c;
					
				}
				if (c == ')') {
					stack.pop();
				}
				
			}
			
			
		}
		return res;
	}
	
	public static String cal(String src) {
        StringBuilder builder = new StringBuilder();
        if (src.contains("(")) {
            Pattern pattern = Pattern.compile("\\(([^()]+)\\)");
            Matcher matcher = pattern.matcher(src);
            int lastEnd = 0;
            while (matcher.find()) {
				
                builder.append(src.substring(lastEnd, matcher.start()));
				System.out.println(builder.toString());
                builder.append(cal(matcher.group(1)));
                lastEnd = matcher.end();
            }
            builder.append(src.substring(lastEnd));
        } else {
            Pattern pattern = Pattern.compile("([\\d.]+)\\s*([*/])\\s*([\\d.]+)");
            builder.append(src);
            Matcher matcher = pattern.matcher(builder.toString());
            while (matcher.find()){
                float f1 = Float.parseFloat(matcher.group(1));
                float f2 = Float.parseFloat(matcher.group(3));
                float result = 0;
                switch (matcher.group(2)){
                    case "*":
                        result = f1 * f2;
                        break;
                    case "/":
                        result = f1 / f2;
                        break;
                }
                builder.replace(matcher.start(), matcher.end(),
                        String.valueOf(result));
                matcher.reset(builder.toString());
            }
            pattern = Pattern.compile("([\\d.]+)\\s*([+-])\\s*([\\d.]+)");
            matcher = pattern.matcher(builder.toString());
            while (matcher.find()){
                float f1 = Float.parseFloat(matcher.group(1));
                float f2 = Float.parseFloat(matcher.group(3));
                float result = 0;
                switch (matcher.group(2)){
                    case "+":
                        result = f1 + f2;
                        break;
                    case "-":
                        result = f1 - f2;
                        break;
                }
                builder.replace(matcher.start(), matcher.end(),
                        String.valueOf(result));
                matcher.reset(builder.toString());
            }
            return builder.toString();
        }
        System.out.println(builder);
        return cal(builder.toString());
    }
	
	public static void main(String[] args) {
        String src = "(3 + (5 - 2) * 10 / 2 * 3 + 15) * (8 - 4)";
        System.out.println(cal(src));
    }
	
	
}
