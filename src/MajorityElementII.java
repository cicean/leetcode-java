import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * 229	Majority Element II	22.2%	Medium
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
 * The algorithm should run in linear time and in O(1) space.
 * 
 * 与Majority Elements不同是这里需要维护两个变量n1和n2，
 * 如果下一个数与这两个数都不同的时候，count1和count2都减一，
 * 如果下一个数与n1或者n2相等的时候，对应的count++。最后的结果必定在n1或者n2中。
 * 
 */
public class MajorityElementII {

	public  List<Integer> majorityElement_1(int[] nums) {
	    List<Integer> res = new ArrayList<Integer>();
	    int first_val = 0, second_val = 0;
	    int first_count = 0, second_count = 0;
	    for (int i = 0; i < nums.length; i++) {
	        if (first_count == 0) {
	            first_val = nums[i];
	            first_count = 1;
	        } else if (nums[i] == first_val) {
	            first_count++;
	        } else if (second_count == 0) {
	            second_val = nums[i];
	            second_count = 1;
	        } else if (nums[i] == second_val) {
	            second_count++;
	        } else {
	            first_count--;
	            second_count--;
	        }
	    }
	    checkMajorityHelper(res, nums, first_count, first_val, second_count,
	            second_val);
	    return res;
	}

	private static void checkMajorityHelper(List<Integer> res, int[] nums,
	        int first_count, int first_val, int second_count, int second_val) {
	    int count1 = 0, count2 = 0, len = nums.length;
	    for (int i = 0; i < len; i++) {
	        if (first_count > 0 && nums[i] == first_val) {
	            count1++;
	        } else if (second_count > 0 && nums[i] == second_val) {
	            count2++;
	        }
	    }
	    if (count1 > len / 3) {
	        res.add(first_val);
	    }
	    if (count2 > len / 3) {
	        res.add(second_val);
	    }
	}
	
	
	public List<Integer> majorityElement_2(int[] nums) {
        if(nums.length == 0) return Collections.emptyList();

        int n1 = nums[0];
        int n2 = nums[0];

        int c1 = 0;
        int c2 = 0;

        for(int i = 0; i < nums.length; i++){

            // put to an empty slot
            if(c1 <= 0 && nums[i] != n2){
                n1 = nums[i];
                c1 = 1;
                continue;
            }

            if(c2 <= 0 && nums[i] != n1){
                n2 = nums[i];
                c2 = 1;
                continue;
            }

            // add count
            if(nums[i] == n1){
                c1++;
                continue;
            }

            if(nums[i] == n2){
                c2++;
                continue;
            }

            // no match

            c1--;
            c2--;
        }

        // faint
        return new ArrayList<>(IntStream.of(n1, n2)
                .filter(n ->
                        Arrays.stream(nums).filter(i -> n == i).count() > nums.length / 3)
                .boxed()
                .collect(Collectors.toSet()));
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,2,2,4,5,6};
		MajorityElementII slt=new MajorityElementII();
		
		System.out.println(slt.majorityElement_2(nums));
		
	}

}
