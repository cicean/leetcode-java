import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import datastructure.PrintList;

/**
128. Longest Consecutive Sequence  QuestionEditorial Solution  My Submissions
Total Accepted: 76532
Total Submissions: 225961
Difficulty: Hard
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Hide Company Tags Google Facebook 
Hide Tags Array Union Find
Hide Similar Problems (M) Binary Tree Longest Consecutive Sequence

*/

public class LongestConsecutiveSequence {
	
	public int longestConsecutive(int[] num) {
        int size = num.length;
        HashMap<Integer, Integer> unmap = new HashMap<Integer, Integer>();
        int res = 0;
        for (int i = 0; i < size; ++i) {
            if (unmap.containsKey(num[i]) == true) continue;
            int val = num[i];
            if (unmap.containsKey(val - 1) == true && unmap.containsKey(val + 1) == true) {
                unmap.put(val, unmap.get(val - 1) + unmap.get(val + 1) + 1);
                unmap.put(val - unmap.get(val - 1), unmap.get(val));
                unmap.put(val + unmap.get(val + 1), unmap.get(val));
            } else if (unmap.containsKey(val - 1) == true) {
                unmap.put(val, unmap.get(val - 1) + 1);
                unmap.put(val - unmap.get(val - 1), unmap.get(val));
            } else if (unmap.containsKey(val + 1) == true) {
                unmap.put(val, unmap.get(val + 1) + 1);
                unmap.put(val + unmap.get(val + 1), unmap.get(val));   
            } else {
                unmap.put(val, 1);
            }
            res = Math.max(res, unmap.get(val));
        }
        return res;
    }
	

    //bloomberg电面
	int[] nums;
	boolean[] mark;
    public List<Integer> longestConsecutive_bb(int[] num) {
    	List<Integer> res = new ArrayList<>();
    
    	if (num == null || num.length == 0) return res;
    	this.nums = num;
    	this.mark = new boolean[num.length];
    	for (int i : num) {
    		System.out.print(i);
    			List<Integer> value = new ArrayList<>();
    			find(i, value);
    			res = (res.size() > value.size()) ? res : value;
    	}
    	
    	return res;
    }
    
    //DFS
    private void find(int i, List<Integer> res) {
    	if (!contains(i)) {
    		return;
    	}
    	res.add(i);
    	find(i + 1, res);
    	find(i - 1, res);
    	System.out.print("i = " + i + ", ");
    }
    
    private boolean contains(int p) {
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] == p && !mark[i]) {
    			mark[i] = true;
    			return true;
    		} 
    	}
    	return false;
    }
    
    
    
    // PocketGam 电面
    public boolean longestConsecutive(int[] num, int len) {
    	int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);
                
                // keep track of the max length 
                res = Math.max(res, sum);
                if (res >= len) return true;
                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
            else {
                // duplicates
                continue;
            }
        }
        return false;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestConsecutiveSequence slt = new LongestConsecutiveSequence();
		PrintList<Integer> res = new PrintList<>();
		int[] num = {100, 4, 200, 1, 3, 2};
		System.out.println(slt.longestConsecutive(num, 4));
		res.printList(slt.longestConsecutive_bb(num));
		//System.out.print(res);
	}

}
