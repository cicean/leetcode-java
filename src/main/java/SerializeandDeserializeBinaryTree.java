import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 297. Serialize and Deserialize Binary Tree
 * Total Accepted: 27936 Total Submissions: 94625 Difficulty: Hard
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following tree
 * 
 * 1 / \ 2 3 / \ 4 5 as "[1,2,3,null,null,4,5]", just the same as how LeetCode
 * OJ serializes a binary tree. You do not necessarily need to follow this
 * format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
 * 
 * Credits:
 * 
 * @author cicean
 *
 *         ��ʵLeetCode�����ı�ʾ��ʽ��ͦ�ã���"[1,2,3,null,null,4,5]"������ʽ�����ǽ�������ʵ�������������л���
 * 
 *         ���л��Ƚ����ף�������һ����α����ͺã��յĵط���null��ʾ����΢��ͬ�ĵط�����Ŀ��ʾ���õ��Ľ����
 *         "[1,2,3,null,null,4,5,null,null,null,null,]"���� 4 �� 5 �������սڵ�����Ҳ����������
 * 
 *         �����л�ʱ�����Ǹ��ݶ��÷ָ�õ�ÿ���ڵ㡣��Ҫע����ǣ������л�ʱ���Ѱ�Ҹ��ڵ����ӽڵ�Ķ�Ӧ��ϵ������֪���������У��������������
 *         ����ȫ���������ĸ��ڵ��±��� i����ô�����Һ��ӵ��±�ֱ�Ϊ 2*i+1 ��
 *         2*i+2���������ﲢ��һ������������������ȫ�������������������Ӧ��ϵ��Ҫ�����޸ġ�������������ӣ�
 * 
 *         5 / \ 4 7 / / 3 2 / / -1 9
 *         ���л����Ϊ[5,4,7,3,null,2,null,-1,null,9,null,null,null,null,null,]��
 * 
 *         ���У��ڵ� 2 ���±��� 5������������ 9 ���±�Ϊ 9�������� 2*i+1=11��ԭ������ ǰ���и� null �ڵ㣬��� null
 *         �ڵ�û�����Һ��ӣ����Ժ���Ľڵ��±궼��ǰ��2����������ֻ��Ҫ��¼ÿ���ڵ�ǰ�ж��ٸ� null
 *         �ڵ㣬�Ϳ����ҳ��ýڵ�ĺ����������ˣ������Һ��ӷֱ�Ϊ 2*(i-num)+1 �� 2*(i-num)+2��numΪ��ǰ�ڵ�֮ǰ null
 *         �ڵ�ĸ�������b
 */
public class SerializeandDeserializeBinaryTree {

	/**
	 * This method will be invoked first, you should design your own algorithm
	 * to serialize a binary tree which denote by a root node to a string which
	 * can be easily deserialized by your own "deserialize" method later.
	 */
	public String serialize(TreeNode root) {
		if (root == null) {
			return "{}";
		}

		ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
		queue.add(root);

		for (int i = 0; i < queue.size(); i++) {
			TreeNode node = queue.get(i);
			if (node == null) {
				continue;
			}
			queue.add(node.left);
			queue.add(node.right);
		}

		while (queue.get(queue.size() - 1) == null) {
			queue.remove(queue.size() - 1);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(queue.get(0).val);
		for (int i = 1; i < queue.size(); i++) {
			if (queue.get(i) == null) {
				sb.append(",#");
			} else {
				sb.append(",");
				sb.append(queue.get(i).val);
			}
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * This method will be invoked second, the argument data is what exactly you
	 * serialized at method "serialize", that means the data is not given by
	 * system, it's given by your own serialize method. So the format of data is
	 * designed by yourself, and deserialize it here as you serialize it in
	 * "serialize" method.
	 */
	public TreeNode deserialize(String data) {
		if (data.equals("{}")) {
			return null;
		}
		String[] vals = data.substring(1, data.length() - 1).split(",");
		data.
		ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
		TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
		queue.add(root);
		int index = 0;
		boolean isLeftChild = true;
		for (int i = 1; i < vals.length; i++) {
			if (!vals[i].equals("#")) {
				TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
				if (isLeftChild) {
					queue.get(index).left = node;
				} else {
					queue.get(index).right = node;
				}
				queue.add(node);
			}
			if (!isLeftChild) {
				index++;
			}
			isLeftChild = !isLeftChild;
		}
		return root;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

// recursive
class Solution {
	/**
	 * This method will be invoked first, you should design your own algorithm
	 * to serialize a binary tree which denote by a root node to a string which
	 * can be easily deserialized by your own "deserialize" method later.
	 */
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		if (root == null)
			return sb.toString();

		seriaHelper(root, sb);

		return sb.substring(0, sb.length() - 1);
	}

	private void seriaHelper(TreeNode root, StringBuilder sb) {
		if (root == null) {
			sb.append("#,");
		} else {
			sb.append(root.val).append(",");
			seriaHelper(root.left, sb);
			seriaHelper(root.right, sb);
		}
	}

	/**
	 * This method will be invoked second, the argument data is what exactly you
	 * serialized at method "serialize", that means the data is not given by
	 * system, it's given by your own serialize method. So the format of data is
	 * designed by yourself, and deserialize it here as you serialize it in
	 * "serialize" method.
	 */
	public TreeNode deserialize(String data) {
		if (data == null || data.length() == 0)
			return null;

		StringTokenizer st = new StringTokenizer(data, ",");
		return deseriaHelper(st);
	}

	private TreeNode deseriaHelper(StringTokenizer st) {
		if (!st.hasMoreTokens())
			return null;

		String val = st.nextToken();
		if (val.equals("#")) {
			return null;
		}

		TreeNode root = new TreeNode(Integer.parseInt(val));
		root.left = deseriaHelper(st);
		root.right = deseriaHelper(st);

		return root;
	}
}
