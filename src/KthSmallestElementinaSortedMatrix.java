import java.util.*;

/**
 * Created by cicean on 9/2/2016.
 * 378. Kth Smallest Element in a Sorted Matrix  QuestionEditorial Solution  My Submissions
 Total Accepted: 9868 Total Submissions: 23971 Difficulty: Medium
 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 return 13.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ n2.

 Hide Company Tags Google Twitter
 Hide Tags Binary Search Heap
 Hide Similar Problems (M) Find K Pairs with Smallest Sums
 给定一个每一行每一列都排好序的矩阵，求其中第k大的元素

 */
public class KthSmallestElementinaSortedMatrix {

    //Java heap solution, time complexity klog(k)
    /**
     * In heap, element is an array
     int[0] represents matrix[i][j] value
     int[1] represents i
     int[2] represents j
     Whenever an element is poll() from heap,
     push the element below it to heap,
     only push the element right to it to heap when it's in first row.
     So we can avoid duplicates.
     */

    //time complexity klog(k)
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(10000,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){
                return a[0]-b[0];
            }
        });
        if(matrix.length==0||k==0){
            return -1;
        }
        heap.offer(new int[]{matrix[0][0],0,0});
        int[] peak = new int[3];
        while(k-->0){
            peak = heap.poll();
            if(peak[1]+1<matrix.length){
                heap.offer(new int[]{matrix[peak[1]+1][peak[2]],peak[1]+1,peak[2]});
            }
            if(peak[1]==0&&peak[2]+1<matrix[0].length){
                heap.offer(new int[]{matrix[peak[1]][peak[2]+1],peak[1],peak[2]+1});
            }
        }
        return peak[0];
    }

    /**
     * Main loop is binary search of max - min.
     Swap from left-bottom to right-top can get count <= mid in O(n) time instead of O(nlogn),
     total complexity will be O(nlogm) while m = max - min.
     */
    public class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int count = getLessEqual(matrix, mid);
                if (count < k) lo = mid + 1;
                else hi = mid - 1;
            }
            return lo;
        }

        private int getLessEqual(int[][] matrix, int val) {
            int res = 0;
            int n = matrix.length, i = n - 1, j = 0;
            while (i >= 0 && j < n) {
                if (matrix[i][j] > val) i--;
                else {
                    res += i + 1;
                    j++;
                }
            }
            return res;
        }
    }

}
