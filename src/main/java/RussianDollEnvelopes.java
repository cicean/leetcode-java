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
 ����һЩ�ŷ�Ŀ�ͳ������ҽ����ŷ�x�Ŀ�ͳ���С����һ���ŷ�yʱ��x����װ��y����������Ƕ�׵�װ������
 ԭ���Ƕ���˹���ޣ��ε�
 */
public class RussianDollEnvelopes {

    /**
     * ������֪������LIS��(�������������)��������Ϊɶ����hard�Ѷȡ�

     ��LIS����ֱ�Ӽ򵥵�dp����dp[i]Ϊ��iΪ��β��LIS���ȣ���dp[i] = max(0,dp[j] | j<i && A[j] < A[i]) + 1

     ���Ӷ�ΪO(n^2)���������Ż���O(nlogn)������Ȼ����֡�

     ������Ҫע�������ʱ��Ҫע���һ����ͬ������£��ڶ����˳��
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
