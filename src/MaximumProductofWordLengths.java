import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by cicean on 8/29/2016.
 * 318. Maximum Product of Word Lengths  QuestionEditorial Solution  My Submissions
 Total Accepted: 31310 Total Submissions: 76496 Difficulty: Medium
 Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

 Example 1:
 Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 Return 16
 The two words can be "abcw", "xtfn".

 Example 2:
 Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 Return 4
 The two words can be "ab", "cd".

 Example 3:
 Given ["a", "aa", "aaa", "aaaa"]
 Return 0
 No such pair of words.

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Bit Manipulation

 Follow up:
 Could you do better than O(n2), where n is the number of words?

 题意：给定一个字符串数组，求它们中，元素各不相同的字符串长度乘积最大值。

 如样例1中，abcw 和 xtfn 成绩为4*4 =16 （元素互不相同） 而abcw 和 abcdef为0，因为有相同的元素

 */
public class MaximumProductofWordLengths {

    /**
     * 分析
     这道题首先想到的方法是brute force, 两个for loop遍历，对两个单词看是否有共同字母，
     求出product即可，显然这种方法时间复杂度是O(n^2*k), k表示单词平均长度。

     然而我们可以简化对于两个单词求product这一步，方法是我们做个预处理，遍历一遍单词，对于每个单词，我们用一个Integer表示其含有的字母情况。
     预处理之后，对于两个单词我们只需要对两个int做个和运算便可以知道两个单词是否存在相同字母。
     这种方法，我们可以将时间复杂度降到O(n^2)。

     当然，在以上方法的基础上，我们可以做一些小优化，
     比如事先对单词根据长度依照长度从大到小排序，这样在两个for loop过程中我们可以根据具体情况直接跳出，
     不用再继续遍历，具体可见代码。
     */

    //time: O(n^2), space: O(n)
    public class Solution {
        public int maxProduct(String[] words) {
            int[] maps = new int[words.length];

            // 将单词按照长度从长到短排序
            Arrays.sort(words, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return s2.length() - s1.length();
                }
            });

            // 对于每个单词，算出其对应的int来表示所含字母情况
            for (int i = 0; i < words.length; i++) {
                int bits = 0;
                for (int j = 0; j < words[i].length(); j++) {
                    char c = words[i].charAt(j);
                    // 注意bit运算优先级
                    bits = bits | (1 << (c - 'a'));
                }
                maps[i] = bits;
            }

            int max = 0;
            for (int i = 0; i < words.length; i++) {
                // 提前结束，没必要继续loop
                if (words[i].length() * words[i].length() <= max)
                    break;
                for (int j = i + 1; j < words.length; j++) {
                    if ((maps[i] & maps[j]) == 0) {
                        max = Math.max(max, words[i].length() * words[j].length());
                        // 下面的结果只会更小，没必要继续loop
                        break;
                    }
                }
            }
            return max;
        }
    }

    /**
     * 直接看看每个字符串都包括了哪个字符，然后一一枚举是否有交集：

     有交集，则乘积为0
     无交集，乘积为 words[i].length() * words[j].length()
     于是写出如下代码
     */

    public class Solution2 {
        public int maxProduct(String[] words) {
            int n = words.length;
            int[][] elements = new int[n][26];
            for (int i = 0; i < n; i++) {
                for (int j = 0;j < words[i].length(); j++) {
                    elements[i][words[i].charAt(j) - 'a'] ++;
                }
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    boolean flag = true;
                    for (int k = 0; k < 26; k++) {
                        if (elements[i][k] != 0 && elements[j][k] != 0) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag && words[i].length() * words[j].length()  > ans)
                        ans = words[i].length() * words[j].length();
                }
            }
            return ans;

        }
    }
}

