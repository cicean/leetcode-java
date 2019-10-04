import java.util.ArrayList;
import java.util.List;

/**
 * 	282	Expression Add Operators	17.7%	Hard
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 * 
 * Examples: "123", 6 -> ["1+2+3", "1*2*3"] "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"] "00", 0 -> ["0+0", "0-0", "0*0"] "3456237490",
 * 9191 -> [] Credits: Special thanks to @davidtan1890 for adding this problem
 * and creating all test cases.
 * 
 * Hide Tags Divide and Conquer Hide Similar Problems (M) Evaluate Reverse
 * Polish Notation (M) Basic Calculator (M) Basic Calculator II (M) Different
 * Ways to Add Parentheses
 * ������������һ��ֻ��������ɵ��ַ��������������������+,-��*�����γ�һ�����ʽ���ñ��ʽ�ļ����Ϊ������targetֵ
 * ���������ҳ����з���Ҫ��ı��ʽ������Ŀ�и��ļ���������ʵ�����ã���������������Ϊ�Ǳ����ɸ�λ���֣���ʵ���ǵģ�����"123",
 * 15�ܷ���"12+3"��˵�����ŵ�����Ҳ���ԡ������Ҫ�ڹ�����������һ�����Ƶ��⣬�Ҿ��ø�Combination Sum II
 * ���֮��֮�������ơ����������Ҫ�������鷳һЩ
 * �������õݹ������⣬������Ҫ��������diff��curNum��һ��������¼��Ҫ�仯��ֵ����һ���ǵ�ǰ������ֵ���������Ƕ���Ҫ��long
 * long�͵ģ���Ϊ�ַ���תΪint�ͺ�������������������ó����͡����ڼӺͼ���diff���Ǽ���Ҫ���ϵ����ͼ���Ҫ��ȥ�����ĸ�ֵ�������ڳ���˵����Щ���ӣ�
 * ��ʱ��diffӦ������һ�εı仯��diff���Լ���Ҫ���ϵ���
 * ���е㲻����⣬���������ٸ����ӣ�����2+3*2������Ҫ���㵽����2��ʱ���ϴ�ѭ����curNum = 5, diff = 3,
 * ���������Ҫ�������2��ʱ��
 * ���µı仯ֵdiffӦΪ3*2=6��������Ҫ��֮ǰ+3�����Ľ��ȥ�����ټ����µ�diff����(5-3)+6=8����Ϊ�±��ʽ2+
 * 3*2��ֵ���е�����⣬����Լ�һ��һ������ɡ�
 * 
 * ����һ����Ҫע����ǣ��������Ϊ"000",0�Ļ������׳������µĴ���
 * 
 * Wrong:["0+0+0","0+0-0","0+0*0","0-0+0","0-0-0","0-0*0","0*0+0","0*0-0",
 * "0*0*0","0+00","0-00","0*00","00+0","00-0",
 * 
 * "00*0","000"]
 * 
 * Correct��["0*0*0","0*0+0","0*0-0","0+0*0","0+0+0","0+0-0","0-0*0","0-0+0",
 * "0-0-0"]
 * 
 * ���ǿ��Կ�������Ľ������0��ͷ���ַ������֣������ⲻ�����֣���������Ҫȥ����Щ��������˷���Ҳ�ܼ򵥣�
 * ����ֻҪ�жϳ��ȴ���1�����ַ��ǡ�0�����ַ�����������ȥ���ɣ��μ��������£�
 * 
 * @author cicean
 *
 */
public class ExpressionAddOperators {

	public List<String> addOperators(String num, int target) {
		List<String> list = new ArrayList<String>();
		if (num.length() == 0)
			return list;
		dfs(num, 0, "", 0, 0, 1, target, list);
		return list;
	}

	public void dfs(String num, int pos, String cStr, int preNum, int cNum,
			int sign, int target, List<String> list) {
		if (sign * cNum + preNum == target && pos == num.length()) {
			list.add(cStr);
			return;
		}
		if (pos >= num.length())
			return;
		int current = 0;
		for (int i = pos; i < num.length(); i++) {
			long currentLong = current * 10 + (long) (num.charAt(i) - '0');
			if (currentLong > Integer.MAX_VALUE
					|| currentLong < Integer.MIN_VALUE)
				return;
			current = (int) currentLong;
			String currentStr = num.substring(pos, i + 1);
			if (pos == 0) {
				dfs(num, i + 1, currentStr, preNum + sign * cNum, current, 1,
						target, list); // for the first one, only add
				if (current == 0)
					break;
				continue;
			}
			dfs(num, i + 1, cStr + "+" + currentStr, preNum + sign * cNum,
					current, 1, target, list); // add
			dfs(num, i + 1, cStr + "-" + currentStr, preNum + sign * cNum,
					current, -1, target, list); // minus
			dfs(num, i + 1, cStr + "*" + currentStr, preNum, current * cNum,
					sign, target, list); // multiply
			if (current == 0)
				return;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
