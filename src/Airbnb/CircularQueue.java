package Airbnb;

/**
 * Created by cicean on 9/12/2018.
 */
public class CircularQueue {

    //基本是九章的解法，不过没用到取模运算。
    //因为题目说不会在 full 或者 empty 时调用 enqueue 或 dequeue，所以这两个方法里的 if 判断可以不加。
    private int[] circular;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int n) {
        // initialize your data structure here
        circular = new int[n];
        front = 0;
        rear = 0;
        size = 0;
    }
    /**
     * @return:  return true if the array is full
     */
    public boolean isFull() {
        // write your code here
        return circular.length == size;
    }

    /**
     * @return: return true if there is no element in the array
     */
    public boolean isEmpty() {
        // write your code here
        return size == 0;
    }

    /**
     * @param element: the element given to be added
     * @return: nothing
     */
    public void enqueue(int element) {
        // write your code here
        if (!this.isFull()) {
            circular[rear++] = element;
            size++;
            if (rear == circular.length) {
                rear = 0;
            }
        }
    }

    /**
     * @return: pop an element from the queue
     */
    public int dequeue() {
        // write your code here
        int num = 0;
        if (!this.isEmpty()) {
            num = circular[front++];
            size--;
            if (front == circular.length) {
                front = 0;
            }
        }
        return num;
    }
}

//Use two pointers head and tail to track the status of the array
class CircularQueue_1 {
    int capacity;
    int[] circularArray;
    int head, tail;
    public CircularQueue_1(int n) {
        // initialize your data structure here
        capacity = n;
        circularArray = new int[n];
        head = 0;
        tail = -1;
    }
    /**
     * @return:  return true if the array is full
     */
    public boolean isFull() {
        // write your code here
        return tail - head + 1 == capacity;
    }

    /**
     * @return: return true if there is no element in the array
     */
    public boolean isEmpty() {
        // write your code here
        return tail < head;
    }

    /**
     * @param element: the element given to be added
     * @return: nothing
     */
    public void enqueue(int element) {
        // write your code here
        if (tail + 1 < capacity) {
            circularArray[++tail] = element;
        } else {
            circularArray[++tail % capacity] = element;
        }

    }

    /**
     * @return: pop an element from the queue
     */
    public int dequeue() {
        // write your code here
        if (head < capacity) {
            return circularArray[head++];
        } else {
            int temp = circularArray[head % capacity];
            head++;
            return temp;
        }

    }
}
