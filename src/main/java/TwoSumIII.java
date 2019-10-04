import java.util.HashMap;

/*
 * 170	Two Sum III - Data structure design 	24.7%	Easy
 * Two Sum III - Data structure design Total Accepted: 311 Total Submissions: 1345
Design and implement a TwoSum class. It should support the following operations:add and find.
add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.
For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false

[·ÖÎö]
HASH table O(N)´æ, O(1) È¡ 
 */
public class TwoSumIII {

	HashMap<Integer,Integer> hashmap = new HashMap<Integer,Integer>();  
    public void add(int number) {  
       if(hashmap.containsKey(number)){  
           hashmap.put(number,hashmap.get(number)+1);  
       }else{  
           hashmap.put(number,1);  
       }  
    }  
  
    public boolean find(int value) {  
         for(Integer key: hashmap.keySet()){  
             if(hashmap.containsKey(value-key)){  
                 if(value == 2*key){  
                     return hashmap.get(key)>=2;  
                 }else{  
                     return true;  
                 }  
             }  
         }  
         return false;  
    }  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoSumIII slt = new TwoSumIII();
		slt.add(1);
		slt.add(3);
		slt.add(5);
		
		System.out.println("find(4) -> "+ slt.find(4));
		System.out.println("find(7) -> "+slt.find(7));
	}

}
