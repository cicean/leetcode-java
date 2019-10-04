import java.util.SortedSet;
import java.util.TreeSet;

/*
 * 220	Contains Duplicate III	15.4%	Medium
 * Given an array of integers, find out whether there are two distinct indices i and j 
 * in the array such that the difference between nums[i] and nums[j] is 
 * at most t and the difference between i and j is at most k.
 * [˼·]
 * ά��һ������Ϊk��window, ÿ�μ���µ�ֵ�Ƿ���ԭ�������е�����ֵ�Ĳ�ֵ��С�ڵ���t��. 
 * ���������forѭ���ᳬʱO(nk). ʹ��treeset( backed by binary search tree) ��subSet����,���Կ�������.
 *  ���Ӷ�Ϊ O(n logk)
 *  
 *  ������Contains Duplicate��Contains Duplicate II������ͬ��������Ҫ�����������Ԫ��֮��Ĺ�ϵ��
 *  �����뵽��˼·���ǣ�ά��һ������Ϊk��window, ÿ�μ���µ�ֵ�Ƿ���ԭ�������е�����ֵ�Ĳ�ֵ��С�ڵ���t.
 *  �������ַ�����ʱ�临�Ӷ�ΪO(nk)������ɳ�ʱ�������Ҫ�����Ż��� 
 *  �ο���ƪ���½ⷨ,������ʹ��treeset���洢Ԫ�أ��Դﵽ���ٲ���Ԫ��ֻ���Ƿ�С��t�����������ʱ�临�Ӷ�ΪO(nlogk).
 *  
 *  1.1 ������ʹ��treeset���洢Ԫ�أ��Դﵽ���ٲ���Ԫ��ֻ���Ƿ�С��t�����������ʱ�临�Ӷ�ΪO(nlogk).
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
