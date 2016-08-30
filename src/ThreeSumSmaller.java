/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target. 

For example, given nums = [-2, 0, 1, 3], and target = 2. 

Return 2. Because there are two triplets which sums are less than 2: 

[-2, 0, 1] 
[-2, 0, 3] 
Follow up: 
Could you solve it in O(n2) runtime? 

[分析] 
延续3Sum & 3Sum Closest的基本套路，先排序输入数组，固定第一个数后在剩余数组区间中寻找所有满足条件的另外两个数，使用two pointers技巧进行寻找。 
关键点是一旦找到 i, j 满足nums[i] + nums[j] + nums[k] < target, 因为数组已排序，则（i, j-1, k),(i, j - 2, k)...(i, i+1, k)均是满足条件的解，这些解总共是 j - i个，而无需一个一个check它们。 
此外需注意此题不需要去重处理，一开始画蛇添足进行了去重处理…… 
很开心思路和leetcode讨论版的最佳思路一致~ 

 * @author cicean
 *
 */
public class ThreeSumSmaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
