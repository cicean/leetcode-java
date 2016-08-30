/*
 * 161	One Edit Distance 	24.0%	Medium
 * Given two strings S and T, determine if they are both one edit distance apart.

Hint:
1. If | n �C m | is greater than 1, we know immediately both are not one-edit distance apart.
2. It might help if you consider these cases separately, m == n and m �� n.
3. Assume that m is always �� n, which greatly simplifies the conditional statements. If m > n, we could just simply swap S and T.
4. If m == n, it becomes finding if there is exactly one modified operation. If m �� n, you do not have to consider the delete operation. Just consider the insert operation in T.

[����]
��ķ���������Edit Distance�㷨����ʵ�����Լ�֮ǰ���������Ϣѧ�����Needleman-Wunsch�㷨����ª�����ˣ�����һ�·���Edit Distance�㷨��DP�ľ����⡣����

�����ĿֻҪO(1)�Ŀռ䣬O(n)��ʱ�临�Ӷȡ��ٶ���һ�¼������
1���޸�һ���ַ����ٶ�����String�ȳ���
2������һ���ַ����м���߽�β��

[ע������]
1��shift����������
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
	  * ���Ӷ�
ʱ�� O(N) �ռ� O(1)

˼·
��Ȼ���ǿ�����Edit Distance�Ľⷨ����distance�Ƿ�Ϊ1����Leetcode�лᳬʱ���������ǿ�������ֻ��һ����ͬ���ص���O(N)ʱ������ɡ���������ַ���ֻ��һ���༭���룬��ֻ�����������

�����ַ���һ������ʱ��˵����һ���滻����������ֻҪ����Ӧλ���ǲ���ֻ��һ���ַ���һ��������
һ���ַ�������һ����1��˵���и����ӻ�ɾ�����������Ǿ��ҵ���һ����Ӧλ�ò�һ�����Ǹ��ַ�������ϳ��ַ������Ǹ��ַ�֮��Ĳ��ֺͽ϶��ַ����Ǹ��ַ���֮��Ĳ�����һ���ģ������Ҫ��
��������ַ������Ȳ�����1���϶�����
	  * @author cicean
	  *
	  */
	 public class Solution {
		    public boolean isOneEditDistance(String s, String t) {
		        int m = s.length(), n = t.length();
		        if(m == n) return isOneModified(s, t);
		        if(m - n == 1) return isOneDeleted(s, t);
		        if(n - m == 1) return isOneDeleted(t, s);
		        // ���Ȳ�����2ֱ�ӷ���false
		        return false;
		    }
		    
		    private boolean isOneModified(String s, String t){
		        boolean modified = false;
		        // ���Ƿ�ֻ�޸���һ���ַ�
		        for(int i = 0; i < s.length(); i++){
		            if(s.charAt(i) != t.charAt(i)){
		                if(modified) return false;
		                modified = true;
		            }
		        }
		        return modified;
		    }
		    
		    public boolean isOneDeleted(String longer, String shorter){
		        // �ҵ���һ�鲻һ�����ַ����������Ƿ�һ��
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
