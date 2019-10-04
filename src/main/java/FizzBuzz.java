import java.util.*;

/**
 * Created by cicean on 10/16/2016.
 * 412. Fizz Buzz   QuestionEditorial Solution  My Submissions
 Total Accepted: 2801
 Total Submissions: 4786
 Difficulty: Easy
 Contributors: Admin
 Write a program that outputs the string representation of numbers from 1 to n.

 But for multiples of three it should output ¡°Fizz¡± instead of the number and for the multiples of five output ¡°Buzz¡±. For numbers which are multiples of both three and five output ¡°FizzBuzz¡±.

 Example:

 n = 15,

 Return:
 [
 "1",
 "2",
 "Fizz",
 "4",
 "Buzz",
 "Fizz",
 "7",
 "8",
 "Fizz",
 "Buzz",
 "11",
 "Fizz",
 "13",
 "14",
 "FizzBuzz"
 ]
 */
public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String tmp = "";
            if (i % 3 == 0) {
                tmp = "Fizz";
            }

            if (i % 5 == 0) {
                tmp = tmp + "Buzz";
            }

            if (tmp.isEmpty()) {
                res.add(String.valueOf(i));
            } else {
                res.add(tmp);
            }
        }

        return res;
    }
}
