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
     *  * һ����ȫ����node��parentָ�롣
     ÿ��node��ֵΪ 0�� 1
     ÿ��parent��ֵΪ������node�� ��and�� ���
     ���ڰ�һ��leaf�����ӣ�0��1����1��0��. 留学申请论坛-丢�亩三分地
     ��������һ��
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

    //������O(1)������ǣ�logN��
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
     number of Island�ĳ���
     ����һ����άchar���ٸ�һ������
     �����꿪ʼ
     �ҳ��������ӣ��������ң�����ͬchar
     ��󷵻�����󵺵����. 1point 3acres 论坛
     �������и��澭�Ƿ�������󵺵��ܳ����о�������Ǽ��ˡ�
     */



}
