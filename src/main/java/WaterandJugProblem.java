import java.util.*;

/**
 * Created by cicean on 9/1/2016.
 * 365. Water and Jug Problem  QuestionEditorial Solution  My Submissions
 Total Accepted: 6070 Total Submissions: 25413 Difficulty: Medium
 You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.

 If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

 Operations allowed:

 Fill any of the jugs completely with water.
 Empty any of the jugs.
 Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 Example 1: (From the famous "Die Hard" example)

 Input: x = 3, y = 5, z = 4
 Output: True
 Example 2:

 Input: x = 2, y = 6, z = 5
 Output: False
 Credits:
 Special thanks to @vinod23 for adding this problem and creating all test cases.

 Hide Company Tags Microsoft
 Hide Tags Math

 *
 * ����: �����������ӣ����Ƿֱ���װx��y��ˮ���������Ƿ���ǡ�ò�����z����ˮ��
 */
public class WaterandJugProblem {


    /**
     * ��Ŀ���Ա�ʾΪ���µ�ʽ:

     mx + ny = z
     ��m,n>0˵���������ˮ����m,n <0��˵�����
     ��x=3��y=5 ������  3 x  + (-1)y = 4 �� m=3 , n=-1 ��Ҳ����˵������Ҫ��3�������װ��3�Σ�5������ӵ���1��

     ������3�ı���ע��ˮ��Ȼ���뵹��5    �������Ϊ�� 0��3
     ���Ű�3�ı���ע��ˮ���ڵ���5 ���������Ϊ  1��5
     ���Ű�5�ı��ӵ����� ��3�ı���װ��5�� ����Ϊ 0��1
     ���Ű�3�ı���ע��ˮ������5�� ����0��4
     ���Բο�  �� http://www.math.tamu.edu/~dallen/hollywood/diehard/diehard.htm

     �����У���mx + ny = z �н⣬��һ����zΪGCD(x,y) ���������������޽⡣

     ����Ҫ�������� x + y >=z
     */
    public class Solution {
        public boolean canMeasureWater(int x, int y, int z) {
            return x + y == z || (x + y > z ) && z % gcd(x,y) == 0;
        }

        private int gcd(int a,int b){
            return b==0? a: gcd(b,a%b);
        }
    }

    /**
     * ˼·��������������һ�£���ʵ���Եȼ۱任Ϊ��ax+by=c������a,b�ֱ�Ϊƿx,y��������cΪ�������Ҫ�ģ�������ԭ�������ת��Ϊ������ax+by=c����ֱ���ϣ��Ƿ����һ���㣬������������
     ax+by=c ���У�x,yΪ����

     �ǽ������Ĺ���������ô�ҵ������ˡ����Ǽ���a
     */
    public class Solution2 {
        public boolean canMeasureWater(int x, int y, int z) {
            if(x>y)
                return canMeasureWater(y,x,z);
            if(z > x+y)
                return false;
            Set<Integer> failSet = new HashSet<>();

            int resX = 0;
            int resY = 0;

            while(true){
                int res = resX * x + resY * y;
                if(failSet.contains(res))
                    return false;
                if(res == z){
                    return true;
                }else if(res < z){
                    resY++;
                }else{
                    resX--;
                }
                failSet.add(res);
            }
        }
    }
}
