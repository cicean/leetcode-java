/**
 * 1062. Longest Repeating Substring
 * Medium
 *
 * 176
 *
 * 9
 *
 * Add to List
 *
 * Share
 * Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.
 *
 *
 *
 * Example 1:
 *
 * Input: "abcd"
 * Output: 0
 * Explanation: There is no repeating substring.
 * Example 2:
 *
 * Input: "abbaba"
 * Output: 2
 * Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
 * Example 3:
 *
 * Input: "aabcaabdaab"
 * Output: 3
 * Explanation: The longest repeating substring is "aab", which occurs 3 times.
 * Example 4:
 *
 * Input: "aaaaa"
 * Output: 4
 * Explanation: The longest repeating substring is "aaaa", which occurs twice.
 *
 *
 * Note:
 *
 * The string S consists of only lowercase English letters from 'a' - 'z'.
 * 1 <= S.length <= 1500
 * Accepted
 * 7,323
 * Submissions
 * 13,239
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ngoc_lam
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 *
 * Facebook
 * |
 * 2
 * Generate all substrings in O(N^2) time with hashing.
 * Choose those hashing of strings with the largest length.
 */
public class LongestRepeatingSubstring {
    /**
     * Solution
     * Split into two subtasks
     * Let's focus here on the solutions which are performing better than naive \mathcal{O}(N^2)O(N
     * 2
     *  ) at least in the best/average cases.
     *
     * Here we have "two in one" problem :
     *
     * Perform a search by a substring length in the interval from 1 to N.
     *
     * Check if there is a duplicate substring of a given length L.
     *
     * Subtask one : Binary search by a substring length
     *
     * A naive solution would be to check all possible string length one by one starting from N - 1: if there is a duplicate substring of length N - 1, then of length N - 2, etc. Note that if there is a duplicate substring of length k, that means that there is a duplicate substring of length k - 1. Hence one could use a binary search by string length here, and have the first problem solved in \mathcal{O}(\log N)O(logN) time.
     *
     * pic
     *
     * The binary search code is quite standard and we will use it here for all approaches to focus on much more interesting subtask number two.
     *
     *
     * Subtask two : Check if there is a duplicate substring of length L
     *
     * We will discuss here three different ideas how to proceed. They are all based on sliding window + hashset, though their performance and space consumption are quite different.
     *
     * Linear-time slice + hashset of already seen strings. \mathcal{O}((N - L) L)O((N−L)L) time complexity and huge space consumption in the case of large strings.
     *
     * Linear-time slice + hashset of hashes of already seen strings. \mathcal{O}((N - L) L)O((N−L)L) time complexity and moderate space consumption even in the case of large strings.
     *
     * Rabin-Karp = constant-time slice + hashset of hashes of already seen strings. Hashes are computed with the rolling hash algorithm. \mathcal{O}(N - L)O(N−L) time complexity and moderate space consumption even in the case of large strings.
     *
     * pic
     *
     *
     *
     * Approach 1: Binary Search + Hashset of Already Seen Strings
     * The idea is straightforward :
     *
     * Move a sliding window of length L along the string of length N.
     *
     * Check if the string in the sliding window is in the hashset of already seen strings.
     *
     * If yes, the duplicate substring is right here.
     *
     * If not, save the string in the sliding window in the hashset.
     *
     * Current
     * 1 / 10
     * Obvious drawback of this approach is a huge memory consumption in the case of large strings.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N \log N)O(NlogN) in the average case and \mathcal{O}(N^2)O(N
     * 2
     *  ) in the worst case. One needs \mathcal{O}((N - L)L)O((N−L)L) for one duplicate check, and one does up to \mathcal{O}(\log N)O(logN) checks. Together that results in \mathcal{O}(\sum\limits_{L}{(N - L)L})O(
     * L
     * ∑
     * ​
     *  (N−L)L), i.e. in \mathcal{O}(N \log N)O(NlogN) in the average case and in \mathcal{O}(N^2)O(N
     * 2
     *  ) in the worst case of L close to N/2N/2.
     *
     * Space complexity : \mathcal{O}(N^2)O(N
     * 2
     *  ) to keep the hashset.
     *
     */
    class Solution {
        /*
          Search a substring of given length
          that occurs at least 2 times.
          Return start position if the substring exits and -1 otherwise.
          */
        public int search(int L, int n, String S) {
            HashSet<String> seen = new HashSet();
            String tmp;
            for(int start = 0; start < n - L + 1; ++start) {
                tmp = S.substring(start, start + L);
                if (seen.contains(tmp)) return start;
                seen.add(tmp);
            }
            return -1;
        }

        public int longestRepeatingSubstring(String S) {
            int n = S.length();
            // binary search, L = repeating string length
            int left = 1, right = n;
            int L;
            while (left <= right) {
                L = left + (right - left) / 2;
                if (search(L, n, S) != -1) left = L + 1;
                else right = L - 1;
            }

            return left - 1;
        }
    }
     /** Approach 2: Binary Search + Hashset of Hashes of Already Seen Strings
     * To reduce the memory consumption by the hashset structure, one could store not the full strings, but their hashes.
     *
     * The drawback of this approach is a time performance, which is still not always linear.
     *
     * pic
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N \log N)O(NlogN) in the average case and \mathcal{O}(N^2)O(N
     * 2
     *  ) in the worst case. One needs \mathcal{O}((N - L)L)O((N−L)L) for one duplicate check, and one does up to \mathcal{O}(\log N)O(logN) checks. Together that results in \mathcal{O}(\sum\limits_{L}{(N - L)L})O(
     * L
     * ∑
     * ​
     *  (N−L)L), i.e. in \mathcal{O}(N \log N)O(NlogN) in the average case and in \mathcal{O}(N^2)O(N
     * 2
     *  ) in the worst case of L close to N/2N/2.
     *
     * Space complexity : \mathcal{O}(N)O(N) to keep the hashset.
     *
     *
     */
     class Solution {
         /*
           Search a substring of given length
           that occurs at least 2 times.
           Return start position if the substring exits and -1 otherwise.
           */
         public int search(int L, int n, String S) {
             HashSet<Integer> seen = new HashSet();
             String tmp;
             int h;
             for(int start = 0; start < n - L + 1; ++start) {
                 tmp = S.substring(start, start + L);
                 h = tmp.hashCode();
                 if (seen.contains(h)) return start;
                 seen.add(h);
             }
             return -1;
         }

         public int longestRepeatingSubstring(String S) {
             int n = S.length();
             // binary search, L = repeating string length
             int left = 1, right = n;
             int L;
             while (left <= right) {
                 L = left + (right - left) / 2;
                 if (search(L, n, S) != -1) left = L + 1;
                 else right = L - 1;
             }

             return left - 1;
         }
     }
     /** Approach 3: Binary Search + Rabin-Karp
     * Rabin-Karp algorithm is used to perform a multiple pattern search in a linear time and with a moderate memory consumption suitable for the large strings.
     *
     * The linear time implementation of this idea is a bit tricky because of two technical problems:
     *
     * How to implement a string slice in a constant time?
     *
     * Hashset memory consumption could be huge for very long strings. One could keep the string hash instead of string itself but hash generation costs \mathcal{O}(L)O(L) for the string of length L, and the complexity of algorithm would be \mathcal{O}((N - L)L)O((N−L)L), N - L for the slice and L for the hash generation. One has to think how to generate hash in a constant time here.
     *
     * Let's now address these problems.
     *
     * String slice in a constant time
     *
     * That's a very language-dependent problem. For the moment for Java and Python there is no straightforward solution, and to move sliding window in a constant time one has to convert string to another data structure.
     *
     * The simplest solution both for Java and Python is to convert string to integer array of ascii-values.
     *
     * Rolling hash : hash generation in a constant time
     *
     * To generate hash of array of length L, one needs \mathcal{O}(L)O(L) time.
     *
     * How to have constant time of hash generation? Use the advantage of slice: only one integer in, and only one - out.
     *
     * That's the idea of rolling hash. Here we'll implement the simplest one, polynomial rolling hash. Beware that's polynomial rolling hash is NOT the Rabin fingerprint.
     *
     * Since one deals here with lowercase English letters, all values in the integer array are between 0 and 25 : arr[i] = (int)S.charAt(i) - (int)'a'.
     * So one could consider string abcd -> [0, 1, 2, 3] as a number in a numeral system with the base 26. Hence abcd -> [0, 1, 2, 3] could be hashed as
     *
     * h_0 = 0 \times 26^3 + 1 \times 26^2 + 2 \times 26^1 + 3 \times 26^0h
     * 0
     * ​
     *  =0×26
     * 3
     *  +1×26
     * 2
     *  +2×26
     * 1
     *  +3×26
     * 0
     *
     *
     * Let's write the same formula in a generalised way, where c_ic
     * i
     * ​
     *   is an integer array element and a = 26a=26 is a system base.
     *
     * h_0 = c_0 a^{L - 1} + c_1 a^{L - 2} + ... + c_i a^{L - 1 - i} + ... + c_{L - 1} a^1 + c_L a^0h
     * 0
     * ​
     *  =c
     * 0
     * ​
     *  a
     * L−1
     *  +c
     * 1
     * ​
     *  a
     * L−2
     *  +...+c
     * i
     * ​
     *  a
     * L−1−i
     *  +...+c
     * L−1
     * ​
     *  a
     * 1
     *  +c
     * L
     * ​
     *  a
     * 0
     *
     *
     * h_0 = \sum_{i = 0}^{L - 1}{c_i a^{L - 1 - i}}h
     * 0
     * ​
     *  =∑
     * i=0
     * L−1
     * ​
     *  c
     * i
     * ​
     *  a
     * L−1−i
     *
     *
     * Now let's consider the slice abcd -> bcde. For int arrays that means [0, 1, 2, 3] -> [1, 2, 3, 4], to remove number 0 and to add number 4.
     *
     * h_1 = (h_0 - 0 \times 26^3) \times 26 + 4 \times 26^0h
     * 1
     * ​
     *  =(h
     * 0
     * ​
     *  −0×26
     * 3
     *  )×26+4×26
     * 0
     *
     *
     * In a generalised way
     *
     * h_1 = (h_0 a - c_0 a^L) + c_{L + 1}h
     * 1
     * ​
     *  =(h
     * 0
     * ​
     *  a−c
     * 0
     * ​
     *  a
     * L
     *  )+c
     * L+1
     * ​
     *
     *
     * Now hash regeneration is perfect and fits in a constant time. There is one more issue to address: possible overflow problem.
     *
     * How to avoid overflow
     *
     * a^La
     * L
     *   could be a large number and hence the idea is to set limits to avoid the overflow. To set limits means to limit a hash by a given number called modulus and use everywhere not hash itself but h % modulus.
     *
     * It's quite obvious that modulus should be large enough, but how large? Here one could read more about the topic, for the problem here 2^{24}2
     * 24
     *   is enough.
     *
     * In a real life, when not all testcases are known in advance, one has to check if the strings with equal hashes are truly equal. Such false-positive strings could happen because of a modulus which is too small and strings which are too long. That leads to Rabin-Karp time complexity \mathcal{O}(NL)O(NL) in the worst case then almost all strings are false-positive. Here it's not the case because all testcases are known and one could adjust the modulus.
     *
     * Another one overflow issue here is purely Java related. While in Python the hash regeneration goes perfectly fine, in Java the same thing is better to rewrite to avoid long overflow.
     *
     *
     * Rabin-Karp algorithm
     *
     * search(L) :
     *
     * Compute the hash of substring S.substring(0, L) and initiate the hashset of already seen substring with this value.
     *
     * Iterate over the start position of substring : from 1 to N - LN−L.
     *
     * Compute rolling hash based on the previous hash value.
     *
     * Return start position if the hash is in the hashset, because that means one met the duplicate.
     *
     * Otherwise, add hash in the hashset.
     *
     * Return -1, that means there is no duplicate string of length L.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N \log N)O(NlogN). \mathcal{O}(\log N)O(logN) for the binary search and \mathcal{O}(N)O(N) for Rabin-Karp algorithm.
     * Space complexity : \mathcal{O}(N)O(N) to keep the hashset.
     */

     class Solution {
         /*
         Rabin-Karp with polynomial rolling hash.
             Search a substring of given length
             that occurs at least 2 times.
             Return start position if the substring exits and -1 otherwise.
             */
         public int search(int L, int a, long modulus, int n, int[] nums) {
             // compute the hash of string S[:L]
             long h = 0;
             for(int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

             // already seen hashes of strings of length L
             HashSet<Long> seen = new HashSet();
             seen.add(h);
             // const value to be used often : a**L % modulus
             long aL = 1;
             for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

             for(int start = 1; start < n - L + 1; ++start) {
                 // compute rolling hash in O(1) time
                 h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
                 h = (h + nums[start + L - 1]) % modulus;
                 if (seen.contains(h)) return start;
                 seen.add(h);
             }
             return -1;
         }

         public int longestRepeatingSubstring(String S) {
             int n = S.length();
             // convert string to array of integers
             // to implement constant time slice
             int[] nums = new int[n];
             for(int i = 0; i < n; ++i) nums[i] = (int)S.charAt(i) - (int)'a';
             // base value for the rolling hash function
             int a = 26;
             // modulus value for the rolling hash function to avoid overflow
             long modulus = (long)Math.pow(2, 24);

             // binary search, L = repeating string length
             int left = 1, right = n;
             int L;
             while (left <= right) {
                 L = left + (right - left) / 2;
                 if (search(L, a, modulus, n, nums) != -1) left = L + 1;
                 else right = L - 1;
             }

             return left - 1;
         }
     }
}
