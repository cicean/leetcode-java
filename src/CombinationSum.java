import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 39	Combination Sum	27.9%	Medium
 Problem:    Combination Sum
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_39
 Notes:
 Given a set of candidate numbers (C) and a target number (T), find all unique 
 combinations in C where the candidate numbers sums to T.
 The same repeated number may be chosen from C unlimited number of times.
 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, .. , ak) must be in non-descending order. (ie, a1 <= a2 <= ... <= ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 2,3,6,7 and target 7, 
 A solution set is: 
 [7] 
 [2, 2, 3] 
 Solution: Sort & Recursion.
*/

public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        ArrayList<Integer> path = new ArrayList<Integer>();
        combinationSumRe(candidates, target, 0, path, res);
        return res;
    }
    void combinationSumRe(int[] candidates, int target, int start, ArrayList<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            ArrayList<Integer> p = new ArrayList<Integer>(path);
            res.add(p);
            return;
        }
        for (int i = start; i < candidates.length && target >= candidates[i]; ++i) {
            path.add(candidates[i]);
            combinationSumRe(candidates, target-candidates[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	CombinationSum slt= new CombinationSum();
		int[] candidates = {2,3,6,7};
		int target = 7;
		List<List<Integer>> res = slt.combinationSum(candidates, target);
		for (List<Integer> l : res) {
			for (Integer i : l) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
