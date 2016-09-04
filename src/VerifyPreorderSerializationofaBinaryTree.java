import java.util.Stack;

/**
 * Created by cicean on 8/30/2016.
 * 331. Verify Preorder Serialization of a Binary Tree  QuestionEditorial Solution  My Submissions
 Total Accepted: 20015 Total Submissions: 59828 Difficulty: Medium
 One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

 _9_
 /   \
 3     2
 / \   / \
 4   1  #  6
 / \ / \   / \
 # # # #   # #
 For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

 Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

 Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

 You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

 Example 1:
 "9,3,4,#,#,1,#,#,2,#,6,#,#"
 Return true

 Example 2:
 "1,#"
 Return false

 Example 3:
 "9,#,#,1"
 Return false

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Stack
 ���⣺������������ǰ�������Ҫ���ڲ�������������£��ж����Ƿ�Ϸ���

 ��ʵ��һ��google������⣬֮ǰ��һĶ���ֵؿ�����
 */
public class VerifyPreorderSerializationofaBinaryTree {

    /**
     * ˼·��

     ջ
     ��Ⱥͳ��ȵĲ�
     ջ

     ��������򵥵�˵���ǲ��ϵĿ���Ҷ�ӽڵ㡣��󿴿��ܲ���ȫ��������

     ������һΪ��������9,3,4,#,#,1,#,#,2,#,6,#,#�� ����x # #��ʱ�򣬾Ͱ�����Ϊ #

     ��ģ��һ����̣�

     9,3,4,#,# => 9,3,# ������
     9,3,#,1,#,# => 9,3,#,# => 9,# ������
     9,#2,#,6,#,# => 9,#,2,#,# => 9,#,# => #
     */
    //Time complexity is O(n), space is also O(n) for the stack.
    public class Solution {
        public boolean isValidSerialization(String preorder) {
            // using a stack, scan left to right
            // case 1: we see a number, just push it to the stack
            // case 2: we see #, check if the top of stack is also #
            // if so, pop #, pop the number in a while loop, until top of stack is not #
            // if not, push it to stack
            // in the end, check if stack size is 1, and stack top is #
            if (preorder == null) {
                return false;
            }
            Stack<String> st = new Stack<>();
            String[] strs = preorder.split(",");
            for (int pos = 0; pos < strs.length; pos++) {
                String curr = strs[pos];
                while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                    st.pop();
                    if (st.isEmpty()) {
                        return false;
                    }
                    st.pop();
                }
                st.push(curr);
            }
            return st.size() == 1 && st.peek().equals("#");
        }
    }

    /**
     * ��Ⱥͳ��ȵĲ�

     ���� dietpepsi �Ĵ��룬ȷʵ˼·��������ĸ�ʤһ�

     In a binary tree, if we consider null as leaves, then

     all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
     all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
     Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we increase diff by2, because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will be zero when finished.

     from :https://leetcode.com/discuss/83824/7-lines-easy-java-solution

     �����﷭��һ�£�

     ���ڶ����������ǰѿյĵط�Ҳ��ΪҶ�ӽڵ㣨����Ŀ�е�#������ô��

     ���еķǿսڵ��ṩ2�����Ⱥ�1����ȣ������⣩
     ���еĿսڵ㵫�ṩ0�����Ⱥ�1�����
     �����ڱ�����ʱ�򣬼���diff = outdegree �C indegree. ��һ���ڵ���ֵ�ʱ��diff �C 1����Ϊ���ṩһ����ȣ����ڵ㲻��#��ʱ��diff+2(�ṩ��������) �������ʽ�Ϸ��ģ���ô����������diff >=0 �������Ϊ0.
     */
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node: nodes) {
            if (--diff < 0) return false;
            if (!node.equals("#")) diff += 2;
        }
        return diff == 0;
    }

}
