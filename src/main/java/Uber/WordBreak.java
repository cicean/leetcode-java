package Uber;

import java.util.*;

/**
 * Created by cicean on 9/10/2018.
 */
public class WordBreak {

    public List<List<String>> wordBreak_Uber(String s, Set<String> dict) {
        Map<String, List<List<String>>> memo = new HashMap<>();
        return wordBreakHelper(s, dict, memo);
    }

    private List<List<String>> wordBreakHelper(String s, Set<String> dict, Map<String, List<List<String>>> memo) {

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<List<String>> results = new ArrayList<>();

        if (s.length() == 0) {
            return results;
        }

        if (dict.contains(s)) {
            List<String> sub = new ArrayList<>();
            sub.add(s);
            results.add(sub);
            //System.out.println("s = " + s);
        }

        for (int len = 1; len < s.length(); len++) {
            String word = s.substring(0, len);
            if (!dict.contains(word)) {
                continue;
            }

            String suffix = s.substring(len);
            List<List<String>> segmentations = wordBreakHelper(suffix,dict,memo);

            for(List<String> segmentation : segmentations) {
                List<String> sub = new ArrayList<>();
                System.out.println("word = " + word);
                sub.add(word);
                sub.addAll(segmentation);
                results.add(sub);
            }
        }

        memo.put(s, results);
        return results;

    }
}
