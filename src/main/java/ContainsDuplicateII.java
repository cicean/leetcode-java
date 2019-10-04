import java.util.HashMap;

/*
 * 219	Contains Duplicate II	25.6%	Easy
 * Given an array of integers and an integer k, find out whether there 
 * there are two distinct indices i and j in the array such that 
 * nums[i] = nums[j] and the difference between i and j is at most k.
 * 
 * 
 */
public class ContainsDuplicateII {

	public boolean containsNearbyDuplicate_1(int[] nums, int k) {
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    int min = Integer.MAX_VALUE;
	 
	    for(int i=0; i<nums.length; i++){
	        if(map.containsKey(nums[i])){
	            int preIndex = map.get(nums[i]);
	            int gap = i-preIndex;
	            min = Math.min(min, gap);
	        }
	        map.put(nums[i], i);
	    }
	 
	    if(min <= k){
	        return true;
	    }else{
	        return false;
	    }
	}
	
	public boolean containsNearbyDuplicate_2(int[] nums, int k) {
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	 
	    for(int i=0; i<nums.length; i++){
	        if(map.containsKey(nums[i])){
	            int pre = map.get(nums[i]);
	            if(i-pre<=k)
	                return true;
	        }
	 
	        map.put(nums[i], i);
	    }
	 
	    return false;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      
		 int[] nums = {1,2,3,4,5};
		 int[] nums2 = {1,2,4,2,6};
		 int k = 2;
		
		 ContainsDuplicateII slt= new ContainsDuplicateII();
		 System.out.println(slt.containsNearbyDuplicate_1(nums,k));
		 System.out.println(slt.containsNearbyDuplicate_1(nums2,k));
		   
		 
	}

}
