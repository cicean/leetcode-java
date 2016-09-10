package facebook;

/**
 * Created by cicean on 9/4/2016.
 * ��������������string, leetcode, codyabc��һ������k = 3,
 * ������string����治����������common substring���ڵ���k.
 * ����������ӣ�����string����cod,���Է���true��
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
