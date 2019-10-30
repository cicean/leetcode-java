import java.util.*;

/**
 * 1167. Minimum Cost to Connect Sticks
 * Medium
 *
 * 53
 *
 * 14
 *
 * Favorite
 *
 * Share
 * You have some sticks with positive integer lengths.
 *
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.
 *
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: sticks = [2,4,3]
 * Output: 14
 * Example 2:
 *
 * Input: sticks = [1,8,3,5]
 * Output: 30
 *
 *
 * Constraints:
 *
 * 1 <= sticks.length <= 10^4
 * 1 <= sticks[i] <= 10^4
 * Accepted
 * 3,870
 * Submissions
 * 6,395
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 4
 * Minimum Cost to Merge Stones
 * Hard
 * How many times does every stick contribute to the answer?
 */

public class MinimumCosttoConnectSticks {

    public int connectSticks(int[] sticks) {
        Arrays.sort(sticks);

        int[] res = new int[sticks.length];
        int[] ref = sticks;
        int refSize = ref.length;
        int refRead = 0;
        int cost = 0;

        while (refSize - refRead > 1) {
            int resRead = 0;
            int resWrite = 0;

            while (refRead < refSize) {
                int sum = 0;
                for (int i = 0; i < 2; ++i) {
                    int a = (refRead < refSize) ? ref[refRead] : Integer.MAX_VALUE;
                    int b = (resRead < resWrite) ? res[resRead] : Integer.MAX_VALUE;

                    if (a <= b) {
                        sum += ref[refRead++];
                    } else {
                        sum += res[resRead++];
                    }
                }

                res[resWrite++] = sum;
                cost += sum;
            }

            int[] tmp = ref;
            ref = res;
            res = tmp;

            refSize = resWrite;
            refRead = resRead;
        }
        return cost;
    }


//    private int[] sticks;
//    private int inputIndex = 0, inputEnd, resultStart = 0, resultEnd = 0;

    private int getMin() {
        if (inputIndex >= inputEnd && resultStart >= resultEnd) return 0;
        if (inputIndex >= inputEnd || (resultStart < resultEnd && sticks[resultStart] < sticks[inputIndex]))
            return sticks[resultStart++];
        return sticks[inputIndex++];
    }

    public int connectSticks_1(int[] sticks) {
        inputEnd = sticks.length;
        if (inputEnd <= 1) return 0;
        this.sticks = sticks;
        Arrays.sort(sticks);
        // for(int i: sticks) {
        //     System.out.print(i+" ");
        // }
        // System.out.println();
        // for(int i: this.sticks) {
        //     System.out.print(i+" ");
        // }
        // System.out.println();

        int res = 0;
        int first = getMin();
        while (first > 0) {
            int second = getMin();
            if (second > 0) {
                int val = first + second;
                res += val;
                sticks[resultEnd++] = val;
            }
            first = getMin();
        }
        return res;
    }

    private List<Integer> sticks;
    private int inputIndex = 0, inputEnd, resultStart = 0, resultEnd = 0;

    private int getMin() {
        if (inputIndex >= inputEnd && resultStart >= resultEnd) return 0;
        if (inputIndex >= inputEnd || (resultStart < resultEnd && sticks.get(resultStart) < sticks.get(inputIndex)))
            return sticks.get(resultStart++).intValue();
        return sticks.get(inputIndex++).intValue();
    }

    int minimumTime(int numOfSubFiles, List<Integer> files)
    {
        Collections.sort(files);
        // for(int i: sticks) {
        //     System.out.print(i+" ");
        // }
        // System.out.println();
        // for(int i: this.sticks) {
        //     System.out.print(i+" ");
        // }
        // System.out.println();

        int res = 0;
        int first = getMin();
        while (first > 0) {
            int second = getMin();
            if (second > 0) {
                int val = first + second;
                res += val;
                sticks.set(resultEnd++, val);
            }
            first = getMin();
        }
        return res;

    }


    int minimumTime(int numOfSubFiles, List<Integer> files)
    {
        if (numOfSubFiles == 0) {
            return 0;
        }

        if (numOfSubFiles == 1) {
            return files.get(0).intValue();
        }

        // WRITE YOUR CODE HERE
        PriorityQueue<Integer> pq =  new PriorityQueue<>();
        // init the queue
        for (Integer i : files) {
            pq.offer(i);
        }

        int res = 0;
        while (pq.size() > 1) {
            Integer tmp1 = pq.poll();
            Integer tmp2 = pq.poll();
            res += tmp1 + tmp2;
            pq.offer(tmp1 + tmp2);
            System.out.println("Test print pq :" + res);
        }

        return res;

    }

}
