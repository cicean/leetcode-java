/**
 * Description
 * Solution
 * Submissions
 * Discuss (184)
 * 1185. Day of the Week
 * Easy
 *
 * 64
 *
 * 748
 *
 * Add to List
 *
 * Share
 * Given a date, return the corresponding day of the week for that date.
 *
 * The input is given as three integers representing the day, month and year respectively.
 *
 * Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 *
 *
 *
 * Example 1:
 *
 * Input: day = 31, month = 8, year = 2019
 * Output: "Saturday"
 * Example 2:
 *
 * Input: day = 18, month = 7, year = 1999
 * Output: "Sunday"
 * Example 3:
 *
 * Input: day = 15, month = 8, year = 1993
 * Output: "Sunday"
 *
 *
 * Constraints:
 *
 * The given dates are valid dates between the years 1971 and 2100.
 * Accepted
 * 15,690
 * Submissions
 * 24,390
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * devendrakota21
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Microsoft
 * |
 * 5
 * Sum up the number of days for the years before the given year.
 * Handle the case of a leap year.
 * Find the number of days for each month of the given year.
 */
public class DayoftheWeek {

    /**
     * Is this a real interview problem?
     * The formula for this problem is Zelle formula
     * Another name: Zeller's congruence or Kim larsen calculation formula.
     */

    class Solution {
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        public String dayOfTheWeek(int d, int m, int y) {
            if (m < 3) {
                m += 12;
                y -= 1;
            }
            int c = y / 100;
            y = y % 100;
            int w = (c / 4 - 2 * c + y + y / 4 + 13 * (m + 1) / 5 + d - 1) % 7;
            return days[(w + 7) % 7];
        }
    }
}
