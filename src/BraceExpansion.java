/**
 * 1087. Brace Expansion
 * Medium
 *
 * 88
 *
 * 8
 *
 * Favorite
 *
 * Share
 * A string S represents a list of words.
 *
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 *
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 *
 * Return all words that can be formed in this manner, in lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 *
 * Input: "abcd"
 * Output: ["abcd"]
 *
 *
 * Note:
 *
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 * Accepted
 * 6,460
 * Submissions
 * 10,813
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 * Decode String
 * Medium
 * Letter Case Permutation
 * Easy
 * Brace Expansion II
 * Hard
 * All generated strings are of the same size. How can we generate all of these strings?
 * Do a backtracking on which each level of it has to choose one single (e.g. 'a') character or any character of the given parenthesized group (e.g. "{a,b,c}")
 */

/**
 * 1096. Brace Expansion II
 * Hard
 *
 * 89
 *
 * 64
 *
 * Favorite
 *
 * Share
 * Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.
 *
 * Grammar can best be understood through simple examples:
 *
 * Single letters represent a singleton set containing that word.
 * R("a") = {"a"}
 * R("w") = {"w"}
 * When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
 * R("{a,b,c}") = {"a","b","c"}
 * R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
 * When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
 * R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
 * R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
 * Formally, the 3 rules for our grammar:
 *
 * For every lowercase letter x, we have R(x) = {x}
 * For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
 * Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.
 *
 *
 *
 * Example 1:
 *
 * Input: "{a,b}{c,{d,e}}"
 * Output: ["ac","ad","ae","bc","bd","be"]
 * Example 2:
 *
 * Input: "{{a,z},a{b,c},{ab,z}}"
 * Output: ["a","ab","ac","z"]
 * Explanation: Each distinct word is written only once in the final answer.
 *
 *
 * Constraints:
 *
 * 1 <= expression.length <= 50
 * expression[i] consists of '{', '}', ','or lowercase English letters.
 * The given expression represents a set of words based on the grammar given in the description.
 * Accepted
 * 4,154
 * Submissions
 * 7,217
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 7
 * Brace Expansion
 * Medium
 * You can write helper methods to parse the next "chunk" of the expression. If you see eg. "a", the answer is just the set {a}. If you see "{", you parse until you complete the "}" (the number of { and } seen are equal) and that becomes a chunk that you find where the appropriate commas are, and parse each individual expression between the commas.
 */
public class BraceExpansion {

    public String[] expand(String S) {
        // TreeSet to sort
        TreeSet<String> set = new TreeSet<>();
        if (S.length() == 0) {
            return new String[]{""};
        } else if (S.length() == 1) {
            return new String[]{S};
        }
        if (S.charAt(0) == '{') {
            int i = 0; // keep track of content in the "{content}"
            while (S.charAt(i) != '}') {
                i++;
            }
            String sub = S.substring(1, i);
            String[] subs = sub.split(",");
            String[] strs = expand(S.substring(i + 1)); // dfs
            for (int j = 0; j < subs.length; j++) {
                for (String str : strs) {
                    set.add(subs[j] + str);
                }
            }
        } else {
            String[] strs = expand(S.substring(1));
            for (String str : strs) {
                set.add(S.charAt(0) + str);
            }
        }
        return set.toArray(new String[0]);
    }


    public List<String> braceExpansionII(String expression) {
        String s = expression;
        char preSign = ',';
        Stack<List<String>> stack = new Stack<>();// Save List<String>
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // case 1. {...} recursive, stack.operate(resList) by preSign
            if (c == '{'){
                int j = i, p = 1;
                while (s.charAt(j) != '}' || p != 0){
                    j++;
                    if (s.charAt(j) == '{') p++;
                    if (s.charAt(j) == '}') p--;
                }
                List<String> slist = braceExpansionII(s.substring(i+1, j));
                if (preSign == '*'){
                    stack.push(merge(stack.pop(), slist));
                }
                else stack.push(slist);
                i = j;
                //default preSign is *
                preSign = '*';
            }
            // case 2 letter operate by preSign
            else if (Character.isLetter(c)){
                List<String> slist = new ArrayList<>();
                slist.add(""+c);
                if (preSign == '*'){
                    stack.push(merge(stack.pop(), slist));
                }
                else stack.push(slist);
                // //default preSign is *
                preSign = '*';
            }
            // case 3. if  == ", ", preSign is  plus, (default preSign is *);
            if (c ==',' || i == s.length()-1){
                preSign = ',';
            }
        }
        // output stack to one dimesion list;
        List<String> res = new ArrayList<>();
        while(!stack.isEmpty()){
            for (String l : stack.pop())
                if (!res.contains(l))res.add(l);
        }
        // sort by lexi-order
        Collections.sort(res);
        return res;
    }
    // multiply operation of 2 List<letter>
    public List<String> merge(List<String> list1, List<String> list2){
        List<String> res = new ArrayList<>();
        for (String l1 : list1){
            for (String l2 : list2){
                res.add(l1+l2);
            }
        }
        return res;
    }


}
