import java.util.*;

/**
 * 479. Largest Palindrome Product
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Find the largest palindrome made from the product of two n-digit numbers.

 Since the result could be very large, you should return the largest palindrome mod 1337.

 Example:

 Input: 2

 Output: 987

 Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

 Note:

 The range of n is [1,8].
 */

public class LargestPalindromeProduct {

  /**
   * Hi there! I am sharing my solution. My idea is strightforward. First off, let's pay attention on some facts. Let's consider maximum number with n digits. Of course that is the number consisting only of digit 9 (999..9). Let's denote that number max, and consider max*max.

   It is obvious that any number which is product of two n digit numbers is less than or equal to max*max.
   Maximum possible length of the product is 2*n.
   If we partition palindrome number into two equal halves, then left half must be equals to the reverse of the right half.
   Well, how to find the largest possible palindrome number? Answer: If max*max is palindrome itself, then that is the largest possible palindrome. Otherwise partition it into two equal(by length) halves. If left half is less than, or equals to the right half, then the largest palindrome number is concatenation of left part and reversed left part. Otherwise decrement left part, then find the largest palindrome as concatenation of left and reverse of it. Can we find next palindrome? Answer: yes. It is enough to repeat the latter operation, to obtain next largest palindrome. For example for input n = 2, max*max = 99*99 = 9801. So left half (further just left) is 98 and right half (further just right) is 01. 98>1, it mean largest palindrome is 9779. But it is not answer for our problem. Because this number is not product of two numbers with 2 digits. Well, to get valid palindromic number, we need to traverse all the palindromic numbers and check whether that number is a product of two numbers with n digits. To get the largest palindromic number, we have to approach the palindromic numbers greedily. It means, we need to traverse them from the largest to the smallest. Once we have found a palindrome, which is product of two n digital numbers return that number by mod 1337. That's all with the idea.
   Note the following points in implementation:

   To optimally validate a palindrome number, (i.e whether it is product of two n digital numbers) use greedy approach. In other words, start from largest possible number until the number is greater than its pair. Because it prevents you from considering duplicate pairs. For instance, if a>b and a*b = pal, then no need to consider b*a = pal. It saves huge amount of time. Cope example:
   for(int i = max;i>pro/i;i--){
   if(pro%i == 0 ) {
   return (int)(pro%m);
   }
   }
   Do not forget to assign palindrome number ( pro in my case, which stands for product) long datatype. Because maximum possible palindrome consists of 16 digits, which is greater than Integer.MAX_VALUE.
   To sum up, I think this problem is NOT an EASY one. It would be better to tag it as MEDIUM.

   P.S: Sorry for dirty code.
   */

  public int largestPalindrome(int n) {
    // if input is 1 then max is 9
    if(n == 1){
      return 9;
    }

    // if n = 3 then upperBound = 999 and lowerBound = 99
    int upperBound = (int) Math.pow(10, n) - 1, lowerBound = upperBound / 10;
    long maxNumber = (long) upperBound * (long) upperBound;

    // represents the first half of the maximum assumed palindrom.
    // e.g. if n = 3 then maxNumber = 999 x 999 = 998001 so firstHalf = 998
    int firstHalf = (int)(maxNumber / (long) Math.pow(10, n));

    boolean palindromFound = false;
    long palindrom = 0;

    while (!palindromFound) {
      // creates maximum assumed palindrom
      // e.g. if n = 3 first time the maximum assumed palindrom will be 998 899
      palindrom = createPalindrom(firstHalf);

      // here i and palindrom/i forms the two factor of assumed palindrom
      for (long i = upperBound; upperBound > lowerBound; i--) {
        // if n= 3 none of the factor of palindrom  can be more than 999 or less than square root of assumed palindrom
        if (palindrom / i > maxNumber || i * i < palindrom) {
          break;
        }

        // if two factors found, where both of them are n-digits,
        if (palindrom % i == 0) {
          palindromFound = true;
          break;
        }
      }

      firstHalf--;
    }

    return (int) (palindrom % 1337);
  }

  private long createPalindrom(long num) {
    String str = num + new StringBuilder().append(num).reverse().toString();
    return Long.parseLong(str);
  }

}
