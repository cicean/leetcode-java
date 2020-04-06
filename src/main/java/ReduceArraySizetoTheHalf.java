/**
 * 1338. Reduce Array Size to The Half
 * Medium
 *
 * 80
 *
 * 10
 *
 * Add to List
 *
 * Share
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 *
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
 * Example 2:
 *
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 * Example 3:
 *
 * Input: arr = [1,9]
 * Output: 1
 * Example 4:
 *
 * Input: arr = [1000,1000,3,7]
 * Output: 1
 * Example 5:
 *
 * Input: arr = [1,2,3,4,5,6,7,8,9,10]
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * arr.length is even.
 * 1 <= arr[i] <= 10^5
 * Accepted
 * 9,657
 * Submissions
 * 14,513
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Akuna Capital
 * |
 * LeetCode
 * Count the frequency of each integer in the array.
 * Start with an empty set, add to the set the integer with the maximum frequency.
 * Keep Adding the integer with the max frequency until you remove at least half of the integers.
 */
public class ReduceArraySizetoTheHalf {

    /**
     * Solution
     * Overview
     * The problem requires us to perform "removal operations" until the array is no more than half it's original length. Each "removal operation" involves choosing a number and removing all occurrences of it. The goal is to perform the minimum possible number of these "removal operations" and return that number.
     *
     * For example, consider the following array.
     *
     * The example array: 3, 5, 4, 3, 2, 6, 2, 2, 1, 9, 7, 5
     *
     * It makes sense to remove the numbers that occur the most times. i.e. it doesn't make sense to remove the 9, because that will only shorten the array by 1. Instead, choosing 2 would shorten the array by 3 (as 2 occurs 3 times). We can repeatedly apply this greedy strategy until the array is of no more than half its original length.
     *
     * While the length of arr is greater than n / 2, find the number in arr that occurs the most times and remove all occurrences of it.
     *
     * Doing this will require 2 steps:
     *
     * Counting how many times each number occurs.
     * Removing the numbers with the highest counts until we've removed at least n / 2 numbers in total.
     * We'll now look at a couple of different algorithms that apply this greedy strategy.
     *
     *
     * Approach 1: Sorting
     * Intuition
     *
     * We'll use the example array from above.
     *
     * The example array: 3, 5, 4, 3, 2, 6, 2, 2, 1, 9, 7, 5
     *
     * As identified above, the first step is to count how many times each number occurs. This would be a lot easier to do if all identical numbers were side-by-side. The simplest way of achieving this is to sort arr using a built-in sort.
     *
     * First step is to sort the input array, arr.
     *
     * Next, we need to do the actual counting.
     *
     * Putting the counts into a new array, counts.
     *
     * Notice that we can simply insert the counts into a new array (called counts). We don't need to remember what the original number associated with each count was (because the return type is simply how many unique numbers we needed to remove).
     *
     * The second step is to start picking off numbers with the highest number of occurrences. To make this easier, we'll start by reverse-sorting (largest to smallest) the counts array.
     *
     * Reverse sorting the counts array.
     *
     * Now that we've reverse-sorted counts, we can iterate down it, adding up the counts we want to take. Remember that each number in counts represent one of the unique numbers in arr. Therefore, of the numbers we remove from counts:
     *
     * Their sum represents how many numbers we've removed from arr. We want to remove at least arr.length / 2 of them.
     * The number of counts removed represents how many unique numbers have been removed from arr. This is the "set size" we ultimately need to return.
     * Therefore, we need a simple loop that iterates over counts, and keeps track of these 2 amounts.
     *
     * Here is a short animation that shows this last step.
     *
     * Current
     * 1 / 4
     * Algorithm
     *
     *
     * Complexity Analysis
     *
     * Time Complexity : O(n \, \log \, n)O(nlogn).
     *
     * The first step, sorting, requires O(n \, \log \, n)O(nlogn) time, assuming the use of a built-in sorting algorithm (i.e. assuming that you didn't write your own selection sort or bubble sort!).
     *
     * The second step, generating counts, takes O(n)O(n) time, because it's a linear scan of the nn items in arr, applying an O(1)O(1) operation to each.
     *
     * Computing the size of the minimum set also takes O(n)O(n), because it is a linear scan of the counts array (which has a length of at most nn).
     *
     * This gives us O(n \, \log \, n) + O(n) + O(n) = O(n \, \log \, n)O(nlogn)+O(n)+O(n)=O(nlogn), because the O(n)O(n) parts are insignificant compared to the O(n \, \log \, n)O(nlogn).
     *
     * Space Complexity : O(n)O(n).
     *
     * In the worst case, all the numbers in arr will be unique, leading to a counts array of length nn, and a space complexity of O(n)O(n).
     *
     * Most programming languages use a built in sorting algorithm that requires O(n)O(n) space. There are a few that only use O(\log \,n)O(logn) space. Most prioritize time over space.
     *
     * Regardless of what space your sorting algorithm is using, the O(n)O(n) space from the first part pulls the overall space complexity to O(n)O(n).
     *
     * Further Optimizations
     *
     * There are a couple of ways to optimize the space complexity of Approach 1 further. Applying these will result in an algorithm with a time complexity of O(n \, \log \, n)O(nlogn) and a space complexity of O(1)O(1). This is an interesting contrast to Approach 3's O(n)O(n) time complexity but O(n)O(n) space.
     *
     * Firstly, we can write the counts array values directly into arr (the input array) using the Two Pointer Technique. Any extra space at the end should be 0'ed (or simply deleted if using a language like Python). This works, because we don't need to look at arr again. Here is some pseudocode to do this.
     *
     * wp = 0
     * rp = 1
     * run_length = 1
     * while rp <= arr.length:
     *     if rp == arr.length or arr[rp - 1] != arr[rp]:
     *         arr[wp] = run_length
     *         wp += 1
     *         run_length = 1
     *     else:
     *         run_length += 1
     *     rp += 1
     * for i = wp to arr.length:
     *     arr[i] = 0
     * Secondly, we could use an O(1)O(1) space sorting algorithm, such as Heapsort or In-Place Merge Sort. It's likely you'd need to write this yourself.
     *
     * Applying both optimizations will give a space complexity of O(1)O(1).
     *
     */

    class Solution {
        public int minSetSize(int[] arr) {

            // Sort the input numbers.
            Arrays.sort(arr);

            // Make the List of Counts
            List<Integer> counts = new ArrayList<>();
            int currentRun = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == arr[i - 1]) {
                    currentRun += 1;
                    continue;
                }
                counts.add(currentRun);
                currentRun = 1;
            }
            counts.add(currentRun);

            Collections.sort(counts);
            Collections.reverse(counts);

            // Remove numbers until at least half are removed.
            int numbersRemovedFromArr = 0;
            int setSize = 0;
            for (int count : counts) {
                numbersRemovedFromArr += count;
                setSize += 1;
                if (numbersRemovedFromArr >= arr.length / 2) {
                    break;
                }
            }

            return setSize;
        }
    }

     /** Approach 2: Hashing/ Counting
     * Intuition
     *
     * A better way of doing the first step is to use a Multiset (also known as a Counter or Bag). A Multiset is, as the name suggests, a type of Set that allows duplicates. It is implemented using a HashMap, where the key is the set items, and the value is an integer stating how many times the item is in the set. In C++, it is called multiset. In Python, it is Counter. In Java and JavaScript, you will have to make your own using a HashMap.
     *
     * For this problem, the keys will be each unique number in arr, and the values will be how many times each occurred. Building this up using a HashMap is straightforward (Counter and multiset are even easier!).
     *
     * multiset = new Hash Map
     * for number in arr:
     *     if number is not in multiset keys:
     *        add number to multiset keys with value of 0
     *     increment value for number by 1
     * Now we need to determine which counts to take, to minimise the final set size. The simplest way is to extract the values, sort them, and then proceed in the same way as Approach 1.
     *
     * Algorithm
     *
     *
     * Complexity Analysis
     *
     * Time Complexity : O(n \, \log \, n)O(nlogn).
     *
     * The first step requires examining each of the nn numbers and then placing them into the HashMap (or multiset or Counter). Because inserting an item into a HashMap takes O(1)O(1) time, this gives an overall time complexity of O(n)O(n).
     *
     * Extracting the values from the HashMap is O(n)O(n). The rest has a worst case of O(n \, \log \, n)O(nlogn), just like in Approach 1.
     *
     * In practice, this approach is always more efficient than Approach 1, often by a considerable amount. We have assumed the worst case—that counts is the same size as arr. However for most cases, counts will be much smaller than arr. The previous algorithm was performing an O(n \, \log \, n)O(nlogn) sorting operation on both arr and counts, whereas this one only performs it on the (likely) smaller counts array.
     *
     * Note that Python's most_common(...) method for Counter is not O(n)O(n).
     *
     * Space Complexity : O(n)O(n).
     *
     * In the worst case, where all numbers in arr are unique, the Multiset will require O(n)O(n) space.
     *
     */
     class Solution {
         public int minSetSize(int[] arr) {

             // Do the counting with a HashMap.
             Map<Integer, Integer> countsMap = new HashMap<>();
             for (int num : arr) {
                 if (!countsMap.containsKey(num)) {
                     countsMap.put(num, 1);
                     continue;
                 }
                 countsMap.put(num, countsMap.get(num) + 1);
             }

             // Reverse sort a list of the Map values.
             List<Integer> counts = new ArrayList<>(countsMap.values());
             Collections.sort(counts);
             Collections.reverse(counts);

             // Remove numbers until at least half are removed.
             int numbersRemovedFromArr = 0;
             int setSize = 0;
             for (int count : counts) {
                 numbersRemovedFromArr += count;
                 setSize += 1;
                 if (numbersRemovedFromArr >= arr.length / 2) {
                     break;
                 }
             }

             return setSize;
         }
     }

    class Solution {
        public int minSetSize(int[] arr) {
            int maxv = 0;
            for(int v : arr) if(v > maxv) maxv = v;
            int[] cs = new int[maxv+1];
            int maxc = 0;
            for(int v : arr) {
                int c = ++cs[v];
                if(c > maxc) maxc = c;
            }
            int[] ccs = new int[maxc+1];
            for(int v=0; v<=maxv; v++) {
                int c = cs[v];
                if(c > 0)
                    ccs[c]++;
            }
            int target = (arr.length+1)>>>1;
            int res = 0;
            for(int c=maxc; ;c--) {
                int n = ccs[c];
                if(n > 0) {
                    int t = target - n*c;
                    if(t <= 0) {
                        res += (target+c-1)/c;
                        break;
                    }
                    target = t;
                    res += n;
                }
            }
            return res;
        }
    }
     /** Approach 3: Hashing and Bucket Sort
     * Intuition
     *
     * This approach is probably not needed for an interview, however, it is interesting that it is possible to get the time complexity of this algorithm down to O(n)O(n), and the techniques used here could potentially apply to other algorithmic problems you might face.
     *
     * In the above approach, the overall time complexity was O(n \, \log \, n)O(nlogn), because we sorted the counts array. Instead of using an O(n \, \log \, n)O(nlogn) sort though, we could instead use Bucket Sort on the counts.
     *
     * Bucket Sort starts by identifying the largest number, mm, in the array to be sorted. It then creates a new array of length mm, initialized to zeroes. It goes through each of the nn numbers in the original array, putting them into the array index that corresponds to their value. An item is put into an index simply by incrementing the value at that index by 1. For example, here is how the counts array looks put into buckets.
     *
     * Counts array put into buckets.
     *
     * After putting all the items into their respective "buckets", we usually then convert the input back into a standard array. However, we're not going to do that here. We can process the items in the "bucket" form, and in fact it's more efficient to do so for this problem.
     *
     * Assuming the items to be sorted are all positive integers (it can be adapted to work with negatives too), Bucket Sort's time and space complexity is proportional to the number of items to be sorted, and to the size of the largest item, i.e. where mm is the largest item in the array, and nn is the number of items in the array, the time complexity is O(\max(n, m)) = O(n + m)O(max(n,m))=O(n+m), and the space complexity is O(m)O(m). Therefore, bucket sort generally works well when mm is low and nn is high (i.e. lots of numbers within a small range.).
     *
     * Here we know that the maximum number in counts couldn't possibly be higher than nn. If it was higher than nn, then there has to have been more items in arr to begin with, which would be a contradiction! We also know that the numbers are all positive integers (a negative count makes no sense in this context). Therefore, this problem is a suitable candidate for Bucket Sort, and with m ≤ nm≤n, the time complexity simplifies to O(n + n) = O(n)O(n+n)=O(n).
     *
     * To make the algorithm a little more efficient in practice, we can identify what the largest count is while doing the first part of the algorithm. We then know that this is the largest bucket we'll need. This will mean too that when we go to get numbers out of the buckets, we won't be having to go past lots of zeroes before we hit the first meaningful data point.
     *
     * Algorithm
     *
     *
     * Complexity Analysis
     *
     * Time Complexity : O(n)O(n).
     *
     * The first step is the same as Approach 2, with a cost of O(n)O(n).
     *
     * The bucket sorting, as explained above, is also O(n)O(n).
     *
     * Therefore, the total time complexity of this algorithm is O(n)O(n).
     *
     * Space Complexity : O(n)O(n).
     *
     * We require O(n)O(n) extra space for the HashMap, and then up to O(n)O(n) extra space to do the Bucket Sort.
     */

     class Solution {
         public int minSetSize(int[] arr) {

             Map<Integer, Integer> counts = new HashMap<>();
             int maxCount = 0;
             for (int num : arr) {
                 if (!counts.containsKey(num)) {
                     counts.put(num, 0);
                 }
                 int newCount = counts.get(num) + 1;
                 counts.put(num, newCount);
                 maxCount = Math.max(maxCount, newCount);
             }

             // Put the counts into buckets.
             int[] buckets = new int[maxCount + 1];
             for (int count : counts.values()) {
                 buckets[count]++;
             }


             // Determine setSize.
             int setSize = 0;
             int numbersToRemoveFromArr = arr.length / 2;
             int bucket = maxCount;
             while (numbersToRemoveFromArr > 0) {
                 int maxNeededFromBucket = numbersToRemoveFromArr / bucket;
                 if (numbersToRemoveFromArr % bucket != 0) {
                     maxNeededFromBucket++;
                 }
                 int setSizeIncrease = Math.min(buckets[bucket], maxNeededFromBucket);
                 setSize += setSizeIncrease;
                 numbersToRemoveFromArr -= setSizeIncrease * bucket;
                 bucket--;
             }
             return setSize;
         }
     }
}
