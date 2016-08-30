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
     * ���Ӷ�
     ʱ�� O(NM) �ռ� O(NM)

     ˼·
     Ϊ�˱�֤�ܳ�����С������ֻҪ��֤ÿ��·��������Ҫ�ظ������ˣ�
     ����1->2->3<-4����һά���������������1��2��4����2->3��1->2->3������·�������ظ��ˡ�
     Ϊ�˾�����֤�ұߵĵ������ߣ���ߵĵ������ߣ������Ǿ�Ӧ��ȥ��Щ���м�ĵ���Ϊ���㡣
     �����������پ��룬���ǿ��Էֿ����������������꣬�����һ���ġ�
     ��������������������굽�е������ľ��룬���ϸ��������굽�е�������ľ��룬���ǽ���ˡ�
     */

    public class Solution {
        public int minTotalDistance(int[][] grid) {
            List<Integer> ipos = new ArrayList<Integer>();
            List<Integer> jpos = new ArrayList<Integer>();
            // ͳ�Ƴ�����Щ��������
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == 1){
                        ipos.add(i);
                        jpos.add(j);
                    }
                }
            }
            int sum = 0;
            // ���������굽�������е�ľ��룬���ﲻ��Ҫ������Ϊ֮ǰͳ��ʱ�ǰ���i��˳��
            for(Integer pos : ipos){
                sum += Math.abs(pos - ipos.get(ipos.size() / 2));
            }
            // ��������굽�������е�ľ��룬������Ҫ������Ϊͳ�Ʋ��ǰ���j��˳��
            Collections.sort(jpos);
            for(Integer pos : jpos){
                sum += Math.abs(pos - jpos.get(jpos.size() / 2));
            }
            return sum;
        }
    }
}
