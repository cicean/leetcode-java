import java.util.*;

/*
 68	Text Justification	14.6%	Hard
 Problem:    Text Justification
 Difficulty: Hard
 Source:     https://oj.leetcode.com/problems/text-justification/
 Notes:
 Given an array of words and a length L, format the text such that each line has exactly L 
 characters and is fully (left and right) justified.
 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 Extra spaces between words should be distributed as evenly as possible. If the number of spaces 
 on a line do not divide evenly between words, the empty slots on the left will be assigned more 
 spaces than the slots on the right.
 For the last line of text, it should be left justified and no extra space is inserted between words.
 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.
 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Note: Each word is guaranteed not to exceed L in length.
 Corner Cases:
 A line other than the last line might contain only one word. What should you do in this case?
 In this case, that line should be left-justified.
 Solution: ...
 */

public class TextJustification {
	public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<String>();
        int i = 0, N = words.length;
        while (i < N) {
            int length = words[i].length();
            int j = i + 1;
            while (j < N && length + words[j].length() + (j-i) <= L) {
                length += words[j++].length();
            }
            StringBuilder s = new StringBuilder(words[i]);
            boolean isLastLine = (j==N);
            boolean oneWord = (j == i + 1);
            int average = (isLastLine || oneWord) ? 1 : (L-length)/(j-i-1);
            int extra = (isLastLine || oneWord) ? 0 : (L-length)%(j-i-1);
            for (int k = i + 1; k < j; ++k) {
                char[] tmp = new char[extra>0?average+1:average];
                Arrays.fill(tmp, ' ');
                s.append(tmp);
                s.append(words[k]);
                extra--;
            }
            char[] tmp = new char[L - s.length()];
            Arrays.fill(tmp, ' ');
            s.append(tmp);
            res.add(s.toString());
            i = j;
        }
        return res;
    }


    /**
     * Date 05/07/2015
     * @author tusroy
     *
     * Video link - https://youtu.be/RORuwHiblPc
     *
     * Given a sequence of words, and a limit on the number of characters that can be put
     * in one line (line width). Put line breaks in the given sequence such that the
     * lines are printed neatly
     *
     * Solution:
     * Badness - We define badness has square of empty spaces in every line. So 2 empty space
     * on one line gets penalized as 4 (2^2) while 1 each empty space on 2 lines gets
     * penalized as 2(1 + 1). So we prefer 1 empty space on different lines over 2 empty space on
     * one line.
     *
     * For every range i,j(words from i to j) find the cost of putting them on one line. If words
     * from i to j cannot fit in one line cost will be infinite. Cost is calculated as square of
     * empty space left in line after fitting words from i to j.
     *
     * Then apply this formula to get places where words need to be going on new line.
     * minCost[i] = minCost[j] + cost[i][j-1]
     * Above formula will try every value of j from i to len and see which one gives minimum
     * cost to split words from i to len.
     *
     * Space complexity is O(n^2)
     * Time complexity is O(n^2)
     *
     * References:
     * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
     */
    public String justify(String words[], int width) {

        int cost[][] = new int[words.length][words.length];

        //next 2 for loop is used to calculate cost of putting words from
        //i to j in one line. If words don't fit in one line then we put
        //Integer.MAX_VALUE there.
        for(int i=0 ; i < words.length; i++){
            cost[i][i] = width - words[i].length();
            for(int j=i+1; j < words.length; j++){
                cost[i][j] = cost[i][j-1] - words[j].length() - 1;
            }
        }

        for(int i=0; i < words.length; i++){
            for(int j=i; j < words.length; j++){
                if(cost[i][j] < 0){
                    cost[i][j] = Integer.MAX_VALUE;
                }else{
                    cost[i][j] = (int)Math.pow(cost[i][j], 2);
                }
            }
        }

        //minCost from i to len is found by trying
        //j between i to len and checking which
        //one has min value
        int minCost[] = new int[words.length];
        int result[] = new int[words.length];
        for(int i = words.length-1; i >= 0 ; i--){
            minCost[i] = cost[i][words.length-1];
            result[i] = words.length;
            for(int j=words.length-1; j > i; j--){
                if(cost[i][j-1] == Integer.MAX_VALUE){
                    continue;
                }
                if(minCost[i] > minCost[j] + cost[i][j-1]){
                    minCost[i] = minCost[j] + cost[i][j-1];
                    result[i] = j;
                }
            }
        }
        int i = 0;
        int j;

        System.out.println("Minimum cost is " + minCost[0]);
        System.out.println("\n");
        //finally put all words with new line added in
        //string buffer and print it.
        StringBuilder builder = new StringBuilder();
        do{
            j = result[i];
            for(int k=i; k < j; k++){
                builder.append(words[k] + " ");
            }
            builder.append("\n");
            i = j;
        }while(j < words.length);

        return builder.toString();
    }

    public class Solution_jiuzhang {
        public ArrayList<String> fullJustify(String[] words, int L) {
            int wordsCount = words.length;
            ArrayList<String> result = new ArrayList<String>();
            int curLen = 0;
            int lastI = 0;
            for (int i = 0; i <= wordsCount; i++) {
                if (i == wordsCount || curLen + words[i].length() + i - lastI > L) {
                    StringBuffer buf = new StringBuffer();
                    int spaceCount = L - curLen;
                    int spaceSlots = i - lastI - 1;
                    if (spaceSlots == 0 || i == wordsCount) {
                        for(int j = lastI; j < i; j++){
                            buf.append(words[j]);
                            if(j != i - 1)
                                appendSpace(buf, 1);
                        }
                        appendSpace(buf, L - buf.length());
                    } else {
                        int spaceEach = spaceCount / spaceSlots;
                        int spaceExtra = spaceCount % spaceSlots;
                        for (int j = lastI; j < i; j++) {
                            buf.append(words[j]);
                            if (j != i - 1)
                                appendSpace(buf, spaceEach + (j - lastI < spaceExtra ? 1 : 0));
                        }
                    }
                    result.add(buf.toString());
                    lastI = i;
                    curLen = 0;
                }
                if (i < wordsCount)
                    curLen += words[i].length();
            }
            return result;
        }

        private void appendSpace(StringBuffer sb, int count) {
            for (int i = 0; i < count; i++)
                sb.append(' ');
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextJustification slt = new TextJustification();
		String[] strs = {"This", "is", "an", "example", "of", "text", "justification."};
		int L = 16;
		List<String> res = slt.fullJustify(strs, L);
		for (String str : res) {
			System.out.println(str);
		}
	}

}
