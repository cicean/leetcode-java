

/*
 Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

Hide Tags Array Two Pointers

 */

public class RemoveDuplicatesfromSortedArrayII {
	
	// Recursive 
	public int removeDuplicates(int[] nums) {
        return removeDuplicateshelp(nums, 2);
    }

	
    public int removeDuplicateshelp(int[] nums, int k) {

        int count = 0;
        int idx = 0;

        // j points the position where the next qualified number 
        // found by i pointer will be written
        for ( int i = 0; i < nums.length; i++ ) {

        	if (i == 0 || nums[i] != nums[i - 1]) {
        		count = 1;
        		nums[idx++] = nums[i];
        		
        	} else {
        		
        		if (count < k) {
        			nums[idx++] = nums[i];
        		}
        		count++;
        	}

        }
        return idx;
    }
    
    public int removeDuplicates_1(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }
    
    /**
     * ���Ӷ�
ʱ�� O(N) �ռ� O(1)

˼·
���ǿ��Խ����ظ������д浽����ǰ�棬��Ϊ���ظ����еĳ���һ��С�ڵ��������У����Բ��õ��ĸ��ǵ����⡣
������˵��������ָ�룬��ָ��ָ��ǰ�����������λ�ã���ָ��ָ���ظ�������һ����ŵ�λ�ã�
��������һ��������һ�����ظ���Ԫ�أ��Ͱ������Ƶ����ظ����е�ĩβ���ж��ظ��ķ����Ǽ�¼��һ�������������֣����Ƿ�һ����
Follow Up ����
Q���������û������Ļ���ν⣿
A�������Ƚ�����������ͬ��������⣬Ȼ��ʱ�临�ӶȽ��ﵽO(NlogN)��
������Ǹ��ù�ϣ��򼯺�����¼���ֳ��ִ�����������O(N)�ռ�õ�O(N)��ʱ�䡣
     * @param args
     */
    
    public int removeDuplicates_3(int[] nums) {
    	 if(nums.length <= 2) return nums.length;
         int dup1 = nums[0];
         int dup2 = nums[1];
         int end = 2;
         for(int i = 2; i < nums.length; i++){
             if(nums[i]!=dup1){
                 nums[end] = nums[i];
                 dup1 = dup2;
                 dup2 = nums[i];
                 end++;
             }
         }
         return end;
     }
    }
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveDuplicatesfromSortedArrayII slt=  new RemoveDuplicatesfromSortedArrayII();
		int [] A = {1,1,1,2,2,3};
		//Scanner sc =  new Scanner(System.in);
    	//System.out.println("Please input an integer");
    	//String s= sc.nextLine();
    	int res = slt.removeDuplicates(A);
    	System.out.println(res);
    	//int N =A.length;
    	for (int i=0; i<res; i++){System.out.print(A[i]+",");}
    	
	}

}
