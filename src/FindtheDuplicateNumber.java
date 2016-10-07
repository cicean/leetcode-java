/**
 * 287	Find the Duplicate Number	28.7%	Hard
 *
 * @author cicean
 *         Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
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
