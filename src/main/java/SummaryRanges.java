import java.util.ArrayList;
import java.util.List;

/*
 * 
 * 	228	Summary Ranges	19.7%	Easy
 * 
 * Given a sorted integer array without duplicates, return the summary of its ranges.

	For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

	Credits:
	Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 * 
 * 2.1两个指针 start, end.  如果nums[end+1] = nums[end]+1, 就移动end指针, 
 * 否则, 插入字符串nums[start]->nums[end]. 
 */
public class SummaryRanges {

	public List<String> summaryRanges_1(int[] nums) {
	    List<String> result = new ArrayList<String>();
	 
	    if(nums == null || nums.length==0)
	        return result;
	 
	    if(nums.length==1){
	        result.add(nums[0]+"");
	    }
	 
	    int pre = nums[0]; // previous element   
	    int first = pre; // first element of each range
	 
	    for(int i=1; i<nums.length; i++){
	            if(nums[i]==pre+1){
	                if(i==nums.length-1){
	                    result.add(first+"->"+nums[i]);
	                }
	            }else{
	                if(first == pre){
	                    result.add(first+"");
	                }else{
	                    result.add(first + "->"+pre);   
	                }
	 
	                if(i==nums.length-1){
	                    result.add(nums[i]+"");
	                }
	 
	                first = nums[i];
	            }
	 
	            pre = nums[i];
	    }
	 
	    return result;
	}
	
	 public List<String> summaryRanges_2(int[] nums) {  
	        List<String> res = new ArrayList<>();  
	        if(nums==null || nums.length<1) return  res;  
	          
	        int s=0, e=0;  
	        while(e<nums.length) {  
	            if(e+1<nums.length && nums[e+1]==nums[e]+1) {  
	                e++;  
	            } else {  
	                if(s==e) {  
	                    res.add(Integer.toString(nums[s]));  
	                } else {  
	                    String str = nums[s] + "->" + nums[e];  
	                    res.add(str);  
	                }  
	                ++e;  
	                s = e;  
	            }  
	        }  
	        return res;  
	    } 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      int[] nums = {0,1,2,4,5,7};
      
      SummaryRanges slt = new SummaryRanges();
      
      System.out.println(slt.summaryRanges_1(nums));
      
	}

}
