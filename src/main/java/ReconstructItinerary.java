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
 ���⣺����һЩ�ɻ�Ʊ����������ҳ�һ�����п���ȫ�����ꡣҪ������

 ��JFK������
 ����ж���⣬����ֵ�����С�ġ�

 */
public class ReconstructItinerary {
    /**
     * ������ʵ��ˮ��ֻҪ����Щ���ݽṹ���յĺõĻ���

     ���صĴ𰸱�Ϊn+1��nΪtickets�ĸ���
     һ�ŷɻ�Ʊֻ����һ�Σ�����Ҫ�������������ظ���
     �ֵ�����Сֻ��Ҫ��֤���Ǳ�����ʱ���С����������ɡ�so����ͼ��ʱ���ڽӱ�ߴ�С����
     */
    //������DFS���⣬һ����recursion, һ����stack��ʵ�֣�
    //Recursion version:
    class solution {
        public void dfs(String departure, Map<String, PriorityQueue<String>> graph, List<String> result) {
            //�������������������һ������ֻ��arrival city��ʱ�򣨼�û�г��ȵ�ʱ�򣬰����city����list��ȥ����Ϊ���϶�����󵽴�ĳ��У�Ȼ��������ǰ����
            PriorityQueue<String> arrivals = graph.get(departure);
            while (arrivals != null && !arrivals.isEmpty()) {
                dfs(arrivals.poll(), graph, result);
            }
            result.add(0, departure);
        }

        public List<String> findItinerary(String[][] tickets) {
            List<String> result = new ArrayList<String>();
            //lexical order��Ҫ���ڴ���graph��ʱ�����priority queue�ȴ��
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
                    //�Ƚ�ȥ��˵���ǳ������У���ô���ȳ����ĳ���������
                    stack.push(graph.get(stack.peek()).poll());
                }
                result.add(0, stack.pop());
            }
            return result;
        }
    }

    /**
     * ����LC����@dietpepsi�Ľ��ֻ��Ĥ�ݡ�
     ��Ŀȷ����������һ��valid���г̣������ڷ�֧�����һ������ͬ������Ŀ�ĵأ������Ҷ��ڶ���valid���г̣�Ҫѡȡ��ĸ˳���С��һ�����ع����г̵ص�һ��������ģ�
     ���ԣ�ʹ�������������������departure�ҵ�arrivals���ϣ�������PriorityQueue�Ա����ε�arrivals������ĸ˳�����У��ٽ����к��Ԫ��˳��ȡ����Ϊdeparture������DFS��Ȼ��һ��һ����ڶ���ؽ����departure����path����λ��

     HashMap��LinkedList�������ؼ��÷����£�

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

    class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
            if(tickets == null || tickets.size() < 1)
                return new ArrayList<String>();
            Map<String, PriorityQueue<String>> map = new HashMap<>();
            for(List<String> ticket : tickets){
                map.putIfAbsent(ticket.get(0), new PriorityQueue<String>());
                map.get(ticket.get(0)).add(ticket.get(1));
            }
            LinkedList<String> res = new LinkedList<String>();
            dfs(map, res, "JFK");
            return res;
        }
        public void dfs(Map<String, PriorityQueue<String>> map, LinkedList<String> res, String airport){

            PriorityQueue<String> pq = map.get(airport);
            while(pq!= null && !pq.isEmpty()){
                dfs(map, res, pq.poll());
            }
            res.addFirst(airport);
        }
    }



}
