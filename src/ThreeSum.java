import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 15	3Sum	16.9%	Medium
 Problem:    3Sum
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/3sum/
 Notes:
 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 Find all unique triplets in the array which gives the sum of zero.
 Note:
 Elements in a triplet (a,b,c) must be in non-descending order. (ie, a <= b <= c)
 The solution set must not contain duplicate triplets.
 For example, given array S = {-1 0 1 2 -1 -4},
 A solution set is:
 (-1, 0, 1)
 (-1, -1, 2)
 Solution: Simplify '3sum' to '2sum' O(n^2). http://en.wikipedia.org/wiki/3SUM
*/

//Ê±¼ä O(N^2) ¿Õ¼ä O(1)
public class ThreeSum {
	 public List<List<Integer>> threeSum(int[] num) {
		 Arrays.sort(num);
		 List<List<Integer>> res = new LinkedList<>();
		 for (int i = 0; i < num.length-2; i++) {
			 if (i == 0 || (i > 0 && num[i] != num[i-1])) {
				 int lo = i+1, hi = num.length-1, sum = 0 - num[i];
				 while (lo < hi) {
					 if (num[lo] + num[hi] == sum) {
						 res.add(Arrays.asList(num[i], num[lo], num[hi]));
						 while (lo < hi && num[lo] == num[lo+1]) lo++;
						 while (lo < hi && num[hi] == num[hi-1]) hi--;
						 lo++; hi--;
					 } else if (num[lo] + num[hi] < sum) lo++;
					 else hi--;
				 }
			 }
		 }
		 return res;
	    }
	 
	 public static void main(String[] args) {
			int[] num = { -1, 0, 1, 2, -1, -4 };
			
			ThreeSum slt = new ThreeSum();

		}
}
