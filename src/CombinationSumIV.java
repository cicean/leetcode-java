/**
 * Created by cicean on 8/29/2016.
 * 377. Combination Sum IV  QuestionEditorial Solution  My Submissions
 Total Accepted: 8477
 Total Submissions: 21281
 Difficulty: Medium
 Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

 Example:

 nums = [1, 2, 3]
 target = 4

 The possible combination ways are:
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 Note that different sequences are counted as different combinations.

 Therefore the output is 7.
 Follow up:
 What if negative numbers are allowed in the given array?
 How does it change the problem?
 What limitation we need to add to the question to allow negative numbers?

 Credits:
 Special thanks to @pbrother for adding this problem and creating all test cases.

 Hide Company Tags Google Snapchat Facebook
 Hide Tags Dynamic Programming
 Hide Similar Problems (M) Combination Sum
 Follow up:
 What if negative numbers are allowed in the given array?
 How does it change the problem?
 What limitation we need to add to the question to allow negative numbers?

 题意：给定一个元素互不相同且均为正数的数组，让你用该数组中的数组合成target（可以重复使用），问有多少种。

 */
public class CombinationSumIV {

    /**
     * dp[i] += dp[i-num]
     */
    public class Solution {
        public int combinationSum4(int[] nums, int target) {
            int[] dp= new int[target+1];
            dp[0] = 1;
            for(int i = 1; i <= target;i++){
                for(int num:nums){
                    if(i >= num) dp[i] += dp[i - num];
                }
            }
            return dp[target];
        }
    }

    /**
     * dp[i+num] += dp[i]
     */
    public class Solution2 {
        public int combinationSum4(int[] nums, int target) {
            int[] dp= new int[target+1];
            dp[0] = 1;
            for(int i = 0; i < target;i++){
                for(int num:nums){
                    if(i + num <= target) dp[i + num] += dp[i];
                }
            }
            return dp[target];
        }
    }


}
