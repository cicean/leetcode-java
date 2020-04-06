import java.util.*;

/**
 * Number of Burgers with No Waste of Ingredients
 * Medium
 *
 * 51
 *
 * 67
 *
 * Add to List
 *
 * Share
 * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
 *
 * Jumbo Burger: 4 tomato slices and 1 cheese slice.
 * Small Burger: 2 Tomato slices and 1 cheese slice.
 * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of remaining cheeseSlices equal to 0. If it is not possible to make the remaining tomatoSlices and cheeseSlices equal to 0 return [].
 *
 *
 *
 * Example 1:
 *
 * Input: tomatoSlices = 16, cheeseSlices = 7
 * Output: [1,6]
 * Explantion: To make one jumbo burger and 6 small burgers we need 4*1 + 2*6 = 16 tomato and 1 + 6 = 7 cheese. There will be no remaining ingredients.
 * Example 2:
 *
 * Input: tomatoSlices = 17, cheeseSlices = 4
 * Output: []
 * Explantion: There will be no way to use all ingredients to make small and jumbo burgers.
 * Example 3:
 *
 * Input: tomatoSlices = 4, cheeseSlices = 17
 * Output: []
 * Explantion: Making 1 jumbo burger there will be 16 cheese remaining and making 2 small burgers there will be 15 cheese remaining.
 * Example 4:
 *
 * Input: tomatoSlices = 0, cheeseSlices = 0
 * Output: [0,0]
 * Example 5:
 *
 * Input: tomatoSlices = 2, cheeseSlices = 1
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 0 <= tomatoSlices <= 10^7
 * 0 <= cheeseSlices <= 10^7
 * Accepted
 * 6,931
 * Submissions
 * 14,017
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * ranjeetsinha13
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * DeliveryHero
 * |
 * LeetCode
 * Can we have an answer if the number of tomatoes is odd ?
 * If we have answer will be there multiple answers or just one answer ?
 * Let us define number of jumbo burgers as X and number of small burgers as Y We have to find an x and y in this equation
 * 1. 4X + 2Y = tomato
 * 2. X + Y = cheese
 *
 */
public class NumberofBurgerswithNoWasteofIngredients {

    /**
     * Intuition
     * Classic problem, https://w.wiki/D2S
     * Sorry that I don't know the name in English.
     * Maybe Chickens and Rabbits problem
     *
     *
     * Explanation
     * tomate number t should not be odd,
     * and it should valid that c * 2 <= t && t <= c * 4.
     *
     * From
     * jumbo + small = cheese
     * jumbo * 2 + small = tomate / 2
     *
     * We can get that
     * jumb0 = tomate / 2 - cheese
     * So that
     * small = cheese * 2 - tomate / 2
     *
     *
     * Complexity
     * Time O(1)
     * Space O(1)
     * @param t
     * @param c
     * @return
     */
    public List<Integer> numOfBurgers(int t, int c) {
        return t % 2 == 0 && c * 2 <= t && t <= c * 4 ? Arrays.asList(t / 2 - c, c * 2 - t / 2) :  new ArrayList();
    }
}
