/**
 * 1153. String Transforms Into Another String
 * Hard
 *
 * 221
 *
 * 60
 *
 * Add to List
 *
 * Share
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 * Example 2:
 *
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 *
 *
 * Note:
 *
 * 1 <= str1.length == str2.length <= 10^4
 * Both str1 and str2 contain only lowercase English letters.
 * Accepted
 * 10,761
 * Submissions
 * 30,802
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 22
 * Model the problem as a graph problem. Add an edge from one character to another if you need to convert between them.
 * What if one character needs to be converted into more than one character?
 * There would be no solution. Thus, every node can have at most one outgoing edge.
 * How to process a linked list?
 * How to process a cycle?
 * What if there is a character with no outgoing edge? You can use it to break all cycles!
 */
public class StringTransformsIntoAnotherString {

    /**
     * Explanation
     * Scan s1 and s2 at the same time,
     * record the transform mapping into a map/array.
     * The same char should transform to the same char.
     * Otherwise we can directly return false.
     *
     * To realise the transformation:
     *
     * transformation of link link ,like a -> b -> c:
     * we do the transformation from end to begin, that is b->c then a->b
     *
     * transformation of cycle, like a -> b -> c -> a:
     * in this case we need a tmp
     * c->tmp, b->c a->b and tmp->a
     * Same as the process of swap two variable.
     *
     * In both case, there should at least one character that is unused,
     * to use it as the tmp for transformation.
     * So we need to return if the size of set of unused characters < 26.
     *
     *
     * Complexity
     * Time O(N) for scanning input
     * Space O(26) to record the mapping
     * running time can be improved if count available character during the scan.
     */

    public boolean canConvert(String s1, String s2) {
        if (s1.equals(s2)) return true;
        Map<Character, Character> dp = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            if (dp.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i))
                return false;
            dp.put(s1.charAt(i), s2.charAt(i));
        }
        return new HashSet<Character>(dp.values()).size() < 26;
    }
}
