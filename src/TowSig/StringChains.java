package TowSig;

import java.util.*;

/**
 * Created by cicean on 9/5/2016.
 * Longest chain
 ��һ���ַ�������, ������һ�����ʿ�ʼ��ɾ��һ����ĸ ������γɵ����ַ�����������ĵ��ʶ����棬���ǺϷ��ģ� chain��������1.Ȼ���������ɾ��ÿɾһ���򳤶�����1. ��Щ���Ӱɣ�(a, abcd, bcd, abd, cd, c)��
 abcd ɾ��һ����ĸ���Ա�� bcd �� abd�� acd��abc������ֻ��bcd�� acd ���������ߣ���������ֻҪ������������ bcd ���Ա��cd �ٱ��c�� ����abdɾ��һ�����ʲ��ܱ�������һ�����ʡ�����ֹͣ��

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
