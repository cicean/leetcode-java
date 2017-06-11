import java.util.Random;

/**
 * Created by cicean on 9/12/2016.
 * 397. Integer Replacement  QuestionEditorial Solution  My Submissions
 Total Accepted: 1612
 Total Submissions: 6763
 Difficulty: Easy
 Given a positive integer n and you can do operations as follow:

 If n is even, replace n with n/2.
 If n is odd, you can replace n with either n + 1 or n - 1.
 What is the minimum number of replacements needed for n to become 1?

 Example 1:

 Input:
 8

 Output:
 3

 Explanation:
 8 -> 4 -> 2 -> 1
 Example 2:

 Input:
 7

 Output:
 4

 Explanation:
 7 -> 8 -> 4 -> 2 -> 1
 or
 7 -> 6 -> 3 -> 2 -> 1
 Hide Company Tags Baidu
 Hide Tags Math

 */
public class IntegerReplacement {



    public int integerReplacement1(int n) {
        return helper((long)n, 0);
    }
    public int helper(long n, int pre){
        if(n <= 3) return (int)(pre + n - 1);
        if(n % 2 == 0) return helper(n/2, pre + 1);
        if(((n + 1) / 2 ) % 2 == 0) return helper(n+1, pre + 1);
        return helper(n-1, pre + 1);
    }

    public int integerReplacement(int n) {
        int count = 0;
        int[] a = {-1, 1};
        Random random = new Random();
        while (n >= 2) {
            if (n % 2 != 0) {
                n = n + a[random.nextInt(2)];
            } else {
                n = n / 2;
            }
            count++;
        }

        return count;
    }
    
    public static void main(String[] args) {
    	IntegerReplacement slt = new IntegerReplacement();
		System.out.println(slt.integerReplacement(65535));
	}
}
