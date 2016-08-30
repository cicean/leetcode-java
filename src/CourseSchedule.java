import java.util.*;

/*
 * 207	Course Schedule	21.8%	Medium
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*
* solution 
* 1.1 BFS 
* 1.2 DFS
 */
public class CourseSchedule {

	public boolean canFinish_1(int numCourses, int[][] prerequisites) {
	    if(prerequisites == null){
	        throw new IllegalArgumentException("illegal prerequisites array");
	    }
	 
	    int len = prerequisites.length;
	 
	    if(numCourses == 0 || len == 0){
	        return true;
	    }
	 
	    // counter for number of prerequisites
	    int[] pCounter = new int[numCourses];
	    for(int i=0; i<len; i++){
	        pCounter[prerequisites[i][0]]++;
	    }
	 
	    //store courses that have no prerequisites
	    LinkedList<Integer> queue = new LinkedList<Integer>();
	    for(int i=0; i<numCourses; i++){
	        if(pCounter[i]==0){
	            queue.add(i);
	        }
	    }
	 
	    // number of courses that have no prerequisites
	    int numNoPre = queue.size();
	 
	    while(!queue.isEmpty()){
	        int top = queue.remove();
	        for(int i=0; i<len; i++){
	            // if a course's prerequisite can be satisfied by a course in queue
	            if(prerequisites[i][1]==top){
	                pCounter[prerequisites[i][0]]--;
	                if(pCounter[prerequisites[i][0]]==0){
	                    numNoPre++;
	                    queue.add(prerequisites[i][0]);
	                }
	            }
	        }
	    }
	 
	    return numNoPre == numCourses;
	}
	
	public boolean canFinish_2(int numCourses, int[][] prerequisites) {
	    if(prerequisites == null){
	        throw new IllegalArgumentException("illegal prerequisites array");
	    }
	 
	    int len = prerequisites.length;
	 
	    if(numCourses == 0 || len == 0){
	        return true;
	    }
	 
	    //track visited courses
	    int[] visit = new int[numCourses];
	 
	    // use the map to store what courses depend on a course 
	    HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
	    for(int[] a: prerequisites){
	        if(map.containsKey(a[1])){
	            map.get(a[1]).add(a[0]);
	        }else{
	            ArrayList<Integer> l = new ArrayList<Integer>();
	            l.add(a[0]);
	            map.put(a[1], l);
	        }
	    }
	 
	    for(int i=0; i<numCourses; i++){
	        if(!canFinishDFS(map, visit, i))
	            return false;
	    }
	 
	    return true;
	}
	 
	private boolean canFinishDFS(HashMap<Integer,ArrayList<Integer>> map, int[] visit, int i){
	    if(visit[i]==-1) 
	        return false;
	    if(visit[i]==1) 
	        return true;
	 
	    visit[i]=-1;
	    if(map.containsKey(i)){
	        for(int j: map.get(i)){
	            if(!canFinishDFS(map, visit, j)) 
	                return false;
	        }
	    }
	 
	    visit[i]=1;
	 
	    return true;
	}
	
	public boolean canFinish_3(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> outNodes = new HashMap<Integer, List<Integer>>();
        int[] inDegree = new int[numCourses];
        Set<Integer> courses = new HashSet<Integer>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            courses.add(i);
            outNodes.put(i, new ArrayList<Integer>());
        }
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            inDegree[to]++;
            List<Integer> nodes = outNodes.get(from);
            nodes.add(to);
        }
        while (!courses.isEmpty()) {
            List<Integer> toRemoved = new ArrayList<Integer>();
            for (int course : courses) {
                if (inDegree[course] == 0) {
                    toRemoved.add(course);
                    for (int node : outNodes.get(course)) {
                        inDegree[node]--;
                    }
                }
            }
            if (toRemoved.isEmpty()) {
                return false;
            }
            courses.removeAll(toRemoved);
        }
        return true;
    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
