package Bloomberg;

import datastructure.PrintList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cicean on 9/20/2016.
 *
 * n=11
 Output£º1, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9
 *
 */
public class OutputByDictionaryOrderInteger {

	 private List<Integer>  lexicalOrder2(int n) {
	        
	        List<Integer> res = new ArrayList<>();

	        Comparator<Integer> comp = new Comparator<Integer>() {
	            @Override
	            public int compare(Integer o1, Integer o2) {
	                String a = "" + o1;
	                String b = "" + o2;
	                return a.compareTo(b);
	            }
	        };

	        Integer[] in = new Integer[n];
	        for (int i = 0; i < n; i++) {
	            in[i] = Integer.valueOf(i + 1);
	        }

	        Arrays.sort(in,comp);

	        //System.out.print(Arrays.deepToString(in));
	        res.addAll(Arrays.asList(in));

	        return res;
	    }
    
    

    public static void main(String[] args) {
		OutputByDictionaryOrderInteger slt = new OutputByDictionaryOrderInteger();
		PrintList<Integer> res = new PrintList<>();
		res.printList(slt.lexicalOrder2(11));
	}





}
