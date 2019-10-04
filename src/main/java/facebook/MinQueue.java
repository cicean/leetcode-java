package facebook;

import java.util.*;

/**
 * Created by cicean on 9/6/2016.
 */
public class MinQueue {


    Queue<Integer> queue;
    Deque<Integer> deque;

    public MinQueue() {
        queue = new LinkedList<Integer>();
        deque = new ArrayDeque<Integer>();
    }

    public void offer(int x) {
        if (queue.size() == 0) {
            queue.offer(x);

            deque.offer(x);
        } else {
            queue.offer(x);
            for (Iterator<Integer> it = deque.descendingIterator(); it.hasNext(); ) {
                if (it.next() > x) {
                    it.remove();
                }
            }
            deque.offer(x);
        }
    }

    public int remove() {
        if (queue.size() == 0) return -1;
        int val = 0;
        if (queue.peek() == deque.peek()) {
            val = queue.remove();
            deque.remove();
        } else {
            val = queue.remove();
        }
        return val;
    }

    public int getMin() {
        return deque.getFirst();
    }


}

