import java.util.HashSet;

/*
 * 217	Contains Duplicate	35.8%	Easy
 * Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, 
 * and it should return false if every element is distinct.
 * 
 * 
 */
public class ContainsDuplicate {

	public boolean containsDuplicate(int[] nums) {
	    if(nums==null || nums.length==0)
	        return false;
	 
	    HashSet<Integer> set = new HashSet<Integer>();
	    for(int i: nums){
	        if(!set.add(i)){
	            return true;
	        }
	    }
	 
	    return false;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
   int[] nums = {1,2,3,4,5};
   int[] nums2 = {1,2,2,4,6};
   
   ContainsDuplicate slt= new ContainsDuplicate();
   
   System.out.println(slt.containsDuplicate(nums));
   System.out.println(slt.containsDuplicate(nums2));
   
	}

}
