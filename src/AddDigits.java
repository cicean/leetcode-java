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
 * 另一个方法比较简单，可以举例说明一下。假设输入的数字是一个5位数字num，则num的各位分别为a、b、c、d、e。

有如下关系：num = a * 10000 + b * 1000 + c * 100 + d * 10 + e

即：num = (a + b + c + d + e) + (a * 9999 + b * 999 + c * 99 + d * 9)

因为 a * 9999 + b * 999 + c * 99 + d * 9 一定可以被9整除，因此num模除9的结果与 a + b + c + d + e 模除9的结果是一样的。

对数字 a + b + c + d + e 反复执行同类操作，最后的结果就是一个 1-9 的数字加上一串数字，最左边的数字是 1-9 之间的，
右侧的数字永远都是可以被9整除的。

这道题最后的目标，就是不断将各位相加，相加到最后，当结果小于10时返回。因为最后结果在1-9之间，
得到9之后将不会再对各位进行相加，因此不会出现结果为0的情况。因为 (x + y) % z = (x % z + y % z) % z，
又因为 x % z % z = x % z，因此结果为 (num - 1) % 9 + 1，只模除9一次，并将模除后的结果加一返回。
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

	public int addDigits_2(int num) {
		return (num-1)%9 + 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
