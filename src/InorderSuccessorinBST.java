import java.util.Stack;

/**
 * 285	Inorder Successor in BST 	33.3%	Medium
 * Problem Description:

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

There are just two cases:

The easier one: p has right subtree, then its successor is just the leftmost child of its right subtree;
The harder one: p has no right subtree, then a traversal is needed to find its successor.
Traversal: we start from the root, each time we see a node with val larger than p -> val, we know this node may be p's successor. So we record it in suc. Then we try to move to the next level of the tree: if p -> val > root -> val, which means p is in the right subtree, then its successor is also in the right subtree, so we update root = root -> right; if p -> val < root -> val, we update root = root -> left similarly; once we find p -> val == root -> val, we know we've reached at p and the current sucis just its successor.

The code is as follows. You may try some examples to see how it works :-)
 * @author cicean
 *
 */
public class InorderSuccessorinBST {
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
	    TreeNode prev = null;
	    while (root != p)
	        root = p.val < root.val ? (prev = root).left : root.right;
	    for (root = root.right; root != null; root = (prev = root).left);
	    return prev;
	}
	
	//recursive solution for both getting the successor and predecessor for a given node in BST.
	public TreeNode successor(TreeNode root, TreeNode p) {
		  if (root == null)
		    return null;

		  if (root.val <= p.val) {
		    return successor(root.right, p);
		  } else {
		    TreeNode left = successor(root.left, p);
		    return (left != null && left.val > p.val) ? left : root;
		  }
		}
	
	//
	
	/**
	 * 复杂度
时间 O(N) 空间 O(N)

思路
题目给定根节点和目标节点。目标节点如果有右节点的情况比较好处理，
我们只要返回它的右节点的最左边的节点就行了（右节点自己没有左节点时则是右节点本身）。
如果目标节点没有右节点，说明比目标节点稍大的节点应该在上面，因为我们知道目标节点的左节点肯定是比目标节点要小的。

那怎么知道目标节点的上面是什么呢？这时就需要从根节点搜索到目标节点了。
这里要注意的是，目标节点的父亲不一定比目标节点大，因为有可能目标节点的是其父亲的右孩子。
所以我们要找的上面，实际上是从目标节点向根节点回溯时，第一个比目标节点大的节点。
最差的情况下，如果回溯到根节点还是比目标节点要小的话，说明目标节点就是整个数最大的数了，这时候返回空。

那怎么实现目标节点向根节点回溯，这里用一个栈就行了，在我们寻找目标节点时，把路径上的节点都压入栈中。
	 * @param args
	 */
	
	public TreeNode inorderSuccessor_1(TreeNode root, TreeNode p) {
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode curr = root;
        // 找到目标节点并把路径上的节点压入栈
        while(curr != p){
            stk.push(curr);
            if(curr.val > p.val){
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        // 如果目标节点有右节点，则找到其右节点的最左边的节点，就是下一个数
        if(curr.right != null){
            curr = curr.right;
            while(curr.left != null){
                curr = curr.left;
            }
            return curr;
        } else {
        // 如果没有右节点，pop栈找到第一个比目标节点大的节点
            while(!stk.isEmpty() && stk.peek().val < curr.val){
                stk.pop();
            }
            // 如果栈都pop空了还没有比目标节点大的，说明没有更大的了
            return stk.isEmpty() ? null : stk.pop();
        }
    }
	
	/**
	 * 复杂度
O(H) 时间 O(H) 空间

思路
这道题给了BST和p，找p的后继，两种情况：

p有右孩子：后继为右孩子的最左孩子
p无右孩子：后继为离它最近的第一个大于他的爹，他的爹中如果没有大于它的，返回Null
情况1比较简单，情况2用栈记录下他的爹们

注意
一开始当成了二叉树找后继，可是节点没有parent指针，一般这种题会有一个方便找爹的线索，不然就是纯搜索问题了。果然仔细看了题是二叉搜索树，这样就可以更方便的找爹了。如果不是二叉搜索树，只是一个二叉树的话，其实也并不难，跟上面的情况分析基本不变，只是当p没有有右孩子的时候，要一直找找到一个爹使得当前节点作为它的左孩子，这个爹就是要找的后继。
另外这道题循环不定式要定义清楚，请看代码注释
	 * @param args
	 */
	
	public TreeNode inorderSuccessor_2(TreeNode root, TreeNode p) {
        if (p.right != null) {
            return findLeftMost(p.right);
        }
        else {
            Stack<TreeNode> stk = new Stack<TreeNode>();
            while (root != p) {
                stk.push(root);
                if (root.val < p.val)
                    root = root.right;
                else if (root.val > p.val)
                    root = root.left;
            }
            if (stk.isEmpty())  //root == p, no successor
                return null;
            //as long as we get here, stack has at least one element
            TreeNode parent = stk.pop();    //let parent be the nearest parent
            while (!stk.isEmpty() && parent.val <= p.val) { 
                parent = stk.pop();
            }
            if (parent.val > p.val) //finally find one bigger than p.val
                return parent;
            return null;    //no one bigger than p.val, it must be stack is empty
            
        }
    }
    public TreeNode findLeftMost(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
