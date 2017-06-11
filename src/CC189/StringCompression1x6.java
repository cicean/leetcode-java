package CC189;

/**
 * Created by cicean on 10/10/2016.
 */
public class StringCompression1x6 {

    public String compress(String str) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) == str.charAt(i)) {
                count++;
            } else {
                sb.append(str.charAt(i - 1));
                sb.append(count);
                count = 1;
            }
        }

        if ( count != 1) {
            sb.append(str.charAt(str.length() - 1));
            sb.append(count);
        }

        return sb.toString();
    }
    
    public static void main(String[] args) {
    	StringCompression1x6 slt = new StringCompression1x6();
        System.out.println(slt.compress("aabcccccaaa"));
	}

}
