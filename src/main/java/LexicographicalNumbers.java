import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cicean on 9/2/2016.
 *
 * 386. Lexicographical Numbers  QuestionEditorial Solution  My Submissions
 Total Accepted: 3911 Total Submissions: 12469 Difficulty: Medium
 Given an integer n, return 1 - n in lexicographical order.

 For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

 Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.

 Hide Company Tags Bloomberg

 * 给定整数n，返回1~n的所有数字组成的集合，要求按照字典的顺序
 */
public class LexicographicalNumbers {

    /**
     * 这题的题目就是要求按照字典序输出1~n的数字，题目说的是输入的n可能非常大，所以肯定不是让你先生成数字，然后排序了。

     我的解题方式是按照位数去生成，我们可以观察下给定的实例n=13，我们发现这些按照字典序的数字的规律是：

     数字与数字的对比是从高位到低位比的，期间按照这个顺序，哪个数字最先出现对应位上数值小于另一个，那么就排在前面。
     如果两个数字长度一致，一定可以直接比出结果，如果不一样的话，那么先按照高位到低位比，知道长度小的那一方用完为止，这时长度小的在前面
     综上，我摸索的生成规则是：

     使用digits数字来表示每一位的取值，用len来表示数字生成的截止位置，这里digits是高位在前。
     初始：从只有len=1位开始生成，且这一位默认为digits[0]=1
     在任何一个状态(当前数字为tmp）下
     1 如果tmp后面加一个0（也就是tmp*10）小于等于n，那么下一个数字就是tmp加一个0（len++）
     2 如果1不满足，那么首先更新digits和len，保证digits[len]+1后生成的数字，是符合条件的，更新完成后就是下一个数字
     */

    //dfs
    public class Solution {
        public void solve(int curr, int n, List<Integer> ret){
            if(curr > n){//curr is the number
                return;
            }
            ret.add(curr);
            for(int i = 0; i < 10; i++){//append 0-9 to the end of curr
                if(curr*10 + i <= n){//recurse as long as its less than n
                    solve(curr*10 + i, n, ret);
                } else break;
            }
        }
        public List<Integer> lexicalOrder(int n) {
            List<Integer> ret = new ArrayList<Integer>();
            for(int i = 1; i < 10; i++){//fix first digit
                solve(i, n, ret);
            }
            return ret;
        }
    }

    /**
     * The basic idea is to find the next number to add.
     Take 45 for example: if the current number is 45, the next one will be 450 (450 == 45 * 10)(if 450 <= n), or 46 (46 == 45 + 1) (if 46 <= n) or 5 (5 == 45 / 10 + 1)(5 is less than 45 so it is for sure less than n).
     We should also consider n = 600, and the current number = 499, the next number is 5 because there are all "9"s after "4" in "499" so we should divide 499 by 10 until the last digit is not "9".
     It is like a tree, and we are easy to get a sibling, a left most child and the parent of any node.
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return list;
    }

    //Bloomberg interview
    private List<Integer>  lexicalOrder2(int n) {

        List<Integer> res = new ArrayList<>();

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String a = "" + o1;
                String b = "" + o2;
                return a.compareTo(b);
            }
        };

        Integer[] in = new Integer[n];
        for (int i = 0; i < n; i++) {
            in[i] = Integer.valueOf(i + 1);
        }

        Arrays.sort(in,comp);

        //System.out.print(Arrays.deepToString(in));
        res.addAll(Arrays.asList(in));

        return res;
    }

}
