package facebook;

/**
 * Phone interview
 * 9/1/2016
 */

/**
 * Created by cicean on 9/2/2016.
 */
public class facebook09012016 {

    /**
     *  * 一个完全树。node有parent指针。
     每个node的值为 0或 1
     每个parent的值为两个子node的 “and” 结果
     现在把一个leaf翻牌子（0变1或者1变0）. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
     把树修正一遍
     */

    class TreeNode {

        TreeNode parent;

        TreeNode left;

        TreeNode right;

        int val;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    //最优是O(1)，最差是（logN）
    public void flipTree(TreeNode leaf) {

            while (leaf != null) {
                leaf.val ^= 1;
                if (leaf.parent != null) {
                    if (leaf.parent.left == leaf) {
                        if (leaf.parent.right.val == 0) break;
                    } else {
                        if (leaf.parent.left.val == 0) break;
                    }
                }
                leaf = leaf.parent;
            }
        }

    /**
     * 2.
     number of Island的超简化
     给出一个二维char表，再给一个坐标
     从坐标开始
     找出所有连接（上下左右）的相同char
     最后返回这个大岛的面积. 1point 3acres 璁哄潧
     【地里有个面经是返回这个大岛的周长，感觉我这个是简化了】
     */



}
