import java.util.*;

/**
 * 158. Read N Characters Given Read4 II - Call multiple times  QuestionEditorial Solution  My Submissions
 Total Accepted: 12785
 Total Submissions: 53156
 Difficulty: Hard
 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function may be called multiple times.

 Hide Company Tags Bloomberg Google Facebook
 Hide Tags String
 Hide Similar Problems (E) Read N Characters Given Read4


 */

/**
 * @param buf Destination buffer
 * @param n   Maximum number of characters to read
 * @return    The number of characters read
 */

/**
 * ���з� ���Ӷ� ʱ�� O(N) �ռ� O(1)
 * 
 * ˼· ��ΪҪ���ö�Σ������ֶ���һЩcorner case��
 * 
 * ��һ�ε���ʱ�����read4�����Ķ����ַ�����Ҫ�Ƚ����ݴ������������ڶ��ε���ʱ�ȶ�ȡ��Щ�ݴ���ַ�
 * �ڶ��ε���ʱ��������ݴ��ַ���û���꣬��ô��Щ�ݴ��ַ��������������ε���ʱʹ��
 * ���ԣ��ѵ��������ô��������ݴ��ַ�����Ϊ�������ָ����ƶԵڶ�������Ƚ��鷳��
 * ����Щ�ַ������Ƚ��ȳ����������ǿ�����һ�������ݴ���Щ�ַ���������ֻҪ���в�Ϊ�գ����ȶ�ȡ���С�
 * 
 * @author cicean
 *
 */

class Solution extends Reader4 {
	Queue<Character> remain = new LinkedList<Character>();

	public int read(char[] buf, int n) {
		int i = 0;
		// ���в�Ϊ��ʱ���ȶ�ȡ�����е��ݴ��ַ�
		while (i < n && !remain.isEmpty()) {
			buf[i] = remain.poll();
			i++;
		}
		for (; i < n; i += 4) {
			char[] tmp = new char[4];
			int len = read4(tmp);
			// ��������ַ�����������Ҫ���ַ�����Ҫ�ݴ���Щ�����ַ�
			if (len > n - i) {
				System.arraycopy(tmp, 0, buf, i, n - i);
				// �Ѷ�����ַ����������
				for (int j = n - i; j < len; j++) {
					remain.offer(tmp[j]);
				}
				// ����������ַ�����������Ҫ���ַ���ֱ�ӿ���
			} else {
				System.arraycopy(tmp, 0, buf, i, len);
			}
			// ͬ���ģ����������4����˵�������Ѿ����꣬���������賤�Ⱥ�Ŀǰ�Ѿ������ĳ��ȵĽ�С��
			if (len < 4)
				return Math.min(i + len, n);
		}
		// ��������˵������������ȡ��ֱ�ӷ���n
		return n;
	}
}

/**
 * ���� ��ζ���һ�ζ�����Ҫ��ͬ����read4()�����е�buffer�൱��global�ģ�ÿ��read()��ʱ��ǰ��read4()
 * �����buffer��ʣ�����ַ��Ļ�Ҫ�����ã��������ֵ���read4()��buffer�����������ݹ�read��ȡ��
 * 
 * ��������ֻҪ��һ��global�����read4()��buffer����ʼλ�ú���ֹλ�ü��ɡ�ÿ��read()�ȶ��ϴ�buffer��û������ַ���
 * �������ֵ���read4(). ��Ȼ��Խ�����⻹�ǵ�ע�⣬����һ����һ����
 * 
 * ���Ӷ� time: O(n), space: O(1)
 * 
 * @author cicean
 *
 */

/*
 * The read4 API is defined in the parent class Reader4. int read4(char[] buf);
 */

class Solution2 extends Reader4 {
	char[] buffer = new char[4];
	int start = 0; // inclusive
	int end = 0; // exclusive

	/**
	 * @param buf
	 *            Destination buffer
	 * @param n
	 *            Maximum number of characters to read
	 * @return The number of characters read
	 */
	public int read(char[] buf, int n) {
		int i = 0;
		while (i < n) {

			// ֮ǰread4()����buffer����ַ���ȫ������
			if (start == end) {
				end = read4(buffer);
				start = 0;
			}

			// ���ΰ�buffer����ַ�����buf��
			while (i < n && start < end) {
				buf[i++] = buffer[start++];
			}

			// �ж��Ƿ񵽴��ļ�ĩβ���ǵĻ�����ѭ��
			if (end != 4)
				break;
		}
		return i;
	}
}

public class ReadNCharactersGivenRead4II extends Reader4 {

	LinkedList<Character> queue = new LinkedList<Character>();

	public int read(char[] buf, int n) {

		char[] _buf = new char[4];

		int total = 0;

		while (true) {
			int l = read4(_buf);

			for (int i = 0; i < l; i++) {
				queue.add(_buf[i]);
			}

			l = Math.min(n - total, queue.size());

			for (int i = 0; i < l; i++) {
				buf[total++] = queue.poll();
			}

			if (l == 0)
				break;
		}

		return total;
	}

}
