import java.util.*;
import java.util.stream.Collectors;

/**
 * 1333. Filter Restaurants by Vegan-Friendly, Price and Distance
 * Medium
 *
 * 47
 *
 * 45
 *
 * Add to List
 *
 * Share
 * Given the array restaurants where  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]. You have to filter the restaurants using three filters.
 *
 * The veganFriendly filter will be either true (meaning you should only include restaurants with veganFriendlyi set to true) or false (meaning you can include any restaurant). In addition, you have the filters maxPrice and maxDistance which are the maximum value for price and distance of restaurants you should consider respectively.
 *
 * Return the array of restaurant IDs after filtering, ordered by rating from highest to lowest. For restaurants with the same rating, order them by id from highest to lowest. For simplicity veganFriendlyi and veganFriendly take value 1 when it is true, and 0 when it is false.
 *
 *
 *
 * Example 1:
 *
 * Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 1, maxPrice = 50, maxDistance = 10
 * Output: [3,1,5]
 * Explanation:
 * The restaurants are:
 * Restaurant 1 [id=1, rating=4, veganFriendly=1, price=40, distance=10]
 * Restaurant 2 [id=2, rating=8, veganFriendly=0, price=50, distance=5]
 * Restaurant 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4]
 * Restaurant 4 [id=4, rating=10, veganFriendly=0, price=10, distance=3]
 * Restaurant 5 [id=5, rating=1, veganFriendly=1, price=15, distance=1]
 * After filter restaurants with veganFriendly = 1, maxPrice = 50 and maxDistance = 10 we have restaurant 3, restaurant 1 and restaurant 5 (ordered by rating from highest to lowest).
 * Example 2:
 *
 * Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 50, maxDistance = 10
 * Output: [4,3,2,1,5]
 * Explanation: The restaurants are the same as in example 1, but in this case the filter veganFriendly = 0, therefore all restaurants are considered.
 * Example 3:
 *
 * Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 30, maxDistance = 3
 * Output: [4,5]
 *
 *
 * Constraints:
 *
 * 1 <= restaurants.length <= 10^4
 * restaurants[i].length == 5
 * 1 <= idi, ratingi, pricei, distancei <= 10^5
 * 1 <= maxPrice, maxDistance <= 10^5
 * veganFriendlyi and veganFriendly are 0 or 1.
 * All idi are distinct.
 * Accepted
 * 6,793
 * Submissions
 * 12,858
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Yelp
 * |
 * LeetCode
 * Do the filtering and sort as said. Note that the id may not be the index in the array.
 */
public class FilterRestaurantsbyVeganFriendlyPriceandDistance {
    class Solution {
        public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
            return Arrays.stream(restaurants)
                    .filter(s -> s[2] >= veganFriendly && s[3] <= maxPrice&& s[4] <= maxDistance)
                    .sorted((a,b) -> {
                        if(a[1] == b[1])
                            return b[0] - a[0];
                        else
                            return b[1] - a[1];
                    })
                    .map(i -> i[0])
                    .collect(Collectors.toList());
        }
    }

    class Solution_PQ {
        public List<Integer> filterRestaurants(int[][] rest, int veganFriendly, int maxPrice, int maxDistance) {
            List<Integer> res = new ArrayList<Integer>();
            int n=rest.length;
            PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> a[1]==b[1]? b[0]-a[0]: b[1]-a[1]);

            for (int i=0;i<n;i++) {
                if (rest[i][3]<=maxPrice && rest[i][4]<=maxDistance) {
                    if (veganFriendly==1 && rest[i][2]==1 || veganFriendly==0 )
                        q.add(rest[i]);
                }
            }

            while (q.size()>0) {
                int[] x = q.poll();
                res.add(x[0]);
            }
            return res;
        }
    }
}
