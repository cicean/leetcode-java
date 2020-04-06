import java.util.*;

/**
 * Tweet Counts Per Frequency
 * Medium
 *
 * 33
 *
 * 387
 *
 * Add to List
 *
 * Share
 * Implement the class TweetCounts that supports two methods:
 *
 * 1. recordTweet(string tweetName, int time)
 *
 * Stores the tweetName at the recorded time (in seconds).
 * 2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime)
 *
 * Returns the total number of occurrences for the given tweetName per minute, hour, or day (depending on freq) starting from the startTime (in seconds) and ending at the endTime (in seconds).
 * freq is always minute, hour or day, representing the time interval to get the total number of occurrences for the given tweetName.
 * The first time interval always starts from the startTime, so the time intervals are [startTime, startTime + delta*1>,  [startTime + delta*1, startTime + delta*2>, [startTime + delta*2, startTime + delta*3>, ... , [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)> for some non-negative number i and delta (which depends on freq).
 *
 *
 * Example:
 *
 * Input
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 *
 * Output
 * [null,null,null,null,[2],[2,1],null,[4]]
 *
 * Explanation
 * TweetCounts tweetCounts = new TweetCounts();
 * tweetCounts.recordTweet("tweet3", 0);
 * tweetCounts.recordTweet("tweet3", 60);
 * tweetCounts.recordTweet("tweet3", 10);                             // All tweets correspond to "tweet3" with recorded times at 0, 10 and 60.
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
 * tweetCounts.recordTweet("tweet3", 120);                            // All tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211> - > 4 tweets.
 *
 *
 * Constraints:
 *
 * There will be at most 10000 operations considering both recordTweet and getTweetCountsPerFrequency.
 * 0 <= time, startTime, endTime <= 10^9
 * 0 <= endTime - startTime <= 10^4
 * Accepted
 * 5,015
 * Submissions
 * 19,432
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Twitter
 * |
 * LeetCode
 */
public class TweetCountsPerFrequency {
    class TweetCounts {

        private Map<String, TreeMap<Integer, Integer>> map;

        public TweetCounts() {
            map = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {

            if (!map.containsKey(tweetName)) map.put(tweetName, new TreeMap<>());
            TreeMap<Integer, Integer> tweetMap = map.get(tweetName);
            tweetMap.put(time, tweetMap.getOrDefault(time, 0) + 1);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {

            if (!map.containsKey(tweetName)) return null;
            List<Integer> res = new LinkedList<>();

            int interval = 0, size = 0;
            if (freq.equals("minute")) {
                interval = 60;
            } else if (freq.equals("hour")) {
                interval = 3600;
            } else {
                interval = 86400;
            }
            size = ((endTime - startTime) / interval) + 1;

            int[] buckets = new int[size];

            TreeMap<Integer, Integer> tweetMap = map.get(tweetName);

            for (Map.Entry<Integer, Integer> entry : tweetMap.subMap(startTime, endTime + 1).entrySet()) {

                int index = (entry.getKey() - startTime) / interval;
                buckets[index] += entry.getValue();
            }

            for (int num : buckets) res.add(num);

            return res;
        }
    }

    class TweetCounts {

        private HashMap<String, List<Integer>> tweets;

        public TweetCounts() {
            tweets = new HashMap<String, List<Integer>>();
        }

        public void recordTweet(String tweetName, int time) {
            if (!tweets.containsKey(tweetName)) {
                tweets.put(tweetName, new ArrayList<Integer>(Arrays.asList(time)));
            }
            else {
                // binary search add
                List<Integer> timeline = tweets.get(tweetName);
                int lo = 0;
                int hi = timeline.size();
                while (lo < hi) {
                    int mid = (lo+hi)/2;
                    if (timeline.get(mid) > time)
                        hi = mid;
                    else
                        lo = mid+1;
                }
                timeline.add(lo, time);
                // for (Integer e : timeline)
                //     System.out.println(e);
                // System.out.println();
            }
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            List<Integer> ans = new ArrayList<>();
            int delta = 0;
            if (freq.equals("minute"))  delta = 60;
            else if (freq.equals("hour"))   delta = 3600;
            else if (freq.equals("day"))    delta = 86400;
            List<Integer> timeline = tweets.getOrDefault(tweetName, new ArrayList<>());

            int start = startTime;
            int startCount = binCount(timeline, start, 0, timeline.size());
            for (int i=1; startTime+delta*i<endTime+1; i++) {
                int end = startTime + delta*i;
                int endCount = binCount(timeline, end, startCount, timeline.size());
                ans.add(endCount - startCount);
                start = end;
                startCount = endCount;
            }
            int end = endTime+1;
            int endCount = binCount(timeline, end, startCount, timeline.size());
            ans.add(endCount-startCount);
            return ans;
        }

        private int binCount(List<Integer> timeline, int time, int lo, int hi) {
            while (lo < hi) {
                int mid = (lo+hi)/2;
                if (timeline.get(mid) >= time)
                    hi = mid;
                else
                    lo = mid + 1;
            }
            return lo;
        }
    }
}
