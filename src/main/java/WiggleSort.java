import java.util.Arrays;

/**
 * Created by cicean on 9/25/2016.
 * 280. Wiggle Sort  QuestionEditorial Solution  My Submissions
 Total Accepted: 16111 Total Submissions: 30681 Difficulty: Medium
 Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

 For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

 Hide Company Tags Google
 Hide Tags Array Sort
 Hide Similar Problems (M) Sort Colors (M) Wiggle Sort II

 */
public class WiggleSort {

    //ʱ�� O(NlogN) �ռ� O(1)
    public void wiggleSort(int[] nums) {
        // �Ƚ���������
        Arrays.sort(nums);
        // ��������һ��һ�Խ���
        for(int i = 2; i < nums.length; i+=2){
            int tmp = nums[i-1];
            nums[i-1] = nums[i];
            nums[i] = tmp;
        }
    }

    //ʱ�� O(N) �ռ� O(1)
    public void wiggleSort_1(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            // ��Ҫ���������������ʱnums[i] < nums[i - 1]��ż��ʱnums[i] > nums[i - 1]
            if((i % 2 == 1 && nums[i] < nums[i-1]) || (i % 2 == 0 && nums[i] > nums[i-1])){
                int tmp = nums[i-1];
                nums[i-1] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
