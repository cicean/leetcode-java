import java.util.LinkedList;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

���read�������ܱ���ε���

[����]
��֮ǰ��һ����Ŀ�ȣ���һ��Ҫ���Ӳ��١���Ҫ����Ϊread�������Ե��ö���Ժ��п����ļ��еĲ������ݱ���������������ʱû���õ��������Ҫ����Ŀռ���������������ַ���

[ע������]
1�����ڼ��������Ľ����
�� buffer �洢���ļ��ж��������ַ�
�� offset ��һ�ζ�ȡ֮��buffer��ʣ���ַ���ƫ����
�� bufsize buffer��ʣ���ַ��ĸ���
 */

  /**
	  * @param buf Destination buffer
	  * @param n   Maximum number of characters to read
	  * @return    The number of characters read
	  */

public class ReadNCharactersGivenRead4II extends Reader4 {

	LinkedList<Character> queue = new LinkedList<Character>();

	
	  public int read(char[] buf, int n) {

	    char[] _buf = new char[4];

	    int total = 0;

	    while(true){
	      int l = read4(_buf);

	      for(int i = 0; i < l; i++){
	        queue.add(_buf[i]);
	      }

	      l = Math.min(n - total, queue.size());

	      for(int i = 0; i < l; i++){
	        buf[total++] = queue.poll();
	      }

	      if(l == 0) break;
	    }

	    return total;
	  }
	
	
}
 
 

