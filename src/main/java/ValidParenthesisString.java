/**
 *
 678. Valid Parenthesis String
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

 Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 Any right parenthesis ')' must have a corresponding left parenthesis '('.
 Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 An empty string is also valid.
 Example 1:
 Input: "()"
 Output: True
 Example 2:
 Input: "(*)"
 Output: True
 Example 3:
 Input: "(*))"
 Output: True
 Note:
 The string size will be in the range [1, 100].

 */

public class ValidParenthesisString {

  //Approach #2: Dynamic Programming [Accepted]

  //Intuition and Algorithm
  //Let dp[i][j] be true if and only if the interval s[i], s[i+1], ..., s[j] can be made valid. Then dp[i][j] is true only if:s[i] is '*', and the interval s[i+1], s[i+2], ..., s[j] can be made valid;
  //or, s[i] can be made to be '(', and there is some k in [i+1, j] such that s[k] can be made to be ')', plus the two intervals cut by s[k] (s[i+1: k] and s[k+1: j+1]) can be made valid.

  /*
  Complexity Analysis

Time Complexity: O(N^3), where NN is the length of the string. There are O(N^2) states corresponding to entries of dp, and we do an average of O(N) work on each state.

Space Complexity: O(N^2), the space used to store intermediate results in dp.
   */
  public boolean checkValidString(String s) {
    int n = s.length();
    if (n == 0) return true;
    boolean[][] dp = new boolean[n][n];

    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == '*') dp[i][i] = true;
      if (i < n-1 &&
          (s.charAt(i) == '(' || s.charAt(i) == '*') &&
          (s.charAt(i+1) == ')' || s.charAt(i+1) == '*')) {
        dp[i][i+1] = true;
      }
    }

    for (int size = 2; size < n; size++) {
      for (int i = 0; i + size < n; i++) {
        if (s.charAt(i) == '*' && dp[i+1][i+size] == true) {
          dp[i][i+size] = true;
        } else if (s.charAt(i) == '(' || s.charAt(i) == '*') {
          for (int k = i+1; k <= i+size; k++) {
            if ((s.charAt(k) == ')' || s.charAt(k) == '*') &&
                (k == i+1 || dp[i+1][k-1]) &&
                (k == i+size || dp[k+1][i+size])) {
              dp[i][i+size] = true;
            }
          }
        }
      }
    }
    return dp[0][n-1];
  }

  /*
  Approach #3: Greedy [Accepted]

Intuition

When checking whether the string is valid, we only cared about the "balance": the number of extra, open left brackets as we parsed through the string. For example, when checking whether '(()())' is valid, we had a balance of 1, 2, 1, 2, 1, 0 as we parse through the string: '(' has 1 left bracket, '((' has 2, '(()' has 1, and so on. This means that after parsing the first i symbols, (which may include asterisks,) we only need to keep track of what the balance could be.

For example, if we have string '(***)', then as we parse each symbol, the set of possible values for the balance is [1] for '('; [0, 1, 2] for '(*'; [1, 2, 3] for '(**'; [0, 1, 2, 3, 4] for '(***', and [0, 1, 2, 3] for '(***)'.

Furthermore, we can prove these states always form a contiguous interval. Thus, we only need to know the left and right bounds of this interval. That is, we would keep those intermediate states described above as [lo, hi] = [1, 1], [0, 2], [1, 3], [0, 4], [0, 3].

Algorithm

Let lo, hi respectively be the smallest and largest possible number of open left brackets after processing the current character in the string.

If we encounter a left bracket (c == '('), then lo++, otherwise we could write a right bracket, so lo--. If we encounter what can be a left bracket (c != ')'), then hi++, otherwise we must write a right bracket, so hi--. If hi < 0, then the current prefix can't be made valid no matter what our choices are. Also, we can never have less than 0 open left brackets. At the end, we should check that we can have exactly 0 open left brackets.

Complexity Analysis

Time Complexity: O(N), where NN is the length of the string. We iterate through the string once.

Space Complexity: O(1), the space used by our lo and hi pointers. However, creating a new character array will take O(N)O(N) space.
   */

  public boolean checkValidString_3(String s) {
    int lo = 0, hi = 0;
    for (char c: s.toCharArray()) {
      lo += c == '(' ? 1 : -1;
      hi += c != ')' ? 1 : -1;
      if (hi < 0) break;
      lo = Math.max(lo, 0);
    }
    return lo == 0;
  }


  class Solution {
    public boolean checkValidString(String s) {
      Stack<Integer>stack1 = new Stack<>();
      Stack<Integer>stack2 = new Stack<>();
      for(int i=0; i<s.length(); i++) {
        if(s.charAt(i) == '(')
          stack1.push(i);
        else if(s.charAt(i) == '*')
          stack2.push(i);
        else {
          if(stack1.isEmpty() && stack2.isEmpty())
            return false;
          if(!stack1.isEmpty())
            stack1.pop();
          else
            stack2.pop();
        }
      }
      while(!stack1.isEmpty() && !stack2.isEmpty()) {
        if(stack1.peek() > stack2.peek())
          return false;
        stack1.pop();
        stack2.pop();
      }
      return stack1.isEmpty();
    }
  }

}
