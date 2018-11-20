package Airbnb;

/**
 * Created by cicean on 9/13/2018.
 * ÿ��?��һ��(x, y, iter)��?����iter ?????(x, y)��?�ĵ��ǵڼ�����
 �ⷨ���Ӵ���С����??����ÿ���������ǰ���ڵ�ǰiter ���ڵڼ����ޣ��ȼ���ǰ����Щ��
 ?ȥ��������ĵ㡣Ȼ���ҵ�?������?�����޵���?��?��(x,y)������?��?��?����������
 �ĵ㣬��?����?�ˣ���Ҫ��?��ӳ�䣬��(x,y)ӳ���(y,x) (��������) �� (M-y, M-x) (��������)��
 M �����޵�??��
 */
public class HilbertCurve {

    /**
     * ??????????????????????????? generation ����ǰһ��generation
     �е����޻��з�?��ÿ��?ȥ�ж����Ǹ�����Ȼ�����?����?��?һ��????����
     ��
     */

    public int hilbertCurve(int x, int y, int iter) {
        if (iter == 0) return 1;
        int len = 1 << (iter - 1);
        int num = 1 << (2 * (iter - 1));
        if (x >= len && y >= len) {
// 3 Shape is identical with previous iteration
            return 2 * num + hilbertCurve(x - len, y - len, iter - 1);
        } else if (x < len && y >= len) {
// 2 Shape is identical with previous iteration
            return num + hilbertCurve(x, y - len, iter - 1);
        } else if (x < len && y < len) {
// 1 Clock-wise rotate 90
            return hilbertCurve(y, x, iter - 1);
        } else {
// 4 Anti-Clockwise rotate 90
            return 3 * num + hilbertCurve(len - y - 1, 2 * len - x - 1, iter - 1);
        }
    }


}
