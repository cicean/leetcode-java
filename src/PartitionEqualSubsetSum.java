import java.util.*;

/**
 * Created by cicean on 10/16/2016.
 * 416. Partition Equal Subset Sum   QuestionEditorial Solution  My Submissions
 Total Accepted: 4277
 Total Submissions: 11179
 Difficulty: Medium
 Contributors: Admin
 Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 Note:
 Each of the array element will not exceed 100.
 The array size will not exceed 200.
 Example 1:

 Input: [1, 5, 11, 5]

 Output: true

 Explanation: The array can be partitioned as [1, 5, 5] and [11].
 Example 2:

 Input: [1, 2, 3, 5]

 Output: false

 Explanation: The array cannot be partitioned into equal sum subsets.
 Hide Company Tags eBay
 Hide Tags Dynamic Programming

 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] a) {
        int sum = 0;
        for(int n:a){
            sum += n;
        }
        if(sum%2>0)
            return false;

        boolean []dp = new boolean[sum/2+1];
        dp[0]=true; // empty array
        int max=0;
        for(int n: a){
            if(n>sum/2)
                return false;  // single number making bigger than sum/2 no way equal partition.
            for(int j = 0; j<=max; j++){
                if(dp[j] && ((j+n) <= sum/2) ){
                    dp[j+n] = true;
                    max = Math.max(max, j+n);
                    if(max==sum/2)
                        return true;
                }
            }
        }
        return dp[sum/2];
    }

    //dfs
    public boolean canPartition_dfs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0;
        for(int i : nums){
            if(map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else{
                map.put(i, 1);
            }
            sum += i;
        }
        if(sum % 2 == 1) return false;
        return helper(map, sum / 2);
    }

    private boolean helper(Map<Integer, Integer> map, int target){
        /*target is achieveable*/
        if(map.containsKey(target) && map.get(target) > 0) return true;
        /*dfs*/
        for(int key : map.keySet()){
            if(key < target && map.get(key) > 0){
                map.put(key, map.get(key) - 1);
                if(helper(map, target - key)) return true;
                map.put(key, map.get(key) + 1);
            }
        }
        return false;
    }

}
