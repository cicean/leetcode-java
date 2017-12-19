import java.util.*;

/**
 * 287	Find the Duplicate Number	28.7%	Hard
 *
 * @author cicean
 *         Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 *         prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *         <p>
 *         Note:
 *         You must not modify the array (assume the array is read only).
 *         You must use only constant, O(1) extra space.
 *         Your runtime complexity should be less than O(n2).
 *         There is only one duplicate number in the array, but it could be repeated more than once.
 *         Credits:
 *         Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 *         <p>
 *         Hide Tags Array Two Pointers Binary Search
 *         Hide Similar Problems (H) First Missing Positive (M) Single Number (M) Linked List Cycle II (M) Missing Number
 *         <p>
 *         <p>
 *         These numbers constitute a linked list and the value of the node (a array cell) is the index of the next node, and there must be a cycle. Therefore, we use the classical "fast and slow pointers".
 *         And you can read the detail which is given at findArrayDuplicate.
 */
public class FindtheDuplicateNumber {

    /**
     * Approach #1 Sorting [Accepted]
     Intuition

     If the numbers are sorted, then any duplicate numbers will be adjacent in the sorted array.

     Algorithm

     Given the intuition, the algorithm follows fairly simply. First, we sort the array, and then we compare each
     element to the previous element. Because there is exactly one duplicated element in the array,
     we know that the array is of at least length 2, and we can return the duplicate element as soon as we find it.
     Complexity Analysis

     Time complexity : O(nlgn)O(nlgn)

     The sort invocation costs O(nlgn)O(nlgn) time in Python and Java, so it dominates the subsequent linear scan.

     Space complexity : O(1)O(1) (or O(n)O(n))

     Here, we sort nums in place, so the memory footprint is constant. If we cannot modify the input array,
     then we must allocate linear space for a copy of nums and sort that instead.
     * @param nums
     * @return
     */

    class Solution {
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i-1]) {
                    return nums[i];
                }
            }

            return -1;
        }
    }

    /**
     * Approach #2 Set [Accepted]
     Intuition

     If we store each element as we iterate over the array, we can simply check each element as we iterate over the array.

     Algorithm

     In order to achieve linear time complexity, we need to be able to insert elements into a data structure
     (and look them up) in constant time. A Set satisfies these constraints nicely, so we iterate over the array
     and insert each element into seen.
     Before inserting it, we check whether it is already there. If it is, then we found our duplicate, so we return it.
     Complexity Analysis

     Time complexity : O(n)O(n)

     Set in both Python and Java rely on underlying hash tables, so insertion and lookup have amortized constant time complexities.
     The algorithm is therefore linear, as it consists of a for loop that performs constant work nn times.

     Space complexity : O(n)O(n)

     In the worst case, the duplicate element appears twice, with one of its appearances at array index n-1n−1.
     In this case, seen will contain n-1n−1 distinct values, and will therefore occupy O(n)O(n) space.
     * @param nums
     * @return
     */

    class Solution2 {
        public int findDuplicate(int[] nums) {
            Set<Integer> seen = new HashSet<Integer>();
            for (int num : nums) {
                if (seen.contains(num)) {
                    return num;
                }
                seen.add(num);
            }

            return -1;
        }
    }


    /**
     * Approach #3 Floyd's Tortoise and Hare (Cycle Detection) [Accepted]
     Intuition

     If we interpret nums such that for each pair of index ii and value v_i, the "next" value v_jis at index v_i,
     we can reduce this problem to cycle detection. See the solution to Linked List Cycle II for more details.

     Algorithm

     First off, we can easily show that the constraints of the problem imply that a cycle must exist.
     Because each number in nums is between 11 and nn, it will necessarily point to an index that exists.
     Therefore, the list can be traversed infinitely, which implies that there is a cycle. Additionally,
     because 00 cannot appear as a value in nums, nums[0] cannot be part of the cycle. Therefore,
     traversing the array in this manner from nums[0] is equivalent to traversing a cyclic linked list.
     Given this, the problem can be solved just like Linked List Cycle II.

     To see the algorithm in action, check out the animation below:
     Complexity Analysis

     Time complexity : O(n)O(n)

     For detailed analysis, refer to Linked List Cycle II.

     Space complexity : O(1)O(1)

     For detailed analysis, refer to Linked List Cycle II.
     * @param nums
     * @return
     */

    public int findDuplicate(int[] nums) {
        if (nums.length > 1) {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] nums = {2, 5, 1, 1, 4, 3};
        FindtheDuplicateNumber slt = new FindtheDuplicateNumber();
        System.out.println(slt.findDuplicate(nums));

    }

}
