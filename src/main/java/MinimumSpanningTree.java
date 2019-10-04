/**
 * Created by cicean on 8/8/2018.
 */

/**
 * Definition for a Connection.
 */
import java.util.*;

class Connection {
    public String city1, city2;
    public int cost;
    public Connection(String city1, String city2, int cost) {
        this.city1 = city1;
        this.city2 = city2;
        this.cost = cost;
    }
  }

public class MinimumSpanningTree {

    public List<Connection> lowestCost(List<Connection> connections) {
        // Write your code here
        if (connections == null || connections.size() == 0) {
            return new ArrayList<Connection>();
        }
        Map<String, Integer> hash = new HashMap<>();
        int n = 0;
        Collections.sort(connections, new Comparator<Connection>() {
            public int compare(Connection a, Connection b) {
                if (a.cost != b.cost) {
                    return a.cost - b.cost;
                }

                if (a.city1.equals(b.city1)) {
                    return a.city2.compareTo(b.city2);
                }

                return a.city1.compareTo(b.city1);
            }
        });

        for (Connection connection : connections) {
            hash.putIfAbsent(connection.city1, ++n);
            hash.putIfAbsent(connection.city2, ++n);
        }

        UnionFind uf = new UnionFind(n + 1);
        List<Connection> results = new ArrayList<>();

        for (Connection connection : connections) {
            int num1 = hash.get(connection.city1);
            int num2 = hash.get(connection.city2);
            if (uf.union(num1, num2)) {
                results.add(connection);
                System.out.println("connection");
            };

        }

        if (results.size() != n - 1) {
            return new ArrayList<Connection>();
        }

        return results;

    }

    class UnionFind {
        private int[] father;

        public UnionFind(int n) {
            this.father = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                father[i] = i;
            }
        }

        public int find (int x) {
            if (father[x] == x) {
                return x;
            }

            return find(father[x]);
        }

        public boolean union (int x, int y) {
            int root_x = find(x);
            int root_y = find(y);

            if (root_x != root_y) {
                father[root_x] = root_y;
                return true;
            }

            return false;
        }

    }
}
