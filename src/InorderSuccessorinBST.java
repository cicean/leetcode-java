import java.util.*;

/**
 * 285	Inorder Successor in BST 	33.3%	Medium
 * Problem Description:
 * <p>
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * <p>
 * Note: If the given node has no in-order successor in the tree, return null.
 * <p>
 * There are just two cases:
 * <p>
 * The easier one: p has right subtree, then its successor is just the leftmost child of its right subtree;
 * The harder one: p has no right subtree, then a traversal is needed to find its successor.
 * Traversal: we start from the root, each time we see a node with val larger than p -> val, we know this node may be p's successor. So we record it in suc. Then we try to move to the next level of the tree: if p -> val > root -> val, which means p is in the right subtree, then its successor is also in the right subtree, so we update root = root -> right; if p -> val < root -> val, we update root = root -> left similarly; once we find p -> val == root -> val, we know we've reached at p and the current sucis just its successor.
 * <p>
 * The code is as follows. You may try some examples to see how it works :-)
 *
 * @author cicean
 */
public class InorderSuccessorinBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode prev = null;
        while (root != p)
            root = p.val < root.val ? (prev = root).left : root.right;
        for (root = root.right; root != null; root = (prev = root).left) ;
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
     * ���Ӷ�
     * ʱ�� O(N) �ռ� O(N)
     * <p>
     * ˼·
     * ��Ŀ�������ڵ��Ŀ��ڵ㡣Ŀ��ڵ�������ҽڵ������ȽϺô���
     * ����ֻҪ���������ҽڵ������ߵĽڵ�����ˣ��ҽڵ��Լ�û����ڵ�ʱ�����ҽڵ㱾����
     * ���Ŀ��ڵ�û���ҽڵ㣬˵����Ŀ��ڵ��Դ�Ľڵ�Ӧ�������棬��Ϊ����֪��Ŀ��ڵ����ڵ�϶��Ǳ�Ŀ��ڵ�ҪС�ġ�
     * <p>
     * ����ô֪��Ŀ��ڵ��������ʲô�أ���ʱ����Ҫ�Ӹ��ڵ�������Ŀ��ڵ��ˡ�
     * ����Ҫע����ǣ�Ŀ��ڵ�ĸ��ײ�һ����Ŀ��ڵ����Ϊ�п���Ŀ��ڵ�����丸�׵��Һ��ӡ�
     * ��������Ҫ�ҵ����棬ʵ�����Ǵ�Ŀ��ڵ�����ڵ����ʱ����һ����Ŀ��ڵ��Ľڵ㡣
     * ��������£�������ݵ����ڵ㻹�Ǳ�Ŀ��ڵ�ҪС�Ļ���˵��Ŀ��ڵ�����������������ˣ���ʱ�򷵻ؿա�
     * <p>
     * ����ôʵ��Ŀ��ڵ�����ڵ���ݣ�������һ��ջ�����ˣ�������Ѱ��Ŀ��ڵ�ʱ����·���ϵĽڵ㶼ѹ��ջ�С�
     *
     * @param
     */

    public TreeNode inorderSuccessor_1(TreeNode root, TreeNode p) {
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode curr = root;
        // �ҵ�Ŀ��ڵ㲢��·���ϵĽڵ�ѹ��ջ
        while (curr != p) {
            stk.push(curr);
            if (curr.val > p.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        // ���Ŀ��ڵ����ҽڵ㣬���ҵ����ҽڵ������ߵĽڵ㣬������һ����
        if (curr.right != null) {
            curr = curr.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        } else {
            // ���û���ҽڵ㣬popջ�ҵ���һ����Ŀ��ڵ��Ľڵ�
            while (!stk.isEmpty() && stk.peek().val < curr.val) {
                stk.pop();
            }
            // ���ջ��pop���˻�û�б�Ŀ��ڵ��ģ�˵��û�и������
            return stk.isEmpty() ? null : stk.pop();
        }
    }

    /**
     * ���Ӷ�
     * O(H) ʱ�� O(H) �ռ�
     * <p>
     * ˼·
     * ��������BST��p����p�ĺ�̣����������
     * <p>
     * p���Һ��ӣ����Ϊ�Һ��ӵ�������
     * p���Һ��ӣ����Ϊ��������ĵ�һ���������ĵ������ĵ������û�д������ģ�����Null
     * ���1�Ƚϼ򵥣����2��ջ��¼�����ĵ���
     * <p>
     * ע��
     * һ��ʼ�����˶������Һ�̣����ǽڵ�û��parentָ�룬һ�����������һ�������ҵ�����������Ȼ���Ǵ����������ˡ���Ȼ��ϸ�������Ƕ����������������Ϳ��Ը�������ҵ��ˡ�������Ƕ�����������ֻ��һ���������Ļ�����ʵҲ�����ѣ����������������������䣬ֻ�ǵ�pû�����Һ��ӵ�ʱ��Ҫһֱ���ҵ�һ����ʹ�õ�ǰ�ڵ���Ϊ�������ӣ����������Ҫ�ҵĺ�̡�
     * ���������ѭ������ʽҪ����������뿴����ע��
     *
     * @param args
     */

    public TreeNode inorderSuccessor_2(TreeNode root, TreeNode p) {
        if (p.right != null) {
            return findLeftMost(p.right);
        } else {
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


    // pocket gems �澭

    public CustomTreeNode findNext(CustomTreeNode node) {
        if (node == null) return node;
      
        CustomTreeNode root = node;
        while (root.parent != null) {
            root = root.parent;
            System.out.println("root val = " + root.val);
        }
        List<CustomTreeNode> res = new ArrayList<>();
        Stack<CustomTreeNode> stk = new Stack<>();
        while (!stk.isEmpty() || root != null) {
            if (root != null) {
                stk.push(root);
                root = root.left;
            } else {
                root = stk.pop();
                	
                if (res.size() > 0 && res.get(res.size() - 1) == node ) return root;

                res.add(root);
                root = root.right;
            }
        }
        return null;


    }

    public CustomTreeNode findNext2(CustomTreeNode node) {
        if (node == null) return null;

        if (node.right != null) {
            return minValue(node.right);
        }

        CustomTreeNode parent = node.parent;
        while (parent != null && node.val > parent.val) {
            parent = parent.parent;
        }

        return parent;

    }

    private CustomTreeNode minValue(CustomTreeNode node) {
        CustomTreeNode cur = node;
        while (cur.left != null) {
            cur = cur.left;
        }

        return cur;
    }





    public static void main(String[] args) {
        // TODO Auto-generated method stub

    	TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		 t1.right = t2;
		 t2.left = t3;
		 
		 CustomTreeNode root = new CustomTreeNode(4);
		 CustomTreeNode l1 = new CustomTreeNode(3);
		 CustomTreeNode l2= new CustomTreeNode(2);
		 CustomTreeNode l3 = new CustomTreeNode(1);
		 CustomTreeNode r1 = new CustomTreeNode(5);
		 CustomTreeNode r2 = new CustomTreeNode(6);
		 CustomTreeNode r3 = new CustomTreeNode(7);
		 
		 root.left = l2;
		 root.right = r2;
		 l2.left = l3;
		 l2.right = l1;
		 l2.parent = root;
		 r2.left = r1;
		 r2.right = r3;
		 r2.parent = root;
		 l1.parent = l2;
		 l3.parent = l2;
		 r1.parent = r2;
		 r3.parent = r2;
		 
		 
		 InorderSuccessorinBST slt = new InorderSuccessorinBST();
		
		 CustomTreeNode test = slt.findNext2(root);
		System.out.print("input Node val = " + root.val + "findNext Node val = " + (test == null ? "null" : test.val));
    }

}

class CustomTreeNode {
    public int val;
    public CustomTreeNode left;
    public CustomTreeNode right;
    public CustomTreeNode parent;

    public CustomTreeNode(int x) {
        this.val = x;
    }
}
