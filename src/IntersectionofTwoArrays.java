import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 349. Intersection of Two Arrays  QuestionEditorial Solution  My Submissions
 Total Accepted: 42176
 Total Submissions: 94568
 Difficulty: Easy
 Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

 Note:
 Each element in the result must be unique.
 The result can be in any order.
 Hide Tags Binary Search Hash Table Two Pointers Sort
 Hide Similar Problems (E) Intersection of Two Arrays II
 给定两个数组，求他们的交集（不算重复）
 */
public class IntersectionofTwoArrays {
    /**
     * 先想到的是HashSet()，其实HashMap也可以，只是需要在遍历nums2的时候，添加到res数组中的数要remove掉，略微麻烦了一点。在LC里跑的时候，HashSet也要快一点。
     另一种类似HashMap做法的BitSet()就快的多了。
     */

    //HashSet
    public class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1.length == 0 || nums2.length == 0) return new int[0];
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for (int i = 0; i < nums1.length; i++) {
                set1.add(nums1[i]);
            }

            for (int i = 0; i < nums2.length; i++) {
                if (set1.contains(nums2[i])) {
                    set2.add(nums2[i]);
                }
            }

            int[] res = set2.stream().mapToInt(Number::intValue).toArray();
            return res;
        }
    }



    //BitSet
    public class Solution2 {
        public int[] intersection(int[] nums1, int[] nums2) {
            int[] res = new int[nums1.length];
            if (nums1.length == 0 || nums2.length == 0) return new int[0];
            int index = 0;
            BitSet set = new BitSet();
            for (int i = 0; i < nums1.length; i++) {
                set.set(nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (set.get(nums2[i]) == true) {
                    res[index++] = nums2[i];
                    set.set(nums2[i], false);
                }
            }
            return Arrays.copyOfRange(res, 0, index);
        }
    }
}
