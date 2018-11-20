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
 �����������飬�����ǵĽ����������ظ���
 */
public class IntersectionofTwoArrays {
    /**
     * ���뵽����HashSet()����ʵHashMapҲ���ԣ�ֻ����Ҫ�ڱ���nums2��ʱ����ӵ�res�����е���Ҫremove������΢�鷳��һ�㡣��LC���ܵ�ʱ��HashSetҲҪ��һ�㡣
     ��һ������HashMap������BitSet()�Ϳ�Ķ��ˡ�
     O(n)
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

    //O(nlogn) ort both arrays, use two pointers

    public class Solution3 {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0;
            int j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    i++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                } else {
                    set.add(nums1[i]);
                    i++;
                    j++;
                }
            }
            int[] result = new int[set.size()];
            int k = 0;
            for (Integer num : set) {
                result[k++] = num;
            }
            return result;
        }
    }



}
