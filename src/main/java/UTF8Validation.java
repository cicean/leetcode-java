/**
 * Created by cicean on 9/8/2016. 393. UTF-8 Validation QuestionEditorial
 * Solution My Submissions Total Accepted: 1416 Total Submissions: 4547
 * Difficulty: Medium A character in UTF8 can be from 1 to 4 bytes long,
 * subjected to the following rules:
 * 
 * For 1-byte character, the first bit is a 0, followed by its unicode code. For
 * n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed
 * by n-1 bytes with most significant 2 bits being 10. This is how the UTF-8
 * encoding would work:
 * 
 * Char. number range | UTF-8 octet sequence (hexadecimal) | (binary)
 * --------------------+--------------------------------------------- 0000
 * 0000-0000 007F | 0xxxxxxx 0000 0080-0000 07FF | 110xxxxx 10xxxxxx 0000
 * 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx 0001 0000-0010 FFFF | 11110xxx
 * 10xxxxxx 10xxxxxx 10xxxxxx Given an array of integers representing the data,
 * return whether it is a valid utf-8 encoding.
 * 
 * Note: The input is an array of integers. Only the least significant 8 bits of
 * each integer is used to store the data. This means each integer represents
 * only 1 byte of data.
 * 
 * Example 1:
 * 
 * data = [197, 130, 1], which represents the octet sequence: 11000101 10000010
 * 00000001.
 * 
 * Return true. It is a valid utf-8 encoding for a 2-bytes character followed by
 * a 1-byte character. Example 2:
 * 
 * data = [235, 140, 4], which represented the octet sequence: 11101011 10001100
 * 00000100.
 * 
 * Return false. The first 3 bits are all one's and the 4th bit is 0 means it is
 * a 3-bytes character. The next byte is a continuation byte which starts with
 * 10 and that's correct. But the second continuation byte does not start with
 * 10, so it is invalid. Hide Company Tags Google Hide Tags Bit Manipulation
 */
public class UTF8Validation {

	public boolean validUtf8(int[] data) {
		int idx = 0;
		while (idx < data.length) {
			int utfIdx = idx++;
			if ((data[utfIdx] & (1 << 7)) == 0)
				continue; // single byte
			for (int i = 6; i >= 0; i--) {
				if ((data[utfIdx] & (1 << i)) > 0) { // find one more byte in
														// multiple bytes
					if (idx >= data.length)
						return false; // not enough bytes
					if (((data[idx] & (1 << 7)) == 0)
							|| ((data[idx] & (1 << 6)) > 0))
						return false; // not starting with 10xxxxxx
					idx++;
				} else if (i == 6)
					return false; // for fist byte in multiple bytes is
									// 10xxxxxx, at least 110xxxxx
				else
					break; // meet 0, remaining as utf content
			}
		}
		return true;
	}

	public class Solution {
		int getLen(int bt) {
			int mask = 0x0080;
			if ((bt & mask) == 0)
				return 0; // check first bit is 1 or 0
			mask >>= 1;
			if ((bt & mask) == 0)
				return -1; // check the second bit, if 0 return -1
			mask >>= 1;
			int len = 1; // using while loop to find the size
			while ((mask != 0) && ((bt & mask) == mask)) {
				mask >>= 1;
				len++;
			}
			return len == 7 ? -1 : len; // the case of 11111111, return -1.
		}

		public boolean validUtf8(int[] data) {
			if (data == null || data.length == 0)
				return false;
			int i = 0;
			while (i < data.length) {
				int len = getLen(data[i]); // get the size from the header byte
				if ((len < 0) || (i + len) >= data.length)
					return false; // not a validate header byte or there is a
									// shortage on the length
				while (len > 0) { // check the data bytes
					if ((data[++i] >> 6) != 2)
						return false; // check 10xxxxxx
					len--;
				}
				if (len > 0)
					return false; // shortage on the length
				i++;
			}
			return true;
		}
	}

	/**
	 * ���ַ���Ҳ��Ҫ������1�ĸ���������Ǳ�ʶ�ֽڣ� �Ƚ�������ƽ����λ������õ�110����˵���������һ���ֽڣ�
	 * ��������ƽ����λ������õ�1110����˵��������������ֽڣ���������ƽ����λ�� ����õ�11110����˵��������������ֽڣ�
	 * ��������ƽ����λ�����Ϊ1�Ļ���˵����10000000������������ܵ���ʶ�ֽڣ�ֱ�ӷ���false���ڷǱ�ʶ�ֽ��У�
	 * ����ƽ����λ������õ��Ĳ���10����˵��������10��ͷ�ģ�ֱ�ӷ���false������cnt�Լ�1���ɹ���ɱ�������true��
	 * 
	 * @param data
	 * @return
	 */
	public boolean validUtf8_gg(int[] data) {
		int count = 0;
		for (int d : data) {
			if (count == 0) {
				if ((d >> 5) == 0b110)
					count = 1;
				else if ((d >> 4) == 0b1110)
					count = 2;
				else if ((d >> 3) == 0b11110)
					count = 3;
				else if ((d >> 7) == 1) 
					return false;
			} else {
				if ((d >> 6) != 0b10) return false;
				count--;
			}
		}
		return count == 0;
	}

}
