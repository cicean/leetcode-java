/**
 * Created by cicean on 8/30/2016.
 * 342. Power of Four  QuestionEditorial Solution  My Submissions
 Total Accepted: 35109
 Total Submissions: 97145
 Difficulty: Easy
 Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

 Example:
 Given num = 16, return true. Given num = 5, return false.

 Follow up: Could you solve it without loops/recursion?

 Credits:
 Special thanks to @yukuairoy for adding this problem and creating all test cases.

 Hide Company Tags Two Sigma
 Hide Tags Bit Manipulation
 Hide Similar Problems (E) Power of Two (E) Power of Three

 */
public class PowerofFour {

    //Bit Manipulation -- 2ms beats 22.59%
    public class Solution {
        public boolean isPowerOfFour(int num) {
            return (num&(num-1))==0 && num>0 && (num-1)%3==0;
        }
    }

    //2. Iteration -- 2 ms beats 22.59%
    public class Solution2 {
        public boolean isPowerOfFour(int n) {
            if (n > 1)
                while (n % 4 == 0) n /= 4;
            return n == 1;
        }
    }

    public class Solution3 {
        public boolean isPowerOfFour(int num) {
            if (num == 1) return true;
            int lastdigit = num % 10;
            return num > 1 && Math.pow(4, 15) % num == 0 && (lastdigit == 4 || lastdigit == 6);
        }
    }


}
