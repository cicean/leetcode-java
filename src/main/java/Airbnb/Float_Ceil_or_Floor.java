package Airbnb;

import java.util.*;


/**
 * Created by cicean on 10/30/2016.
 */

class NumWithDiff {
    int num;
    double diffWithCeil;
    public NumWithDiff(int n, double c) {
        this.num = n;
        this.diffWithCeil = c;
    }
}

class CPP implements Comparator< Double > {
    public int compare(Double d1, Double d2) {
        d1 = d1 - Math.floor(d1);
        d2 = d2 - Math.floor(d2);
        if (d2 > d1) {
            return 1;
        } else {
            return -1;
        }
    }
}
class CPN implements Comparator< Double > {
    public int compare(Double d1, Double d2) {
        d1 = d1 - Math.floor(d1);
        d2 = d2 - Math.floor(d2);
        if (d1 > d2) {
            return 1;
        } else {
            return -1;
        }
    }
}

public class Float_Ceil_or_Floor {

    /*
* To execute Java, please define "static void main" on a class. 涓€浜?-涓夊垎-鍦帮紝鐙鍙戝竷
* named Solution.
*. From 1point 3acres bbs
* If you need more classes, simply define them inline.

Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn).
We want to find a way to round each element in A such that after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T where  yi = Floor(xi) or Ceil(xi), ceiling or floor of xi.. Waral 鍗氬鏈夋洿澶氭枃绔?,
We also want to minimize sum |x_i-y_i|
*/
    public int[] Rounding(double[] inputs) {
        double sum = 0;
        int sRound = 0; // separately rounded
        List<Double> ls = new ArrayList<Double>();
        for (double d : inputs) {
            sum += d;
            sRound += (int) Math.round(d);
            ls.add(d);
        }
        int tRound = (int) Math.round(sum); // together rounded
        int diff = tRound - sRound;
        boolean over = true;
        if (diff >= 0) {
            Collections.sort(ls, new CPP());
        } else {
            Collections.sort(ls, new CPN());
            over = false;
        }
        diff = (int) Math.abs(diff);
        Set<Double> set = new HashSet<Double>();
        for (int i = 0; i < diff; ++i) {
            set.add(ls.get(i));
        }
        int[] res = new int[inputs.length];
        for (int i = 0; i < res.length; ++i) {
            double d = inputs[i];
            int tmp = (int) Math.round(d);
            if (set.contains(d) && diff > 0) {
                if (over) {
                    ++tmp;
                } else {
                    --tmp;
                }
                --diff;
            }
            res[i] = tmp;
        }
        return res;
    }

    // 思路就是先把所有floor加起来，然后看差多少，然后把多少个floor转成ceil
    // 转的时候按照num本身与ceil的距离排序
    public int[] getNearlyArrayWithSameSum(double[] arr) {
        NumWithDiff[] arrWithDiff = new NumWithDiff[arr.length];
        double sum = 0.0;
        int floorSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int floor = (int) arr[i];
            int ceil = floor;
            if (floor < arr[i]) ceil++; // 查是不是4.0这种floor/ceil都是本身的
            floorSum += floor;
            sum += arr[i];
            arrWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i]); // 把ceil都放在数组里面进行比较. more info on 1point3acres.com
        }
        int num = (int) Math.round(sum);
        int diff = num - floorSum;
        Arrays.sort(arrWithDiff, new Comparator<NumWithDiff>() {
            public int compare(NumWithDiff n1, NumWithDiff n2) {
                if (n1.diffWithCeil <= n2.diffWithCeil) return -1;
                else return 1;
            }
        });
        int[] res = new int[arr.length];
        int i = 0;
        for (; i < diff; i++) {
            res[i] = arrWithDiff[i].num; // 这些放ceil
        }
        for (; i < arr.length; i++) {
            res[i] = arrWithDiff[i].num - 1; // 剩下的只放floor
        }
        return res;
    }


}




