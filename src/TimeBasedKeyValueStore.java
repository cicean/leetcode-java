/**
 * 981. Time Based Key-Value Store
 * Medium
 *
 * 330
 *
 * 46
 *
 * Favorite
 *
 * Share
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 *
 * Example 1:
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 *
 * Example 2:
 *
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 *
 * Note:
 *
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 * Accepted
 * 26,510
 * Submissions
 * 51,637
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 10
 *
 * Amazon
 * |
 * 6
 *
 * Sumologic
 * |
 * 5
 *
 * Lyft
 * |
 * 3
 *
 * Facebook
 * |
 * 3
 */
import javafx.util.Pair;

public class TimeBasedKeyValueStore {

    /**
     *
     * Approach 1: HashMap + Binary Search
     * Intuition and Algorithm
     *
     * For each key we get or set, we only care about the timestamps and values for that key. We can store this information in a HashMap.
     *
     * Now, for each key, we can binary search the sorted list of timestamps to find the relevant value for that key.
     * Complexity Analysis
     *
     * Time Complexity: O(1)O(1) for each set operation, and O(\log N)O(logN) for each get operation, where NN is the number of entries in the TimeMap.
     *
     * Space Complexity: O(N)O(N).
     */
    class TimeMap {
        Map<String, List<Pair<Integer, String>>> M;

        public TimeMap() {
            M = new HashMap();
        }

        public void set(String key, String value, int timestamp) {
            if (!M.containsKey(key))
                M.put(key, new ArrayList<Pair<Integer, String>>());

            M.get(key).add(new Pair(timestamp, value));
        }

        public String get(String key, int timestamp) {
            if (!M.containsKey(key)) return "";

            List<Pair<Integer, String>> A = M.get(key);
            int i = Collections.binarySearch(A, new Pair<Integer, String>(timestamp, "{"),
                    (a, b) -> Integer.compare(a.getKey(), b.getKey()));

            if (i >= 0)
                return A.get(i).getValue();
            else if (i == -1)
                return "";
            else
                return A.get(-i-2).getValue();
        }
    }

    /**
     * Approach 2: TreeMap
     * Intuition and Algorithm
     *
     * In Java, we can use TreeMap.floorKey(timestamp) to find the largest timestamp smaller than the given timestamp.
     *
     * We build our solution in the same way as Approach 1, swapping in this functionality.
     * Complexity Analysis
     *
     * Time Complexity: O(1)O(1) for each set operation, and O(\log N)O(logN) for each get operation, where NN is the number of entries in the TimeMap.
     *
     * Space Complexity: O(N)O(N).
     */
    class TimeMap2__treemap {
        Map<String, TreeMap<Integer, String>> M;

        public TimeMap() {
            M = new HashMap();
        }

        public void set(String key, String value, int timestamp) {
            if (!M.containsKey(key))
                M.put(key, new TreeMap());

            M.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!M.containsKey(key)) return "";

            TreeMap<Integer, String> tree = M.get(key);
            Integer t = tree.floorKey(timestamp);
            return t != null ? tree.get(t) : "";
        }
    }
}
