import java.util.*;

/**
 * 1202. Smallest String With Swaps
 * Medium
 *
 * 350
 *
 * 12
 *
 * Add to List
 *
 * Share
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 * Accepted
 * 9,971
 * Submissions
 * 22,567
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * siddharthp538
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * ByteDance
 * |
 * 4
 *
 * Amazon
 * |
 * 2
 * Think of it as a graph problem.
 * Consider the pairs as connected nodes in the graph, what can you do with a connected component of indices ?
 * We can sort each connected component alone to get the lexicographically minimum string.
 */
public class SmallestStringWithSwaps {

    class Solution {
        private int[] father;

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            int n = s.length();
            // 1: init father array for union find
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }

            // 2: union nodes with edges in between
            for (List<Integer> pair : pairs) {
                int x = pair.get(0);
                int y = pair.get(1);
                if (findFather(x) != findFather(y)) {
                    union(x, y);
                }
                /* */
            }

            // 3: group connected components in graph
            List<Integer>[] groups = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                int id = findFather(i);
                if (groups[id] == null) {
                    groups[id] = new ArrayList<>();
                }
                groups[id].add(i);
            }

            // 4: sort each group, put the elements back to string
            char[] res = new char[n];
            for (int i = 0; i < n; i++) {
                List<Integer> comp = groups[i];
                if (comp == null) continue;
                char[] sorted = new char[comp.size()];
                for (int j = 0; j < comp.size(); j++) {
                    sorted[j] = s.charAt(comp.get(j));
                }
                Arrays.sort(sorted);
                for (int j = 0; j < comp.size(); j++) {
                    res[comp.get(j)] = sorted[j];
                }
            }

            return new String(res);
        }

        private int findFather (int x) {
            if (father[x] != x) {
                father[x] = findFather(father[x]);
            }
            return father[x];
        }

        private void union (int x, int y) {
            x = father[x];
            y = father[y];
            if (x < y) {
                father[y] = findFather(x);
            } else {
                father[x] = findFather(y);
            }
        }
    }


    class Solution {
        private int[] parent;
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            if (s == null || s.length() == 0) {
                return null;
            }
            parent = new int[s.length()];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            for (List<Integer> pair : pairs) {
                union(pair.get(0), pair.get(1));
            }

            Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
            char[] sChar = s.toCharArray();
            for (int i = 0; i < sChar.length; i++) {
                int root = find(i);
                map.putIfAbsent(root, new PriorityQueue<>());
                map.get(root).offer(sChar[i]);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sChar.length; i++) {
                sb.append(map.get(find(i)).poll());
            }
            return sb.toString();
        }
        private int find(int index) {
            while (parent[index] != index) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }
            return index;
        }
        private void union(int a, int b) {
            int aParent = find(a);
            int bParent = find(b);
            if (aParent < bParent) {
                parent[bParent] = aParent;
            } else {
                parent[aParent] = bParent;
            }
        }
    }

}
