import java.util.*;

/**
 * 640. Solve the Equation
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

 If there is no solution for the equation, return "No solution".

 If there are infinite solutions for the equation, return "Infinite solutions".

 If there is exactly one solution for the equation, we ensure that the value of x is an integer.

 Example 1:
 Input: "x+5-3+x=6+x-2"
 Output: "x=2"
 Example 2:
 Input: "x=x"
 Output: "Infinite solutions"
 Example 3:
 Input: "2x=x"
 Output: "x=0"
 Example 4:
 Input: "2x+3x-6x=x+2"
 Output: "x=-1"
 Example 5:
 Input: "x=x+2"
 Output: "No solution"

 */

public class SolvetheEquation {

  /**
   * Approach #1 Partioning Coefficients [Accepted]

   In the current approach, we start by splitting the given equationequation based on = sign.
   This way, we've separated the left and right hand side of this equation. Once this is done,
   we need to extract the individual elements(i.e. x's and the numbers) from both sides of the equation.
   To do so, we make use of breakIt function, in which we traverse over the given equation(either left hand side or right hand side),
   and put the separated parts into an array.

   Now, the idea is as follows. We treat the given equation as if we're bringing all the x's on the left hand side and all the rest of the numbers on the right hand side as done below for an example.

   x+5-3+x=6+x-2

   x+x-x=6-2-5+3

   Thus, every x in the left hand side of the given equation is treated as positive, while that on the right hand side is treated as negative, in the current implementation.

   Likewise, every number on the left hand side is treated as negative, while that on the right hand side is treated as positive. Thus, by doing so, we obtain all the x's in the new lhslhs and all the numbers in the new rhsrhs of the original equation.

   Further, in case of an x, we also need to find its corresponding coefficients in order to evaluate the final effective coefficient of x on the left hand side. We also evaluate the final effective number on the right hand side as well.

   Now, in case of a unique solution, the ratio of the effective rhsrhs and lhslhs gives the required result. In case of infinite solutions, both the effective lhslhs and rhsrhs turns out to be zero e.g. x+1=x+1. In case of no solution, the coefficient of x(lhslhs) turns out to be zero, but the effective number on the rhsrhs is non-zero.

   Complexity Analysis

   Time complexity : O(n). Generating coefficients and findinn lhs and rhs will take O(n)O(n).

   Space complexity : O(n). ArrayList resres size can grow upto nn.

   */

  public class Solution {
    public String coeff(String x) {
      if (x.length() > 1 && x.charAt(x.length() - 2) >= '0' && x.charAt(x.length() - 2) <= '9')
        return x.replace("x", "");
      return x.replace("x", "1");
    }
    public String solveEquation(String equation) {
      String[] lr = equation.split("=");
      int lhs = 0, rhs = 0;
      for (String x: breakIt(lr[0])) {
        if (x.indexOf("x") >= 0) {
          lhs += Integer.parseInt(coeff(x));
        } else
          rhs -= Integer.parseInt(x);
      }
      for (String x: breakIt(lr[1])) {
        if (x.indexOf("x") >= 0)
          lhs -= Integer.parseInt(coeff(x));
        else
          rhs += Integer.parseInt(x);
      }
      if (lhs == 0) {
        if (rhs == 0)
          return "Infinite solutions";
        else
          return "No solution";
      }
      return "x=" + rhs / lhs;
    }
    public List< String > breakIt(String s) {
      List < String > res = new ArrayList < > ();
      String r = "";
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
          if (r.length() > 0)
            res.add(r);
          r = "" + s.charAt(i);
        } else
          r += s.charAt(i);
      }
      res.add(r);
      return res;
    }
  }

  /**
   * Approach #2 Using regex for spliting [Accepted]

   Algorithm

   In the last approach, we made use of a new function breakIt to obtain the individual components of either the left hand side or the right hand side. Instead of doing so, we can also make use of splitting based on + or - sign, to obtain the individual elements. The rest of the process remains the same as in the last approach.

   In order to do the splitting, we make use of an expression derived from regular expressions(regex). Simply speaking, regex is a functionality used to match a target string based on some given criteria. The ?=n quantifier, in regex, matches any string that is followed by a specific string nn. What it's saying is that the captured match must be followed by nn but the nn itself isn't captured.

   By making use of this kind of expression in the split functionality, we make sure that the partitions are obtained such that the + or - sign remains along with the parts(numbers or coefficients) even after the splitting.

   Complexity Analysis

   Time complexity : O(n). Generating coefficients and finding lhs and rhs will take O(n).

   Space complexity : O(n). ArrayList resres size can grow upto nn.

   */

  public class Solution2 {
    public String coeff(String x) {
      if (x.length() > 1 && x.charAt(x.length() - 2) >= '0' && x.charAt(x.length() - 2) <= '9')
        return x.replace("x", "");
      return x.replace("x", "1");
    }
    public String solveEquation(String equation) {
      String[] lr = equation.split("=");
      int lhs = 0, rhs = 0;
      for (String x: lr[0].split("(?=\\+)|(?=-)")) {
        if (x.indexOf("x") >= 0) {

          lhs += Integer.parseInt(coeff(x));
        } else
          rhs -= Integer.parseInt(x);
      }
      for (String x: lr[1].split("(?=\\+)|(?=-)")) {
        if (x.indexOf("x") >= 0)
          lhs -= Integer.parseInt(coeff(x));
        else
          rhs += Integer.parseInt(x);
      }
      if (lhs == 0) {
        if (rhs == 0)
          return "Infinite solutions";
        else
          return "No solution";
      } else
        return "x=" + rhs / lhs;
    }
  }

}
