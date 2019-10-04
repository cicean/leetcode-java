import java.util.*;

/**
 * Created by cicean on 9/13/2018.
 * 787. Cheapest Flights Within K Stops
 * DescriptionHintsSubmissionsDiscussSolution
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 *
 *
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 * Note:
 *
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops {

    /**
     * Approach #1: Maintain Cheapest To Target [Accepted]
     Intuition and Algorithm

     Say pre[node] is the smallest distance to that node within T stops.
     Let's try to find the smallest distance dis[node] to that node within T+1 rounds.
     For every edge in flights that connects places u and v with cost w,
     the new distance would be dis[v] = min(dis[v], pre[u] + w).

     Actually, we'll use dis = dist[0] and pre = dist[1] initially.
     That will let us reuse the arrays dis = dist[1] and pre = dist[0] for the next iteration (i = 1) in our loop,
     and so on.
     Complexity Analysis

     Time Complexity: O(E * K), where E is the length of flights.

     Space Complexity: O(n), the space used to store dis and pre.
     */
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            int[][] dist = new int[2][n];
            int INF = Integer.MAX_VALUE / 2;
            Arrays.fill(dist[0], INF);
            Arrays.fill(dist[1], INF);
            dist[0][src] = dist[1][src] = 0;

            for (int i = 0; i <= K; ++i)
                for (int[] edge: flights)
                    dist[i&1][edge[1]] = Math.min(dist[i&1][edge[1]], dist[~i&1][edge[0]] + edge[2]);

            return dist[K&1][dst] < INF ? dist[K&1][dst] : -1;
        }
    }

    /**
     * Intuition

     Instead of nodes being places, use places and number of stops.
     We want to find the lowest cost from source to target, which makes Dijkstra's a good candidate algorithm.

     If we continually extend our potential flightpaths in order of cost,
     we know once we've reached the destination dst that it was the lowest cost way to get there.
     This is the idea behind Dijkstra's algorithm.

     Algorithm

     Dijkstra's algorithm uses a priority queue to continually search the next node with the lowest cost.

     If we've come to a node and it has a lower recorded cost or we've taken too many steps, we don't need to search it.
     If we reach our destination, because we are searching in order of lowest cost first, it must have the lowest cost.

     Otherwise, for every outbound flight from node that is better,
     we'll add it to our priority queue of things to search.

     Time Complexity: O(E + n \log n)O(E+nlogn), where EE is the total number of flights.

     Space Complexity: O(n)O(n), the size of the heap.

     */


    class Solution_dijkstra {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            int[][] graph = new int[n][n];
            for (int[] flight: flights)
                graph[flight[0]][flight[1]] = flight[2];

            Map<Integer, Integer> best = new HashMap();

            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
            pq.offer(new int[]{0, 0, src});

            while (!pq.isEmpty()) {
                int[] info = pq.poll();
                int cost = info[0], k = info[1], place = info[2];
                if (k > K+1 || cost > best.getOrDefault(k * 1000 + place, Integer.MAX_VALUE))
                    continue;
                if (place == dst)
                    return cost;

                for (int nei = 0; nei < n; ++nei) if (graph[place][nei] > 0) {
                    int newcost = cost + graph[place][nei];
                    if (newcost < best.getOrDefault((k+1) * 1000 + nei, Integer.MAX_VALUE)) {
                        pq.offer(new int[]{newcost, k+1, nei});
                        best.put((k+1) * 1000 + nei, newcost);
                    }
                }
            }

            return -1;
        }
    }
}
