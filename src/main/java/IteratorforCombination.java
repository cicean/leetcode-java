import java.util.*;

/**
 * 1286. Iterator for Combination
 * Medium
 *
 * 100
 *
 * 10
 *
 * Add to List
 *
 * Share
 * Design an Iterator class, which has:
 *
 * A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * A function next() that returns the next combination of length combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next combination.
 *
 *
 * Example:
 *
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
 *
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 *
 *
 * Constraints:
 *
 * 1 <= combinationLength <= characters.length <= 15
 * There will be at most 10^4 function calls per test.
 * It's guaranteed that all calls of the function next are valid.
 * Accepted
 * 4,971
 * Submissions
 * 7,638
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
 * LeetCode
 * Generate all combinations as a preprocessing.
 * Use bit masking to generate all the combinations.
 */
public class IteratorforCombination {



    class CombinationIterator {

        Queue<String> qu = new LinkedList();
        String orig = "";
        public CombinationIterator(String characters, int combinationLength) {
            orig = characters;
            find("", 0, combinationLength);
        }

        void find(String str, int index, int len) {
            if(len ==0) {
                qu.add(str);
                return;
            }
            for(int i= index; i<orig.length(); i++) {
                char ch = orig.charAt(i);
                find(str+ch , i+1, len-1);
            }

        }

        public String next() {
            if(!qu.isEmpty()) {
                return qu.poll();
            }
            return "";
        }

        public boolean hasNext() {
            return !qu.isEmpty();
        }
    }

    class CombinationIterator_1 {
        char[] ch;
        int k;
        int l;
        int count = 0;
        int[] res;
        public CombinationIterator_1(String characters, int combinationLength) {
            ch = characters.toCharArray();
            k = ch.length;
            l = combinationLength;
            res = new int[l];
            for(int i = 0; i < l; i++){
                res[i] = i;
            }
        }

        public String next() {
            StringBuilder sb = new StringBuilder();
            for(int i : res){
                sb.append(ch[i]);
            }
            int j = l-1;
            int ref = k;
            while(j >= 0 && res[j]+1 == ref){
                j--;
                ref--;
            }
            if(j < 0){
                res = null;
            }else{
                res[j]++;
                j++;
                while(j < l){
                    res[j] = res[j-1]+1;
                    j++;
                }
            }

            return sb.toString();
        }

        public boolean hasNext() {
            return res != null;
        }
    }

    class CombinationIterator_3 {
        Queue<String> combination = new LinkedList<String>();
        public CombinationIterator_3(String characters, int combinationLength) {
            //boolean[] seen = new boolean[characters.length()];
            helper(characters,new StringBuilder(),combinationLength,0);
        }

        public String next() {
            if(hasNext()){
                return combination.poll();
            }
            return "";
        }

        public boolean hasNext() {
            if(combination.size() > 0){
                return true;
            }
            return false;

        }

        public void helper(String characters,StringBuilder sb, int combinationLength, int start){
            if(sb.length() == combinationLength){
                combination.add(sb.toString());
                return;
            }
            for(int i=start;i<characters.length();i++){
                sb.append(characters.charAt(i));
                helper(characters,sb,combinationLength,i+1);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
