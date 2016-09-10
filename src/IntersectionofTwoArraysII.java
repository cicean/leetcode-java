import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 350. Intersection of Two Arrays II  QuestionEditorial Solution  My Submissions
 Total Accepted: 29641
 Total Submissions: 70275
 Difficulty: Easy
 Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:
 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 Hide Tags Binary Search Hash Table Two Pointers Sort
 Hide Similar Problems (E) Intersection of Two Arrays
 题意：给定两个数组，求他们的交集（可以重复）
 */
public class IntersectionofTwoArraysII {

    /**
     * 复杂度
     O(Min(N,M)) 时间 O(Min(N,M)) 空间

     思路
     先排序，用两个指针从头扫：
     小的那个肯定不行，指针往后
     相等的全都放到result里

     注意
     */
    public class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            int k = 0, l1 = nums1.length, l2 = nums2.length;
            int[] result = new int[l1];
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0, j = 0;
            while (i < l1 && j < l2)
                bif (nums1[i] < nums2[j]) i++;
                else if (nums1[i] == nums2[j++]) result[k++] = nums1[i++];
            return Arrays.copyOf(result, k);
        }
    }

    /**
     * Follow up
     What if the given array is already sorted? How would you optimize your algorithm?

     What if nums1's size is small compared to num2's size? Which algorithm is better?

     What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

     If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.

     If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.

     */

    //hashmap
    public class Solution2 {
        public int[] intersect(int[] nums1, int[] nums2) {
            List<Integer> res = new ArrayList();
            Map<Integer, Integer> map = new HashMap();
            //Using HashMap to store values in nums1[]
            for (int i = 0; i < nums1.length; i++) {
                if (!map.containsKey(nums1[i])) map.put(nums1[i], 1);
                else map.put(nums1[i], map.get(nums1[i])+1);
            }
            //Modify the map with the amount of equal keys in nums2
            for (int i = 0; i < nums2.length; i++) {
                //Make sure the value of nums2[i] in map is larger than 0
                if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                    res.add(nums2[i]);
                    map.put(nums2[i], map.get(nums2[i])-1);
                }
            }
            //Transform ArrayList() to int[]
            int[] ans = new int[res.size()];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = res.get(i);
            }
            return ans;
        }
    }

}
