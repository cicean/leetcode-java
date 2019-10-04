/**
 * Created by cicean on 8/29/2016.
 * 306. Additive Number  QuestionEditorial Solution  My Submissions
 Total Accepted: 15428 Total Submissions: 57759 Difficulty: Medium
 Additive number is a string whose digits can form additive sequence.

 A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

 For example:
 "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 1 + 99 = 100, 99 + 100 = 199
 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

 Follow up:
 How would you handle overflow for very large input integers?

 Credits:
 Special thanks to @jeantimex for adding this problem and creating all test cases.

 Hide Company Tags Epic Systems

 */
public class AdditiveNumber {

    /**
     * 解答：
     不越界长度的Recursive:
     */

    public boolean isValid(Long x1, Long x2, String num, int start) {
        //当可以走到后面没有和了的时候，说明这个string满足条件
        if (start == num.length()) return true;
        x2 = x1 + x2;
        x1 = x2 - x1;
        String sum = x2.toString();
        //string.startsWith()直接可以知道这个sum是不是存在于num中
        return num.startsWith(sum, start) && isValid(x1, x2, num, start + sum.length());
    }

    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        for (int i = 1; i <= len / 2; i++) {
            if (num.charAt(0) == '0' && i > 1) return false;
            Long x1 = Long.parseLong(num.substring(0, i));
            for (int j = 1; Math.max(i, j) <= len - i - j; j++) {
                if (num.charAt(i) == '0' && j > 1) break;
                Long x2 = Long.parseLong(num.substring(i, i + j));
                if (isValid(x1, x2, num, i + j)) return true;
            }
        }
        return false;
    }
    /**
     * 越界长度的recursive:
     */

    public boolean isValid(BigInteger x1, BigInteger x2, String num, int start) {
        if (start == num.length()) return true;
        x2 = x2.add(x1);
        x1 = x2.subtract(x1);
        String sum = x2.toString();
        return num.startsWith(sum, start) && isValid(x1, x2, num, start + sum.length());
    }

    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        for (int i = 1; i <= len / 2; i++) {
            if (num.charAt(0) == '0' && i > 1) return false;
            BigInteger x1 = new BigInteger(num.substring(0, i));
            for (int j = 1; Math.max(i, j) <= len - i - j; j++) {
                if (num.charAt(i) == '0' && j > 1) break;
                BigInteger x2 = new BigInteger(num.substring(i, i + j));
                if (isValid(x1, x2, num, i + j)) return true;
            }
        }
        return false;
    }

    /**
     * 越界长的度iterator: (tail recursion is easy to transfer)
     */
    public boolean isValid(int i, int j, String num) {
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        BigInteger x1 = new BigInteger(num.substring(0, i));
        BigInteger x2 = new BigInteger(num.substring(i, i + j));
        String sum;
        for (int start = i + j; start < num.length(); start += sum.length()) {
            x2 = x2.add(x1);
            x1 = x2.subtract(x1);
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }

    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        for (int i = 1; i <= len / 2; i++) {
            for (int j = 1; Math.max(i, j) <= len - i - j; j++) {
                if (isValid(i, j, num)) {
                    return true;
                }
            }
        }
        return false;
    }




}
