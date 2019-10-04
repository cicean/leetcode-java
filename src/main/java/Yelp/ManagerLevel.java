package Yelp;

import java.util.*;

/**
 * Created by cicean on 9/19/2018.
 */
public class ManagerLevel {
    /**
     * 题目是给一个List of string pairs,
     * 每一个pair里面是两个名字, 第一个人是第二个人的上级. 要求写一个算法返回其中职位最高的人.
     */

    public List<String> findManager(String[][] employees) {
        List<String> managers = new ArrayList<>();
        if (employees == null || employees.length == 0
                || employees[0].length == 0) {
            return  managers;
        }
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, Set<String>> graph = new HashMap<>();
        Set<String> members = new HashSet<>();
        for (String[] employee : employees) {
            System.out.println("employee -> " + employee[0] + ", manager -> " + employee[1]);
            graph.computeIfAbsent(employee[0],x -> new HashSet<String>()).add(employee[1]);
            indegree.put(employee[1], indegree.getOrDefault(employee[1], 0) + 1);
            members.add(employee[0]);
            members.add(employee[1]);
        }

        Queue<String> queue = new LinkedList<>();
        for (String member : members) {
            if (!indegree.containsKey(member)) {
                queue.add(member);
            }
        }

        int count = 0;
        List<List<String>> levels = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                String member = queue.poll();
                System.out.print("level = " + count + ", memeber -> " + member);
                tmp.add(member);
                levels.add(tmp);
                count++;
                if (graph.containsKey(member)) {
                    for (String manager : graph.get(member)) {
                        if (indegree.get(manager) - 1 == 0) {
                            queue.add(manager);
                            indegree.remove(manager);
                        } else {
                            indegree.put(manager, indegree.get(manager) - 1);
                        }
                    }
                }
            }
        }

        return count == members.size() ? levels.get(levels.size() - 1) : managers;
    }

    public static void main(String[] args) throws Throwable {
        Main solution = new Main();

        //List<String[]> test = new ArrayList<>();
        String[][] levels = {{"A","B"},{"B","C"},{"E", "C"},{"C","D"},{"F","E"}};

        List<String> results = solution.findManager(levels);

        System.out.print(Arrays.toString(results.toArray()));

    }
}
