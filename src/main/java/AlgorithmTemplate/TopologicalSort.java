package AlgorithmTemplate;

import datastructure.DirectedGraphNode;

import java.util.*;

public class TopologicalSort {

    class Solution {
        /**
         * @param graph: A list of Directed graph node
         * @return: Any topological order for the given graph.
         */
        public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            // write your code here
            ArrayList<DirectedGraphNode> result = new ArrayList<>();
            HashMap<DirectedGraphNode, Integer> map = new HashMap();
            for (DirectedGraphNode node : graph) {
                for (DirectedGraphNode neighbor : node.neighbors) {
                    if (map.containsKey(neighbor)) {
                        map.put(neighbor, map.get(neighbor) + 1);
                    } else {
                        map.put(neighbor, 1);
                    }
                }
            }
            Queue<DirectedGraphNode> q = new LinkedList<>();
            for (DirectedGraphNode node : graph) {
                if (!map.containsKey(node)) {
                    q.offer(node);
                    result.add(node);
                }
            }
            while (!q.isEmpty()) {
                DirectedGraphNode node = q.poll();
                for (DirectedGraphNode n : node.neighbors) {
                    map.put(n, map.get(n) - 1);
                    if (map.get(n) == 0) {
                        result.add(n);
                        q.offer(n);
                    }
                }
            }
            return result;
        }
    }
}
