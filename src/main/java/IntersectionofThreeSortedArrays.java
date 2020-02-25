import java.util.*;

/**
 * 1213. Intersection of Three Sorted Arrays
 * Easy
 *
 * 84
 *
 * 6
 *
 * Add to List
 *
 * Share
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * Output: [1,5]
 * Explanation: Only 1 and 5 appeared in the three arrays.
 *
 *
 * Constraints:
 *
 * 1 <= arr1.length, arr2.length, arr3.length <= 1000
 * 1 <= arr1[i], arr2[i], arr3[i] <= 2000
 * Accepted
 * 12,534
 * Submissions
 * 16,176
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 3
 *
 * TripAdvisor
 * |
 * 2
 * Intersection of Two Arrays
 * Easy
 * Count the frequency of all elements in the three arrays.
 * The elements that appeared in all the arrays would have a frequency of 3.
 */

public class IntersectionofThreeSortedArrays {

    /**
     * binarysearch
     * n(arr1) * 2 * log(n)
     */

    class Solution {
        public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
            List<Integer> res = new ArrayList<>();

            if (arr1 == null || arr1.length == 0) {
                return res;
            }

            for (int i : arr1) {
                if (binarySearch(arr2, i) != -1 && binarySearch(arr3, i) != -1) {
                    res.add(i);
                }
            }

            return res;
        }

        private int binarySearch(int[] A, int target) {
            if (A == null || A.length == 0) {
                return -1;
            }

            int start = 0, end = A.length - 1;
            while (start + 1 < end) {
                int mid = start + (end -start) / 2;
                if (A[mid] == target) {
                    end = mid;
                } else if (A[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (A[start] == target) {
                return start;
            }

            if (A[end] ==  target) {
                return end;
            }

            return -1;
        }
    }

    class Solution_ {
        public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
            List<Integer> ret = new ArrayList<>();
            int i = 0, j = 0, k = 0;
            while (i < arr1.length && j < arr2.length && k < arr3.length) {
                if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                    ret.add(arr1[i]);
                    i++;
                    j++;
                    k++;
                } else if (arr1[i] < arr2[j]) {
                    i++;
                } else if (arr2[j] < arr3[k]) {
                    j++;
                } else {
                    k++;
                }
            }
            return ret;
        }
    }


}
