/**
 * Created by cicean on 9/20/2016.
 * 402. Remove K Digits  QuestionEditorial Solution  My Submissions
 * Total Accepted: 1628
 * Total Submissions: 5787
 * Difficulty: Medium
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 10002 and will be ¡Ý k.
 * The given num does not contain any leading zero.
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * Hide Company Tags Google
 * Hide Tags Stack
 * Hide Similar Problems (H) Create Maximum Number
 */
public class RemoveKDigits {


    public static String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : num.toCharArray()) {
            while (k > 0 && sb.length() != 0 && sb.charAt(sb.length() - 1) > c) {
                sb.setLength(sb.length() - 1);
                k--;
            }
            if (sb.length() != 0 || c != '0') sb.append(c);  // Only append when it is not leading zero
        }
        if (k >= sb.length()) return "0";
        if(k > 0) sb.setLength(sb.length() - Math.min(k, sb.length())); // use all remaining k
        return sb.toString();
    }

}


