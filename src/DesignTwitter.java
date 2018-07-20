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
 Ҫ�����һ�����ݽṹ��ʹ��������twitter��4�ֻ���������
 ���ơ���ù�ע�û�����������10�����ġ���ע�û���ȡ����ע��
 */
public class DesignTwitter {

    /**
     * �������������Ƹ��򵥵����أ����з�����Ϣ����������£���ӹ�ע��ȡ����ע�ȹ��ܡ�
     * ������Ҫ��������ϣ����������һ���ǽ����û��������к���֮���ӳ�䣬
     * ��һ���ǽ����û�����������Ϣ֮���ӳ�䡣���ڻ������������Ҫ��ʱ��˳�����еģ�
     * ��ô���ǿ�����һ�����ͱ���cnt��ģ��ʱ��㣬ÿ��һ����Ϣ��cnt����1��
     * ��ô���Ǿ�֪��cnt�����������ġ���ô�����ڽ����û�����������Ϣ֮���ӳ��ʱ��
     * ����Ҫ����ÿ����Ϣ����ʱ���cnt֮���ӳ�䡣��������Ҫ�ѵ�����ʵ��getNewsFeed()������
     * ���������ȡ�Լ��ͺ��ѵ����10����Ϣ�����ǵ��������û�Ҳ��ӵ��Լ��ĺ����б��У�
     * Ȼ��������û������к��ѣ�����ÿ�����ѵ�������Ϣ��ά��һ����СΪ10�Ĺ�ϣ��
     * ����±���������Ϣ�ȹ�ϣ�����������ϢҪ��
     * ��ô�������Ϣ���룬Ȼ��ɾ����������Ǹ���Ϣ���������ǾͿ����ҳ����10����Ϣ�ˣ�
     */

    /**
     * Map + Set + PriorityQueue

     ϵͳӦ��ά��������Ϣ��

     1). һ��ȫ�ֵ����ļ�������postCount �û�������ʱ��������+1

     2). ����Id -> ���ļ�������ӳ�䣺tweetIdMap ������¼���ĵĴ���

     3). ����Id -> �û�Id��ӳ�䣺tweetOwnerMap ������¼���ĵķ�������˭

     4). �û�Id -> ��ע���󼯺ϵ�ӳ�䣺 followeeMap ������¼�û��Ĺ�ע���󣨹�ע/ȡ����ע��
     ������ʵ�֣�

     1). ��ע follow / ȡ����ע unfollow��

     ֻ��Ҫά��followeeMap�ж�Ӧ�Ĺ�ע���󼯺�followeeSet����
     2). �������� postTweet��

     �������ļ�����postCount��ά��tweetIdMap��

     ���û������ķ����б�������һ����¼
     3). ��ȡ�������� getNewsFeed��

     ������Ҫʹ�����ȶ���PriorityQueue����Ϊpq

     �����û��Ĺ�ע�����б���ÿһλ��ע���������һ�����������pq�С�

     Ȼ���pq�е��������һ�����ģ���ΪtopTweetId��

     ͨ��tweetOwnerMap�ҵ��������ĵķ�����userId��Ȼ�󽫸÷����ߵ���һ���Ƚ��µ����������pq��

     ֱ������10�����µ����ģ�����pqΪ��Ϊֹ��

     */

    public class Twitter {
        /**
         * ��Ҫ����һ����ʾÿһ��״̬�ģ���Ҫ��ʱ��id���Լ����¶��������
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
        //ÿ���˷�����������Ϣ
        private HashMap<Integer,List<Tweet>> timelines;
        //�˼ʹ�ϵ�����ظ�follow����Ҫ��set
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
         * ȫ����ѡǰ10��Ȼ����ʱ�������ע��Ҫѡ���Լ��ĺͱ��˵�
         * */
        public List<Integer> getNewsFeed(int userId) {
            HashSet<Integer> followees = relations.get(userId);
            List<Tweet> candidates = new ArrayList<Tweet>();
            //�ֱ�ѡ�񣬿���ѡ��ÿ���˵�ǰ10���ͺ�
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

            List<Integer> test = new ArrayList<>();

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
