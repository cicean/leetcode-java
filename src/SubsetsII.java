import java.util.*;

/*
 90	Subsets II	27.6%	Medium
 Problem:    Subsets II
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/subsets-ii/
 Notes:
 Given a collection of integers that might contain duplicates, S, return all possible subsets.
 Note:
 Elements in a subset must be in non-descending order.
 The solution set must not contain duplicate subsets.
 For example,
 If S = [1,2,2], a solution is:
 [
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
 ]
 Solution: ..Similar to Subset I.
 
 ˵��
��i��ͬ�������ظ������֣� ����[1,2(1)] [1,2(2)]�����ͬ�� �ݹ������ÿ����ѡȡ�Ŀ����Ǻ͵ݹ�������ͬ�ģ�������nums��[1��2��2��2�� ��ǰ�ݹ�ջ�ǣ�1��2��1���� �¼���ģ�1��2(1), 2(2)]�ǺϷ��ģ� ���Ǻ����Ԫ�ؾͲ�����ͬ�ˣ���1, 2(1), 2(3)]�Ͳ��Ϸ� ��Ϊ�ظ��ˣ���
��ѡȡ��Ԫ���ڵݹ�ջ�����ÿ�ε�iȡindex��ֵʱ��, ���Ե�i!=index��ֵʱ����ȥ��

���Ӷ�
ʱ��O(2^n) �ռ�O(2^n)
 */

public class SubsetsII {

    public List<List<Integer>> subsetsWithDup_1(int[] S) {
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
            if (i != start && S[i] == S[i-1]) continue;
            path.add(S[i]);
            subsetsRe(S, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
    
	public List<List<Integer>> subsetsWithDup(int[] S) {
        return subsetsWithDup_2(S);
    }
    
    public List<List<Integer>> subsetsWithDup_2(int[] S) {
        Arrays.sort(S);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        int presz = 0;
        for (int i = 0; i < S.length; ++i) {
            int sz = res.size();
            for (int j = 0; j < sz; ++j) {
                if (i == 0 || S[i] != S[i-1] || j >= presz) {
                    List<Integer> path = new ArrayList<Integer>(res.get(j));
                    path.add(S[i]);
                    res.add(path);
                }
            }
            presz = sz;
        }
        return res;
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
		SubsetsII slt = new SubsetsII();
		int[] num = {1,2,2};
		print(slt.subsetsWithDup(num));
	}

}
