import java.util.HashMap;
import java.util.TreeMap;

/**
 * 846. Hand of Straights
 * Medium
 *
 * 435
 *
 * 59
 *
 * Add to List
 *
 * Share
 * Alice has a hand of cards, given as an array of integers.
 *
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 *
 * Return true if and only if she can.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 *
 *
 * Note:
 *
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 * Accepted
 * 30,322
 * Submissions
 * 58,731
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * awice
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 8
 */
public class HandofStraights {

    /**
     * Approach #1: Brute Force [Accepted]
     * Intuition
     *
     * We will repeatedly try to form a group (of size W) starting with the lowest card. This works because the lowest card still in our hand must be the bottom end of a size W straight.
     *
     * Algorithm
     *
     * Let's keep a count {card: number of copies of card} as a TreeMap (or dict).
     *
     * Then, repeatedly we will do the following steps: find the lowest value card that has 1 or more copies (say with value x), and try to remove x, x+1, x+2, ..., x+W-1 from our count. If we can't, then the task is impossible.
     * Complexity Analysis
     *
     * Time Complexity: O(N * (N/W))O(N∗(N/W)), where NN is the length of hand. The (N / W)(N/W) factor comes from min(count). In Java, the (N / W)(N/W) factor becomes \log NlogN due to the complexity of TreeMap.
     *
     * Space Complexity: O(N)O(N).
     */

    class Solution {
        public boolean isNStraightHand(int[] hand, int W) {
            TreeMap<Integer, Integer> count = new TreeMap();
            for (int card: hand) {
                if (!count.containsKey(card))
                    count.put(card, 1);
                else
                    count.replace(card, count.get(card) + 1);
            }

            while (count.size() > 0) {
                int first = count.firstKey();
                for (int card = first; card < first + W; ++card) {
                    if (!count.containsKey(card)) return false;
                    int c = count.get(card);
                    if (c == 1) count.remove(card);
                    else count.replace(card, c - 1);
                }
            }

            return true;
        }
    }

    class Solution_1 {
        public boolean isNStraightHand(int[] hand, int W) {
            // If there are not a multiple of W cards, it cannot form straights all of W.
            if (hand.length % W != 0) {
                return false;
            }

            int[] counts = new int[W];
            // Create counts just of size W. All cards go into those decks.
            for (int i = 0; i < hand.length; ++i) {
                ++counts[hand[i] % W];
            }

            for (int i = 0; i < counts.length; ++i) {
                if (counts[i] != hand.length / W) {
                    return false;
                }
            }
            return true;
        }
    }

    //follow up

    /**
     * Define “X-Straight” as X cards with consecutive numbers (X >= 3). Determine if the deck can be fully divided into sets of “X-Straight”.
     * Example: 1, 2, 3, 4, 4, 5, 6 -> True
     */

    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public boolean determine(int[] arr) {
        for (int elem : arr) {
            map.put(elem, map.getOrDefault(elem, 0) + 1);
        }
        return helper(arr, 0);
    }

    public boolean helper(int[] arr, int pos) {
        if (pos == arr.length) return true;
        int cur = arr[pos];
        for (int k = 3; k < map.keySet().size(); k++) {
            HashMap<Integer, Integer> copy = new HashMap<Integer, Integer>(map);
            for (int i = cur; i < cur + k; i++) {
                if (!map.containsKey(i)) return false;
                if (map.get(i) == 0) return false;
                map.put(i, map.get(i)-1);
            }
            while (pos < arr.length && map.get(arr[pos]) == 0) pos++;
            if (helper(arr, pos))
                return true;
            map = copy;
        }
        return false;

    }

    class Solution_test {
        public boolean determine(int[] arr) {
            for (int elem : arr) {
                map.put(elem, map.getOrDefault(elem, 0) + 1);
            }
            return helper(arr, 0);
        }

    }


}
