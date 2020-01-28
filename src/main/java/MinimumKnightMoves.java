/**
 * 1197. Minimum Knight Moves
 * Medium
 *
 * 118
 *
 * 42
 *
 * Add to List
 *
 * Share
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
 * then one square in an orthogonal direction.
 *
 *
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 * Example 2:
 *
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 *
 * Constraints:
 *
 * |x| + |y| <= 300
 * Accepted
 * 9,787
 * Submissions
 * 30,284
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 7
 *
 * Google
 * |
 * 5
 *
 * Amazon
 * |
 * 2
 * You can simulate the movements since the limits are low.
 * Is there a search algorithm applicable to this problem?
 * Since we want the minimum number of moves, we can use Breadth First Search.
 */

public class MinimumKnightMoves {

    class Solution_DP {
        public int minKnightMoves(int x, int y) {
            int[][] dp = new int[301][301];
            x = Math.abs(x);
            y = Math.abs(y);
            return moveKnight(dp, x, y);

        }

        int moveKnight(int[][] dp, int x, int y){
            int sum = x+y;
            if(sum == 0) return 0;
            if(sum == 1) return 3;
            if(sum == 2) return 2;
            //if(sum == 3) return 1;
            if(dp[x][y] > 0) return dp[x][y];
            dp[x][y] = Math.min(moveKnight(dp, Math.abs(x-2), Math.abs(y-1)), moveKnight(dp, Math.abs(x-1), Math.abs(y-2)))+1;
            return dp[x][y];
        }

        class Solution_DFS {
            public int minKnightMoves(int x, int y) {
                int MOD = Math.abs(y) + 2;
                return dfs(Math.abs(x), Math.abs(y), new HashMap<>(), MOD);
            }

            public int dfs(int x, int y, Map<Integer, Integer> map, int MOD) {
                int index = x * MOD + y;
                if (map.containsKey(index)) {
                    return map.get(index);
                }
                int ans = 0;
                if (x + y == 0) {
                    ans = 0;
                } else if (x + y == 2) {
                    ans = 2;
                } else {
                    ans = Math.min(dfs(Math.abs(x - 1), Math.abs(y - 2), map, MOD),
                            dfs(Math.abs(x - 2), Math.abs(y - 1), map, MOD)) + 1;
                }
                map.put(index, ans);
                return ans;
            }
        }
}
