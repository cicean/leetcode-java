/**
 * 904. Fruit Into Baskets
 * Medium
 *
 * 713
 *
 * 1080
 *
 * Add to List
 *
 * Share
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 *
 * Note:
 *
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 * Accepted
 * 85,809
 * Submissions
 * 203,514
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ruanjingru
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 5
 */
public class FruitIntoBaskets {

    class Solution {
        public int totalFruit(int[] tree) {
            int last_fruit = -1;
            int scecond_last_fruit = -1;
            int last_fruit_count = 0;
            int current_max = 0;
            int max = 0;

            for (int fruit : tree) {
                if (fruit == last_fruit || fruit == scecond_last_fruit) {
                    current_max++;
                } else {
                    current_max = last_fruit_count + 1;
                }

                if (fruit == last_fruit) {
                    last_fruit_count++;
                } else {
                    scecond_last_fruit = last_fruit;
                    last_fruit = fruit;
                    last_fruit_count = 1;
                }

                max = Math.max(current_max, max);
            }

            return max;
        }
    }
}
