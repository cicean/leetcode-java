import java.util.*;

/**
 * Created by cicean on 10/16/2016.
 * 411. Minimum Unique Word Abbreviation   QuestionEditorial Solution  My Submissions
 Total Accepted: 815
 Total Submissions: 2822
 Difficulty: Hard
 Contributors: Admin
 A string such as "word" contains the following abbreviations:

 ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

 Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

 Note:
 In the case of multiple answers as shown in the second example below, you may return any one of them.
 Assume length of target string = m, and dictionary size = n. You may assume that m ¡Ü 21, n ¡Ü 1000, and log2(n) + m ¡Ü 20.
 Examples:
 "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

 "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
 Hide Company Tags Google
 Hide Tags Backtracking Bit Manipulation
 Hide Similar Problems (M) Generalized Abbreviation (E) Valid Word Abbreviation

 */

class Node{ // Trie Node
    Node[] nodes;
    boolean isWord;
    Node(){
        nodes = new Node[26];
        isWord = false;
    }
    void add(String str){ // add a word to Trie
        if (str.length() == 0) isWord=true; // end of a word
        else {
            int idx = str.charAt(0)-'a'; // insert a new node
            if (nodes[idx] == null) nodes[idx] = new Node();
            nodes[idx].add(str.substring(1));
        }
    }
    boolean isAbbr(String abbr, int num){
        if ( num > 0){ // number of '*'
            for (Node node : nodes){
                if (node != null && node.isAbbr(abbr, num-1)) return true;
            }
            return false; // not exist in the dictionary
        } else {
            if (abbr.length()==0) return isWord; // at the end of the addr
            int idx=0; // get the number of '*' at the start of the abbr
            while (idx < abbr.length() && abbr.charAt(idx) >='0' && abbr.charAt(idx) <='9' ) {
                num = (num*10) + (abbr.charAt(idx++)-'0');
            }
            if (num>0) return isAbbr(abbr.substring(idx),num); // start with number
            else { // start with non-number
                if (nodes[abbr.charAt(0)-'a'] != null )
                    return nodes[abbr.charAt(0)-'a'].isAbbr(abbr.substring(1), 0);
                else return false; // not exist in the dictionary
            }
        }
    }
}
public class MinimumUniqueWordAbbreviation {

    /**
     * Use Trie to build a dictionary with a function to check abbreviation.
     Use DFS with backtracking to generate the abbreviations of a given length.
     Use binary search to find the smallest possible length.
     * @param target
     * @param dictionary
     * @return
     */
    public String minAbbreviation(String target, String[] dictionary) {
        List<String> dict = new ArrayList();
        int len = target.length();
        for (String str : dictionary) if (str.length() == len ) dict.add(str);
        if (dict.isEmpty()) return ""+len;
        Node root = new Node();
        for (String str : dict) root.add(str);
        char[] cc = target.toCharArray();
        String ret = null;

        int min = 1, max = len;
        while (max >= min) {
            int mid = min+( (max-min)/2 );
            List<String> abbs = new ArrayList();
            getAbbs(cc, 0, mid, new StringBuilder(), abbs);
            boolean conflict = true;
            for (String abbr: abbs){
                if ( ! root.isAbbr(abbr,0) ) {
                    conflict = false;
                    ret = abbr;
                    break;
                }
            }
            if (conflict) {
                min = mid+1;
            } else {
                max = mid-1;
            }
        }
        return ret;
    }


    void getAbbs(char[] cc, int s, int len, StringBuilder sb, List<String> abbs){ //DFS with backtracking
        boolean preNum = (sb.length() > 0 ) && (sb.charAt(sb.length()-1) >= '0') && (sb.charAt(sb.length()-1) <= '9');
        if (len == 1)  {
            if ( s  < cc.length) {
                if (s==cc.length-1) abbs.add(sb.toString() + cc[s]); // add one char
                if (! preNum ) abbs.add(sb.toString() + (cc.length-s) ); // add a number
            }
        } else if (len > 1 ) {
            int last = sb.length();
            for (int i=s+1; i < cc.length; i++ ){
                if (! preNum) { // add a number
                    sb.append(i-s);
                    getAbbs(cc, i, len-1, sb, abbs);
                    sb.delete(last, sb.length());
                }
                if (i==s+1) { // add one char
                    sb.append(cc[s]);
                    getAbbs(cc, i, len-1, sb, abbs);
                    sb.delete(last, sb.length());
                }
            }
        }
    }


}
