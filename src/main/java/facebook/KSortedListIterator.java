package facebook;

/**
 * Created by cicean on 9/7/2016.
 *
 */
public class KSortedListIterator implements java.util.Iterator<E> {


    public LinkedNode mergeKLists(ListNode[] lists) {
        //implementation from leetcode. visit 1point3acres.com for more.
    }

    MergeKSortedLists mergeKlists = new MergeKSortedLists();

    private LinkedNode<E> current = mergeKlists.mergeKLists(lists);

    private KSortedListIterator() {
        current = head;  // from the enclosing class --. 1point3acres.com/bbs
        // ListIterator cannot be a static class-google 1point3acres
    }

    public boolean hasNext() {
        return (current != null);
    }

    public E next() {
        if (hasNext()) {
            E result = current.item;
            current = current.next;   // may be null
            return result;
        }  // no next element. 围观我们@1point 3 acres
        throw new java.util.NoSuchElementException("linked list.next");
    }
    // ListIterator cannot be a static class. more info on 1point3acres.com


}
