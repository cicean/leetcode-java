/**
 * 1136. Parallel Courses
 * Hard
 *
 * 114
 *
 * 6
 *
 * Add to List
 *
 * Share
 * There are N courses, labelled from 1 to N.
 *
 * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.
 *
 * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 *
 * Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: N = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation:
 * In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
 * Example 2:
 *
 *
 *
 * Input: N = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation:
 * No course can be studied because they depend on each other.
 *
 *
 * Note:
 *
 * 1 <= N <= 5000
 * 1 <= relations.length <= 5000
 * relations[i][0] != relations[i][1]
 * There are no repeated relations in the input.
 * Accepted
 * 5,763
 * Submissions
 * 9,528
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ngoc_lam
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 *
 * Uber
 * |
 * 3
 * Try to think of it as a graph problem.
 * When will be impossible to study all the courses?
 * What if the directed graph has a cycle?
 * If you build a graph using the relations, what would be the graph type?
 * So the graph is a directed acyclic graph (DAG).
 * Imagine having a long path in the DAG, what can you say about the answer then?
 * How to find the longest path in a DAG?
 * We can use DP in order to solve this.
 */
public class ParallelCourses {
    public int minimumSemesters(int N, int[][] relations) {
        Map<Integer, List<Integer>> g = new HashMap<>(); // key: prerequisite, value: course list.
        int[] inDegree = new int[N + 1]; // inDegree[i]: number of prerequisites for i.
        for (int[] r : relations) {
            g.computeIfAbsent(r[0], l -> new ArrayList<>()).add(r[1]); // construct graph.
            ++inDegree[r[1]]; // count prerequisites for r[1].
        }
        Queue<Integer> q = new LinkedList<>(); // save current 0 in-degree vertices.
        for (int i = 1; i <= N; ++i)
            if (inDegree[i] == 0)
                q.offer(i);
        int semester = 0;
        while (!q.isEmpty()) { // BFS traverse all currently 0 in degree vertices.
            for (int sz = q.size(); sz > 0; --sz) { // sz is the search breadth.
                int c = q.poll();
                --N;
                if (!g.containsKey(c)) continue; // c's in-degree is currently 0, but it has no prerequisite.
                for (int course : g.remove(c))
                    if (--inDegree[course] == 0) // decrease the in-degree of course's neighbors.
                        q.offer(course); // add current 0 in-degree vertex into Queue.
            }
            ++semester; // need one more semester.
        }
        return N == 0 ? semester : -1;
    }


    /**
     * dp + dfs?
     */
    class Solution {
        public int minimumSemesters(int N, int[][] relations) {
            // -1 visiting, 0, not visited, 1 visited
            int[] visited = new int[N + 1];
            int[] depth = new int[N + 1];
            List<Integer>[] edges = new ArrayList[N + 1];
            for(int i = 1; i <= N; i++) {
                edges[i] = new ArrayList<>();
                depth[i] = 1;
                visited[i] = 0;
            }
            for(int[] r : relations) {
                edges[r[0]].add(r[1]);
            }
            for(int i = 1; i <= N; i++) {
                if(visited[i] == 0) {
                    boolean retVal = isCycle(i, visited, depth, edges);
                    if(retVal) {
                        return -1;
                    }
                }
            }
            int sems = 0;
            for(int i = 1; i <= N; i++) {
                sems = Math.max(sems, depth[i]);
            }
            return sems;
        }

        private boolean isCycle(int i, int[] visited, int[] depth, List<Integer>[] edges) {
            if(visited[i] == 1) {
                return false;
            }
            if(visited[i] == -1) {
                return true;
            }
            visited[i] = -1;
            for(int x : edges[i]) {
                boolean retVal = isCycle(x, visited, depth, edges);
                if(retVal) return true;
                depth[i] = Math.max(depth[i], 1 + depth[x]);
            }
            visited[i] = 1;
            return false;
        }
    }
}
