import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Combination Sum II
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/combination-sum-ii/
 Notes:
 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations
 in C where the candidate numbers sums to T.
 Each number in C may only be used once in the combination.
 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, .. , ak) must be in non-descending order. (ie, a1 <= a2 <= ... <= ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
 A solution set is: 
 [1, 7] 
 [1, 2, 5] 
 [2, 6] 
 [1, 1, 6] 
 Solution: ..Similar to Combination Sum I, except for line 42 && 44.
 */

public class CombinationSumII {
	public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(num);
        ArrayList<Integer> path = new ArrayList<Integer>();
        combinationSumRe(num, target, 0, path, res);
        return res;
    }
    void combinationSumRe(int[] candidates, int target, int start, ArrayList<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            ArrayList<Integer> p = new ArrayList<Integer>(path);
            res.add(p);
            return;
        }
        for (int i = start; i < candidates.length && target >= candidates[i]; ++i) {
            if (i!=start && candidates[i-1] == candidates[i]) continue;
            path.add(candidates[i]);
            combinationSumRe(candidates, target-candidates[i], i+1, path, res);
            path.remove(path.size() - 1);
        }
    }

    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            // a cllection - duplicates
            // used only once
            // avoid duplicate answer list: sort the array and check if the elements is the same as the previous one
            // traverse from the next element in recursive function
            if(candidates == null || candidates.length == 0) return new ArrayList<>();
            Arrays.sort(candidates);
            List<List<Integer>> ans = new ArrayList<>();
            find(candidates, target, 0, ans, new ArrayList<>(), 0);
            return ans;
        }

        private void find(int[] candidates, int target, int sum, List<List<Integer>> ans, List<Integer> list, int start){
            if(target == sum){
                ans.add(new ArrayList<>(list));
                return;
            }
            for(int i=start; i<candidates.length; i++){
                // pre condition: the array is sorted
                if(sum + candidates[i] > target) break;
                // avoid duplicates
                if(i-1 >= start && candidates[i] == candidates[i-1]) continue;
                list.add(candidates[i]);
                find(candidates, target, sum + candidates[i], ans, list, i+1);
                list.remove(list.size() - 1);
            }
        }

    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	CombinationSumII slt= new CombinationSumII();
		int[] candidates = {10,1,2,7,6,1,5};
		int target = 8;
		List<List<Integer>> res = slt.combinationSum2(candidates, target);
		for (List<Integer> l : res) {
			for (Integer i : l) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
