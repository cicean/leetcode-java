package Google;

/**
 * Created by cicean on 9/24/2016.
 *
 */
public class EncodeString {

    /**
     * ������"aaaabb" --> "4xa2xb"
     �������pattern�� <���� + x + һ���ַ�>������"4xab"���������"aaaab".  Follow up ���test
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
