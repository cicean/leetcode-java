import java.util.*;

/*
 169	Majority Element	35.0%	Easy
 Problem:    Majority Element
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/majority-element/
 Notes:
 Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 You may assume that the array is non-empty and the majority element always exist in the array.
 Solution: 1. Runtime: O(n) — Moore voting algorithm: We maintain a current candidate and a counter initialized to 0. As we iterate the array, we look at the current element x:
 If the counter is 0, we set the current candidate to x and the counter to 1.
 If the counter is not 0, we increment or decrement the counter based on whether x is the current candidate.
 After one pass, the current candidate is the majority element. Runtime complexity = O(n).
 2. Runtime: O(n) — Bit manipulation: We would need 32 iterations, each calculating the number of 1's for the ith bit of all n numbers. Since a majority must exist, therefore, either count of 1's > count of 0's or vice versa (but can never be equal). The majority number’s ith bit must be the one bit that has the greater count.
 */

public class MajorityElement {
	
	public int majorityElement_1(int[] num) {
        int n = num.length;
        if (n == 0) return 0;
        if (n == 1) return num[0];
        int res = num[0], cnt = 1;
        for (int i = 1; i < n; ++i) {
            if (cnt == 0) {
                res = num[i];
                ++cnt;
                continue;
            }
            if (res == num[i]) ++cnt;
            else --cnt;
        }
        return res;
    }
	
	
    public int majorityElement_2(int[] num) {
        int n = num.length;
        if (n == 0) return 0;
        if (n == 1) return num[0];
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            int one = 0, zero = 0;
            for (int j = 0; j < n; ++j) {
                if (((num[j]>>i) & 1) == 1) ++one;
                else ++zero;
            }
            if (one > zero) res = res | (1<<i);
        }
        return res;
    }
    
    
    public int majorityElement_3(int[] num) {  
        HashMap<Integer,Integer> map=new HashMap<>();  
        for(int a:num){  
            if(map.get(a)!=null){  
                int i=map.get(a);  
                 map.put(a,i+1);  
            }  
            else{  
                map.put(a,1);  
            }  
        }  
        for(Map.Entry<Integer,Integer> m:map.entrySet()){  
            if(m.getValue()>num.length/2){  
                return m.getKey();  
            }  
        }  
        return 0;  
    }  
    
    public int majorityElement_4(int[] num) {  
        Arrays.sort(num);  
        return num[num.length/2];  
    }  
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] tests = { 
                { 1, 1, 1, 2, 2 }, 
                { 1 }, 
                { 1, 2, 1 },
                { 1, 1, 2, 2, 2 }, 
                { 1, 1 }, 
                { 1, 2, 3, 2, 1 } 
        };
        int[] res = {1,1,1,2,1,1};
        
        MajorityElement slt = new MajorityElement();
        
        for (int i = 0; i < tests.length; i++) {
            System.out.print(slt.majorityElement_2(tests[i]));
            System.out.println(" " + res[i]);
        }
    }
	

}
