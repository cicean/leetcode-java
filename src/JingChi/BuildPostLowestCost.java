package JingChi;

import java.util.*;

public class BuildPostLowestCost {

    /**
     * 假设位置为x[]，仓库的位置为avg，距离总和为sum。
     * cost = (x[0] - avg)^2 + (x[1] - avg)^2 + ... + (x[n-1] - avg)^2
     *      = (x[0]^2 + x[1]^2 + ... + x[n-1]^2) - 2 * (x[0] + x[1] + ... + x[n-1]) * avg + n * avg^2
     *      = c(常数) - 2 * b(常数) * avg + a(常数) * avg^2
     * 极值 = (-b) / (2*a) = 2 * sum / (2 * n) = sum / n = 平均数
     *
     * 作者：_Kazusa
     * 链接：https://www.jianshu.com/p/51d07e5a4b7c
     * 來源：简书
     * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     * @param locations
     * @return
     */
    // matrix
    public int shortestDistance(int[][] grid) {
        // Write your code here
        int n = grid.length;
        if (n == 0)
            return -1;

        int m = grid[0].length;
        if (m == 0)
            return -1;

        List<Integer> sumx = new ArrayList<Integer>();
        List<Integer> sumy = new ArrayList<Integer>();
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (grid[i][j] == 1) {
                    x.add(i);
                    y.add(j);
                }

        Collections.sort(x);
        Collections.sort(y);

        int total = x.size();

        sumx.add(0);
        sumy.add(0);
        for (int i = 1; i <= total; ++i) {
            sumx.add(sumx.get(i-1) + x.get(i-1));
            sumy.add(sumy.get(i-1) + y.get(i-1));
        }

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (grid[i][j] == 0) {
                    int cost_x = get_cost(x, sumx, i, total);
                    int cost_y = get_cost(y, sumy, j, total);
                    if (cost_x + cost_y < result)
                        result = cost_x + cost_y;
                }

        return result;
    }

    public int get_cost(List<Integer> x, List<Integer> sum, int pos, int n) {
        if (n == 0)
            return 0;
        if (x.get(0) > pos)
            return sum.get(n) - pos * n;

        int l = 0, r = n - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (x.get(mid) <= pos)
                l = mid;
            else
                r = mid - 1;
        }

        int index = 0;
        if (x.get(r) <= pos)
            index = r;
        else
            index = l;
        return sum.get(n) - sum.get(index + 1) - pos * (n - index - 1) +
                (index + 1) * pos - sum.get(index + 1);
    }



}
