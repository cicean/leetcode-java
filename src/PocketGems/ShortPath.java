package PocketGems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cicean on 9/13/2016.
 */
public class ShortPath {

    //PocketGem 电面

    /**
     * 一開始問我知不知道任何Shortest Path的演算法，我想到了Dijkstra，但實在沒有認真讀過，所以跟他說沒有印象。
     * . Waral 鍗氬鏈夋洿澶氭枃绔?,
     * 接著給我一張地圖，像是以下這樣
     * ['02111',
     * '01001',
     * '01001',
     * '01003',]
     * <p>
     * <p>
     * 0: can't pass
     * 1: can pass-google 1point3acres
     * 2: starting point
     * 3: goal point
     */

    private class Position {
        int x;
        int y;

        public Position(int i, int j) {
            this.x = i;
            this.y = j;
        }
    }

    int res = Integer.MAX_VALUE;
    Set<Character> keys = new HashSet<>();

    public int shortpath(String[] map) {

        if (map == null) return 0;
        int lenth = 0;
        List<List<Position>> res = new ArrayList<>();
        List<Position> path = new ArrayList<>();
        boolean[][] visited = new boolean[map.length][map[0].length()];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length(); j++) {
                if (map[i].charAt(j) == '2' && findpath(i , j , visited, map, lenth)) {
                        return this.res;
                }
            }

        }
        return 0;
    }

    private boolean findpath(int i, int j, boolean[][] visited, String[] map, int lenth) {
                int m = map.length;
        int n = map[0].length();
        
        if (i < 0 || i >= m || j < 0 || j >= n) return false;
        System.out.println("i = " + i + ", j = " + j + ", char = " + String.valueOf(map[i].charAt(j)));
        if (map[i].charAt(j) == '3') {
            res = Math.min(res, lenth);
            return true;
        } 
        if (visited[i][j] || map[i].charAt(j) == '0') return false;
        if (!visited[i][j] && (map[i].charAt(j) == 'a' || map[i].charAt(j) == 'b')) this.keys.add(map[i].charAt(j));
        if (!visited[i][j] && map[i].charAt(j) == 'A') {
        	System.out.println("gate A");
        	if (!keys.contains('a')) {
        		System.out.println("No Key 'a'");
        		return false;
        	}
        }
        if (!visited[i][j] && map[i].charAt(j) == 'B') {
        	System.out.println("gate B");
        	if (!this.keys.contains('b')) {
        		System.out.println("No Key 'b'");
        		return false;
        	}
        }
        
        visited[i][j] = true;
        lenth++;
        System.out.println(", count = " + lenth);
        if (findpath(i + 1, j, visited, map, lenth)) return true;
        if (findpath(i - 1, j, visited, map, lenth)) return true;
        if (findpath(i, j + 1, visited, map, lenth)) return true;
        if (findpath(i, j - 1, visited, map, lenth)) return true;
        visited[i][j] = false;
        lenth--;
        return false;
    }

    public static void main(String[] args) {
        String[] map = {"02111",
                "01001",
                "01001",
                "01003"};
        
        String[] map2 = {"02111",
                         "01001",
                         "00001",
                         "00ab1",
                         "11A11",
                         "1000B", 
                         "11001",
                         "01003"};
        
        ShortPath slt = new ShortPath();
        System.out.println(slt.shortpath(map2));
        System.out.println(slt.keys.contains('b'));
    }


}
