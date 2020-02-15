/**
 * 1094. Car Pooling
 * Medium
 *
 * 336
 *
 * 15
 *
 * Add to List
 *
 * Share
 * You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)
 *
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.
 *
 * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
 *
 *
 *
 * Example 1:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 *
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 * Example 3:
 *
 * Input: trips = [[2,1,5],[3,5,7]], capacity = 3
 * Output: true
 * Example 4:
 *
 * Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
 * Output: true
 *
 *
 *
 * Constraints:
 *
 * trips.length <= 1000
 * trips[i].length == 3
 * 1 <= trips[i][0] <= 100
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * 1 <= capacity <= 100000
 * Accepted
 * 19,816
 * Submissions
 * 35,003
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 4
 *
 * Amazon
 * |
 * 3
 * Meeting Rooms II
 * Medium
 * Sort the pickup and dropoff events by location, then process them in order.
 */
public class CarPooling {
    /**
     * Complexity Analysis
     * Runtime: O(n), where n is the number of trips.
     * Memory: O(m), where m is the number of stops.
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int stops[] = new int[1001];
        for (int t[] : trips) {
            stops[t[1]] += t[0];
            stops[t[2]] -= t[0];
        }
        for (int i = 0; capacity >= 0 && i < 1001; ++i) capacity -= stops[i];
        return capacity >= 0;
    }
}
