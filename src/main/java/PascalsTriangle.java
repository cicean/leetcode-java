import java.util.*;

/*
 118	Pascal's Triangle	30.1%	Easy
 Problem:    Pascal's Triangle
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_118
 Notes:
 Given numRows, generate the first numRows of Pascal's triangle.
 For example, given numRows = 5,
 Return
 [
      [1],
     [1,1],
    [1,2,1],
   [1,3,3,1],
  [1,4,6,4,1]
 ]
 Solution: .....
 */


public class PascalsTriangle {
	
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows < 1) return res;
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(1);
        res.add(temp);
        for (int i = 1; i < numRows; ++i) {
            List<Integer> t = new ArrayList<Integer>();
            t.add(1); 
            for (int j = 1; j < i; ++j) {
                t.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
            }
            t.add(1);
            res.add(t);
        }
        return res;
    }
	
	public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> row = new ArrayList<Integer>();
        if (numRows < 1) return res;
        
        for (int i = 0; i < numRows; i++) {
        	
        	row.add(0, 1);
        	
        	for (int j = 1; j < i +1; j ++ ) {
        		row.add(j, row.get(j) + row.get(j+1));
        	}
        	
        	res.add(new ArrayList<Integer>(row));
        }
        
        
        return res;
    }
	
	
	
	
	
	
	public static void print(List<List<Integer>> res) {
    	for (int i = 0; i < res.size(); i++) {
    		for (int j = 0; j < res.get(i).size(); j++) {
    			System.out.print(res.get(i).get(j) + " ");
    		}
    		System.out.println();
    	}
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PascalsTriangle slt = new PascalsTriangle();
		int numRows = 5;
		print(slt.generate1(numRows));
	}

}
