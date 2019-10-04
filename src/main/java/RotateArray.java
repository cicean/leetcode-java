import java.util.HashMap;

/**
 * 189	Rotate Array	17.9%	Easy
 *
 * 0.Problem:
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] 
 * is rotated to [5,6,7,1,2,3,4].
 * Note:
 * Try to come up as many solutions as you can, there are at least 
 * 3 different ways to solve this problem.
 * 
 * 1.Refer.:
 * reverse by three times
 * solution 1.1.hashmap
 *          1.2.arry copy
 */

public class RotateArray {

	public void rotate_1(int[] nums, int k) {  
        HashMap<Integer, Integer> map  = new HashMap<Integer, Integer>();  
      
        int len = nums.length;  
        if( len > k) {  
            for(int i= len-k; i< len; i++) {  
                map.put(i - (len-k), nums[i]);  
            }  
            for (int i =0; i< len -k ; i++) {  
                map.put( i + k , nums[i]);  
            }  
            for(int j =0; j< map.size();j++)  
                nums[j]= map.get(j);  
        }  
    }  
	
	public void rotate_2(int[] nums, int k) {  
        int len = nums.length;  
        int[] temp = new int[len];  
        if (len > k) {  
            // Refer :  http://stackoverflow.com/questions/26610309/java-rotating-array  
            System.arraycopy(nums, 0, temp, k, len-k);  
            System.arraycopy(nums, len-k, temp, 0, k);  
              
            System.arraycopy(temp, 0, nums, 0, len); // nums[i] = temp[i], i= 0... len  
  
        }  
          
        if( len <k) {  // this part is wroten by myself -hua  
            k = k%len;  
            System.out.println("k=" + k);  
            rotate_2(nums, k);  
        }  
              
          
    }  
	
	public static void reverse(int[] nums, int start, int end) {
	    while (start < end) {
	      int temp = nums[start];
	      nums[start] = nums[end];
	      nums[end] = temp;
	      start = start + 1;
	      end = end - 1;
	    }
	  }
	  
	  public static void rotate(int[] nums, int k) {
	    k = k % nums.length;
	    reverse(nums, 0, nums.length - 1);
	    reverse(nums, 0, k - 1);
	    reverse(nums, k, nums.length - 1);
	  }
	  
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RotateArray slt = new RotateArray();
		int[] nums ={1,2,3,4,5,6,7};
		int k= 3;
		slt.rotate_1(nums, k);
		for(int i=0;i<nums.length;i++){
			System.out.print(nums[i]+",");
		}
		
	}

}
