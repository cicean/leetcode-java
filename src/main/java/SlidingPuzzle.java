import java.util.*;

/**
 * 773. Sliding Puzzle
 * DescriptionHintsSubmissionsDiscussSolution
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 *
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 * Examples:
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 * Note:
 *
 * board will be a 2 x 3 array as described above.
 * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 */

public class SlidingPuzzle {

    /**
     * Approach #1: Breadth-First Search [Accepted]
     * Intuition
     *
     * We can think of this problem as a shortest path problem on a graph. Each node is a different board state,
     * and we connect two boards by an edge if they can be transformed into one another in one move.
     * We can solve shortest path problems with breadth first search.
     *
     * Algorithm
     *
     * For our breadth first search, we will need to be able to represent the nodes as something hashable,
     * and we'll need to enumerate the neighbors of a board. Afterwards,
     * we can use a typical template for breadth-first search as shown below:
     *
     * queue = collections.deque([(start, 0)])
     * seen = {start}
     * while queue:
     *     node, depth = queue.popleft()
     *     if node == target: return depth
     *     for nei in neighbors(node):
     *         if nei not in seen:
     *             seen.add(nei)
     *             queue.append((nei, depth+1))
     * To represent the nodes as something hashable, in Python, we convert the board to 1 dimension and use a tuple;
     * in Java we can either encode the board as an integer, or more generally use Arrays.deepToString.
     *
     * To enumerate the neighbors of a board, we'll remember where the zero is. Then, there are only 4 possible neighbors.
     * If the board is flattened to 1 dimension, these neighbors occur at distances (1, -1, C, -C) from the zero,
     * where C is the number of columns in the board.
     * Complexity Analysis
     *
     * Time Complexity: O(R * C * (R * C)!)O(R?C?(R?C)!), where R, CR,C are the number of rows and columns in board.
     * There are O((R * C)!)O((R?C)!) possible board states.
     *
     * Space Complexity: O(R * C * (R * C)!)O(R?C?(R?C)!).
     */
    class Solution {
        public int slidingPuzzle(int[][] board) {
            int R = board.length, C = board[0].length;
            int sr = 0, sc = 0;
            search:
            for (sr = 0; sr < R; sr++)
                for (sc = 0; sc < C; sc++)
                    if (board[sr][sc] == 0)
                        break search;

            int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            Queue<Node> queue = new ArrayDeque();
            Node start = new Node(board, sr, sc, 0);
            queue.add(start);

            Set<String> seen = new HashSet();
            seen.add(start.boardstring);

            String target = Arrays.deepToString(new int[][]{{1,2,3}, {4,5,0}});

            while (!queue.isEmpty()) {
                Node node = queue.remove();
                if (node.boardstring.equals(target))
                    return node.depth;

                for (int[] di: directions) {
                    int nei_r = di[0] + node.zero_r;
                    int nei_c = di[1] + node.zero_c;

                    if ((Math.abs(nei_r - node.zero_r) + Math.abs(nei_c - node.zero_c) != 1) ||
                            nei_r < 0 || nei_r >= R || nei_c < 0 || nei_c >= C)
                        continue;

                    int[][] newboard = new int[R][C];
                    int t = 0;
                    for (int[] row: node.board)
                        newboard[t++] = row.clone();
                    newboard[node.zero_r][node.zero_c] = newboard[nei_r][nei_c];
                    newboard[nei_r][nei_c] = 0;

                    Node nei = new Node(newboard, nei_r, nei_c, node.depth+1);
                    if (seen.contains(nei.boardstring))
                        continue;
                    queue.add(nei);
                    seen.add(nei.boardstring);
                }
            }

            return -1;
        }
    }

    class Node {
        int[][] board;
        String boardstring;
        int zero_r;
        int zero_c;
        int depth;
        Node(int[][] B, int r, int c, int d) {
            board = B;
            boardstring = Arrays.deepToString(board);
            zero_r = r;
            zero_c = c;
            depth = d;
        }
    }


    /**
     * Approach #2: A* Search [Accepted]
     * Intuition
     *
     * As in Approach #1, this is a problem about searching on a graph.
     *
     * We can use the "A* Star Search Algorithm" to search promising nodes in our graph first,
     * with guarantees that it will find the best answer.
     *
     * For every node, we have some estimated cost node.priority = node.depth + node.heuristic,
     * where node.depth is the actual distance travelled, and node.
     * heuristic is our heuristic (guess) of the remaining distance to travel. If the heuristic is admissible
     * (it never overestimates the distance to the goal),
     * then the following algorithm is guaranteed to terminate at the best answer.
     *
     * For solvers familiar with Dijkstra's Algorithm, A* Search is a special case of Dijkstra's with node.
     * heuristic = 0 always. On certain types of graphs and with good heuristics,
     * this approach is substantially faster then a breadth-first search.
     *
     * Algorithm
     *
     * Let's keep a priority queue that sorts by node.depth + node.heuristic. As before, each node represents a puzzle board.
     *
     * The heuristic we use is the sum of the taxicab distance of each (non-zero) number to it's final destination.
     * This heuristic is admissible as we need at least this many moves.
     *
     * To speed up our algorithm, we use targetWrong,
     * which has a near zero heuristic distance to the target (meaning our search will aim for it quickly).
     * If it finds it, we don't have to search all the boards.
     *
     * We could prove that the set of boards can be split in half, with one half transformable to target,
     * and the other half transformable to targetWrong.
     * One way to convince yourself of this is to see that every piece except the last 2 can be placed in the correct position,
     * but a formal proof analyzing the parity of inversions of the underlying permutation is outside the scope of this article.
     * For more information see link.
     * Complexity Analysis
     *
     * Time Complexity: O(R * C * (R * C)!)O(R?C?(R?C)!), where R, CR,C are the number of rows and columns in board.
     * Tighter bounds are possible, but difficult to prove. (In testing with random permutations of a 3x3 board,
     * about 50 times less nodes were searched compared to breadth-first-search.)
     *
     * Space Complexity: O(R * C * (R * C)!)O(R?C?(R?C)!).
     */
    class Solution_A_square {
        public int slidingPuzzle(int[][] board) {
            int R = board.length, C = board[0].length;
            int sr = 0, sc = 0;

            //Find sr, sc
            search:
            for (sr = 0; sr < R; sr++)
                for (sc = 0; sc < C; sc++)
                    if (board[sr][sc] == 0)
                        break search;

            int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            PriorityQueue<Node> heap = new PriorityQueue<Node>((a, b) ->
                    (a.heuristic + a.depth) - (b.heuristic + b.depth));
            Node start = new Node(board, sr, sc, 0);
            heap.add(start);

            Map<String, Integer> cost = new HashMap();
            cost.put(start.boardstring, 9999999);

            String target = Arrays.deepToString(new int[][]{{1,2,3}, {4,5,0}});
            String targetWrong = Arrays.deepToString(new int[][]{{1,2,3}, {5,4,0}});

            while (!heap.isEmpty()) {
                Node node = heap.poll();
                if (node.boardstring.equals(target))
                    return node.depth;
                if (node.boardstring.equals(targetWrong))
                    return -1;
                if (node.depth + node.heuristic > cost.get(node.boardstring))
                    continue;

                for (int[] di: directions) {
                    int nei_r = di[0] + node.zero_r;
                    int nei_c = di[1] + node.zero_c;

                    // If the neighbor is not on the board or wraps incorrectly around rows/cols
                    if ((Math.abs(nei_r - node.zero_r) + Math.abs(nei_c - node.zero_c) != 1) ||
                            nei_r < 0 || nei_r >= R || nei_c < 0 || nei_c >= C)
                        continue;

                    int[][] newboard = new int[R][C];
                    int t = 0;
                    for (int[] row: node.board)
                        newboard[t++] = row.clone();

                    // Swap the elements on the new board
                    newboard[node.zero_r][node.zero_c] = newboard[nei_r][nei_c];
                    newboard[nei_r][nei_c] = 0;

                    Node nei = new Node(newboard, nei_r, nei_c, node.depth+1);
                    if (nei.depth + nei.heuristic >= cost.getOrDefault(nei.boardstring, 9999999))
                        continue;
                    heap.add(nei);
                    cost.put(nei.boardstring, nei.depth + nei.heuristic);
                }
            }

            return -1;
        }
    }

    class Node {
        int[][] board;
        String boardstring;
        int heuristic;
        int zero_r;
        int zero_c;
        int depth;
        Node(int[][] B, int zr, int zc, int d) {
            board = B;
            boardstring = Arrays.deepToString(board);

            //Calculate heuristic
            heuristic = 0;
            int R = B.length, C = B[0].length;
            for (int r = 0; r < R; ++r)
                for (int c = 0; c < C; ++c) {
                    if (board[r][c] == 0) continue;
                    int v = (board[r][c] + R*C - 1) % (R*C);
                    // v/C, v%C: where board[r][c] should go in a solved puzzle
                    heuristic += Math.abs(r - v/C) + Math.abs(c - v%C);
                }
            heuristic /= 2;
            zero_r = zr;
            zero_c = zc;
            depth = d;
        }
    }

    class Solution {
        public int slidingPuzzle(int[][] board) {
            if (board == null || board.length != 2 || board[0].length != 3) return -1;
            String target = "123450";
            String start = getBoard(board);
            Set<String> set = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(start);
            set.add(start);
            int res = 0;
            int[][] dirs = {{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{2,4}};
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    if (cur.equals(target)) {
                        return res;
                    }
                    int zeroPos = cur.indexOf('0');
                    for (int dir : dirs[zeroPos]) {
                        String next = swap(cur, zeroPos, dir);
                        if (!set.contains(next)) {
                            set.add(next);
                            queue.offer(next);
                        }
                    }
                }
                res++;
            }
            return -1;
        }

        String swap(String str, int i, int j) {
            char[] ch = str.toCharArray();
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            return String.valueOf(ch);
        }

        String getBoard(int[][] board) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    sb.append(board[i][j]);
                }
            }
            return sb.toString();
        }
    }

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

    /**
     * Sliding Puzzle III
     * ����3 x 3�ľ��󣬱��Ϊ1~9������8�������������֣�1~8������һ��Ϊ��(��0��ʾ)��
     * ���Ƿ��ܽ���Ӧ�����ַŵ���Ӧ��ŵĸ�����(�ո�ֻ�ܺ���������λ�ý���),��������"YES"���������"NO"��
     */
    class Matrix{
        public int[][] v = new int[3][3];
        public int d;
        public Matrix(int[][] matrix) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    v[i][j] = matrix[i][j];
                }
            }
        }
        public int contor() {
            int res = 0, cnt = 0;
            int[] fac = {1, 1, 2, 6, 24, 120, 720, 5040, 40320};
            for (int i = 0; i < 9; i++) {
                cnt = 0;
                for (int j = i + 1; j < 9; j++) {
                    if (v[j / 3][j % 3] < v[i / 3][i % 3]) {
                        cnt++;
                    }
                }
                res += cnt * fac[8 - i];
            }
            return res;
        }
        public Matrix up(int x, int y) {
            if (x == 0) {
                return null;
            }
            Matrix state = new Matrix(this.v);
            state.d = d + 1;
            int t = state.v[x][y];
            state.v[x][y] = state.v[x - 1][y];
            state.v[x - 1][y] = t;
            return state;
        }
        public Matrix down(int x, int y) {
            if (x == 2) {
                return null;
            }
            Matrix state = new Matrix(this.v);
            state.d = d + 1;
            int t = state.v[x][y];
            state.v[x][y] = state.v[x + 1][y];
            state.v[x + 1][y] = t;
            return state;
        }
        public Matrix left(int x, int y) {
            if (y == 0) {
                return null;
            }
            Matrix state = new Matrix(this.v);
            state.d = d + 1;
            int t = state.v[x][y];
            state.v[x][y] = state.v[x][y - 1];
            state.v[x][y - 1] = t;
            return state;
        }
        public Matrix right(int x, int y) {
            if (y == 2) {
                return null;
            }
            Matrix state = new Matrix(this.v);
            state.d = d + 1;
            int t = state.v[x][y];
            state.v[x][y] = state.v[x][y + 1];
            state.v[x][y + 1] = t;
            return state;
        }
    }
    public class Solution {
        /**
         * @param matrix: The 3*3 matrix
         * @return: The answer
         */
        public String jigsawPuzzle(int[][] matrix) {
            // Write your code here
            int ans = -1;
            Queue<Matrix> queue = new LinkedList<Matrix>();
            Set<Integer> hashSet = new HashSet<Integer>();
            Matrix ins = new Matrix(matrix);
            queue.offer(ins);
            hashSet.add(ins.contor());
            while (!queue.isEmpty()) {
                Matrix h = queue.poll();
                if (h.contor() == 46233) {
                    ans = h.d;
                    break;
                }
                int x = 0, y = 0;
                for (int i = 0; i < 9; i++) {
                    if (h.v[i / 3][i % 3] == 0) {
                        x = i / 3;
                        y = i % 3;
                        break;
                    }
                }
                Matrix state;
                state = h.up(x, y);
                if (state != null && !hashSet.contains(state.contor())) {
                    hashSet.add(state.contor());
                    queue.offer(state);
                }
                state = h.down(x, y);
                if (state != null && !hashSet.contains(state.contor())) {
                    hashSet.add(state.contor());
                    queue.offer(state);
                }
                state = h.left(x, y);
                if (state != null && !hashSet.contains(state.contor())) {
                    hashSet.add(state.contor());
                    queue.offer(state);
                }
                state = h.right(x, y);
                if (state != null && !hashSet.contains(state.contor())) {
                    hashSet.add(state.contor());
                    queue.offer(state);
                }
            }
            if(ans != -1) {
                return "YES";
            } else {
                return "NO";
            }
        }
    }

}
