package Google;

/**
 * Created by cicean on 9/23/2016.
 * ale is the lexical order smallest subsequnce of length 3.
 * 给一个string, 找出lexical order 最小的， size==k的， subsequence, (note, not substring)
String findMin(String s, k){} 
e.g.
input
s=pineapple, k==3, 
ale is the lexical order smallest subsequnce of length 3. 
我是暴力求解的： 
1. find the first occur position of distinct char. 
2. then start from that position. 
3. dfs to find lenght==3, subsequence(dfs, combination way); 
4. find the one with smallest lexical order. 
 */
public class MinLexicalOrderSubsequnce {

    public String minLexical(String num, int k) {
        StringBuilder sb = new StringBuilder();
        if (num == null) return "";
        int n = num.length();
        if (n <= k) return num;
        int deletenum = n - k;
        for (char c : num.toCharArray()) {
            while(deletenum > 0 && sb.length() != 0 && sb.charAt(sb.length() - 1) > c) {
                sb.setLength(sb.length() - 1);
                deletenum--;
            }
            sb.append(c);
        }
        
        sb.setLength(k);

        return sb.toString();
    }
    
    public static void main(String[] args) {
		MinLexicalOrderSubsequnce slt = new MinLexicalOrderSubsequnce();
		System.out.println(slt.removeKdigits("pinabcdefg", 1));
    
	}
}
