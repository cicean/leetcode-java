package eBay;

import java.util.*;

public class LongestSubArrayHavingSumK {

    public static int atMostSum(int[] nums,int k){
        int sum = 0;
        int cnt = 0, maxcnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((sum + nums[i]) <= k) {
                sum += nums[i];
                cnt++;
            }
             else if(sum!=0){
                sum = sum - nums[i - cnt] + nums[i];
            }
            maxcnt = Math.max(cnt, maxcnt);
        }
        return maxcnt;
    }

    public static void main(String[] args){
        int[] nums = { 1, 2, 1, 0, 1, 1, 0 };
        int k = 4;
        System.out.print(atMostSum(nums,k));
    }

}
