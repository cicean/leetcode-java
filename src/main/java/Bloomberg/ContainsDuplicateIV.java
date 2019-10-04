package Bloomberg;

/**
 * Created by cicean on 9/20/2016. 在一个排序的数组中找一个数出现的次数， 用binary search.
 */
public class ContainsDuplicateIV {

	public int containsDuplicate(int[] nums, int k) {
		if (nums == null)
			return 0;

		int res = 0;
		int i = 0;
		int j = nums.length;
		int index = 0;
		while (i < j) {
			int mid = (i + j) / 2;
			if (nums[mid] < k)
				i = mid + 1;
			else if (nums[mid] > k)
				j = mid - 1;
			else {
				res++;
				index = mid;
				break;
			}
		}

		for (int n = index + 1; n < nums.length; n++) {
			if (nums[n] == k)
				res++;
			else
				break;
		}
		for (int n = index - 1; n >= 0; n--) {
			if (nums[n] == k)
				res++;
			else
				break;
		}

		return res;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 2, 3, 3, 3, 4, 5 };
		int[] nums2 = { 1, 2, 2, 4, 6 };

		ContainsDuplicateIV slt = new ContainsDuplicateIV();

		System.out.println(slt.containsDuplicate(nums, 0));
		System.out.println(slt.containsDuplicate(nums2, 2));

	}

}
