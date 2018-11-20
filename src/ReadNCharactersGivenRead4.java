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
 * ���Ӷ�

˼·
��һ����ʱ���飬���ÿ��read4�����ַ�������һ��ָ����buf����Ŀǰ�洢����λ�ã�Ȼ�������ʱ��������ݴ浽buf��Ӧ��λ�þ����ˡ�������Ҫע������corner case��

������ζ�������ַ�����������ֻ��Ҫ����һ���־�����ɶ�ȡ����ʱ������Ҫ�����ĳ����Ǳ��ζ����ĸ�����ʣ����������н�С��
���read4û�ж���4����˵�������Ѿ����꣬��ʱ����ڶ��������ݳ��ȣ���ΪҲ���ܴ�������ֻ��Ҫ����һ���ֵ����������Ҫ���������賤�Ⱥ�Ŀǰ�Ѿ������ĳ��ȵĽ�С��
 * @author cicean
 *
 */

//ʱ�� O(N) �ռ� O(1)
class Solution extends Reader4 {
    public int read(char[] buf, int n) {
        for(int i = 0; i < n; i += 4){
            char[] tmp = new char[4];
            // �����ݶ�����ʱ����
            int len = read4(tmp);
            // ����ʱ���鿽����buf���飬�������ĳ����Ǳ��ζ����ĸ�����ʣ����������н�С��
            System.arraycopy(tmp, 0, buf, i, Math.min(len, n - i));
            // ���������4����˵���Ѿ������ˣ����������賤�Ⱥ�Ŀǰ�Ѿ������ĳ��ȵĽ�С��
            if(len < 4) return Math.min(i + len, n);
        }
        // ���ѭ����û�з��أ�˵����ȡ���ַ���4�ı���
        return n;
    }
}

/**
 * ˼·���տ�ʼ����Ŀ�����˰��������ʲô����˼��
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
