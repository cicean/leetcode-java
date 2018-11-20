package DataBricks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicean on 8/9/2018.
 */
public class ValidSQL {

    public List<String> parse(String s) {
        List<String> res = new ArrayList<>();
        int i = 0, n = s.length();
        while (i < n) {
            String cmd = "";
            boolean quote = false;
            while (i < n && (s.charAt(i) != ';' || quote)) {
                cmd += s.charAt(i);
                if (s.charAt(i) == '\\' && i + 1 < n) {
                    cmd += s.charAt(++i);
                } else if (s.charAt(i) == '"') {
                    quote = !quote;
                }
                i++;
            }
            res.add(cmd);
            i++;
        }
        return res;
    }

}
