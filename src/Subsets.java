import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 78	Subsets	28.2%	Medium4
 Problem:    Subsets
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/subsets/
 Notes:
 Given a set of distinct integers, S, return all possible subsets.
 Note:
 Elements in a subset must be in non-descending order.
 The solution set must not contain duplicate subsets.
 For example,
 If S = [1,2,3], a solution is:
 [
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
 ]
 Solution: 1. Updated Iterative solution.
           2. Updated Recursive solution.
 */

public class Subsets {

    public List<List<Integer>> subsets_1(int[] S) {
        Arrays.sort(S);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        subsetsRe(S, 0, path, res);
        return res;
    }
    void subsetsRe(int[] S, int start, List<Integer> path, List<List<Integer>> res) {
        List<Integer> sub = new ArrayList<Integer>(path);
        res.add(sub);
        for (int i = start; i < S.length; ++i) {
            path.add(S[i]);
            subsetsRe(S, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
    
    /*
     * 说明
一道经典的回溯问题, 要注意的是在leetcode中如果想AC的话数组必须先排序才可以.

复杂度
因为长度为n的数组的子集个数为2^n
时间O(2^n) 空间O(2^n)
     */
    
	public List<List<Integer>> subsets(int[] S) {
        return subsets_2(S);
    } 
	
    public List<List<Integer>> subsets_2(int[] S) {
        Arrays.sort(S);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < S.length; ++i) {
            int sz = res.size();
            for (int j = 0; j < sz; ++j) {
                List<Integer> path = new ArrayList<Integer>(res.get(j));
                path.add(S[i]);
                res.add(path);
            }
        }
        return res;
    }

    public List<List<Integer>> subsets_bitops(int[] ns) {
        long limit = 1 << ns.length;
        List<List<Integer>> ret = new ArrayList<>();

        for(long i =0; i < limit; i ++){
            List<Integer> set = new ArrayList<>();
            ret.add(set);
            for(int j =0; j < ns.length; j ++){
                if(isSet(i, j))
                    set.add(ns[j]);
            }
        }

        return ret;
    }

    boolean isSet(long n, int i){
        return ((1 << i) & n) != 0  ;
    }
    
  
    
    public static void print(List<List<Integer>> a) {
    	for (List<Integer> l : a) {
    		for (Integer i : l) {
    			System.out.print(i + " ");
    		}
    		System.out.println();
    	}
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subsets slt = new Subsets();
				int[] num = {4,1,0};
				print(slt.subsets(num));

	}

}
