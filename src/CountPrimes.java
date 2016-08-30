import java.util.*;

/**
 * 204	Count Primes	19.0%	Easy
 *
 * 0.Problem:
 * Count the number of prime numbers less than a non-negative number, n
 * 
 * 1.Refer.:
 * 1.1 This solution exceeds time limit.
 * 2.1 This solution is the implementation of Sieve of Eratosthenes.
 * 
 * Sieve of Eratosthenes.
 * ��Ȼ���������⻹��Ч����Ҫ����һ��ɸѡ����
 * �Ƚ�1�ڵ�(��Ϊ1��������)��
 * ��2ȥ��������ĸ����������ܱ�2���������ڵ�������2�ı����ڵ���
 * ��3ȥ��������ĸ�������3�ı����ڵ���
 * �ֱ���4��5��������Ϊ����ȥ����Щ���Ժ�ĸ������������һֱ���е��ڳ������������ȫ���ڵ�Ϊֹ��������1��50��������Ҫһֱ���е�����Ϊ47Ϊֹ����ʵ�ϣ����Լ򻯣������Ҫ��1��n��Χ��������ֻ����е�����Ϊn^2(����n)��ȡ���������ɡ������1��50��ֻ����е���50^2��Ϊ�������ɡ���
 * �����һ������������ô��������������������Ȼ����������Ϊʲô��������ȥ�����أ�����˵4.��Ϊ�ܱ����������Ļ���Ȼ��ǰ��������������ˣ�������2��
 * 
 */
public class CountPrimes {

	public int countPrimes_1(int n) {
	    n = n-1;
	 
	    ArrayList<Integer> primes = new ArrayList<Integer>();
	 
	    if(n<=1) 
	        return 0;
	    if(n==2)
	        return 1;
	    if(n==3)
	        return 2;
	 
	    primes.add(2);
	    primes.add(3);
	 
	    for(int i=4; i<=n; i++){
	        boolean isPrime = true;
	        for(int p: primes){
	            int m = i%p;
	            if(m==0){
	                isPrime = false;
	                break;
	            }
	        }
	 
	        if(isPrime){
	            primes.add(i);
	        }
	    }
	 
	    return primes.size();
	}
	
	
	public int countPrimes_2(int n) {
		if (n <= 2)
			return 0;
	 
		// init an array to track prime numbers
		boolean[] primes = new boolean[n];
		for (int i = 2; i < n; i++)
			primes[i] = true;
	 
		for (int i = 2; i <= Math.sqrt(n - 1); i++) {
		// or for (int i = 2; i <= n-1; i++) {
			if (primes[i]) {
				for (int j = i + i; j < n; j += i)
					primes[j] = false;
			}
		}
	 
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (primes[i])
				count++;
		}
	 
		return count;
	}
	
	public int countPrimes_3(int n) {  
        if(n <= 2) return 0;  
        List<Integer> primes = new ArrayList<Integer>();  
        primes.add(2);  
        for (int i = 3; i < n; i += 2) {  
            int sqrt_i = (int) Math.sqrt(i);  
            for (int j = 0; i % primes.get(j) != 0; j++) {  
                if (primes.get(j) > sqrt_i) {  
                    primes.add(i);  
                    break;  
                }  
            }  
        }  
        return primes.size();  
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountPrimes slt = new CountPrimes();
		for (int i = 2; i < 100; i++) {
		      System.err.print(slt.countPrimes_2(i)+" ");
		    }
		    System.out.println(slt.countPrimes_2(499979));
		  }
	

}
