/*
 162	Find Peak Element	31.5%	Medium
 Problem:    Find Peak Element
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/find-peak-element/
 Notes:
 A peak element is an element that is greater than its neighbors.
 Given an input array where num[i] �� num[i+1], find a peak element and return its index.
 You may imagine that num[-1] = num[n] = -��.
 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 Find the peak element.
*/

public class FindPeakElement {

	public int findPeakElement(int[] num) {
        int left = 0, right = num.length - 1, mid = -1;
        while (left <= right) {
            mid = (left + right) /2;
            if ((mid == 0 || num[mid-1] <= num[mid]) && (mid == num.length - 1 || num[mid] >= num[mid+1]))
                return mid;
            if (mid > 0 && num[mid-1] > num[mid]) {
                right = mid - 1;
            } else if (num[mid+1] > num[mid]) {
                left = mid + 1;
            }
        }
        return mid;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {1,2,3,1};
		FindPeakElement slt = new FindPeakElement();
		int res = slt.findPeakElement(num);
		System.out.print(num[res]+" is a peak element and index number is "+ res);
	}
}
