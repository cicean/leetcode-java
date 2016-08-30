
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author cicean
 *
 */
public class UniqueWordAbbreviation {

	public class ValidWordAbbr {
	    Map<String, Set<String> > map = new HashMap<>();

	    public ValidWordAbbr(String[] dictionary) {
	        for(int i=0; i<dictionary.length; i++) {
	            String s = dictionary[i];
	            if(s.length() > 2 ) {
	                s = s.charAt(0) + Integer.toString(s.length()-2) + s.charAt(s.length()-1);
	            }
	            if(map.containsKey(s) ) {
	                map.get(s).add(dictionary[i]);
	            } else {
	                Set<String> set = new HashSet<String>();
	                set.add(dictionary[i]);
	                map.put(s, set);
	            }
	        }
	    }

	    public boolean isUnique(String word) {
	        //input check
	        String s = word;
	        if(s.length() > 2 ) {
	            s = s.charAt(0) + Integer.toString(s.length()-2) + s.charAt(s.length()-1);
	        }
	        if(!map.containsKey(s)) return true;
	        else return map.get(s).contains(word) && map.get(s).size()<=1;
	        
	    }
	}


	// Your ValidWordAbbr object will be instantiated and called as such:
	// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
	// vwa.isUnique("Word");
	// vwa.isUnique("anotherWord");
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
