package facebook;

import java.util.*;

/**
 * Created by cicean on 9/6/2016.
 * Given a set of 2D points, some integer k,
 * find the k points closest to the origin, (0,0)
 */
public class FindKthPointtoOrigin {

    //Using heap as implementation of priority queue to store the fist k points.
    // So the complexity is O(n log k) with O(k) memory.
    class solution1 {

        class Point implements Comparable<Point> {
            int x, y;
            double dist;

            public Point(int x, int y, Point originPoint) {
                this.x = x;
                this.y = y;
                this.dist = Math.hypot(x - originPoint.x, y - originPoint.y);
            }

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public int compareTo(Point that) {
                return Double.valueOf(that.dist).compareTo(dist);
            }

            @Override
            public String toString() {
                return "x: " + x + " y: " + y;
            }
        }

        public Collection<Point> getClosestPoints(Collection<Point> points, int k) {
            PriorityQueue<Point> queue = new PriorityQueue<Point>(k);

            for (Point point : points) {
                if (queue.size() < k) {
                    queue.offer(point);
                } else {
                    if (queue.peek().compareTo(point) < 0) {
                        queue.poll();
                        queue.offer(point);
                    }
                }
            }

            return queue;
        }

    }

    /**
     * Then traverse the array dist and print out all the corresponding points which have distance less than or equal to num. Time= O(n).

     overall time complexity= O(n)
     */

    class solution2 {



        public class FindNClosestPointsToOrigin {

            // this is a custom comparator we use to arrange points in a PriorityQueue
            public  Comparator<Point> pointComparator = new Comparator<Point>() {
                public int compare(Point p1, Point p2){
                    if(p2.getDistanceFromOrigin() > p1.getDistanceFromOrigin())
                        return 1;
                    else if(p2.getDistanceFromOrigin() < p1.getDistanceFromOrigin())
                        return -1;
                    else
                        return 0;
                }
            };

            public static void main(String[] args) throws IOException{
                BufferedReader br = new BufferedReader(new FileReader(new File("NClosestPointsToOrigin.txt")));
                int n = Integer.parseInt(br.readLine());
                PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(n,pointComparator);

                // add first n points in the max heap
                while(maxHeap.size() < n){
                    String[] temp = br.readLine().split("\\s+");
                    int x = Integer.parseInt(temp[0]);
                    int y = Integer.parseInt(temp[1]);
                    Point newPoint = new Point(x,y);
                    maxHeap.add(newPoint);
                }

                // check for other candidate points in the plane which are closest to the origin
                String temp;
                while((temp = br.readLine()) != null){
                    String[] tempArray = temp.split("\\s+");
                    int x = Integer.parseInt(tempArray[0]);
                    int y = Integer.parseInt(tempArray[1]);
                    Point newPoint = new Point(x,y);
                    if(maxHeap.peek().getDistanceFromOrigin() > newPoint.getDistanceFromOrigin()){
                        maxHeap.poll();
                        maxHeap.add(newPoint);
                    }
                }

                System.out.println("The " + n + " closest points to origin are as follows - ");
                while(!maxHeap.isEmpty()){
                    Point p = maxHeap.poll();
                    System.out.println(p.x + " " + p.y + " " + p.getDistanceFromOrigin());
                }
            }




        }

    }

    class ClosestPoints {

        private static final Point root = new Point();

        Comparator<Point> c = new Comparator<Point>(){
            double pointDistance(Point p1, Point p2){
                return Math.sqrt(Math.pow(2, (p1.x - p2.x)) + Math.pow(2, (p1.y - p2.y)));
            }

            @Override
            public int compare(Point p1, Point p2) {
                double d1 = pointDistance(p1, root);
                double d2 = pointDistance(p2, root);
                if(d1 < d2){
                    return -1;
                }
                if(d1 == d2){
                    return 0;
                }
                return 1;
            }
        };

        public Point[] getClosestToRoot(Point[] input, int k){
            if(k <= 0) return new Point[0];

            int l = 0; int h = input.length -1; int target = k-1;
            int p = partition(input, l, h);
            while(p != target){
                if(p < target){
                    p = partition(input, p+1, h);
                } else{
                    p = partition(input, l, p-1);
                }
            }
            return Arrays.copyOf(input, k);
        }

        int partition(Point[] input, int l, int h){
            int range = h-l+1;
            int p = l + (int)Math.random()*range;
            swap(input, p, h);
            Point pivot = input[h];
            int firstHigh = l;
            for(int i = l; i < h; i++){
                if(c.compare(input[i], pivot) < 1){
                    swap(input, i, firstHigh);
                    firstHigh++;
                }
            }
            swap(input, firstHigh, h);
            return firstHigh;
        }

        void swap(Object[] input, int i, int j){
            Object tmp = input[i];
            input[i] = input[j];
            input[j] = tmp;
        }
    }


}

// this point class is used to store 2D points in a plane
class Point{
    int x;
    int y;

    // this is the constructor for the Point class
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    // this function is used to get the distance of a point from the origin
    public double getDistanceFromOrigin(){
        return Math.sqrt((this.x)*(this.x) + (this.y)*(this.y));
    }
}
