import java.util.ArrayList;
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

//ʱ�� O(N^2) �ռ� O(1)
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

	//no sort use the hashmap
	/*  No hashmaps and no sorting, but may require
 * a lot of memory if the range of numbers in nums[] is pretty big.
    Basic idea - use integers from nums[] as indices
    in a new array - call it histogram. Then number at
    index x in this histogram would represent number of
    elements in nums[] equal to x
*/
	public class Solution {
		public List<List<Integer>> threeSum(int[] nums) {
			Integer[] hist;
			List<List<Integer>> ret;

			ret = new ArrayList<List<Integer>>();
			if (nums.length < 3)
				return ret;

			// Loop 1 - determine bounds [sMin,sMax]
			// Any number from nums[] outside of these bounds
			// cannot make a tulip (a,b,c) where a+b+c=0
			Integer max1 = null, max2 = null, min1 = null, min2 = null;
			for (int i = 0; i < nums.length; i++) {
				if (i == 0) {
					max1 = min1 = nums[0];
				} else {

					if (nums[i] < min1){
						min2 = min1;
						min1 = nums[i];
					}
					else if (min2 == null || nums[i] < min2)
						min2 = nums[i];

					if (nums[i] > max1){
						max2 = max1;
						max1 = nums[i];
					}
					else if (max2 == null || nums[i] > max2)
						max2 = nums[i];
				}
			}

			int sMin = Math.max(min1, 0 - max1 - max2);
			int sMax = Math.min(max1, 0 - min1 - min2);
			int offset = -sMin;
			int size = sMax - sMin + 1;
			if (size < 1)
				return ret;
			hist = new Integer[size];

			// Loop 2 - build histogram - array with indices matching
			// numbers from input nums[] array restricted to [sMin,sMax] range.
			// This histogram has more elements than distinct set of valid input integers.
			// Some elements of histogram are nulls since their indices are not present in nums[].
			// Use offset to start array from 0
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < sMin || nums[i] > sMax)
					continue;
				if (null == hist[nums[i] + offset])
					hist[nums[i] + offset] = 1;
				else
					hist[nums[i] + offset]++;

			}

			// Loop 3 - loop through histogram
			// In the outer loop only consider negative numbers since
			// at least one number in tulip (a,b,c) should be negative
			// (excluding {0,0,0} case)
			for (int i = 0;i - offset < 0;i++)
			{
				// number a from (a,b,c) isn't present in nums[]
				if (null == hist[i])
					continue;
				// Nested loop to find 2 more numbers in the tulip that includes number i
				// Consider only tulips with elements in non-descending order
				for (int j = i;j < hist.length && (j <=  3 * offset - i - j);j++)
				{
					// Several validations:
					// when tulip (a,b,c) includes 2 equal numbers a and b
					if (j == i && hist[i]<2)
						continue;
					// when histogram doesn't have number j
					if (null == hist[j])
						continue;
					// when third number in the tulip is missing or is out of bound
					if (3*offset - i - j < 0 || 3*offset - i - j >= size || null == hist[3*offset - i - j])
						continue;
					// when tulip (a,b,c) includes 2 equal numbers b and c
					if ((j ==  3 * offset - i - j) && hist[j] < 2 )
						continue;
					// if we got here - add a valid tulip
					ret.add(Arrays.asList(i - offset,j - offset, 2*offset - i - j));
				}
			}

			// Handle case when array includes 3 zeros
			if (null!= hist[offset] && hist[offset] >= 3)
				ret.add(Arrays.asList(0,0,0));

			return ret;

		}
	}
	 
	 public static void main(String[] args) {
			int[] num = { -1, 0, 1, 2, -1, -4 };
			
			ThreeSum slt = new ThreeSum();

		}
}
