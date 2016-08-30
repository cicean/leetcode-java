import java.util.ArrayList;
import java.util.List;

/**
 * Problem Description:

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:

vector<string> decode(string s) {
  //... your code
  return strs;
} 
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
Well, let's use an example strs = ["#$%", "", "12"] to illustrate the encoding and decoding algorithms. The idea is to use some character as sentinel. But since the input strs may contain any character, including the sentinel, we still need to use other information to avoid ambiguation. Specifically, we use the length of the string. The above string will be encoded as

3##$%0#2#12

Each color represents the encoding of each string and we use # as the sentinel.

During decoding, we will initialize a starting point p to be 0. Then we find the first # starting from p, which is just the sentinel for the first string and characters between p and the first # encode the length of the following string, using which we would be able to extract that string. In the above example, the length of the first string is 3 and we extract 3 characters after the first sentinel # and get #$%, which is just the first string. Then we move p to the point after the first string and continue the above process. Finally, all strings will be extracted out.

The code is as follows. If you find it not that clear, run it on the above example and you will get how it works.
 * @author cicean
 *
 */
public class EncodeandDecodeStrings {

	 // Encodes a list of strings to a single string. 
    public String encode(List<String> strs) { 
        if(strs == null || strs.size() == 0) return ""; 
        StringBuilder sb = new StringBuilder(); 
        for(String str : strs){ 
            sb.append(str.length()).append("#").append(str).append("#"); 
        } 
          
        return sb.toString(); 
    } 
  
    // Decodes a single string to a list of strings. 
    public List<String> decode(String s) { 
        List<String> list = new ArrayList<String>(); 
        if(s == null || s.length() ==0) return list; 
          
        int i = 0; 
        int start = 0; 
        while(start < s.length() && (i = s.indexOf("#",start))!= -1){ 
            int len = Integer.parseInt(s.substring(start, i)); 
            i++; 
            String str = s.substring(i, i+len); 
            list.add(str); 
            start = i+len+1; 
        } 
        return list; 
    } 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
