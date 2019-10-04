/*
 * 
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

时间O(n)和空间O(1)解法

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)

Hide Tags Array
Hide Similar Problems (H) Trapping Rain Water (M) Maximum Product Subarray

 * 
 * 不能用除法， 则维护当前元素左边所有元素的乘积以及右边所有元素的乘积， 
 * 相乘得到 product of array except self !
 * 
 * Because we cannot use division, so assume we have two integer arrays with the same length of nums, int[] leftProd = new int[nums.length];
 *  int[] rightProd = new int[nums.length], we store the product of all the left elements in leftProd and the product of all the right elements in rightProd, 
 *  then the product of leftProd[i] and rightProd[i] will be the value we want to put into the result. take the example of num[] = {2, 4, 3, 6}, 
 *  thenleftProd will be {1, 2, 8, 24} , and rightProd will be {72, 18, 6, 1}.
 *  
 *  维持两个数组, left[] and right[]. 分别记录 第i个元素 左边相加的和left[i] and  右边相加的和right[i].  
 *  那么结果res[i]即为  left[i]+right[i]. follow up 要求O(1)空间. 利用返回的结果数组,先存right数组. 
 *  再从左边计算left,同时计算结果值, 这样可以不需要额外的空间.
 */
public class ProductofArrayExceptSelf {
	
	/**
	 * 算一遍全部元素的积再分别除以每一项要仔细考虑元素为零的情况。
	 * 没有零直接除下去。
	 * 一个零 零的位置对应值为其他元素之积，其他位置为零。
	 * 两个以上的零全部都是零。
	 * O(N) O(1)
	 * @param nums
	 * @return
	 */
	
	public int[] productExceptSelf(int[] nums) {
        int p = 1, zeroNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                p = p * nums[i];
            } else {
                zeroNum += 1;
            }
        }
        
        if (zeroNum == 0) {
            for (int i = 0; i < nums.length; i++) {
            nums[i] = p / nums[i];
            }
        } 
    
        if (zeroNum == 1) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    nums[i] = p;
                } else {
                    nums[i] = 0;
                }
            }
        }
        
        if (zeroNum > 1) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
        
        return nums;
    }
	
	/**
	 * 实际上，我们可以用结果数组自身来存储left和right数组的信息。
	 * 首先还是同样的算出每个点左边所有数的乘积，存入数组中。然而在反向算右边所有数的乘积时，
	 * 我们不再把它单独存入一个数组，而是直接乘到之前的数组中，这样乘完后结果就已经出来了。
	 * 另外，因为我们不再单独开辟一个数组来存储右边所有数，不能直接根据数组上一个来得知右边所有数乘积，
	 * 所以我们需要额外一个变量来记录右边所有数的乘积。这里为了清晰对称，遍历左边的时候也加入了一个额外变量来记录。
	 * 注意
	 * 因为第一位在第一轮从左向右乘的时候乘不到，结果数组中会得到0，
	 * 所以要先将第一位置为1，即res[0] = 1，其他的不用初始化
	 * 因为涉及左右两边的数，所有数组长度为1的时候就直接返回自身就行了
	 * @param nums
	 * @return
	 */
	public int[] productExceptSelf_1(int[] nums) {
		if(nums == null) return null;
		int[] res = new int[nums.length];
		res[res.length-1] = 1;
		for(int i=nums.length-2;i>=0;i--){
			res[i]=res[i+1]*nums[i+1];
		}
		int left=1;
		for(int i=0; i<nums.length;i++){
			res[i] *=left;
			left *= nums[i];
		}
		return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductofArrayExceptSelf slt = new ProductofArrayExceptSelf();
		int[] nums ={1,2,3,4};
		int[] res = slt.productExceptSelf(nums);
		for(int i=0; i<res.length;i++){
		System.out.print(res[i]+",");
		}
	}

}
