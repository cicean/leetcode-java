/**
 * 1047. Remove All Adjacent Duplicates In String
 * Easy
 *
 * 469
 *
 * 39
 *
 * Add to List
 *
 * Share
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 *
 * We repeatedly make duplicate removals on S until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 *
 * Note:
 *
 * 1 <= S.length <= 20000
 * S consists only of English lowercase letters.
 * Accepted
 * 50,620
 * Submissions
 * 76,164
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * coding4232
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Bloomberg
 * |
 * 6
 * Remove All Adjacent Duplicates in String II
 * Medium
 * Use a stack to process everything greedily.
 */
public class RemoveAllAdjacentDuplicateInString {

    public String removeDuplicates(String s) {
        int i = 0, n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) // count = 2
                i -= 2;
        }
        return new String(res, 0, i);
    }

    class Solution {
        public String removeDuplicates(String S) {

            char[] charArray = S.toCharArray();
            int index = -1;

            for(char c : charArray){
                if(index >= 0 && charArray[index]==c)
                    index--;
                else
                    charArray[++index]=c;
            }
            return new String(charArray,0,index+1);
        }
}
