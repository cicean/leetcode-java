package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 * Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn). . From 1point 3acres bbs. 围观我们@1point 3 acres
 We want to find a way to round each element in A such that after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T where  yi = Floor(xi) or Ceil(xi), ceiling or floor of xi.
 We also want to minimize sum |x_i-y_i|
 http://massivealgorithms.blogspot.com/2017/07/round-numbers-airbnb.html
 那么问题就来了，给个input list of floating points, 要求output list of integers, 满足以下两个constraint， 就是和跟Round(x1+x2+... +xn)的结果一样，但是minimize output 和input的绝对值差之和
 #Input: A = [x1, x2, ..., xn]
 # Sum T = Round(x1+x2+... +xn)  ;  178.93E => 179
 # Output: B = [y1, y2, ...., yn]

 # Constraint #1: y1+y2+...+yn = T
 # Constraint #2: minimize sum(abs(diff(xi - yi)))

 举例. visit 1point3acres.com for more.
 # A = [1.2, 2.3, 3.4]
 # Round(1.2 + 2.3 + 3.4) = 6.9 => 7
 # 1 + 2 + 3 => 6
 .鐣欏璁哄潧-涓€浜?-涓夊垎鍦?
 # 1 + 3 + 3 => 7. 鍥磋鎴戜滑@1point 3 acres
 # 0.2 + 0.7 + 0.4 = 1.3

 # 1 + 2 + 4 => 7
 # 0.2 + 0.3 + 0.6 = 1.1
 所以[1,2,4]比[1,3,3]要好
 */
public class RoundingPrice {

    private int floorSum;

    public int[] roundUp(double[] arr) {
        int n = arr.length;
        NumWithDiff[] arrWithDiff = new NumWithDiff[n];
        double sum = 0.0;
        int floorSum = 0;
        for (int i = 0; i < n; i++) {
            int floor = (int) arr[i];
            int ceil = floor;
            if (floor < arr[i]) ceil++;
            floorSum += floor;
            sum += arr[i];
            arrWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i]);
        }
        int num = (int) Math.round(sum);
        int diff = num - floorSum;
        Arrays.sort(arrWithDiff, new Comparator<NumWithDiff>() {
            @Override
            public int compare(NumWithDiff n1, NumWithDiff n2) {
                if (n1.diffWithCeil <= n2.diffWithCeil) return -1;
                else return 1;
            }
        });
        Arrays.sort(arrWithDiff, (a, b) ->
        (Double.compare(b.diffWithCeil, a.diffWithCeil)));
        int[] res = new int[n];
        int i = 0;
        for (; i < diff; i++) {
            res[i] = arrWithDiff[i].num; // 这些放 ceil
        }
        for (; i < n; i++) {
            res[i] = arrWithDiff[i].num - 1; // 剩下的只放 floor
        }
        return res;
    }
    class NumWithDiff {
        int num;
        double diffWithCeil;
        public NumWithDiff(int n, double c) {
            this.num = n;
            this.diffWithCeil = c;
        }
    }

    public int[] round(Double[] input) {
        if(input == null || input.length == 0) return new int[0];

        Queue<Double> minCeil = new PriorityQueue<Double>(input.length, new Comparator<Double>() {
            public int compare(Double x, Double y) {
                return Double.compare(Math.ceil(x) - x, Math.ceil(y) - y);
            }
        });
        // Get offset
        Double sum = 0.0;
        int floorSum = 0;
        int[] res = new int[input.length];
        for(int i = 0; i < input.length; i++) {
            sum += input[i];
            int floor = (int) Math.floor(input[i]);
            floorSum += floor;
            res[i] = floor;
            minCeil.add(input[i]);
        }
        // Numbers contributing to larger
        int offset = (int) Math.round(sum) - floorSum;
        Set<Double> mins = new HashSet<>();
        for(int i = 0; i < offset; i++) {
            mins.add(minCeil.poll());
        }

        for(int i = 0; i < res.length && offset > 0; i++) {
            if(mins.contains(input[i])) {
                res[i]++;
                offset--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java 8.");

        for (String string : strings) {
            System.out.println(string);
        }

        Solution solution = new Solution();

        Double[] inputs = {1.2, 2.3, 3.4};

        System.out.print(Arrays.toString(solution.round(inputs)));


    }
}
