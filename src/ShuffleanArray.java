import java.util.Arrays;
import java.util.Random;

/**
 * Created by cicean on 9/2/2016.
 * 384. Shuffle an Array  QuestionEditorial Solution  My Submissions
 Total Accepted: 6833 Total Submissions: 15329 Difficulty: Medium
 Shuffle a set of numbers without duplicates.

 Example:

 // Init an array with set 1, 2, and 3.
 int[] nums = {1,2,3};
 Solution solution = new Solution(nums);

 // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 solution.shuffle();

 // Resets the array back to its original configuration [1,2,3].
 solution.reset();

 // Returns the random shuffling of array [1,2,3].
 solution.shuffle();

 ����һ�����飬ʵ�������ӿ� reset() �� shuffle()�� ǰ��Ϊ��λ���س�ʼ�����飬����Ϊ��������顣
 */
public class ShuffleanArray {

    //����swap��ÿ�δ�[i,n-1]�����һ�������͵�i�����������ɡ�
    public class Solution {
        private int[] nums;
        private int[] output;
        private Random random;

        public Solution(int[] nums) {
            this.nums = nums;
            this.output = Arrays.copyOf(nums,nums.length);
            this.random = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return this.output = Arrays.copyOf(nums,nums.length);
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int n = output.length;
            for (int i = 0; i < n; i++) {
                int _id = random.nextInt(n-i);
                int temp = output[i];
                output[i] = output[i+_id];
                output[i+_id] = temp;
            }
            return output;
        }
    }

    //O(n) Java solution by using random class and swapping current with a random previous index.
    public class Solution2 {
        private int[] nums = null;
        private Random random = null;
        public Solution2(int[] nums) {
            this.nums = nums;
            random = new Random(System.currentTimeMillis());
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return Arrays.copyOf(nums,nums.length); // just return a copy.
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int[] ans = Arrays.copyOf(nums,nums.length); // create a copy
            for(int i = 1 ; i < nums.length ; i++){
                int swapIndex = random.nextInt(i+1); // generate a random number within visited elements including current index.
                swap(ans,i,swapIndex); // swap the index
            }
            return ans;
        }
        private void swap(int[] ans, int from , int to){
            int temp = ans[from];
            ans[from] = ans[to];
            ans[to] = temp;
        }
    }

}
