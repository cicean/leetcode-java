package Pinterest;

import java.util.*;

/**
 * Created by cicean on 9/3/2018.
 */
public class GraphCommonAncestor {

    public boolean hasCommonAncestor(int[][] edges, int i, int j) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return false;
        }

        Map<Integer, Set<Integer>> graphChildToParent = new HashMap<>();
        Map<Integer, Set<Integer>> graphParentToChild = new HashMap<>();
        for (int[] edge : edges) {
            graphChildToParent.computeIfAbsent(edge[1], x->new HashSet<Integer>()).add(edge[0]);
            graphChildToParent.computeIfAbsent(edge[0], x->new HashSet<Integer>()).add(edge[1]);
        }

        Queue<Integer> parent = new LinkedList<>();
        Set<Integer> parentSet_i = graphChildToParent.get(i);
        parent.addAll(graphChildToParent.get(i));

        while (!parent.isEmpty()) {
            int size = parent.size();
            for (int k = 0; k < size; k++) {
                Integer p = parent.poll();
                Queue<Integer> chilren = new LinkedList<>();
                for (Integer x : graphParentToChild.get(p)) {
                    if (x != i) {
                        chilren.offer(x);
                    }
                }
                while (!chilren.isEmpty()) {
                    int size2 = parent.size();
                    for (int m = 0; m < size2; m++) {
                        Integer c = chilren.poll();
                        if (c == j) {
                            return true;
                        } else {
                            chilren.addAll(graphParentToChild.get(c));
                        }
                    }

                }

                if (graphChildToParent.containsKey(p)) {
                    parent.addAll(graphChildToParent.get(p));
                }
            }
        }



        return false;
    }
}
