import java.util.*;
/**
 * Back
 * 794. Sliding Puzzle II
 * Description
 * On a 3x3 board, there are 8 tiles represented by the integers 1 through 8, and an empty square represented by 0.
 *
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * Given an initial state of the puzzle board and final state, return the least number of moves required so that the initial state to final state.
 *
 * If it is impossible to move from initial state to final state, return -1.
 *
 * Have you met this question in a real interview?
 * Example
 * Given an initial state:
 *
 * [
 *  [2,8,3],
 *  [1,0,4],
 *  [7,6,5]
 * ]
 * and a final state:
 *
 * [
 *  [1,2,3],
 *  [8,0,4],
 *  [7,6,5]
 * ]
 * Return 4
 * Explanation:
 *
 * [                 [
 *  [2,8,3],          [2,0,3],
 *  [1,0,4],   -->    [1,8,4],
 *  [7,6,5]           [7,6,5]
 * ]                 ]
 *
 * [                 [
 *  [2,0,3],          [0,2,3],
 *  [1,8,4],   -->    [1,8,4],
 *  [7,6,5]           [7,6,5]
 * ]                 ]
 *
 * [                 [
 *  [0,2,3],          [1,2,3],
 *  [1,8,4],   -->    [0,8,4],
 *  [7,6,5]           [7,6,5]
 * ]                 ]
 *
 * [                 [
 *  [1,2,3],          [1,2,3],
 *  [0,8,4],   -->    [8,0,4],
 *  [7,6,5]           [7,6,5]
 * ]                 ]
 * Challenge
 * How to optimize the memory?
 * Can you solve it with A* algorithm?
 * Related Problems
 */

public class SlidingPuzzleII {



    public class Solution_BFS_II {
        /**
         * @param init_state: the initial state of chessboard
         * @param final_state: the final state of chessboard
         * @return: return an integer, denote the number of minimum moving
         */
        public int minMoveStep(int[][] init_state, int[][] final_state) {
            String source = matrixToString(init_state);
            String target = matrixToString(final_state);

            Queue<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            queue.offer(source);
            visited.add(source);

            int step = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String curt = queue.poll();
                    if (curt.equals(target)) {
                        return step;
                    }

                    for (String next : getNext(curt)) {
                        if (visited.contains(next)) {
                            continue;
                        }
                        queue.offer(next);
                        visited.add(next);
                    }
                }
                step++;
            }

            return -1;
        }

        public String matrixToString(int[][] state) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(state[i][j]);
                }
            }
            return sb.toString();
        }

        public List<String> getNext(String state) {
            List<String> states = new ArrayList<>();
            int[] dx = {0, 1, -1, 0};
            int[] dy = {1, 0, 0, -1};

            int zeroIndex = state.indexOf('0');
            int x = zeroIndex / 3;
            int y = zeroIndex % 3;

            for (int i = 0; i < 4; i++) {
                int x_ = x + dx[i];
                int y_ = y + dy[i];
                if (x_ < 0 || x_ >= 3 || y_ < 0 || y_ >= 3) {
                    continue;
                }

                char[] chars = state.toCharArray();
                chars[x * 3 + y] = chars[x_ * 3 + y_];
                chars[x_ * 3 + y_] = '0';
                states.add(new String(chars));
            }

            return states;
        }
    }

    public class Solution_A_Square{
        /**
         * @param init_state: the initial state of chessboard
         * @param final_state: the final state of chessboard
         * @return: return an integer, denote the number of minimum moving
         */
        int lim = -1;
        boolean flag = false;
        int[] w = new int[9];
        int[] z = new int[9];
        int[][] a = new int[3][3];
        int[] fx = new int[]{-1,0,0,1};
        int[] fy = new int[]{0,-1,1,0};
        public int minMoveStep(int[][] init_state, int[][] final_state) {
            // # write your code here
            int x = 0, y = 0;
            for(int i = 0; i < 3; ++i) {
                for(int j = 0; j < 3; ++j) {
                    if(init_state[i][j] == 0) {
                        x = i;
                        y = j;
                    }
                    a[i][j] = init_state[i][j];
                    w[final_state[i][j]] = i;
                    z[final_state[i][j]] = j;
                }
            }
            while(!flag) {
                lim++;
                dfs(0,x,y);
            }
            return lim;
        }
        public void dfs(int now, int x, int y) {
            if(flag)return;
            if(now > lim)return;
            int add = guess();http://www.lintcode.com/en/problem/sliding-puzzle-ii/#
            if(now + add > lim)return;
            if(add == 0) {
                flag = true;
                return;
            }
            for(int i = 0; i < 4; ++i) {
                int nx = x + fx[i];
                int ny = y + fy[i];
                if(nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                    int tmp = a[x][y];
                    a[x][y] = a[nx][ny];
                    a[nx][ny] = tmp;
                    dfs(now + 1, nx, ny);
                    tmp = a[x][y];
                    a[x][y] = a[nx][ny];
                    a[nx][ny] = tmp;
                }
            }
        }
        public int guess() {
            int sum = 0;
            for(int i = 0; i < 3; ++i) {
                for(int j = 0; j < 3; ++j) {
                    if(a[i][j] > 0) {
                        sum += Math.abs(w[a[i][j]]-i) + Math.abs(z[a[i][j]]-j);
                    }
                }
            }
            return sum;
        }
    }
}
