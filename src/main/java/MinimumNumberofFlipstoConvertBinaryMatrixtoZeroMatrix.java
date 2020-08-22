/**
 * 1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
 * Hard
 *
 * 122
 *
 * 18
 *
 * Add to List
 *
 * Share
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.
 *
 * Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
 *
 * Binary matrix is a matrix with all cells equal to 0 or 1 only.
 *
 * Zero matrix is a matrix with all cells equal to 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0],[0,1]]
 * Output: 3
 * Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
 * Example 2:
 *
 * Input: mat = [[0]]
 * Output: 0
 * Explanation: Given matrix is a zero matrix. We don't need to change it.
 * Example 3:
 *
 * Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
 * Output: 6
 * Example 4:
 *
 * Input: mat = [[1,0,0],[1,0,0]]
 * Output: -1
 * Explanation: Given matrix can't be a zero matrix
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[0].length
 * 1 <= m <= 3
 * 1 <= n <= 3
 * mat[i][j] is 0 or 1.
 * Accepted
 * 6,250
 * Submissions
 * 9,018
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * lucifer1004
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 3
 * Flipping same index two times is like not flipping it at all. Each index can be flipped one time.
 * Try all possible combinations. O(2^(n*m)).
 */
public class MinimumNumberofFlipstoConvertBinaryMatrixtoZeroMatrix {

//    java
//    You are here!
//    Your runtime beats 84.07 % of java submissions.
//            Runtime (ms)Distribution (%)
//
//    Zoom area by dragging across this chart
//
//    Accepted Solutions Memory Distribution
//    Sorry. We do not have enough accepted submissions to show distribution chart.
//    Invite friends to challenge Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
//    Submitted Code: 0 minutes ago
//    Language: java
//
//    Back to problem
//× Close
//    sample 0 ms submission
    class Solution {
        public final int MAX_STEP = 10;
        public int minFlips(int[][] mat) {
            if(mat.length == 0) return 0;

            int minOverall =  minFlips(convertToInt(mat), 0, mat[0].length, mat.length * mat[0].length);
            if(minOverall >= MAX_STEP) {
                return -1;
            }else{
                return minOverall;
            }
        }

        public int minFlips(int matInt, int position, int columnSize, int matSize) {
            //System.out.println("miniFlips: " + Integer.toString(matInt, 2) + ", position=" + position);
            if(matInt ==0 ) return 0;

            if(position >= matSize) {
                return MAX_STEP;// didn't find a solution
            }

            // no flip
            int noFlip = minFlips(matInt, position +1, columnSize, matSize);

            // flip
            int newMatInt = flip(matInt, position, columnSize, matSize);
            int flip = 1 + minFlips(newMatInt,position+1, columnSize, matSize);

            return  Math.min(noFlip, flip);
        }
        public int convertToInt(int[][] mat) {
            int intValue = 0;
            for(int i = 0; i < mat.length; i++){
                for(int j = 0; j < mat[i].length; j++) {
                    intValue = intValue <<1;

                    if(mat[i][j] !=0) {
                        intValue = intValue + 1;
                    }
                }
            }
            return intValue;
        }

        public int flip(int matInt, int position, int columnSize, int limit) {
            int mask = 1;

            //itself
            mask = mask << (limit -1 - position);
            int top = (position / columnSize > 0) ? mask << columnSize : 0;
            int left = (position % columnSize > 0) ? mask << 1 : 0;
            int right = ((position + 1) % columnSize != 0)? mask >> 1 : 0;
            int bottom = (position + columnSize < limit)? mask >> columnSize : 0;
            mask = mask + top + left + right + bottom;

            return matInt ^ mask;
        }

    }
}
