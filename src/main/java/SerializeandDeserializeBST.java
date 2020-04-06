import java.util.*;

/**
 * 449. Serialize and Deserialize BST
 * DescriptionHintsSubmissionsDiscussSolution
 * Discuss Pick One
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * The encoded string should be as compact as possible.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

public class SerializeandDeserializeBST {

    /**
     * Contest
     * Articles
     * Discuss
     * •
     * Store
     * Sign up Sign in
     * 449. Serialize and Deserialize BST
     * DescriptionHintsSubmissionsDiscussSolution
     * Java PreOrder + Queue solution
     * Hi all, I think my solution is pretty straightforward and easy to understand, not that efficient though. And the serialized tree is compact.
     * Pre order traversal of BST will output root node first, then left children, then right.
     * <p>
     * root left1 left2 leftX right1 rightX
     * If we look at the value of the pre-order traversal we get this:
     * <p>
     * rootValue (<rootValue) (<rootValue) (<rootValue) |separate line| (>rootValue) (>rootValue)
     * Because of BST's property: before the |separate line| all the node values are less than root value, all the node values after |separate line| are greater than root value. We will utilize this to build left and right tree.
     * <p>
     * Pre-order traversal is BST's serialized string. I am doing it iteratively.
     * To deserialized it, use a queue to recursively get root node, left subtree and right subtree.
     * <p>
     * I think time complexity is O(NlogN).
     * Errr, my bad, as @ray050899 put below, worst case complexity should be O(N^2), when the tree is really unbalanced.
     * <p>
     * My implementation
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

    /**
     * Solution
     * How to make the encoded string as compact as possible
     * This question is similar to the Google interview question discussed last week.
     * <p>
     * To serialize a binary tree means to
     * <p>
     * Encode tree structure.
     * <p>
     * Encode node values.
     * <p>
     * Choose delimiters to separate the values in the encoded string.
     * <p>
     * bla
     * <p>
     * Hence there are three axes of optimisation here.
     * <p>
     * <p>
     * Approach 1: Postorder traversal to optimise space for the tree structure.
     * Intuition
     * <p>
     * Let's use here the fact that BST could be constructed from preorder or postorder traversal only. Please check this article for the detailed discussion. In brief, it's a consequence of two facts:
     * <p>
     * Binary tree could be constructed from preorder/postorder and inorder traversal.
     * <p>
     * Inorder traversal of BST is an array sorted in the ascending order: inorder = sorted(preorder).
     * <p>
     * That means that BST structure is already encoded in the preorder or postorder traversal and hence they are both suitable for the compact serialization.
     * <p>
     * Serialization could be easily implemented with both strategies, but for optimal deserialization better to choose the postorder traversal because member/global/static variables are not allowed here.
     * <p>
     * pic
     * <p>
     * Implementation
     * <p>
     * <p>
     * Complexity Analysis
     * <p>
     * Time complexity : \mathcal{O}(N)O(N) both for serialization and deserialization. Let's compute the solution with the help of master theorem T(N) = aT\left(\frac{b}{N}\right) + \Theta(N^d)T(N)=aT(
     * N
     * b
     * ​
     * )+Θ(N
     * d
     * ). The equation represents dividing the problem up into aa subproblems of size \frac{N}{b}
     * b
     * N
     * ​
     * in \Theta(N^d)Θ(N
     * d
     * ) time. Here one divides the problem in two subproblemes a = 2, the size of each subproblem (to compute left and right subtree) is a half of initial problem b = 2, and all this happens in a constant time d = 0. That means that \log_b(a) > dlog
     * b
     * ​
     * (a)>d and hence we're dealing with case 1 that means \mathcal{O}(N^{\log_b(a)}) = \mathcal{O}(N)O(N
     * log
     * b
     * ​
     * (a)
     * )=O(N) time complexity.
     * <p>
     * Space complexity : \mathcal{O}(N)O(N), since we store the entire tree. Encoded string: one needs to store (N - 1)(N−1) delimiters, and NN node values in the encoded string. Tree structure is encoded in the order of values and uses no space.
     */

    public class Codec {
        public StringBuilder postorder(TreeNode root, StringBuilder sb) {
            if (root == null) return sb;
            postorder(root.left, sb);
            postorder(root.right, sb);
            sb.append(root.val);
            sb.append(' ');
            return sb;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = postorder(root, new StringBuilder());
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        public TreeNode helper(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
            if (nums.isEmpty()) return null;
            int val = nums.getLast();
            if (val < lower || val > upper) return null;

            nums.removeLast();
            TreeNode root = new TreeNode(val);
            root.right = helper(val, upper, nums);
            root.left = helper(lower, val, nums);
            return root;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
            for (String s : data.split("\\s+"))
                nums.add(Integer.valueOf(s));
            return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
        }
    }

    /** Approach 2: Convert int to 4-bytes string to optimise space for node values.
     * Intuition
     *
     * Approach 1 works fine with the small node values but starts to consume more and more space in the case of large ones.
     *
     * For example, the tree [2,null,3,null,4] is encoded as a string "4 3 2" which uses 5 bytes to store the values and delimiters, 1 byte per value or delimiter. So far everything is fine.
     *
     * Let's consider now the tree [12345,null,12346,null,12347] which is encoded as "12347 12346 12345" and consumes 17 bytes to store 3 integers and 2 delimiters, 15 bytes for node values only. At the same time it's known that 4 bytes is enough to store an int value, i.e. 12 bytes should be enough for 3 integers. 15 > 12 and hence the storage of values could be optimised.
     *
     * How to do it? Convert each integer into 4-bytes string.
     *
     * pic2
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) both for serialization and deserialization.
     *
     * Space complexity : \mathcal{O}(N)O(N), since we store the entire tree. Encoded string: one needs 2(N - 1)2(N−1) bytes for the delimiters, and 4 N4N bytes for the node values in the encoded string. Tree structure is encoded in the order of node values and uses no space.
     *
     */


    /**
     * Approach 3: Get rid of delimiters.
     * Intuition
     * <p>
     * Approach 2 works well except for delimiter usage.
     * <p>
     * Since all node values are now encoded as 4-bytes strings, one could just split the encoded string into 4-bytes chunks, convert each chunk back to the integer and proceed further.
     * <p>
     * pic3
     * <p>
     * Implementation
     * <p>
     * <p>
     * Complexity Analysis
     * <p>
     * Time complexity : \mathcal{O}(N)O(N) both for serialization and deserialization.
     * <p>
     * Space complexity : \mathcal{O}(N)O(N), since we store the entire tree. Encoded string: no delimiters, no additional space for the tree structure, just 4 N4N bytes for the node values in the encoded string.
     * <p>
     * Analysis written by @liaison and @andvary
     */

    public class Codec_1 {
        // Encodes a tree to a list.
        public void postorder(TreeNode root, StringBuilder sb) {
            if (root == null) return;
            postorder(root.left, sb);
            postorder(root.right, sb);
            sb.append(intToString(root.val));
        }

        // Encodes integer to bytes string
        public String intToString(int x) {
            char[] bytes = new char[4];
            for (int i = 3; i > -1; --i) {
                bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
            }
            return new String(bytes);
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            postorder(root, sb);
            return sb.toString();
        }

        // Decodes list to tree.
        public TreeNode helper(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
            if (nums.isEmpty()) return null;
            int val = nums.getLast();
            if (val < lower || val > upper) return null;

            nums.removeLast();
            TreeNode root = new TreeNode(val);
            root.right = helper(val, upper, nums);
            root.left = helper(lower, val, nums);
            return root;
        }

        // Decodes bytes string to integer
        public int stringToInt(String bytesStr) {
            int result = 0;
            for (char b : bytesStr.toCharArray()) {
                result = (result << 8) + (int) b;
            }
            return result;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
            int n = data.length();
            for (int i = 0; i < (int) (n / 4); ++i) {
                nums.add(stringToInt(data.substring(4 * i, 4 * i + 4)));
            }

            return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class Codec_2 {

        private static final String NN = "#";
        private static final String Spliter = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder res = new StringBuilder();
            dfsSerialize(root, res);
            return res.toString();
        }

        private void dfsSerialize(TreeNode root, StringBuilder res) {
            if (root == null) {
                return;
            }
            res.append(root.val);
            res.append(Spliter);
            dfsSerialize(root.left, res);
            dfsSerialize(root.right, res);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] dataArr = data.split(Spliter);
            int[] idx = new int[1];
            return buildTree(dataArr, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode buildTree(String[] dataArr, int[] idx, int min, int max) {
            if (idx[0] == dataArr.length) {
                return null;
            }
            int val = Integer.parseInt(dataArr[idx[0]]);
            if (val < min || val > max) {
                return null;
            }
            idx[0]++;
            TreeNode root = new TreeNode(val);
            root.left = buildTree(dataArr, idx, min, val);
            root.right = buildTree(dataArr, idx, val, max);
            return root;
        }
    }

}
