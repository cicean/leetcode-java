package Bloomberg;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cicean on 9/12/2016.
 * bloomberg ����
 * 1. ��һ�����飬�����ж��ٸ��������ظ������ӣ�
 [5,2,2,4] -> ����1����Ϊֻ��2һ�����ظ���
 [8,33,7,8,7,7] -> ����2����Ϊ7��8�����ظ�
 [1,2,3] -> ����0����Ϊû���ظ�����
 */
public class CountDuplicate {

    //O(nlogn + n)
    public int countDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Arrays.sort(nums);
        int count = 0;
        boolean cur = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i] && cur == false) {
                count++;
                cur = true;
            } else {
                cur = false;
            }
        }
        return  count;
    }

    public int countDuplicate_bitset(int[] nums) {
        BitSet bs = new BitSet();
        Set<Integer> res = new HashSet<>();
        for (int i :nums) {
            if (!bs.get(i)) bs.set(i);
            else res.add(i);
        }
        return res.size();

    }


    
    public static void main(String[] args) {
		CountDuplicate slt = new CountDuplicate();
		int[] nums = {2,1,2, 4, 8, 7, 8, 8,4};
		System.out.println(slt.countDuplicate_bitset(nums));
	}
}
