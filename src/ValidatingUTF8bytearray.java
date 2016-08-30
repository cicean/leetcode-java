/**
 * 
 * @author cicean
 *
 */
public class ValidatingUTF8bytearray {

	public static boolean validate(byte[] bytes) {

	    int length = bytes.length;

	    if (length < 1 || length > 6)
	        return false;

	    byte b = bytes[0];

	    if (length == 1)
	        return (b & (1 << 7)) == 0;

	    int n;

	    if ((b & 0xFF) >> 1 == (byte) 0b1111110)
	        n = 5;
	    else if ((b & 0xFF) >> 2 == (byte) 0b111110)
	        n = 4;
	    else if ((b & 0xFF) >> 3 == (byte) 0b11110)
	        n = 3;
	    else if ((b & 0xFF) >> 4 == (byte) 0b1110)
	        n = 2;
	    else if ((b & 0xFF) >> 5 == (byte) 0b110)
	        n = 1;
	    else
	        return false;

	    if (length-1 != n)
	        return false;

	    for (int i = 1; i < length; i++)
	        if ((bytes[i] & 0xFF) >> 6 != (byte) 0b10)
	            return false;

	    return true;

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
