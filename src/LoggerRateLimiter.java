import java.util.*;

/**
 * Created by cicean on 8/31/2016.
 * 359. Logger Rate Limiter  QuestionEditorial Solution  My Submissions
 Total Accepted: 4413 Total Submissions: 7831 Difficulty: Easy
 Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

 Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

 It is possible that several messages arrive roughly at the same time.

 Example:

 Logger logger = new Logger();

 // logging string "foo" at timestamp 1
 logger.shouldPrintMessage(1, "foo"); returns true;

 // logging string "bar" at timestamp 2
 logger.shouldPrintMessage(2,"bar"); returns true;

 // logging string "foo" at timestamp 3
 logger.shouldPrintMessage(3,"foo"); returns false;

 // logging string "bar" at timestamp 8
 logger.shouldPrintMessage(8,"bar"); returns false;

 // logging string "foo" at timestamp 10
 logger.shouldPrintMessage(10,"foo"); returns false;

 // logging string "foo" at timestamp 11
 logger.shouldPrintMessage(11,"foo"); returns true;
 Credits:
 Special thanks to @memoryless for adding this problem and creating all test cases.

 Hide Company Tags Google
 Hide Tags Hash Table Design
 Hide Similar Problems (M) Design Hit Counter

 */
public class LoggerRateLimiter {

    //方法一：使用哈希映射记录各个时间点的消息，使用一个集合记录最近10秒的所有消息。
    //方法二：在方法一基础上优化，使用循环数组来保存最近10秒的消息。
    //避免memory leak

    public class Logger {
        private List<String>[] buf = new List[10];
        private Set<String> set = new HashSet<>();
        private int from = 0;

        /** Initialize your data structure here. */
        public Logger() {
            for(int i=0; i<buf.length; i++) buf[i] = new ArrayList<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false. The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            for(int t = from; t <= timestamp - 10; t ++) {
                for(String s : buf[(t+buf.length)%buf.length]) {
                    set.remove(s);
                }
                buf[(t+buf.length)%buf.length].clear();
            }
            if (!set.add(message)) return false;
            buf[timestamp%buf.length].add(message);
            from = timestamp - 9;
            return true;
        }
    }

    class Log {
        int timestamp;
        String message;
        public Log(int aTimestamp, String aMessage) {
            timestamp = aTimestamp;
            message = aMessage;
        }
    }

    public class Logger {
        PriorityQueue<Log> recentLogs;
        Set<String> recentMessages;

        /** Initialize your data structure here. */
        public Logger() {
            recentLogs = new PriorityQueue<Log>(10, new Comparator<Log>() {
                public int compare(Log l1, Log l2) {
                    return l1.timestamp - l2.timestamp;
                }
            });

            recentMessages = new HashSet<String>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            while (recentLogs.size() > 0)   {
                Log log = recentLogs.peek();
                // discard the logs older than 10 minutes
                if (timestamp - log.timestamp >= 10) {
                    recentLogs.poll();
                    recentMessages.remove(log.message);
                } else
                    break;
            }
            boolean res = !recentMessages.contains(message);
            if (res) {
                recentLogs.add(new Log(timestamp, message));
                recentMessages.add(message);
            }
            return res;
        }
    }

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
}
