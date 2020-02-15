import java.util.*;

/**
 * 767. Reorganize String
 * Medium
 *
 * 941
 *
 * 50
 *
 * Add to List
 *
 * Share
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * S will consist of lowercase letters and have length in range [1, 500].
 *
 *
 * Accepted
 * 50,769
 * Submissions
 * 109,857
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ti9yowl
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 11
 *
 * Amazon
 * |
 * 9
 *
 * Google
 * |
 * 5
 *
 * Microsoft
 * |
 * 3
 * Rearrange String k Distance Apart
 * Hard
 * Task Scheduler
 * Medium
 * Alternate placing the most common letters.
 */

public class ReorganizeString {
    /**
     * Approach #1: Sort by Count [Accepted]
     * Intuition
     *
     * If we should make no two 'a's adjacent, it is natural to write "aXaXaXa..." where "X" is some letter. For now, let's assume that the task is possible (ie. the answer is not "".)
     *
     * Let's sort the string S, so all of the same kind of letter occur in continuous blocks. Then when writing in the following interleaving pattern, like S[3], S[0], S[4], S[1], S[5], S[2], adjacent letters never touch. (The specific interleaving pattern is that we start writing at index 1 and step by 2; then start from index 0 and step by 2.)
     *
     * The exception to this rule is if N is odd, and then when interleaving like S[2], S[0], S[3], S[1], S[4], we might fail incorrectly if there is a block of the same 3 letters starting at S[0] or S[1]. To prevent failing erroneously in this case, we need to make sure that the most common letters all occur at the end.
     *
     * Finally, it is easy to see that if N is the length of the string, and the count of some letter is greater than (N+1) / 2, the task is impossible.
     *
     * Algorithm
     *
     * Find the count of each character, and use it to sort the string by count.
     *
     * If at some point the number of occurrences of some character is greater than (N + 1) / 2, the task is impossible.
     *
     * Otherwise, interleave the characters in the order described above.
     * Complexity Analysis
     *
     * Time Complexity: O(\mathcal{A}(N + \log{\mathcal{A}}))O(A(N+logA)), where NN is the length of SS, and \mathcal{A}A is the size of the alphabet. In Java, our implementation is O(N + \mathcal{A} \log {\mathcal{A}})O(N+AlogA). If \mathcal{A}A is fixed, this complexity is O(N)O(N).
     *
     * Space Complexity: O(N)O(N). In Java, our implementation is O(N + \mathcal{A})O(N+A).
     */

    class Solution {
        public String reorganizeString(String S) {
            int N = S.length();
            int[] counts = new int[26];
            for (char c: S.toCharArray()) counts[c-'a'] += 100;
            for (int i = 0; i < 26; ++i) counts[i] += i;
            //Encoded counts[i] = 100*(actual count) + (i)
            Arrays.sort(counts);

            char[] ans = new char[N];
            int t = 1;
            for (int code: counts) {
                int ct = code / 100;
                char ch = (char) ('a' + (code % 100));
                if (ct > (N+1) / 2) return "";
                for (int i = 0; i < ct; ++i) {
                    if (t >= N) t = 0;
                    ans[t] = ch;
                    t += 2;
                }
            }

            return String.valueOf(ans);
        }
    }

    /**
     * Approach #2: Greedy with Heap [Accepted]
     * Intuition
     *
     * One consequence of the reasoning in Approach #1, is that a greedy approach that tries to write the most common letter (that isn't the same as the previous letter written) will work.
     *
     * The reason is that the task is only impossible if the frequency of a letter exceeds (N+1) / 2. Writing the most common letter followed by the second most common letter keeps this invariant.
     *
     * A heap is a natural structure to repeatedly return the current top 2 letters with the largest remaining counts.
     *
     * Approach
     *
     * We store a heap of (count, letter). [In Python, our implementation stores negative counts.]
     *
     * We pop the top two elements from the heap (representing different letters with positive remaining count), and then write the most frequent one that isn't the same as the most recent one written. After, we push the correct counts back onto the heap.
     *
     * Actually, we don't even need to keep track of the most recent one written. If it is possible to organize the string, the letter written second can never be written first in the very next writing.
     *
     * At the end, we might have one element still on the heap, which must have a count of one. If we do, we'll add that to the answer too.
     *
     * Proof of Invariant
     *
     * The invariant mentioned in the [Intuition] section seems true when playing with it, but here is a proof. Let C_iC
     * i
     * ​
     *   be the count of each letter yet to be written, and NN be the number of letters left to write. We want to show this procedure maintains the invariant 2 * \max\limits_i(C_i) \leq N+12∗
     * i
     * max
     * ​
     *  (C
     * i
     * ​
     *  )≤N+1.
     *
     * Say C'_iC
     * i
     * ′
     * ​
     *   are the counts after one writing step.
     *
     * If \max(C_i) > \text{3rdmax}(C_i)max(C
     * i
     * ​
     *  )>3rdmax(C
     * i
     * ​
     *  ), then \max(C'_i) \leq \max(C_i) - 1max(C
     * i
     * ′
     * ​
     *  )≤max(C
     * i
     * ​
     *  )−1, and so 2\max(C'_i) \leq 2\max(C_i) - 2 \leq N-12max(C
     * i
     * ′
     * ​
     *  )≤2max(C
     * i
     * ​
     *  )−2≤N−1 as desired.
     *
     * If M = \max(C_i) = \text{3rdmax}(C_i)M=max(C
     * i
     * ​
     *  )=3rdmax(C
     * i
     * ​
     *  ), then 3M \leq N3M≤N. Also, because M \geq 1M≥1, N \geq 3N≥3. Then, 2M \leq \frac{2N}{3} \leq N-12M≤
     * 3
     * 2N
     * ​
     *  ≤N−1 as desired.
     *
     * This completes the proof of this invariant.
     *
     */

    class Solution_2 {
        public String reorganizeString(String S) {
            int N = S.length();
            int[] count = new int[26];
            for (char c: S.toCharArray()) count[c-'a']++;
            PriorityQueue<MultiChar> pq = new PriorityQueue<MultiChar>((a, b) ->
                    a.count == b.count ? a.letter - b.letter : b.count - a.count);

            for (int i = 0; i < 26; ++i) if (count[i] > 0) {
                if (count[i] > (N + 1) / 2) return "";
                pq.add(new MultiChar(count[i], (char) ('a' + i)));
            }

            StringBuilder ans = new StringBuilder();
            while (pq.size() >= 2) {
                MultiChar mc1 = pq.poll();
                MultiChar mc2 = pq.poll();
            /*This code turns out to be superfluous, but explains what is happening
            if (ans.length() == 0 || mc1.letter != ans.charAt(ans.length() - 1)) {
                ans.append(mc1.letter);
                ans.append(mc2.letter);
            } else {
                ans.append(mc2.letter);
                ans.append(mc1.letter);
            }*/
                ans.append(mc1.letter);
                ans.append(mc2.letter);
                if (--mc1.count > 0) pq.add(mc1);
                if (--mc2.count > 0) pq.add(mc2);
            }

            if (pq.size() > 0) ans.append(pq.poll().letter);
            return ans.toString();
        }
    }

    class MultiChar {
        int count;
        char letter;
        MultiChar(int ct, char ch) {
            count = ct;
            letter = ch;
        }
    }

    class Solution_no_sort {
        public String reorganizeString(String S) {
            int[] hash = new int[26];
            for (int i = 0; i < S.length(); i++) {
                hash[S.charAt(i) - 'a']++;
            }
            int max = 0, letter = 0;
            for (int i = 0; i < hash.length; i++) {
                if (hash[i] > max) {
                    max = hash[i];
                    letter = i;
                }
            }
            if (max > (S.length() + 1) / 2) {
                return "";
            }
            char[] res = new char[S.length()];
            int idx = 0;
            while (hash[letter] > 0) {
                res[idx] = (char) (letter + 'a');
                idx += 2;
                hash[letter]--;
            }
            for (int i = 0; i < hash.length; i++) {
                while (hash[i] > 0) {
                    if (idx >= res.length) {
                        idx = 1;
                    }
                    res[idx] = (char) (i + 'a');
                    idx += 2;
                    hash[i]--;
                }
            }
            return String.valueOf(res);
        }
    }

}
