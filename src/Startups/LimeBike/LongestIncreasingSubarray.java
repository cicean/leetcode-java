package LimeBike;

import java.util.*;

public class LongestIncreasingSubarray {

    public int lenOfLongIncSubArr(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = 1, len = 1, n = nums.length;

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                len++;
                continue;
            }
            max = Math.max(max, len);
            len = 1;
        }

        max = Math.max(max, len);

        // if is increasing continue
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                len++;
                continue;
            }
            max = Math.max(max, len);
            len = 1;
        }

        max = Math.max(max, len);
        return max;
    }

    //if can be K break

    public int lenOfLongIncSubArr_k(int[] nums, int k) {


        if (nums == null || nums.length == 0) return 0;

        int max = 1, len = 1, b = 0, n = nums.length;

        List<Integer> continueIncreasinglenth = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            //System.out.println("lenOfLongIncSubArr len = " + len);
            if (nums[i - 1] < nums[i]) {
                len++;
                //System.out.println("lenOfLongIncSubArr len = " + len);
                continue;
            }
            System.out.println("lenOfLongIncSubArr len = " + len);
            continueIncreasinglenth.add(len);
            len = 1;
        }
        continueIncreasinglenth.add(len);

        if (continueIncreasinglenth.size() <= k + 1) {
            for (int i = 0; i < continueIncreasinglenth.size(); i++) {
                return max += continueIncreasinglenth.get(i);
            }
        }

        for (int i = 0; i <= k; i++) {
            max += continueIncreasinglenth.get(i);
        }
        int lastsum = max;
        for (int i = k + 1; i < continueIncreasinglenth.size() - k; i++) {
            max = Math.max(max, lastsum - continueIncreasinglenth.get(i - k - 1) + continueIncreasinglenth.get(i));
            lastsum += continueIncreasinglenth.get(i) - continueIncreasinglenth.get(i - k - 1);
        }

        return max;

    }


    /* Driver program to test above function */
    public static void main(String[] args)
    {
        int arr[] = {5, 6, 3, 5, 7, 8, 9, 1, 2};
        int n = arr.length;
        System.out.println("Length = " +
                lenOfLongIncSubArr(arr));

    }

    String s = "abcd";

    char[] test = s.toCharArray();

}
