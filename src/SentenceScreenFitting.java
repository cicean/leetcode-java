/**
 * Created by cicean on 10/16/2016.
 * 418. Sentence Screen Fitting   QuestionEditorial Solution  My Submissions
 Total Accepted: 1063
 Total Submissions: 4462
 Difficulty: Medium
 Contributors: Admin
 Given a rows x cols screen and a sentence represented by a list of words, find how many times the given sentence can be fitted on the screen.

 Note:

 A word cannot be split into two lines.
 The order of words in the sentence must remain unchanged.
 Two consecutive words in a line must be separated by a single space.
 Total words in the sentence won't exceed 100.
 Length of each word won't exceed 10.
 1 ¡Ü rows, cols ¡Ü 20,000.
 Example 1:

 Input:
 rows = 2, cols = 8, sentence = ["hello", "world"]

 Output:
 1

 Explanation:
 hello---
 world---

 The character '-' signifies an empty space on the screen.
 Example 2:

 Input:
 rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

 Output:
 2

 Explanation:
 a-bcd-
 e-a---
 bcd-e-

 The character '-' signifies an empty space on the screen.
 Example 3:

 Input:
 rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

 Output:
 1

 Explanation:
 I-had
 apple
 pie-I
 had--

 The character '-' signifies an empty space on the screen.
 Hide Company Tags Google
 Hide Tags Dynamic Programming

 */
public class SentenceScreenFitting {

    /**
     * Let me explain a little to help others understand this solution well.

     String s = String.join(" ", sentence) + " " ;. This line gives us a formatted sentence to be put to our screen.
     start is the counter for how many valid characters from s have been put to our screen.
     if (s.charAt(start % l) == ' ') is the situation that we don't need an extra space for current row. The current row could be successfully fitted. So that we need to increase our counter by using start++.
     The else is the situation, which the next word can't fit to current row. So that we need to remove extra characters from next word.
     start / s.length() is (# of valid characters) / our formatted sentence.
     12 days ago reply quote

     * @param sentence
     * @param rows
     * @param cols
     * @return
     */

    //Optimized to 12ms. O(m + n), m: length of sentence by char, n: rows.
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int len = s.length(), count = 0;
        int[] map = new int[len];
        for (int i = 1; i < len; ++i) {
            map[i] = s.charAt(i) == ' ' ? 1 : map[i-1] - 1;
        }
        for (int i = 0; i < rows; ++i) {
            count += cols;
            count += map[count % len];
        }
        return count / len;
    }
}
