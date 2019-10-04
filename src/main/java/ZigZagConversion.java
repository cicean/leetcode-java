/*
 * 6	ZigZag Conversion	22.1%	Easy
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */


public class ZigZagConversion {
	
	public String convert_1(String s, int nRows) {
        int n = s.length();
        if (n <= 1 || nRows <= 1) return s;
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j + i < n; j += 2*nRows - 2) {
                res.append(s.charAt(j+i));
                if (i == 0 || i == nRows - 1) continue;
                if (j + 2*nRows - 2 - i < n) 
                    res.append(s.charAt(j + 2*nRows - 2 - i));
            }
        }
        return res.toString();
    }
	
	public String convert_2(String s, int nRows) {
        if (nRows == 1)
            return s;
        StringBuilder builder = new StringBuilder();
        int step = 2 * nRows - 2;
        for (int i = 0; i < nRows; i++) {
            if (i == 0 || i == nRows - 1) {
                for (int j = i; j < s.length(); j = j + step) {
                    builder.append(s.charAt(j));
                }
            } else {
                int j = i;
                boolean flag = true;
                int step1 = 2 * (nRows - 1 - i);
                int step2 = step - step1;
                while (j < s.length()) {
                    builder.append(s.charAt(j));
                    if (flag)
                        j = j + step1;
                    else
                        j = j + step2;
                    flag = !flag;
                }
            }
        }
        return builder.toString();
    }
	
	public static void main(String[] args)
	{
		ZigZagConversion slt = new ZigZagConversion();
		String str =  "PAYPALISHIRING";
		int row = 3;
		
		String coverseresult = slt.convert_1(str, row);
		
		System.out.println(coverseresult);
	}
	
}

