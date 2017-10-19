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





}
