import java.util.SortedSet;
import java.util.TreeSet;

/*
 * 220	Contains Duplicate III	15.4%	Medium
 * Given an array of integers, find out whether there are two distinct indices i and j 
 * in the array such that the difference between nums[i] and nums[j] is 
 * at most t and the difference between i and j is at most k.
 * [思路]
 * 维持一个长度为k的window, 每次检查新的值是否与原来窗口中的所有值的差值有小于等于t的. 
 * 如果用两个for循环会超时O(nk). 使用treeset( backed by binary search tree) 的subSet函数,可以快速搜索.
 *  复杂度为 O(n logk)
 *  
 *  该题与Contains Duplicate和Contains Duplicate II都不相同，这题主要考察的是两个元素之间的关系。
 *  最先想到的思路就是，维持一个长度为k的window, 每次检查新的值是否与原来窗口中的所有值的差值有小于等于t.
 *  但是这种方法的时间复杂度为O(nk)，会造成超时。因此需要进行优化。 
 *  参考这篇文章解法,发现其使用treeset来存储元素，以达到快速查找元素只差是否小于t的情况，这样时间复杂度为O(nlogk).
 *  
 *  1.1 发现其使用treeset来存储元素，以达到快速查找元素只差是否小于t的情况，这样时间复杂度为O(nlogk).
 *  2.1 Another solution that is easier to understand.
 * 
 */
public class ContainsDuplicateIII {
	
	public boolean containsNearbyAlmostDuplicate_1(int[] nums, int k, int t) {
		if (k < 1 || t < 0)
			return false;
	 
		TreeSet<Integer> set = new TreeSet<Integer>();
	 
		for (int i = 0; i < nums.length; i++) {
			int c = nums[i];
			if ((set.floor(c) != null && c <= set.floor(c) + t)
			|| (set.ceiling(c) != null && c >= set.ceiling(c) -t))
				return true;
	 
			set.add(c);
	 
			if (i >= k)
				set.remove(nums[i - k]);
		}
	 
		return false;
	}

	public boolean containsNearbyAlmostDuplicate_2(int[] nums, int k, int t) {  
        //input check  
        if(k<1 || t<0 || nums==null || nums.length<2) return false;  
          
        SortedSet<Long> set = new TreeSet<Long>();  
          
        for(int j=0; j<nums.length; j++) {  
            SortedSet<Long> subSet =  set.subSet((long)nums[j]-t, (long)nums[j]+t+1);  
            if(!subSet.isEmpty()) return true;  
              
            if(j>=k) {  
                set.remove((long)nums[j-k]);  
            }  
            set.add((long)nums[j]);  
        }  
        return false;  
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int[] nums = {1,3,4,5};
		 int[] nums2 = {1,2,4,2,6};
		 int k = 2;
		 int t = 2 ;
				 
		 ContainsDuplicateIII slt= new ContainsDuplicateIII();
		 System.out.println(slt.containsNearbyAlmostDuplicate_1(nums,k,t));
		 System.out.println(slt.containsNearbyAlmostDuplicate_2(nums2,k,t));
	}

}
