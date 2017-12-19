/**
 * 258 Add Digits
 * @author cicean
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Hint:

A naive implementation of the above process is trivial. Could you come up with other methods?
What are all the possible results?
How do they occur, periodically or randomly?
You may find this Wikipedia article useful.
 *
 * solution 2:
 * ��һ�������Ƚϼ򵥣����Ծ���˵��һ�¡����������������һ��5λ����num����num�ĸ�λ�ֱ�Ϊa��b��c��d��e��

�����¹�ϵ��num = a * 10000 + b * 1000 + c * 100 + d * 10 + e

����num = (a + b + c + d + e) + (a * 9999 + b * 999 + c * 99 + d * 9)

��Ϊ a * 9999 + b * 999 + c * 99 + d * 9 һ�����Ա�9���������numģ��9�Ľ���� a + b + c + d + e ģ��9�Ľ����һ���ġ�

������ a + b + c + d + e ����ִ��ͬ����������Ľ������һ�� 1-9 �����ּ���һ�����֣�����ߵ������� 1-9 ֮��ģ�
�Ҳ��������Զ���ǿ��Ա�9�����ġ�

���������Ŀ�꣬���ǲ��Ͻ���λ��ӣ���ӵ���󣬵����С��10ʱ���ء���Ϊ�������1-9֮�䣬
�õ�9֮�󽫲����ٶԸ�λ������ӣ���˲�����ֽ��Ϊ0���������Ϊ (x + y) % z = (x % z + y % z) % z��
����Ϊ x % z % z = x % z����˽��Ϊ (num - 1) % 9 + 1��ֻģ��9һ�Σ�����ģ����Ľ����һ���ء�
 */
public class AddDigits {
	
	public int addDigits(int num) {
		int next = getNext(num);
		while (next >= 10) {
			next = getNext(num);
		}
		return next;
	}
	
	private int getNext(int num) {
		String s = String.valueOf(num);
		int sum = 0;
		for (char ch : s.toCharArray()) {
			sum += (ch - '0');
		}
		return sum;
	}

	public class Solution {

		public int addDigits(int num) {

			return num==0?0:(num%9==0?9:(num%9));

		}

	}

	public int addDigits_2(int num) {
		return (num-1)%9 + 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
