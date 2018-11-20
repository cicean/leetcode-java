package Airbnb;

import java.io.IOException;
import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 * 白人面试官，感觉非常冷，啥都不问，上来直接做题。题目是2D iterator，加一个remove。我10多min就写完了，但是面试官说能run，但是design不太好，让我换一种方法。提示利用iterator的remove方法，我对iterator的remove方法不是很熟，我说能不能查api，他说可以。
 然后我就查api，然后lz对api里说的看不大懂，然后面试官帮忙run了一个case，
 然后我懂了，然后就改，然后又出了几个很傻逼的bug，最后面试官说再给你1min调，然后lz终于调出来。然后面试官说great。（感觉安慰我）。然后我就问问题，但是很傻逼的是，我问的问题和那个面试官做的东西不一样，
 面试官不懂怎么回答，然后我就让他讲了一下他的工作，然后我又问了2个。然后就Bye了。
 with remove
 */
public class Flatten2DVector {

    //设计一个迭代器来实现摊平二维向量的功能
    public class Vector2D implements Iterator<Integer> {

        private Iterator<List<Integer>> i;
        private Iterator<Integer> j;

        public Vector2D(List<List<Integer>> vec2d) {
            // Initialize your data structure here
            i = vec2d.iterator();
            j = null;
        }

        @Override
        public Integer next() {
            // Write your code here
            if (!hasNext()) {
                return null;
            }
            return j.next();
        }

        @Override
        public boolean hasNext() {
            // Write your code here
            while ((j == null || !j.hasNext()) && i.hasNext())
                j = i.next().iterator();
            return j != null && j.hasNext();
        }

        @Override
        public void remove() {
            while (j == null && i.hasNext()) {
                j = i.next().iterator();
            }

            if (j != null)
                j.remove();
        }
    }

}

//使用2个栈的算法解决Flatten 2D Vector
//课堂所讲解的算法

class Solution_jiuzhang{
    public class Vector2D implements Iterator<Integer> {
        Stack<List<Integer>> stack = new Stack<>();
        Stack<Integer> stackj;

        void pushListListToStack(List<List<Integer>> vec2d) {
            Stack<List<Integer>> temp = new Stack<>();
            for (List<Integer> nested : vec2d) {
                temp.push(nested);
            }

            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }

        void pushListToStack(List<Integer> vec) {
            Stack<Integer> temp = new Stack<>();
            for (Integer nested : vec) {
                temp.push(nested);
            }

            while (!temp.isEmpty()) {
                stackj.push(temp.pop());
            }
        }

        public Vector2D(List<List<Integer>> vec2d) {
            pushListListToStack(vec2d);
            // Initialize your data structure here
            stackj = new Stack<>();
        }

        public Integer next() {
            // Write your code here
            if(!hasNext()) {
                return null;
            }
            return stackj.pop();
        }

        public boolean hasNext() { // 准备下一个元素
            // Write your code here
            while (stackj.isEmpty() && !stack.isEmpty())
                pushListToStack(stack.pop());
            return !stackj.isEmpty();
        }

        public void remove() {}
    }
}

class solutionwithremove{

    class Vector2D implements Iterator<Integer> {
        private int row = 0;
        private int col = 0;
        private List<List<Integer>> vec;
        public Vector2D(List<List<Integer>> vec2d) {
            vec = vec2d;
        }

        @Override
        public Integer next() {
            return vec.get(row).get(col++);
        }

        @Override
        public boolean hasNext() {
            //if row<vec.size() && col<list.get(row).size() no need to enter while loop,it's true
            //otherwise, go to the next row
            //see if there's any row left
            while(row<vec.size() && col==vec.get(row).size()) {
                col = 0;
                row++;
            }
            return !(row==vec.size());
        }
        @Override
        public void remove() {
            //remove value that is nexted last round
            vec.get(row).remove(col-1);
            col--;
        }
    }
}


class Iterator2D {

    private int i = 0;
    private int j = 0;
    private List<List<Integer>> vec2d;

    public Iterator2D(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        this.vec2d = vec2d;
    }


    public Integer next() throws Exception{
        // Write your code here
        if (!hasNext()) {
            throw new IOException("no elements!");
        }
        return vec2d.get(i).get(j++);
    }


    public boolean hasNext() {
        // Write your code here
        while(i<vec2d.size() && j==vec2d.get(i).size()) {
            j = 0;
            i++;
        }
        return !(i==vec2d.size());
    }


    public void remove() throws Exception{
        if (vec2d.size() != 0) {
            if (vec2d.get(vec2d.size() - 1).size() == 1) {
                vec2d.remove(vec2d.size() - 1);
            } else {
                vec2d.get(vec2d.size() - 1).remove( vec2d.get(vec2d.size() - 1).size() - 1);
            }
        } else {
            throw new IOException("no elements!");
        }
    }
}

public class Main {



    public static void main(String[] args) throws Throwable {
        Main solution = new Main();

        List<List<Integer>> inputs = new ArrayList<>();
        int[][] tests = {{1,2,3},{4},{5,6}};

        for (int[] test : tests) {
            List<Integer> tmp = new ArrayList<>();
            for (int i : test) {
                tmp.add(i);
            }
            inputs.add(tmp);

        }

        Iterator2D i = new Iterator2D(inputs);

        try {
            //i.remove();
            System.out.print("next ->" + i.next());
            i.remove();
            System.out.print("remove");
            System.out.print("next ->" + i.next());
            System.out.print("next ->" + i.next());
            i.remove();
            System.out.print("next ->" + i.next());
            System.out.print("next ->" + i.next());
            System.out.print("next ->" + i.next());
        } catch (Exception e) {
            System.out.print("no message");
        }

    }

}


