import java.util.*;

/**
 * Created by cicean on 9/1/2016.
 * 366. Find Leaves of Binary Tree  QuestionEditorial Solution  My Submissions
 Total Accepted: 4542 Total Submissions: 8357 Difficulty: Medium
 Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 Example:
 Given binary tree
 1
 / \
 2   3
 / \
 4   5
 Returns [4, 5, 3], [2], [1].

 Explanation:
 1. Removing the leaves [4, 5, 3] would result in this tree:

 1
 /
 2
 2. Now removing the leaf [2] would result in this tree:

 1
 3. Now removing the leaf [1] would result in the empty tree:

 []
 Returns [4, 5, 3], [2], [1].

 Credits:
 Special thanks to @elmirap for adding this problem and creating all test cases.

 Hide Company Tags LinkedIn
 Hide Tags Tree Depth-first Search

 */
public class FindLeavesofBinaryTree {

    /**
     * �ݹ�
     ���Ӷ�
     O(N) ʱ�� O(N) �ռ�

     ˼·
     ����⻻�仰˵������ÿ��node��index�����index���������������ڵ����ڵ�list��index������4,5,3��index��0�� 2��index��1��1��index��2.
     ��ô���أ����֣����󣬿��ҡ�
     ȷ��һ�����index����֪����������index�Ƕ��٣��Һ��ӵ�index�Ƕ��٣���ǰ������index�������Һ��ӵ�index���ֵ+1������Ժ����׵ع۲�õ����������1��˵������2��index��1���Һ���3��index��0����1��index�϶���max(1, 0) + 1����2.

     ע��
     if (list.size() == cur) {
     list.add(new ArrayList<Integer>());
     }
     ��δ���Ϊʲô������ôд��
     ��Ϊͨ�����ٵݹ���Է��֣�����index���������ģ�û����Ծд�������һ������������0����1����2����0����1����2����3�����������0����2����1����4���������
     DFS
     */

    public class Solution {
        public List<List<Integer>> findLeaves(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            helper(list, root);
            return list;
        }

        //calculate the index of this root passed in and put it in that index, at last return where this root was put
        private int helper(List<List<Integer>> list, TreeNode root) {
            if (root == null)
                return -1;
            int left = helper(list, root.left);
            int right = helper(list, root.right);
            int cur = Math.max(left, right) + 1;
            if (list.size() == cur)
                list.add(new ArrayList<Integer>());
            list.get(cur).add(root.val);
            return cur;
        }
    }
}
