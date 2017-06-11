package datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 9/15/2016.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
