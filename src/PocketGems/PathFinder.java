package PocketGems;

import java.util.*;

/**
 * Created by cicean on 9/9/2016.
 * ��һ�⣺Path Finder-google 1point3acres
 ����ͼ��ͼ�п��ܴ��ڻ�����Source��Destination������·����DFS + backtracking ���ģ��ο� http://www.geeksforgeeks.org/find-paths-given-source-destination/
 ������ʽ�ǣ�vector<string> lines.
 A J
 A : B C
 B : C E G
 C : A F
 D : C J
 F : B H
 G : C D
 H : A B F I
 I : B

 �����ʽ:["ABGDJ", "ACFBGDJ", "ACFHBGDJ", "ACFHIBGDJ"] vector<string>
 */
public class PathFinder {

    public List<String> pathFinder(List<String> lines) {
        List<String> res = new ArrayList<>();

        if (lines == null || lines.size() == 0) return res;

        Set<Character> visited = new HashSet<>();
        Map<Character, char[]> map = new HashMap<>();
        char start = 'A', end = 'Z';
        for (int i = 0; i < lines.size(); i++) {
            String tmp = lines.get(i).replaceAll("\\W", "");
            if (i == 0) {
                if (tmp.length() != 2) {
                    return res;
                }
                start = tmp.charAt(0);
                end = tmp.charAt(1);
            }
            map.put(tmp.charAt(0), tmp.substring(1,tmp.length()).toCharArray());
        }
        dfs(start, end, "", visited, map, res);
        return res;
    }
    private void dfs(char start, char end, String path, Set<Character> visited, Map<Character, char[]> map, List<String> res) {
    	
    	path += String.valueOf(start);
    	visited.add(start);
    	
    	if (start == end) {
    		res.add(path);
    	} else {
    		char[] adj = map.get(start);
        	if (adj != null) {
        		for (char c : adj) {
        			if (!visited.contains(c)) {
        				dfs(c, end, path, visited, map, res);
        				
        			}
        		}
        	}
    	}
    	visited.remove(start);
    }


    
    public static void main(String[] args) {
		String test = "A C";
		String test2 = "A : B C";
        String test3 = "B : C";
		List<String> file = new ArrayList<>();
		file.add(test);
		file.add(test2);
		//System.out.println(test.replaceAll("\\W", ""));
		//System.out.println(test2.replaceAll("\\W", ""));
		test2 = test2.replaceAll("\\W", "");
		//System.out.println(test2.substring(1, test2.length()));
		PathFinder slt = new PathFinder();
		List<String> reStrings = slt.pathFinder(file);
		for (String string : reStrings) {
			System.out.println(string);
		}
	}

}
