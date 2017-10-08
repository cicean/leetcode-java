package VMware;

/**
 * Given two numbers n and k, print k-th prime factor among all prime factors of n. For example, if the input number is 15 and k is 2, then output should be “5”. And if the k is 3, then output should be “-1” (there are less than k prime factors).

 Examples:

 Input : n = 225, k = 2
 Output : 3
 Prime factors are 3 3 5 5. Second
 prime factor is 3.

 Input : n = 81, k = 5
 Output : -1
 Prime factors are 3 3 3 3

 */

public class KthPrimeFactorOfaGivenNumber {

  private long kPrimeFactor(long n, long k) {

    while (n % 2 == 0) {
      k--;
      n = n / 2;
      if (k == 0) {
        return 2;
      }
    }

    for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
      while (n % i == 0) {
        if (k == 1) {
          return i;
        }
        k--;
        n = n / i;
      }
    }

    if (n > 2 && k == 1) {
      return n;
    }

    return -1;
  }


}
