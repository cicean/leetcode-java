import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 */
public class SlidingPuzzleIII {

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
        public SlidingPuzzle.Matrix up(int x, int y) {
            if (x == 0) {
                return null;
            }
            SlidingPuzzle.Matrix state = new SlidingPuzzle.Matrix(this.v);
            state.d = d + 1;
            int t = state.v[x][y];
            state.v[x][y] = state.v[x - 1][y];
            state.v[x - 1][y] = t;
            return state;
        }
        public SlidingPuzzle.Matrix down(int x, int y) {
            if (x == 2) {
                return null;
            }
            SlidingPuzzle.Matrix state = new SlidingPuzzle.Matrix(this.v);
            state.d = d + 1;
            int t = state.v[x][y];
            state.v[x][y] = state.v[x + 1][y];
            state.v[x + 1][y] = t;
            return state;
        }
        public SlidingPuzzle.Matrix left(int x, int y) {
            if (y == 0) {
                return null;
            }
            SlidingPuzzle.Matrix state = new SlidingPuzzle.Matrix(this.v);
            state.d = d + 1;
            int t = state.v[x][y];
            state.v[x][y] = state.v[x][y - 1];
            state.v[x][y - 1] = t;
            return state;
        }
        public SlidingPuzzle.Matrix right(int x, int y) {
            if (y == 2) {
                return null;
            }
            SlidingPuzzle.Matrix state = new SlidingPuzzle.Matrix(this.v);
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
            Queue<SlidingPuzzle.Matrix> queue = new LinkedList<SlidingPuzzle.Matrix>();
            Set<Integer> hashSet = new HashSet<Integer>();
            SlidingPuzzle.Matrix ins = new SlidingPuzzle.Matrix(matrix);
            queue.offer(ins);
            hashSet.add(ins.contor());
            while (!queue.isEmpty()) {
                SlidingPuzzle.Matrix h = queue.poll();
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
                SlidingPuzzle.Matrix state;
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
