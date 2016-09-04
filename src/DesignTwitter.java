import java.util.*;

/**
 * Created by cicean on 8/31/2016.
 * 355. Design Twitter  QuestionEditorial Solution  My Submissions
 Total Accepted: 6547 Total Submissions: 28257 Difficulty: Medium
 Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

 postTweet(userId, tweetId): Compose a new tweet.
 getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 follow(followerId, followeeId): Follower follows a followee.
 unfollow(followerId, followeeId): Follower unfollows a followee.
 Example:

 Twitter twitter = new Twitter();

 // User 1 posts a new tweet (id = 5).
 twitter.postTweet(1, 5);

 // User 1's news feed should return a list with 1 tweet id -> [5].
 twitter.getNewsFeed(1);

 // User 1 follows user 2.
 twitter.follow(1, 2);

 // User 2 posts a new tweet (id = 6).
 twitter.postTweet(2, 6);

 // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 twitter.getNewsFeed(1);

 // User 1 unfollows user 2.
 twitter.unfollow(1, 2);

 // User 1's news feed should return a list with 1 tweet id -> [5],
 // since user 1 is no longer following user 2.
 twitter.getNewsFeed(1);
 Hide Company Tags Amazon Twitter
 Hide Tags Hash Table Heap Design
 要求设计一个数据结构，使其能满足twitter的4种基本操作，
 发推、获得关注用户和自身最新10条推文、关注用户和取消关注。
 */
public class DesignTwitter {

    /**
     * 这道题让我们设计个简单的推特，具有发布消息，获得新鲜事，添加关注和取消关注等功能。
     * 我们需要用两个哈希表来做，第一个是建立用户和其所有好友之间的映射，
     * 另一个是建立用户和其所有消息之间的映射。由于获得新鲜事是需要按时间顺序排列的，
     * 那么我们可以用一个整型变量cnt来模拟时间点，每发一个消息，cnt自增1，
     * 那么我们就知道cnt大的是最近发的。那么我们在建立用户和其所有消息之间的映射时，
     * 还需要建立每个消息和其时间点cnt之间的映射。这道题的主要难点在于实现getNewsFeed()函数，
     * 这个函数获取自己和好友的最近10条消息，我们的做法是用户也添加到自己的好友列表中，
     * 然后遍历该用户的所有好友，遍历每个好友的所有消息，维护一个大小为10的哈希表，
     * 如果新遍历到的消息比哈希表中最早的消息要晚，
     * 那么将这个消息加入，然后删除掉最早的那个消息，这样我们就可以找出最近10条消息了，
     */

    /**
     * Map + Set + PriorityQueue

     系统应当维护下列信息：

     1). 一个全局的推文计数器：postCount 用户发推文时，计数器+1

     2). 推文Id -> 推文计数器的映射：tweetIdMap 用来记录推文的次序

     3). 推文Id -> 用户Id的映射：tweetOwnerMap 用来记录推文的发送者是谁

     4). 用户Id -> 关注对象集合的映射： followeeMap 用来记录用户的关注对象（关注/取消关注）
     方法的实现：

     1). 关注 follow / 取消关注 unfollow：

     只需要维护followeeMap中对应的关注对象集合followeeSet即可
     2). 发送推文 postTweet：

     更新推文计数器postCount，维护tweetIdMap；

     向用户的推文发送列表中新增一条记录
     3). 获取推文推送 getNewsFeed：

     这里需要使用优先队列PriorityQueue，记为pq

     遍历用户的关注对象列表，将每一位关注对象的最新一条推文添加至pq中。

     然后从pq中弹出最近的一条推文，记为topTweetId；

     通过tweetOwnerMap找到这条推文的发送者userId，然后将该发送者的下一条比较新的推文添加至pq。

     直到弹出10条最新的推文，或者pq为空为止。

     */

    public class Twitter {
        /**
         * 需要定义一个表示每一条状态的，主要是时间id，以及重新定义排序的
         *
         * */
        private class Tweet {
            public int time;
            public int tweetId;
            public Tweet(int tweetId,int time){
                this.time = time;
                this.tweetId = tweetId;
            }

        }
        private int timeStamp ;
        //每个人发布的推特信息
        private HashMap<Integer,List<Tweet>> timelines;
        //人际关系，会重复follow所以要用set
        private HashMap<Integer,HashSet<Integer>> relations;
        /** Initialize your data structure here. */
        public Twitter() {
            this.timelines = new HashMap<Integer,List<Tweet>>();
            this.relations = new HashMap<Integer,HashSet<Integer>>();

        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            if(!timelines.containsKey(userId)){
                timelines.put(userId, new ArrayList<Tweet>());
            }
            timelines.get(userId).add(new Tweet(tweetId,timeStamp++));

        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        /**
         * 全部都选前10，然后按照时间戳排序，注意要选择自己的和别人的
         * */
        public List<Integer> getNewsFeed(int userId) {
            HashSet<Integer> followees = relations.get(userId);
            List<Tweet> candidates = new ArrayList<Tweet>();
            //分别选择，可以选择每个人的前10条就好
            List<Tweet> timeline = timelines.get(userId);
            if(timeline!=null){
                for(int i=timeline.size()-1;i>=Math.max(0,timeline.size()-10);i--){
                    candidates.add(timeline.get(i));
                }
            }
            if(followees != null){
                for(Integer followee:followees){
                    timeline = timelines.get(followee);
                    if(timeline == null)
                        continue;
                    for(int i=timeline.size()-1;i>=Math.max(0,timeline.size()-10);i--){
                        candidates.add(timeline.get(i));
                    }
                }
            }
            Collections.sort(candidates,new Comparator<Tweet> (){
                public int compare(Tweet o1, Tweet o2) {
                    return o2.time - o1.time;
                }
            });
            List<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<Math.min(10,candidates.size());i++){
                list.add(candidates.get(i).tweetId);
            }
            return list;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(followerId == followeeId) return;
            if(!relations.containsKey(followerId)){
                relations.put(followerId,new HashSet<Integer>());
            }
            relations.get(followerId).add(followeeId);

        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            HashSet<Integer> list = relations.get(followerId);
            if(list == null) return ;
            list.remove(followeeId);
        }
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */


}
