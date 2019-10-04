import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
 120	Triangle	27.4%	Medium
 Problem:    Triangle
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/triangle/
 Notes:
 Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 For example, given the following triangle
 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 Note:
 Bonus point if you are able to do this using only O(n) extra space, where n is the total number 
 of rows in the triangle.
 Solution: Note that there are n elements in the n-th row (n starts from 1).
 1. DP. Do not need additional spaces (happen in-place).
 2.Bottom-Up (Good Solution)

 Up-Botton
 所以时间复杂度是O(n^2)。
 而空间上每次只需维护一层即可（因为当前层只用到上一层的元素），所以空间复杂度是O(n)
 */

public class Triangle {

	public int minimumTotal(List<List<Integer>> triangle) {
		for (int i = triangle.size() - 2; i >= 0; --i) {
			List<Integer> cur = triangle.get(i);
			List<Integer> next = triangle.get(i + 1);
			for (int j = 0; j < i + 1; ++j) {
				cur.set(j, Math.min(next.get(j), next.get(j + 1)) + cur.get(j));
			}
		}
		return triangle == null ? 0 : triangle.get(0).get(0);
	}
	

	public int minimumTotal_2(ArrayList<ArrayList<Integer>> triangle) {
		int[] total = new int[triangle.size()];
		int l = triangle.size() - 1;

		for (int i = 0; i < triangle.get(l).size(); i++) {
			total[i] = triangle.get(l).get(i);
		}

		// iterate from last second row
		for (int i = triangle.size() - 2; i >= 0; i--) {
			for (int j = 0; j < triangle.get(i + 1).size() - 1; j++) {
				total[j] = triangle.get(i).get(j)
						+ Math.min(total[j], total[j + 1]);
			}
		}

		return total[0];
	}
	
	public int minimumTotal_3(List<List<Integer>> triangle) {
        for(int i = triangle.size() - 2; i >= 0; i--)
            for(int j = 0; j <= i; j++)
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
        return triangle.get(0).get(0);
    }

//	public int minimumTotal_3(List<List<Integer>> triangle) {
//		int res = 0;
//
//		for (List<Integer> t : triangle) {
//
//			Object[] x = (Object[])t.toArray();
//			Arrays.sort(x);
//			System.out.println(t.toString());
//			Integer i = Integer.parseInt(x[0].toString());
//			res = res + i.intValue();
//		}
//
//		return res;
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> input = new ArrayList<List<Integer>>();
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(2);
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(3);
		l2.add(4);
		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(6);
		l3.add(5);
		l3.add(7);
		List<Integer> l4 = new ArrayList<Integer>();
		l4.add(4);
		l4.add(1);
		l4.add(8);
		l4.add(3);
		input.add(l1);
		input.add(l2);
		input.add(l3);
		input.add(l4);
		Triangle slt = new Triangle();
		System.out.println(slt.minimumTotal(input));
	}

}
