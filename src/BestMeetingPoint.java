import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cicean on 8/29/2016.
 *
 * 296. Best Meeting Point
 *
 * Total Accepted: 6720 Total Submissions: 13745 Difficulty: Hard
 A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

 For example, given three people living at (0,0), (0,4), and (2,2):

 1 - 0 - 0 - 0 - 1
 |   |   |   |   |
 0 - 0 - 0 - 0 - 0
 |   |   |   |   |
 0 - 0 - 1 - 0 - 0
 The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

 Hint:

 Try to solve it in one dimension first. How can this solution apply to the two dimension case?
 Hide Company Tags Twitter
 Hide Tags Math Sort
 Hide Similar Problems (H) Shortest Distance from All Buildings

 */
public class BestMeetingPoint {

    /**
     * 复杂度
     时间 O(NM) 空间 O(NM)

     思路
     为了保证总长度最小，我们只要保证每条路径尽量不要重复就行了，
     比如1->2->3<-4这种一维的情况，如果起点是1，2和4，那2->3和1->2->3这两条路径就有重复了。
     为了尽量保证右边的点向左走，左边的点向右走，那我们就应该去这些点中间的点作为交点。
     由于是曼哈顿距离，我们可以分开计算横坐标和纵坐标，结果是一样的。
     所以我们算出各个横坐标到中点横坐标的距离，加上各个纵坐标到中点纵坐标的距离，就是结果了。
     */

    public class Solution {
        public int minTotalDistance(int[][] grid) {
            List<Integer> ipos = new ArrayList<Integer>();
            List<Integer> jpos = new ArrayList<Integer>();
            // 统计出有哪些横纵坐标
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == 1){
                        ipos.add(i);
                        jpos.add(j);
                    }
                }
            }
            int sum = 0;
            // 计算纵坐标到纵坐标中点的距离，这里不需要排序，因为之前统计时是按照i的顺序
            for(Integer pos : ipos){
                sum += Math.abs(pos - ipos.get(ipos.size() / 2));
            }
            // 计算横坐标到横坐标中点的距离，这里需要排序，因为统计不是按照j的顺序
            Collections.sort(jpos);
            for(Integer pos : jpos){
                sum += Math.abs(pos - jpos.get(jpos.size() / 2));
            }
            return sum;
        }
    }
}
