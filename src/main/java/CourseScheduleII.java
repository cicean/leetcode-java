import java.util.*;

/*
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 *
 *Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
 *
 *相比较于 【LeetCode】Course Schedule 解题报告，返回的结果是一组排序结果，而非是否能够排序。
 *因为只要返回任意一种合法的拓扑排序结果即可，所以只需在BFS过程中记录下来先后访问的节点即可。
 *
 */
public class CourseScheduleII {
	
/**
 * 复杂度
	时间 O(N) 空间 O(N)
	思路
	和I不同的是，这题要返回路径。其实也没有什么不同的，不过是多加一个数组，记录广度优先搜索的遍历顺序就行了。
	注意
	当没有先修课的时候，随便返回一个顺序就行了，所以初始化的时候就初始化为升序序列，方便后面直接返回。
 * @param numCourses
 * @param prerequisites
 * @return
 */
	
	public class Solution {
	    public int[] findOrder(int numCourses, int[][] prerequisites) {
	        int[] res = new int[numCourses];
	        ArrayList<Integer>[] graph = new ArrayList[numCourses];
	        int[] indegree = new int[numCourses];
	        for(int i = 0; i < prerequisites.length; i++){
	            if(graph[prerequisites[i][1]] == null){
	                graph[prerequisites[i][1]] = new ArrayList<Integer>();
	            }
	            graph[prerequisites[i][1]].add(prerequisites[i][0]);
	            indegree[prerequisites[i][0]]++;
	        }
	        Queue<Integer> queue = new LinkedList<Integer>();
	        for(int i = 0; i < indegree.length; i++){
	            if(indegree[i] == 0){
	                queue.add(i);
	            }
	        }
	        
	        // 用idx记录输出数组的下标
	        int idx = 0;
	        while(!queue.isEmpty()){
	            Integer curr = queue.poll();
	            res[idx++] = curr;
	            if(graph[curr] == null) continue;
	            for(Integer next : graph[curr]){
	                if(--indegree[next] == 0){
	                    queue.offer(next); 
	                }
	            }
	        }
	        // 如果有环则返回空数组
	        return idx != numCourses ? new int[0] : res;
	    }
	}

	public int[] findOrder_1(int numCourses, int[][] prerequisites) {
    int re[] = new int[numCourses];
    int m = prerequisites.length;  
    int count[] = new int[numCourses];
    LinkedList<Integer> stack = new LinkedList<Integer>();
    int p = 0;
    for (int i = 0; i < m; i++) {
            count[prerequisites[i][0]]++;
    }
    for (int i = 0; i < numCourses; i++) {
            if (count[i] == 0) {
                    stack.push(i);
                    re[p++] = i;
            }
    }
    while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int i = 0; i < m; i++) {
                    if (prerequisites[i][1] == cur) { 
                            if (--count[prerequisites[i][0]] == 0) {
                                    stack.push(prerequisites[i][0]);
                                    re[p++] = prerequisites[i][0];
                                    }
                            
                    }
            }
    }
    return p < numCourses ? new int[0] : re;
}
	
	public int[] findOrder_2(int numCourses, int[][] prerequisites) {  
        List<Set<Integer>> adjLists = new ArrayList<Set<Integer>>();  
        for (int i = 0; i < numCourses; i++) {  
            adjLists.add(new HashSet<Integer>());  
        }  
          
        for (int i = 0; i < prerequisites.length; i++) {  
            adjLists.get(prerequisites[i][1]).add(prerequisites[i][0]);  
        }  
          
        int[] indegrees = new int[numCourses];  
        for (int i = 0; i < numCourses; i++) {  
            for (int x : adjLists.get(i)) {  
                indegrees[x]++;  
            }  
        }  
          
        Queue<Integer> queue = new LinkedList<Integer>();  
        for (int i = 0; i < numCourses; i++) {  
            if (indegrees[i] == 0) {  
                queue.offer(i);  
            }  
        }  
          
        int[] res = new int[numCourses];  
        int count = 0;  
        while (!queue.isEmpty()) {  
            int cur = queue.poll();  
            for (int x : adjLists.get(cur)) {  
                indegrees[x]--;  
                if (indegrees[x] == 0) {  
                    queue.offer(x);  
                }  
            }  
            res[count++] = cur;  
        }  
          
        if (count == numCourses) return res;  
        return new int[0];  
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
