import java.util.*;

/**
 * Created by cicean on 9/1/2016.
 * 368. Largest Divisible Subset  QuestionEditorial Solution  My Submissions
 Total Accepted: 9061 Total Submissions: 28751 Difficulty: Medium
 Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

 If there are multiple solutions, return any subset is fine.

 Example 1:

 nums: [1,2,3]

 Result: [1,2] (of course, [1,3] will also be ok)
 Example 2:

 nums: [1,2,4,8]

 Result: [1,2,4,8]
 Credits:
 Special thanks to @Stomach_ache for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Dynamic Programming Math

 题意：给定一个数组，求其中的一个最大子集，要求该子集中任意的两个元素满足 x % y ==0 或者 y % x==0
 */
public class LargestDivisibleSubset {
    //参考的discussion里的解法， 核心思想从小到大，每一位数都能被比他大的数整除。
    public class Solution {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            List<Integer> result = new ArrayList();
            if (nums == null || nums.length == 0) return result;

            Arrays.sort(nums);
            int len = nums.length;
            int[] parent = new int[len];
            int[] count = new int[len];
            int max = 0, maxIndex = -1;
            //对于i从后往前看，找出每一个可以被它整除的数的数组，并更新它作为从这里开始，往后最大的subset，记录下最大数组开始的地方，并把下一个数记在parent里
            for (int i = len - 1; i >= 0; i--) {
                for (int j = i; j < len; j++) {
                    if (nums[j] % nums[i] == 0 && count[i] < count[j] + 1) {
                        count[i] = count[j] + 1;
                        parent[i] = j;
                        if (count[i] > max) {
                            max = count[i];
                            maxIndex = i;
                        }
                    }
                }
            }

            for (int i = 0; i < max; i++) {
                //找出最长的这个数组中的每一个数
                result.add(nums[maxIndex]);
                maxIndex = parent[maxIndex];
            }
            return result;
        }
    }

    //O(N^2) 时间 O(N) 空间

    /**
     * 和LIS很相似，dp[i]表示nums数组从0到i的最大集合的size.
     这题应该分成两个问题：

     得到最大集合size
     输出这个集合
     对于第一个问题，最大集合size就是dp数组的最大值，可以边画表边维护一个当前最大值;
     对于第二个问题，我们要维护一个parent数组，记录nums[i]加入到了哪个集合;
     dp[i] = max(dp[i], dp[j] + 1), where 0<=j<i

     注意
     注意这个case：
     [1,2,4,8,9,72]
     到72的时候，往前找到9，可以整除，更新dp[5]为max(1, 2 + 1) = 3,
     注意此时应该继续往前找，不能停，直到找到8,发现dp[3] + 1 = 5 > 3，于是更新dp[i]
     注意就是不能停，找到一个能整除并不够，前面有可能有更大的啊~~
     */
    public class Solution2 {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            if (nums.length == 0)
                return new ArrayList<Integer>();
            //nums has at least one element
            int n = nums.length;
            Arrays.sort(nums);
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            int[] parent = new int[n];
            Arrays.fill(parent, -1);//当parent数组中某数为-1时，表示这个数自己是一个集合
            int max = 1, max_index = -1;
            for (int i = 1; i < n; i++) {   //calculate dp[i]
                for (int j = i - 1; j >= 0; j--) {  //i > j
                    if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {    //positive distinct numbers in num
                        dp[i] = dp[j] + 1;
                        parent[i] = j;
                        if (dp[i] > max) {
                            max = dp[i];
                            max_index = i;
                        }
                    }
                }
            }
            return genResult(nums, parent, max_index);
        }
    }

    public List<Integer> genResult(int[] nums, int[] parent, int max_index) {
        List<Integer> result = new ArrayList<>();
        if (max_index == -1) {  //每个数都是单独成集合，都不能合并
            result.add(nums[0]);    //随便输出一个数，这里选第一个
            return result;
        }
        int iter = max_index;
        while (iter != -1) {
            result.add(nums[iter]);
            iter = parent[iter];
        }
        return result;
    }
}
