import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

/**
 * 362. Design Hit Counter
 * Medium
 *
 * 619
 *
 * 62
 *
 * Add to List
 *
 * Share
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 *
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * Example:
 *
 * HitCounter counter = new HitCounter();
 *
 * // hit at timestamp 1.
 * counter.hit(1);
 *
 * // hit at timestamp 2.
 * counter.hit(2);
 *
 * // hit at timestamp 3.
 * counter.hit(3);
 *
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 *
 * // hit at timestamp 300.
 * counter.hit(300);
 *
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 *
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 * Follow up:
 * What if the number of hits per second could be very large? Does your design scale?
 *
 * Accepted
 * 73,661
 * Submissions
 * 117,607
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * elmirap
 * Logger Rate Limiter
 * Created by cicean on 9/1/2016.
 *
 * Hide Company Tags Dropbox Google
 * Hide Tags Design
 * Hide Similar Problems (E) Logger Rate Limiter
 */
public class DesignHitCounter {

    /**
     * ˼·: �ø������ʱ�������, ���ȡ�����ʱ��ѹ�������ɾ��������,
     * Ȼ�����ÿ��ܶ�����, ��ô��ÿ��ļ���������һ�������,
     * ������Ҫ����һ����ǰ�����������, ��������������, ����+1,
     * �����ǰ���׽�������, �Ͱ���һ���ڵļ�������ȥ����
     */

    class Hit {
        int timestamp;
        int count;
        Hit next;
        Hit(int timestamp) {
            this.timestamp = timestamp;
            this.count = 1;
        }
    }

    public class HitCounter {

        private Hit start = new Hit(0);
        private Hit tail = start;
        private int count = 0;

        /** Initialize your data structure here. */
        public HitCounter() {

        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {

            long timest = System.currentTimeMillis();

            if (tail.timestamp == timestamp) {
                tail.count ++;
                count ++;
            } else {
                tail.next = new Hit(timestamp);
                tail = tail.next;
                count ++;
            }
            getHits(timestamp);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            while (start.next != null && timestamp - start.next.timestamp >= 300) {
                count -= start.next.count;
                start.next = start.next.next;
            }
            if (start.next == null) tail = start;
            return count;
        }
    }




    /**
     * O(s) s is total seconds in given time interval, in this case 300.
     * basic ideal is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300 seconds.
     * hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which record current time.
     * If it is not current time,it means it is 300s or 600s... ago and need to reset to 1.
     */
    public class HitCounter {
        private int[] times;
        private int[] hits;
        /** Initialize your data structure here. */
        public HitCounter() {
            times = new int[300];
            hits = new int[300];
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int total = 0;
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    total += hits[i];
                }
            }
            return total;
        }


    }

    //DropBox
    // No multithreading

    class DropBoxHit {

        long timestamp;
        int count;
        DropBoxHit next;

        public DropBoxHit(long timestamp) {
            this.timestamp = timestamp;
            this.count = 1;
        }

    }

    public class DropBoxHitCounter{

        private Lock lock;

        private DropBoxHit head = new DropBoxHit(0);
        private DropBoxHit tail = head;
        private int count = 0;

        public DropBoxHitCounter() {
            lock = new ReentrantLock();
        }

        public void hit() {
            synchronized (this) {}
            lock.lock();
            long timestamp = System.currentTimeMillis();
            try {
                Thread.sleep(100);
                if (tail.timestamp == timestamp) {
                    tail.count++;
                    Thread.sleep(100);
                    count++;
                } else {
                    tail.next = new DropBoxHit(timestamp);
                    Thread.sleep(100);
                    count++;
                    tail = tail.next;
                }
                getHits();
            } catch (InterruptedException e) {

            }
            lock.unlock();
            }


        public int getHits() {
            long timestamp = System.currentTimeMillis();
            while (head.next != null && timestamp - head.next.timestamp >= 300) {
                count -= head.next.count;
                head.next = head.next.next;
            }
            if (head.next == null) tail = head;
            return count;
        }
    }

    class HitCounter {

        /** Initialize your data structure here. */
        ArrayList<Integer> cache = new ArrayList<>();
        public HitCounter() {

        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            cache.add(timestamp);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            if (cache.size() == 0) return 0;
            int start = 0, end = cache.size()-1;
            while (start < end) {
                int mid = start + (end-start)/2;
                if (cache.get(mid) > timestamp-300) {
                    end = mid;
                } else {
                    start = mid+1;
                }
            }
            if (cache.get(start) <= timestamp-300) {
                start++;
            }
            return cache.size()-start;
        }
    }

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */



}
