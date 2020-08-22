/**
 * 1122. Relative Sort Array
 * Easy
 *
 * 509
 *
 * 37
 *
 * Add to List
 *
 * Share
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 *
 * Constraints:
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 * Accepted
 * 46,822
 * Submissions
 * 69,406
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * Lovedeep
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 2
 *
 * Google
 * |
 * 2
 * Using a hashmap, we can map the values of arr2 to their position in arr2.
 * After, we can use a custom sorting function.
 */
public class RelativeSortArray {
//
//    Because 0 <= arr1[i], arr2[i] <= 1000, we use an array to count every element.
//    Go through every element in the second array, and update values of the first array based on the order in the second array.
//    Time: O(1)
//    Space: O(1)
    class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            int[] cnt = new int[1001];
            for(int n : arr1) cnt[n]++;
            int i = 0;
            for(int n : arr2) {
                while(cnt[n]-- > 0) {
                    arr1[i++] = n;
                }
            }
            for(int n = 0; n < cnt.length; n++) {
                while(cnt[n]-- > 0) {
                    arr1[i++] = n;
                }
            }
            return arr1;
        }
    }

//
//--Follow-up: What if this constraint 0 <= arr1[i], arr2[i] <= 1000 doesn't exist?
//    Use TreeMap.

    class Solution2 {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int n : arr1) map.put(n, map.getOrDefault(n, 0) + 1);
            int i = 0;
            for(int n : arr2) {
                while(map.get(n) > 0) {
                    arr1[i++] = n;
                    map.put(n, map.get(n)-1);
                }
            }
            for(int n : map.keySet()){
                while(map.get(n) > 0) {
                    arr1[i++] = n;
                    map.put(n, map.get(n)-1);
                }
            }
            return arr1;
        }
    }
//    Time: O(NlogN)
//    Space: O(N)
}
