/**
 * 552. Student Attendance Record II
 * Hard
 *
 * 490
 *
 * 92
 *
 * Add to List
 *
 * Share
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.
 *
 * A student attendance record is a string that only contains the following three characters:
 *
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * Example 1:
 * Input: n = 2
 * Output: 8
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" won't be regarded as rewardable owing to more than one absent times.
 * Note: The value of n won't exceed 100,000.
 *
 * Accepted
 * 20,824
 * Submissions
 * 58,418
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * fallcreek
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 4
 * Student Attendance Record I
 */
public class StudentAttendanceRecordII {
    /**
     * Approach #1 Brute Force [Time Limit Exceeded]
     * In the brute force approach, we actually form every possible string comprising of the letters "A", "P", "L" and check if the string is rewardable by checking it against the given criterias. In order to form every possible string, we make use of a recursive gen(string, n) function. At every call of this function, we append the letters "A", "P" and "L" to the input string, reduce the required length by 1 and call the same function again for all the three newly generated strings.
     *
     * Current
     * 1 / 16
     *
     * Complexity Analysis
     *
     * Time complexity : O(3^n)O(3
     * n
     *  ). Exploring 3^n3
     * n
     *   combinations.
     * Space complexity : O(n^n)O(n
     * n
     *  ). Recursion tree can grow upto depth nn and each node contains string of length O(n)O(n).
     */
    public class Solution {
        int count,M=1000000007;
        public int checkRecord(int n) {
            count = 0;
            gen("", n);
            return count;
        }
        public void gen(String s, int n) {
            if (n == 0 && checkRecord(s))
                count=(count+1)%M;
            else if (n > 0) {
                gen(s + "A", n - 1);
                gen(s + "P", n - 1);
                gen(s + "L", n - 1);
            }
        }
        public boolean checkRecord(String s) {
            int count = 0;
            for (int i = 0; i < s.length() && count < 2; i++)
                if (s.charAt(i) == 'A')
                    count++;
            return s.length() > 0 && count < 2 && s.indexOf("LLL") < 0;
        }
    }


    /** Approach #2 Using Recursive formulae [Time Limit Exceeded]
    * Algorithm
    *
    * The given problem can be solved easily if we can develop a recurring relation for it.
    *
    * Firstly, assume the problem to be considering only the characters LL and PP in the strings. i.e. The strings can contain only LL and PP. The effect of AA will be taken into account later on.
    *
    * In order to develop the relation, let's assume that f[n]f[n] represents the number of possible rewardable strings(with LL and PP as the only characters) of length nn. Then, we can easily determine the value of f[n]f[n] if we know the values of the counts for smaller values of nn. To see how it works, let's examine the figure below:
    *
    * Recurrence
    *
    * The above figure depicts the division of the rewardable string of length nn into two strings of length n-1n−1 and ending with LL or PP. The string ending with PP of length nn is always rewardable provided the string of length n-1n−1 is rewardable. Thus, this string accounts for a factor of f[n-1]f[n−1] to f[n]f[n].
    *
    * For the first string ending with LL, the rewardability is dependent on the further strings of length n-3n−3. Thus, we consider all the rewardable strings of length n-3n−3 now. Out of the four combinations possible at the end, the fourth combination, ending with a LLLL at the end leads to an unawardable string. But, since we've considered only rewardable strings of length n-3n−3, for the last string to be rewardable at length n-3n−3 and unawardable at length n-1n−1, it must be preceded by a PP before the LLLL.
    *
    * Thus, accounting for the first string again, all the rewardable strings of length n-1n−1, except the strings of length n-4n−4 followed by PLLPLL, can contribute to a rewardable string of length nn. Thus, this string accounts for a factor of f[n-1] - f[n-4]f[n−1]−f[n−4] to f[n]f[n].
    *
    * Thus, the recurring relation becomes:
    *
    * f[n] = 2f[n-1] - f[n-4]f[n]=2f[n−1]−f[n−4]
    *
    * We store all the f[i]f[i] values in an array. In order to compute f[i]f[i], we make use of a recursive function func(n) which makes use of the above recurrence relation.
    *
    * Now, we need to put the factor of character AA being present in the given string. We know, atmost one AA is allowed to be presnet in a rewardable string. Now, consider the two cases.
    *
    * No AA is present: In this case, the number of rewardable strings is the same as f[n]f[n].
    *
    * A single AA is present: Now, the single AA can be present at any of the nn positions. If the AA is present at the i^{th}i
    * th
    *   position in the given string, in the form: "<(i-1) characters>, A, <(n-i) characters>", the total number of rewardable strings is given by: f[i-1] * f[n-i]f[i−1]∗f[n−i]. Thus, the total number of such substrings is given by: \sum_{i=1}^{n} (f[i-1] * f[n-i])∑
    * i=1
    * n
    * ​
    *  (f[i−1]∗f[n−i]).
    *
    *
    * Complexity Analysis
    *
    * Time complexity : O(2^n)O(2
    * n
    *  ). method funcfunc will take 2^n2
    * n
    *   time.
    *
    * Space complexity : O(n)O(n). ff array is used of size nn.
    */

    public class Solution {
        int M=1000000007;
        public int checkRecord(int n) {
            int[] f =new int[n+1];
            f[0]=1;
            for(int i=1;i<=n;i++)
                f[i]=func(i);
            int sum=func(n);
            for(int i=1;i<=n;i++){
                sum+=(f[i-1]*f[n-i])%M;
            }
            return sum%M;
        }
        public int func(int n)
        {
            if(n==0)
                return 1;
            if(n==1)
                return 2;
            if(n==2)
                return 4;
            if(n==3)
                return 7;
            return (2*func(n-1) - func(n-4))%M;
        }
    }

     /** Approach #3 Using Dynamic Programming [Accepted]
     * Algorithm
     *
     * In the last approach, we calculated the values of f[i]f[i] everytime using the recursive function, which goes till its root depth everytime. But, we can reduce a large number of redundant calculations, if we use the results obtained for previous f[j]f[j] values directly to obtain f[i]f[i] as f[i] = 2f[i-1] + f[i-4]f[i]=2f[i−1]+f[i−4].
     *
     *  **Complexity Analysis**
     * Time complexity : O(n)O(n). One loop to fill ff array and one to calculate sumsum
     *
     * Space complexity : O(n)O(n). ff array of size nn is used.
     */

     public class Solution {
         long M = 1000000007;
         public int checkRecord(int n) {
             long[] f = new long[n <= 5 ? 6 : n + 1];
             f[0] = 1;
             f[1] = 2;
             f[2] = 4;
             f[3] = 7;
             for (int i = 4; i <= n; i++)
                 f[i] = ((2 * f[i - 1]) % M + (M - f[i - 4])) % M;
             long sum = f[n];
             for (int i = 1; i <= n; i++) {
                 sum += (f[i - 1] * f[n - i]) % M;
             }
             return (int)(sum % M);
         }
     }

     /** Approach #4 Dynamic Programming with Constant Space [Accepted]
     * Algorithm
     *
     * We can observe that the number and position of PP's in the given string is irrelevant. Keeping into account this fact, we can obtain a state diagram that represents the transitions between the possible states as shown in the figure below:
     *
     * State_Diagram
     *
     * This state diagram contains the states based only upon whether an AA is present in the string or not, and on the number of LL's that occur at the trailing edge of the string formed till now. The state transition occurs whenver we try to append a new character to the end of the current string.
     *
     * Based on the above state diagram, we keep a track of the number of unique transitions from which a rewardable state can be achieved. We start off with a string of length 0 and keep on adding a new character to the end of the string till we achieve a length of nn. At the end, we sum up the number of transitions possible to reach each rewardable state to obtain the required result.
     *
     * We can use variables corresponding to the states. axlyaxly represents the number of strings of length ii containing xx a'sa
     * ′
     *  s and ending with yy l'sl
     * ′
     *  s.
     *
     * Below code is inspired by @stefanpochmann.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(n)O(n). Single loop to update the states.
     *
     * Space complexity : O(1)O(1). Constant Extra Space is used.
     *
     * Approach #5 Using less variables [Accepted]
     * Algorithm
     *
     * In the last approach discussed, we've made use of six extra temporary variables just to keep a track of the change in the current state. The same result can be obtained by using a lesser number of temporary variables too.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(n)O(n). Single loop to update the states.
     *
     * Space complexity : O(1)O(1). Constant Extra Space is used.
     */

     public class Solution {
         long M = 1000000007;
         public int checkRecord(int n) {
             long a0l0 = 1;
             long a0l1 = 0, a0l2 = 0, a1l0 = 0, a1l1 = 0, a1l2 = 0;
             for (int i = 0; i < n; i++) {
                 long new_a0l0 = (a0l0 + a0l1 + a0l2) % M;
                 long new_a0l1 = a0l0;
                 long new_a0l2 = a0l1;
                 long new_a1l0 = (a0l0 + a0l1 + a0l2 + a1l0 + a1l1 + a1l2) % M;
                 long new_a1l1 = a1l0;
                 long new_a1l2 = a1l1;
                 a0l0 = new_a0l0;
                 a0l1 = new_a0l1;
                 a0l2 = new_a0l2;
                 a1l0 = new_a1l0;
                 a1l1 = new_a1l1;
                 a1l2 = new_a1l2;
             }
             return (int)((a0l0 + a0l1 + a0l2 + a1l0 + a1l1 + a1l2) % M);
         }
     }
}
