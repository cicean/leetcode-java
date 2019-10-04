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

 ���⣺����һ�����飬�����е�һ������Ӽ���Ҫ����Ӽ������������Ԫ������ x % y ==0 ���� y % x==0
 */
public class LargestDivisibleSubset {
    //�ο���discussion��Ľⷨ�� ����˼���С����ÿһλ�����ܱ����������������
    public class Solution {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            List<Integer> result = new ArrayList();
            if (nums == null || nums.length == 0) return result;

            Arrays.sort(nums);
            int len = nums.length;
            int[] parent = new int[len];
            int[] count = new int[len];
            int max = 0, maxIndex = -1;
            //����i�Ӻ���ǰ�����ҳ�ÿһ�����Ա����������������飬����������Ϊ�����￪ʼ����������subset����¼��������鿪ʼ�ĵط���������һ��������parent��
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
                //�ҳ������������е�ÿһ����
                result.add(nums[maxIndex]);
                maxIndex = parent[maxIndex];
            }
            return result;
        }
    }

    //O(N^2) ʱ�� O(N) �ռ�

    /**
     * ��LIS�����ƣ�dp[i]��ʾnums�����0��i����󼯺ϵ�size.
     ����Ӧ�÷ֳ��������⣺

     �õ���󼯺�size
     ����������
     ���ڵ�һ�����⣬��󼯺�size����dp��������ֵ�����Ա߻����ά��һ����ǰ���ֵ;
     ���ڵڶ������⣬����Ҫά��һ��parent���飬��¼nums[i]���뵽���ĸ�����;
     dp[i] = max(dp[i], dp[j] + 1), where 0<=j<i

     ע��
     ע�����case��
     [1,2,4,8,9,72]
     ��72��ʱ����ǰ�ҵ�9����������������dp[5]Ϊmax(1, 2 + 1) = 3,
     ע���ʱӦ�ü�����ǰ�ң�����ͣ��ֱ���ҵ�8,����dp[3] + 1 = 5 > 3�����Ǹ���dp[i]
     ע����ǲ���ͣ���ҵ�һ����������������ǰ���п����и���İ�~~
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
            Arrays.fill(parent, -1);//��parent������ĳ��Ϊ-1ʱ����ʾ������Լ���һ������
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
        if (max_index == -1) {  //ÿ�������ǵ����ɼ��ϣ������ܺϲ�
            result.add(nums[0]);    //������һ����������ѡ��һ��
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
