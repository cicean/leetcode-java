/**
 * 736. Parse Lisp Expression
 * Hard
 *
 * 238
 *
 * 181
 *
 * Add to List
 *
 * Share
 * You are given a string expression representing a Lisp-like expression to return the integer value of.
 *
 * The syntax for these expressions is given as follows.
 *
 * An expression is either an integer, a let-expression, an add-expression, a mult-expression, or an assigned variable. Expressions always evaluate to a single integer.
 * (An integer could be positive or negative.)
 * A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the string "let", then there are 1 or more pairs of alternating variables and expressions, meaning that the first variable v1 is assigned the value of the expression e1, the second variable v2 is assigned the value of the expression e2, and so on sequentially; and then the value of this let-expression is the value of the expression expr.
 * An add-expression takes the form (add e1 e2) where add is always the string "add", there are always two expressions e1, e2, and this expression evaluates to the addition of the evaluation of e1 and the evaluation of e2.
 * A mult-expression takes the form (mult e1 e2) where mult is always the string "mult", there are always two expressions e1, e2, and this expression evaluates to the multiplication of the evaluation of e1 and the evaluation of e2.
 * For the purposes of this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter, then zero or more lowercase letters or digits. Additionally for your convenience, the names "add", "let", or "mult" are protected and will never be used as variable names.
 * Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context of that evaluation, the innermost scope (in terms of parentheses) is checked first for the value of that variable, and then outer scopes are checked sequentially. It is guaranteed that every expression is legal. Please see the examples for more details on scope.
 * Evaluation Examples:
 * Input: (add 1 2)
 * Output: 3
 *
 * Input: (mult 3 (add 2 3))
 * Output: 15
 *
 * Input: (let x 2 (mult x 5))
 * Output: 10
 *
 * Input: (let x 2 (mult x (let x 3 y 4 (add x y))))
 * Output: 14
 * Explanation: In the expression (add x y), when checking for the value of the variable x,
 * we check from the innermost scope to the outermost in the context of the variable we are trying to evaluate.
 * Since x = 3 is found first, the value of x is 3.
 *
 * Input: (let x 3 x 2 x)
 * Output: 2
 * Explanation: Assignment in let statements is processed sequentially.
 *
 * Input: (let x 1 y 2 x (add x y) (add x y))
 * Output: 5
 * Explanation: The first (add x y) evaluates as 3, and is assigned to x.
 * The second (add x y) evaluates as 3+2 = 5.
 *
 * Input: (let x 2 (add (let x 3 (let x 4 x)) x))
 * Output: 6
 * Explanation: Even though (let x 4 x) has a deeper scope, it is outside the context
 * of the final x in the add-expression.  That final x will equal 2.
 *
 * Input: (let a1 3 b2 (add a1 1) b2)
 * Output 4
 * Explanation: Variable names can contain digits after the first character.
 *
 * Note:
 *
 * The given string expression is well formatted: There are no leading or trailing spaces, there is only a single space separating different components of the string, and no space between adjacent parentheses. The expression is guaranteed to be legal and evaluate to an integer.
 * The length of expression is at most 2000. (It is also non-empty, as that would not be a legal expression.)
 * The answer and all intermediate calculations of that answer are guaranteed to fit in a 32-bit integer.
 * Accepted
 * 10,226
 * Submissions
 * 22,072
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * 1337c0d3r
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * Ternary Expression Parser
 * Medium
 * Number of Atoms
 * Hard
 * Basic Calculator IV
 * Hard
 * * If the expression starts with a digit or '-', it's an integer:
 * return it. * If the expression starts with a letter, it's a variable.
 * Recall it by checking the current scope in reverse order. * Otherwise,
 * group the tokens (variables or expressions) within this expression
 * by counting the "balance" `bal` of the occurrences of `'('` minus the number of occurrences of `')'`.
 * When the balance is zero, we have ended a token. For example, `(add 1 (add 2 3))`
 * should have tokens `'1'` and `'(add 2 3)'`. * For add and mult expressions,
 * evaluate each token and return the addition or multiplication of them.
 * * For let expressions, evaluate each expression sequentially and assign it to the variable in the current scope,
 * then return the evaluation of the final expression.
 */
public class ParseLispExpression {
    /**
     * Approach #1: Recursive Parsing [Accepted]
     * Intuition and Algorithm
     *
     * This question is relatively straightforward in terms of the idea of the solution,
     * but presents substantial difficulties in the implementation.
     *
     * Expressions may involve the evaluation of other expressions, which motivates a recursive approach.
     *
     * One difficulty is managing the correct scope of the variables. We can use a stack of hashmaps.
     * As we enter an inner scope defined by parentheses, we need to add that scope to our stack, and when we exit,
     * we need to pop that scope off.
     *
     * Our main evaluate function will go through each case of what form the expression could take.
     *
     * If the expression starts with a digit or '-', it's an integer: return it.
     *
     * If the expression starts with a letter, it's a variable. Recall it by checking the current scope in reverse order.
     *
     * Otherwise, group the tokens (variables or expressions) within this expression by counting the "balance"
     * bal of the occurrences of '(' minus the number of occurrences of ')'. When the balance is zero, we have ended a token.
     * For example, (add 1 (add 2 3)) should have tokens '1' and '(add 2 3)'.
     *
     * For add and mult expressions, evaluate each token and return the addition or multiplication of them.
     *
     * For let expressions, evaluate each expression sequentially and assign it to the variable in the current scope,
     * then return the evaluation of the final expression.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the length of expression. Each expression is evaluated once,
     *  but within that evaluation we may search the entire scope.
     *
     * Space Complexity: O(N^2)O(N
     * 2
     *  ). We may pass O(N)O(N) new strings to our evaluate function when making intermediate evaluations,
     *  each of length O(N)O(N). With effort, we could reduce the total space complexity to O(N)O(N)
     *  with interning or passing pointers.
     */

    class Solution {
        ArrayList<Map<String, Integer>> scope;
        public Solution() {
            scope = new ArrayList();
            scope.add(new HashMap());
        }

        public int evaluate(String expression) {
            scope.add(new HashMap());
            int ans = evaluate_inner(expression);
            scope.remove(scope.size() - 1);
            return ans;
        }

        public int evaluate_inner(String expression) {
            if (expression.charAt(0) != '(') {
                if (Character.isDigit(expression.charAt(0)) || expression.charAt(0) == '-')
                    return Integer.parseInt(expression);
                for (int i = scope.size() - 1; i >= 0; --i) {
                    if (scope.get(i).containsKey(expression))
                        return scope.get(i).get(expression);
                }
            }

            List<String> tokens = parse(expression.substring(
                    expression.charAt(1) == 'm' ? 6 : 5, expression.length() - 1));
            if (expression.startsWith("add", 1)) {
                return evaluate(tokens.get(0)) + evaluate(tokens.get(1));
            } else if (expression.startsWith("mult", 1)) {
                return evaluate(tokens.get(0)) * evaluate(tokens.get(1));
            } else {
                for (int j = 1; j < tokens.size(); j += 2) {
                    scope.get(scope.size() - 1).put(tokens.get(j-1), evaluate(tokens.get(j)));
                }
                return evaluate(tokens.get(tokens.size() - 1));
            }
        }

        public List<String> parse(String expression) {
            List<String> ans = new ArrayList();
            int bal = 0;
            StringBuilder buf = new StringBuilder();
            for (String token: expression.split(" ")) {
                for (char c: token.toCharArray()) {
                    if (c == '(') bal++;
                    if (c == ')') bal--;
                }
                if (buf.length() > 0) buf.append(" ");
                buf.append(token);
                if (bal == 0) {
                    ans.add(new String(buf));
                    buf = new StringBuilder();
                }
            }
            if (buf.length() > 0)
                ans.add(new String(buf));

            return ans;
        }
    }

    class Solution {
        public int evaluate(String expression) {
            return eval(expression, new HashMap<>());
        }
        private int eval(String exp, Map<String, Integer> parent) {
            if (exp.charAt(0) != '(') {
                // just a number or a symbol
                if (Character.isDigit(exp.charAt(0)) || exp.charAt(0) == '-')
                    return Integer.parseInt(exp);
                return parent.get(exp);
            }
            // create a new scope, add add all the previous values to it
            Map<String, Integer> map = new HashMap<>();
            map.putAll(parent);
            List<String> tokens = parse(exp.substring(exp.charAt(1) == 'm' ? 6 : 5, exp.length() - 1));
            if (exp.startsWith("(a")) { // add
                return eval(tokens.get(0), map) + eval(tokens.get(1), map);
            } else if (exp.startsWith("(m")) { // mult
                return eval(tokens.get(0), map) * eval(tokens.get(1), map);
            } else { // let
                for (int i = 0; i < tokens.size() - 2; i += 2)
                    map.put(tokens.get(i), eval(tokens.get(i + 1), map));
                return eval(tokens.get(tokens.size() - 1), map);
            }
        }
        private List<String> parse(String str) {
            // seperate the values between two parentheses
            List<String> res = new ArrayList<>();
            int par = 0;
            StringBuilder sb = new StringBuilder();
            for (char c: str.toCharArray()) {
                if (c == '(') par++;
                if (c == ')') par--;
                if (par == 0 && c == ' ') {
                    res.add(new String(sb));
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) res.add(new String(sb));
            return res;
        }
    }

    class Solution {

        Deque<Map<String, Integer>> scopes;

        public int evaluate(String expression) {
            // draw a picture before you start writing code.
            // recursive
            scopes = new ArrayDeque<>();
            int[] pos= {0};
            return eval(expression, pos);
        }

        int eval(String expr, int[] pos) {
            if (expr.charAt(pos[0]) == '(')
                pos[0]++;
            String token = getToken(expr, pos);
            if (token.equals("add")) {
                pos[0]++;  // skip space after add
                int left = eval(expr, pos);
                pos[0]++;  // skip spave between two operand
                int right = eval(expr, pos);
                pos[0]++; // skip ')'
                return left + right;
            } else if (token.equals("mult")) {
                pos[0]++;
                int left = eval(expr, pos);
                pos[0]++;
                int right = eval(expr, pos);
                pos[0]++;
                return left * right;
            } else if (token.equals("let")) {
                scopes.addFirst(new HashMap<String, Integer>());
                Map<String, Integer> cur = scopes.getFirst();
                int result = 0;
                while (pos[0] < expr.length() && expr.charAt(pos[0]) != ')') {
                    pos[0]++;
                    if (expr.charAt(pos[0]) == '(') { //Must be last expre
                        result = eval(expr, pos);
                        break;
                    }
                    String var = getToken(expr, pos);
                    if (expr.charAt(pos[0]) == ')') { // last expr
                        if (Character.isAlphabetic(var.charAt(0)))
                            result = getValueFromScope(var);
                        else
                            result =  Integer.parseInt(var);
                        break;
                    }
                    pos[0]++; // skip spave between symbol and value
                    cur.put(var, eval(expr, pos));
                }
                scopes.pollFirst();
                pos[0]++;
                return result;
            } else if (Character.isAlphabetic(token.charAt(0))) { // symbol
                return getValueFromScope(token);
            } else {
                return Integer.parseInt(token);
            }
            //System.out.println("Something wrong happens in eval(), token value: " + token);
            //return 0;
        }

        String getToken(String expr, int[] pos) {
            // string until get next space or ')'
            int start = pos[0];
            while (pos[0] < expr.length()) {
                if (expr.charAt(pos[0]) == ' ' || expr.charAt(pos[0]) == ')')
                    break;
                pos[0]++;
            }
            return expr.substring(start, pos[0]);
        }

        int getValueFromScope(String token) {
            for (Map<String, Integer> map : scopes) {
                if (map.containsKey(token))
                    return map.get(token);
            }
            System.out.println("Didn't find " + token + " in scopes");
            return 0;
        }
    }
}
