package Godaddy;

import java.util.*;

/**
 * Created by cicean on 10/14/2016.
 *
 */
public class MissingWords {

	public List<String> missingWords(String S, String T) {

		int i = 0;
		int j = 0;
		int start = 0;
		boolean flag = false;
		List<String> res = new ArrayList<>();
		while (j < T.length() && i < S.length()) {
			if (S.charAt(i) != T.charAt(j) && S.charAt(i) != ' ') {
				if (!flag) {
					start = i;
					i++;
					flag = true;
				} else {
					i++;
				}
			}

			if (S.charAt(i) != T.charAt(j) && S.charAt(i) == ' ') {
				
				System.out.println("i = " + i + ", j = " + j + "start = " + start);
				System.out.println(S.substring(start , i).isEmpty());
				  res.add(S.substring(start, i));
				
				i++;
				start = i;
				flag = false;

			}

			if (S.charAt(i) == T.charAt(j)) {
				i++;
				j++;
			}

		}

		if (S.length() - i > 0) {
			String[] tokens = S.substring(i, S.length()).split(" ");
			for (String s : tokens) {
				if(!s.isEmpty()) res.add(s);
			}
		}
		
		

		return res;
	}

    public List<String> missingWords1(String s, String t) {

        String[] token1s = s.split(" ");
        String[] token2s = t.split(" ");

        HashMap<String, Integer> map1 = new LinkedHashMap<>();
        List<String> res = new ArrayList<>();

        for (String token2 : token2s) {
        	System.out.println(token2);
            if (map1.containsKey(token2)) map1.put(token2, map1.get(token2) + 1);
            else map1.put(token2, 1);
        }

        for (String token1 : token1s) {
        	System.out.println(token1);
            if (!map1.containsKey(token1)) {
               res.add(token1);
            } else {
            	if( map1.get(token1) - 1 == 0) map1.remove(token1);
            	else map1.put(token1, map1.get(token1) - 1);
            }
        }

        for (Map.Entry<String, Integer> m : map1.entrySet()) {
            int count = m.getValue();
            while (count > 0 ) {
                res.add(m.getKey());
                count--;

            }
        }

        return res;

    }

	public static void main(String[] args) {
		MissingWords slt = new MissingWords();
		String S = "I am using hark";
		String T = "am using hark";
		
		for (String string : slt.missingWords1(S, T)) {
			System.out.println(string);
		}
	}
}
