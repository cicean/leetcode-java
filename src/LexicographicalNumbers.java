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

 * ��������n������1~n������������ɵļ��ϣ�Ҫ�����ֵ��˳��
 */
public class LexicographicalNumbers {

    /**
     * �������Ŀ����Ҫ�����ֵ������1~n�����֣���Ŀ˵���������n���ܷǳ������Կ϶������������������֣�Ȼ�������ˡ�

     �ҵĽ��ⷽʽ�ǰ���λ��ȥ���ɣ����ǿ��Թ۲��¸�����ʵ��n=13�����Ƿ�����Щ�����ֵ�������ֵĹ����ǣ�

     ���������ֵĶԱ��ǴӸ�λ����λ�ȵģ��ڼ䰴�����˳���ĸ��������ȳ��ֶ�Ӧλ����ֵС����һ������ô������ǰ�档
     ����������ֳ���һ�£�һ������ֱ�ӱȳ�����������һ���Ļ�����ô�Ȱ��ո�λ����λ�ȣ�֪������С����һ������Ϊֹ����ʱ����С����ǰ��
     ���ϣ������������ɹ����ǣ�

     ʹ��digits��������ʾÿһλ��ȡֵ����len����ʾ�������ɵĽ�ֹλ�ã�����digits�Ǹ�λ��ǰ��
     ��ʼ����ֻ��len=1λ��ʼ���ɣ�����һλĬ��Ϊdigits[0]=1
     ���κ�һ��״̬(��ǰ����Ϊtmp����
     1 ���tmp�����һ��0��Ҳ����tmp*10��С�ڵ���n����ô��һ�����־���tmp��һ��0��len++��
     2 ���1�����㣬��ô���ȸ���digits��len����֤digits[len]+1�����ɵ����֣��Ƿ��������ģ�������ɺ������һ������
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
