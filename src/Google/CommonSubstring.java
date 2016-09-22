package Google;

/**
 * Created by cicean on 9/15/2016.
 * Google Onsite
 */
public class CommonSubstring {

	//给两个字符串，找到第二个在第一个中第一次出现的位置
    public int commonSubstringIndex(String A, String B) {
        int index = 0;
        
        if (A == null || B == null) return index;
        
        for (int i = 0; i < A.length(); i ++) {
            if (A.substring(i, i + B.length()).equals(B)) {
                return i;
            }
        }
        return index;
    }
    
    //找一个字符串中period的字符段
    
    public static void main(String[] args) {
		CommonSubstring slt = new CommonSubstring();
		String A = "abcababa";
		String B = "aba";
		System.out.println(slt.commonSubstringIndex(A, B));
 	}

}
