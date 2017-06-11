package Godaddy;

/**
 * Created by cicean on 10/14/2016.
 */
public class RearrangeWords {

	public String rearrangeWord(String s) {
		int last = s.length() - 1;
		int i = last;
		String res = "";
		char[] tmp = s.toCharArray();
		while (i > 0 && tmp[i - 1] >= tmp[i])
			--i;
		if (i == 0) {
			return "no anwser";
		}

		for (int j = last; j >= i; --j) {
			if (tmp[j] > tmp[i - 1]) {
				char c = tmp[j];
				tmp[j] = tmp[i - 1];
				tmp[i - 1] = c;
				for (int l = i, r = last; l < r; ++l, --r) {
					char x = tmp[l];
					tmp[l] = tmp[r];
					tmp[r] = x;
				}
			}
		}

		return new String(tmp);
	}

	public static void main(String[] args) {
		RearrangeWords slt = new RearrangeWords();
		String s = "dcba";
		System.out.println(slt.rearrangeWord(s));
	}
}
