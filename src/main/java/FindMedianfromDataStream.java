import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 * Created by cicean on 8/29/2016.
 * Total Accepted: 22193 Total Submissions: 97460 Difficulty: Hard
 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 Examples:
 [2,3,4] , the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.
 For example:

 add(1)
 add(2)
 findMedian() -> 1.5
 add(3)
 findMedian() -> 2
 Credits:
 Special thanks to @Louis1992 for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Heap Design

 */
public class FindMedianfromDataStream {

    /**
     * 分析
     这道题的经典做法就是维护两个Heap, 一个MaxHeap, 一个MinHeap，维护他们的size，比如保证

     MaxHeap.size() - MinHeap.size() >= 1
     当然这种题可以有些follow up, 比如：

     除了heap做，还有什么其他方法？
     BST可以，比如可以在Node里增加一个size表示整个children的数量，findMedian时间复杂度会增加到log(n);
     如果是找比如处在1/10th的元素，怎么找？
     同样可以用两个heap做，维护一个的size是另一个size的1/9即可。
     复杂度
     time: addNum -> O(log(n)), findMedian -> O(1)
     space: O(n)
     */
    class MedianFinder {

        // Adds a number into the data structure.
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        public void addNum(int num) {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());

            // 维护两个heap的size
            if (minHeap.size() > maxHeap.size())
                maxHeap.add(minHeap.remove());
        }

        // Returns the median of current data stream
        public double findMedian() {
            if (maxHeap.size() == minHeap.size())
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            else
                return maxHeap.peek();
        }
    }

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
    
    //LTE
    private int initSize = 5;
    private int[] store = new int[5];
    private int count = 0;
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        if (count >= initSize) increasingArray();
        if (num != 0) store[count] = num;
        count++;
        System.out.println("insert numbers" + count);
    }

    // Returns the median of current data stream
    public double findMedian() {
    	int[] tmp = new int[count];
        System.arraycopy(store, 0, tmp, 0, count);
    	Arrays.sort(tmp);
    	int low = count / 2;
        return count % 2 == 0 ? (double) (tmp[low - 1] + tmp[low]) / 2 : new Double(tmp[low]);
    }
    
    private void increasingArray() {
        initSize += 5;
        System.out.println("initSize = " + initSize);
        int[] temp = store;
        store = new int[initSize];
        System.arraycopy(temp, 0, store, 0, temp.length);
    }
    
    public static void main(String[] args) {
		FindMedianfromDataStream slt = new FindMedianfromDataStream();
		slt.addNum(6);
		System.out.println(slt.findMedian());
		slt.addNum(10);
		System.out.println(slt.findMedian());
		slt.addNum(2);
		System.out.println(slt.findMedian());
		
	}


}
