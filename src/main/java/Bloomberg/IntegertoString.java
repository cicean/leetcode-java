package Bloomberg;

/**
 * Created by cicean on 9/11/2016.
 * Integer to String
 *  �������ţ���������СֵԽ��ǿ������
 *
 */
public class IntegertoString {

    public String integerTostring(int n) {
        if (n > Integer.MAX_VALUE || n < Integer.MIN_VALUE) return "";
        String res = "";
        if (n < 0) {
            res += "-";
        }
        res += String.valueOf(Math.abs(n));

        return res;
    }
    
    public static void main(String[] args) {
		IntegertoString slt = new IntegertoString();
		System.out.println(slt.integerTostring(0));
	}
}
