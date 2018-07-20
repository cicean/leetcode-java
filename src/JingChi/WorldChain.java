package JingChi;

import java.util.*;

public class WorldChain {

    public boolean canWorldChain_1(String[] words) {
        if (words == null || words.length == 0) {
            return false;
        }

        Map<Character, List<Character>> map = new HashMap<>();

        for (String word : words) {
            if (word.length() < 1) {
                continue;
            }
            char firstc = word.charAt(0);
            char lastc = word.charAt(word.length() - 1);
            System.out.println("first letter = " + firstc);
            map.computeIfAbsent(firstc, x -> new ArrayList<Character>()).add(lastc);
        }


        Queue<Character> queue = new LinkedList<>();

        if (!words[0].isEmpty()) {
            queue.offer(words[0].charAt(0));
        }
        char start = queue.peek();
        char end = 0;
        while (!queue.isEmpty()) {
            char c = queue.poll();
            end = c;
            System.out.println("current queue = " + c);
            if (map.containsKey(c)) {
                List<Character> value = map.get(c);
                for (Character last : value) {
                    queue.offer(last);
                }
                map.remove(c);
            }
        }

        if (start == end) {
            return true;
        }

        return false;
    }

    public boolean canWorldChain(String[] words) {
        if (words == null || words.length == 0) {
            return false;
        }

        int[] parent = new int[27];
        Arrays.fill(parent, -1);
        for (String word : words) {
            if (word.length() < 1) {
                continue;
            }
            char firstc = word.charAt(0);
            char lastc = word.charAt(word.length() - 1);
            union(parent, lastc - 'a', firstc - 'a');
        }

        for (int i : parent) {
            System.out.println("i = " + i);
        }

        return false;
    }

    int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        if (xset != yset)
            parent[xset] = yset;
    }
}
