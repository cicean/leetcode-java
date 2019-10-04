package Uber;

import java.util.*;

/**
 * Created by cicean on 9/11/2018.
 */
public class intersections {

    /**
     * Example
     Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

     Challenge
     Can you implement it in three different algorithms?
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        int[] temp = new int[nums1.length];
        int index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (index == 0 || temp[index - 1] != nums1[i]) {
                    temp[index++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[index];
        for (int k = 0; k < index; k++) {
            result[k] = temp[k];
        }

        return result;
    }

    /**
     * Example
     Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionII(int[] nums1, int[] nums2) {
        // write your code here
        // Write your code here
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums1.length; ++i) {
            if (map.containsKey(nums1[i]))
                map.put(nums1[i], map.get(nums1[i]) + 1);
            else
                map.put(nums1[i], 1);
        }

        List<Integer> results = new ArrayList<Integer>();

        for (int i = 0; i < nums2.length; ++i)
            if (map.containsKey(nums2[i]) &&
                    map.get(nums2[i]) > 0) {
                results.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }

        int result[] = new int[results.size()];
        for(int i = 0; i < results.size(); ++i)
            result[i] = results.get(i);

        return result;
    }

    class Pair {
        public int row, col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Given [[1,2,3],[3,4,5],[3,9,10]], return 1
     * @param arrs: the arrays
     * @return: the number of the intersection of the arrays
     */
    public int intersectionOfArrays(int[][] arrs) {
        // write your code here
        Comparator<Pair> comparator = new Comparator<Pair>() {
            public int compare(Pair x, Pair y) {
                return arrs[x.row][x.col] - arrs[y.row][y.col];
            }
        };

        Queue<Pair> queue = new PriorityQueue<>(arrs.length, comparator);

        for (int i = 0; i < arrs.length; i++) {
            if (arrs[i].length == 0) {
                return 0;
            }

            Arrays.sort(arrs[i]);
            queue.offer(new Pair(i, 0));
        }

        int lastValue = 0, count = 0;
        int intersection = 0;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            if (arrs[pair.row][pair.col] != lastValue || count == 0) {
                if (count == arrs.length) {
                    System.out.println(lastValue);
                    intersection++;
                }
                lastValue = arrs[pair.row][pair.col];
                count = 1;
            } else {
                count++;
            }

            pair.col++;
            if (pair.col < arrs[pair.row].length) {
                queue.offer(pair);
            }
        }

        // kickoff the last number
        if (count == arrs.length) {
            System.out.println(lastValue);
            intersection++;
        }

        return intersection;
    }
}
