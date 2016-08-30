/*
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.

Show Tags
Have you met this question in a real interview?

思路：刚开始看题目，看了半天才明白什么个意思。
首先read4是一个读文件的函数，只能读4个char。
char [] buffer = new char[4]
int size = read4(buffer)
这里read4将空buffer改成了file 4 char.
然后题目的目的是： 用read4,构成read函数，读n个字符，然后要将
read(char[] buf, int n)里面的buf也改了。也就是说，要用buffer赋值buf.
而且只能读n个char。

read 要返回读了多少个char.
题目其实在考两种极端情况：

1. 文件最后只剩下<4的时候，也就是需要读的char字数 >> 文件本身有的char数目，比如：n=50 readbyte=23. 最后读到<4的时候，read下一次就不读了。
然后赋值的时候，只赋值最后这<4的char 给buf。

(至于n = 50, readbyte = 20 的情况，也就是最后一个也是4，whileloop readbyte<n, 自动会跳出，不再继续读了。)

2. 文件char数目>>n,比如：文件有50个char，只要读23个char。read4会读满4个char，但是最后我们只需要这3个char。所以最后赋值的时候，只需要最后3个。

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
