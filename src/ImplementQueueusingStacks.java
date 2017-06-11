import java.util.Stack;

/*
 * 232	Implement Queue using Stacks	35.9%	Easy
 * Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- 
which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. 
You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 * 
 * 面试遇到过这道题了，当时有个follow up question想了很久才答上来，
 * 好像是：你现在push是O(1)，pop的worse case是O(n)的，
 * 如果要保证pop是O(1)，你要怎么做？（在push不必须是O(1)的情况下）
 * 
 */
public class ImplementQueueusingStacks {
	
	class MyQueue{
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		public void push(int x) {
			
	        stack1.push(x);
	    }

	    // Removes the element from in front of queue.
		public void pop() {
	        if(stack2.empty()){
	        	while(!stack1.empty()){
	        		stack2.push(stack1.pop());
	        	}
	        }
	        stack2.pop();
	    }

	    // Get the front element.
		public int peek() {
	        if(stack2.empty()){
	        	while(!stack1.empty()){
	        		stack2.push(stack1.pop());
	        	}
	        }
	        return stack2.peek();
	    }

	    // Return whether the queue is empty.
		public boolean empty() {
	        return (stack1.empty() && stack2.empty());
	    }
		
	}

	//push O(n), popO(1), popO(1)
	class MyQueue2{
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();

		public void push(int x) {
			stack2.push(x);
			while (!stack1.empty()) {
				stack2.push(stack1.pop());
			}
			stack1 = stack2;
			stack2 = stack1;
		}

		// Removes the element from in front of queue.
		public void pop() {

			stack1.pop();
		}

		// Get the front element.
		public int peek() {

			return stack1.peek();
		}

		// Return whether the queue is empty.
		public boolean empty() {
			return (stack1.empty() && stack2.empty());
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
