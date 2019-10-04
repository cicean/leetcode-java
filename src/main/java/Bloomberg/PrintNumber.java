package Bloomberg;

import datastructure.PrintList;

import java.util.*;

/**
 * Created by cicean on 9/12/2016.
 * Bloomberg �����澭
 * ʵ��һ����������ӡ100000��999999����ǰ��λSum���ں���λSum����.
 */
public class PrintNumber {

    Map<Integer, List<Integer>> map = new HashMap<>();

    private int getSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }

        return sum;
    }

    public List<Integer> printNumber() {

        for (int i = 1; i <= 999; i++) {
            int key = getSum(i);
            if (map.containsKey(key)) map.get(key).add(i);
            else {
                List<Integer> value = new ArrayList<>();
                value.add(i);
                map.put(key, value);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (List<Integer> entry : map.values()) {
            for (Integer i : entry) {
                for (Integer j : entry) {
                    int tmp = i * 1000 + j;

                    if (tmp > 100000) {
                        res.add(tmp);
                    }
                }
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
		PrintNumber slt = new PrintNumber();
		PrintList<Integer> res = new PrintList<>();
		System.out.println(slt.printNumber().size());
		
	}
}
