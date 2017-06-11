import java.util.Stack;


/*
 155	Min Stack	18.5%	Easy014
 Problem:    Min Stack 
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/min-stack/
 Notes:
 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.
*/

public class MinStack {

	 private Stack<Integer> stack = new Stack<>();
	    private Stack<Integer> minStack = new Stack<>();
	    public void push(int x) {
	      stack.push(x);
	      if (minStack.isEmpty() || x <= minStack.peek()) {
	         minStack.push(x);
	      }
	    }
	    public void pop() {
	      if (stack.pop().equals(minStack.peek())) minStack.pop();
	    }
	    public int top() {
	      return stack.peek();
	    }
	    public int getMin() {
	      return minStack.peek();
	    }

}

class solution{

	Stack<Integer> stack=new Stack<>();
	int min=Integer.MAX_VALUE;
	public void push(int x) {
		if(x<=min) {stack.push(min); min=x;}
		stack.push(x);
	}
	public void pop() {
		if(stack.peek()==min){ stack.pop(); min=stack.pop(); }
		else stack.pop();
	}
	public int top() {
		return stack.peek();
	}
	public int getMin() {
		return min;
	}
}

class MaxStack{

	Stack<Integer> stk;
	int max = Integer.MIN_VALUE;

	public MaxStack(){
		stk = new Stack<>();
	}

	public void push(int x) {
		if (x >= max) {
			stk.push(max);
			max = x;
		}
	}

}
