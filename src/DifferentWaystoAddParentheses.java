import java.util.ArrayList;
import java.util.List;

/*
 * 241	Different Ways to Add Parentheses	28.1%	Medium
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
 *	Well, this problem seems to be a little tricky at first glance. However, the idea (taken from this link) is really simple. 
 *	Let's take the equation 2*3-4*5 in the problem statement as an example to illustrate the idea. 
 *	The idea is fairly simple: each time we encounter an operator, split the input string into two parts, 
 *one left to the operator and the other right to it. 
 *For example, when we reach -, we split the string into 2*3 and 4*5. Then we recursively 
 *(yeah, this is the biggest simplification) compute all possible values of the left and right parts and operate on all the possible pairs of them. 
 *The idea will become much more obvious if you read the following code.
 */
public class DifferentWaystoAddParentheses {

	public List<Integer> diffWaysToCompute(String input) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		if (input == null || input.length() == 0) {
			return result;
		}
		
		for (int i = 0; i < input.length();i++) {
			char c = input.charAt(i);
			if (c != '+'&& c != '-' && c != '*') {
				continue;
			}
			
			List<Integer> part1Result = diffWaysToCompute(input.substring(0, i));
			List<Integer> part2Result = diffWaysToCompute(input.substring(i+1, input.length()));
			
			for (Integer m : part1Result) {
				for (Integer n : part2Result) {
					if (c == '+') {
						result.add(m+n);
					}else if (c == '-') {
						result.add(m-n);
					}else if (c == '*') {
						result.add(m*n);
					}
				}
			}
			
		}
		
		if (result.size() == 0) {
			result.add(Integer.parseInt(input));
		}
		
		return result;      
      
    }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DifferentWaystoAddParentheses slt = new DifferentWaystoAddParentheses();
		String input = "2*3-4*5";
		//List<Integer> res = slt.diffWaysToCompute(input);
		System.out.print(slt.diffWaysToCompute(input));
	}

}
