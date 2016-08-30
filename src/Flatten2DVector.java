import java.util.ArrayList;
import java.util.List;

/**
 * 251	Flatten 2D Vector 	25.8%	Medium
 * @author cicean
 * LeetCode: Flatten 2D Vector
AUG 8 2015

Implement an iterator to flatten a 2d vector.

For example,

Given 2d vector =

1
[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, 
the order of elements returned by next should be:
 [1,2,3,4,5,6].
 * The idea is very simple. We keep two variables row and col for the range of rows and cols. Specifically, row is the number of rows of vec2d and col is the number of columns of the current 1d vector in vec2d. We also keep two variables r and c to point to the current element.

In the constructor, we initialize row and col as above and initialize both r and c to be 0(pointing to the first element).

In hasNext(), we just need to check whether r and c are still in the range limited by row andcol.

In next(), we first record the current element, which is returned later. Then we update the running indexes and possibly the range if the current element is the last element of the current 1d vector.

A final and important note, since in next(), we record the current element, we need to guarantee that there is an element. So we implement a helper function skipEmptyVector() to skip the empty vectors. It is also important to handle the case that vec2d is empty (in this case, we set col = -1).

The time complexity of hasNext() is obviously O(1) and the time complexity of next is alsoO(1) in an amortized sense.

The code is as follows.
 */
public class Flatten2DVector {
	
	private int[] arrCounters;
	private int counter = 0;
	

	public  void Vector2D(List<List<Integer>> vec2d) {
		int count = 0;
		if (vec2d == null) {
			arrCounters = new int[0];
		} else {
			for (List<Integer> l : vec2d) {
				count += (l == null) ? 0 : l.size();
			}
			arrCounters = new int[count];
			
			count = 0;
			for (List<Integer> l : vec2d) {
				for (int in : l) {
					arrCounters[count++] = in;
				}
				
			}
		}
	}
		
		public int next() {
			int val = Integer.MIN_VALUE;
			if (counter < arrCounters.length) {
				val = arrCounters[counter];
			}
			
			counter++;
			return val;
		}
		
		public boolean hasNext() {
			return counter < arrCounters.length;
		}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Flatten2DVector slt = new Flatten2DVector();
		List<List<Integer>> vec2d = new ArrayList<>();
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		List<Integer> l3 = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			if (i < 3) {
				l1.add(i);
			} else if (i == 3 ) {
				l2.add(i);
			} else {
				l3.add(i);
			}
			
		}
		
		vec2d.add(l1);
		vec2d.add(l2);
		vec2d.add(l3);
		
		slt.Vector2D(vec2d);
		slt.next();
		slt.hasNext();
		System.out.println(vec2d);
		for (int i = 0; i < slt.arrCounters.length; i++){
		System.out.print(slt.arrCounters[i] + ",");
		}
	}

}
