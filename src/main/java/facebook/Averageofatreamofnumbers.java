package facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 9/7/2016.
 * Average of a stream of numbers
 Difficulty Level: Rookie
 Given a stream of numbers, print average (or mean) of the stream at every point. For example, let us consider the stream as 10, 20, 30, 40, 50, 60, бн

 Average of 1 numbers is 10.00
 Average of 2 numbers is 15.00
 Average of 3 numbers is 20.00
 Average of 4 numbers is 25.00
 Average of 5 numbers is 30.00
 Average of 6 numbers is 35.00
 ..................
 To print mean of a stream, we need to find out how to find average when a new number is being added to the stream. To do this, all we need is count of numbers seen so far in the stream, previous average and new number. Let n be the count, prev_avg be the previous average and x be the new number being added. The average after including x number can be written as (prev_avg*n + x)/(n+1).

 #include <stdio.h>

 // Returns the new average after including x
 float getAvg(float prev_avg, int x, int n)
 {
 return (prev_avg*n + x)/(n+1);
 }
 */
public class Averageofatreamofnumbers {

    public List<Double> movingAverage(int[] numbers, int windowSize) {
        List<Double> res = new ArrayList<>();
        int j = 0;
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum = sum + numbers[i];

            if (i - j < windowSize - 1) {
                res.add((double)numbers[i]);
            } else {
                double tmp = (double) sum / windowSize;
                res.add(tmp);
                sum = sum - numbers[j];
                j++;
            }
        }
        return res;
    }
}
