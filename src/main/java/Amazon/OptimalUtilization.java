package Amazon;

import java.util.*;

/***
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value. Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible. Return a list of ids of selected elements. If no pair is possible, return an empty list.
 *
 * Example 1:
 *
 * Input:
 * a = [[1, 2], [2, 4], [3, 6]]
 * b = [[1, 2]]
 * target = 7
 *
 * Output: [[2, 1]]
 *
 * Explanation:
 * There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
 * Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
 * Example 2:
 *
 * Input:
 * a = [[1, 3], [2, 5], [3, 7], [4, 10]]
 * b = [[1, 2], [2, 3], [3, 4], [4, 5]]
 * target = 10
 *
 * Output: [[2, 4], [3, 2]]
 *
 * Explanation:
 * There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
 * Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
 * These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
 * Example 3:
 *
 * Input:
 * a = [[1, 8], [2, 7], [3, 14]]
 * b = [[1, 5], [2, 10], [3, 14]]
 * target = 20
 *
 * Output: [[3, 1]]
 * Example 4:
 *
 * Input:
 * a = [[1, 8], [2, 15], [3, 9]]
 * b = [[1, 8], [2, 11], [3, 12]]
 * target = 20
 *
 * Output: [[1, 3], [3, 2]]
 */

public class OptimalUtilization {

    public List<List<Integer>> aircraftUtilization(int maxTravelDist,
                                                   int[][] forwardRouteList,
                                                   int[][] returnRouteList){
        List<List<Integer>> res = new ArrayList<>();

        int len1 = forwardRouteList.length, len2 = returnRouteList.length;
        if(len1 == 0 || len2 == 0) return res;
        Arrays.sort(forwardRouteList, (int[] a, int[] b) -> (a[1] - b[1]));
        Arrays.sort(returnRouteList, (int[] a, int[] b) -> (a[1] - b[1]));
        int left = 0, right = len2 - 1, maxVal = -1;
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        while(left < len1 && right >= 0){
            int sum = forwardRouteList[left][1] + returnRouteList[right][1];
            if(sum > maxTravelDist){ --right; continue;}
            if(sum >= maxVal){
                int r = right;
                map.putIfAbsent(sum, new ArrayList<>());
                // check the duplicates
                while(r >= 0 && returnRouteList[r][1] == returnRouteList[right][1]){
                    List<Integer> list = new ArrayList<>();
                    list.add(forwardRouteList[left][0]); list.add(returnRouteList[r][0]);
                    map.get(sum).add(list); --r;
                }
                maxVal = sum;
            }
            ++left;
        }
        return map.get(maxVal);
    }

    List<List<Integer>> optimalUtilization(
            int deviceCapacity,
            List<List<Integer>> foregroundAppList,
            List<List<Integer>> backgroundAppList)
    {
        // WRITE YOUR CODE HERE
        List<List<Integer>> res = new ArrayList<>();
        int len1 = foregroundAppList.size();
        int len2 = backgroundAppList.size();

        if (len1 == 0 || len2 == 0) {
            return res;
        }

        Collections.sort(foregroundAppList, (List<Integer> a, List<Integer> b) -> (a.get(1).intValue() - b.get(1).intValue()));
        Collections.sort(backgroundAppList, (List<Integer> a, List<Integer> b) -> (a.get(1).intValue() - b.get(1).intValue()));
        int left = 0, right = len2 - 1, maxVal = -1;
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        while (left < len1 && right >= 0) {
            int sum = foregroundAppList.get(left).get(1) + backgroundAppList.get(right).get(1);
            if (sum > deviceCapacity) {
                --right;
                continue;
            }
            if (sum >= maxVal) {
                int r = right;
                map.putIfAbsent(sum, new ArrayList<>());
                while (r >= 0 && backgroundAppList.get(r).get(1) == backgroundAppList.get(right).get(1)) {
                    List<Integer> list =  new ArrayList<>();
                    list.add(foregroundAppList.get(left).get(0));
                    list.add(backgroundAppList.get(r).get(0));
                    map.get(sum).add(list);
                    --r;
                }
                maxVal = sum;
            }
            ++left;
        }

        return map.get(maxVal);
    }

    List<List<Integer>> optimalUtilization(
            int deviceCapacity,
            List<List<Integer>> foregroundAppList,
            List<List<Integer>> backgroundAppList)
    {
        // WRITE YOUR CODE HERE
        List<List<Integer>> res = new ArrayList<>();
        int len1 = foregroundAppList.size();
        int len2 = backgroundAppList.size();

        if (len1 == 0 || len2 == 0) {
            return res;
        }

        Collections.sort(foregroundAppList, (List<Integer> a, List<Integer> b) -> (a.get(1).intValue() - b.get(1).intValue()));
        Collections.sort(backgroundAppList, (List<Integer> a, List<Integer> b) -> (a.get(1).intValue() - b.get(1).intValue()));
        int max = Integer.MIN_VALUE;
        int m = foregroundAppList.size();
        int n = backgroundAppList.size();
        int i =0;
        int j =n-1;
        while(i<m && j >= 0) {
            int sum = foregroundAppList.get(i).get(1) + backgroundAppList.get(j).get(1);
            if(sum > deviceCapacity) {
                --j;
            } else {
                if(max <= sum) {
                    if(max < sum) {
                        max = sum;
                        res.clear();
                    }
                    List<Integer> tmp1 = new ArrayList<Integer>();
                    tmp1.add(foregroundAppList.get(i).get(0));
                    tmp1.add(backgroundAppList.get(j).get(0));
                    res.add(tmp1);
                    int index = j-1;
                    while(index >=0 && backgroundAppList.get(index).get(1) == backgroundAppList.get(index+1).get(1)) {
                        List<Integer> tmp2 = new ArrayList<Integer>();
                        tmp1.add(foregroundAppList.get(i).get(0));
                        tmp1.add(backgroundAppList.get(index--).get(0));
                        res.add(tmp2);
                    }
                }
                ++i;
            }
        }

        return res;
    }
}
