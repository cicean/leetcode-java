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
 * 既然是素数问题还对效率有要求尝试一下筛选法。
 * 先将1挖掉(因为1不是素数)。
 * 用2去除它后面的各个数，把能被2整除的数挖掉，即把2的倍数挖掉。
 * 用3去除它后面的各数，把3的倍数挖掉。
 * 分别用4、5…各数作为除数去除这些数以后的各数。这个过程一直进行到在除数后面的数已全被挖掉为止。例如找1～50的素数，要一直进行到除数为47为止（事实上，可以简化，如果需要找1～n范围内素数表，只需进行到除数为n^2(根号n)，取其整数即可。例如对1～50，只需进行到将50^2作为除数即可。）
 * 即如果一个数是素数那么，后面能整除它的数必然不是素数。为什么合数不用去整除呢？比如说4.因为能被合数整出的话必然被前面的素数整除过了（这里是2）
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
