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

    //Iterative Java DP solution
    public class Solution {
        public List<List<Integer>> combinationSum(int[] cands, int t) {
            Arrays.sort(cands); // sort candidates to try them in asc order
            List<List<List<Integer>>> dp = new ArrayList<>();
            for (int i = 1; i <= t; i++) { // run through all targets from 1 to t
                List<List<Integer>> newList = new ArrayList(); // combs for curr i
                // run through all candidates <= i
                for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                    // special case when curr target is equal to curr candidate
                    if (i == cands[j]) newList.add(Arrays.asList(cands[j]));
                        // if current candidate is less than the target use prev results
                    else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                        if (cands[j] <= l.get(0)) {
                            List cl = new ArrayList<>();
                            cl.add(cands[j]); cl.addAll(l);
                            newList.add(cl);
                        }
                    }
                }
                dp.add(newList);
            }
            return dp.get(t-1);
        }
    }


    //facebook give n and the order of sum to n
    public List<List<Integer>> combinationSum_fb(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(n <1 && n > Integer.MAX_VALUE) return res;
        List<Integer> set = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }
        combinationSumRe_fb(set, n, 0, path, res);
        return res;
    }
    void combinationSumRe_fb(List<Integer> set, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            List<Integer> p = new ArrayList<Integer>(path);
            res.add(p);
            return;
        }
        for (int i = start; i < set.size() && target >= set.get(i); ++i) {
            path.add(set.get(i));
            combinationSumRe_fb(set, target-set.get(i), i, path, res);
            path.remove(path.size() - 1);
        }
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	CombinationSum slt= new CombinationSum();
		int[] candidates = {2,3,6,7};
		int target = 7;
		slt.printList( slt.combinationSum(candidates, target));
		slt.printList(slt.combinationSum_fb(3));
		
	}
    
    public void printList(List<List<Integer>> result) {
    	if (result == null || result.size() == 0) System.out.println("Empty List!");
    	for (List<Integer> l : result) {
			for (Integer i : l) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
    }
}
