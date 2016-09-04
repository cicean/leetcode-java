/**
 * Created by cicean on 9/2/2016.
 *
 * 391. Perfect Rectangle  QuestionEditorial Solution  My Submissions
 Total Accepted: 1292
 Total Submissions: 9374
 Difficulty: Hard
 Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

 Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


 Example 1:

 rectangles = [
 [1,1,3,3],
 [3,1,4,2],
 [3,2,4,4],
 [1,3,2,4],
 [2,3,3,4]
 ]

 Return true. All 5 rectangles together form an exact cover of a rectangular region.

 Example 2:

 rectangles = [
 [1,1,2,3],
 [1,3,2,4],
 [3,1,4,2],
 [3,2,4,4]
 ]

 Return false. Because there is a gap between the two rectangular regions.

 Example 3:

 rectangles = [
 [1,1,3,3],
 [3,1,4,2],
 [1,3,2,4],
 [3,2,4,4]
 ]

 Return false. Because there is a gap in the top center.

 Example 4:

 rectangles = [
 [1,1,3,3],
 [3,1,4,2],
 [1,3,2,4],
 [2,2,4,4]
 ]

 Return false. Because two of the rectangles overlap with each other.
 Hide Company Tags
 */
public class PerfectRectangle {

    /**
     * 这道题是LeetCode第二周编程比赛的压轴题目，然而我并没有做出来，我想了两种方法都无法通过OJ的大数据集合，
     * 第一种方法是对于每一个矩形，我将其拆分为多个面积为1的单位矩形，
     * 然后以其左下方的点为标记，用一个哈希表建立每一个单位矩形和遍历到的矩形的映射，
     * 因为每个单位矩形只能属于一个矩形，否则就会有重叠，我感觉这种思路应该没错，
     * 但是由于把每一个遍历到的矩形拆分为单位矩形再建立映射很费时间，
     * 尤其是当矩形很大的时候，TLE就很正常了，
     * 后来我试的第二种方法是对于遍历到的每个矩形都和其他所有矩形检测一遍是否重叠，这种方法也是毫无悬念的TLE。

     博主能力有限，只能去论坛中找各位大神的解法，发现下面两种方法比较fancy，也比较好理解。
     首先来看第一种方法，这种方法的设计思路很巧妙，利用了mask，也就是位操作Bit Manipulation的一些技巧，下面这张图来自这个帖子：



     所有的矩形的四个顶点只会有下面蓝，绿，红三种情况，
     其中蓝表示该顶点周围没有其他矩形，T型的绿点表示两个矩形并排相邻，
     红点表示四个矩形相邻，那么在一个完美矩形中，蓝色的点只能有四个，这是个很重要的判断条件。
     我们再来看矩形的四个顶点，我们按照左下，左上，右上，右下的顺序来给顶点标号为1，2，4，8，为啥不是1，2，3，4呢，
     我们注意它们的二进制1(0001)，2(0010)，4(0100)，8(1000)，这样便于我们与和或的操作，
     我们还需要知道的一个判定条件是，当一个点是某一个矩形的左下顶点时，这个点就不能是其他矩形的左下顶点了，
     这个条件对于这四种顶点都要成立，那么对于每一个点，如果它是某个矩形的四个顶点之一，
     我们记录下来，如果在别的矩形中它又是相同的顶点，那么直接返回false即可，
     这样就体现了我们标记为1，2，4，8的好处，我们可以按位检查1。如果每个点的属性没有冲突，
     那么我们来验证每个点的mask是否合理，通过上面的分析，我们知道每个点只能是蓝，绿，红三种情况的一种，
     其中蓝的情况是mask的四位中只有一个1，分别就是1(0001)，2(0010)，4(0100)，8(1000)，而且蓝点只能有四个；
     那么对于T型的绿点，mask的四位中有两个1，那么就有六种情况，分别是12(1100), 10(1010), 9(1001), 6(0110), 5(0101), 3(0011)；
     而对于红点，mask的四位都是1，只有一种情况15(1111)，那么我们可以通过直接找mask是1，2，4，8的个数，
     也可以间接通过找不是绿点和红点的个数，看是否是四个。最后一个判定条件是每个矩形面积累加和要等于最后的大矩形的面积，
     那么大矩形的面积我们通过计算最小左下点和最大右上点来计算出来即可
     */

    /**
     * 每个绿点其实都是两个顶点的重合，每个红点都是四个顶点的重合，而每个蓝点只有一个顶点，
     * 有了这条神奇的性质就不用再去判断“每个点最多只能是一个矩形的左下，左上，右上，或右下顶点”这条性质了，
     * 我们直接用一个set，对于遍历到的任意一个顶点，如果set中已经存在了，则删去这个点，如果没有就加上，
     * 这样最后会把绿点和红点都滤去，剩下的都是蓝点，我们只要看蓝点的个数是否为四个，再加上检测每个矩形面积累加和要等于最后的大矩形的面积即可，
     */
    /**
     * 核心思想就是:能够正好围成一个矩形的情况就是:
     * 有且只有:
     *   - 最左下 最左上 最右下 最右上 的四个点只出现过一次,其他肯定是成对出现的(保证完全覆盖)
     *   - 上面四个点围成的面积,正好等于所有子矩形的面积之和(保证不重复)
     * Created by MebiuW on 16/8/29.
     */
    public class Solution {
        public boolean isRectangleCover(int[][] rectangles) {
            int left = Integer.MAX_VALUE;
            int right = Integer.MIN_VALUE;
            int top = Integer.MIN_VALUE;
            int bottom = Integer.MAX_VALUE;
            int n = rectangles.length;
            HashSet<String> flags = new HashSet<String>();
            int totalArea = 0;
            for(int i=0;i<n;i++){
                left = Math.min(left,rectangles[i][0]);
                bottom = Math.min(bottom,rectangles[i][1]);
                right = Math.max(right,rectangles[i][2]);
                top = Math.max(top,rectangles[i][3]);
                totalArea += (rectangles[i][3]-rectangles[i][1])*(rectangles[i][2]-rectangles[i][0]);
                String pointLT = rectangles[i][0] + " "+ rectangles[i][3];
                String pointLB = rectangles[i][0] + " "+ rectangles[i][1];
                String pointRT = rectangles[i][2] + " "+ rectangles[i][3];
                String pointRB = rectangles[i][2] + " "+ rectangles[i][1];
                if (!flags.contains(pointLT)) flags.add(pointLT); else flags.remove(pointLT);
                if (!flags.contains(pointLB)) flags.add(pointLB); else flags.remove(pointLB);
                if (!flags.contains(pointRT)) flags.add(pointRT); else flags.remove(pointRT);
                if (!flags.contains(pointRB)) flags.add(pointRB); else flags.remove(pointRB);
            }
            if(flags.size()==4 && flags.contains(left+" "+top) && flags.contains(left+" "+bottom) && flags.contains(right+" "+bottom) && flags.contains(right+" "+top)){
                return totalArea == (right - left) * (top - bottom);
            }
            return false;
        }
    }


}
