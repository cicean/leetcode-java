import java.util.*;

/**
 * 515. Find Largest Value in Each Tree Row
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 You need to find the largest value in each row of a binary tree.

 Example:
 Input:

 1
 / \
 3   2
 / \   \
 5   3   9

 Output: [1, 3, 9]

 */

public class FindLargestValueinEachTreeRow {

  /**
   * Just a simple pre-order traverse idea.
   * Use depth to expand result list size and put the max value in the appropriate position.
   */

  public class Solution {
    public List<Integer> largestValues(TreeNode root) {
      List<Integer> res = new ArrayList<Integer>();
      helper(root, res, 0);
      return res;
    }
    private void helper(TreeNode root, List<Integer> res, int d){
      if(root == null){
        return;
      }
      //expand list size
      if(d == res.size()){
        res.add(root.val);
      }
      else{
        //or set value
        res.set(d, Math.max(res.get(d), root.val));
      }
      helper(root.left, res, d+1);
      helper(root.right, res, d+1);
    }
  }

}
