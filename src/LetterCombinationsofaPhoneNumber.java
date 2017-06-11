import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 17	Letter Combinations of a Phone Number	25.8%	Medium
 Problem:    Letter Combinations of a Phone Number
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/letter-combinations-of-a-phone-number/
 Notes:
 Given a digit string, return all possible letter combinations that the number could represent.
 A mapping of digit to letters (just like on the telephone buttons) is given below.
 Input:Digit string "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 Note:
 Although the above answer is in lexicographical order, your answer could be in any order you want.
 Solution: ...
 */

//假设有k个digit 每个digit可以代表m个字符, 时间O(m^k) 空间O(m^k)
public class LetterCombinationsofaPhoneNumber {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        String[] keyboard = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterCombinationsRe(keyboard, res, digits, "");
        return res;
    }

    public void letterCombinationsRe(String[] keyboard, ArrayList<String> res, String digits, String s) {
        if (s.length() == digits.length()) {
            res.add(s);
            return;
        }
        String letters = keyboard[digits.charAt(s.length()) - '0'];
        for (int i = 0; i < letters.length(); ++i) {
            letterCombinationsRe(keyboard, res, digits, s + letters.charAt(i));
        }
    }

    //FIFO queue
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits == null || digits.length() == 0) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LetterCombinationsofaPhoneNumber slt = new LetterCombinationsofaPhoneNumber();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input an integer");
        String s = sc.nextLine();
        List<String> res = slt.letterCombinations(s);
        System.out.print(res);
    }

}
