package Yelp;

import java.util.*;

/**
 * Created by cicean on 9/20/2018.
 */
public class WordLadder {

    /**
     * 单词接龙 可以从字典链接的最长
     * @param start
     * @param end
     * @param dict
     * @return
     */
    public int ladderLength(String start, String end, List<String> dict) {
        if (start == null || end == null || dict == null) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int result = 0;
        while(!queue.isEmpty()) {
            // level traversal
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String tmp = queue.poll();
                if (tmp.equals(end)) {
                    return result + 1;
                }
                for (int j = 0; j < tmp.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++){
                        StringBuilder sb = new StringBuilder(tmp);
                        sb.setCharAt(j,c);
                        String word = sb.toString();
                        if (word.equals(end)) {
                            return result + 2;
                        }

                        if(dict.contains(word)) {
                            dict.remove(word);
                            queue.offer(word);
                        }
                    }

                }
            }
            result++;
        }
        return 0;
    }


    /**
     * return all combination of words list
     * @param start
     * @param end
     * @param dict
     * @return
     */
    public List<List<String>> findLadders(String start, String end,
                                          Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);

        bfs(map, distance, start, end, dict);

        List<String> path = new ArrayList<String>();

        dfs(ladders, path, end, start, distance, map);

        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
             String start, Map<String, Integer> distance,
             Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<String>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) {
                    dfs(ladders, path, next, start, distance, map);
                }
            }
        }
        path.remove(path.size() - 1);
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
             String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }

        return expansion;
    }

}
