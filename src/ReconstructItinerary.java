import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 332. Reconstruct Itinerary  QuestionEditorial Solution  My Submissions
 Total Accepted: 17203 Total Submissions: 65173 Difficulty: Medium
 Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

 Note:
 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 All airports are represented by three capital letters (IATA code).
 You may assume all tickets form at least one valid itinerary.
 Example 1:
 tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 Example 2:
 tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Depth-first Search Graph
 题意：给定一些飞机票，让你从中找出一个序列可以全部用完。要求如下

 从JFK出发。
 如果有多个解，输出字典序最小的。

 */
public class ReconstructItinerary {
    /**
     * 这题其实很水，只要你这些数据结构掌握的好的话。

     返回的答案必为n+1，n为tickets的个数
     一张飞机票只能用一次，所以要计数。（可能重复）
     字典序最小只需要保证我们遍历的时候从小到大遍历即可。so，建图的时候邻接表边从小到大。
     */
    //都是用DFS来解，一个用recursion, 一个用stack来实现：
    //Recursion version:
    class solution {
        public void dfs(String departure, Map<String, PriorityQueue<String>> graph, List<String> result) {
            //深度优先搜索，搜索到一个城市只是arrival city的时候（即没有出度的时候，把这个city记入list中去，因为它肯定是最后到达的城市，然后依次向前类推
            PriorityQueue<String> arrivals = graph.get(departure);
            while (arrivals != null && !arrivals.isEmpty()) {
                dfs(arrivals.poll(), graph, result);
            }
            result.add(0, departure);
        }

        public List<String> findItinerary(String[][] tickets) {
            List<String> result = new ArrayList<String>();
            //lexical order的要求在存入graph的时候就用priority queue先存好
            Map<String, PriorityQueue<String>> graph = new HashMap<>();
            for (String[] iter : tickets) {
                graph.putIfAbsent(iter[0], new PriorityQueue<String>());
                graph.get(iter[0]).add(iter[1]);
            }

            dfs("JFK", graph, result);
            return result;
        }
    }

    //Stack version:

    class sultion2 {
        public List<String> findItinerary(String[][] tickets) {
            List<String> result = new ArrayList<String>();
            Map<String, PriorityQueue<String>> graph = new HashMap();
            for (String[] iter : tickets) {
                graph.putIfAbsent(iter[0], new PriorityQueue<String>());
                graph.get(iter[0]).add(iter[1]);
            }

            Stack<String> stack = new Stack<String>();
            stack.push("JFK");
            while (!stack.isEmpty()) {
                while (graph.containsKey(stack.peek()) && !graph.get(stack.peek()).isEmpty()) {
                    //先进去的说明是出发城市，那么最先出发的城市最后出来
                    stack.push(graph.get(stack.peek()).poll());
                }
                result.add(0, stack.pop());
            }
            return result;
        }
    }

    /**
     * 来自LC大神@dietpepsi的解答，只能膜拜。
     题目确定了至少有一条valid的行程（不存在分支情况，一定有相同的最终目的地），而且对于多条valid的行程，要选取字母顺序较小的一条。重构的行程地点一定是有序的，
     所以，使用深度优先搜索，根据departure找到arrivals集合，并利用PriorityQueue对本航段的arrivals进行字母顺序排列，再将排列后的元素顺序取出作为departure，继续DFS，然后一层一层从内而外地将起点departure放入path的首位。

     HashMap和LinkedList的两个关键用法如下：

     putIfAbsent
     Method Detail:

     V putIfAbsent(K key, V value)
     If the specified key is not already associated with a value, associate it with the given value. This is equivalent to

     if (!map.containsKey(key))
     return map.put(key, value);
     else
     return map.get(key);
     except that the action is performed atomically.

     Parameters:

     key - key with which the specified value is to be associated
     value - value to be associated with the specified key

     Returns:

     the previous value associated with the specified key, or null if there was no mapping for the key. (A null return can also indicate that the map previously associated null with the key, if the implementation supports null values.)

     addFirst
     public void addFirst(E e)
     Inserts the specified element at the beginning of this list.

     Specified by:

     addFirst in interface Deque<E>

     Parameters:

     e - the element to add
     */
    public class Solution3 {
        Map<String, PriorityQueue<String>> flights = new HashMap();
        LinkedList<String> path = new LinkedList();
        public List<String> findItinerary(String[][] tickets) {
            for (String[] oneway: tickets) {
                flights.putIfAbsent(oneway[0], new PriorityQueue());
                flights.get(oneway[0]).add(oneway[1]);
            }
            dfs("JFK");
            return path;
        }
        public void dfs(String departure) {
            PriorityQueue<String> arrivals = flights.get(departure);
            while (arrivals != null && !arrivals.isEmpty()) dfs(arrivals.poll());
            path.addFirst(departure);
        }
    }



}
