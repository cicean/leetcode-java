/*
 * 
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

ʱ��O(n)�Ϳռ�O(1)�ⷨ

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)

Hide Tags Array
Hide Similar Problems (H) Trapping Rain Water (M) Maximum Product Subarray

 * 
 * �����ó����� ��ά����ǰԪ���������Ԫ�صĳ˻��Լ��ұ�����Ԫ�صĳ˻��� 
 * ��˵õ� product of array except self !
 * 
 * Because we cannot use division, so assume we have two integer arrays with the same length of nums, int[] leftProd = new int[nums.length];
 *  int[] rightProd = new int[nums.length], we store the product of all the left elements in leftProd and the product of all the right elements in rightProd, 
 *  then the product of leftProd[i] and rightProd[i] will be the value we want to put into the result. take the example of num[] = {2, 4, 3, 6}, 
 *  thenleftProd will be {1, 2, 8, 24} , and rightProd will be {72, 18, 6, 1}.
 *  
 *  ά����������, left[] and right[]. �ֱ��¼ ��i��Ԫ�� �����ӵĺ�left[i] and  �ұ���ӵĺ�right[i].  
 *  ��ô���res[i]��Ϊ  left[i]+right[i]. follow up Ҫ��O(1)�ռ�. ���÷��صĽ������,�ȴ�right����. 
 *  �ٴ���߼���left,ͬʱ������ֵ, �������Բ���Ҫ����Ŀռ�.
 */
public class ProductofArrayExceptSelf {
	
	/**
	 * ��һ��ȫ��Ԫ�صĻ��ٷֱ����ÿһ��Ҫ��ϸ����Ԫ��Ϊ��������
	 * û����ֱ�ӳ���ȥ��
	 * һ���� ���λ�ö�ӦֵΪ����Ԫ��֮��������λ��Ϊ�㡣
	 * �������ϵ���ȫ�������㡣
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
	 * ʵ���ϣ����ǿ����ý�������������洢left��right�������Ϣ��
	 * ���Ȼ���ͬ�������ÿ��������������ĳ˻������������С�Ȼ���ڷ������ұ��������ĳ˻�ʱ��
	 * ���ǲ��ٰ�����������һ�����飬����ֱ�ӳ˵�֮ǰ�������У���������������Ѿ������ˡ�
	 * ���⣬��Ϊ���ǲ��ٵ�������һ���������洢�ұ�������������ֱ�Ӹ���������һ������֪�ұ��������˻���
	 * ����������Ҫ����һ����������¼�ұ��������ĳ˻�������Ϊ�������Գƣ�������ߵ�ʱ��Ҳ������һ�������������¼��
	 * ע��
	 * ��Ϊ��һλ�ڵ�һ�ִ������ҳ˵�ʱ��˲�������������л�õ�0��
	 * ����Ҫ�Ƚ���һλ��Ϊ1����res[0] = 1�������Ĳ��ó�ʼ��
	 * ��Ϊ�漰�������ߵ������������鳤��Ϊ1��ʱ���ֱ�ӷ������������
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
