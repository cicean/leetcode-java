import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 8/29/2016.
 *
 * 301. Remove Invalid Parentheses  QuestionEditorial Solution  My Submissions
 Total Accepted: 18448 Total Submissions: 55068 Difficulty: Hard
 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]
 Credits:
 Special thanks to @hpplayer for adding this problem and creating all test cases.

 Hide Company Tags Facebook
 Hide Tags Depth-first Search Breadth-first Search
 Hide Similar Problems (E) Valid Parentheses

 */
public class RemoveInvalidParentheses {

    public void remove(String s, List<String> result, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            //���'('��')'���ȵĻ����ͼ���ɨ��ȥ
            if (stack >= 0) continue;
            //�������Ǿ��ҵ���ǰ�п���ɾȥ��')'��Ȼ��ɾ�����µ�string
            for (int j = last_j; j <= i; j++) {
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), result, i, j, par);
                }
            }
            return;
        }

        String reversed = new StringBuilder(s).reverse().toString();
        //���ֻ������ɨ�ˣ�par[0]����'('��ʱ�����ǻ�Ҫ�ٴ�������ɨһ��
        if (par[0] == '(') {
            remove(reversed, result, 0, 0, new char[]{')', '('});
        } else {
            //�������鶼ɨ���ˣ��ͼ�������ȥ
            result.add(reversed);
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

}
