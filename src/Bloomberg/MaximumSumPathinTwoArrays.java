package Bloomberg;

/**
 * Created by cicean on 9/10/2016.
 * Maximum Sum Path in Two Arrays
 Given two sorted arrays such the arrays may have some common elements. Find the sum of the maximum sum path to reach from beginning of any array to end of any of the two arrays. We can switch from one array to another array only at common elements.

 Expected time complexity is O(m+n) where m is the number of elements in ar1[] and n is the number of elements in ar2[].

 Examples:

 Input:  ar1[] = {2, 3, 7, 10, 12}, ar2[] = {1, 5, 7, 8}
 Output: 35
 35 is sum of 1 + 5 + 7 + 10 + 12.
 We start from first element of arr2 which is 1, then we
 move to 5, then 7.  From 7, we switch to ar1 (7 is common)
 and traverse 10 and 12.

 Input:  ar1[] = {10, 12}, ar2 = {5, 7, 9}
 Output: 22
 22 is sum of 10 and 12.
 Since there is no common element, we need to take all
 elements from the array with more sum.

 Input:  ar1[] = {2, 3, 7, 10, 12, 15, 30, 34}
 ar2[] = {1, 5, 7, 8, 10, 15, 16, 19}
 Output: 122
 122 is sum of 1, 5, 7, 8, 10, 12, 15, 30, 34
 We strongly recommend that you click here and practice it, before moving on to the solution.

 The idea is to do something similar to merge process of merge sort. We need to calculate sums of elements between all common points for both arrays. Whenever we see a common point, we compare the two sums and add the maximum of two to the result. Following are detailed steps.

 1) Initialize result as 0. Also initialize two variables sum1 and sum2 as 0. Here sum1 and sum2 are used to store sum of element in ar1[] and ar2[] respectively. These sums are between two common points.

 2) Now run a loop to traverse elements of both arrays. While traversing compare current elements of ar1[] and ar2[].

 2.a) If current element of ar1[] is smaller than current element of ar2[], then update sum1, else if current element of ar2[] is smaller, then update sum2.

 2.b) If current element of ar1[] and ar2[] are same, then take the maximum of sum1 and sum2 and add it to the result. Also add the common element to the result.
 */
public class MaximumSumPathinTwoArrays {

    // Utility function to find maximum of two integers
    int max(int x, int y) {
        return (x > y) ? x : y;
    }

    // This function returns the sum of elements on maximum path
    // from beginning to end
    int maxPathSum(int ar1[], int ar2[]) {
        // initialize indexes for ar1[] and ar2[]
        int i = 0, j = 0;

        // Initialize result and current sum through ar1[] and ar2[].
        int result = 0, sum1 = 0, sum2 = 0;

        int m = ar1.length, n = ar2.length;

        // Below 3 loops are similar to merge in merge sort
        while (i < ar1.length && j < ar2.length) {
            // Add elements of ar1[] to sum1
            if (ar1[i] < ar2[j])
                sum1 += ar1[i++];

                // Add elements of ar2[] to sum2
            else if (ar1[i] > ar2[j])
                sum2 += ar2[j++];

                // we reached a common point
            else {
                // Take the maximum of two sums and add to result
                result += max(sum1, sum2);

                // Update sum1 and sum2 for elements after this
                // intersection point
                sum1 = 0;
                sum2 = 0;

                // Keep updating result while there are more common
                // elements
                while (i < m && j < n && ar1[i] == ar2[j]) {
                    result += ar1[i++];
                    j++;
                }
            }
        }

        // Add remaining elements of ar1[]
        while (i < m)
            sum1 += ar1[i++];

        // Add remaining elements of ar2[]
        while (j < n)
            sum2 += ar2[j++];

        // Add maximum of two sums of remaining elements
        result += max(sum1, sum2);

        return result;

    }

    
    
    public static void main(String[] args) {
		int[] nums1 = {2,3,7,10,12};
		int[] nums2 = {1,5,7,8};
		
		MaximumSumPathinTwoArrays slt = new MaximumSumPathinTwoArrays();
		System.out.println(slt.maxPathSum(nums1, nums2));
		
	}

}
