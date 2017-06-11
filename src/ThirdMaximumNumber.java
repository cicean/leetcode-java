import java.util.*;

/**
 * Created by cicean on 10/23/2016.
 * 414. Third Maximum Number   QuestionEditorial Solution  My Submissions
 Total Accepted: 3487
 Total Submissions: 13345
 Difficulty: Easy
 Contributors: ZengRed , 1337c0d3r
 Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

 Example 1:
 Input: [3, 2, 1]

 Output: 1

 Explanation: The third maximum is 1.
 Example 2:
 Input: [1, 2]

 Output: 2

 Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 Example 3:
 Input: [2, 2, 3, 1]

 Output: 1

 Explanation: Note that the third maximum here means the third maximum distinct number.
 Both numbers with value 2 are both considered as second maximum.
 Hide Company Tags Amazon
 Hide Tags Array
 Hide Similar Problems (M) Kth Largest Element in an Array

 */
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        int max, mid, small, count;
        max = mid = small = Integer.MIN_VALUE;
        count = 0;  //Count how many top elements have been found.

        for( int x: nums) {
            //Skip loop if max or mid elements are duplicate. The purpose is for avoiding right shift.
            if( x == max || x == mid ) {
                continue;
            }

            if (x > max) {
                //right shift
                small = mid;
                mid = max;

                max = x;
                count++;
            } else if( x > mid) {
                //right shift
                small = mid;

                mid = x;
                count++;
            } else if ( x >= small) { //if small duplicated, that's find, there's no shift and need to increase count.
                small = x;
                count++;
            }
        }

        //"count" is used for checking whether found top 3 maximum elements.
        if( count >= 3) {
            return small;
        } else {
            return max;
        }
    }


    public int thirdMax_pq(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.contains(i)) {
                pq.offer(i);
                set.add(i);
                if (pq.size() > 3) {
                    set.remove(pq.poll());
                }
            }
        }
        if (pq.size() < 3) {
            while (pq.size() > 1) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}
