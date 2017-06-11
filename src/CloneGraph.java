
import datastructure.PrintGraph;
import datastructure.UndirectedGraphNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
 133	Clone Graph	24.0%	Medium
 Problem:    Clone Graph
 Difficulty: Medium
 Source:     http://oj.leetcode.com/problems/clone-graph/
 Notes:
 Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

 OJ's undirected graph serialization:
 Nodes are labeled from 0 to N - 1, where N is the total nodes in the graph.
 We use # as a separator for each node, and , as a separator for each neighbor of the node.
 As an example, consider the serialized graph {1,2#2#2}.
 The graph has a total of three nodes, and therefore contains three parts as separated by #.
 Connect node 0 to both nodes 1 and 2.
 Connect node 1 to node 2.
 Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:
 1
 / \
 /   \
 0 --- 2
 / \
 \_/
 Solution: 1. DFS. 2. BFS.
 */

/**
 * Definition for undirected graph. class UndirectedGraphNode { int label;
 * List<UndirectedGraphNode> neighbors; UndirectedGraphNode(int x) { label = x;
 * neighbors = new ArrayList<UndirectedGraphNode>(); } };
 */

public class CloneGraph {
	public UndirectedGraphNode cloneGraph_1(UndirectedGraphNode node) {
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		return cloneGraphRe(node, map);
	}

	public UndirectedGraphNode cloneGraphRe(UndirectedGraphNode node,
			HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
		if (node == null)
			return null;
		if (map.containsKey(node)) {
			return map.get(node);
		}
		UndirectedGraphNode newnode = new UndirectedGraphNode(node.label);
		map.put(node, newnode);
		for (UndirectedGraphNode cur : node.neighbors) {
			newnode.neighbors.add(cloneGraphRe(cur, map));
		}
		return newnode;
	}

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		if (node == null)
			return null;
		queue.offer(node);
		map.put(node, new UndirectedGraphNode(node.label));
		while (queue.isEmpty() == false) {
			UndirectedGraphNode cur = queue.poll();
			for (UndirectedGraphNode neighbor : cur.neighbors) {
				if (map.containsKey(neighbor) == false) {
					UndirectedGraphNode newnode = new UndirectedGraphNode(
							neighbor.label);
					map.put(neighbor, newnode);
					queue.offer(neighbor);
				}
				map.get(cur).neighbors.add(map.get(neighbor));
			}
		}
		return map.get(node);
	}



	public class Solution {
		private HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

		public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
			return deepClone(node);
		}

		private UndirectedGraphNode deepClone(UndirectedGraphNode node) {
			if (node == null)
				return null;

			if (map.containsKey(node)) {
				return map.get(node);
			}

			UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
			map.put(node, clone);
			for (UndirectedGraphNode neighbor : node.neighbors) {

				clone.neighbors.add(deepClone(neighbor));

			}

			return clone;
		}
	}
	
	private HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
	
	public UndirectedGraphNode deepCloneGraph(UndirectedGraphNode node) {
        return deepClone(node);
    }

    private UndirectedGraphNode deepClone(UndirectedGraphNode node) {
        if (node == null) return null;

        if (map.containsKey(node)) {
            return map.get(node);
        }

        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node, clone);
        for (UndirectedGraphNode neighbor: node.neighbors) {
            if (node.label < neighbor.label) {
                clone.neighbors.add(deepClone(neighbor));
            }
        }

        return clone;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UndirectedGraphNode a = new UndirectedGraphNode(0);
		UndirectedGraphNode b = new UndirectedGraphNode(1);
		UndirectedGraphNode c = new UndirectedGraphNode(2);
		UndirectedGraphNode d = new UndirectedGraphNode(3);
		UndirectedGraphNode e = new UndirectedGraphNode(4);
		UndirectedGraphNode f = new UndirectedGraphNode(5);

		a.neighbors.add(b);
		a.neighbors.add(c);
		a.neighbors.add(d);
		b.neighbors.add(c);
		b.neighbors.add(e);
		c.neighbors.add(d);
		c.neighbors.add(e);
		c.neighbors.add(f);
		d.neighbors.add(f);
		e.neighbors.add(f);

		CloneGraph slt = new CloneGraph();
		// print(a);
		//UndirectedGraphNode res = slt.cloneGraph(a);
		PrintGraph print = new PrintGraph();
		print.printGraph(slt.cloneGraph(a));;
		print.printGraph(slt.deepCloneGraph(a));;

	}

}
