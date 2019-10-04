package Airbnb;
import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 */
public class CollatzConjecture {

    /**
     * ��Ŀ�Ǹ��㹫ʽ������ż���Ļ���2�������Ļ��ͱ��3*n+1�������κ�һ��������
     * ��ѧ����������������1��ÿ��һ��������1����һ�����ޣ����ҳ���Χ���������
     */

    Map<Integer, Integer> map = new HashMap<>();

    public int findLongestSteps(int num) {
        if (num < 1) return 0;
        int res = 0;
        for (int i = 1; i <= num; i++) {
            int t = findSteps(i);
            map.put(i, t);
            res = Math.max(res, t);
        }
        return res;
    }

    private int findSteps(int num) {
        if (num <= 1) {
            return 1;
        }

        if (map.containsKey(num)) {
            return map.get(num);
        }

        if (num % 2 == 0) {
            return 1 + findSteps(num / 2);
        }

        return 1 + findSteps(3 * num + 1);
    }

}
