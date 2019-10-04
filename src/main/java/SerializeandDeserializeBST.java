import java.util.*;

/**
 * 449. Serialize and Deserialize BST
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

 The encoded string should be as compact as possible.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

public class SerializeandDeserializeBST {

  /**
   * Contest
   Articles
   Discuss
   •
   Store
   Sign up Sign in
   449. Serialize and Deserialize BST
   DescriptionHintsSubmissionsDiscussSolution
   Java PreOrder + Queue solution
   Hi all, I think my solution is pretty straightforward and easy to understand, not that efficient though. And the serialized tree is compact.
   Pre order traversal of BST will output root node first, then left children, then right.

   root left1 left2 leftX right1 rightX
   If we look at the value of the pre-order traversal we get this:

   rootValue (<rootValue) (<rootValue) (<rootValue) |separate line| (>rootValue) (>rootValue)
   Because of BST's property: before the |separate line| all the node values are less than root value, all the node values after |separate line| are greater than root value. We will utilize this to build left and right tree.

   Pre-order traversal is BST's serialized string. I am doing it iteratively.
   To deserialized it, use a queue to recursively get root node, left subtree and right subtree.

   I think time complexity is O(NlogN).
   Errr, my bad, as @ray050899 put below, worst case complexity should be O(N^2), when the tree is really unbalanced.

   My implementation
   */

  public class Solution {
    private static final String SEP = ",";
    private static final String NULL = "null";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      if (root == null) return NULL;
      //traverse it recursively if you want to, I am doing it iteratively here
      Stack<TreeNode> st = new Stack<>();
      st.push(root);
      while (!st.empty()) {
        root = st.pop();
        sb.append(root.val).append(SEP);
        if (root.right != null) st.push(root.right);
        if (root.left != null) st.push(root.left);
      }
      return sb.toString();
    }

    // Decodes your encoded data to tree.
    // pre-order traversal
    public TreeNode deserialize(String data) {
      if (data.equals(NULL)) return null;
      String[] strs = data.split(SEP);
      Queue<Integer> q = new LinkedList<>();
      for (String e : strs) {
        q.offer(Integer.parseInt(e));
      }
      return getNode(q);
    }

    // some notes:
    //   5
    //  3 6
    // 2   7
    private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7
      if (q.isEmpty()) return null;
      TreeNode root = new TreeNode(q.poll());//root (5)
      Queue<Integer> samllerQueue = new LinkedList<>();
      while (!q.isEmpty() && q.peek() < root.val) {
        samllerQueue.offer(q.poll());
      }
      //smallerQueue : 3,2   storing elements smaller than 5 (root)
      root.left = getNode(samllerQueue);
      //q: 6,7   storing elements bigger than 5 (root)
      root.right = getNode(q);
      return root;
    }
  }

}
