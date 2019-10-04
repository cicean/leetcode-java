import java.util.*;

/**
 * Created by cicean on 8/31/2016.
 * 356. Line Reflection  QuestionEditorial Solution  My Submissions
 Total Accepted: 3303 Total Submissions: 11501 Difficulty: Medium
 Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

 Example 1:
 Given points = [[1,1],[-1,1]], return true.

 Example 2:
 Given points = [[1,1],[-1,-1]], return false.

 Follow up:
 Could you do better than O(n2)?

 Hint:

 Find the smallest and largest x-value for all points.
 If there is a line then it should be at y = (minX + maxX) / 2.
 For each point, make sure that it has a reflected point in the opposite side.
 Credits:
 Special thanks to @memoryless for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Hash Table Math
 Hide Similar Problems (H) Max Points on a Line

 */
public class LineReflection {

    /**
     * 这道题给了我们一堆点，问我们存不存在一条平行于y轴的直线，
     * 使得所有的点关于该直线对称。题目中的提示给的相当充分，
     * 我们只要按照提示的步骤来做就可以解题了。
     * 首先我们找到所有点的横坐标的最大值和最小值，
     * 那么二者的平均值就是中间直线的横坐标，
     * 然后我们遍历每个点，如果都能找到直线对称的另一个点，
     * 则返回true，反之返回false
     */

    public class Solution {
        public boolean isReflected(int[][] points) {
            int max = 0, min = 0;
            HashMap<Integer, HashSet<Integer>> hashmap = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                max = Math.max(points[i][0], max);
                min = Math.min(points[i][0], min);
                if (!hashmap.containsKey(points[i][1])) {
                    HashSet<Integer> hashset = new HashSet<>();
                    hashset.add(points[i][0]);
                    hashmap.put(points[i][1], hashset);
                } else {
                    hashmap.get(points[i][1]).add(points[i][0]);
                }
            }
            for (int i = 0; i < points.length; i++) {
                if (!hashmap.containsKey(points[i][1]) || !hashmap.get(points[i][1]).contains(max + min - points[i][0])) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 这个解法是看的discuss里的，看着简单，但想到很难。
     * 我们要求是不是对称，就是要求每一个点是不是有个点跟它对应。
     * 因为可以一个点重复出现，决定我们用hashset来做。记录每一个出现的点，
     * 然后再用hashset来找其对应的点。
     */
    public boolean isReflected(int[][] points) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<String> set = new HashSet<String>();
        for (int[] p : points) {
            set.add(p[0] + "," + p[1]);
            min = Math.min(min, p[0]);
            max = Math.max(max, p[0]);
        }

        int sum = min + max;
        for (int[] p : points) {
            if (!set.contains((sum - p[0]) + "," + p[1])) {
                return false;
            }
        }
        return true;
    }


}
