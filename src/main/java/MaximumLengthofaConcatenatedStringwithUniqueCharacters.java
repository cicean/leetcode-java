import java.util.*;

/**
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 * Medium
 *
 * 174
 *
 * 34
 *
 * Add to List
 *
 * Share
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 * Accepted
 * 12.1K
 * Submissions
 * 26.6K
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * sherzod99
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * 14
 * You can try all combinations and keep mask of characters you have.
 * You can use DP.
 */
public class MaximumLengthofaConcatenatedStringwithUniqueCharacters {

    public int maxLength(List<String> A) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        int res = 0;
        for (String s : A) {
            int a = 0, dup = 0;
            for (char c : s.toCharArray()) {
                dup |= a & (1 << (c - 'a'));
                a |= 1 << (c - 'a');
            }
            if (dup > 0) continue;
            for (int i = dp.size() - 1; i >= 0; --i) {
                if ((dp.get(i) & a) > 0) continue;
                dp.add(dp.get(i) | a);
                res = Math.max(res, Integer.bitCount(dp.get(i) | a));
            }
        }
        return res;
    }

    class Solution {
        public int maxLength(List<String> arr) {
            int[] signature = new int[arr.size()];
            int maxLen = 0;

            for(int i=0; i<arr.size(); ++i) {
                String str = arr.get(i);
                for(int j=0; j<str.length(); ++j) {
                    int letter = 1 << str.charAt(j) - 'a';
                    if((signature[i] & letter) == 0) {
                        signature[i] |= letter;
                    }else {
                        signature[i] = 0;
                        break;
                    }
                }
            }

            maxLen = backtrack(arr, signature, 0, 0, 0);


            return maxLen;
        }

        private int backtrack(List<String> strs, int[] arr, int start, int currState, int count) {
            int maxCount = count;

            for(int i=start; i<arr.length; ++i) {

                // if it is a unique string add the state and add the length
                if((arr[i] != 0) && (currState & arr[i]) == 0) {
                    int strLen = strs.get(i).length();
                    int newCount = backtrack(strs, arr, i+1 ,currState | arr[i], count+strLen);
                    maxCount = Math.max(newCount, maxCount);
                }
            }

            return maxCount;

        }

    }
}
