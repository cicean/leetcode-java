package Bloomberg;

import java.util.Random;

/**
 * Created by cicean on 9/12/2016.
 *  给你一个长度为n的数组作为输入,
 *  给你一个现有功能random(i,j) 随机生成从i到j的某个值.
 *  希望你写一个功能, 从array里面等概率挑出三个数(就要三个,不是参数),
 *  这三个数只要求index不同且等概率, 不要求值不同.
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
