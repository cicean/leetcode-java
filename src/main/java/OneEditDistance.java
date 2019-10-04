/*
 * 161	One Edit Distance 	24.0%	Medium
 * Given two strings S and T, determine if they are both one edit distance apart.

Hint:
1. If | n – m | is greater than 1, we know immediately both are not one-edit distance apart.
2. It might help if you consider these cases separately, m == n and m ≠ n.
3. Assume that m is always ≤ n, which greatly simplifies the conditional statements. If m > n, we could just simply swap S and T.
4. If m == n, it becomes finding if there is exactly one modified operation. If m ≠ n, you do not have to consider the delete operation. Just consider the insert operation in T.

[分析]
最笨的方法就是用Edit Distance算法，其实就是自己之前搞的生物信息学里面的Needleman-Wunsch算法。孤陋寡闻了，搜了一下发现Edit Distance算法是DP的经典题。。。

这个题目只要O(1)的空间，O(n)的时间复杂度。假定有一下几种情况
1）修改一个字符（假定两个String等长）
2）插入一个字符（中间或者结尾）

[注意事项]
1）shift变量的作用
 */
public class OneEditDistance {
	
	public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null)  return false;
        int a = s.length(), b = t.length();
        int i = 0, shift = Math.abs(a - b);
        if (shift > 1) return false;
        int m = a < b ? a : b;
        while (i < m && s.charAt(i) == t.charAt(i)) ++i;
        if (i == m) return shift > 0;
        if (shift == 0)  i++;
        while (i < m && (a < b ? s.charAt(i) == t.charAt(i + shift) : s.charAt(i + shift) == t.charAt(i))) i++;
        return i == m;
    }
	
	 public boolean isOneEditDistance_1(String s, String t) {
	        int m = s.length(), n = t.length();
	        if (m>n) return isOneEditDistance(t, s);
	        if (n-m>1) return false;
	        int i =0, shift = n-m;
	        while (i<m && s.charAt(i)==t.charAt(i)) ++i;
	        if (i==m) return shift > 0; // if two string are the same (shift==0), return false
	        if (shift==0) i++; // if n==m skip current char in s (modify operation in s)
	        while (i<m && s.charAt(i)==t.charAt(i+shift)) i++; // use shift to skip one char in t
	        return i == m;
	    }
	 
	 /**
	  * 复杂度
时间 O(N) 空间 O(1)

思路
虽然我们可以用Edit Distance的解法，看distance是否为1，但Leetcode中会超时。这里我们可以利用只有一个不同的特点在O(N)时间内完成。如果两个字符串只有一个编辑距离，则只有两种情况：

两个字符串一样长的时候，说明有一个替换操作，我们只要看对应位置是不是只有一个字符不一样就行了
一个字符串比另一个长1，说明有个增加或删除操作，我们就找到第一个对应位置不一样的那个字符，如果较长字符串在那个字符之后的部分和较短字符串那个字符及之后的部分是一样的，则符合要求
如果两个字符串长度差距大于1，肯定不对
	  * @author cicean
	  *
	  */
	 public class Solution {
		    public boolean isOneEditDistance(String s, String t) {
		        int m = s.length(), n = t.length();
		        if(m == n) return isOneModified(s, t);
		        if(m - n == 1) return isOneDeleted(s, t);
		        if(n - m == 1) return isOneDeleted(t, s);
		        // 长度差距大于2直接返回false
		        return false;
		    }
		    
		    private boolean isOneModified(String s, String t){
		        boolean modified = false;
		        // 看是否只修改了一个字符
		        for(int i = 0; i < s.length(); i++){
		            if(s.charAt(i) != t.charAt(i)){
		                if(modified) return false;
		                modified = true;
		            }
		        }
		        return modified;
		    }
		    
		    public boolean isOneDeleted(String longer, String shorter){
		        // 找到第一组不一样的字符，看后面是否一样
		        for(int i = 0; i < shorter.length(); i++){
		            if(longer.charAt(i) != shorter.charAt(i)){
		                return longer.substring(i + 1).equals(shorter.substring(i));
		            }
		        }
		        return true;
		    }
		}

	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s= "abc";
		String t = "abcd";
		OneEditDistance slt = new OneEditDistance();
		System.out.print(slt.isOneEditDistance(s, t));
	
		
	}

}
