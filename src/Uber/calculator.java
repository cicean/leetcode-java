package Uber;

import java.util.*;

/**
 * 类似basic calculator I ,
 add(1,2)
 sub(3,4).
 add(add(1,2),sub(3,4))
 input是一个string  比如"add(add(1,2),sub(3,4))"

 */

public class calculator {

  public int calculatorUB(String s) {
    Stack<Character> stack = new Stack<>();
    int res = 0;
    int i = 0;
    while (i < s.length()) {
      char c = s.charAt(i);
      if (Character.isAlphabetic(c)) {
        if (s.substring(i,i+3).equals("add")) {
          stack.push('+');
          i = i + 4;
        } else if (s.substring(i,i+3).equals("sub")) {
          stack.push('-');
          i = i + 4;
        } else {
          return -1;
        }
      } else if (Character.isDigit(c)){
        stack.push(c);
        i++;
      } else if (c == ')') {
        int b = stack.pop() - '0';
        int a = stack.pop() - '0';
        char op = stack.pop();
        if (op == '+') {
          res = a + b;
        } else {
          res = a - b;
        }
        if (!stack.isEmpty()) {
          stack.push((char)res);
        }
        i++;
      }

    }

    return res;
  }

  /**
   * Implement a basic calculator to evaluate a simple expression string.

   The expression string may contain open ( and closing parentheses ),
   the plus + or minus sign -, non-negative integers and empty spaces .
   * @param s
   * @return
     */
  public int calculate(String s) {
    // Write your code here
    Stack<Integer> stack = new Stack<Integer>();
    int result = 0;
    int number = 0;
    int sign = 1;
    for(int i = 0; i < s.length(); i ++) {
      char c = s.charAt(i);
      if(Character.isDigit(c)) {
        number = 10 * number + (int)(c - '0');
      } else if(c == '+') {
        result += sign * number;
        number = 0;
        sign = 1;
      } else if(c == '-') {
        result += sign * number;
        number = 0;
        sign = -1;
      } else if(c == '(') {
        //we push the result first, then sign;
        stack.push(result);
        stack.push(sign);
        //reset the sign and result for the value in the parenthesis
        sign = 1;
        result = 0;
      } else if(c == ')') {
        result += sign * number;
        number = 0;
        result *= stack.pop();    //stack.pop() is the sign before the parenthesis
        result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

      }
    }
    if(number != 0) {
      result += sign * number;
    }
    return result;
  }


  /**
   * Implement a basic calculator to evaluate a simple expression string.

   The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

   You may assume that the given expression is always valid.
   */

  public int calculate(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    List<String> list = new ArrayList<>();
    Deque<String> deque = new ArrayDeque<>();

    int n = 0;
    for (char ch : s.toCharArray()) {
      if (ch == ' ') {
        continue;
      }
      if (Character.isDigit(ch)) {
        n = n * 10 + ch - '0';
      } else {
        list.add("" + n);
        n = 0;
        list.add("" + ch);
      }
    }
    list.add("" + n);

    for (int i = 0; i < list.size(); i++) {
      String cur = list.get(i);
      if (cur.equals("*") || cur.equals("/")) {
        int a = Integer.parseInt(deque.pop());
        int b = Integer.parseInt(list.get(i+1));
        i++;
        int c = cur.equals("*") ? a * b : a / b;
        deque.push("" + c);
      } else {
        deque.push(cur);
      }
    }
    // System.out.println(deque.toString());

    while (deque.size() != 1) {
      int a = Integer.parseInt(deque.pollLast());
      String sign = deque.pollLast();
      int b = Integer.parseInt(deque.pollLast());
      int c = sign.equals("+") ? a + b : a - b;
      deque.offerLast("" + c);
    }

    return (int)Integer.parseInt(deque.peek());
  }

  /**
   * Basic Calculator III does not have variables;
   * Iterative solution: O(n) time, O(n) space
   * @param s
   * @return
     */
  public int basicCalculatorIII(String s) {
    int l1 = 0, o1 = 1;
    int l2 = 1, o2 = 1;

    Deque<Integer> stack = new ArrayDeque<>(); // stack to simulate recursion

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (Character.isDigit(c)) {
        int num = c - '0';

        while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
          num = num * 10 + (s.charAt(++i) - '0');
        }

        l2 = (o2 == 1 ? l2 * num : l2 / num);

      } else if (c == '(') {
        // First preserve current calculation status
        stack.offerFirst(l1); stack.offerFirst(o1);
        stack.offerFirst(l2); stack.offerFirst(o2);

        // Then reset it for next calculation
        l1 = 0; o1 = 1;
        l2 = 1; o2 = 1;

      } else if (c == ')') {
        // First preserve the result of current calculation
        int num = l1 + o1 * l2;

        // Then restore previous calculation status
        o2 = stack.poll(); l2 = stack.poll();
        o1 = stack.poll(); l1 = stack.poll();

        // Previous calculation status is now in effect
        l2 = (o2 == 1 ? l2 * num : l2 / num);

      } else if (c == '*' || c == '/') {
        o2 = (c == '*' ? 1 : -1);

      } else if (c == '+' || c == '-') {
        l1 = l1 + o1 * l2;
        o1 = (c == '+' ? 1 : -1);

        l2 = 1; o2 = 1;
      }
    }

    return (l1 + o1 * l2);
  }


  /**
   * is the most general form but its level two operators do not include division (/).
   * Solutions for this version, however, require some extra effort apart from the general structure in section III.
   * Due to the presence of variables (free variables, to be exact),
   * the partial results for each level of calculations may not be pure numbers,
   * but instead expressions (simplified, of course).
   * So we have to come up with some structure to represent these partial results.
   */
  private static class Term implements Comparable<Term> {
    List<String> vars;
    static final Term C = new Term(Arrays.asList()); // this is the term for pure numbers

    Term(List<String> vars) {
      this.vars = vars;
    }

    public int hashCode() {
      return vars.hashCode();
    }

    public boolean equals(Object obj) {
      if (this == obj) return true;

      if (!(obj instanceof Term)) return false;

      Term other = (Term)obj;

      return this.vars.equals(other.vars);
    }

    public String toString() {
      return String.join("*", vars);
    }

    public int compareTo(Term other) {
      if (this.vars.size() != other.vars.size()) {
        return Integer.compare(other.vars.size(), this.vars.size());
      } else {
        for (int i = 0; i < this.vars.size(); i++) {
          int cmp = this.vars.get(i).compareTo(other.vars.get(i));
          if (cmp != 0) return cmp;
        }
      }

      return 0;
    }
  }

  private static class Expression {
    Map<Term, Integer> terms;

    Expression(Term term, int coeff) {
      terms = new HashMap<>();
      terms.put(term, coeff);
    }

    void addTerm(Term term, int coeff) {
      terms.put(term, coeff + terms.getOrDefault(term, 0));
    }
  }

  private Term merge(Term term1, Term term2) {
    List<String> vars = new ArrayList<>();

    vars.addAll(term1.vars);
    vars.addAll(term2.vars);
    Collections.sort(vars);

    return new Term(vars);
  }

  private Expression add(Expression expression1, Expression expression2, int sign) {
    for (Map.Entry<Term, Integer> e : expression2.terms.entrySet()) {
      expression1.addTerm(e.getKey(), sign * e.getValue());
    }

    return expression1;
  }

  private Expression mult(Expression expression1, Expression expression2) {
    Expression res = new Expression(Term.C, 0);

    for (Map.Entry<Term, Integer> e1 : expression1.terms.entrySet()) {
      for (Map.Entry<Term, Integer> e2 : expression2.terms.entrySet()) {
        res.addTerm(merge(e1.getKey(), e2.getKey()), e1.getValue() * e2.getValue());
      }
    }

    return res;
  }

  private Expression calculate(String s, Map<String, Integer> map) {
    Expression l1 = new Expression(Term.C, 0);
    int o1 = 1;

    Expression l2 = new Expression(Term.C, 1);
    // we don't need 'o2' because the precedence level two operators contain '*' only

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (Character.isDigit(c)) {  // this is a number
        int num = c - '0';

        while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
          num = num * 10 + (s.charAt(++i) - '0');
        }

        l2 = mult(l2, new Expression(Term.C, num));

      } else if (Character.isLowerCase(c)) { // this is a variable
        int j = i;

        while (i + 1 < s.length() && Character.isLowerCase(s.charAt(i + 1))) i++;

        String var = s.substring(j, i + 1);
        Term term = map.containsKey(var) ? Term.C : new Term(Arrays.asList(var));
        int num = map.getOrDefault(var, 1);

        l2 = mult(l2, new Expression(term, num));

      } else if (c == '(') { // this is a subexpression
        int j = i;

        for (int cnt = 0; i < s.length(); i++) {
          if (s.charAt(i) == '(') cnt++;
          if (s.charAt(i) == ')') cnt--;
          if (cnt == 0) break;
        }

        l2 = mult(l2, calculate(s.substring(j + 1, i), map));

      } else if (c == '+' || c == '-') { // level one operators
        l1 = add(l1, l2, o1);
        o1 = (c == '+' ? 1 : -1);

        l2 = new Expression(Term.C, 1);
      }
    }

    return add(l1, l2, o1);
  }

  private List<String> format(Expression expression) {
    List<Term> terms = new ArrayList<>(expression.terms.keySet());

    Collections.sort(terms);

    List<String> res = new ArrayList<>(terms.size());

    for (Term term : terms) {
      int coeff = expression.terms.get(term);

      if (coeff == 0) continue;

      res.add(coeff + (term.equals(Term.C) ? "" : "*" + term.toString()));
    }

    return res;
  }

  public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
    Map<String, Integer> map = new HashMap<>();

    for (int i = 0; i < evalvars.length; i++) {
      map.put(evalvars[i], evalints[i]);
    }

    return format(calculate(expression, map));
  }

}
