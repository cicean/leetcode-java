/**
 * Created by cicean on 8/29/2016.
 * FaceBook
 *  Dot Product.
 A={a1, a2, a3,...}. 1point3acres.com/bbs
 B={b1, b2, b3,...}
 dot_product = a1 * b1 + a2 * b2 + a3 * b3 ＋。。。
 如果数组很稀疏，例如
 A={a1, ...., a300, ...., a5000}
 B={...., b100, ..., b300, ..., b1000, ...}. 1point3acres.com/bbs
 里面有很多0，用什么数据结构表示能节省空间。我开始说用hashmap，但是hashmap不能做有顺序的iteration。然后改用list和array，两个都可以。后面做题的时用的array。
 题目是：
 input A=[[1, a1], [300, a300], [5000, a5000]]. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
 B=[[100, b100], [300, b300], [1000, b1000]]
 求 dot product. 问了时间复杂度。
 Follow up:
 如果length(B) >>> length(A)，即B非常长，怎么做能减少时间复杂度。对A里面的每个数，用binary search找B中相对应的值，这样时间复杂度是O(nlogm) (n = len(A), m =len(B)).时间不够没写代码， 就说了一下思路和复杂度。.1point3acres缃?
 */
public class DotProductofSparseVectors {

    double[] elemsTable;
    int usedGeneration = 0;
    int[] whenUsed;

    public double DotProductofSparseVectors(Interval[] A, Interval[] B) {
        if (A.length == 0 || B.length == 0) return 0;
        int len = A[A.length - 1].start < B[B.length - 1].start ? A[A.length - 1].start : B[B.length - 1].start;
        elemsTable = new double[ len+ 1];
        whenUsed = new int[len + 1];
        usedGeneration++;
        for (int i = 0; i < A.length; i++) {
            elemsTable[A[i].start] =A[i].end;
            whenUsed[A[i].start] = usedGeneration;
        }

        double dotP = 0.0;
        for (int i = 0; i < B.length; i++) {
            if (whenUsed[B[i].start] == usedGeneration) {
                dotP += elemsTable[B[i].start] * B[i].end;
            }
        }
        return dotP;
    }



}
