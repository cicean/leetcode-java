import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
public class BraceExpansionII {

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
