/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 

Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.

Credits:
Special thanks to @DjangoUnchained for adding this problem and creating all test cases.

 

这道题让我们求一个无序数组中是否有任意三个数字是递增关系的，我最先相处的方法是用一个dp数组，
dp[i]表示在i位置之前小于等于nums[i]的数字的个数(包括其本身)，我们初始化dp数组都为1，
然后我们开始遍历原数组，对当前数字nums[i]，
我们遍历其之前的所有数字，如果之前某个数字nums[j]小于nums[i]，那么我们更新dp[i] = max(dp[i], dp[j] + 1)，如果此时dp[i]到3了，
则返回true，若遍历完成，则返回false，参见代码如下：

但是题目中要求我们O(n)的时间复杂度和O(1)的空间复杂度，上面的那种方法一条都没满足，所以白写了。
我们下面来看满足题意的方法，这个思路是，我们遍历数组，维护一个最小值，和倒数第二小值，遍历原数组的时候，
如果当前数字小于等于最小值，更新最小值，如果小于等于倒数第二小值，更新倒数第二小值，
如果当前数字比最小值和倒数第二小值都大，说明此时有三个递增的子序列了，直接返回ture，否则遍历结束返回false，参见代码如下：

 * @author cicean
 *
 */
public class IncreasingTripletSubsequence {
	
	//use dp
	public boolean increasingTriplet(int[] nums) {
		if (nums == null || nums.length < 3) return false;
		int[] dp = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					if (dp[i] >= 3) return true;
				}
			}
		}
        return false;
    }
	
	public boolean increasingTriplet_1(int[] nums) {
		if (nums == null || nums.length < 3) return false;
		int s = Integer.MAX_VALUE, m = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > s && nums[i] > m) {
				return true;
			}
			if (s >= nums[i]) s = nums[i];
			else if (m > nums[i]) m = nums[i];			
		}
		
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			IncreasingTripletSubsequence slTripletSubsequence = new IncreasingTripletSubsequence();
			int a[] = {2,1,5,0,4,6};
			System.out.println(slTripletSubsequence.increasingTriplet(a));
	}

}
