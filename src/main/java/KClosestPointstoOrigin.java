import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 973. K Closest Points to Origin
 * Medium
 *
 * 805
 *
 * 75
 *
 * Favorite
 *
 * Share
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * Accepted
 * 131,951
 * Submissions
 * 215,624
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 57
 *
 * Facebook
 * |
 * 45
 *
 * Asana
 * |
 * 5
 *
 * Google
 * |
 * 4
 *
 * Expedia
 * |
 * 4
 *
 * Apple
 * |
 * 3
 *
 * eBay
 * |
 * 2
 * Kth Largest Element in an Array
 * Medium
 * Top K Frequent Elements
 * Medium
 * Top K Frequent Words
 * Medium
 */

public class KClosestPointstoOrigin {

    /**
     * Approach 1: Sort
     * Intuition
     *
     * Sort the points by distance, then take the closest K points.
     *
     * Algorithm
     *
     * There are two variants.
     *
     * In Java, we find the K-th distance by creating an array of distances and then sorting them. After,
     * we select all the points with distance less than or equal to this K-th distance.
     *
     * In Python, we sort by a custom key function - namely, the distance to the origin. Afterwards,
     * we return the first K elements of the list.
     *
     *
     *     Complexity Analysis
     *
     *      Time Complexity: O(N \log N)O(NlogN), where NN is the length of points.
     *
     *      Space Complexity: O(N)O(N).
     * */

    class Solution_sort {
        public int[][] kClosest(int[][] points, int K) {
            int N = points.length;
            int[] dists = new int[N];
            for (int i = 0; i < N; ++i)
                dists[i] = dist(points[i]);

            Arrays.sort(dists);
            int distK = dists[K-1];

            int[][] ans = new int[K][2];
            int t = 0;
            for (int i = 0; i < N; ++i)
                if (dist(points[i]) <= distK)
                    ans[t++] = points[i];
            return ans;
        }

        public int dist(int[] point) {
            return point[0] * point[0] + point[1] * point[1];
        }
    }

    /**
     * Approach 2: Divide and Conquer
     * Intuition
     *
     * We want an algorithm faster than N \log NNlogN. Clearly, the only way to do this is to use the fact that
     * the K elements returned can be in any order -- otherwise we would be sorting which is at least N \log NNlogN.
     *
     * Say we choose some random element x = A[i] and split the array into two buckets: one bucket of all the elements
     * less than x, and another bucket of all the elements greater than or equal to x. This is known as "quickselecting by a pivot x".
     *
     * The idea is that if we quickselect by some pivot, on average in linear time we'll reduce the problem to
     * a problem of half the size.
     *
     * Algorithm
     *
     * Let's do the work(i, j, K) of partially sorting the subarray (points[i], points[i+1], ..., points[j])
     * so that the smallest K elements of this subarray occur in the first K positions (i, i+1, ..., i+K-1).
     *
     * First, we quickselect by a random pivot element from the subarray. To do this in place, we have two pointers i and j,
     * and move these pointers to the elements that are in the wrong bucket -- then, we swap these elements.
     *
     * After, we have two buckets [oi, i] and [i+1, oj], where (oi, oj) are the original (i, j) values when calling work(i, j, K).
     * Say the first bucket has 10 items and the second bucket has 15 items. If we were trying to partially sort say, K = 5 items,
     * then we only need to partially sort the first bucket: work(oi, i, 5). Otherwise, if we were trying to partially sort say,
     * K = 17 items, then the first 10 items are already partially sorted, and we only need to partially sort the next 7 items:
     * work(i+1, oj, 7).
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N) in average case complexity, where NN is the length of points.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution {
        int[][] points;
        public int[][] kClosest(int[][] points, int K) {
            this.points = points;
            sort(0, points.length - 1, K);
            return Arrays.copyOfRange(points, 0, K);
        }

        public void sort(int i, int j, int K) {
            if (i >= j) return;
            int k = ThreadLocalRandom.current().nextInt(i, j);
            swap(i, k);

            int mid = partition(i, j);
            int leftLength = mid - i + 1;
            if (K < leftLength)
                sort(i, mid - 1, K);
            else if (K > leftLength)
                sort(mid + 1, j, K - leftLength);
        }

        public int partition(int i, int j) {
            int oi = i;
            int pivot = dist(i);
            i++;

            while (true) {
                while (i < j && dist(i) < pivot)
                    i++;
                while (i <= j && dist(j) > pivot)
                    j--;
                if (i >= j) break;
                swap(i, j);
            }
            swap(oi, j);
            return j;
        }

        public int dist(int i) {
            return points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }

        public void swap(int i, int j) {
            int t0 = points[i][0], t1 = points[i][1];
            points[i][0] = points[j][0];
            points[i][1] = points[j][1];
            points[j][0] = t0;
            points[j][1] = t1;
        }
    }

    class Solution_pq {

        private int dist(int[] point) {
            return point[0] * point[0] + point[1] * point[1];
        }

        class KClosestComparator implements Comparator<int[]> {

            public int compare(int[] p1, int[] p2) {

                return dist(p1) < dist(p2) ? 1 : -1;
            }
        }

        public int[][] kClosest(int[][] points, int K) {

            if (points.length <= K) {
                return points;
            }

            int[][] result = new int[K][2];

            PriorityQueue<int[]> pq = new PriorityQueue<>(K, new KClosestComparator());

            for (int[] p : points) {
                pq.offer(p);
                if (pq.size() > K) {
                    pq.poll();
                }
            }

            int i = 0;
            System.out.println(pq.size());
            pq.toArray(result);

            return result;
        }
    }

}
