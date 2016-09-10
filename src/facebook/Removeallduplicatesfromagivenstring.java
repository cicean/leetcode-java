package facebook;

import java.util.*;

/**
 * Created by cicean on 9/4/2016.
 *
 */
public class Removeallduplicatesfromagivenstring {
    public String removeDuplicates(String string) {
        string = string.toLowerCase();
        BitSet bitset = new BitSet(26);
        StringBuilder result = new StringBuilder();
        for (int i=0;i<string.length();i++) {
            char ch = string.charAt(i);
            int pos = ch - 'a';
            if (!bitset.get(pos)) {
                bitset.set(pos);
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Removeallduplicatesfromagivenstring slt = new Removeallduplicatesfromagivenstring();
        String s = "kbbkww";
        String output = slt.removeDuplicates(s);
        System.out.print(output);
    }
}
