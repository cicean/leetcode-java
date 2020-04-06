/**
 * 1044. Longest Duplicate Substring
 * Hard
 *
 * 191
 *
 * 116
 *
 * Add to List
 *
 * Share
 * Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)
 *
 * Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)
 *
 *
 *
 * Example 1:
 *
 * Input: "banana"
 * Output: "ana"
 * Example 2:
 *
 * Input: "abcd"
 * Output: ""
 *
 *
 * Note:
 *
 * 2 <= S.length <= 10^5
 * S consists of lowercase English letters.
 * Accepted
 * 6,796
 * Submissions
 * 26,902
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * aoyunhui1002
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 7
 *
 * Oracle
 * |
 * 3
 *
 * Microsoft
 * |
 * 3
 * Binary search for the length of the answer. (If there's an answer of length 10, then there are answers of length 9, 8, 7, ...)
 * To check whether an answer of length K exists, we can use Rabin-Karp 's algorithm.
 */
public class LongestDuplicateSubstring {

    /**
     * Solution
     * Approach 1: Binary Search + Rabin-Karp
     * String Searching Algorithms
     *
     * The problem is a follow-up of Longest Repeating Substring, and typically used to check if you're comfortable with string searching algortihms.
     *
     * Best algorithms have a linear execution time in average. The most popular ones are Aho-Corasick, KMP and Rabin-Karp: Aho-Corasick is used by fgrep, KMP is used for chinese string searching, and Rabin-Karp is used for plagiarism detection and in bioinformatics to look for similarities in two or more proteins.
     *
     * The first two are optimised for a single pattern search, and Rabin-Karp for a multiple pattern search, that is exactly the case here.
     *
     * Split into two subtasks
     *
     * Here we have "two in one" problem :
     *
     * Perform a search by a substring length in the interval from 1 to N.
     *
     * Check if there is a duplicate substring of a given length L.
     *
     * Subtask one : Binary search
     *
     * A naive solution would be to check all possible string length one by one starting from N - 1: if there is a duplicate substring of length N - 1, then of length N - 2, etc. Note that if there is a duplicate substring of length k, that means that there is a duplicate substring of length k - 1. Hence one could use a binary search by string length here, and have the first problem solved in \mathcal{O}(\log N)O(logN) time.
     *
     * fig
     *
     * Subtask two : Rabin-Karp
     *
     * Subtask two, to check if there is duplicate substring of a given length, is a multiple pattern search. Let's use Rabin-Karp algorithm to solve it in a linear time.
     *
     * The idea is very simple :
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
     * Python is already providing memoryview, which is known to be surprisingly slow, and there are a lot of discussion about strview.
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
     * It's quite obvious that modulus should be large enough, but how large? Here one could read more about the topic, for the problem here the standard int overflow in 2^{32}2
     * 32
     *   is enough.
     *
     * In a real life, when not all testcases are known in advance, one has to check if the strings with equal hashes are truly equal. Such false-positive strings could happen because of a modulus which is too small and strings which are too long. That leads to Rabin-Karp time complexity \mathcal{O}(NL)O(NL) in the worst case then almost all strings are false-positive. Here it's not the case because all testcases are known and one could adjust the modulus.
     *
     * Another one overflow issue here is purely Java related. While in Python the hash regeneration goes perfectly fine, in Java the same thing is better to rewrite to avoid long overflow. Check here the nice explanation by @hqt.
     *
     *
     * Binary search algorithm
     *
     * Use binary search by a substring length to check lengths from 1 to N left = 1, right = N. While left != right:
     *
     * L = left + (right - left) / 2
     *
     * If search(L) != -1 (i.e. there is a duplicate substring), left = L + 1
     *
     * Otherwise (no duplicate substring), right = L.
     *
     * Return duplicate string of length left - 1, or an empty string if there is no such a string.
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
     * Return start position if the hash is in the hashset, because that means a duplicate string.
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
            for (int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

            // already seen hashes of strings of length L
            HashSet<Long> seen = new HashSet();
            seen.add(h);
            // const value to be used often : a**L % modulus
            long aL = 1;
            for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

            for (int start = 1; start < n - L + 1; ++start) {
                // compute rolling hash in O(1) time
                h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
                h = (h + nums[start + L - 1]) % modulus;
                if (seen.contains(h)) return start;
                seen.add(h);
            }
            return -1;
        }

        public String longestDupSubstring(String S) {
            int n = S.length();
            // convert string to array of integers
            // to implement constant time slice
            int[] nums = new int[n];
            for (int i = 0; i < n; ++i) nums[i] = (int) S.charAt(i) - (int) 'a';
            // base value for the rolling hash function
            int a = 26;
            // modulus value for the rolling hash function to avoid overflow
            long modulus = (long) Math.pow(2, 32);

            // binary search, L = repeating string length
            int left = 1, right = n;
            int L;
            while (left <= right) {
                L = left + (right - left) / 2;
                if (search(L, a, modulus, n, nums) != -1) left = L + 1;
                else right = L - 1;
            }

            int start = search(left - 1, a, modulus, n, nums);
            return S.substring(start, start + left - 1);
        }
    }

    /**
     * dp +
     */
    class Solution {
        private class BSTNode {
            int low, high;
            BSTNode left, right;
            BSTNode(int low, int high) {
                this.low = low;
                this.high = high;
            }
        }

        public String longestDupSubstring(String S) {
            char[] s = S.toCharArray();
            int len = s.length;
            s = Arrays.copyOf(s, len + 1);
            // end char
            s[len] = '*';

            int[] dp = new int[len], chs = new int[26];
            for (int i = 0; i < len; i++) {
                dp[i] = i;
                ++chs[s[i] - 'a'];
            }

            for (int i = 0; i < 26; i++) {
                if (chs[i] == len) {
                    return S.substring(1);
                }
            }

            BSTNode[] trees = new BSTNode[len];
            int[] r = helper(s, dp, 0, len, trees);
            return String.valueOf(s, r[0], r[1]);
        }

        private int[] helper(char[] s, int[] dp, int l, int r, BSTNode[] trees) {
            int pos = 0, max = -1;
            while (l < r) {
                int nl = partitionAndMoveForward(s, dp, l, r);
                if (nl - l == 1) {
                    if (0 > max) {
                        pos = dp[l] - 1;
                        max = 0;
                    }
                } else if (nl - l == 2) {
                    int count = 1, a = dp[l], b = dp[l + 1];
                    if (s[a] == s[b]) {
                        int low = Math.min(a, b), high = Math.max(a, b);
                        count = searchAndUpdate(s, low - 1, high - low, trees);
                    }
                    if (count > max) {
                        pos = dp[l] - 1;
                        max = count;
                    }
                } else {
                    int[] m = helper(s, dp, l, nl, trees);
                    if (m[1] + 1 > max) {
                        pos = m[0] - 1;
                        max = m[1] + 1;
                    }
                }
                l = nl;
            }
            return new int[]{pos, max};
        }

        private int searchAndUpdate(char[] s, int v, int gap, BSTNode[] trees) {
            BSTNode p = trees[gap];
            while (p != null) {
                if (v < p.high && v >= p.low) {
                    return p.high - v;
                }
                if (v >= p.high && p.right != null) {
                    p = p.right;
                    continue;
                } else if (v < p.low && p.left != null) {
                    p = p.left;
                    continue;
                }
                break;
            }

            int len = s.length, a = v, b = v + gap;
            while (b < len && s[a] == s[b]) {
                a += 1;
                b += 1;
            }
            int high = a;
            a = v;
            b = v + gap;
            while (a >= 0 && s[a] == s[b]) {
                a -= 1;
                b -= 1;
            }
            int low = a + 1;

            if (p == null) {
                trees[gap] = new BSTNode(low, high);
            } else if (v >= p.high) {
                p.right = new BSTNode(low, high);

            } else {
                p.left = new BSTNode(low, high);
            }

            return high - v;
        }

        private int partitionAndMoveForward(char[] s, int[] dp, int l, int r) {
            char target = s[dp[l]];
            while (l < r) {
                if (s[dp[l]] == target) {
                    ++dp[l];
                    ++l;
                    continue;
                }

                if (s[dp[r - 1]] != target) {
                    --r;
                    continue;
                }

                int tmp = dp[l];
                dp[l] = dp[r - 1] + 1;
                dp[r - 1] = tmp;
                l += 1;
                r -= 1;
            }
            return l;
        }
    }
}
