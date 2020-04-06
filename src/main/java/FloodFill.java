/**
 * 733. Flood Fill
 * Easy
 *
 * 772
 *
 * 151
 *
 * Add to List
 *
 * Share
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 *
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 *
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
 *
 * At the end, return the modified image.
 *
 * Example 1:
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 * Note:
 *
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 * Accepted
 * 89.2K
 * Submissions
 * 168.1K
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * jatermelon
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 11
 *
 * Palantir Technologies
 * |
 * 7
 *
 * Apple
 * |
 * 4
 *
 * Snapchat
 * |
 * 2
 *
 * Qualtrics
 * |
 * 2
 *
 * Microsoft
 * |
 * 2
 * Island Perimeter
 * Easy
 * Write a recursive function that paints the pixel if it's the correct color, then recurses on neighboring pixels.
 */
public class FloodFill {

    /**
     * Approach #1: Depth-First Search [Accepted]
     * Intuition
     *
     * We perform the algorithm explained in the problem description: paint the starting pixels, plus adjacent pixels of the same color, and so on.
     *
     * Algorithm
     *
     * Say color is the color of the starting pixel. Let's floodfill the starting pixel: we change the color of that pixel to the new color, then check the 4 neighboring pixels to make sure they are valid pixels of the same color, and of the valid ones, we floodfill those, and so on.
     *
     * We can use a function dfs to perform a floodfill on a target pixel.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N), where NN is the number of pixels in the image. We might process every pixel.
     *
     * Space Complexity: O(N)O(N), the size of the implicit call stack when calling dfs.
     */

    class Solution {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int color = image[sr][sc];
            if (color != newColor) dfs(image, sr, sc, color, newColor);
            return image;
        }
        public void dfs(int[][] image, int r, int c, int color, int newColor) {
            if (image[r][c] == color) {
                image[r][c] = newColor;
                if (r >= 1) dfs(image, r-1, c, color, newColor);
                if (c >= 1) dfs(image, r, c-1, color, newColor);
                if (r+1 < image.length) dfs(image, r+1, c, color, newColor);
                if (c+1 < image[0].length) dfs(image, r, c+1, color, newColor);
            }
        }
    }

}
