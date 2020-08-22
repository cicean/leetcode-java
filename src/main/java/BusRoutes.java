/**
 * 815. Bus Routes
 * Hard
 *
 * 645
 *
 * 21
 *
 * Add to List
 *
 * Share
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 *
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 *
 * Example:
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *
 *
 * Constraints:
 *
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5.
 * 0 <= routes[i][j] < 10 ^ 6.
 * Accepted
 * 32,514
 * Submissions
 * 77,659
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 4
 *
 * Amazon
 * |
 * 2
 */
public class BusRoutes {

    /**
     * Approach #1: Breadth First Search [Accepted]
     * Intuition
     *
     * Instead of thinking of the stops as nodes (of a graph), think of the buses as nodes. We want to take the least number of buses, which is a shortest path problem, conducive to using a breadth-first search.
     *
     * Algorithm
     *
     * We perform a breadth first search on bus numbers. When we start at S, originally we might be able to board many buses, and if we end at T we may have many targets for our goal state.
     *
     * One difficulty is to efficiently decide whether two buses are connected by an edge. They are connected if they share at least one bus stop. Whether two lists share a common value can be done by set intersection (HashSet), or by sorting each list and using a two pointer approach.
     *
     * To make our search easy, we will annotate the depth of each node: info[0] = node, info[1] = depth.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: Let NN denote the number of buses, and b_ib
     * i
     * ​
     *   be the number of stops on the iith bus.
     *
     * To create the graph, in Python we do O(\sum (N - i) b_i)O(∑(N−i)b
     * i
     * ​
     *  ) work (we can improve this by checking for which of r1, r2 is smaller), while in Java we did a O(\sum b_i \log b_i)O(∑b
     * i
     * ​
     *  logb
     * i
     * ​
     *  ) sorting step, plus our searches are O(N \sum b_i)O(N∑b
     * i
     * ​
     *  ) work.
     *
     * Our (breadth-first) search is on NN nodes, and each node could have NN edges, so it is O(N^2)O(N
     * 2
     *  ).
     *
     * Space Complexity: O(N^2 + \sum b_i)O(N
     * 2
     *  +∑b
     * i
     * ​
     *  ) additional space complexity, the size of graph and routes. In Java, our space complexity is O(N^2)O(N
     * 2
     *  ) because we do not have an equivalent of routes. Dual-pivot quicksort (as used in Arrays.sort(int[])) is an in-place algorithm, so in Java we did not increase our space complexity by sorting.
     *
     * Analysis written by: @awice.
     */

    import java.awt.Point;

    class Solution {
        public int numBusesToDestination(int[][] routes, int S, int T) {
            if (S==T) return 0;
            int N = routes.length;

            List<List<Integer>> graph = new ArrayList();
            for (int i = 0; i < N; ++i) {
                Arrays.sort(routes[i]);
                graph.add(new ArrayList());
            }
            Set<Integer> seen = new HashSet();
            Set<Integer> targets = new HashSet();
            Queue<Point> queue = new ArrayDeque();

            // Build the graph.  Two buses are connected if
            // they share at least one bus stop.
            for (int i = 0; i < N; ++i)
                for (int j = i+1; j < N; ++j)
                    if (intersect(routes[i], routes[j])) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }

            // Initialize seen, queue, targets.
            // seen represents whether a node has ever been enqueued to queue.
            // queue handles our breadth first search.
            // targets is the set of goal states we have.
            for (int i = 0; i < N; ++i) {
                if (Arrays.binarySearch(routes[i], S) >= 0) {
                    seen.add(i);
                    queue.offer(new Point(i, 0));
                }
                if (Arrays.binarySearch(routes[i], T) >= 0)
                    targets.add(i);
            }

            while (!queue.isEmpty()) {
                Point info = queue.poll();
                int node = info.x, depth = info.y;
                if (targets.contains(node)) return depth+1;
                for (Integer nei: graph.get(node)) {
                    if (!seen.contains(nei)) {
                        seen.add(nei);
                        queue.offer(new Point(nei, depth+1));
                    }
                }
            }

            return -1;
        }

        public boolean intersect(int[] A, int[] B) {
            int i = 0, j = 0;
            while (i < A.length && j < B.length) {
                if (A[i] == B[j]) return true;
                if (A[i] < B[j]) i++; else j++;
            }
            return false;
        }
    }

    class Solution {
        public int numBusesToDestination(int[][] routes, int S, int T) {
            int n = routes.length;
            HashMap<Integer, HashSet<Integer>> to_routes = new HashMap<>();
            for (int i = 0; i < routes.length; ++i) {
                for (int j : routes[i]) {
                    if (!to_routes.containsKey(j))
                        to_routes.put(j, new HashSet<Integer>());
                    to_routes.get(j).add(i);
                }
            }
            Queue<int[]> bfs = new ArrayDeque();
            bfs.offer(new int[] {S, 0});
            HashSet<Integer> seen = new HashSet<>();
            seen.add(S);
            boolean[] seen_routes = new boolean[n];
            while (!bfs.isEmpty()) {
                int stop = bfs.peek()[0], bus = bfs.peek()[1];
                bfs.poll();
                if (stop == T) return bus;
                for (int i : to_routes.get(stop)) {
                    if (seen_routes[i]) continue;
                    for (int j : routes[i]) {
                        if (!seen.contains(j)) {
                            seen.add(j);
                            bfs.offer(new int[] {j, bus + 1});
                        }
                    }
                    seen_routes[i] = true;
                }
            }
            return -1;
        }
    }


    class Solution {
        public int numBusesToDestination(int[][] routes, int S, int T) {
            //first find max number of stops
            int max=0;
            for(int [] stops: routes)
            {
                for(int stop: stops)
                    max = Math.max(max, stop);
            }

            int[] busCount = new int [ max+1];
            Arrays.fill(busCount, max);
            busCount[S]=0;
            boolean update=true;
            while(update)
            {
                update=false;
                //BFS
                for(int[] route: routes)
                {

                    int min = max;
                    for(int stop : route)
                    {
                        min = Math.min(min, busCount[stop]);
                    }

                    min++;
                    for(int stop:route)
                    {
                        if(busCount[stop]>min)
                        {
                            busCount[stop]=min;
                            update=true;
                        }
                    }
                }

            }

            return busCount[T]==max?-1:busCount[T];
        }


    }
}
