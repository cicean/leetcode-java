package Uber;

import java.util.*;

/**
 * Created by cicean on 9/18/2018.
 */
public class CourseScheduleTopologicalSort {

    /**
     * �������ܹ��� n �ſ���Ҫѡ����Ϊ 0 �� n - 1.
     һЩ�γ�����֮ǰ��Ҫ���������һЩ�γ̣�����Ҫѧϰ�γ� 0 ����Ҫ��ѧϰ�γ� 1 ����ʾΪ[0,1]
     ����n�ſ��Լ����ǵ��Ⱦ��������ж��Ƿ����������пγ̣�
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Write your code here
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];

        for (int i = 0;i < numCourses; i++)
            edges[i] = new ArrayList<Integer>();

        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]] ++ ;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue queue = new LinkedList();
        for(int i = 0; i < degree.length; i++){
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()){
            int course = (int)queue.poll();
            count ++;
            int n = edges[course].size();
            for(int i = 0; i < n; i++){
                int pointer = (int)edges[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                }
            }
        }

        return count == numCourses;
    }

    /**
     * ����Ҫȥ��n�ž��µĿβ��ܻ��offer����Щ�α����Ϊ 0 �� n-1 ��
     ��һЩ�γ���Ҫ��ǰ�ÿγ̡������������Ҫ�Ͽγ�0������Ҫ��ѧ�γ�1��������һ��ƥ������ʾ���ǣ� [0,1]

     ����γ̵���������һЩǰ�ÿγ̵����󣬷�����Ϊ��ѧ�����пγ������ŵ�ѧϰ˳��

     ���ܻ��ж����ȷ��˳����ֻҪ����һ�־Ϳ����ˡ����������������пγ̣�����һ�������顣
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Write your code here
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];

        for (int i = 0;i < numCourses; i++)
            edges[i] = new ArrayList<Integer>();

        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]] ++ ;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue queue = new LinkedList();
        for(int i = 0; i < degree.length; i++){
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0;
        int[] order = new int[numCourses];
        while(!queue.isEmpty()){
            int course = (int)queue.poll();
            order[count] = course;
            count ++;
            int n = edges[course].size();
            for(int i = n - 1; i >= 0 ; i--){
                int pointer = (int)edges[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                }
            }
        }

        if (count == numCourses)
            return order;

        return new int[0];
    }

    /**
     * ������ n �Ų�ͬ�����Ͽγ�, ���Ϊ 1 �� n. ÿһ�ڿζ��г���ʱ��(�γ̳���) t �� �ڵ� d ��ر�. �γ�Ӧ�������� t �죬�����ڵ�d���֮ǰ���, �㽫�ӵ�һ�쿪ʼ.
     ���� n �����Ͽγ��� pairs (t, d) ����ʾ, ����������ҵ������ϵ���������Ŀγ���.
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        // write your code here
        if (courses == null || courses.length == 0) {
            return 0;
        }
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for (int i = 0; i < courses.length; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                queue.offer(courses[i][0]);
            }
            else if (!queue.isEmpty() && courses[i][0] < queue.peek()) {
                time += courses[i][0] - queue.poll();
                queue.offer(courses[i][0]);
            }
        }
        return queue.size();
    }

    /**
     * �ܹ���n���γ̣���0��n-1����Щ�γ̿������Ⱦ����������磬�����޿γ�0�����������һ�ſγ�1�������ſ�֮��Ĺ�ϵ��ʾΪ:[0,1]
     ���ǵ��γ̵��������Ⱦ������Ե��б���������Եõ����пγ̵Ĳ�ͬ������������
     */
    boolean[][] pre = null;
    int[] preCnt = null;
    boolean[] selected = null;
    int res = 0;
    int n;

    void dfs(int level) {
        if (level == n) {
            ++res;
            return;
        }

        int i, j;
        for (i = 0; i < n; ++i) {
            if (!selected[i] && preCnt[i] == 0) {
                selected[i] = true;
                for (j = 0; j < n; ++j) {
                    if (pre[i][j]) {
                        --preCnt[j];
                    }
                }

                dfs(level + 1);

                for (j = 0; j < n; ++j) {
                    if (pre[i][j]) {
                        ++preCnt[j];
                    }
                }
                selected[i] = false;
            }
        }
    }

    public int topologicalSortNumber(int nn, int[][] p) {
        n = nn;
        int i, j;
        // pre[i][j] = true if and only if course i needs to be taken before course j
        pre = new boolean[n][n];
        preCnt = new int[n];
        selected = new boolean[n];

        for (i = 0; i < n; ++i) {
            preCnt[i] = 0;
            selected[i] = false;
            for (j = 0; j < n; ++j) {
                pre[i][j] = false;
            }
        }

        for (i = 0; i < p.length; ++i) {
            pre[p[i][1]][p[i][0]] = true;
            ++preCnt[p[i][0]];
        }

        dfs(0);
        return res;
    }


    /**
     * build libary order
     * @param target
     * @param dependences
     * @return
     */
    public List<String> buildOrder(String target, Map<String, List<String>> dependences) {
        if (target == null || dependences == null) return null;

        Map<String, Integer> indegree = new HashMap<>();
        Map<String, List<String>> prebuildtotarget = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> uniquedependence = new HashSet<>();
        for (Map.Entry<String, List<String>> entry : dependences.entrySet()) {
            indegree.put(entry.getKey(), entry.getValue().size());
            uniquedependence.add(entry.getKey());
            for (String prebuilddependence : entry.getValue()) {
                prebuildtotarget.computeIfAbsent(prebuilddependence, x -> new ArrayList<String>()).add(entry.getKey());
                uniquedependence.add(prebuilddependence);
            }
        }

        for (String dep : uniquedependence) {
            if (!indegree.containsKey(dep)) {
                queue.offer(dep);
            }
        }

        //System.out.print("length of " + uniquedependence.size());
        List<String> results = new ArrayList<>();

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            results.add(curr);
            System.out.println("curr = " + curr);
            if (!prebuildtotarget.containsKey(curr)) {
                continue;
            }

            for (String next : prebuildtotarget.get(curr)) {
                if (indegree.get(next) - 1 == 0) {
                    queue.offer(next);
                }
                indegree.put(next, indegree.get(next) - 1);
            }


        }

        //System.out.print("result" + results.size());
        return results.size() != uniquedependence.size() ? new ArrayList<>() : results;

    }

    public static void main(String[] args) throws Throwable {
//        int rows = int32OrDefault(System.getProperty("rows"), 6);
//        int cols = int32OrDefault(System.getProperty("cols"), 7);
//        int humans = int32OrDefault(System.getProperty("humans"), 2);
//        int robots = int32OrDefault(System.getProperty("robots"), 0);
//
//        System.out.println("\n>>>>> Board = " + rows + " rows x " + cols + " cols; Human Players = " + humans + "; Robot Players = " + robots + "\n\n");
//
//        Main game = new Main(rows, cols, humans, robots);
//
//        game.start();

        Main object = new Main();
        String target = "4";
        Map<String, List<String>> deps = new HashMap<>();
        List<String> a = new ArrayList<>();
        a.add("3");
        a.add("2");
        deps.put("4", a);
        List<String> b = new ArrayList<>();
        b.add("1");
        deps.put("3",b);
        deps.put("2",b);
        List<String> res = object.buildOrder("4",deps);

        System.out.print("res" + Arrays.toString(res.toArray()));

    }

}
