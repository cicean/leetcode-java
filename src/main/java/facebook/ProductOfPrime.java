package facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cicean on 9/4/2016.
 */
public class ProductOfPrime {
    //product of the array   给定一个array，返回里面元素乘积的所有可能值。
    // 例如给定array:[1,2,3,4]   应该返回 [1, 2, 3, 4, 6, 8, 12, 24]

    public List<Integer> productofArray(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0)  return res;
        backtracking(nums, 0, 1, res);
        Collections.sort(res);
        return res;
    }

    private void backtracking(int[] nums, int start, int product, List<Integer> res) {
        if (res.contains(product)) return;
        res.add(product);
        for (int i = start; i < nums.length; i++) {
            product *= nums[i];
            backtracking(nums, i + 1, product, res);
            product /= nums[i];
            //System.out.println(product);
        }
    }
}
