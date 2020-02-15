/**
 * 825. Friends Of Appropriate Ages
 * Medium
 *
 * 250
 *
 * 524
 *
 * Add to List
 *
 * Share
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 *
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 *
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 *
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 *
 * How many total friend requests are made?
 *
 * Example 1:
 *
 * Input: [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * Example 2:
 *
 * Input: [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * Example 3:
 *
 * Input: [20,30,100,110,120]
 * Output:
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 *
 *
 * Notes:
 *
 * 1 <= ages.length <= 20000.
 * 1 <= ages[i] <= 120.
 * Accepted
 * 27,729
 * Submissions
 * 67,660
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 5
 * Complexity Analysis
 *
 * Time Complexity: O(\mathcal{A}^2 + N)O(A
 * 2
 *  +N), where NN is the number of people, and \mathcal{A}A is the number of ages.
 *
 * Space Complexity: O(\mathcal{A})O(A), the space used to store count.
 */
public class FriendsOfAppropriateAges {
    /**
     * Approach #1: Counting [Accepted]
     * Intuition
     *
     * Instead of processing all 20000 people, we can process pairs of (age, count) representing how many people are that age. Since there are only 120 possible ages, this is a much faster loop.
     *
     * Algorithm
     *
     * For each pair (ageA, countA), (ageB, countB), if the conditions are satisfied with respect to age, then countA * countB pairs of people made friend requests.
     *
     * If ageA == ageB, then we overcounted: we should have countA * (countA - 1) pairs of people making friend requests instead, as you cannot friend request yourself.
     *
     */

    class Solution {
        public int numFriendRequests(int[] ages) {
            int[] count = new int[121];
            for (int age: ages) count[age]++;

            int ans = 0;
            for (int ageA = 0; ageA <= 120; ageA++) {
                int countA = count[ageA];
                for (int ageB = 0; ageB <= 120; ageB++) {
                    int countB = count[ageB];
                    if (ageA * 0.5 + 7 >= ageB) continue;
                    if (ageA < ageB) continue;
                    if (ageA < 100 && 100 < ageB) continue;
                    ans += countA * countB;
                    if (ageA == ageB) ans -= countA;
                }
            }

            return ans;
        }
    }

    class Solution_2 {
        public int numFriendRequests(int[] ages) {
            int res = 0;
            int[] numInAge = new int[121], sumInAge = new int[121];

            for(int i : ages)
                numInAge[i] ++;

            for(int i = 1; i <= 120; ++i)
                sumInAge[i] = numInAge[i] + sumInAge[i - 1];

            for(int i = 15; i <= 120; ++i) {
                if(numInAge[i] == 0) continue;
                int count = sumInAge[i] - sumInAge[i / 2 + 7];
                res += count * numInAge[i] - numInAge[i]; //people will not friend request themselves, so  - numInAge[i]
            }
            return res;
        }
    }
}
