/**
 * 987. Vertical Order Traversal of a Binary Tree
 * Medium
 *
 * 186
 *
 * 433
 *
 * Favorite
 *
 * Share
 * Given a binary tree, return the vertical order traversal of its nodes values.
 *
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 *
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 *
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 *
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 * Example 2:
 *
 *
 *
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 *
 * Note:
 *
 * The tree will have between 1 and 1000 nodes.
 * Each node's value will be between 0 and 1000.
 *
 * Accepted
 * 16,640
 * Submissions
 * 51,288
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * saketpu
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * 5
 *
 * Google
 * |
 * 5
 *
 * Amazon
 * |
 * 2
 */
public class VerticalOrderTraversalofaBinaryTree {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        PriorityQueue<Point> pq = new PriorityQueue<Point>(1005,new Comparator<Point>(){
            public int compare(Point p1,Point p2){
                if(p1.x < p2.x) return -1;
                if(p2.x < p1.x) return 1;
                if(p1.y > p2.y) return -1;
                if(p1.y < p2.y) return 1;
                return p1.val - p2.val;
            }
        });

        verticalTraversalHelper(root,0,0,pq);
        Point prev = null;
        List<Integer> l = new ArrayList<>();
        while(!pq.isEmpty()){
            Point p = pq.poll();
            if(prev == null || p.x != prev.x){
                if(prev != null) res.add(l);
                l = new ArrayList<>();
            }
            l.add(p.val);
            prev = p;
        }

        if(res.size() > 0) res.add(l);
        return res;
    }

    private void verticalTraversalHelper(TreeNode root,int x,int y,PriorityQueue<Point> pq){
        if(root == null) return;
        pq.offer(new Point(x,y,root.val));
        verticalTraversalHelper(root.left,x-1,y-1,pq);
        verticalTraversalHelper(root.right,x+1,y-1,pq);
    }
}


