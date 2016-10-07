package Google;

import java.util.*;

/**
 * Created by cicean on 9/23/2016.
 * ʵ��һ��iterator, input ��һ��array{3, 8, 0, 12, 2, 9}, ϣ������� {8, 8, 8, 9, 9},
 * Ҳ����eventh number���� ��Ƶ�� oddth number ����ʣ�
 * {3, 8, 12, 0, 2, 9}�� ����3��8�� 0��12�� 2��9.
 */
public class ImplementInterator {

    public int[] arrayInterator(int[] nums) {
        Integer[] a = null;
        List<Integer> res = new ArrayList<>();
        if (nums == null) return null;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                if (nums[i] == 0) {
                    i++;
                } else {
                    a = new Integer[nums[i]];
                }
            } else {
                Arrays.fill(a,Integer.valueOf(nums[i]));
                res.addAll(new ArrayList<Integer>(Arrays.asList(a)));
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static void main(String[] args) {
		ImplementInterator slt = new ImplementInterator();
		int[] nums = {3, 8, 0, 12, 2, 9};
		int[] res = slt.arrayInterator(nums);
		for (int r : res) {
			System.out.println(r +", ");
		}
		
	}

}
