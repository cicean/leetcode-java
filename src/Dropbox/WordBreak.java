package Dropbox;

import java.util.*;

/**
 * Created by cicean on 9/26/2018.
 */
public class WordBreak {

    private int getMaxLength(Set<String> dict) {
        int maxLength = 0;
        for (String word : dict) {
            maxLength = Math.max(maxLength, word.length());
        }
        return maxLength;
    }

    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        if (s == null ||  dict == null) {
            return true;
        }

        int maxLength = getMaxLength(dict);
        boolean[] canSegment = new boolean[s.length() + 1];

        canSegment[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            for (int lastWordLength = 1; lastWordLength <= maxLength && lastWordLength <= i; lastWordLength++) {
                if (!canSegment[i - lastWordLength]) {
                    continue;
                }
                String word = s.substring(i - lastWordLength, i);
                if (dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }

        return canSegment[s.length()];
    }

    /**
     * all possible solution
     */
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

    // 九章硅谷求职算法集训营DFS剪枝版本：
    public class Solution {
        /**
         * @param s a string
         * @param wordDict a set of words
         */
        List<String> res = new ArrayList<String>();
        boolean[] f = null;
        char[] s;
        Set<String> dict = null;
        int n;

        boolean dfs(int p, String now) {
            if (p == n) {
                res.add(now);
                return true;
            }

            if (!f[p]) {
                return false;
            }

            if (p > 0) {
                now += " ";
            }

            boolean success = false;
            String cur = "";
            for (int i = p; i < n; ++i) {
                cur += s[i];
                if (dict.contains(cur)) {
                    success |= dfs(i + 1, now + cur);
                }
            }

            f[p] = success;
            return success;
        }

        public List<String> wordBreak(String ss, Set<String> wordDict) {
            dict = wordDict;
            s = ss.toCharArray();
            n = s.length;
            int i;
            f = new boolean[n];
            for (i = 0; i < n; ++i) {
                f[i] = true;
            }

            dfs(0, "");
            return res;
        }
    }

    // 九章硅谷求职算法集训营动态规划+DFS版本：
    public class Solution {
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */

        List<String> res = new ArrayList<String>();
        List<List<Integer>>f = new ArrayList<>();
        int n;

        void dfs(int p, String s, String now) {
            if (p == n) {
                res.add(now);
                return;
            }

            if (p > 0) {
                now += " ";
            }

            for (int i : f.get(p)) {
                dfs(i, s, now + s.substring(p, i));
            }
        }

        public List<String> wordBreak(String s, Set<String> dict) {
            n = s.length();
            int i, j;
            for (i = 0; i < n; ++i) {
                f.add(new ArrayList<>());
            }

            for (i = n - 1; i >= 0; --i) {
                for (j = i + 1; j <= n; ++j) {
                    if (dict.contains(s.substring(i, j))) {
                        if (j == n || f.get(j).size() > 0) {
                            f.get(i).add(j);
                        }
                    }
                }
            }

            dfs(0, s, "");
            return res;
        }
    }

    // version 1:
    public class Solution {
        private void search(int index, String s, List<Integer> path,
                            boolean[][] isWord, boolean[] possible,
                            List<String> result) {
            if (!possible[index]) {
                return;
            }

            if (index == s.length()) {
                StringBuilder sb = new StringBuilder();
                int lastIndex = 0;
                for (int i = 0; i < path.size(); i++) {
                    sb.append(s.substring(lastIndex, path.get(i)));
                    if (i != path.size() - 1) sb.append(" ");
                    lastIndex = path.get(i);
                }
                result.add(sb.toString());
                return;
            }

            for (int i = index; i < s.length(); i++) {
                if (!isWord[index][i]) {
                    continue;
                }
                path.add(i + 1);
                search(i + 1, s, path, isWord, possible, result);
                path.remove(path.size() - 1);
            }
        }

        public List<String> wordBreak(String s, Set<String> wordDict) {
            ArrayList<String> result = new ArrayList<String>();
            if (s.length() == 0) {
                return result;
            }

            boolean[][] isWord = new boolean[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    String word = s.substring(i, j + 1);
                    isWord[i][j] = wordDict.contains(word);
                }
            }

            boolean[] possible = new boolean[s.length() + 1];
            possible[s.length()] = true;
            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i; j < s.length(); j++) {
                    if (isWord[i][j] && possible[j + 1]) {
                        possible[i] = true;
                        break;
                    }
                }
            }

            List<Integer> path = new ArrayList<Integer>();
            search(0, s, path, isWord, possible, result);
            return result;
        }
    }

// version 2:

    public class Solution {
        public ArrayList<String> wordBreak(String s, Set<String> dict) {
            // Note: The Solution object is instantiated only once and is reused by each test case.
            Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
            return wordBreakHelper(s,dict,map);
        }

        public ArrayList<String> wordBreakHelper(String s, Set<String> dict, Map<String, ArrayList<String>> memo){
            if(memo.containsKey(s)) return memo.get(s);
            ArrayList<String> result = new ArrayList<String>();
            int n = s.length();
            if(n <= 0) return result;
            for(int len = 1; len <= n; ++len){
                String subfix = s.substring(0,len);
                if(dict.contains(subfix)){
                    if(len == n){
                        result.add(subfix);
                    }else{
                        String prefix = s.substring(len);
                        ArrayList<String> tmp = wordBreakHelper(prefix, dict, memo);
                        for(String item:tmp){
                            item = subfix + " " + item;
                            result.add(item);
                        }
                    }
                }
            }
            memo.put(s, result);
            return result;
        }
    }


    //version：高频题班
    public class Solution {
        public List<String> wordBreak(String s, Set<String> dict) {
            Map<String, List<String>> done = new HashMap<>();
            done.put("", new ArrayList<>());
            done.get("").add("");

            return dfs(s, dict, done);
        }

        List<String> dfs(String s, Set<String> dict, Map<String, List<String>> done) {
            if (done.containsKey(s)) {
                return done.get(s);
            }
            List<String> ans = new ArrayList<>();

            for (int len = 1; len <= s.length(); len++) {  //将s 分割成s1 s2  其中s1长度为len
                String s1 = s.substring(0, len);
                String s2 = s.substring(len);

                if (dict.contains(s1)) {
                    List<String> s2_res = dfs(s2, dict, done);
                    for (String item : s2_res) {
                        if (item == "") {
                            ans.add(s1);
                        } else {
                            ans.add(s1 + " " + item);
                        }
                    }
                }
            }
            done.put(s, ans);
            return ans;
        }
    }
}
