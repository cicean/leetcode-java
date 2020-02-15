/**
 * 875. Koko Eating Bananas
 * Medium
 *
 * 495
 *
 * 55
 *
 * Add to List
 *
 * Share
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
 *
 * Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
 *
 * Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
 *
 * Return the minimum integer K such that she can eat all the bananas within H hours.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], H = 8
 * Output: 4
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], H = 5
 * Output: 30
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], H = 6
 * Output: 23
 *
 *
 * Note:
 *
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 * Accepted
 * 26,697
 * Submissions
 * 54,095
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ankush23
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Airbnb
 * |
 * 12
 *
 * Facebook
 * |
 * 3
 *
 * Google
 * |
 * 2
 * Minimize Max Distance to Gas Station
 * Complexity Analysis
 *
 * Time Complexity: O(N \log W)O(NlogW), where NN is the number of piles, and WW is the maximum size of a pile.
 *
 * Space Complexity: O(1)O(1).
 */

public class KokoEatingBananas {

    /**
     * Approach 1: Binary Search
     * Intuition
     *
     * If Koko can finish eating all the bananas (within H hours) with an eating speed of K, she can finish with a larger speed too.
     *
     * If we let possible(K) be true if and only if Koko can finish with an eating speed of K, then there is some X such that possible(K) = True if and only if K >= X.
     *
     * For example, with piles = [3, 6, 7, 11] and H = 8, there is some X = 4 so that possible(1) = possible(2) = possible(3) = False, and possible(4) = possible(5) = ... = True.
     *
     * Algorithm
     *
     * We can binary search on the values of possible(K) to find the first X such that possible(X) is True: that will be our answer. Our loop invariant will be that possible(hi) is always True, and lo is always less than or equal to the answer. For more information on binary search, please visit [LeetCode Explore - Binary Search].
     *
     * To find the value of possible(K), (ie. whether Koko with an eating speed of K can eat all bananas in H hours), we simulate it. For each pile of size p > 0, we can deduce that Koko finishes it in Math.ceil(p / K) = ((p-1) // K) + 1 hours, and we add these times across all piles and compare it to H.
     *
     *
     */

    class Solution {
        public int minEatingSpeed(int[] piles, int H) {
            int lo = 1;
            int hi = 1_000_000_000;
            while (lo < hi) {
                int mi = (lo + hi) / 2;
                if (!possible(piles, H, mi))
                    lo = mi + 1;
                else
                    hi = mi;
            }

            return lo;
        }

        // Can Koko eat all bananas in H hours with eating speed K?
        public boolean possible(int[] piles, int H, int K) {
            int time = 0;
            for (int p: piles)
                time += (p-1) / K + 1;
            return time <= H;
        }
    }

    class Solution_2 {
        public int minEatingSpeed(int[] piles, int H) {
            // 计算出最初平均速度
            long bananaCount = 0;
            for (int bananaPile : piles) {
                bananaCount += bananaPile;
            }
            int initialK = (int)(bananaCount % H > 0 ?  (bananaCount / H) + 1 : (bananaCount / H));

            int allHours = Integer.MAX_VALUE;
            int speed = initialK - 1;

            while(allHours > H){
                speed ++;
                allHours = getHours(piles, speed);
            }
            return speed;
        }

        private int getHours(int[] piles, int speed){
            int cnt = 0;
            for (int pile : piles) {
                if (pile % speed == 0) {
                    cnt += 1;
                } else {
                    cnt += (pile / speed) + 1;
                }
            }
            return cnt;
        }
    }
}
