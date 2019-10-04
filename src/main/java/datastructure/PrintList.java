package datastructure;

import java.util.List;

public class PrintList<T> {

    public void printList(List<T> result) {
        if (result == null || result.size() == 0) {
        	System.out.println("Empty List!");
        	return;
        }
            
        System.out.print(" [");
        for (T l : result) {
            if (result.lastIndexOf(l) == result.size() - 1) System.out.println(l + "]");
            else System.out.print(l + ",");
        }
    }

    public void printListandList(List<List<T>> result) {
        if (result == null || result.size() == 0) {
        	System.out.println("Empty List!");
        	return;
        }
            
        System.out.print(" [");
        for (List<T> r : result) {
            System.out.print("[");
            for (T t : r) {
                if (r.lastIndexOf(t) == r.size() - 1)  System.out.print(t + "]");
                else System.out.print(t + ", ");
            }
            if (result.lastIndexOf(r) == result.size() - 1) System.out.println("]");
            else System.out.print(", ");
            
        }
    }

}
