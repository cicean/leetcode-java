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
The class name of the Java function had been updated to Ali.MyStack instead of Stack.
 * Solution:
 * 1.1���������У�push: O(1)��pop: O(n)��top: O(n)
 * ����������q1��q2ʵ��һ��ջ��pushʱ����Ԫ����ӵ�q1�Ķ�β��popʱ��q1�г����һ��Ԫ���������ӵ�q2�У�
 * Ȼ��pop��q1�е����һ��Ԫ�أ�Ȼ��ע��ǵ�q1��q2��
 * �Ա�֤�������Ԫ��ʱʼ����q1����ӡ�top�ĵ������ơ�
 * 1.2���������У�push: O(n)��pop: O(1)��top: O(1)
 * ����Ԫ�ض����򱣴���q1�У�������ӵ�Ԫ����q1����ǰ�ˣ�
 * ��������أ�ÿ��pushʱ������Ԫ�طŵ��յ�q2��Ȼ���q1��Ԫ�������ӵ�q2�Ķ�β��
 * ��󽻻�q1��q2������q1���׵�Ԫ�ؾ��������ӵ�Ԫ�أ�pop��topֱ�ӷ���q1���׵�Ԫ�ؾͺá�
 * 
 * ����Լ������еĲ���������Ч�ģ����磬����Կ�ջִ��pop����top������
 * ȡ������ʹ�õ����ԣ�queue����û�б�ԭ��֧�֡������ʹ��list����deque��˫�˶��У�ģ��һ�����У�ֻҪ��֤�����ʹ�ö��еı�׼�������ɡ����༴ֻ�����²�������Ч�ģ�push to back�������β����pop from front���������ף���size��ȡ���д�С���Լ�is empty���ж��Ƿ�Ϊ�գ�

	����˼·��
	push(x) -- ʹ��queue��push to back����.
	pop() -- ��queue�г���β�������Ԫ��pop from frontȻ��push to back�����ִ��һ��pop from front
	top() -- ��queue������Ԫ��pop from frontȻ��push to back��ʹ�ø�������top��¼ÿ�ε�����Ԫ�أ�����top
	empty() -- ʹ��queue��is empty����.
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
