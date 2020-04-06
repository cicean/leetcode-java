import java.util.*;

/**
 * 1371. Find the Longest Substring Containing Vowels in Even Counts
 * Medium
 *
 * 189
 *
 * 5
 *
 * Add to List
 *
 * Share
 * Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eleetminicoworoep"
 * Output: 13
 * Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
 * Example 2:
 *
 * Input: s = "leetcodeisgreat"
 * Output: 5
 * Explanation: The longest substring is "leetc" which contains two e's.
 * Example 3:
 *
 * Input: s = "bcbcbc"
 * Output: 6
 * Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 x 10^5
 * s contains only lowercase English letters.
 * Accepted
 * 3,191
 * Submissions
 * 5,914
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * LeetCode
 * Represent the counts (odd or even) of vowels with a bitmask.
 * Precompute the prefix xor for the bitmask of vowels and then get the longest valid substring.
 */
public class FindtheLongestSubstringContainingVowelsinEvenCounts {
    /**
     * Explanation
     * cur records the count of "aeiou"
     * cur & 1 = the records of a % 2
     * cur & 2 = the records of e % 2
     * cur & 4 = the records of i % 2
     * cur & 8 = the records of o % 2
     * cur & 16 = the records of u % 2
     * seen note the index of first occurrence of cur
     *
     * Note that we don't really need the exact count number,
     * we only need to know if it's odd or even.
     *
     * If it's one of aeiou,
     * 'aeiou'.find(c) can find the index of vowel,
     * cur ^= 1 << 'aeiou'.find(c) will toggle the count of vowel.
     *
     * But for no vowel characters,
     * 'aeiou'.find(c) will return -1,
     * that's reason that we do 1 << ('aeiou'.find(c) + 1) >> 1.
     *
     * Hope this explain enough.
     *
     * Complexity
     * Time O(N), Space O(1)
     */

    public int findTheLongestSubstring(String s) {
        int res = 0 , cur = 0, n = s.length();
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; ++i) {
            cur ^= 1 << ("aeiou".indexOf(s.charAt(i)) + 1 ) >> 1;
            seen.putIfAbsent(cur, i);
            res = Math.max(res, i - seen.get(cur));
        }
        return res;
    }
}
