package Ali;

public class alibaba {

}

public class MyStack<E> {

  private E[] arr = null;
  private int n;
  private int top = -1;
  private int bottom = 0;
  private int size = 0;

  public MyStack<E>(int n) {
    this.n = n;
    this.arr = (E[]) new Object[n];

  }

  // Push element x onto stack.
  public void push(E x) {
    if (isFull()) {
      this.arr[bottom] = x;
      if (top == n - 1) {
        top = bottom;
        bottom++;
      } else if (bottom == n -1) {
        top++;
        bottom = 0;
      } else {
        bottom++;
        top=bottom;
      }
    } else {
      this.size++;
      if (top == n - 1) {
        top=0;
        this.arr[top] = x;
      } else {
        this.arr[++top] = x;
      }
    }

  }

  // Removes the element on top of the stack.
  public E pop() {
    if (this.size > 0) {
      this.size--;
      E result = this.arr[top];
      this.arr[top] = null;
      if (top == 0) {
        top = n;
      } else {
        this.top--;
      }
      return result;
    }
    return null;
  }

  // Get the top element.
  public E peek() {
    if (this.size > 0) {
      return this.arr[top];
    }
    return null;
  }

  // Return whether the stack is empty.
  public boolean empty() {
     if (this.size == 0) {
       return true;
     }
     return false;
  }

  // check the stack full
  public boolean isFull() {
    if (this.size == this.n) {
      return true;
    }
    return false;
  }
}
