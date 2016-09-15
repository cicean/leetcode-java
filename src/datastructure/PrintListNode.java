package datastructure;

/**
 * Created by cicean on 9/12/2016.
 */
public class PrintListNode {

    public  void print(ListNode node){
        while (node != null)
        {
            if(node.next != null)
            {
                System.out.print(node.val + "->");
                node = node.next;
            }
            else if(node.next == null)
            {
                System.out.println(node.val);
                node = node.next;
            }
        }

    }

}
