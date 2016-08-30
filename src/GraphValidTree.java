import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 261	Graph Valid Tree 	24.6%	Medium
 * @author cicean
 * Problem Description:

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together inedges.

As suggested by the hint, just check for cycle and connectedness in the graph. 
Both of these can be done via DFS.
总结，重点由两个：

Connected — go through boolean[] visited, check whether there is some nodes which have final state as false
No cycle — boolean[] visited
Another point is that after you push the neighbor, you need to remove the node from the neighbor.

应用Union Find算法的好例子，对Union Find算法不熟悉，可参看 
http://blog.csdn.net/dm_vincent/article/details/7655764 
http://blog.csdn.net/dm_vincent/article/details/7769159 
个人觉得该博文阐述非常通俗易懂，第二个链接是博主举的几个应用例子。 
对这题依次实现了不同优化程度的union find算法，从运行时间可看出优化是很有效果的。 

第二部分代码是利用教材中的UnionFind算法实现，实现中包含路径压缩和按大小以及按秩(rank)求并思想，且仅需要一个数组s[],初始时s[i] = -1, 在合并过程中根的s[]值是该根所对应树的size的相反数，而非根节点的s[]指向其父节点。每次find一个节点p会将p到根上的所有节点的父节点均置为根，从而高效压缩路径。利用unionFind求解该题时，需要稍修改union，union函数返回值改为布尔类型，union时，若发现两个节点已在同一颗树中，即root相同，说明该条边会使得树中产生一个cycle，返回false，说明输入并非valid tree。全部边顺利union结束还不能说明输入是valid tree，验证union后有且仅有一个根方能确认输入是valid tree。 
优化的union可以按大小也可以按高度合并，因为路径压缩过程会改变树的高度，所以其与按高度求并并不完全兼容，书中提到目前并不清楚这种情况下如何有效重新计算高度，答案是不去计算，就让每棵树存储的高度是估计的高度（rank），理论上证明按秩求并和按大小求并效率是一样的，且高度的更新不如大小更新频繁。 
 */
public class GraphValidTree {
	
	/**
	 * 这种验证有效的图都是一类题。无非就是几种方法，比如DFS。注意树有个特征就是一个child不能有两个parent, 根平时拓扑排序找环的的差别，当然由于这道题是无向图，不用考虑这方面。
	 * 这题有几个要注意的地方，比如一个树只能有一个root，如果这个树存在多个root, 那肯定无效。
	 * 
由于这道题是无向图，那么我们其实可以从任意一个点开始DFS，不用必须从root，因为无向图从任意一点都可以遍历到整块部分。添加edge的时候两个方向都添就可以了，另外就是还有个注意的地方就是如果在遍历neighbors的时候，
自己parent不要遍历，否则直接返回false以为有环了。
总结一句就是，这种无向图每个点遍历一次就可以了，否则就意味着有环。
复杂度
time: O(V + E), space: O(h)

代码复杂度
time: O(V + E), space: O(h)

	 * @param n
	 * @param edges
	 * @return
	 */
	
	public class Solution {
	     public boolean validTree(int n, int[][] edges) {
	        List<Integer>[] graph = new List[n];

	        for (int i = 0; i < n; i++) {
	            graph[i] = new ArrayList<Integer>();
	        }

	        for (int i = 0; i < edges.length; i++) {
	            graph[edges[i][0]].add(edges[i][1]);
	            graph[edges[i][1]].add(edges[i][0]);
	        }

	        boolean[] visited = new boolean[n];
	        if (!dfs(graph, 0, visited, -1)) {
	            return false;
	        }
	        
	        // 检测是否存在多个root, 如是意味着图无效
	        for (int i = 0; i < n; i++) {
	            if (!visited[i]) {
	                return false;
	            }
	        }

	        return true;
	    }

	    private boolean dfs(List<Integer>[] graph, int i, boolean[] visited, int prev) {
	        // 发现环
	        if (visited[i]) {
	            return false;
	        }
	        visited[i] = true;

	        for (int num : graph[i]) {
	            if (prev != num && !dfs(graph, num, visited, i)) {
	                return false;
	            }
	        }

	        return true;
	    }
	}
for 
	
	public boolean validTree(int n, int[][] edges) {
		if (n < 1)  return false;
		if (edges == null || edges.length == 0 || edges[0].length == 0) {
			if (n == 1) {
				return true;
			} else {
				return false;
			}
		}
		
		List<Set<Integer>> graph =  new ArrayList<Set<Integer>>();
		for (int i = 0; i < n; i++) {
			graph.add(new HashSet<Integer>());
		}
		
		for (int[] e:edges) {
			graph.get(e[0]).add(e[1]);
			graph.get(e[1]).add(e[0]);
		}
		
		boolean[] visited = new boolean[n];
		LinkedList<Integer> stack = new LinkedList<Integer>();
		stack.push(0);
		while (!stack.isEmpty()) {
			int node = stack.pop();
			if (visited[node]) {
				return false;
			}
			
			visited[node] = true;
			for (int nb: graph.get(node)){
				stack.push(nb);
				graph.get(nb).remove(node);
			}
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	
	 // http://blog.csdn.net/dm_vincent/article/details/7655764  
    int count = 0;  
    int[] id = null;  
    int[] size = null;  
    public boolean validTree_1(int n, int[][] edges) {  
        initUnionFind(n);  
        for (int i = 0; i < edges.length; i++) {  
            int p = edges[i][0], q = edges[i][1];  
            if (find(p) == find(q))  
                return false;  
            union(p, q);  
        }  
        return count == 1 ? true : false;  
    }  
    public void initUnionFind(int n) {  
        id = new int[n];  
        size = new int[n];  
        for (int i = 0; i < n; i++) {  
            id[i] = i;  
            size[i] = 1;  
        }  
        count = n;  
    }  
    //version 4: weighted quick union with path compression, find & union, very close to O(1)  
    public int find(int p) {  
        while (p != id[p]) {  
            id[p] = id[id[p]];  
            p = id[p];  
        }  
        return p;  
    }  
    public void union(int p, int q) { //same with version 3  
        int pRoot = find(p), qRoot = find(q);  
        if (size[pRoot] < size[qRoot]) {  
            id[pRoot] = qRoot;  
            size[qRoot] += size[pRoot];  
        } else {  
            id[qRoot] = pRoot;  
            size[pRoot] += size[qRoot];  
        }  
        count--;  
    }  
    //version 3: weighted quick union, find & union, O(logn)  
    public int find3(int p) { // same with version 2  
        while (p != id[p]) p = id[p];  
        return p;  
    }  
    public void union3(int p, int q) {  
        int pRoot = find(p), qRoot = find(q);  
        if (size[pRoot] < size[qRoot]) {  
            id[pRoot] = qRoot;  
            size[qRoot] += size[pRoot];  
        } else {  
            id[qRoot] = pRoot;  
            size[pRoot] += size[qRoot];  
        }  
        count--;  
    }  
    // version 2: quick union, find & union, O(tree height)  
    public int find2(int p) {  
        while (p != id[p]) p = id[p];  
        return p;  
    }  
    public void union2(int p, int q) {  
        int pRoot = find(p), qRoot = find(q);  
        id[pRoot] = qRoot;  
        count--;  
    }  
    // version 1: quick find, find O(1), union O(n)  
    public int find1(int p) {  
        return id[p];  
    }  
    public void union1(int p, int q) {  
        int pId = find(p), qId = find(q);// 特别注意进入循环先保存原始值，循环过程id[p]会被更改  
        for (int i = 0; i < id.length; i++) {  
            if (id[i] == pId)  
                id[i] = qId;  
        }  
        count--;  
    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
