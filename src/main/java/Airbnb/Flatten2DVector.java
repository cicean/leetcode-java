package Airbnb;

import java.io.IOException;
import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 * �������Թ٣��о��ǳ��䣬ɶ�����ʣ�����ֱ�����⡣��Ŀ��2D iterator����һ��remove����10��min��д���ˣ��������Թ�˵��run������design��̫�ã����һ�һ�ַ�������ʾ����iterator��remove�������Ҷ�iterator��remove�������Ǻ��죬��˵�ܲ��ܲ�api����˵���ԡ�
 Ȼ���ҾͲ�api��Ȼ��lz��api��˵�Ŀ����󶮣�Ȼ�����Թٰ�ærun��һ��case��
 Ȼ���Ҷ��ˣ�Ȼ��͸ģ�Ȼ���ֳ��˼�����ɵ�Ƶ�bug��������Թ�˵�ٸ���1min����Ȼ��lz���ڵ�������Ȼ�����Թ�˵great�����о���ο�ң���Ȼ���Ҿ������⣬���Ǻ�ɵ�Ƶ��ǣ����ʵ�������Ǹ����Թ����Ķ�����һ����
 ���Թٲ�����ô�ش�Ȼ���Ҿ���������һ�����Ĺ�����Ȼ����������2����Ȼ���Bye�ˡ�
 with remove
 */
public class Flatten2DVector {

    //���һ����������ʵ��̯ƽ��ά�����Ĺ���
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

//ʹ��2��ջ���㷨���Flatten 2D Vector
//������������㷨

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

        public boolean hasNext() { // ׼����һ��Ԫ��
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


