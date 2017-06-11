package Google;

/**
 * Created by cicean on 9/24/2016.
 *
 */
public class EncodeString {

    /**
     * 举例："aaaabb" --> "4xa2xb"
     解码规则：pattern是 <数字 + x + 一个字符>，比如"4xab"，解码后是"aaaab".  Follow up 如何test
     */

    public String encodeString(String s) {
        StringBuilder sb = new StringBuilder();
        if (s == null || s.length() == 0) return sb.toString();
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
            } else {
                if (count != 1) {
                sb.append(count);
                sb.append('x');
                count = 1;
                }
                sb.append(s.charAt(i));
            }
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
		EncodeString slt = new EncodeString();
		System.out.println(slt.encodeString("aaaa"));
	}
}
