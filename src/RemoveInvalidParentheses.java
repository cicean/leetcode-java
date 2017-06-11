import java.util.*;


/**
 * Created by cicean on 8/29/2016.
 *
 * 301. Remove Invalid Parentheses  QuestionEditorial Solution  My Submissions
 Total Accepted: 18448 Total Submissions: 55068 Difficulty: Hard
 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]
 Credits:
 Special thanks to @hpplayer for adding this problem and creating all test cases.

 Hide Company Tags Facebook
 Hide Tags Depth-first Search Breadth-first Search
 Hide Similar Problems (E) Valid Parentheses

 */
public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    public void remove(String s, List<String> result, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            //如果'('比')'大或等的话，就继续扫下去
            if (stack >= 0) continue;
            //否则，我们就找到当前有可能删去的')'，然后删掉看新的string
            for (int j = last_j; j <= i; j++) {
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), result, i, j, par);
                }
            }
            return;
        }

        String reversed = new StringBuilder(s).reverse().toString();
        //如果只从左到右扫了，par[0]还是'('的时候，我们还要再从右往左扫一遍
        if (par[0] == '(') {
            remove(reversed, result, 0, 0, new char[]{')', '('});
        } else {
            //否则两遍都扫完了，就加入结果中去
            result.add(reversed);
        }
    }



    //BFS
    public List<String> removeInvalidParentheses1(String s) {
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> ends = new LinkedList<>(); //here consider ends is index of last removed element!

        List<String> res = new ArrayList<>();
        int size = 1;
        queue.offer(s);
        ends.add(s.length());

        while (!queue.isEmpty()) {
            String str = queue.poll();
            int end = ends.poll();

            if (isValid(str)) res.add(str);

            //only when not found, we need to consider next level
            if (res.size() > 0) continue;
            for (int i = end - 1; i >= 0; i--) {
                if (str.charAt(i) != '(' && str.charAt(i) != ')') continue;
                String next = (new StringBuilder()).append(str.substring(0, i)).append(str.substring(i + 1)).toString();
                queue.offer(next);
                ends.add(i);

                //skip continuous ')' and '('
                while (i > 0 && str.charAt(i) == str.charAt(i - 1)) i--;
            }

            //check level finish, if not found, go on to next level
            if (--size == 0) {
                if (res.size() > 0) break;
                size = queue.size();
            }
        }

        return res;
    }


    private boolean isValid(String s) {
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') open++;
            else if (c == ')') open--;
            if (open < 0) return false;
        }
        return open == 0;
    }

    //Facebook give only one solution
    //balance parentheses in a string
    //use remove
    public String balanceParentheses(String s) {
        if (s == null || s.length() == 0) return null;

        int balance = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                balance++;
                sb.append(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                balance--;
                if (balance >= 0) {
                    sb.append(s.charAt(i));
                } else {
                	balance++;
                }
            } else {
            	sb.append(s.charAt(i));
            }
            
        }
        
        if (balance > 0) {
        	for (int i = 1; i <= balance; i++) {
        		sb.append(new Character(')'));
        	}
        } 
        System.out.println(balance);
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	RemoveInvalidParentheses slt = new RemoveInvalidParentheses();
    	String s = "))a)(";
    	slt.balanceParentheses(s);
    	System.out.print(slt.balanceParentheses(s));
	}

}
