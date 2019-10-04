package Dropbox;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by cicean on 9/26/2018.
 */
public class DesignPhoneDirectory {

    /**
     * 1.public BitSet(int nbits) ����һ��λ set�����ĳ�ʼ��С������ʽ��ʾ������Χ�� 0 �� nbits-1 ��λ�����е�λ��ʼ��Ϊ false��

     2.public void set(int bitIndex) ��ָ����������λ����Ϊ true��

     3.public int nextClearBit(int fromIndex) ���ص�һ������Ϊ false ��λ���������ⷢ����ָ������ʼ������֮��������ϡ�

     4.public void clear(int bitIndex) ������ָ������λ����Ϊ false��
     */

    //Bitset and efficient get() + comments
    public class PhoneDirectory {

        BitSet bitset;
        int max; // max limit allowed
        int smallestFreeIndex; // current smallest index of the free bit

        public PhoneDirectory(int maxNumbers) {
            this.bitset = new BitSet(maxNumbers);
            this.max = maxNumbers;
        }

        public int get() {
            // handle bitset fully allocated
            if(smallestFreeIndex == max) {
                return -1;
            }
            int num = smallestFreeIndex;
            bitset.set(smallestFreeIndex);
            //Only scan for the next free bit, from the previously known smallest free index
            smallestFreeIndex = bitset.nextClearBit(smallestFreeIndex);
            return num;
        }

        public boolean check(int number) {
            return bitset.get(number) == false;
        }

        public void release(int number) {
            //handle release of unallocated ones
            if(bitset.get(number) == false)
                return;
            bitset.clear(number);
            if(number < smallestFreeIndex) {
                smallestFreeIndex = number;
            }
        }
    }

    /**
     * Use a queue for get, O(1).
     Use another array for check, O(1)
     Only need to do one-time initialization when starting, O(N)
     */
    public class PhoneDirectory2 {
        Queue<Integer> availableQ;
        int[] checkList;
        public PhoneDirectory2(int maxNumbers) {
            availableQ = new LinkedList<Integer>();
            checkList = new int[maxNumbers];
            for(int i=0;i<maxNumbers;i++)
                availableQ.add(i);
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get() {
            if(availableQ.isEmpty()==true) return -1;
            else checkList[availableQ.peek()] = -1;
            return availableQ.poll();
        }

        /** Check if a number is available or not. */
        public boolean check(int number) {
            if(checkList[number]==-1) return false;
            else return true;
        }

        /** Recycle or release a number. */
        public void release(int number) {
            if(checkList[number]==-1){
                checkList[number] = 0;
                availableQ.add(number);
            }
        }
    }
}
