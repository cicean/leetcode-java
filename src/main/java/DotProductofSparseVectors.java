/**
 * Created by cicean on 8/29/2016.
 * FaceBook
 *  Dot Product.
 A={a1, a2, a3,...}. 1point3acres.com/bbs
 B={b1, b2, b3,...}
 dot_product = a1 * b1 + a2 * b2 + a3 * b3 ��������
 ��������ϡ�裬����
 A={a1, ...., a300, ...., a5000}
 B={...., b100, ..., b300, ..., b1000, ...}. 1point3acres.com/bbs
 �����кܶ�0����ʲô���ݽṹ��ʾ�ܽ�ʡ�ռ䡣�ҿ�ʼ˵��hashmap������hashmap��������˳���iteration��Ȼ�����list��array�����������ԡ����������ʱ�õ�array��
 ��Ŀ�ǣ�
 input A=[[1, a1], [300, a300], [5000, a5000]]. 牛人云集,丢�亩三分地
 B=[[100, b100], [300, b300], [1000, b1000]]
 �� dot product. ����ʱ�临�Ӷȡ�
 Follow up:
 ���length(B) >>> length(A)����B�ǳ�������ô���ܼ���ʱ�临�Ӷȡ���A�����ÿ��������binary search��B�����Ӧ��ֵ������ʱ�临�Ӷ���O(nlogm) (n = len(A), m =len(B)).ʱ�䲻��ûд���룬 ��˵��һ��˼·�͸��Ӷȡ�.1point3acres�?
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
