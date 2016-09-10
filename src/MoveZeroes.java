/**
 * 283 Move Zeroes 42.8% Easy Given an array nums, write a function to move all
 * 0's to the end of it while maintaining the relative order of the non-zero
 * elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
 * should be [1, 3, 12, 0, 0].
 * 
 * Note: You must do this in-place without making a copy of the array. Minimize
 * the total number of operations. Credits: Special thanks to
 * 
 * @jianchao.li.fighter for adding this problem and creating all test cases.
 * 
 *                      Hide Tags Array Two Pointers Hide Similar Problems (E)
 *                      Remove Element 解题思路： 题目可以在O(n)时间复杂度内求解
 * 
 *                      算法步骤：
 * 
 *                      使用两个"指针"x和y，初始令y = 0
 * 
 *                      利用x遍历数组nums：
 * 
 *                      若nums[x]非0，则交换nums[x]与nums[y]，并令y+1
 * 
 *                      算法简析：
 * 
 *                      y指针指向首个0元素可能存在的位置
 * 
 *                      遍历过程中，算法确保[y, x)范围内的元素均为0
 * 
 * @author cicean
 *
 */
public class MoveZeroes {

	// method2: find non-zero put in new order, then all zero filled the array
	public void moveZeroes(int[] nums) {
		int i = 0;
		for (int n : nums) {
			if (n != 0)
				nums[i++] = n;
		}
		while (i < nums.length)
			nums[i++] = 0;
	}

	// java solution with 2 pointers
	public void moveZeroes_1(int[] nums) {
		int length = nums.length;
		int j = 0;
		for (int i = 0; i < length; i++) {
			if (nums[i] != 0) {
				nums[j] = nums[i];
				j += 1;
			}
		}
		for (int k = j; k < length; k++) {
			nums[k] = 0;
		}
	}

	// simple in-place java solution, O(n) time complexity
	public void moveZeroes_2(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				count++;
			} else {
				nums[i - count] = nums[i];
				if (count != 0) {
					nums[i] = 0;
				}
			}
		}
		return;
	}

	// facebook
	// 有一个数组，里面有0和非0元素，要求把所有非0元素移动到数组的前端，并返回非0元素的个数，非零元素顺序随意。
	// 非零元素之后数组有什么我们不关心。要求最小化写入次数。
	public void moveZeroes_fb(int[] nums) {
		int count = 0;
		int len = nums.length - 1;
		for (int i = len; i >= 0; i--) {
			if (nums[i] == 0) {
				count++;
			} else {
				nums[count + i] = nums[i];
				if (count != 0) {
					nums[i] = 0;
				}
			}

		}
		return;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoveZeroes slt = new MoveZeroes();
		int[] nums = new int[] { 1, 7, 0 };
		slt.moveZeroes_fb(nums);
		for (int i : nums) {
			System.out.print(i + ", ");
		}

	}

}
