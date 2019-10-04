import java.util.*;
/*
 119	Pascal's Triangle II	29.4%	Easy
 Problem:    Pascal's Triangle II
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_119
 Notes:
 Given an index k, return the kth row of the Pascal's triangle.
 For example, given k = 3,
 Return [1,3,3,1].
 Note:
 Could you optimize your algorithm to use only O(k) extra space?
 Solution: from back to forth...
 */

public class PascalsTriangleII {
	public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            for (int j = i - 1; j >= 1; --j) {
                res.set(j,res.get(j) + res.get(j-1));
            }
            res.add(1);
        }
        return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PascalsTriangleII slt = new PascalsTriangleII();
		int rowIndex = 3;
		System.out.print(slt.getRow(rowIndex));
	}

}
