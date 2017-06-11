import java.util.*;

/**
 * Created by cicean on 10/23/2016.
 *
 * 440. K-th Smallest in Lexicographical Order   QuestionEditorial Solution  My Submissions
 Total Accepted: 317
 Total Submissions: 2409
 Difficulty: Hard
 Contributors: Stomach_ache
 Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

 Note: 1 ¡Ü k ¡Ü n ¡Ü 109.

 Example:

 Input:
 n: 13   k: 2

 Output:
 10

 Explanation:
 The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 Hide Company Tags Hulu

 */
public class KthSmallestinLexicographicalOrder {

    public int findKthNumber(int n, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        int count=0;
        for(int i=1;i<10;i++) {
            stack.push(i);
            while(!stack.isEmpty()) {
                int current = stack.pop();
                if(k==++count) return current;
                int countInSubtree = count(n,current);
                if(countInSubtree+count<k) {
                    count=countInSubtree+count-1;
                } else {
                    for(int j=9;j>=0;j--) {
                        int nextVal = Integer.parseInt(""+current+j);
                        if(nextVal<=n) {
                            stack.push(nextVal);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private int count(int n, long val) {
        int ans = 0;
        int number = 1;
        while(val<=n) {
            ans += number;
            val *= 10;
            number *= 10;
        }
        if(n<(val/10+number/10-1)) ans -= val/10+number/10-1-n;
        return ans;
    }
}
