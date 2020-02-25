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

    class Solution {
//     public int calculate(String s) {
//         int num = 0, preNum = 0, sum = 0;
//         char preSign = '+';

//         for(int i = 0; i < s.length() + 1; i++) {
//             char c = i < s.length() ? s.charAt(i) : '+';
//             if(c == ' ') continue;

//             if(Character.isDigit(c)) {
//                 num = num * 10 + c - '0';
//             } else if(c == '(') {
//                 // find the last ) position
//                 int j = i;
//                 int cnt = 0;
//                 while(j < s.length()) {
//                     if(s.charAt(j) == '(') cnt++;
//                     else if(s.charAt(j) == ')') cnt--;

//                     if(cnt == 0) break;
//                     j++;
//                 }
//                 num = calculate(s.substring(i + 1, j + 1));
//                 i = j;
//             } else {
//                 if(preSign == '+') {
//                     sum += preNum;
//                     preNum = num;
//                 } else if(preSign == '-') {
//                     sum += preNum;
//                     preNum = -num;
//                 } else if(preSign == '*') {
//                     preNum *= num;
//                 } else if(preSign == '/') {
//                     preNum /= num;
//                 } else if(preSign == ')') {
//                     break;
//                 }

//                 preSign = c;
//                 num = 0;
//             }
//         }

        //         return sum + preNum;
//     }
        public int calculate(String s) {
            return helper(s, new int[] {0});
        }

        public int helper(String s, int[] start) {
            if(start[0] == s.length()) return 0;
            int num = 0, preNum = 0, sum = 0;
            char preSign = '+';
            int startIndex = start[0];

            for(int i = startIndex; i < s.length() + 1; i++) {
                char c = i < s.length() ? s.charAt(i) : '+';
                if(c == ' ') continue;

                if(Character.isDigit(c)) {
                    num = num * 10 + c - '0';
                } else if(c == '(') {
                    start[0] = i + 1;
                    num = helper(s, start);
                    i = start[0];
                } else {
                    if(preSign == '+') {
                        sum += preNum;
                        preNum = num;
                    } else if(preSign == '-') {
                        sum += preNum;
                        preNum = -num;
                    } else if(preSign == '*') {
                        preNum *= num;
                    } else if(preSign == '/') {
                        preNum /= num;
                    } else if(preSign == ')') {
                        break;
                    }

                    if(c == ')') start[0] = i;
                    preSign = c;
                    num = 0;
                }
            }

            return sum + preNum;
        }
    }

    public class ExpressionEvaluation {

        class TreeNode {
            public int val;
            public String s;
            public TreeNode left, right;

            public TreeNode(int val, String ss) {
                this.val = val;
                this.s = ss;
                this.left = this.right = null;
            }

        }

        public class Solution {

            int get(String a, Integer base) {
                if (a.equals("+") || a.equals("-"))
                    return 1 + base;
                if (a.equals("*") || a.equals("/"))
                    return 2 + base;

                return Integer.MAX_VALUE;
            }

            void dfs(TreeNode root, ArrayList<String> as) {
                if(root==null)
                    return;
                if (root.left != null)
                    dfs(root.left, as);

                if (root.right != null)
                    dfs(root.right, as);
                as.add(root.s);
            }

            public int evaluateExpression(String[] expression) {
                // write your code here
                Stack<TreeNode> stack = new Stack<TreeNode>();
                TreeNode root = null;
                int val = 0;
                Integer base = 0;
                for (int i = 0; i <= expression.length; i++) {
                    if(i != expression.length)
                    {

                        if (expression[i].equals("(")) {
                            base += 10;
                            continue;
                        }
                        if (expression[i].equals(")")) {
                            base -= 10;
                            continue;
                        }
                        val = get(expression[i], base);

                    }
                    TreeNode right = i == expression.length ? new TreeNode(
                            Integer.MIN_VALUE, "") : new TreeNode(val,
                            expression[i]);
                    while (!stack.isEmpty()) {
                        if (right.val <= stack.peek().val) {
                            TreeNode nodeNow = stack.pop();

                            if (stack.isEmpty()) {
                                right.left = nodeNow;

                            } else {
                                TreeNode left = stack.peek();
                                if (left.val < right.val) {
                                    right.left = nodeNow;
                                } else {
                                    left.right = nodeNow;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    stack.push(right);
                }

                ArrayList<String> reversepolish = new ArrayList<String>();
                dfs(stack.peek().left, reversepolish);
                String[] str = new String[reversepolish.size()];
                reversepolish.toArray(str);
                //System.out.println(as);

                return evalreversepolish(str);
            }

            int evalreversepolish(String[] tokens) {
                int returnValue = 0;
                String operators = "+-*/";

                Stack<String> stack = new Stack<String>();

                for (String ss : tokens) {
                    if (!operators.contains(ss)) {
                        stack.push(ss);
                    } else {
                        int a = Integer.valueOf(stack.pop());
                        int b = Integer.valueOf(stack.pop());
                        if (ss.equals("+")) {
                            stack.push(String.valueOf(a + b));
                        } else if (ss.equals("-")) {
                            stack.push(String.valueOf(b - a));
                        } else if (ss.equals("*")) {
                            stack.push(String.valueOf(a * b));
                        } else if (ss.equals("/")) {
                            stack.push(String.valueOf(b / a));
                        }
                    }
                }
                if(stack.isEmpty())
                    returnValue = 0;
                else
                    returnValue = Integer.valueOf(stack.pop());

                return returnValue;
            }
        };
    }

}
