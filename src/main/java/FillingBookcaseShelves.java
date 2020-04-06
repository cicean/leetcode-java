/**
 * Filling Bookcase Shelves
 * Medium
 *
 * 408
 *
 * 22
 *
 * Add to List
 *
 * Share
 * We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
 *
 * We want to place these books in order onto bookcase shelves that have total width shelf_width.
 *
 * We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.  We repeat this process until there are no more books to place.
 *
 * Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
 *
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * Explanation:
 * The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 *
 *
 * Constraints:
 *
 * 1 <= books.length <= 1000
 * 1 <= books[i][0] <= shelf_width <= 1000
 * 1 <= books[i][1] <= 1000
 * Accepted
 * 10,714
 * Submissions
 * 18,680
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * Use dynamic programming: dp(i) will be the answer to the problem for books[i:].
 *
 */
public class FillingBookcaseShelves {

    /**
     * dp[i]: the min height for placing first books i - 1 on shelves
     * For dp[i+1],
     * either place book i on a new shelve => dp[i] + height[i],
     * or grab previous books together with book i and move to next level together,
     * utlitzing the sub problem dp[j] => min(dp[j] + max(height[j+1] .. height[i])),
     * where sum(width[j+1] + ... + sum(width[i]) <= shelve_width
     */
    class Solution {
        public int minHeightShelves(int[][] books, int shelf_width) {
            int[] dp = new int[books.length + 1];

            dp[0] = 0;

            for (int i = 1; i <= books.length; ++i) {
                int width = books[i-1][0];
                int height = books[i-1][1];
                dp[i] = dp[i-1] + height;
                for (int j = i - 1; j > 0 && width + books[j-1][0] <= shelf_width; --j) {
                    height = Math.max(height, books[j-1][1]);
                    width += books[j-1][0];
                    dp[i] = Math.min(dp[i], dp[j-1] + height);
                }
            }
            return dp[books.length];
        }
    }
}
