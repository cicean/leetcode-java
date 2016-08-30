

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
     * 复杂度
时间 O(N) 空间 O(1)

思路
我们可以将不重复的序列存到数列前面，因为不重复序列的长度一定小于等于总序列，所以不用担心覆盖的问题。
具体来说，用两个指针，快指针指向当前数组遍历到的位置，慢指针指向不重复序列下一个存放的位置，
这样我们一旦遍历到一个不重复的元素，就把它复制到不重复序列的末尾。判断重复的方法是记录上一个遍历到的数字，看是否一样。
Follow Up 后续
Q：如果数组没有排序的话如何解？
A：可以先将其排序再用同样方法求解，然而时间复杂度将达到O(NlogN)，
如果我们改用哈希表或集合来记录数字出现次数，可以用O(N)空间得到O(N)的时间。
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
