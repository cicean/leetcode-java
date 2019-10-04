import java.util.Arrays;

/**
 * Created by cicean on 8/29/2016.
 *
 * 324. Wiggle Sort II  QuestionEditorial Solution  My Submissions
 Total Accepted: 15740 Total Submissions: 65599 Difficulty: Medium
 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example:
 (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Sort
 Hide Similar Problems (M) Sort Colors (M) Kth Largest Element in an Array (M) Wiggle Sort
 ����һ�����飬Ҫ������������� ������λ��Ҫ�������ߡ�
 �� nums[1] > nums[0]  ��nums[1] > nums[2]
 */
public class WiggleSortII {

    /**
     *�ѵ��ǻ�����ȵ�Ԫ�أ���Ҫ������Ԫ�س���wiggle�⣬������ȡ�
     ��ô�Ͳ���ȡ��������ڵ�Ԫ�ؽ�������Ҫ�ͺ����Ԫ�ؽ��������磺

     //1 2 3 4 5 6
     //3 6 2 5 1 4
     �����ռ�������ǣ�����һ��������temp������������Ҫ�Ĺ��ɷ���Ԫ�أ����copy��ԭ����nums��
     �򵥵�˼·���ǣ�����nums����n������
     ����ѭ��n/2�λ���n/2+1�Σ�ÿ��ѭ����Ϊtemp�����������nΪ����ʱ�����һ��ѭ��ֻ���һ��������
     �����System.arraycopy(sourceArray, 0, targetArray, 0, targetArray.length).
     */

    //����Ȼ�����߷ֱ�ȡ�����Ӷ�O(nlogn)
    public class Solution {
        public void wiggleSort(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length, mid = (n-1)/2, index = 0;
            int[] temp = new int[n];
            for (int i = 0; i <= mid; i++) {
                temp[index] = nums[mid-i];
                if (index+1 < n) {
                    temp[index+1] = nums[n-1-i];
                }
                index += 2;
            }
            System.arraycopy(temp, 0, nums, 0, n);
        }
    }

    //�ÿ��ŵ�˼�������λ����Ȼ���ٺϲ����ߡ�
    //����Ӷ�O(n^2)��ƽ�����Ӷ�O(n)
    public class Solution2 {
        public void wiggleSort(int[] nums) {
            int medium = findMedium(nums, 0, nums.length - 1, (nums.length + 1) >> 1);
            int s = 0, t = nums.length - 1 , mid_index = (nums.length + 1) >> 1;
            int[] temp = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < medium)
                    temp[s++] = nums[i];
                else if (nums[i] > medium)
                    temp[t--] = nums[i];
            }

            while (s < mid_index) temp[s++] = medium;
            while (t >= mid_index) temp[t--] = medium;

            t = nums.length;
            for (int i = 0; i < nums.length; i++)
                nums[i] = (i & 1) == 0 ? temp[--s] : temp[--t];
        }

        private int findMedium(int[] nums, int L, int R, int k) {
            if (L >= R) return nums[R];
            int i = partition(nums, L, R);
            int cnt = i - L + 1;
            if (cnt == k) return nums[i];
            return cnt > k ? findMedium(nums, L, i - 1, k) : findMedium(nums, i + 1, R, k - cnt);
        }

        private int partition(int[] nums, int L, int R) {
            int val = nums[L];
            int i = L, j = R + 1;
            while (true) {
                while (++i < R && nums[i] < val) ;
                while (--j > L && nums[j] > val) ;
                if (i >= j) break;
                swap(nums, i, j);
            }
            swap(nums, L, j);
            return j;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }




}
