package TripAdviser;

import java.util.*;

/**
 * Created by cicean on 9/30/2018.
 */
public class IntersectionOfArrayswithNthsmallestElement {
    class Pair {
        public int row, col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    /**
     * @param arrs: the arrays
     * @return: the number of the intersection of the arrays
     */
    public int intersectionOfArrays(int[][] arrs, int N) {
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
                    if (intersection == N) {
                        return lastValue;
                    }
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

        return intersection == N ? lastValue : -1;
    }

    public int commonNthsmallest(int[][] arrs, int N) {
        if (arrs == null || arrs.length == 0
                || arrs[0].length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new PriorityQueue<>(3, (a,b) -> b - a);
        for (int[] a : arrs) {
            for(int i : a) {
                if (map.getOrDefault(i, 0) + 1 == arrs.length) {
                    queue.offer(i);
                    if (queue.size() > N){
                        queue.poll();
                    }
                }
                map.put(i, map.getOrDefault(i,0) + 1);
            }
        }

        return queue.peek();
    }
}
