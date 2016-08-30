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
According to the definition of tree on Wikipedia: ��a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.��
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together inedges.

As suggested by the hint, just check for cycle and connectedness in the graph. 
Both of these can be done via DFS.
�ܽᣬ�ص���������

Connected �� go through boolean[] visited, check whether there is some nodes which have final state as false
No cycle �� boolean[] visited
Another point is that after you push the neighbor, you need to remove the node from the neighbor.

Ӧ��Union Find�㷨�ĺ����ӣ���Union Find�㷨����Ϥ���ɲο� 
http://blog.csdn.net/dm_vincent/article/details/7655764 
http://blog.csdn.net/dm_vincent/article/details/7769159 
���˾��øò��Ĳ����ǳ�ͨ���׶����ڶ��������ǲ����ٵļ���Ӧ�����ӡ� 
����������ʵ���˲�ͬ�Ż��̶ȵ�union find�㷨��������ʱ��ɿ����Ż��Ǻ���Ч���ġ� 

�ڶ����ִ��������ý̲��е�UnionFind�㷨ʵ�֣�ʵ���а���·��ѹ���Ͱ���С�Լ�����(rank)��˼�룬�ҽ���Ҫһ������s[],��ʼʱs[i] = -1, �ںϲ������и���s[]ֵ�Ǹø�����Ӧ����size���෴�������Ǹ��ڵ��s[]ָ���丸�ڵ㡣ÿ��findһ���ڵ�p�Ὣp�����ϵ����нڵ�ĸ��ڵ����Ϊ�����Ӷ���Чѹ��·��������unionFind������ʱ����Ҫ���޸�union��union��������ֵ��Ϊ�������ͣ�unionʱ�������������ڵ�����ͬһ�����У���root��ͬ��˵�������߻�ʹ�����в���һ��cycle������false��˵�����벢��valid tree��ȫ����˳��union����������˵��������valid tree����֤union�����ҽ���һ��������ȷ��������valid tree�� 
�Ż���union���԰���СҲ���԰��߶Ⱥϲ�����Ϊ·��ѹ�����̻�ı����ĸ߶ȣ��������밴�߶��󲢲�����ȫ���ݣ������ᵽĿǰ���������������������Ч���¼���߶ȣ����ǲ�ȥ���㣬����ÿ�����洢�ĸ߶��ǹ��Ƶĸ߶ȣ�rank����������֤�������󲢺Ͱ���С��Ч����һ���ģ��Ҹ߶ȵĸ��²����С����Ƶ���� 
 */
public class GraphValidTree {
	
	/**
	 * ������֤��Ч��ͼ����һ���⡣�޷Ǿ��Ǽ��ַ���������DFS��ע�����и���������һ��child����������parent, ��ƽʱ���������һ��ĵĲ�𣬵�Ȼ���������������ͼ�����ÿ����ⷽ�档
	 * �����м���Ҫע��ĵط�������һ����ֻ����һ��root�������������ڶ��root, �ǿ϶���Ч��
	 * 
���������������ͼ����ô������ʵ���Դ�����һ���㿪ʼDFS�����ñ����root����Ϊ����ͼ������һ�㶼���Ա��������鲿�֡����edge��ʱ������������Ϳ����ˣ�������ǻ��и�ע��ĵط���������ڱ���neighbors��ʱ��
�Լ�parent��Ҫ����������ֱ�ӷ���false��Ϊ�л��ˡ�
�ܽ�һ����ǣ���������ͼÿ�������һ�ξͿ����ˣ��������ζ���л���
���Ӷ�
time: O(V + E), space: O(h)

���븴�Ӷ�
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
	        
	        // ����Ƿ���ڶ��root, ������ζ��ͼ��Ч
	        for (int i = 0; i < n; i++) {
	            if (!visited[i]) {
	                return false;
	            }
	        }

	        return true;
	    }

	    private boolean dfs(List<Integer>[] graph, int i, boolean[] visited, int prev) {
	        // ���ֻ�
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
        int pId = find(p), qId = find(q);// �ر�ע�����ѭ���ȱ���ԭʼֵ��ѭ������id[p]�ᱻ����  
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
