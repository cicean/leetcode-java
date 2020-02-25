import java.util.HashSet;
import java.util.Set;

/**
 * 824. Goat Latin
 * Easy
 *
 * 221
 *
 * 535
 *
 * Add to List
 *
 * Share
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 *
 * The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 *
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 *
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 *
 *
 *
 * Example 1:
 *
 * Input: "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 *
 * Input: "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 *
 *
 * Notes:
 *
 * S contains only uppercase, lowercase and spaces. Exactly one space between each word.
 * 1 <= S.length <= 150.
 * Accepted
 * 51,214
 * Submissions
 * 83,902
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 11
 *
 */

public class GoatLatin {

    /**
     * Approach #1: String [Accepted]
     * Intuition
     *
     * We apply the steps given in the problem in a straightforward manner. The difficulty lies in the implementation.
     *
     * Algorithm
     *
     * For each word in the sentence split, if it is a vowel we consider the word, otherwise we consider the rotation of the word (either word[1:] + word[:1] in Python, otherwise word.substring(1) + word.substring(0, 1) in Java).
     *
     * Afterwards, we add "ma", the desired number of "a"'s, and a space character.
     * Complexity Analysis
     *
     * Time Complexity: O(N^2)O(N
     * 2
     *  ), where NN is the length of S. This represents the complexity of rotating the word and adding extra "a" characters.
     *
     * Space Complexity: O(N^2)O(N
     * 2
     *  ), the space added to the answer by adding extra "a" characters.
     */

    class Solution {
        public String toGoatLatin(String S) {
            Set<Character> vowel = new HashSet();
            for (char c: new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'})
                vowel.add(c);

            int t = 1;
            StringBuilder ans = new StringBuilder();
            for (String word: S.split(" ")) {
                char first = word.charAt(0);
                if (vowel.contains(first)) {
                    ans.append(word);
                } else {
                    ans.append(word.substring(1));
                    ans.append(word.substring(0, 1));
                }
                ans.append("ma");
                for (int i = 0; i < t; i++)
                    ans.append("a");
                t++;
                ans.append(" ");
            }

            ans.deleteCharAt(ans.length() - 1);
            return ans.toString();
        }
    }

    class Solution_1 {
        public String toGoatLatin(String S) {
            String[] arr = S.split(" ");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = toGoatLatin(arr[i], i + 1);
            }
            return String.join(" ", arr);
        }

        private String toGoatLatin(String S, int i) {
            StringBuilder sb = new StringBuilder(S);

            if (!isVowel(S.charAt(0))) {
                sb.deleteCharAt(0);
                sb.append(S.charAt(0));
            }

            sb.append("ma");
            for ( ; i > 0; i--) sb.append('a');
            return sb.toString();
        }

        private boolean isVowel(char c) {
            if (c < 'a') c += 32;
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        }
    }
}
