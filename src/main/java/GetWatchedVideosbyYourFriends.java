import java.util.*;

/**
 * 1311. Get Watched Videos by Your Friends
 * Medium
 *
 * 57
 *
 * 84
 *
 * Add to List
 *
 * Share
 * There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.
 *
 * Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest path equal to k with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
 * Output: ["B","C"]
 * Explanation:
 * You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
 * Person with id = 1 -> watchedVideos = ["C"]
 * Person with id = 2 -> watchedVideos = ["B","C"]
 * The frequencies of watchedVideos by your friends are:
 * B -> 1
 * C -> 2
 * Example 2:
 *
 *
 *
 * Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
 * Output: ["D"]
 * Explanation:
 * You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).
 *
 *
 * Constraints:
 *
 * n == watchedVideos.length == friends.length
 * 2 <= n <= 100
 * 1 <= watchedVideos[i].length <= 100
 * 1 <= watchedVideos[i][j].length <= 8
 * 0 <= friends[i].length < n
 * 0 <= friends[i][j] < n
 * 0 <= id < n
 * 1 <= level < n
 * if friends[i] contains j, then friends[j] contains i
 * Accepted
 * 5,103
 * Submissions
 * 12,310
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * LeetCode
 * Do BFS to find the kth level friends.
 * Then collect movies saw by kth level friends and sort them accordingly.
 */

public class GetWatchedVideosbyYourFriends {

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int N = friends.length;
        boolean[] visited = new boolean[N]; // keeping list of visited frnds
        visited[id] = true;// i'm my own frnd

        ArrayList<Integer> q = new ArrayList<>();
        q.add(id);

        for (int k = 0; k < level; k++) {// depth less than level
            ArrayList<Integer> newQ = new ArrayList<>();
            for (int v : q) {
                for (int w : friends[v]) {// this is frnds of frnds
                    if (!visited[w]) {
                        visited[w] = true;
                        newQ.add(w);
                    }
                }
            }
            q = newQ;//change the list to own frnd of frnd
        }

        HashMap<String, Integer> freq = new HashMap<>();

        for (int person : q) {
            for (String v : watchedVideos.get(person)) {
                freq.put(v, 1 + freq.getOrDefault(v, 0));
            }
        }

        List<String> ans = new ArrayList<>(freq.keySet());

        ans.sort((a, b) -> {// custom sort
            int fa = freq.get(a);
            int fb = freq.get(b);
            if (fa != fb) {
                return fa - fb;
            } else {
                return a.compareTo(b);
            }
        });

        return ans;
    }

    public List<String> watchedVideosByFriends_1(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        List<String> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[friends.length];
        q.add(id);
        visited[id] = true;
        int k = 0;
        Map<String, Integer> frequent = new HashMap<>();
        while (!q.isEmpty() && k <= level) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int f = q.poll();
                if (k == level) {
                    for (String w : watchedVideos.get(f)) {
                        frequent.put(w, 1 + frequent.getOrDefault(w, 0));
                    }
                } else {
                    for (int ff : friends[f]) {
                        if (!visited[ff]) {
                            q.add(ff);
                            visited[ff] = true;
                        }

                    }
                }
            }
            k++;
        }

        ans = new ArrayList<>(frequent.keySet());
        System.out.println(Arrays.toString(ans.toArray()));
        ans.sort((a, b) -> {
            int fa = frequent.get(a);
            int fb = frequent.get(b);
            if (fa != fb) {
                return fa - fb;
            } else {
                return a.compareTo(b);
            }
        });

        return ans;
    }


}
