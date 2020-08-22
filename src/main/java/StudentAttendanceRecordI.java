/**
 * 551. Student Attendance Record I
 * Easy
 *
 * 237
 *
 * 777
 *
 * Add to List
 *
 * Share
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * You need to return whether the student could be rewarded according to his attendance record.
 *
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 * Accepted
 * 74,711
 * Submissions
 * 160,067
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * fallcreek
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * Student Attendance Record II
 * Hard
 */
public class StudentAttendanceRecordI {
    /**
     * Approach #1 Simple Solution [Accepted]
     * One simple way of solving this problem is to count number of A'sA
     * ′
     *  s in the string and check whether the string LLLLLL is a substring of a given string. If number of A'sA
     * ′
     *  s is less than 22 and LLLLLL is not a subtring of a given string then return truetrue, otherwise return falsefalse.
     *
     * indexOfindexOf method can be used to check substring in a string. It return the index within this string of the first occurrence of the specified character or -1, if the character does not occur.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(n)O(n). Single loop and indexOf method takes O(n)O(n) time.
     * Space complexity : O(1)O(1). Constant space is used.
     */

    public class Solution {
        public boolean checkRecord(String s) {
            int count=0;
            for(int i=0;i<s.length();i++)
                if(s.charAt(i)=='A')
                    count++;
            return count<2 && s.indexOf("LLL")<0;
        }
    }

    /** Approach #2 Better Solution [Accepted]
    * Algorithm
    *
    * One optimization of above method is to break the loop when count of A'sA
    * ′
    *  s becomes 22.
    *
    *
    * Complexity Analysis
    *
    * Time complexity : O(n)O(n). Single loop and indexOf method takes O(n)O(n) time.
    * Space complexity : O(1)O(1). Constant space is used.
     */
    public class Solution {
        public boolean checkRecord(String s) {
            int count=0;
            for(int i=0;i<s.length() && count<2 ;i++)
                if(s.charAt(i)=='A')
                    count++;
            return count<2 && s.indexOf("LLL")<0;
        }
    }

    /** Approach #3 Single pass Solution (Without indexOf method) [Accepted]
    * Algorithm
    *
    * We can solve this problem in a single pass without using indexOf method. In a single loop we can count number of A'sA
    * ′
    *  s and also check the substring LLLLLL in a given string.
    *
    *  **Complexity Analysis**
    * Time complexity : O(n)O(n). Single loop upto string length is used.
    * Space complexity : O(1)O(1). Constant space is used.
    * Approach #4 Using Regex [Accepted]
    * Algorithm
    *
    * One interesting solution is to use regex to match the string. Java provides the java.util.regex package for pattern matching with regular expressions. A regular expression is a special sequence of characters that helps you match or find other strings or sets of strings, using a specialized syntax held in a pattern.
    *
    * Following are the regex's used in this solution:
    *
    * . : Matches any single character except newline.
    *
    * * : Matches 0 or more occurrences of the preceding expression.
    *
    * .* : Matches any string
    *
    * a|b : Matches either a or b
    * matchesmatches method is used to check whether or not the string matches the given regular expression.
    *
    * Regular Expression of the string containing two or more than two A'sA
    * ′
    *  s will be .*A.*A.*.∗A.∗A.∗ and the regular expression of the string containing substring LLLLLL will be .*LLL.*.∗LLL.∗. We can merge this two regex using |∣ and form a regex of string containing either more than one AA or containing substring LLLLLL. Then regex will look like: .*(A.*A|LLL).*.∗(A.∗A∣LLL).∗. We will return true only when the string doesn't matches this regex.
    *
    *
    * Complexity Analysis
    *
    * Time complexity : O(n)O(n). matchesmatches method takes O(n)O(n) time.
    *
    * Space complexity : O(1)O(1). No Extra Space is used.
    */

    public class Solution {
        public boolean checkRecord(String s) {
            int countA = 0;
            for (int i = 0; i < s.length() && countA < 2; i++) {
                if (s.charAt(i) == 'A')
                    countA++;
                if (i <= s.length() - 3 && s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L')
                    return false;
            }
            return countA < 2;
        }
    }

}
