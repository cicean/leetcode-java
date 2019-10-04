import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 345. Reverse Vowels of a String  QuestionEditorial Solution  My Submissions
 Total Accepted: 39627
 Total Submissions: 108781
 Difficulty: Easy
 Write a function that takes a string as input and reverse only the vowels of a string.

 Example 1:
 Given s = "hello", return "holle".

 Example 2:
 Given s = "leetcode", return "leotcede".

 Note:
 The vowels does not include the letter "y".

 Hide Company Tags Google
 Hide Tags Two Pointers String
 Hide Similar Problems (E) Reverse String
 ���⣺����һ���ַ���������������Ԫ����ĸ����

 ˼·��ͬ��˫ָ�룬����Ԫ����a,e,i,o,u ע���Сд����ͣ����
 */
public class ReverseVowelsofaString {

    /**
     * ˼·�� ʹ��two pointers, һ��ָ���ͷ��β����һ��ָ���β��ͷ��һ������ԭ����ĸ��swap�� �ж�ĳЩԪ���Ƿ��ڼ����У� ʹ��hashset��

     ʱ�临�Ӷȣ� O(n) n is length of string
     �ռ临�Ӷȣ� O(n)
     */

    public class Solution {
        public String reverseVowels(String s) {
            Set<Character> set = new HashSet<Character>();
            set.add('a');
            set.add('e');
            set.add('i');
            set.add('o');
            set.add('u');
            set.add('A');
            set.add('E');
            set.add('I');
            set.add('O');
            set.add('U');
            char[] c = s.toCharArray();

            int i = 0, j = s.length() - 1;
            while (i < j) {
                while (i < j && !set.contains(c[i])) {
                    i++;
                }
                while (i < j && !set.contains(c[j])) {
                    j--;
                }
                char tmp = c[i];
                c[i] = c[j];
                c[j] = tmp;
                i++;
                j--;
            }
            return String.valueOf(c);
        }
    }

    public class Solution1 {
        static final String vowels = "aeiouAEIOU";
        public String reverseVowels(String s) {
            int first = 0, last = s.length() - 1;
            char[] array = s.toCharArray();
            while(first < last){
                while(first < last && vowels.indexOf(array[first]) == -1){
                    first++;
                }
                while(first < last && vowels.indexOf(array[last]) == -1){
                    last--;
                }
                char temp = array[first];
                array[first] = array[last];
                array[last] = temp;
                first++;
                last--;
            }
            return new String(array);
        }
    }



    //2. StringBuilder --15ms
    public class Solution2 {
        public String reverseVowels(String s) {
            StringBuilder sb = new StringBuilder();
            int j = s.length() - 1;
            for (int i = 0; i < s.length(); i++) {
                if ("AEIOUaeiou".indexOf(s.charAt(i)) != -1) {
                    while (j >= 0 && "AEIOUaeiou".indexOf(s.charAt(j)) == -1) j--;
                    sb.append(s.charAt(j));
                    j--;
                }
                else sb.append(s.charAt(i));
            }
            return sb.toString();
        }
    }


}
