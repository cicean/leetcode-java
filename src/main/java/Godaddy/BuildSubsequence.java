package Godaddy;

import datastructure.PrintList;

import java.util.*;

/**
 * Created by cicean on 10/14/2016.
 *
 * Build the subsequence
 *
 * 给一个String 比如ab, 返回它的所有subset:{a, b, ab}可以参考leetcode 78:subset
 * Combination of Characters in String (All subsets of characters)
 */
public class BuildSubsequence {

    public List<String> combinationString(String s) {
        List<String> res = new ArrayList<>();
        char[] a = s.toCharArray();
        //Arrays.sort(a);
        String[] tmp = res.toArray(new String[res.size()]);
        combinationStringRe(a, 0, new StringBuilder(), res);
        return res;
    }

    private void combinationStringRe(char[] a, int start, StringBuilder path, List<String> res) {
        if (path.length() != 0 && !res.contains(path.toString())) {
            res.add(path.toString());
        } 

        for (int i = start; i < a.length; i++) {
            path.append(a[i]);
            combinationStringRe(a, i + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }
    
    public void combination(char input[]){
        Map<Character, Integer> countMap = new TreeMap<>();
        for (char ch : input) {
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }
        char str[] = new char[countMap.size()];
        int count[] = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            str[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }
        char[] output = new char[input.length];
        combination(str, count, 0, output, 0);
     }

     private void combination(char input[],int count[],int pos, char output[],int len){
         
         for(int i=pos; i < input.length; i++){
             if (count[i] == 0) {
                 continue;
             }
             output[len] = input[i];
             count[i]--;
             combination(input, count, i, output, len + 1);
             count[i]++;
         }
     }
    
    public static void main(String[] args) {
		BuildSubsequence slt = new BuildSubsequence();
		PrintList<String> res = new PrintList<>();
		res.printList(slt.combinationString("bc"));;
	}
}
