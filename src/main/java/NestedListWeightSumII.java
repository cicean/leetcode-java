import datastructure.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 8/30/2016.
 * 364. Nested List Weight Sum II  QuestionEditorial Solution  My Submissions
 Total Accepted: 3465
 Total Submissions: 7132
 Difficulty: Medium
 Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

 Example 1:
 Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

 Example 2:
 Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)

 Hide Company Tags LinkedIn
 Hide Tags Depth-first Search
 Hide Similar Problems (E) Nested List Weight Sum

 */
public class NestedListWeightSumII {

    /**
     * 这一题其实挺tricky的，如果说第一道题的关键是记录层次，
     * 那么这一题的关键是把这一层的integer sum传到下一层去，
     */
    public int DFS(List<NestedInteger> nestedList, int intSum) {
        //关键点在于把上一层的integer sum传到下一层去，这样的话，接下来还有几层，每一层都会加上这个integer sum,也就等于乘以了它的层数
        List<NestedInteger> nextLevel = new ArrayList<>();
        int listSum = 0;
        for (NestedInteger list : nestedList) {
            if (list.isInteger()) {
                intSum += list.getInteger();
            } else {
                nextLevel.addAll(list.getList());
            }
        }
        listSum = nextLevel.isEmpty() ? 0 : DFS(nextLevel, intSum);
        return listSum + intSum;
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        return DFS(nestedList, 0);
    }
}
