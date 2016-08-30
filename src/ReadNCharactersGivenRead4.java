/*
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.

Show Tags
Have you met this question in a real interview?

˼·���տ�ʼ����Ŀ�����˰��������ʲô����˼��
����read4��һ�����ļ��ĺ�����ֻ�ܶ�4��char��
char [] buffer = new char[4]
int size = read4(buffer)
����read4����buffer�ĳ���file 4 char.
Ȼ����Ŀ��Ŀ���ǣ� ��read4,����read��������n���ַ���Ȼ��Ҫ��
read(char[] buf, int n)�����bufҲ���ˡ�Ҳ����˵��Ҫ��buffer��ֵbuf.
����ֻ�ܶ�n��char��

read Ҫ���ض��˶��ٸ�char.
��Ŀ��ʵ�ڿ����ּ��������

1. �ļ����ֻʣ��<4��ʱ��Ҳ������Ҫ����char���� >> �ļ������е�char��Ŀ�����磺n=50 readbyte=23. ������<4��ʱ��read��һ�ξͲ����ˡ�
Ȼ��ֵ��ʱ��ֻ��ֵ�����<4��char ��buf��

(����n = 50, readbyte = 20 �������Ҳ�������һ��Ҳ��4��whileloop readbyte<n, �Զ������������ټ������ˡ�)

2. �ļ�char��Ŀ>>n,���磺�ļ���50��char��ֻҪ��23��char��read4�����4��char�������������ֻ��Ҫ��3��char���������ֵ��ʱ��ֻ��Ҫ���3����

byte = Math.min(n-readbytes, size);
 */



public class ReadNCharactersGivenRead4 extends Reader4 {
	
	 public int read(char[] buf, int n) {  
	        char[] buffer = new char[4];  
	          
	        boolean lessthan4 = false;  
	        int Readbyte = 0;  
	        int bytes = 0;  
	          
	        while(!lessthan4 && Readbyte<n){  
	            int size = read4(buffer);  
	            if(size<4){  
	                lessthan4 = true;  
	            }  
	              
	            bytes = Math.min(n-Readbyte,size);  
	            for(int i=0; i<bytes; i++){  
	                buf[Readbyte+i] = buffer[i];  
	            }  
	            Readbyte += bytes;  
	        }  
	        return Readbyte;  
	    }  
	 
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Reader4{
	int read4(char[] buf) {
		return 4;
	}
}
