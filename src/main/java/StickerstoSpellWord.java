import java.util.*;

/**
 * 691. Stickers to Spell Word
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 We are given N different types of stickers. Each sticker has a lowercase English word on it.

 You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.

 You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

 What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.

 Example 1:

 Input:

 ["with", "example", "science"], "thehat"
 Output:

 3
 Explanation:

 We can use 2 "with" stickers, and 1 "example" sticker.
 After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 Also, this is the minimum number of stickers necessary to form the target string.
 Example 2:

 Input:

 ["notice", "possible"], "basicbasic"
 Output:

 -1
 Explanation:

 We can't form the target "basicbasic" from cutting letters from the given stickers.
 Note:

 stickers has length in the range [1, 50].
 stickers consists of lowercase English words (without apostrophes).
 target has length in the range [1, 15], and consists of lowercase English letters.
 In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
 The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
 */

public class StickerstoSpellWord {

  /**
   * Approach 1: Optimized Exhaustive Search



   Intuition

   A natural answer is to exhaustively search combinations of stickers. Because the data is randomized,
   there are many heuristics available to us that will make this faster.

   For all stickers, we can ignore any letters that are not in the target word.

   When our candidate answer won't be smaller than an answer we have already found, we can stop searching this path.

   We should try to have our exhaustive search bound the answer as soon as possible, so the effect
   described in the above point happens more often.

   When a sticker dominates another, we shouldn't include the dominated sticker in our sticker collection.
   [Here, we say a sticker A dominates B if A.count(letter) >= B.count(letter) for all letters.]



   Algorithm

   Firstly, for each sticker, let's create a count of that sticker (a mapping letter -> sticker.
   count(letter)) that does not consider letters not in the target word. Let A be an array of these
   counts. Also, let's create t_count, a count of our target word.

   Secondly, let's remove dominated stickers. Because dominance is a transitive relation,
   we only need to check if a sticker is not dominated by any other sticker once -
   the ones that aren't dominated are included in our collection.

   We are now ready to begin our exhaustive search. A call to search(ans) denotes that
   we want to decide the minimum number of stickers we can used in A to satisfy the target count t_count.
   ans will store the currently formed answer, and best will store the current best answer.

   If our current answer can't beat our current best answer, we should stop searching.
   Also, if there are no stickers left and our target is satisfied, we should update our answer.

   Otherwise, we want to know the maximum number of this sticker we can use. For example,
   if this sticker is 'abb' and our target is 'aaabbbbccccc', then we could use a maximum of 3 stickers.
   This is the maximum of math.ceil(target.count(letter) / sticker.count(letter)), taken over all letters in sticker.
   Let's call this quantity used.

   After, for the sticker we are currently considering, we try to use used of them, then used - 1,
   used - 2 and so on. The reason we do it in this order is so that we can arrive at a value for
   best more quickly, which will stop other branches of our exhaustive search from continuing.

   The Python version of this solution showcases using collections.Counter as a way to simplify
   some code sections, whereas the Java solution sticks to arrays.

   Complexity Analysis

   Time Complexity: Let NN be the number of stickers, and TT be the number of letters in the target word.
   A bound for time complexity is O(N^{T+1} T^2)
   : for each sticker, we'll have to try using it up to T+1T+1 times, and updating our target count costs O(T)O(T),
   which we do up to TT times. Alternatively, since the answer is bounded at TT,
   we can prove that we can only search up to \binom{N+T-1}{T-1}(times. This would be O(\binom{N+T-1}{T-1} T^2)

   Space Complexity: O(N+T)O(N+T), to store stickersCount, targetCount, and handle the recursive callstack when calling search.
   */

  class Solution {
    int best;
    int[][] stickersCount;
    int[] targetCount;

    public void search(int ans, int row) {
      if (ans >= best) return;
      if (row == stickersCount.length) {
        for (int c: targetCount) if (c > 0) return;
        best = ans;
        return;
      }

      int used = 0;
      for (int i = 0; i < stickersCount[row].length; i++) {
        if (targetCount[i] > 0 && stickersCount[row][i] > 0) {
          used = Math.max(used, (targetCount[i] - 1) / stickersCount[row][i] + 1);
        }
      }
      for (int i = 0; i < stickersCount[row].length; i++) {
        targetCount[i] -= used * stickersCount[row][i];
      }

      search(ans + used, row + 1);
      while (used > 0) {
        for (int i = 0; i < stickersCount[row].length; i++) {
          targetCount[i] += stickersCount[row][i];
        }
        used--;
        search(ans + used, row + 1);
      }
    }

    public int minStickers(String[] stickers, String target) {
      int[] targetNaiveCount = new int[26];
      for (char c: target.toCharArray()) targetNaiveCount[c - 'a']++;

      int[] index = new int[26];
      int t = 0;
      for (int i = 0; i < 26; i++) {
        if (targetNaiveCount[i] > 0) {
          index[i] = t++;
        } else {
          index[i] = -1;
        }
      }

      targetCount = new int[t];
      t = 0;
      for (int c: targetNaiveCount) if (c > 0) {
        targetCount[t++] = c;
      }

      stickersCount = new int[stickers.length][t];
      for (int i = 0; i < stickers.length; i++) {
        for (char c: stickers[i].toCharArray()) {
          int j = index[c - 'a'];
          if (j >= 0) stickersCount[i][j]++;
        }
      }

      int anchor = 0;
      for (int i = 0; i < stickers.length; i++) {
        for (int j = anchor; j < stickers.length; j++) if (j != i) {
          boolean dominated = true;
          for (int k = 0; k < t; k++) {
            if (stickersCount[i][k] > stickersCount[j][k]) {
              dominated = false;
              break;
            }
          }

          if (dominated) {
            int[] tmp = stickersCount[i];
            stickersCount[i] = stickersCount[anchor];
            stickersCount[anchor++] = tmp;
            break;
          }
        }
      }

      best = target.length() + 1;
      search(0, anchor);
      return best <= target.length() ? best : -1;
    }
  }

  /**
   * Approach 2: Dynamic Programming



   Intuition

   Suppose we need dp[state] stickers to satisfy all target[i]'s for which the i-th bit of state is set.
   We would like to know dp[(1 << len(target)) - 1].



   Algorithm

   For each state, let's work with it as now and look at what happens to it after applying a sticker.
   For each letter in the sticker that can satisfy an unset bit of state, we set the bit (now |= 1 << i).
   At the end, we know now is the result of applying that sticker to state, and we update our dp appropriately.

   When using Python, we will need some extra techniques from Approach #1 to pass in time.

   Complexity Analysis

   Time Complexity: O(2^T * S * T)O(2
   ​T
   ​​ ∗S∗T) where SS be the total number of letters in all stickers, and TT be the number of letters in the target word.
   We can examine each loop carefully to arrive at this conclusion.

   Space Complexity: O(2^T)O(2
   ​T
   ​​ ), the space used by dp.

   */

  class Solution2 {
    public int minStickers(String[] stickers, String target) {
      int N = target.length();
      int[] dp = new int[1 << N];
      for (int i = 1; i < 1 << N; i++) dp[i] = -1;

      for (int state = 0; state < 1 << N; state++) {
        if (dp[state] == -1) continue;
        for (String sticker: stickers) {
          int now = state;
          for (char letter: sticker.toCharArray()) {
            for (int i = 0; i < N; i++) {
              if (((now >> i) & 1) == 1) continue;
              if (target.charAt(i) == letter) {
                now |= 1 << i;
                break;
              }
            }
          }
          if (dp[now] == -1 || dp[now] > dp[state] + 1) {
            dp[now] = dp[state] + 1;
          }
        }
      }
      return dp[(1 << N) - 1];
    }
  }



}
