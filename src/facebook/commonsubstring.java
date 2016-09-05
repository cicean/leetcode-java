package facebook;

/**
 * Created by cicean on 9/4/2016.
 * 出两个给出两个string, leetcode, codyabc和一个数字k = 3,
 * 问两个string里面存不存在连续的common substring大于等于k.
 * 比如这个例子，两个string都有cod,所以返回true。
 */
public class commonsubstring {

        public boolean commonSub(String s1,String s2,int k){
            int len = s1.length();
            int wid  =s2.length();

            if(k > len || k > wid) return false;

            char[] arr1=s1.toCharArray();
            char[] arr2=s2.toCharArray();
            int[][] dp=new int[wid][len];

            for(int i=1; i < wid; i++){
                for(int j = 1;j < len; j++){
                    if(arr2[i] == arr1[j]){
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }else{
                        dp[i][j]=0;
                    }

                    if(dp[i][j] >= k) return true;
                }
            }
            return false;
        }



}
