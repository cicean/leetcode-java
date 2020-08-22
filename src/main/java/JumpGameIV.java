/**
 * 1345. Jump Game IV
 * Hard
 *
 * 118
 *
 * 9
 *
 * Add to List
 *
 * Share
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 * Example 2:
 *
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You don't need to jump.
 * Example 3:
 *
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 * Example 4:
 *
 * Input: arr = [6,1,9]
 * Output: 2
 * Example 5:
 *
 * Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 * Accepted
 * 5,437
 * Submissions
 * 15,456
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * Build a graph of n nodes where nodes are the indices of the array and edges for node i are nodes i+1, i-1, j where arr[i] == arr[j].
 * Start bfs from node 0 and keep distance, answer is the distance when you reach onode n-1.
 */
public class JumpGameIV {

    class Solution {
        public int minJumps(int[] arr) {
            int n = arr.length;
            HashMap<Integer, List<Integer>> indicesOfValue = new HashMap<>();
            for (int i = 0; i < n; i++)
                indicesOfValue.computeIfAbsent(arr[i], x -> new LinkedList<>()).add(i);
            boolean[] visited = new boolean[n]; visited[0] = true;
            Queue<Integer> q = new LinkedList<>(); q.offer(0);
            int step = 0;
            while (!q.isEmpty()) {
                for (int size = q.size(); size > 0; --size) {
                    int i = q.poll();
                    if (i == n - 1) return step; // Reached to last index
                    List<Integer> next = indicesOfValue.get(arr[i]);
                    next.add(i - 1); next.add(i + 1);
                    for (int j : next) {
                        if (j >= 0 && j < n && !visited[j]) {
                            visited[j] = true;
                            q.offer(j);
                        }
                    }
                    next.clear(); // avoid later lookup indicesOfValue arr[i]
                }
                step++;
            }
            return 0;
        }
    }


    class Solution2 {
        int[] stack;
        int size;
        byte[] map;

        public int minJumps(int[] arr) {
            final int N = arr.length;
            if (N <= 2) {
                return N == 2 ? 1 : 0;
            }
            Map<Integer, Integer> firsts = new HashMap<>();
            int[] prevs = new int[N];
            for (int i = 0; i < N; i++) {
                Integer nix = firsts.put(arr[i], i);
                prevs[i] = nix == null ? -1 : nix;
            }
            int[] stack = this.stack = new int[N];
            byte[] map = this.map = new byte[N];
            int sz0 = 0;
            this.size = 1;
            stack[0] = 0;
            map[0] = 1;
            for (int step = 1; ; step++) {
                int index = sz0;
                sz0 = this.size;
                for (; index < sz0; index++) {
                    int ix = stack[index];
                    if (map[ix] == 1) {
                        map[ix] = 2;
                        Integer x = firsts.remove(arr[ix]);
                        if (x != null) {
                            for (int i = x; i >= 0; i = prevs[i]) {
                                if (update(i, (byte) 2)) {
                                    return step;
                                }
                            }
                        }
                    }
                    if (update(ix + 1, (byte) 1)) {
                        return step;
                    }
                    if (update(ix - 1, (byte) 1)) {
                        return step;
                    }
                }
            }
        }

        private boolean update(int index, byte mark) {
            if (index < 0 || index >= map.length || map[index] != 0) {
                return false;
            }
            map[index] = mark;
            stack[size++] = index;
            return index == map.length - 1;
        }
    }
}
