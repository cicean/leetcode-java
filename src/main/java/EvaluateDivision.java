import java.util.*;

/**
 * Created by cicean on 9/12/2016.
 * 399. Evaluate Division  QuestionEditorial Solution  My Submissions
 Total Accepted: 678
 Total Submissions: 2150
 Difficulty: Medium
 Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

 Example:
 Given a / b = 2.0, b / c = 3.0.
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 return [6.0, 0.5, -1.0, 1.0, -1.0 ].

 The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

 According to the example above:

 equations = [ ["a", "b"], ["b", "c"] ],
 values = [2.0, 3.0],
 queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

 Hide Company Tags Google
 Hide Tags Graph

 */
public class EvaluateDivision {

    public double[] calcEquation(String[][] equations, double[] values, String[][] query) {
        double[] result = new double[query.length];
        // filter unexpected words
        // ���˵�û�����������ַ�
        Set<String> words = new HashSet<>();
        for (String[] strs : equations) {
            words.add(strs[0]);
            words.add(strs[1]);
        }
        for (int i = 0; i < query.length; ++i) {
            String[] keys = query[i];
            if (!words.contains(keys[0]) && !words.contains(keys[1])) result[i] = -1.0d;
            else {
                Stack<Integer> stack = new Stack<>();
                result[i] = helper(equations, values, keys, stack);
            }
        }
        return result;
    }

    public double helper(String[][] equations, double[] values, String[] keys, Stack<Integer> stack) {
        // ֱ�Ӳ��ң�key��˳��������
        // look up equations directly
        for (int i = 0; i < equations.length; ++i) {
            if (equations[i][0].equals(keys[0]) && equations[i][1].equals(keys[1])) return values[i];
            if (equations[i][0].equals(keys[1]) && equations[i][1].equals(keys[0])) return 1 / values[i];
        }
        // lookup equations by other equations
        // ��Ӳ��ң�key��˳��Ҳ������
        for (int i = 0; i < equations.length; ++i) {
            if (!stack.contains(i) && keys[0].equals(equations[i][0])) {
                stack.push(i);
                double temp = values[i] * helper(equations, values, new String[]{equations[i][1], keys[1]}, stack);
                if (temp > 0) return temp;
                else stack.pop();
            }
            if (!stack.contains(i) && keys[0].equals(equations[i][1])) {
                stack.push(i);
                double temp = helper(equations, values, new String[]{equations[i][0], keys[1]}, stack) / values[i];
                if (temp > 0) return temp;
                else stack.pop();
            }
        }
        // �鲻��������-1
        return -1.0d;
    }

    class Solution {
        Map<String,String> parents= new HashMap<>();
        Map<String, Double> vals = new HashMap<>();

        public double[] calcEquation(List<List<String>> equs, double[] values, List<List<String>> queries) {
            double[] res = new double[queries.size()];
            for(int i =0;i<values.length;i++){
                union(equs.get(i).get(0),equs.get(i).get(1),values[i]);
            }
            for(int i =0;i<queries.size();i++){
                String x=queries.get(i).get(0), y = queries.get(i).get(1);
                res[i]=(parents.containsKey(x)&& parents.containsKey(y) &&find(x)==find(y))?
                        vals.get(x)/vals.get(y): -1.0;
            }
            return res;
        }
        public void add(String x){
            if(parents.containsKey(x) ) return;
            parents.put(x,x);
            vals.put(x,1.0);
        }
        public String find (String x){
            String p = parents.getOrDefault(x,x);
            if(x!=p){
                String pp = find(p);
                vals.put(x,vals.get(x) * vals.get(p));
                parents.put(x,pp);
            }
            return parents.getOrDefault(x,x);
        }
        public void union(String x,String y,double v){
            add(x);add(y); String px = find(x); String py = find(y);
            parents.put(px,py);
            vals.put(px, v*vals.get(y)/vals.get(x));
        }
    }
}
