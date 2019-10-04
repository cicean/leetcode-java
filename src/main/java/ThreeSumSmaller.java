/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target. 

For example, given nums = [-2, 0, 1, 3], and target = 2. 

Return 2. Because there are two triplets which sums are less than 2: 

[-2, 0, 1] 
[-2, 0, 3] 
Follow up: 
Could you solve it in O(n2) runtime? 

[����] 
����3Sum & 3Sum Closest�Ļ�����·���������������飬�̶���һ��������ʣ������������Ѱ����������������������������ʹ��two pointers���ɽ���Ѱ�ҡ� 
�ؼ�����һ���ҵ� i, j ����nums[i] + nums[j] + nums[k] < target, ��Ϊ������������i, j-1, k),(i, j - 2, k)...(i, i+1, k)�������������Ľ⣬��Щ���ܹ��� j - i����������һ��һ��check���ǡ� 
������ע����ⲻ��Ҫȥ�ش���һ��ʼ�������������ȥ�ش����� 
�ܿ���˼·��leetcode���۰�����˼·һ��~ 

 * @author cicean
 *
 */
public class ThreeSumSmaller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
