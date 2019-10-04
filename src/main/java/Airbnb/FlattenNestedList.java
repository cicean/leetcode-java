package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/13/2018.
 */
public class FlattenNestedList {

    public List<Integer> getPreference(List<List<Integer>> preferences) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (List<Integer> l : preferences) {
            for (int i = 0; i < l.size() - 1; i++) {
                int from = l.get(i);
                int to = l.get(i + 1);
                if (!nodes.containsKey(from)) {
                    inDegree.put(from, 0);
                    nodes.put(from, new HashSet<>());
                }
                if (!nodes.containsKey(to)) {
                    inDegree.put(to, 0);
                    nodes.put(to, new HashSet<>());
                }
                if (!nodes.get(from).contains(to)) {
                    Set<Integer> s = nodes.get(from);
                    s.add(to);
                    nodes.put(from, s);
                }
                inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int k : inDegree.keySet()) {
            if (inDegree.get(k) == 0) {
                q.offer(k);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int id = q.poll();
            res.add(id);
            Set<Integer> neighbors = nodes.get(id);
            for (int next : neighbors) {
                int degree = inDegree.get(next) - 1;
                inDegree.put(next, degree);
                if (degree == 0) q.offer(next);
            }
        }
        return res;
    }
}
