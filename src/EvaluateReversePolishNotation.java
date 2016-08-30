import java.util.Stack;

/*
 150	Evaluate Reverse Polish Notation	21.1%	Medium
 Problem:    Evaluate Reverse Polish Notation
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/evaluate-reverse-polish-notation/
 Notes:
 Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 Solution: stack.
 */

public class EvaluateReversePolishNotation {
	
	public int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<Integer>();
        for (int i = 0; i < tokens.length; ++i) {
            if ((tokens[i].compareTo("+") != 0) && (tokens[i].compareTo("-") != 0) 
                    && (tokens[i].compareTo("*") != 0) && (tokens[i].compareTo("/") != 0)) {
                stk.push(Integer.parseInt(tokens[i]));
            } else {
                int op2 = stk.pop();
                int op1 = stk.pop();
                int res = 0;
                if(tokens[i].compareTo("+") == 0)
                    res = op1 + op2;
                else if(tokens[i].compareTo("-") == 0)
                    res = op1 - op2;
                else if(tokens[i].compareTo("*") == 0)
                    res = op1 * op2;
                else if(tokens[i].compareTo("/") == 0)
                    res = op1 / op2;
                stk.push(res);
            }
        }
        return stk.pop();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EvaluateReversePolishNotation slt = new EvaluateReversePolishNotation();
		String[] s ={"2", "1", "+", "3", "*"};
		int res = slt.evalRPN(s);
		for(int i=0; i<s.length;i++){
			if(s[i].compareTo("+") == 0){
				System.out.print("("+s[i-2]+s[i]+s[i-1]+")");
			}
			if(s[i].compareTo("*") == 0){
				System.out.print(s[i]+s[i-1]);
			}
		}
		System.out.print("="+res);
	}

}
