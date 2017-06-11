import java.util.*;

/*
 127	Word Ladder	19.3%	Medium
 Problem:    Word Ladder
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
考虑边界情况，如果dict为空，或start.equals(end)，则不满足BFS中循环的条件，在最后返回0.
如果是正常情况，start和end不等且dict包含转换需要的阶梯词组，那么转换次数加2，就是所求的转换序列长度。使用BFS，利用其按层次操作的性质，可以得到最优解。

第一层while循环：利用队列先进先出的原则，先用size = q.size()确定下一层for循环要从队列取出size个元素。这样可以保证这一层被完全遍历。当里面的三层for循环结束，即q的前size个元素全部遍历过之后，操作次数count++.
第二层for循环：对当前这一层的size个元素进行遍历。每次循环取出的元素存为新的字符串cur。
第三层for循环：遍历字符串cur的每个字符。
第四层for循环：将遍历到的cur的第i个字符换成从a到z的26个字母，存为新字符串word。然后查找dict里是否包含word：若存在，则从dict中删除此元素防止以后重复使用（无限循环），并将这个元素放入队列q，用于下一层的BFS循环。一旦找到和end相同的字符串，就返回转换序列长度 = 操作层数 + 2，即count+2。
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
                //里面的两次循环是将cur的每一位都替换成26个字母的情况，再去dict中查找
                for (int j = 0; j < cur.length(); j++) {
                    //在最内层的循环向q增加元素，并从dict里删除，体现bfs思想
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
	 * 主要解题思路的bfs，把每一种可能的character都放进去试，看能不能有一条线边到endWord. 
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
	 * 当然，这样的时间还不是最优化的，
	 * 如果我们从两头扫，扫到中间任何一个word能够串联起来都可以，如果没有找到可以串联的word,那么返回0。
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
