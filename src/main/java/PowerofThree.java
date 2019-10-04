/**
 * Created by cicean on 8/30/2016.
 * 326. Power of Three  QuestionEditorial Solution  My Submissions
 Total Accepted: 61902
 Total Submissions: 161371
 Difficulty: Easy
 Given an integer, write a function to determine if it is a power of three.

 Follow up:
 Could you do it without using any loop / recursion?

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Math
 Hide Similar Problems (E) Power of Two (E) Power of Four

 */
public class PowerofThree {

    //1. Recursion -- 20 ms beats 24%
    public class Solution {
        public boolean isPowerOfThree(int n) {
            return n>0 && (n==1 || (n%3==0 && isPowerOfThree(n/3)));
        }
    }

    //2. Iteration -- 15-17 ms beats 72.54%-91.74%
    public class Solution2 {
        public boolean isPowerOfThree(int n) {
            if (n > 1)
                while (n % 3 == 0) n /= 3;
            return n == 1;
        }
    }

    //know the Integer MAX of power
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        return Math.pow(3, 20) % n == 0;
    }
}
