package Dropbox;

import java.util.*;

/**
 * Created by cicean on 9/26/2018.
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String teststr) {
        // write your code here
        if (pattern == null || teststr == null) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();

        String[] strs = teststr.split(" ");

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);

            if (!map.containsKey(c)) {
                if (map.containsValue(strs[i])) {
                    return false;
                }
                map.put(c, strs[i]);
            }
            else {
                if (!strs[i].equals(map.get(c))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * no space of the words
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return match(pattern, str, map, set);
    }

    private boolean match(String pattern,
                          String str,
                          Map<Character, String> map,
                          Set<String> set) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }

        Character c = pattern.charAt(0);
        if (map.containsKey(c)) {
            if (!str.startsWith(map.get(c))) {
                return false;
            }

            return match(
                    pattern.substring(1),
                    str.substring(map.get(c).length()),
                    map,
                    set
            );
        }

        for (int i = 0; i < str.length(); i++) {
            String word = str.substring(0, i + 1);
            if (set.contains(word)) {
                continue;
            }
            map.put(c, word);
            set.add(word);
            if (match(pattern.substring(1),
                    str.substring(i + 1),
                    map,
                    set)) {
                return true;
            }
            set.remove(word);
            map.remove(c);
        }

        return false;
    }
}
