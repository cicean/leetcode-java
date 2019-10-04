package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/13/2018.
 */
public class FindMedian {

    class Solution_stream {
        /**
         * Find Median from Data Stream
         * @param nums: A list of integers
         * @return: the median of numbers
         */
        // public int[] medianII(int[] nums) {
        //     // write your code here
        // }

        private PriorityQueue<Integer> maxHeap, minHeap;
        private int numOfElements = 0;

        public int[] medianII(int[] nums) {
            // write your code here
            Comparator<Integer> revCmp = new Comparator<Integer>() {
                @Override
                public int compare(Integer left, Integer right) {
                    return right.compareTo(left);
                }
            };
            int cnt = nums.length;
            maxHeap = new PriorityQueue<Integer>(cnt, revCmp);
            minHeap = new PriorityQueue<Integer>(cnt);
            int[] ans = new int[cnt];
            for (int i = 0; i < cnt; ++i) {
                addNumber(nums[i]);
                ans[i] = getMedian();
            }
            return ans;
        }
        void addNumber(int value) {
            maxHeap.add(value);
            if (numOfElements%2 == 0) {
                if (minHeap.isEmpty()) {
                    numOfElements++;
                    return;
                }
                else if (maxHeap.peek() > minHeap.peek()) {
                    Integer maxHeapRoot = maxHeap.poll();
                    Integer minHeapRoot = minHeap.poll();
                    maxHeap.add(minHeapRoot);
                    minHeap.add(maxHeapRoot);
                }
            }
            else {
                minHeap.add(maxHeap.poll());
            }
            numOfElements++;
        }
        int getMedian() {
            return maxHeap.peek();
        }
    }

    public class Solution_k {
        /**
         *
         * Median of K Sorted Arrays
         * @param nums: the given k sorted arrays
         * @return: the median of the given k sorted arrays
         */
        // public double findMedian(int[][] nums) {
        //     // write your code here
        // }

        public double findMedian(int[][] nums) {
            int n = getTotal(nums);
            if (n == 0) {
                return 0;
            }

            if (n % 2 != 0) {
                return findKth(nums, n / 2 + 1);
            }

            return (findKth(nums, n / 2) + findKth(nums, n / 2 + 1)) / 2.0;
        }

        private int getTotal(int[][] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i].length;
            }
            return sum;
        }

        // k is not zero-based, it starts from 1.
        private int findKth(int[][] nums, int k) {
            int start = 0, end = Integer.MAX_VALUE;

            // find the last number x that >= k numbers are >= x.
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (getGTE(nums, mid) >= k) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (getGTE(nums, start) >= k) {
                return start;
            }

            return end;
        }

        // get how many numbers greater than or equal to val in 2d array
        private int getGTE(int[][] nums, int val) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += getGTE(nums[i], val);
            }
            return sum;
        }

        // get how many numbers greater than or equal to val in an array
        private int getGTE(int[] nums, int val) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int start = 0, end = nums.length - 1;

            // find first element >= val
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] >= val) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (nums[start] >= val) {
                return nums.length - start;
            }

            if (nums[end] >= val) {
                return nums.length - end;
            }

            return 0;
        }
    }

    public class Solution_2 {
    /*
      Median of two Sorted Arrays
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
        // public double findMedianSortedArrays(int[] A, int[] B) {
        public double findMedianSortedArrays(int[] A, int[] B) {
            int n = A.length + B.length;

            if (n % 2 == 0) {
                return (findKth(A, B, n / 2) + findKth(A, B, n / 2 + 1)) / 2.0;
            }

            return findKth(A, B, n / 2 + 1);
        }

        // k is not zero-based, it starts from 1
        public int findKth(int[] A, int[] B, int k) {
            if (A.length == 0) {
                return B[k - 1];
            }
            if (B.length == 0) {
                return A[k - 1];
            }

            int start = Math.min(A[0], B[0]);
            int end = Math.max(A[A.length - 1], B[B.length - 1]);

            // find first x that >= k numbers is smaller or equal to x
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (countSmallerOrEqual(A, mid) + countSmallerOrEqual(B, mid) < k) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (countSmallerOrEqual(A, start) + countSmallerOrEqual(B, start) >= k) {
                return start;
            }

            return end;
        }

        private int countSmallerOrEqual(int[] arr, int number) {
            int start = 0, end = arr.length - 1;

            // find first index that arr[index] > number;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (arr[mid] <= number) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (arr[start] > number) {
                return start;
            }

            if (arr[end] > number) {
                return end;
            }

            return arr.length;
        }
    }

    public class Solution_one_unsort {
        /**
         * @param nums: A list of integers
         * @return: An integer denotes the middle number of the array
         */
        // public int median(int[] nums) {
        //     // write your code here
        // }

        public int median(int[] nums) {
            return sub(nums, 0, nums.length - 1, (nums.length + 1)/2);
        }
        private int sub(int[] nums, int start, int end, int size) {
            int mid = (start + end) / 2;
            int pivot = nums[mid];
            int i = start - 1, j = end + 1;
            for (int k = start; k < j; k++) {
                if (nums[k] < pivot) {
                    i++;
                    int tmp = nums[i];
                    nums[i] = nums[k];
                    nums[k] = tmp;
                } else if (nums[k] > pivot) {
                    j--;
                    int tmp = nums[j];
                    nums[j] = nums[k];
                    nums[k] = tmp;
                    k--;
                }
            }
            if (i - start + 1 >= size) {
                return sub(nums, start, i, size);
            } else if (j - start >= size) {
                return nums[j-1];
            } else {
                return sub(nums, j, end, size - (j - start));
            }
        }
    }
}
