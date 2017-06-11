package CC189;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cicean on 10/11/2016.
 * CC189 chapter 15
 */
public class LockedATM {
    private Lock lock;
    private int balance = 100;

    public LockedATM() {
        lock = new ReentrantLock();
    }

    public int withdraw(int value) {
        lock.lock();
        int tmp = balance;
        try {
            Thread.sleep(100);
            tmp = tmp - value;
            balance = tmp;
        } catch (InterruptedException e) {}
        lock.unlock();
        return tmp;
    }

    public int deposit(int value) {
        lock.lock();
        int tmp = balance;
        try {
            Thread.sleep(100);
            tmp = tmp + value;
            Thread.sleep(300);
            balance = tmp;
        } catch (InterruptedException e) {}
        lock.unlock();
        return tmp;
    }
}
