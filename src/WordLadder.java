import java.util.*;

/*
 127	Word Ladder	19.3%	Medium
 Problem:    sWord Ladder
 Difficulty: High
 Source:     https://oj.leetcode.com/problems/word-ladder/
 Notes:
 Given two words (start and end), and a dictionary, find the length of shortest transformation 
 sequence from start to end, such that:
 Only one letter can be changed at a time
 Each intermediate word must exist in the dictionary
 For example,
 Given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.
 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 Solution: BFS.
*/

public class WordLadder {
	
	/**
	 * Note
���Ǳ߽���������dictΪ�գ���start.equals(end)��������BFS��ѭ��������������󷵻�0.
��������������start��end������dict����ת����Ҫ�Ľ��ݴ��飬��ôת��������2�����������ת�����г��ȡ�ʹ��BFS�������䰴��β��������ʣ����Եõ����Ž⡣

��һ��whileѭ�������ö����Ƚ��ȳ���ԭ������size = q.size()ȷ����һ��forѭ��Ҫ�Ӷ���ȡ��size��Ԫ�ء��������Ա�֤��һ�㱻��ȫ�����������������forѭ����������q��ǰsize��Ԫ��ȫ��������֮�󣬲�������count++.
�ڶ���forѭ�����Ե�ǰ��һ���size��Ԫ�ؽ��б�����ÿ��ѭ��ȡ����Ԫ�ش�Ϊ�µ��ַ���cur��
������forѭ���������ַ���cur��ÿ���ַ���
���Ĳ�forѭ��������������cur�ĵ�i���ַ����ɴ�a��z��26����ĸ����Ϊ���ַ���word��Ȼ�����dict���Ƿ����word�������ڣ����dict��ɾ����Ԫ�ط�ֹ�Ժ��ظ�ʹ�ã�����ѭ�������������Ԫ�ط������q��������һ���BFSѭ����һ���ҵ���end��ͬ���ַ������ͷ���ת�����г��� = �������� + 2����count+2��
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public int ladderLength_4(String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList();
        q.offer(start);
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                //if give the start = end
                if (cur.equals(end)) {
                    return count + 1;
                }
                //���������ѭ���ǽ�cur��ÿһλ���滻��26����ĸ���������ȥdict�в���
                for (int j = 0; j < cur.length(); j++) {
                    //�����ڲ��ѭ����q����Ԫ�أ�����dict��ɾ��������bfs˼��
                    for (char c = 'a'; c <= 'z'; c++) {
                        StringBuilder sb = new StringBuilder(cur);
                        sb.setCharAt(j, c);
                        String word = sb.toString();
                        if (end.equals(word)) return count+2;
                        else if (dict.contains(word)) {
                            dict.remove(word);
                            q.offer(word);
                        }
                    }
                }
            }
            count++;
        }
        return 0;
    }
	/**
	 * ��Ҫ����˼·��bfs����ÿһ�ֿ��ܵ�character���Ž�ȥ�ԣ����ܲ�����һ���߱ߵ�endWord. 
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	
	public class Solution {


        //Java Solution using Dijkstra's algorithm
	    public int ladderLength_1(String beginWord, String endWord, Set<String> wordList) {
	        //BFS to solve the problem
	        int count = 1;
	        Set<String> reached = new HashSet<String>();
	        reached.add(beginWord);
	        wordList.add(endWord);
	        
	        while (!reached.contains(endWord)) {
	            Set<String> toAdd = new HashSet<String>();
	            for (String word : reached) {
	                
	                for (int i = 0; i < word.length(); i++) {
	                    char[] chars = word.toCharArray();
	                    for (char c = 'a'; c <= 'z'; c++) {
	                        chars[i] = c;
	                        String newWord = String.valueOf(chars);
	                        if (wordList.contains(newWord)) {
	                            toAdd.add(newWord);
	                            wordList.remove(newWord);
	                        }
	                    }
	                }
	            }
	            count++;
	            if (toAdd.size() == 0) return 0;
	            reached = toAdd;
	        }
	        return count;
	    }
	}
	
	/**
	 * ��Ȼ��������ʱ�仹�������Ż��ģ�
	 * ������Ǵ���ͷɨ��ɨ���м��κ�һ��word�ܹ��������������ԣ����û���ҵ����Դ�����word,��ô����0��
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	
	    public int ladderLength_(String beginWord, String endWord, Set<String> wordList) {
	        int count = 1;
	        Set<String> beginSet = new HashSet<String>();
	        Set<String> endSet = new HashSet<String>();
	        Set<String> visited = new HashSet<String>();
	        beginSet.add(beginWord);
	        endSet.add(endWord);
	        
	        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
	            
	            
	            if (beginSet.size() > endSet.size()) {
	                Set<String> temp = beginSet;
	                beginSet = endSet;
	                endSet = temp;
	            }
	            
	            Set<String> toAdd = new HashSet<String>();
	            for (String word : beginSet) {
	                for (int i = 0; i < word.length(); i++) {
	                    char[] chars = word.toCharArray();
	                    for (char c = 'a'; c <= 'z'; c++) {
	                        chars[i] = c;
	                        String newWord = String.valueOf(chars);
	                        if (endSet.contains(newWord)) return count + 1;
	                        if (!visited.contains(newWord) && wordList.contains(newWord)) {
	                            toAdd.add(newWord);
	                            visited.add(newWord);
	                        }
	                    }
	                }
	            }
	            count++;
	            beginSet = toAdd;
	        }
	        return 0;
	    }
	
	
	public int ladderLength(String start, String end, Set<String> dict) {
        Queue<String> cur = new LinkedList<String>();
        if(start.compareTo(end) == 0) return 0;
        cur.offer(start);
        int depth = 1;
        Set<String> visited = new HashSet<String>();
        while (cur.isEmpty() == false) {
            Queue<String> queue = new LinkedList<String>();
            while(cur.isEmpty() == false) {
                String str = cur.poll();
                char[] word = str.toCharArray();
                for (int i = 0; i < word.length; ++i) {
                    char before = word[i];
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        word[i] = ch;
                        String temp = new String(word);
                        if (end.compareTo(temp) == 0) return depth + 1;
                        if (dict.contains(temp) == true && visited.contains(temp) == false) {
                            queue.offer(temp);
                            visited.add(temp);
                        }
                    }
                    word[i] = before;
                }
            }
            cur = queue;
            ++depth;
        }
        return 0;
    }
	
	
	public int ladderLength_2(String beginWord, String endWord, Set<String> wordDict) {
        Queue<String> fq = new LinkedList<>();
        Queue<String> bq = new LinkedList<>();
        Set<String> fVisited = new HashSet<>();
        Set<String> bVisited = new HashSet<>();
        
        int fDist = 1;
        int bDist = 1;
        fq.offer(beginWord);
        fVisited.add(beginWord);
        bq.offer(endWord);
        bVisited.add(endWord);
        
        while (!fq.isEmpty() && !bq.isEmpty()) {
            if (fq.size() < bq.size()) {
                int size = fq.size();
                for (int i = 0; i < size; ++i) {
                    String word = fq.poll();
                    
                    for (int j = 0; j < word.length(); ++j) {
                        for (char c = 'a'; c <= 'z'; ++c) {
                            char[] newChars = word.toCharArray();
                            newChars[j] = c;
                            String newWord = new String(newChars);
                            
                            if (wordDict.contains(newWord) && fVisited.add(newWord)) {
                                if (bVisited.contains(newWord)) {
                                    return fDist + bDist;
                                }
                                
                                fq.offer(newWord);
                            }
                        }
                    }
                }
                
                ++fDist;
            } else {
                int size = bq.size();
                for (int i = 0; i < size; ++i) {
                    String word = bq.poll();
                    
                    for (int j = 0; j < word.length(); ++j) {
                        for (char c = 'a'; c <= 'z'; ++c) {
                            char[] newChars = word.toCharArray();
                            newChars[j] = c;
                            String newWord = new String(newChars);
                            
                            if (wordDict.contains(newWord) && bVisited.add(newWord)) {
                                if (fVisited.contains(newWord)) {
                                    return fDist + bDist;
                                }
                                
                                bq.offer(newWord);
                            }
                        }
                    }
                }
                
                ++bDist;
            }
        }
        
        return 0;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String start = "hit";
		String end = "cog";
		String[] dicts = { "hot","dot","dog","lot","log" };
		Set<String> dict = new HashSet<String>();
		for (int i = 0; i < dicts.length; i++) {
			dict.add(dicts[i]);
		}
		WordLadder slt=new WordLadder();
		
		System.out.print(slt.ladderLength(start, end, dict));
		
		
		//List<List<String>> res = findLadders(start, end, dict);
	//	for (List<String> l : res) {
		//	for (String s : l) System.out.print(s + " ");
	//		System.out.println();
		//}
	//}
	}

}
