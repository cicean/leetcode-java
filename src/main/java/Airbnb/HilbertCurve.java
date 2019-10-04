package Airbnb;

/**
 * Created by cicean on 9/13/2018.
 * 每次?你一个(x, y, iter)，?你在iter ?????(x, y)坐?的点是第几个？
 解法：从大往小左，逐??化。每次先算出当前点在当前iter 是在第几象限，先加上前面那些跳
 ?去的象限里的点。然后找到?个点在?个象限的相?坐?新(x,y)，但是?不?！?于三四象限
 的点，因?方向?了，需要做?面映射，把(x,y)映射成(y,x) (第三象限) 或 (M-y, M-x) (第四象限)，
 M 是象限的??。
 */
public class HilbertCurve {

    /**
     * ??????????????????????????? generation 是由前一个generation
     有的象限会有翻?。每次?去判断在那个象限然后把相?的坐?翻?一下????就行
     了
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
