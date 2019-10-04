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
* To execute Java, please define "static void main" on a class. 丢��?-三分-地，独家发布
* named Solution.
*. From 1point 3acres bbs
* If you need more classes, simply define them inline.

Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn).
We want to find a way to round each element in A such that after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T where  yi = Floor(xi) or Ceil(xi), ceiling or floor of xi.. Waral 博客有更多文�?,
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

    // ˼·�����Ȱ�����floor��������Ȼ�󿴲���٣�Ȼ��Ѷ��ٸ�floorת��ceil
    // ת��ʱ����num������ceil�ľ�������
    public int[] getNearlyArrayWithSameSum(double[] arr) {
        NumWithDiff[] arrWithDiff = new NumWithDiff[arr.length];
        double sum = 0.0;
        int floorSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int floor = (int) arr[i];
            int ceil = floor;
            if (floor < arr[i]) ceil++; // ���ǲ���4.0����floor/ceil���Ǳ����
            floorSum += floor;
            sum += arr[i];
            arrWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i]); // ��ceil����������������бȽ�. more info on 1point3acres.com
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
            res[i] = arrWithDiff[i].num; // ��Щ��ceil
        }
        for (; i < arr.length; i++) {
            res[i] = arrWithDiff[i].num - 1; // ʣ�µ�ֻ��floor
        }
        return res;
    }


}




