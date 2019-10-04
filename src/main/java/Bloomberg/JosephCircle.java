package Bloomberg;

import java.util.*;

/**
 * Created by cicean on 10/9/2016.
 */
public class JosephCircle {

    public int joseph(int totalNum, int countNum) {
        List<Integer> start = new ArrayList<>();
        for (int i = 1; i <= totalNum; i++) {
            start.add(i);
        }

        // from k start count
        int k = 0;
        while (start.size() > 1) {
            k = k + countNum;

            //mth people index position
            k = k % (start.size()) - 1;

            //the k is the last element?
            if (k < 0) {
                start.remove(start.size() - 1);
                k = 0;
            } else {
                start.remove(k);
            }
        }

        return start.get(0);
    }
}
