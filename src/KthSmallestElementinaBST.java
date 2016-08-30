import java.util.*;


/*
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 �� k �� BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
 * 
 * 
 * ����һ�ö�����������BST������дһ������kthSmallest�ҳ����е�kС��Ԫ�ء�

ע�⣺

����Լ���k������Ч�ģ� 1 �� k �� BST��Ԫ��������

��һ��˼����

���BST���޸ģ�����/ɾ��������ʮ��Ƶ����������ҪƵ�����ҳ���kС��Ԫ�أ�Ӧ�������Ż�kthSmallest������

��ʾ��

��������BST�����ԡ�

���������޸�BST�ڵ�Ľṹʱ��Ӧ����������

����ʱ�临�Ӷ�Ӧ����O(BST�ĸ߶�)��

����˼·��
BST�����������ʣ�

������������Ԫ�ص�ֵ��С�ڸ��ڵ��ֵ

������������Ԫ�ص�ֵ�����ڸ��ڵ��ֵ

��˲�������������� -> �� -> �ң������Ե���˳�����BST�еĽڵ㣬�Ӷ��õ���kС��Ԫ�أ�ʱ�临�Ӷ�O(k)


1.�ռ任ʱ�� 
BST�������ǣ���������������У��õ��ĵ��������Կ���ʹ��һ��stack�������������ֱ���ҵ���K��Ԫ�أ� 
2. �����Ľ���� 
����ÿ���ڵ�root����������Ϊ���ڵ�����Ľڵ���������S�� 
���S==K������root->val; 
���S > K����root��������������ҵ�KСԪ�أ� 
���S

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
