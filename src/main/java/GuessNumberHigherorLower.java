/**
 * Created by cicean on 9/1/2016.
 *374. Guess Number Higher or Lower  QuestionEditorial Solution  My Submissions
 Total Accepted: 18949 Total Submissions: 59976 Difficulty: Easy
 We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number is higher or lower.

 You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

 -1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
 Example:
 n = 10, I pick 6.

 Return 6.
 Hide Company Tags Google
 Hide Tags Binary Search
 Hide Similar Problems (E) First Bad Version (M) Guess Number Higher or Lower II

 题意：猜数字，给定一个数n，要你调用guess函数，来判断你猜的n和对方的值是否一样。
 */
public class GuessNumberHigherorLower {

    /**
     * What we learn from this problem?

     low+(high-low)/2 yields the same value with (low+high)/2. However, the first expression is less expensive. In addition, the following expression can be used:

     low+((high-low)>>1)
     (low+high)>>>1
     Under the assumption that high and low are both non-negative, we know for sure that the upper-most bit (the sign-bit) is zero.

     So both high and low are in fact 31-bit integers.

     high = 0100 0000 0000 0000 0000 0000 0000 0000 = 1073741824
     low  = 0100 0000 0000 0000 0000 0000 0000 0000 = 1073741824
     When you add them together they may "spill" over into the top-bit.

     high + low =       1000 0000 0000 0000 0000 0000 0000 0000
     =  2147483648 as unsigned 32-bit integer
     = -2147483648 as signed   32-bit integer

     (high + low) / 2   = 1100 0000 0000 0000 0000 0000 0000 0000 = -1073741824
     (high + low) >>> 1 = 0100 0000 0000 0000 0000 0000 0000 0000 = 1073741824

     * @param n
     * @return
     */

    //This is a typical binary search problem. Here is a Java solution.
    public int guessNumber(int n) {
        int low=1;
        int high=n;

        while(low <= high){
            int mid = low+((high-low)/2);
            int result = guess(mid);
            if(result==0){
                return mid;
            }else if(result==1){
                low = mid+1;
            }else{
                high=mid-1;
            }
        }

        return -1;
    }
}
