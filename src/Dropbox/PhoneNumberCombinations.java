package Dropbox;

import java.util.*;

/**
 * Created by cicean on 9/26/2018.
 */
public class PhoneNumberCombinations {

    public class Solution {
        public ArrayList<String> letterCombinations(String digits) {
            ArrayList<String> result = new ArrayList<String>();

            if (digits == null || digits.equals("")) {
                return result;
            }

            Map<Character, char[]> map = new HashMap<Character, char[]>();
            map.put('0', new char[] {});
            map.put('1', new char[] {});
            map.put('2', new char[] { 'a', 'b', 'c' });
            map.put('3', new char[] { 'd', 'e', 'f' });
            map.put('4', new char[] { 'g', 'h', 'i' });
            map.put('5', new char[] { 'j', 'k', 'l' });
            map.put('6', new char[] { 'm', 'n', 'o' });
            map.put('7', new char[] { 'p', 'q', 'r', 's' });
            map.put('8', new char[] { 't', 'u', 'v'});
            map.put('9', new char[] { 'w', 'x', 'y', 'z' });

            StringBuilder sb = new StringBuilder();
            helper(map, digits, sb, result);

            return result;
        }

        private void helper(Map<Character, char[]> map, String digits,
                            StringBuilder sb, ArrayList<String> result) {
            if (sb.length() == digits.length()) {
                result.add(sb.toString());
                return;
            }

            for (char c : map.get(digits.charAt(sb.length()))) {
                sb.append(c);
                helper(map, digits, sb, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

// version: 高频题班

    public class Solution2 {
        /**
         * @param digits: A digital string
         * @return: all posible letter combinations
         */

        private void dfs(int x, int l, String str, String digits, String phone[], List<String> ans) {
            if (x == l) {
                ans.add(str);
                return;
            }
            int d = digits.charAt(x) - '0';
            for (char c : phone[d].toCharArray()) {
                dfs(x + 1, l, str + c, digits, phone, ans);
            }
        }

        public List<String> letterCombinations(String digits) {
            // write your code here
            String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

            List<String> ans = new ArrayList<>();
            if (digits.length() == 0) {
                return ans;
            }

            dfs(0, digits.length(), "", digits, phone, ans);
            return ans;
        }

        /**
         * 0-9每个数字可以map到几个字母上，就和电话上的numberpad类似，然后给你一个词典，
         * 给你一串数字，让你输出所有用词典里的词把整个数字map出来的方法。比如说给你数字234567，
         * 给你词典["ADG", "JMP", "JMQ"], 你要输出[["ADG", "JMP"], ["ADG", "JMQ"]]
         */
    }

    public List<List<String>> letterCombinations(String digits, Set<String> dict) {
        // write your code here
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<List<String>> results = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        if (dict == null || digits.length() == 0) {
            return results;
        }

        dfs(0, digits.length(), "", digits, phone, ans);

        for (String s : ans) {
            List<String> tmp = wordBreakII(s, dict);
            if (tmp.size() != 0) {
                results.add(wordBreakII(s, dict));
            }

        }
        return results;
    }

    private void dfs(int x, int l, String str, String digits, String phone[], List<String> ans) {
        if (x == l) {
            ans.add(str);
            return;
        }
        int d = digits.charAt(x) - '0';
        for (char c : phone[d].toCharArray()) {
            dfs(x + 1, l, str + c, digits, phone, ans);
        }
    }

    public List<String> wordBreakII(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String, List<String>> memo = new HashMap<>();
        return wordBreakHelper(s, dict, memo);
    }

    public List<String> wordBreakHelper(String s,
                                        Set<String> dict,
                                        Map<String, List<String>> memo){
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> results = new ArrayList<String>();

        if (s.length() == 0) {
            return results;
        }

        if (dict.contains(s)) {
            results.add(s);
        }

        for (int len = 1; len < s.length(); ++len){
            String word = s.substring(0, len);
            if (!dict.contains(word)) {
                continue;
            }

            String suffix = s.substring(len);
            List<String> segmentations = wordBreakHelper(suffix, dict, memo);

            for (String segmentation: segmentations){
                results.add(word + " " + segmentation);
            }
        }

        memo.put(s, results);
        return results;
    }



    public static void main(String[] args) throws Throwable {
        Main solution = new Main();

        int[] list = {1, 2, 6, 12, 24};
        //System.out.print(Arrays.toString(solution.primeNumber(0).toArray()));
//        System.out.print("result = " + solution.buySoda(list, 10));
//        System.out.print("results = " + Arrays.toString(solution.buySodaII(list, 10).toArray()));

        String[] dict = {"adg", "jmp", "jmq"};
        Set<String> set = new HashSet<>();
        for (String s : dict) {
            set.add(s);
        }

        System.out.print("results = " + Arrays.toString(solution.letterCombinations("234567", set).toArray()));

    }
}
