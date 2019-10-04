import java.util.*;

/**
 * Created by cicean on 8/31/2016.
 * 354. Russian Doll Envelopes  QuestionEditorial Solution  My Submissions
 Total Accepted: 8052 Total Submissions: 26652 Difficulty: Hard
 You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

 What is the maximum number of envelopes can you Russian doll? (put one inside other)

 Example:
 Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

 Hide Company Tags Google
 Hide Tags Binary Search Dynamic Programming
 Hide Similar Problems (M) Longest Increasing Subsequence
 给定一些信封的宽和长，当且仅当信封x的宽和长均小于另一个信封y时，x可以装入y，求最多可以嵌套的装几个？
 原来是俄罗斯套娃，晕倒
 */
public class RussianDollEnvelopes {

    /**
     * 看到就知道是求LIS了(最大上升子序列)，不明白为啥会是hard难度。

     求LIS可以直接简单的dp，设dp[i]为以i为结尾的LIS长度，则dp[i] = max(0,dp[j] | j<i && A[j] < A[i]) + 1

     复杂度为O(n^2)，但可以优化到O(nlogn)，排序然后二分。

     本题需要注意排序的时候要注意第一项相同的情况下，第二项的顺序。
     */

    // O^2
    public class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            if(envelopes==null||envelopes.length==0)
                return 0;
            Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
            int max = 0;
            int dp [] = new int [envelopes.length];
            for(int i = 0; i < envelopes.length; i++){
                dp[i] = 1;
                for(int j = 0; j < i; j++){
                    if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1])
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(dp[i], max);
            }
            return max;


        }
    }

    //Binary Search
    //O(NlgN)
    public class Solution2 {
        public int maxEnvelopes(int[][] envelopes) {
            if(envelopes == null || envelopes.length == 0
                    || envelopes[0] == null || envelopes[0].length != 2)
                return 0;
            Arrays.sort(envelopes, new Comparator<int[]>(){
                public int compare(int[] arr1, int[] arr2){
                    if(arr1[0] == arr2[0])
                        return arr2[1] - arr1[1];
                    else
                        return arr1[0] - arr2[0];
                }
            });
            int dp[] = new int[envelopes.length];
            int len = 0;
            for(int[] envelope : envelopes){
                int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
                if(index < 0)
                    index = -(index + 1);
                dp[index] = envelope[1];
                if(index == len)
                    len++;
            }
            return len;
        }
    }
}
