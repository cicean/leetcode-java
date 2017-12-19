import java.math.BigInteger;
import java.util.*;

/**
 * 686. Repeated String Match
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

 For example, with A = "abcd" and B = "cdabcdab".

 Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

 Note:
 The length of A and B will be between 1 and 10000.
 */

public class RepeatedStringMatch {

    /**
     * Approach #1: Ad-Hoc [Accepted]

     Intuition

     The question can be summarized as "What is the smallest k for which B is a substring of A * k?" We can just try every k.

     Algorithm

     Imagine we wrote S = A+A+A+.... If B is to be a substring of S, we only need to check whether some S[0:], S[1:], ...,
     S[len(A) - 1:] starts with B, as S is long enough to contain B, and S has period at most len(A).

     Now, suppose q is the least number for which len(B) <= len(A * q). We only need to check whether B is a substring of
     A * q or A * (q+1). If we try k < q, then B has larger length than A * q and therefore can't be a substring.
     When k = q+1, A * k is already big enough to try all positions for B; namely, A[i:i+len(B)] == B for i = 0, 1, ..., len(A) - 1.

     Complexity Analysis

     Time Complexity: O(N*(N+M))O(N∗(N+M)), where M, NM,N are the lengths of strings A, B. We create two strings A * q, A * (q+1) which have length at most O(M+N). When checking whether B is a substring of A, this check takes naively the product of their lengths.

     Space complexity: As justified above, we created strings that used O(M+N)O(M+N) space.
     */
    class Solution {
        public int repeatedStringMatch(String A, String B) {
            int q = 1;
            StringBuilder S = new StringBuilder(A);
            for (; S.length() < B.length(); q++) S.append(A);
            if (S.indexOf(B) >= 0) return q;
            if (S.append(A).indexOf(B) >= 0) return q+1;
            return -1;
        }
    }

    /**
     * Approach #2: Rabin-Karp (Rolling Hash) [Accepted]

     Intuition

     As in Approach #1, we've reduced the problem to deciding whether B is a substring of some A * k. Using the following technique, we can decide whether B is a substring in O(len(A) * k)O(len(A)∗k) time.

     Algorithm

     For strings SS, consider each S[i]S[i] as some integer ASCII code. Then for some prime pp, consider the following function modulo some prime modulus \mathcal{M}M:

     \text{hash}(S) = \sum_{0 \leq i < len(S)} p^i * S[i]hash(S)=∑
     ​0≤i<len(S)Notably, \text{hash}(S[1:] + x) = \frac{(\text{hash}(S) - S[0])}{p} + p^{n-1} xhash(S[1:]+x)=
     ​px. This shows we can get the hash of every substring of A * q in time complexity linear to it's size. (We will also use the fact that p^{-1} = p^{\mathcal{M}-2} \mod \mathcal{M}

     However, hashes may collide haphazardly. To be absolutely sure in theory, we should check the answer in the usual way. The expected number of checks we make is in the order of 1 + \frac{s}{\mathcal{M}}1+
     ​​  where ss is the number of substrings we computed hashes for (assuming the hashes are equally distributed), which is effectively 1.

     */

    class Solution2 {
        public boolean check(int index, String A, String B) {
            for (int i = 0; i < B.length(); i++) {
                if (A.charAt((i + index) % A.length()) != B.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        public int repeatedStringMatch(String A, String B) {
            int q = (B.length() - 1) / A.length() + 1;
            int p = 113, MOD = 1_000_000_007;
            int pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(MOD)).intValue();

            long bHash = 0, power = 1;
            for (int i = 0; i < B.length(); i++) {
                bHash += power * B.codePointAt(i);
                bHash %= MOD;
                power = (power * p) % MOD;
            }

            long aHash = 0; power = 1;
            for (int i = 0; i < B.length(); i++) {
                aHash += power * A.codePointAt(i % A.length());
                aHash %= MOD;
                power = (power * p) % MOD;
            }

            if (aHash == bHash && check(0, A, B)) return q;
            power = (power * pInv) % MOD;

            for (int i = B.length(); i < (q + 1) * A.length(); i++) {
                aHash -= A.codePointAt((i - B.length()) % A.length());
                aHash *= pInv;
                aHash += power * A.codePointAt(i % A.length());
                aHash %= MOD;
                if (aHash == bHash && check(i - B.length() + 1, A, B)) {
                    return i < q * A.length() ? q : q + 1;
                }
            }
            return -1;
        }
    }
}
