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
     * �������LeetCode�ڶ��ܱ�̱�����ѹ����Ŀ��Ȼ���Ҳ�û�������������������ַ������޷�ͨ��OJ�Ĵ����ݼ��ϣ�
     * ��һ�ַ����Ƕ���ÿһ�����Σ��ҽ�����Ϊ������Ϊ1�ĵ�λ���Σ�
     * Ȼ���������·��ĵ�Ϊ��ǣ���һ����ϣ����ÿһ����λ���κͱ������ľ��ε�ӳ�䣬
     * ��Ϊÿ����λ����ֻ������һ�����Σ�����ͻ����ص����Ҹо�����˼·Ӧ��û��
     * �������ڰ�ÿһ���������ľ��β��Ϊ��λ�����ٽ���ӳ��ܷ�ʱ�䣬
     * �����ǵ����κܴ��ʱ��TLE�ͺ������ˣ�
     * �������Եĵڶ��ַ����Ƕ��ڱ�������ÿ�����ζ����������о��μ��һ���Ƿ��ص������ַ���Ҳ�Ǻ��������TLE��

     �����������ޣ�ֻ��ȥ��̳���Ҹ�λ����Ľⷨ�������������ַ����Ƚ�fancy��Ҳ�ȽϺ���⡣
     ����������һ�ַ��������ַ��������˼·�����������mask��Ҳ����λ����Bit Manipulation��һЩ���ɣ���������ͼ����������ӣ�



     ���еľ��ε��ĸ�����ֻ�������������̣������������
     ��������ʾ�ö�����Χû���������Σ�T�͵��̵��ʾ�������β������ڣ�
     ����ʾ�ĸ��������ڣ���ô��һ�����������У���ɫ�ĵ�ֻ�����ĸ������Ǹ�����Ҫ���ж�������
     �������������ε��ĸ����㣬���ǰ������£����ϣ����ϣ����µ�˳������������Ϊ1��2��4��8��Ϊɶ����1��2��3��4�أ�
     ����ע�����ǵĶ�����1(0001)��2(0010)��4(0100)��8(1000)����������������ͻ�Ĳ�����
     ���ǻ���Ҫ֪����һ���ж������ǣ���һ������ĳһ�����ε����¶���ʱ�������Ͳ������������ε����¶����ˣ�
     ����������������ֶ��㶼Ҫ��������ô����ÿһ���㣬�������ĳ�����ε��ĸ�����֮һ��
     ���Ǽ�¼����������ڱ�ľ�������������ͬ�Ķ��㣬��ôֱ�ӷ���false���ɣ�
     ���������������Ǳ��Ϊ1��2��4��8�ĺô������ǿ��԰�λ���1�����ÿ���������û�г�ͻ��
     ��ô��������֤ÿ�����mask�Ƿ����ͨ������ķ���������֪��ÿ����ֻ���������̣������������һ�֣�
     �������������mask����λ��ֻ��һ��1���ֱ����1(0001)��2(0010)��4(0100)��8(1000)����������ֻ�����ĸ���
     ��ô����T�͵��̵㣬mask����λ��������1����ô��������������ֱ���12(1100), 10(1010), 9(1001), 6(0110), 5(0101), 3(0011)��
     �����ں�㣬mask����λ����1��ֻ��һ�����15(1111)����ô���ǿ���ͨ��ֱ����mask��1��2��4��8�ĸ�����
     Ҳ���Լ��ͨ���Ҳ����̵�ͺ��ĸ��������Ƿ����ĸ������һ���ж�������ÿ����������ۼӺ�Ҫ�������Ĵ���ε������
     ��ô����ε��������ͨ��������С���µ��������ϵ��������������
     */

    /**
     * ÿ���̵���ʵ��������������غϣ�ÿ����㶼���ĸ�������غϣ���ÿ������ֻ��һ�����㣬
     * ����������������ʾͲ�����ȥ�жϡ�ÿ�������ֻ����һ�����ε����£����ϣ����ϣ������¶��㡱���������ˣ�
     * ����ֱ����һ��set�����ڱ�����������һ�����㣬���set���Ѿ������ˣ���ɾȥ����㣬���û�оͼ��ϣ�
     * ����������̵�ͺ�㶼��ȥ��ʣ�µĶ������㣬����ֻҪ������ĸ����Ƿ�Ϊ�ĸ����ټ��ϼ��ÿ����������ۼӺ�Ҫ�������Ĵ���ε�������ɣ�
     */
    /**
     * ����˼�����:�ܹ�����Χ��һ�����ε��������:
     * ����ֻ��:
     *   - ������ ������ ������ ������ ���ĸ���ֻ���ֹ�һ��,�����϶��ǳɶԳ��ֵ�(��֤��ȫ����)
     *   - �����ĸ���Χ�ɵ����,���õ��������Ӿ��ε����֮��(��֤���ظ�)
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
