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
     * ������������һ�ѵ㣬�����Ǵ治����һ��ƽ����y���ֱ�ߣ�
     * ʹ�����еĵ���ڸ�ֱ�߶Գơ���Ŀ�е���ʾ�����൱��֣�
     * ����ֻҪ������ʾ�Ĳ��������Ϳ��Խ����ˡ�
     * ���������ҵ����е�ĺ���������ֵ����Сֵ��
     * ��ô���ߵ�ƽ��ֵ�����м�ֱ�ߵĺ����꣬
     * Ȼ�����Ǳ���ÿ���㣬��������ҵ�ֱ�߶ԳƵ���һ���㣬
     * �򷵻�true����֮����false
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
     * ����ⷨ�ǿ���discuss��ģ����ż򵥣����뵽���ѡ�
     * ����Ҫ���ǲ��ǶԳƣ�����Ҫ��ÿһ�����ǲ����и��������Ӧ��
     * ��Ϊ����һ�����ظ����֣�����������hashset��������¼ÿһ�����ֵĵ㣬
     * Ȼ������hashset�������Ӧ�ĵ㡣
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
