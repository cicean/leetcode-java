import java.util.Stack;

/*
 20	Valid Parentheses	26.6%	Easy
 Problem:    Valid Parentheses
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/valid-parentheses/
 Notes:
 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 Solution: stack.
 */

public class ValidParentheses {
	public boolean isValid(String s) {
        Stack<Character> stk = new Stack<Character>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch== '{') {
                stk.push(ch);
            }
            else {
                if (stk.empty() || Math.abs(stk.pop() - ch) > 2) // bcause ch '( )' -'[ ]'133 135 93 '{ }' minus 2 
                    return false;
            }
        }
        return stk.empty(); 
    }
	
	public static void main(String[] args){
		
		ValidParentheses slt = new ValidParentheses();
		String s = "(){}[]";
		boolean result = slt.isValid(s);
		System.out.println(result);
	 }
	  
}
