package Bloomberg;

/**
 * Created by cicean on 9/12/2016.
 * Bloomberg 电面跪筋
 * 第一题是给了一串0,1,0,1,1,1,1,1,0,0,0,1.......的数组，
 * 然后给了一个K， 表示最多可以翻转K个0，从0翻转到1。问题是最长的连续的1序列有多长。
 */
public class LongestContinuousflipOneSubarray {

    //Bloomberg
    public int maxlength(int[] nums, int k) {
    	if (nums == null) return 0;
        int max = 0;
        int numberOfzero = 0;
        int i = 0;
        int j = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[j] == 0) numberOfzero++;
            System.out.println("i = " + i + ", j = " + j );
            if (numberOfzero > k)  {
                	max = Math.max(max, j - i);
                    while (nums[i] != 0) {
                        i++;
                    }
                    numberOfzero--;
                    i++;
            }
            j++;
        }
        max = Math.max(max, j - i);
    return max;
    }

    /**
     * Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array
     Given an array of 0s and 1s, find the position of 0 to be replaced with 1 to get longest continuous sequence of 1s. Expected time complexity is O(n) and auxiliary space is O(1).
     Example:

     Input:
     arr[] =  {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1}
     Output:
     Index 9
     Assuming array index starts from 0, replacing 0 with 1 at index 9 causes
     the maximum continuous sequence of 1s.

     Input:
     arr[] =  {1, 1, 1, 1, 0}
     Output:
     Index 4
     We strongly recommend to minimize the browser and try this yourself first.

     A Simple Solution is to traverse the array, for every 0, count the number of 1s on both sides of it. Keep track of maximum count for any 0. Finally return index of the 0 with maximum number of 1s around it. The time complexity of this solution is O(n2).

     Using an Efficient Solution, the problem can solved in O(n) time. The idea is to keep track of three indexes, current index (curr), previous zero index (prev_zero) and previous to previous zero index (prev_prev_zero). Traverse the array, if current element is 0, calculate the difference between curr and prev_prev_zero (This difference minus one is the number of 1s around the prev_zero). If the difference between curr and prev_prev_zero is more than maximum so far, then update the maximum.
     Finally return index of the prev_zero with maximum difference.
     * @param arr
     * @param n
     * @return
     */

    // Returns index of 0 to be replaced with 1 to get longest
    // continuous sequence of 1s.  If there is no 0 in array, then
    // it returns -1.
    static int maxOnesIndex(int arr[], int n)
    {
        int max_count = 0;  // for maximum number of 1 around a zero
        int max_index=0;  // for storing result
        int prev_zero = -1;  // index of previous zero
        int prev_prev_zero = -1; // index of previous to previous zero

        // Traverse the input array
        for (int curr=0; curr<n; ++curr)
        {
            // If current element is 0, then calculate the difference
            // between curr and prev_prev_zero
            if (arr[curr] == 0)
            {
                // Update result if count of 1s around prev_zero is more
                if (curr - prev_prev_zero > max_count)
                {
                    max_count = curr - prev_prev_zero;
                    max_index = prev_zero;
                }

                // Update for next iteration
                prev_prev_zero = prev_zero;
                prev_zero = curr;
            }
        }

        // Check for the last encountered zero
        if (n-prev_prev_zero > max_count)
            max_index = prev_zero;

        return max_index;
    }


    // Driver program to test above function
    public static void main(String[] args)
    {
        int arr[] = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1};
        int n = arr.length;
        LongestContinuousflipOneSubarray slt = new LongestContinuousflipOneSubarray();
        System.out.println("Index of 0 to be replaced is "+
                maxOnesIndex(arr, n));
        System.out.println(slt.maxlength(arr, 1));
    }


}
