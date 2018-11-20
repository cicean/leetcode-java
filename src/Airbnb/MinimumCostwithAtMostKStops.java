package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/13/2018.
 */
public class MinimumCostwithAtMostKStops {

    public int minCost(List<String> lines, String source, String target, int k) {
        if (lines.size() == 0 || k < 0) {
            return 0;
        }
        Map<String, Flight> nodes = new HashMap<>();
        for (String line : lines) {
            String[] s = line.trim().split(",");
            String[] t = s[0].trim().split("->");
            String from = t[0];
            String to = t[1];
            int cost = Integer.valueOf(s[1].trim());
            if (!nodes.containsKey(from)) nodes.put(from, new Flight(from));
            if (!nodes.containsKey(to)) nodes.put(to, new Flight(to));
            nodes.get(from).nextNodes.put(to, cost);
        }
        boolean find = false;
        Queue<String> q = new LinkedList<>();
        Queue<Integer> cost = new LinkedList<>();
        q.offer(source);
        cost.offer(0);
        int stops = -1;
        while (!q.isEmpty()) {
            stops++;
            if (stops > k + 1) {
                break;
            }
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Flight curr = nodes.get(q.poll());
                int currCost = cost.poll();
                curr.minCost = Math.min(curr.minCost, currCost);
                if (curr.name.equals(target)) {
                    find = true;
                    continue;
                }
                for (String next : curr.nextNodes.keySet()) {
                    int nextCost = currCost + curr.nextNodes.get(next);
                    if (nextCost < nodes.get(next).minCost && (stops < k || stops
                            == k && next.equals(target))) {
                        q.offer(next);
                        cost.offer(nextCost);
                    }
                }
            }
        }
        return find ? nodes.get(target).minCost : -1;
    }
}
