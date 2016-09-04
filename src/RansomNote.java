import java.util.*;

/**
 * Created by cicean on 9/2/2016.
 * 383. Ransom Note  QuestionEditorial Solution  My Submissions
 Total Accepted: 14028 Total Submissions: 31748 Difficulty: Easy
 ?Given? an ?arbitrary? ransom? note? string ?and ?another ?string ?containing ?letters from? all ?the ?magazines,? write ?a ?function ?that ?will ?return ?true ?if ?the ?ransom ? note ?can ?be ?constructed ?from ?the ?magazines ; ?otherwise, ?it ?will ?return ?false. ??

 Each ?letter? in? the? magazine ?string ?can? only ?be? used ?once? in? your ?ransom? note.

 Note:
 You may assume that both strings contain only lowercase letters.

 canConstruct("a", "b") -> false
 canConstruct("aa", "ab") -> false
 canConstruct("aa", "aab") -> true
 Hide Company Tags Apple
 Hide Tags String
 ���������ַ���magazines��ransomNote�����Ƿ���Դ�magazines�г�ȡ��ĸ��ÿ����ĸֻ����һ�Σ����ransomNote
 */
public class RansomNote {

    /**
     * ֻҪ�ж�ransomNote�ַ��ǲ���ȫ����magazines�м��ɣ���hash��
     */
    public class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] cnt = new int[26];
            for (int i = 0; i < magazine.length(); i++) cnt[magazine.charAt(i) - 97]++;// 97 == 'a'
            for (int i = 0; i < ransomNote.length(); i++) if (--cnt[ransomNote.charAt(i) - 97] < 0) return false;
            return true;
        }
    }

    //map
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magM = new HashMap<>();
        for (char c:magazine.toCharArray()){
            int newCount = magM.getOrDefault(c, 0)+1;
            magM.put(c, newCount);
        }
        for (char c:ransomNote.toCharArray()){
            int newCount = magM.getOrDefault(c,0)-1;
            if (newCount<0)
                return false;
            magM.put(c, newCount);
        }
        return true;
    }

}
