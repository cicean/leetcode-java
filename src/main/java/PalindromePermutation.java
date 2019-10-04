import java.util.BitSet;

/**
 * 266	Palindrome Permutation 	44.3%	Easy
 * Problem Description:

Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. 
How about character which occurs odd number of times?

https://leetcode.com/discuss/53180/1-4-lines-python-ruby-c-c-java 
 * @author cicean
 *
 */
public class PalindromePermutation {
	
	// Method 2: https://leetcode.com/discuss/53180/1-4-lines-python-ruby-c-c-java  
    public boolean canPermutePalindrome(String s) {  
        BitSet bs = new BitSet();  
        for (byte b : s.getBytes())  
            bs.flip(b);  
        return bs.cardinality() < 2;  
    }  
    // Method 1  
    public boolean canPermutePalindrome1(String s) {  
        int[] map = new int[256];  
        for (int i = 0; i < s.length(); i++) {  
            map[s.charAt(i)]++;  
        }  
        int oddOccurCounter = 0;  
        for (int i = 0; i < 256; i++) {  
            if (((map[i]) & 1) == 1)  
                oddOccurCounter++;  
        }  
        if ((s.length() & 1) == 1)  
            return oddOccurCounter == 1;  
        else  
            return oddOccurCounter == 0;  
    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
