package Bloomberg;

import java.util.Random;

/**
 * Created by cicean on 9/12/2016.
 *  ����һ������Ϊn��������Ϊ����,
 *  ����һ�����й���random(i,j) ������ɴ�i��j��ĳ��ֵ.
 *  ϣ����дһ������, ��array����ȸ�������������(��Ҫ����,���ǲ���),
 *  ��������ֻҪ��index��ͬ�ҵȸ���, ��Ҫ��ֵ��ͬ.
 */
public class ThreeRandomNumber {

    private int[] res = new int[3];

    private int randomRang(int i, int j) {
        int res = 0;
        return res;
    }

    public int[] randomNumber(int[] nums) {
        if (nums == null || nums.length <3) return res;
        int len = nums.length;
        Random random = new Random();
        int i = random.nextInt(len);
        int j = random.nextInt(len);
        res[0] = nums[this.randomRang(0,i)];
        res[1] = nums[this.randomRang(i,j)];
        res[2] = nums[this.randomRang(j,len)];
        return res;

    }
}
