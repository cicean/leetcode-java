package Google;

import datastructure.DirectedGraphNode;
import datastructure.UndirectedGraphNode;

import java.util.HashMap;

/**
 * Created by cicean on 9/15/2016.
 * deep copyһ������ͼ������ͼ�������Ǵ�valueС��nodeָ��value���
 */
public class DeepCloneGraph {

    private HashMap<UndirectedGraphNode, DirectedGraphNode> map = new HashMap<>();

    public DirectedGraphNode deepCloneGraph(UndirectedGraphNode node) {
        return deepClone(node);
    }

    private DirectedGraphNode deepClone(UndirectedGraphNode node) {
        if (node == null) return null;

        if (map.containsKey(node)) {
            return map.get(node);
        }

        DirectedGraphNode clone = new DirectedGraphNode(node.label);
        map.put(node, clone);
        for (UndirectedGraphNode neighbor: node.neighbors) {
            if (node.label < neighbor.label) {
                clone.neighbors.add(deepClone(neighbor));
            }
        }

        return clone;
    }
}
