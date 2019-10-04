import java.util.*;

/**
 * Created by cicean on 9/1/2016.
 * 373. Find K Pairs with Smallest Sums  QuestionEditorial Solution  My Submissions
 Total Accepted: 9998 Total Submissions: 36190 Difficulty: Medium
 You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

 Define a pair (u,v) which consists of one element from the first array and one element from the second array.

 Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

 Example 1:
 Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

 Return: [1,2],[1,4],[1,6]

 The first 3 pairs are returned from the sequence:
 [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 Example 2:
 Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

 Return: [1,1],[1,1]

 The first 2 pairs are returned from the sequence:
 [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 Example 3:
 Given nums1 = [1,2], nums2 = [3],  k = 3

 Return: [1,3],[2,3]

 All possible pairs are returned from the sequence:
 [1,3],[2,3]
 Credits:
 Special thanks to @elmirap and @StefanPochmann for adding this problem and creating all test cases.

 Hide Company Tags Google Uber
 Hide Tags Heap
 Hide Similar Problems (M) Kth Smallest Element in a Sorted Matrix

 *
 * �������������������������nums1��nums2���Լ�һ������k��

 ����һ������(u, v)��������һ�������е�һ��Ԫ���Լ��ڶ��������е�һ��Ԫ�ء�

 Ѱ�Һ���С��k������������(u1,v1),(u2,v2) ...(uk,vk)��
 */
public class FindKPairswithSmallestSums {

    /**
     * �ⷨI ���ö����ݽṹ��Heap��

     ��nums1���±�Ϊi��nums2�±�Ϊj�����鳤��Ϊsize1��size2��

     ���Ƚ�������Ԫ�ء���MaxInt, None, None�������

     ��i = j = 0

     ��nums1[0] + nums2[j]��Ѷ�Ԫ��top���бȽϣ�

     ���Ѷ�Ԫ�ؽϴ���(nums1[i] + nums2[j], i, j)����ѣ�iȡֵ[0, size1)��Ȼ����j = j + 1

     ���Ѷ�Ԫ�ص�����������

     ѭ��ֱ������
     �����ض������룬�����㷨���˻�Ϊ��nums1��nums2�ĵѿ�����ȫ������ѡ�
     */
    public class Solution {
        class Pair{
            int[] pair;
            int idx; // current index to nums2
            long sum;
            Pair(int idx, int n1, int n2){
                this.idx = idx;
                pair = new int[]{n1, n2};
                sum = (long) n1 + (long) n2;
            }
        }
        class CompPair implements Comparator<Pair> {
            public int compare(Pair p1, Pair p2){
                return Long.compare(p1.sum, p2.sum);
            }
        }
        public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<int[]> ret = new ArrayList<>();
            if (nums1==null || nums2==null || nums1.length ==0 || nums2.length ==0) return ret;
            int len1 = nums1.length, len2=nums2.length;

            PriorityQueue<Pair> q = new PriorityQueue(k, new CompPair());
            for (int i=0; i<nums1.length && i<k ; i++) { // only need first k number in nums1 to start
                q.offer( new Pair(0, nums1[i],nums2[0]) );
            }
            for (int i=1; i<=k && !q.isEmpty(); i++) { // get the first k sums
                Pair p = q.poll();
                ret.add( p.pair );
                if (p.idx < len2 -1 ) { // get to next value in nums2
                    int next = p.idx+1;
                    q.offer( new Pair(next, p.pair[0], nums2[next]) );
                }
            }
            return ret;
        }
    }

    /**
     * Java Solution

     This problem is similar to Super Ugly Number. The basic idea is using an array to track the index of the next element in the other array.

     The best way to understand this solution is using an example such as nums1={1,3,11} and nums2={2,4,8}.
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<int[]>();

        k = Math.min(k, nums1.length*nums2.length);

        if(k==0)
            return result;

        int[] idx = new int[nums1.length];

        while(k>0){
            int min = Integer.MAX_VALUE;
            int t=0;
            for(int i=0; i<nums1.length; i++){
                if(idx[i]<nums2.length && nums1[i]+nums2[idx[i]]<min){
                    t=i;
                    min = nums1[i]+nums2[idx[i]];
                }
            }

            int[] arr = {nums1[t], nums2[idx[t]]};
            result.add(arr);

            idx[t]++;

            k--;
        }

        return result;
    }
}
