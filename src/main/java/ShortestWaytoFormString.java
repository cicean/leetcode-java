/**
 * 1055. Shortest Way to Form String
 * Medium
 *
 * 109
 *
 * 6
 *
 * Favorite
 *
 * Share
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
 *
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 * Example 2:
 *
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
 * Example 3:
 *
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 *
 *
 * Constraints:
 *
 * Both the source and target strings consist of only lowercase English letters from "a"-"z".
 * The lengths of source and target string are between 1 and 1000.
 * Accepted
 * 6,557
 * Submissions
 * 11,168
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * weiyang95
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 9
 * Is Subsequence
 * Easy
 * Number of Matching Subsequences
 * Medium
 * Which conditions have to been met in order to be impossible to form the target string?
 * If there exists a character in the target string which doesn't exist in the source string then it will be impossible to form the target string.
 * Assuming we are in the case which is possible to form the target string, how can we assure the minimum number of used subsequences of source?
 * For each used subsequence try to match the leftmost character of the current subsequence with the leftmost character of the target string, if they match then erase both character otherwise erase just the subsequence character whenever the current subsequence gets empty, reset it to a new copy of subsequence and increment the count, do this until the target sequence gets empty. Finally return the count.
 */

public class ShortestWaytoFormString {
    /*
    This is a really nice solution that runs in O(|Σ|*M + N) time that is based off of @xiangmo's solution (where M is length of S and N is length of target).
I'd like to provide an explanation to hopefully clear up the idea behind this code more.

The general idea behind the code is similar to @TwoHu's solution post: . The difference is the inverted index data structure used.

The main idea behind this code is also to build up an inverted index data structure for the source string and then to greedily use characters from source to build up the target. In this code, it's the dict array. Each character is mapped to an index where it is found at in source. In this code, dict[i][c - 'a'] represents the earliest index >= i where character c occurs in source.

For example, if source = "xyzy", then dict[0]['y' - 'a'] = 1 but dict[2]['y'-'a'] = 3.

Also a value of -1, means that there are no occurrences of character c after the index i.

So, after this inverted data structure is built (which took O(|Σ|*M) time). We iterate through the characters of our target String. The idxOfS represents the current index we are at in source.
For each character c in target, we look for the earliest occurrence of c in source using dict via dict[idxOfS][c - 'a']. If this is -1, then we have not found any other occurrences and hence we need to use a new subsequence of S.

Otherwise, we update idxOfS to be dict[idxOfS][c - 'a'] + 1 since we can only choose characters of source that occur after this character if we wish to use the same current subsequence to build the target.

dict[idxOfS][c-'a'] = N - 1 is used as a marker value to represent that we have finished consuming the entire source and hence need to use a new subsequence to continue.

(I would highly recommend reading @Twohu's examples of how to use the inverted index data structure to greedily build target using the indexes. They go into much more detail).

At the end, the check for (idxOfS == 0? 0 : 1) represents whether or not we were in the middle of matching another subsequence. If we were in the middle of matching it, then we would need an extra subsequence count of 1 since it was never accounted for.

Hopefully, this helped a little. I really liked this solution and it wasn't commented so it may be hard to understand directly.

I included my commented and slightly modified code:
     */

    public int shortestWay(String source, String target) {
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();

        int M = s.length;
        int N = t.length;

        // Build inverted index for source
        int[][] dict = new int[M][26];

        Arrays.fill(dict[M-1], -1); // -1 represents no occurrence of the character

        dict[M-1][s[M-1] - 'a'] = M-1;
        for(int x = M - 2; x >= 0; --x) {
            dict[x] = Arrays.copyOf(dict[x+1], 26);
            dict[x][s[x] - 'a'] = x;
        }

        int ans = 0;
        int idx = 0;
        // Go through target and account for each character
        for(char c: t) {
            // If there are no occurrences of c with index greater than 0
            // then it doesn't occur at all. Hence, we cannot get that letter from
            // a subsequence of source.
            if(dict[0][c - 'a'] == -1) return -1;

            // If there are no c's left in source that occur >= idx
            // but there are c's from earlier in the subsequence
            // add 1 to subsequence count and reset idx of source to 0.
            if(dict[idx][c - 'a'] == -1) {
                ++ans;
                idx = 0;
            }

            // Then continue taking letters from the subsequence
            idx = dict[idx][c-'a'] + 1;

            if(idx == M) {
                ++ans;
                idx = 0;
            }
        }

        return ans + (idx == 0 ? 0: 1);
    }
}
