import java.util.*;

/**
 * Created by cicean on 8/30/2016.
 * 346. Moving Average from Data Stream  QuestionEditorial Solution  My Submissions
 Total Accepted: 7176
 Total Submissions: 12749
 Difficulty: Easy
 Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

 For example,
 MovingAverage m = new MovingAverage(3);
 m.next(1) = 1
 m.next(10) = (1 + 10) / 2
 m.next(3) = (1 + 10 + 3) / 3
 m.next(5) = (10 + 3 + 5) / 3
 Hide Company Tags Google
 Hide Tags Design Queue

 */
public class MovingAveragefromDataStream {

    /**
     * ˼·:sliding window

     �������ڵ�˼����Ǹ��ݻ������ڵĴ�С���δ�ǰ������������ȡ��Ӧ���ڴ�С��Ԫ��,�����ƶ��Ĺ�����,��Ҫ��ͣ���Ƴ����ڵ���Ԫ��,Ȼ��ĿǰԪ�ؼ��뵽������,ֱ���ƶ�������β�����������ں����� ��һ��ʵ�ַ�ʽ����ʹ��˫�˶��С�

     ����:

     1.��������long���ͱ������
     */

    public class MovingAverage {
        private Deque<Integer> dequeue=new LinkedList<>();
        private int size;
        private long sum;

        public MovingAverage(int size){
            this.size=size;
        }

        public double next(int val){
            if(dequeue.size()==size)
                sum-=dequeue.removeFirst();
            dequeue.addLast(val);
            sum+=val;
            return (double)sum/dequeue.size();
        }
    }

    public class MovingAverage2 {

        /** Initialize your data structure here. */
        Queue<Integer> data = new LinkedList<>();
        int size;
        int sum;
        public MovingAverage2(int size) {
            this.size = size;
            this.sum = 0;
        }

        public double next(int val) {
            if(data.size() >= size){
                data.offer(val);
                int head = data.poll();
                sum = sum - head + val;

                return sum/size;
            }else{
                sum += val;
                data.offer(val);
                return sum / data.size();
            }
        }
    }
}
