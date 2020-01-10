/**
 * 866. Prime Palindrome
 * Medium
 *
 * 114
 *
 * 324
 *
 * Favorite
 *
 * Share
 * Find the smallest prime palindrome greater than or equal to N.
 *
 * Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
 *
 * For example, 2,3,5,7,11 and 13 are primes.
 *
 * Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
 *
 * For example, 12321 is a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: 6
 * Output: 7
 * Example 2:
 *
 * Input: 8
 * Output: 11
 * Example 3:
 *
 * Input: 13
 * Output: 101
 *
 *
 * Note:
 *
 * 1 <= N <= 10^8
 * The answer is guaranteed to exist and be less than 2 * 10^8.
 * Accepted
 * 10,209
 * Submissions
 * 48,878
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * mirosuaf
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 9
 */
public class PrimePalindrome {

    class Solution {
        public int primePalindrome(int N) {
            for (int L = 1; L <= 5; ++L) {
                //Check for odd-length palindromes
                for (int root = (int)Math.pow(10, L - 1); root < (int)Math.pow(10, L); ++root) {
                    StringBuilder sb = new StringBuilder(Integer.toString(root));
                    for (int k = L-2; k >= 0; --k)
                        sb.append(sb.charAt(k));
                    int x = Integer.valueOf(sb.toString());
                    if (x >= N && isPrime(x))
                        return x;
                    //If we didn't check for even-length palindromes:
                    //return N <= 11 ? min(x, 11) : x
                }

                //Check for even-length palindromes
                for (int root = (int)Math.pow(10, L - 1); root < (int)Math.pow(10, L); ++root) {
                    StringBuilder sb = new StringBuilder(Integer.toString(root));
                    for (int k = L-1; k >= 0; --k)
                        sb.append(sb.charAt(k));
                    int x = Integer.valueOf(sb.toString());
                    if (x >= N && isPrime(x))
                        return x;
                }
            }

            throw null;
        }

        public boolean isPrime(int N) {
            if (N < 2) return false;
            int R = (int) Math.sqrt(N);
            for (int d = 2; d <= R; ++d)
                if (N % d == 0) return false;
            return true;
        }
    }

    class Solution_2 {
        public int primePalindrome(int N) {
            if (8 <= N && N <= 11) return 11;
            for (int x = 1; x < 100000; x++) {
                String s = Integer.toString(x), r = new StringBuilder(s).reverse().toString();
                int y = Integer.parseInt(s + r.substring(1));
                if (y >= N && isPrime(y)) return y;
            }
            return -1;
        }

        public Boolean isPrime(int x) {
            if (x < 2 || x % 2 == 0) return x == 2;
            for (int i = 3; i * i <= x; i += 2)
                if (x % i == 0) return false;
            return true;
        }
    }
}
