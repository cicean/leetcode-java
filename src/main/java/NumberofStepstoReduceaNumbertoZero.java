/**
 * 1342. Number of Steps to Reduce a Number to Zero
 * Easy
 *
 * 240
 *
 * 27
 *
 * Add to List
 *
 * Share
 * Given a non-negative integer num, return the number of steps to reduce it to zero. If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 14
 * Output: 6
 * Explanation:
 * Step 1) 14 is even; divide by 2 and obtain 7.
 * Step 2) 7 is odd; subtract 1 and obtain 6.
 * Step 3) 6 is even; divide by 2 and obtain 3.
 * Step 4) 3 is odd; subtract 1 and obtain 2.
 * Step 5) 2 is even; divide by 2 and obtain 1.
 * Step 6) 1 is odd; subtract 1 and obtain 0.
 * Example 2:
 *
 * Input: num = 8
 * Output: 4
 * Explanation:
 * Step 1) 8 is even; divide by 2 and obtain 4.
 * Step 2) 4 is even; divide by 2 and obtain 2.
 * Step 3) 2 is even; divide by 2 and obtain 1.
 * Step 4) 1 is odd; subtract 1 and obtain 0.
 * Example 3:
 *
 * Input: num = 123
 * Output: 12
 *
 *
 * Constraints:
 *
 * 0 <= num <= 10^6
 * Accepted
 * 54,377
 * Submissions
 * 63,067
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * Simulate the process to get the final answer.
 */
public class NumberofStepstoReduceaNumbertoZero {

    /**
     * Solution
     * Approach 1: Simulation
     * Intuition
     *
     * The most intuitive, and easiest, solution is to simply simulate the rules
     * and count how many steps are carried out in order to reach zero.
     *
     * As an example, assume our starting value is 43. The steps we need to take will be:
     *
     * 43 # Odd, subtract 1
     * 42 # Even, divide by 2
     * 21 # Odd, subtract 1
     * 20 # Even, divide by 2
     * 10 # Even, divide by 2
     *  5 # Odd, subtract 1
     *  4 # Even divide by 2
     *  2 # Even, divide by 2
     *  1 # Odd, subtract 1
     *  0
     * This is a total of 9 steps.
     *
     * Here's an animation of this same algorithm being used with a starting value of 211.
     *
     * Current
     * 1 / 12
     * Algorithm
     *
     * The algorithm works by simulating each step of the rules; if the current number is even then divide it by 2.
     * Else if it's odd, subtract 1 from it. Each time we perform one of these actions, we increment the steps we've
     * taken by 1 so that we can return it at the end.
     *
     * To check if a number is even or odd, we can use the modulus (%) operator. Recall that the modulus operator
     * gives us the remainder when we divide two numbers. If number % 2 is 1, then we know number must be odd.
     * Otherwise, the only other value it could be is 0, which means number must be even.
     *
     *
     * Complexity Analysis
     *
     * Let n = numn=num.
     *
     * Time Complexity : O(\log \, n)O(logn).
     *
     * At each step, what we did depended on whether the remaining numnum was odd or even.
     * If numnum was even, we halved what was left. If it was odd, we only subtracted 11.
     * However, by subtracting 11, we were making it even, and so on the next step we were guaranteed to halve it.
     *
     * What this means is that in the worst case, we're halving it on every second step. We treat the \frac{1}{2}
     * 2
     * 1
     * ​
     *   of the time as a constant though, so in essence, we say that at each step, numnum is being halved.
     *
     * When something is halved at every step, it has a O(\log \, n)O(logn) time complexity.
     *
     * Space Complexity : O(1)O(1).
     *
     * We only use a constant number of integer variables, and so the space complexity is O(1)O(1).
     *
     * It's impossible for us to do better than a time complexity of O(\log \,
     * n)O(logn)—unless we were willing to hardcode all 1 million possible cases we could be given
     * (the problem statement says 0 <= num <= 10^6). But we really don't want to do that! By the end of this article,
     * you'll be able to see why it's impossible for an algorithm to do better.
     *
     */

    public int numberOfSteps(int num) {
        int steps = 0; // We need to keep track of how many steps this takes.
        while (num != 0) { // Remember, we're taking steps until num is 0.
            if (num % 2 == 0) { // Modulus operator tells us num is *even*.
                num = num / 2; // So we divide num by 2.
            } else { // Otherwise, num must be *odd*.
                num = num - 1; // So we subtract 1 from num.
            }
            steps = steps + 1; // We *always* increment steps by 1.
        }
        return steps; // And at the end, the answer is in steps so we return it.
    }

     /** Approach 2: Counting Bits
     * Note: Approach 2 and 3 don't change the time complexity, but they offer a different way of
     * thinking about the problem that studying will hopefully help you expand your problem solving skills!
     * A prerequisite for these last 2 approaches is knowing how numbers are represented in binary.
     *
     * At each step, we either subtract 1 from num, or we divide num by 2. In binary, these two operations
     * do something very simple, but very interesting, to a number!
     *
     * Recall that odd numbers always have a last bit of 1. Subtracting 1, from an odd number, changes the last bit from 1 to 0.
     *
     * Showing 53 - 1 changes the last bit to zero
     *
     * Dividing by 2 removes the last bit from the number.
     *
     * Showing 52 / 2 removes the last bit
     *
     * For example, have a look at the binary representation of 210 as it's reduced to zero.
     *
     * Current
     * 1 / 12
     * The bits slid along, and each became the "last" bit. Notice how the 0s took one step to remove, and the 1s took two steps to remove.
     *
     * This means that we could simply analyze the binary representation of the starting num to determine the number of steps needed to reduce it.
     *
     * So, to get our answer, we can just add two steps for every 1, and add one step for every 0, for each bit in the binary representation.
     *
     * Showing the bits of a number map to either one or two steps
     *
     * There's one thing to be careful of, and that is not inadvertently counting the last bit as two steps. The last bit to remove will always be a 1—it was the most significant bit in the original num. The algorithm above would add 2 for removing this final 1. But actually, when we subtract 1 from it, it goes to zero. So we don't need add two steps for this bit. The simplest way of handling this case is to subtract 1 from our final steps count, as we know this "off-by-one-error" will always happen (except when the initial num is 0, we need to be careful of that edge case too!).
     *
     * Let's look at another example. The number we'll use is 78; this can be written in binary as 1001110. The binary contains four 1s and three 0s, so our total number of steps must be (4 * 2) + (3 * 1) - 1 = 10. This the correct result!
     *
     * Algorithm
     *
     * To count the bits we'll convert our number into a binary string, for each character if it's a "1" we'll add two steps, else if it's "0" we'll add one step.
     *
     * In Java, we can use Integer.toBinaryString(...) to convert an int to binary. The binary number is represented as String.
     *
     * In Python, we can use bin(...) to convert an int to binary. Like in Java, the binary number is represented as a str. However, it also contains 0b on the start—this is simply a code to say the str is a binary number. The "pythonic" thing to do is chop these two characters off with a list splice. i.e., to get the binary for num, you would do bin(num)[2:].
     *
     *
     * In Python, we can do this really elegantly using the string.count(...) and len(...) functions.
     *
     *
     * Complexity Analysis
     *
     * Let n = numn=num.
     *
     * Time Complexity : O(\log \, n)O(logn).
     *
     * Converting a number into string can be done in \log \, nlogn time.
     *
     * We then loop over each bit, doing a single operation each time. The number of bits in a number is \log_2 \, numberlog
     * 2
     * ​
     *  number, so the time complexity is O(\log \, n)O(logn).
     *
     * Space Complexity : O(\log \, n)O(logn).
     *
     * Because we convert the number into a string, we'll have \log_2 \, numberlog
     * 2
     * ​
     *  number characters in our string. This gives us a space complexity of O(\log \, n)O(logn).
     *
     */

     public int numberOfSteps(int num) {

         // Get the binary for num, as a String.
         String binaryString = Integer.toBinaryString(num);

         int steps = 0;
         // Iterate over all the bits in the binary string.
         for (char bit : binaryString.toCharArray()) {
             if (bit == '1') { // If the bit is a 1
                 steps = steps + 2; // Then it'll take 2 to remove.
             } else { // bit == '0'
                 steps = steps + 1; // Then it'll take 1 to remove.
             }
         }

         // We need to subtract 1, because the last bit was over-counted.
         return steps - 1;
     }

     /** Approach 3: Counting Bits with Bitwise Operators
     * In Approach 2, we needed to convert the number into a string representation.
     * Strings are considerably larger than the integer they represent though. Another way of inspecting bits,
     * to check if they're 1 or 0, is to use the bitwise-and (&) operator.
     *
     * The result of a & b (a bitwise-and b) looks at each bit in both a and b at the same time.
     * If both bits are 1 then bitwise-and sets the same bit of the result to 1, but if either are 0 it sets the bit to 0.
     *
     * For example, 109 and 57 can be written as 1101101 and 111001 respectively.
     * This image shows what happens when we bitwise-and them.
     *
     * Bitwise and of 109 and 57 showing result
     *
     * So, to actually inspect a specific bit,
     * we can use a number that has a 1 followed by enough 0s to put the 1 at the position we want it (we commonly call this a "bitmask").
     * With this number, we bitwise-and (&) it with the input number. If the input number has a 1 at the same position, it'll output 1 at that position,
     * and because all other numbers are 0 they will be 0 in the output as well.
     *
     * These numbers of the form 1, followed by some number of 0s,
     * are actually just the powers of two, where the power is the number of 0s after the one. As such,
     * we can check if a bit is a 1 in a number by doing num & (1 << bit) where bit is the bit we want to
     * check (0-indexed from the right).
     *
     * For example, let's check if the sixth bit of 109 is a 1.
     *
     * Bitwise and of 109 and 32 showing result of 32
     *
     * The output is 100000, which is not zero.
     * Therefore, we know that the sixth bit has to be a 1.
     *
     * Let's also check if the the fifth bit of 109 is a 1.
     *
     * Bitwise and of 109 and 16 showing result of 0
     *
     * The output is 0000000, which is zero. Therefore, we know that the fifth bit must be a 0.
     *
     * Just like Approach 2, we look at each bit, and if it's a 1 we add 2 to steps,
     * otherwise if it's a 0, we add 1 to steps.
     *
     * Algorithm
     *
     * Unlike the previous approach, this approach won't work correctly when num = 0 is the input.
     * The previous approach did an iteration for the lone 0 bit as it was in the string, but for this approach the loop won't run at all. -1 will then be returned because of the steps - 1. The solution is to check for num == 0 at the start and return 0 if it is detected.
     *
     *
     * Complexity Analysis
     *
     * Let n = numn=num.
     *
     * Time Complexity : O(\log \, n)O(logn).
     *
     * We're pulling out each of the \log \, nlogn bits from num and performing an O(1)O(1) operation on each one. Therefore, the total time complexity is, again, O(\log \, n)O(logn).
     *
     * Space Complexity : O(1)O(1).
     *
     * We only use a constant number of integer variables, and so the space complexity is O(1)O(1).
     */

     public int numberOfSteps(int num) {

         // We need to handle this as a special case, otherwise it'll return -1.
         if (num == 0) return 0;

         int steps = 0;

         for (int powerOfTwo = 1; powerOfTwo <= num; powerOfTwo = powerOfTwo * 2) {
             // Apply the bit mask to check if the bit at "powerOfTwo" is a 1.
             if ((powerOfTwo & num) != 0) {
                 steps = steps + 2;
             } else {
                 steps = steps + 1;
             }
         }

         // We need to subtract 1, because the last bit was over-counted.
         return steps - 1;
     }
}
