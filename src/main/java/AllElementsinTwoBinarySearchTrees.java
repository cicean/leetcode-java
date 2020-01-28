import java.util.*;

/**
 * 1305. All Elements in Two Binary Search Trees
 * Medium
 *
 * 108
 *
 * 3
 *
 * Add to List
 *
 * Share
 * Given two binary search trees root1 and root2.
 *
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 *
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 * Example 3:
 *
 * Input: root1 = [], root2 = [5,1,7,0,2]
 * Output: [0,1,2,5,7]
 * Example 4:
 *
 * Input: root1 = [0,-10,10], root2 = []
 * Output: [-10,0,10]
 * Example 5:
 *
 *
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 *
 * Constraints:
 *
 * Each tree has at most 5000 nodes.
 * Each node's value is between [-10^5, 10^5].
 * Accepted
 * 9,662
 * Submissions
 * 12,735
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * LeetCode
 * Traverse the first tree in list1 and the second tree in list2.
 * Merge the two trees in one list and sort it.
 */
public class AllElementsinTwoBinarySearchTrees {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        ans.addAll(inorderTraversal(root1));
        ans.addAll(inorderTraversal(root2));
        Collections.sort(ans);
        return ans;
    }

//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        if (root == null) return res;
//        List<Integer> left = inorderTraversal(root.left);
//        List<Integer> right = inorderTraversal(root.right);
//        res.addAll(left);
//        res.add(root.val);
//        res.addAll(right);
//        return res;
//
//    }

    public List<Integer> getAllElements_II(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = inorderTraversal(root1);
        List<Integer> l2 = inorderTraversal(root2);
        return mergeArray(l1, l2);
    }

    private List<Integer> inorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        traverse(list, root);
        return list;
    }

    private void traverse(List list, TreeNode root){
        if (root == null)
            return;
        traverse(list, root.left);
        list.add(root.val);
        traverse(list, root.right);
    }

    private  List<Integer> mergeArray(List<Integer> array1, List<Integer> array2){
        int m = array1.size();
        int n = array2.size();

        List<Integer> list = new ArrayList<>(m + n);
        int p1 = 0;
        int p2 = 0;
        while (p1 < m && p2 < n){
            if (array1.get(p1) <= array2.get(p2)){
                list.add(array1.get(p1++));
            }else {
                list.add(array2.get(p2++));
            }
        }
        while (p1 < m){
            list.add(array1.get(p1++));
        }

        while (p2 < n){
            list.add(array2.get(p2++));
        }
        return list;
    }

}
