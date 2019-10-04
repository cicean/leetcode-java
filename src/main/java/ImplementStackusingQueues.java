import java.util.LinkedList;
import java.util.Queue;

/*
 * 225	Implement Stack using Queues	30.0%	Medium
 * Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
Update (2015-06-11):
The class name of the Java function had been updated to MyStack instead of Stack.
 * Solution:
 * 1.1用两个队列，push: O(1)，pop: O(n)，top: O(n)
 * 用两个队列q1，q2实现一个栈。push时把新元素添加到q1的队尾。pop时把q1中除最后一个元素外逐个添加到q2中，
 * 然后pop掉q1中的最后一个元素，然后注意记得q1和q2，
 * 以保证我们添加元素时始终向q1中添加。top的道理类似。
 * 1.2用两个队列，push: O(n)，pop: O(1)，top: O(1)
 * 所有元素都倒序保存在q1中，即后添加的元素在q1的最前端，
 * 如何做到呢？每次push时，把新元素放到空的q2，然后把q1中元素逐个添加到q2的队尾，
 * 最后交换q1和q2。这样q1队首的元素就是最后添加的元素，pop和top直接返回q1队首的元素就好。
 * 
 * 你可以假设所有的操作都是有效的（例如，不会对空栈执行pop或者top操作）
 * 取决于你使用的语言，queue可能没有被原生支持。你可以使用list或者deque（双端队列）模拟一个队列，只要保证你仅仅使用队列的标准操作即可——亦即只有如下操作是有效的：push to back（加入队尾），pop from front（弹出队首），size（取队列大小）以及is empty（判断是否为空）

	解题思路：
	push(x) -- 使用queue的push to back操作.
	pop() -- 将queue中除队尾外的所有元素pop from front然后push to back，最后执行一次pop from front
	top() -- 将queue中所有元素pop from front然后push to back，使用辅助变量top记录每次弹出的元素，返回top
	empty() -- 使用queue的is empty操作.
 * 
 * 
 * 
 */
public class ImplementStackusingQueues {

	class MyStack {

		private Queue queue;

		public void push(int x) {
			Queue q = new LinkedList();     // could be any queue type, see note above
			q.add(x);
			q.add(queue);
			queue = q;
		}

		public void pop() {
			queue.remove();
			queue = (Queue) queue.peek();
		}

		public int top() {
			return (int) queue.peek();
		}

		public boolean empty() {
			return queue == null;
		}
	}

	class MyStack_1 {  
	    private Queue<Integer> q1 = new LinkedList<>();  
	    private Queue<Integer> q2 = new LinkedList<>();  
	      
	    // Push element x onto stack.  
	    public void push(int x) {  
	        q1.offer(x);  
	    }  
	  
	    // Removes the element on top of the stack.  
	    public void pop() {  
	        while (q1.size() > 1) {  
	            q2.offer(q1.poll());  
	        }  
	        q1.poll();  
	          
	        Queue<Integer> tmp = q1;  
	        q1 = q2;  
	        q2 = tmp;  
	    }  
	  
	    // Get the top element.  
	    public int top() {  
	        while (q1.size() > 1) {  
	            q2.offer(q1.poll());  
	        }  
	        int top = q1.peek();  
	        q2.offer(q1.poll());  
	          
	        Queue<Integer> tmp = q1;  
	        q1 = q2;  
	        q2 = tmp;  
	          
	        return top;  
	    }  
	  
	    // Return whether the stack is empty.  
	    public boolean empty() {  
	        return q1.isEmpty();  
	    }  
	}  
	
	class MyStack_2 {  
	    private Queue<Integer> q = new LinkedList<>();  
	      
	    // Push element x onto stack.  
	    public void push(int x) {  
	        q.offer(x);  
	    }  
	  
	    // Removes the element on top of the stack.  
	    public void pop() {  
	        int size = q.size();  
	        for (int i = 1; i < size; i++) {  
	            q.offer(q.poll());  
	        }  
	        q.poll();  
	    }  
	  
	    // Get the top element.  
	    public int top() {  
	        int size = q.size();  
	        for (int i = 1; i < size; i++) {  
	            q.offer(q.poll());  
	        }  
	        int top = q.peek();  
	        q.offer(q.poll());  
	        return top;  
	    }  
	  
	    // Return whether the stack is empty.  
	    public boolean empty() {  
	        return q.isEmpty();  
	    }  
	}  
	
	class MyStack_3 {
		LinkedList<Integer> queue;

		void MyStack() {
			this.queue = new LinkedList<Integer>();
		}

		// Push element x onto stack.
		public void push(int x) {
			queue.add(x);
		}

		// Removes the element on top of the stack.
		public void pop() {
			queue.remove(queue.size()-1);
		}

		// Get the top element.
		public int top() {
			return queue.getLast();
		}

		// Return whether the stack is empty.
		public boolean empty() {
			return queue.isEmpty();
		}
	}
	
	class MyStack_4 {
	    LinkedList<Integer> queue1 = new LinkedList<Integer>();
	    LinkedList<Integer> queue2 = new LinkedList<Integer>();
	 
	    // Push element x onto stack.
	    public void push(int x) {
	        if(empty()){
	            queue1.offer(x);
	        }else{
	            if(queue1.size()>0){
	                queue2.offer(x);
	                int size = queue1.size();
	                while(size>0){
	                    queue2.offer(queue1.poll());
	                    size--;
	                }
	            }else if(queue2.size()>0){
	                queue1.offer(x);
	                int size = queue2.size();
	                while(size>0){
	                    queue1.offer(queue2.poll());
	                    size--;
	                }
	            }
	        }
	    }
	 
	    // Removes the element on top of the stack.
	    public void pop() {
	        if(queue1.size()>0){
	            queue1.poll();
	        }else if(queue2.size()>0){
	            queue2.poll();
	        }
	    }
	 
	    // Get the top element.
	    public int top() {
	       if(queue1.size()>0){
	            return queue1.peek();
	        }else if(queue2.size()>0){
	            return queue2.peek();
	        }
	        return 0;
	    }
	 
	    // Return whether the stack is empty.
	    public boolean empty() {
	        return queue1.isEmpty() & queue2.isEmpty();
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
