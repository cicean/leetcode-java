package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cicean on 9/7/2016. combination product，
 * 给一个质数数组，输出所有可能的product，输出顺序无所谓。 比如给一个数组 [2,3,5] 输出 [2,3,5,6,10,30] 这个比较简单
 * follow up是数组里有重复，结果没有重复。比如 给 [2,2,2]，要输出 [2,4,8]，顺序无所谓 SubSet I and SubSet II
 */
public class combinationproduct {

	public List<Integer> combinationProduct(int[] nums) {
		Arrays.sort(nums);
		List<Integer> res = new ArrayList<>();
		int prpduct = 1;
		combinationproductRe(nums, 0, 1, res);
		res.remove(0);
		return res;
	}

	private void combinationproductRe(int[] nums, int start, int product,
			List<Integer> res) {
		if (!res.contains(product)) {
			res.add(product);
		}

		for (int i = start; i < nums.length; i++) {
			product *= nums[i];
			combinationproductRe(nums, i + 1, product, res);
			product /= nums[i];
		}
	}

	// follow up if contain the duplicate;
    public List<Integer> combinationProductDu(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int prpduct = 1;
        combinationproductRe(nums, 0, 1, res);
        res.remove(0);
        return res;
    }

    public void combinationproductDuRe(int[] nums, int start, int product,
                                      List<Integer> res) {
        if (!res.contains(product)) {
            res.add(product);
        }

        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            product *= nums[i];
            combinationproductRe(nums, i + 1, product, res);
            product /= nums[i];
        }
    }
	

	public static void main(String[] args) {
		combinationproduct slt = new combinationproduct();
		int[] nums = new int[] { 2, 3, 5 };
        int[] numsDu = new int[] {2, 2, 2};
		slt.printList(slt.combinationProduct(nums));
        slt.printList(slt.combinationProductDu(numsDu));

	}

	public void printList(List<Integer> result) {
		if (result == null || result.size() == 0)
			System.out.println("Empty List!");
		for (Integer l : result) {

			System.out.print(l + " ");

			System.out.println();
		}
	}

}
