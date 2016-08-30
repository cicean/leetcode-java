import java.util.*;


/*
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
 * 
 * 
 * 给定一棵二叉搜索树（BST），编写一个函数kthSmallest找出其中第k小的元素。

注意：

你可以假设k总是有效的， 1 ≤ k ≤ BST的元素总数。

进一步思考：

如果BST的修改（插入/删除）操作十分频繁，并且需要频繁地找出第k小的元素，应该怎样优化kthSmallest函数？

提示：

尝试利用BST的属性。

如果你可以修改BST节点的结构时，应该怎样做？

最优时间复杂度应该是O(BST的高度)。

解题思路：
BST具有如下性质：

左子树中所有元素的值均小于根节点的值

右子树中所有元素的值均大于根节点的值

因此采用中序遍历（左 -> 根 -> 右）即可以递增顺序访问BST中的节点，从而得到第k小的元素，时间复杂度O(k)


1.空间换时间 
BST的特性是，如果按照中序排列，得到的递增序；所以可以使用一个stack进行中序遍历，直到找到第K个元素； 
2. 树树的结点数 
对于每个节点root，计算以它为根节点的树的节点数，计作S。 
如果S==K，返回root->val; 
如果S > K，在root的左子树里面查找第K小元素； 
如果S

 * 
 */

public class KthSmallestElementinaBST {
	
	Queue<TreeNode> q = new LinkedList<TreeNode>();

	public int kthSmallest(TreeNode root, int k) {
	    inorder(root);
	    int result = 0;
	    TreeNode tmp = null;
	    while(k != 0){
	        tmp = q.poll();
	        k--;
	    }

	    result = tmp.val;
	    return result;
	}

	private void inorder(TreeNode root){
	    if(root != null){
	        inorder(root.left);
	        q.offer(root);
	        inorder(root.right);
	    }
	}

	
	public int kthSmallest_1(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode p=root;
        s.push(p);
        int cnt=0;
        do{
            while(p!=null){
                s.push(p);
                p=p.left;
            }
            TreeNode top=s.pop();
            cnt++;
            if(cnt==k){
                return top.val;
            }
            p=top.right;
        }while(!s.empty());
        return 0;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t1 = new TreeNode(3);
		TreeNode t2 = new TreeNode(9);
		TreeNode t3 = new TreeNode(20);
		TreeNode t4 = new TreeNode(15);
		TreeNode t5 = new TreeNode(7);
		t1.left = t2;
		t1.right = t3;
		t3.left = t4;
		t3.right = t5;
		
		int k = 4;
		
		KthSmallestElementinaBST slt = new KthSmallestElementinaBST();
	
		System.out.println(slt.kthSmallest(t1, k));
	
	}

}
