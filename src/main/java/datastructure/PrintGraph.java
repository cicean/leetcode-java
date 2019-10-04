package datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 9/15/2016.
 */
public class PrintGraph {

    public void printGraph(UndirectedGraphNode res) {
        if (null == res)
            return;
        List<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
        int index = 0;
        if (null != res)
            nodes.add(res);
        while (index != nodes.size()) {
            UndirectedGraphNode n = nodes.get(index);
            System.out.print(n.label + "(");
            for (UndirectedGraphNode neighbor : n.neighbors) {
                if (!(nodes.contains(neighbor))) {
                    nodes.add(neighbor);
                }
                System.out.print(neighbor.label + ",");
            }
            System.out.println(")");
            index = index + 1;
        }
    }
}
