/**
 * Created by cicean on 8/30/2016.
 * 330. Patching Array  QuestionEditorial Solution  My Submissions
 Total Accepted: 13990 Total Submissions: 45896 Difficulty: Hard
 Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

 Example 1:
 nums = [1, 3], n = 6
 Return 1.

 Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 So we only need 1 patch.

 Example 2:
 nums = [1, 5, 10], n = 20
 Return 2.
 The two patches can be [2, 4].

 Example 3:
 nums = [1, 2, 2], n = 5
 Return 0.
 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Greedy
 ���⣺����һ������nums��һ����n����������ٵ���ʹ��[1,n]�е�ÿ������������������Ԫ�غ����
 */
public class PatchingArray {

    /**
     * һ��ʼ���˸������־�Ѿ����Ա���ϵ�����Ȼ���С���󿴣��ĸ������ܱ���ʾ�ľ���ӽ�ȥ��Ȼ����£����������ܱ���ϵ�������Ȼ��RE�ˣ����������ݣ�2147483647�� = =| ��ô������顣�������Ź֡�

     Ȼ��ο���discuss��˼·��https://leetcode.com/discuss/82822/solution-explanation

     ������known_sum��ʾ��֪��������Ϊ[1,known_sum)�����������ʾ�Ǿͼ��ˣ�

     nums[i] <= known_sum��������֪��ΧΪ��[1,known_sum + nums[i] )
     nums[i] >  known_sum,  ���known_sum��������ܴﵽ���ķ�Χ��������֪��Χ����Ϊ��[1,known_sum *2  )
     */
    public class Solution {
        public int minPatches(int[] nums, int n) {
            int cnt = 0,i=0;
            for(long known_sum = 1;known_sum <= n;){
                if(i < nums.length && known_sum >= nums[i]){
                    known_sum += nums[i++];
                }else{
                    known_sum <<= 1;
                    cnt++;
                }
            }
            return cnt;
        }
    }



}
