package datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 9/15/2016.
 *
 */
public class DirectedGraphNode {

    public int label;
    public List<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int val) {
        this.label = val;
        neighbors = new ArrayList<>();
    }
}
