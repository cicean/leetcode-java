package facebook;

public class Findlongestarithmeticsubsequenceinanunsortedarray {
	
	// The idea is to maintain a 2d array. Called length[input.length][input.length]
	// length[i][j] means the length of the arithmetic that ends with input[i] and input[j]
	// And a hashMap to record the index of every number
	// We traverse the input from index 1 to the end.
	// Everytime we meet a number, fix this as the end of sequence. Waral 鍗氬鏈夋洿澶氭枃绔�,
	// Then traverse back and try to make every number as second last number. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
	// When we fix one as second last one number, we calculate the gap as input[last] - input[secondLast]
	// look into hashmap to find is there a number in the input equals to input[secondLast]. 
	// And check its index whether it is smaller than secondLast.. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
	// If it is. Then this is the third last number. And we should already have length[thridLast][secondLast]
	// Then length[secondLast][last] = length[thridLast][secondLast] + 1
	// If it is not. We make length[secondLast][last] = 2 -- those two number are the only numbers in the arithmetic
	//'Time complexity: O(n^2), space complexity: O(n^2) -- for only return length'
	public int findLongest(int[] input) {
	        if (input.length <= 2) {
	            return 2;
	        }
	        int maxLen = 2;
	        int[][] length = new int[input.length][input.length];
	        HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
	        for (int i = 0; i < input.length; i++) {
	            if (!valueToIndex.containsKey(input[i])) {
	                valueToIndex.put(input[i], new ArrayList<Integer>());
	            }
	            valueToIndex.get(input[i]).add(i);
	        }
	        for (int index = 1; index < input.length; index++) {
	            for (int secondLast = index - 1; secondLast >= 0; secondLast--) {
	                int gap = input[index] - input[secondLast];
	                int next = input[secondLast] - gap;
	                if (valueToIndex.containsKey(next)) {
	                    int nextIndex = -1;
	                    for (int j = valueToIndex.get(next).size() - 1; j >= 0; j--) {
	                        if (valueToIndex.get(next).get(j) < secondLast) {
	                            nextIndex = valueToIndex.get(next).get(j);
	                            break;
	                        }
	                    }
	                    if (nextIndex != -1) {
	                        length[secondLast][index] = length[nextIndex][secondLast] + 1;
	                        maxLen = Math.max(maxLen, length[secondLast][index]);
	                    }
	                }
	                if (length[secondLast][index] == 0) {
	                    length[secondLast][index] = 2;
	                }
	            }
	        }
	        return maxLen;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
