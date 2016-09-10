package facebook;

import java.util.HashSet;

/**
 * Created by cicean on 9/4/2016.
 * 出两个给出两个string, leetcode, codyabc和一个数字k = 3,
 * 问两个string里面存不存在连续的common substring大于等于k.
 * 比如这个例子，两个string都有cod,所以返回true。
 */
public class commonsubstring {

        //O(m*n)
        public boolean commonSub(String s1,String s2,int k){
            if (s1 == null || s2 == null || s1.length() < k || s2.length() < k || k == 0) return false;
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

    //O(m + n)
    public boolean commonSubHelp(String s1,String s2,int k){
        
        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i <= s1.length() - k; i++) {
            if (!hs.contains(s1.substring(i, i + k))) hs.add(s1.substring(i, i + k));
            System.out.println(s1.substring(i, i + k));
        }

        for (int i = 0; i <= s2.length() - k; i++) {
            if (hs.contains(s2.substring(i, i + k))) return true;
        }

        return false;
    }
    
    public boolean commonSub2(String s1,String s2,int k) {
    	if (s1 == null || s2 == null || k == 0) return false;
    	if (s1.length() >= s2.length()) return commonSubHelp(s2, s1, k);
    	if (s1.length() < s2.length()) return commonSubHelp(s1, s2, k);
    	return false;
    }
    
    public static void main(String[] args) {
    	String s1 = "leetcode";
    	String s2 = "code";
    	int k = 3;
    	commonsubstring slt = new commonsubstring();
    	System.out.println(slt.commonSub2(s1, s2, k));
    	System.out.println(slt.commonSub(s1, s2, k));
		
	}



}
