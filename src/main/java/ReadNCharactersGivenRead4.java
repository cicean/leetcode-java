/*
 * 157. Read N Characters Given Read4  QuestionEditorial Solution  My Submissions
Total Accepted: 16072
Total Submissions: 54370
Difficulty: Easy
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.

Hide Company Tags Facebook
Hide Tags String
Hide Similar Problems (H) Read N Characters Given Read4 II - Call multiple times


 */


import java.util.*;

/**
 * 复杂度

思路
用一个临时数组，存放每次read4读到字符，再用一个指针标记buf数组目前存储到的位置，然后将这个临时数组的内容存到buf相应的位置就行了。这里需要注意两个corner case：

如果本次读到多个字符，但是我们只需要其中一部分就能完成读取任务时，我们要拷贝的长度是本次读到的个数和剩余所需个数中较小的
如果read4没有读满4个，说明数据已经读完，这时候对于读到的数据长度，因为也可能存在我们只需要其中一部分的情况，所以要返回总所需长度和目前已经读到的长度的较小的
 * @author cicean
 *
 */

//时间 O(N) 空间 O(1)
class Solution extends Reader4 {
    public int read(char[] buf, int n) {
        for(int i = 0; i < n; i += 4){
            char[] tmp = new char[4];
            // 将数据读入临时数组
            int len = read4(tmp);
            // 将临时数组拷贝至buf数组，这里拷贝的长度是本次读到的个数和剩余所需个数中较小的
            System.arraycopy(tmp, 0, buf, i, Math.min(len, n - i));
            // 如果读不满4个，说明已经读完了，返回总所需长度和目前已经读到的长度的较小的
            if(len < 4) return Math.min(i + len, n);
        }
        // 如果循环内没有返回，说明读取的字符是4的倍数
        return n;
    }
}

/**
 * 思路：刚开始看题目，看了半天才明白什么个意思。
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
 * @author cicean
 *
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

// facebook
class ReadNCharactersGivenRead4K extends Reader4k {

	public int read(char[] buf, int n) {
		char buffer[4096];
		int count = 0;
		int remain = n;
		int tmp = 4096;

		while(remain > 0 && tmp == 4096) {
			tmp = Read4k(buffer);
			if(tmp < remain) {
				memcpy(buf+count, buffer, tmp);
				count += tmp;
				remain -= tmp;
			}else {
				memcpy(buf+count, buffer, remain);
				return n;
			}
		}
		return count;
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

class Reader4k{
	int read4k(char[] buf) {
		return 4096;
	}
}


public List<String> buildOrder(String target, Map<String, List<String>> dependences) {
	if (target == null || dependences == null) return null;

	Map<String, Integer> indegree = new HashMap<>();
	Map<String, List<String>> prebuildtotarget = new HashMap<>();
	Queue<String> queue = new LinkedList<>();
	Set<String> uniquedependence = new HashSet<>();
	for (Map.Entry<String, List<String>> entry : dependences.entrySet()) {
		indegree.put(entry.getKey(), entry.getValue().size());
		uniquedependence.add(entry.getKey());
		for (String prebuilddependence : entry.getValue()) {
			prebuildtotarget.computeIfAbsent(prebuilddependence, x -> new ArrayList<String>()).add(entry.getKey());
			uniquedependence.add(prebuilddependence);
			if (!dependences.containsKey(prebuilddependence)) {
				indegree.put(prebuilddependence, 0);
				queue.offer(prebuilddependence);
			}
		}
	}

	List<String> results = new ArrayList<>();

	while (!queue.isEmpty()) {
		String curr = queue.poll();
		results.add(curr);
		if (!prebuildtotarget.containsKey(curr)) {
			continue;
		}

		for (String next : prebuildtotarget.get(curr)) {
			if (indegree.get(next) - 1 == 0) {
				queue.offer(next);
			}
			indegree.put(next, indegree.get(next) - 1);
		}


	}

	return results.size() != uniquedependence.size() ? new ArrayList<>() : results;

}
}
