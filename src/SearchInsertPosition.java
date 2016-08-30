/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Search Insert Position
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_35
 Notes:
 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 You may assume no duplicates in the array.
 Here are few examples.
 [1,3,5,6], 5 -> 2
 [1,3,5,6], 2 -> 1
 [1,3,5,6], 7 -> 4
 [1,3,5,6], 0 -> 0
 Solution: Binary search.
 */

public class SearchInsertPosition {
	public int searchInsert(int[] A, int target) {
        int i = 0, j = A.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (A[mid] == target) return mid;
            if (A[mid] < target) i = mid + 1;
            else j = mid - 1;
        }
        return i;
    }
	
	public static void main(String[] args) {
		int[] A = new int[] { 1, 3, 5, 6 };
		SearchInsertPosition slt = new SearchInsertPosition();
		System.out.println(slt.searchInsert(A, 0));
	}
}
