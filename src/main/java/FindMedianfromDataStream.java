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

    class MedianFinder {

        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        /** initialize your data structure here. */
        public MedianFinder() {

            minHeap = new PriorityQueue<Integer>((a,b)-> b - a);
            maxHeap = new PriorityQueue<Integer>();
        }

        public void addNum(int num) {


            if(minHeap.size()==0 || num<minHeap.peek())
            {
                minHeap.offer(num);
            }
            else
            {
                maxHeap.offer(num);
            }

        }

        public double findMedian() {

            while(Math.abs(minHeap.size()-maxHeap.size())>=2)
            {
                rebalanceHeaps(maxHeap, minHeap);
            }

            if (maxHeap.size()==minHeap.size())
            {
                return (double) (minHeap.peek()+maxHeap.peek())/2;
            }
            else if(minHeap.size()>maxHeap.size())
            {
                return minHeap.peek();
            }
            else
            {
                return maxHeap.peek();
            }

        }

        private void rebalanceHeaps(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap)
        {
            PriorityQueue<Integer> smallHeap;
            PriorityQueue<Integer> bigHeap;

            if(maxHeap.size()<minHeap.size())
            {
                smallHeap = maxHeap;
                bigHeap = minHeap;
            }
            else {
                smallHeap = minHeap;
                bigHeap = maxHeap;
            }

            smallHeap.offer(bigHeap.poll());
        }
    }

    /**
     * ����
     �����ľ�����������ά������Heap, һ��MaxHeap, һ��MinHeap��ά�����ǵ�size�����籣֤

     MaxHeap.size() - MinHeap.size() >= 1
     ��Ȼ�����������Щfollow up, ���磺

     ����heap��������ʲô����������
     BST���ԣ����������Node������һ��size��ʾ����children��������findMedianʱ�临�ӶȻ����ӵ�log(n);
     ������ұ��紦��1/10th��Ԫ�أ���ô�ң�
     ͬ������������heap����ά��һ����size����һ��size��1/9���ɡ�
     ���Ӷ�
     time: addNum -> O(log(n)), findMedian -> O(1)
     space: O(n)
     */
    class MedianFinderII {

        // Adds a number into the data structure.
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        public void addNum(int num) {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());

            // ά������heap��size
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
