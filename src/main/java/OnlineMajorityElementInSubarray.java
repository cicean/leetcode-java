/**
 * 1157. Online Majority Element In Subarray
 * Hard
 *
 * 171
 *
 * 22
 *
 * Add to List
 *
 * Share
 * Implementing the class MajorityChecker, which has the following API:
 *
 * MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the given array arr;
 * int query(int left, int right, int threshold) has arguments such that:
 * 0 <= left <= right < arr.length representing a subarray of arr;
 * 2 * threshold > right - left + 1, ie. the threshold is always a strict majority of the length of the subarray
 * Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right] that occurs at least threshold times, or -1 if no such element exists.
 *
 *
 *
 * Example:
 *
 * MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
 * majorityChecker.query(0,5,4); // returns 1
 * majorityChecker.query(0,3,3); // returns -1
 * majorityChecker.query(2,3,2); // returns 2
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 20000
 * 1 <= arr[i] <= 20000
 * For each query, 0 <= left <= right < len(arr)
 * For each query, 2 * threshold > right - left + 1
 * The number of queries is at most 10000
 * Accepted
 * 6,043
 * Submissions
 * 15,880
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * pmanvi123
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * What's special about a majority element ?
 * A majority element appears more than half the length of the array number of times.
 * If we tried a random index of the array, what's the probability that this index has a majority element ?
 * It's more than 50% if that array has a majority element.
 * Try a random index for a proper number of times so that the probability of not finding the answer tends to zero.
 */
public class OnlineMajorityElementInSubarray {

    /**
     * SegmentTree
     */
    class MajorityChecker {
        private int[] nums;
        private SegmentTreeNode treeRoot;
        private Map<Integer, List<Integer>> idxMap;

        public MajorityChecker(int[] nums) {
            this.nums = nums;
            this.idxMap = new HashMap<>();
            this.treeRoot = buildTree(0, nums.length - 1, nums);

            for (int i = 0; i < nums.length; ++i) {
                List<Integer> idxList = idxMap.get(nums[i]);
                if (idxList == null) {
                    idxList = new ArrayList<>();
                    idxMap.put(nums[i], idxList);
                }
                idxList.add(i);
            }
        }

        public int query(int left, int right, int threshold) {
            Pair cand = queryTree(treeRoot, left, right);
            if (cand.freq >= threshold) {
                return cand.val;
            }

            // According to Java Collections.binarySearch API:
            // if key doesn't exist in the list, will return: -(insertion point) - 1
            int leftIdx = Collections.binarySearch(idxMap.get(cand.val), left);
            if (leftIdx < 0) {
                leftIdx = -leftIdx - 1;
            }

            int rightIdx = Collections.binarySearch(idxMap.get(cand.val), right);
            if (rightIdx < 0) {
                rightIdx = -rightIdx - 2;
            }

            if (rightIdx - leftIdx + 1 >= threshold) {
                return cand.val;
            }

            return -1;
        }

        //////////////////////////////////////////////
        // following code is SegmentTree related stuff
        private SegmentTreeNode buildTree(int start, int end, int[] nums) {
            if (start == end) {
                return new SegmentTreeNode(start, end, new Pair(nums[start], 1));
            }

            SegmentTreeNode root = new SegmentTreeNode(start, end, null);

            int mid = start + (end - start) / 2;
            root.left = buildTree(start, mid, nums);
            root.right = buildTree(mid + 1, end, nums);
            root.pair = mergePair(root.left.pair, root.right.pair);

            return root;
        }

        private Pair queryTree(SegmentTreeNode root, int start, int end) {
            if (start <= root.start && root.end <= end) {
                return root.pair;
            }

            Pair res = new Pair(0, 0);
            int mid = root.start + (root.end - root.start) / 2;
            if (start <= mid) {
                res = mergePair(res, queryTree(root.left, start, end));
            }
            if (mid < end) {
                res = mergePair(res, queryTree(root.right, start, end));
            }

            return res;
        }

        private Pair mergePair(Pair pair1, Pair pair2) {
            if (pair1.val == pair2.val) {
                return new Pair(pair1.val, pair1.freq + pair2.freq);
            }

            if (pair1.freq > pair2.freq) {
                return new Pair(pair1.val, pair1.freq - pair2.freq);
            }

            return new Pair(pair2.val, pair2.freq - pair1.freq);
        }

        static class SegmentTreeNode {
            int start;
            int end;
            Pair pair;
            SegmentTreeNode left;
            SegmentTreeNode right;

            SegmentTreeNode(int start, int end, Pair pair) {
                this.start = start;
                this.end = end;
                this.pair = pair;
                this.left = null;
                this.right = null;
            }
        }

        static class Pair {
            int val;
            int freq;

            Pair(int val, int freq) {
                this.val = val;
                this.freq = freq;
            }
        }
    }


    /**
     * Binary search
     */
    class MajorityChecker {
        int[] arr;
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        public MajorityChecker(int[] arr) {
            this.arr = arr;
            for(int i = 0;i<arr.length;i++){
                ArrayList<Integer> temp = map.getOrDefault(arr[i],new ArrayList<Integer>());
                temp.add(i);
                map.put(arr[i],temp);
            }
        }

        public int query(int left, int right, int threshold) {
            for(int i = 0;i<20;i++){
                int min = left, max = right;
                int candidate = arr[min + (int)(Math.random() * (max-min+1))];
                ArrayList<Integer> temp = map.get(candidate);
                if(temp.size() < threshold) continue;
                // the range will be [low,high]
                int low = Collections.binarySearch(temp,left);
                int high = Collections.binarySearch(temp,right);
                //if low or high is negative, means not found, will return (-insert postion - 1);
                if(low < 0) low = - low - 1;
                if(high < 0) high = (- high - 1) - 1;//make high positive, then high--
                if(high - low + 1 >= threshold) return candidate;
            }
            return -1;
        }
    }
}
