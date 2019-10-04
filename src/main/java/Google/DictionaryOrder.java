package Google;

import java.util.*;

/**
 * Created by cicean on 9/25/2016.
 */
public class DictionaryOrder {

    public boolean dictOrder(String s, String dict) {

        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        for (char d : dict.toCharArray()) {
            map.put(Character.valueOf(d),count++);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                int index = map.get(s.charAt(i));
                if (i < index) return false;
                else map.put(s.charAt(i), i);
            }
        }

        return true;

    }
    
    public static void main(String[] args) {
		DictionaryOrder slt = new DictionaryOrder();
		System.out.println(slt.dictOrder("google", "ole"));
		System.out.println(slt.dictOrder("elle", "ole"));
	}
}
