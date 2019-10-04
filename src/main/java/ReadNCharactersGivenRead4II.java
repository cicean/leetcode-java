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
 * 队列法 复杂度 时间 O(N) 空间 O(1)
 * 
 * 思路 因为要调用多次，这里又多了一些corner case：
 * 
 * 第一次调用时，如果read4读出的多余字符我们要先将其暂存起来，这样第二次调用时先读取这些暂存的字符
 * 第二次调用时，如果连暂存字符都没读完，那么这些暂存字符还得留给第三次调用时使用
 * 所以，难点就在于怎么处理这个暂存字符。因为用数组和指针控制对第二种情况比较麻烦，
 * 且这些字符满足先进先出，所以我们可以用一个队列暂存这些字符。这样，只要队列不为空，就先读取队列。
 * 
 * @author cicean
 *
 */

class Solution extends Reader4 {
	Queue<Character> remain = new LinkedList<Character>();

	public int read(char[] buf, int n) {
		int i = 0;
		// 队列不为空时，先读取队列中的暂存字符
		while (i < n && !remain.isEmpty()) {
			buf[i] = remain.poll();
			i++;
		}
		for (; i < n; i += 4) {
			char[] tmp = new char[4];
			int len = read4(tmp);
			// 如果读到字符多于我们需要的字符，需要暂存这些多余字符
			if (len > n - i) {
				System.arraycopy(tmp, 0, buf, i, n - i);
				// 把多余的字符存入队列中
				for (int j = n - i; j < len; j++) {
					remain.offer(tmp[j]);
				}
				// 如果读到的字符少于我们需要的字符，直接拷贝
			} else {
				System.arraycopy(tmp, 0, buf, i, len);
			}
			// 同样的，如果读不满4个，说明数据已经读完，返回总所需长度和目前已经读到的长度的较小的
			if (len < 4)
				return Math.min(i + len, n);
		}
		// 如果到这里，说明都是完美读取，直接返回n
		return n;
	}
}

/**
 * 分析 多次读与一次读的主要不同在于read4()函数中的buffer相当于global的，每次read()的时候前面read4()
 * 里读进buffer的剩下来字符的还要继续用，不够才又调用read4()往buffer里新增加内容供read读取。
 * 
 * 所以我们只要存一个global的针对read4()的buffer的起始位置和终止位置即可。每次read()先读上次buffer里没读完的字符，
 * 不够才又调用read4(). 当然，越界问题还是得注意，跟上一道题一样。
 * 
 * 复杂度 time: O(n), space: O(1)
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

			// 之前read4()读进buffer里的字符已全部读完
			if (start == end) {
				end = read4(buffer);
				start = 0;
			}

			// 依次把buffer里的字符读进buf里
			while (i < n && start < end) {
				buf[i++] = buffer[start++];
			}

			// 判断是否到达文件末尾，是的话跳出循环
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
