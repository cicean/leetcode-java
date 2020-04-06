/**
 * 635. Design Log Storage System
 * Medium
 *
 * 234
 *
 * 103
 *
 * Add to List
 *
 * Share
 * You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 *
 * Design a log storage system to implement the following functions:
 *
 * void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
 *
 *
 * int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
 *
 * Example 1:
 * put(1, "2017:01:01:23:59:59");
 * put(2, "2017:01:01:22:59:59");
 * put(3, "2016:01:01:00:00:00");
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
 * Note:
 * There will be at most 300 operations of Put or Retrieve.
 * Year ranges from [2000,2017]. Hour ranges from [00,23].
 * Output for Retrieve has no order required.
 * Accepted
 * 15,725
 * Submissions
 * 27,378
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * fallcreek
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Twitter
 * |
 * 3
 * Design In-Memory File System
 * Hard
 */
public class DesignLogStorageSystem {
    /**
     * Approach #1 Converting timestamp into a number [Accepted]
     * This solution is based on converting the given timestap into a number. This can help because retrieval of Logs lying within a current range can be very easily done if the range to be used can be represented in the form of a single number. Let's look at the individual implementations directly.
     *
     * put: Firstly, we split the given timestamp based on : and store the individual components obtained into an stst array. Now, in order to put this log's entry into the storage, firstly, we convert this timestamp, now available as individual components in the stst array into a single number. To obtain a number which is unique for each timestamp, the number chosen is such that it represents the timestamp in terms of seconds. But, doing so for the Year values can lead to very large numbers, which could lead to a potential overflow. Since, we know that the Year's value can start from 2000 only, we subtract 1999 from the Year's value before doing the conversion of the given timestamp into seconds. We store this timestamp(in the form of a number now), along with the Log's id, in s listlist, which stores data in the form [converted\_timestamp, id][converted_timestamp,id].
     *
     * retrieve: In order to retrieve the logs' ids lying within the timestamp ss and ee, with a granularity gragra, firstly, we need to convert the given timestamps into seconds. But, since, we need to take care of granularity, before doing the conversion, we need to consider the effect of granularity. Granularity, in a way, depicts the precision of the results. Thus, for a granularity corresponding to a Day, we need to consider the portion of the timestamp considering the precision upto Day only. The rest of the fields can be assumed to be all 0's. After doing this for both the start and end timestamp, we do the conversion of the timestamp with the required precision into seconds. Once this has been done, we iterate over all the logs in the listlist to obtain the ids of those logs which lie within the required range. We keep on adding these ids to a resres list which is returned at the end of this function call.
     *
     *  **Performance Analysis**
     * The putmethod takes O(1)O(1) time to insert a new entry into the given set of logs.
     *
     * The retrieve method takes O(n)O(n) time to retrieve the logs in the required range. Determining the granularity takes O(1)O(1) time. But, to find the logs lying in the required range, we need to iterate over the set of logs atleast once. Here, nn refers to the number of entries in the current set of logs.
     */

    public class LogSystem {
        ArrayList < long[] > list;
        public LogSystem() {
            list = new ArrayList < long[] > ();
        }

        public void put(int id, String timestamp) {
            int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
            list.add(new long[] {convert(st), id});
        }
        public long convert(int[] st) {
            st[1] = st[1] - (st[1] == 0 ? 0 : 1);
            st[2] = st[2] - (st[2] == 0 ? 0 : 1);
            return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60 + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
        }
        public List < Integer > retrieve(String s, String e, String gra) {
            ArrayList < Integer > res = new ArrayList();
            long start = granularity(s, gra, false);
            long end = granularity(e, gra, true);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[0] >= start && list.get(i)[0] < end)
                    res.add((int) list.get(i)[1]);
            }
            return res;
        }

        public long granularity(String s, String gra, boolean end) {
            HashMap < String, Integer > h = new HashMap();
            h.put("Year", 0);
            h.put("Month", 1);
            h.put("Day", 2);
            h.put("Hour", 3);
            h.put("Minute", 4);
            h.put("Second", 5);
            String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
            String[] st = s.split(":");
            for (int i = 0; i <= h.get(gra); i++) {
                res[i] = st[i];
            }
            int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
            if (end)
                t[h.get(gra)]++;
            return convert(t);
        }
    }

     /** Approach #2 Better Retrieval [Accepted]
     * This method remains almost the same as the last approach, except that, in order to store the timestamp data, we make use of a TreeMap instead of a list, with the key values being the timestamps converted in seconds form and the values being the ids of the corresponding logs.
     *
     * Thus, the put method remains the same as the last approach. However, we can save some time in retrieve approach by making use of tailMap function of TreeMap, which stores the entries in the form of a sorted navigable binary tree. By making use of this function, instead of iterating over all the timestamps in the storage to find the timestamps lying within the required range(after considering the granularity as in the last approach), we can reduce the search space to only those elements whose timestamp is larger than(or equal to) the starting timestamp value.
     *
     *  **Performance Analysis**
     * The TreeMap is maintained internally as a Red-Black(balanced) tree. Thus, the putmethod takes O\big(log(n)\big)O(log(n)) time to insert a new entry into the given set of logs. Here, nn refers to the number of entries currently present in the given set of logs.
     *
     * The retrieve method takes O(m_{start})O(m
     * start
     * ​
     *  ) time to retrieve the logs in the required range. Determining the granularity takes O(1)O(1) time. To find the logs in the required range, we only need to iterate over those elements which already lie in the required range. Here, m_{start}m
     * start
     * ​
     *   refers to the number of entries in the current set of logs which have a timestamp greater than the current startstart value.
     */
     public class LogSystem {
         TreeMap < Long, Integer > map;
         public LogSystem() {
             map = new TreeMap < Long, Integer > ();
         }

         public void put(int id, String timestamp) {
             int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
             map.put(convert(st), id);
         }
         public long convert(int[] st) {
             st[1] = st[1] - (st[1] == 0 ? 0 : 1);
             st[2] = st[2] - (st[2] == 0 ? 0 : 1);
             return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60 + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
         }
         public List < Integer > retrieve(String s, String e, String gra) {
             ArrayList < Integer > res = new ArrayList();
             long start = granularity(s, gra, false);
             long end = granularity(e, gra, true);
             for (long key: map.tailMap(start).keySet()) {
                 if (key >= start && key < end)
                     res.add(map.get(key));
             }
             return res;
         }

         public long granularity(String s, String gra, boolean end) {
             HashMap < String, Integer > h = new HashMap();
             h.put("Year", 0);
             h.put("Month", 1);
             h.put("Day", 2);
             h.put("Hour", 3);
             h.put("Minute", 4);
             h.put("Second", 5);
             String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
             String[] st = s.split(":");
             for (int i = 0; i <= h.get(gra); i++) {
                 res[i] = st[i];
             }
             int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
             if (end)
                 t[h.get(gra)]++;
             return convert(t);
         }
     }
}
