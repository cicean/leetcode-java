import java.util.ArrayList;
import java.util.List;

/*
 * 247	Strobogrammatic Number II 	22.4%	Medium
 * LeetCode: Strobogrammatic Number II
AUG 9 2015

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,

Given n = 2, return ["11","69","88","96"].
 */
public class StrobogrammaticNumberII {

	private char[] validNumbers = new char[] {'0', '1', '6', '8', '9'};
	private char[] singleable = new char[] {'0', '1',  '8'};
	
	public List<String> findStrobogrammatic(int n) {
		assert n > 0;
		List<String> ret = new ArrayList<>();
		
		if (n == 1) {
			for (char c : singleable) {
				ret.add(String.valueOf(c));
				
			}
			return ret;
		}
		
		if (n%2 == 0) {
			helper(n, new StringBuilder(), ret);
		} else {
			helper(n - 1, new StringBuilder(), ret);
			List<String> tmp = new ArrayList<>();
			for (String s : ret) {
				for (char c : singleable) {
					tmp.add(new StringBuilder(s).insert(s.length()/2, c).toString());
				}
			}
			ret = tmp;
		}
		return ret;
	}
	
	private void helper(int n, StringBuilder sb, List<String> ret) {
		if (sb.length() > n) return;
		
		if (sb.length() == n) {
			if (sb.length() > 0 && sb.charAt(0) != '0') {
				ret.add(sb.toString());
			}
			return;
		}
		
		for (char c : validNumbers) {
			StringBuilder tmp = new StringBuilder(sb);
			String s = "" + c + findMatch(c);
			tmp.insert(tmp.length()/2, s);
			helper(n, tmp, ret);
		}
	}
	
	private char findMatch(char c) {
		switch (c) {
		case '1':
			return '1';
		case '6':
			return '9';
		case '9':
			return '6';
		case '8':
			return '8';
		case '0':
			return '0';
		default:
			return 0;
		
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StrobogrammaticNumberII slt = new StrobogrammaticNumberII();
		int n = 2;
		System.out.println(slt.findStrobogrammatic(n));
				
	}

}
