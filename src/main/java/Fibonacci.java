/**
 * Find the Nth number in Fibonacci sequence.

A Fibonacci sequence is defined as follow:

The first two numbers are 0 and 1.
The i th number is the sum of i-1 th number and i-2 th number.
The first ten numbers in Fibonacci sequence is:

0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...

 Notice

The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.

Have you met this question in a real interview? Yes
Example
Given 1, return 0

Given 2, return 1

Given 10, return 34
 * @author cicean
 *
 */
public class Fibonacci {

	 /**
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci(int n) {
        // write your code here
    	 int a = 0;
         int b = 1;
         for (int i = 0; i < n - 1; i++) {
             int c = a + b;
             a = b;
             b = c;
         }
         return a;
    }
    
    public int fibonacci_1(int n) {
        // write your code here
        //int res = 0;
        if (n == 0) {return 0;}
        if (n == 1) {return 1;}
        if (n == 2) {return 1;}
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
