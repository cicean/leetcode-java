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
 题意：给定二叉树的前序遍历，要你在不构造树的情况下，判断树是否合法。

 其实是一道google电面的题，之前在一亩三分地看过。
 */
public class VerifyPreorderSerializationofaBinaryTree {

    /**
     * 思路：

     栈
     入度和出度的差
     栈

     这个方法简单的说就是不断的砍掉叶子节点。最后看看能不能全部砍掉。

     已例子一为例，：”9,3,4,#,#,1,#,#,2,#,6,#,#” 遇到x # #的时候，就把它变为 #

     我模拟一遍过程：

     9,3,4,#,# => 9,3,# 继续读
     9,3,#,1,#,# => 9,3,#,# => 9,# 继续读
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
     * 入度和出度的差

     看了 dietpepsi 的代码，确实思路比我上面的更胜一筹：

     In a binary tree, if we consider null as leaves, then

     all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
     all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
     Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we increase diff by2, because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will be zero when finished.

     from :https://leetcode.com/discuss/83824/7-lines-easy-java-solution

     我这里翻译一下：

     对于二叉树，我们把空的地方也作为叶子节点（如题目中的#），那么有

     所有的非空节点提供2个出度和1个入度（根除外）
     所有的空节点但提供0个出度和1个入度
     我们在遍历的时候，计算diff = outdegree – indegree. 当一个节点出现的时候，diff – 1，因为它提供一个入度；当节点不是#的时候，diff+2(提供两个出度) 如果序列式合法的，那么遍历过程中diff >=0 且最后结果为0.
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
