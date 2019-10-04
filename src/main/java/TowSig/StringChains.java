package TowSig;

import java.util.*;

/**
 * Created by cicean on 9/5/2016.
 * Longest chain
 给一个字符串数组, 以任意一个单词开始，删除一个字母 ，如果形成的新字符串还在数组的单词堆里面，则是合法的， chain长度增加1.然后继续往下删，每删一个则长度增加1. 举些例子吧：(a, abcd, bcd, abd, cd, c)：
 abcd 删除一个字母可以变成 bcd ， abd， acd，abc。但是只有bcd， acd 可以往下走，所以下面只要考虑这两个。 bcd 可以变成cd 再变成c。 但是abd删除一个单词不能变成数组的一个单词。所以停止。

 *
 */
public class StringChains {

     int longestChain(String[] words) {
        HashSet<String> dict = new HashSet<String>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //use map to store the longest len for string, save time
        for (String s : words) {
            dict.add(s);
        }
        int longest = 0;
        for (String s : words) {
            if (s.length() <= longest) {
                continue;//no need to process s, since its longest length can be at most longest
            }
            int len = helper(s, dict, map) + 1;//s itself is already in the dict, so + 1
            map.put(s, len);
            longest = Math.max(len, longest);
        }
        return longest;


    }

    public int helper(String s, HashSet<String> dict, HashMap<String, Integer> map) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            String newStr = s.substring(0, i) + s.substring(i + 1);
            //create a new string by removing a char
            if (dict.contains(newStr)) {
                if (map.containsKey(newStr)) {
                    result = Math.max(result, map.get(newStr));
                } else {
                    result = Math.max(result, helper(newStr, dict, map) + 1);
                }

            }
        }
        return result;
    }

}
