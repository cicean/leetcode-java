import java.util.List;

/**
 * 722. Remove Comments
 * Medium
 * 239
 * 727
 * Add to List
 * Share
 * Given a C++ program, remove comments from it. The program source is an array where source[i] is the i-th line of the source code.
 * This represents the result of splitting the original source code string by the newline character \n.
 * In C++, there are two types of comments, line comments, and block comments.
 * The string “” denotes a line comment, which represents that it and rest of the characters to the right of it in the same line should be ignored.
 * The string “” denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of “” should be ignored.
 * (Here, occurrences happen in reading order: line by line from left to right.) To be clear, the string “”/ does not yet end the block comment,
 * as the ending would be overlapping the beginning.
 * The first effective comment takes precedence over others: if the string “” occurs in a block comment, it is ignored.
 * Similarly, if the string “” occurs in a line or block comment, it is also ignored.
 * If a certain line of code is empty after removing comments, you must not output that line: each string in the answer list will be non-empty.
 * There will be no control characters, single quote, or double quote characters. For example, source = "string s = "“” Not a comment. “”";"
 * will not be a test case. (Also, nothing else such as defines or macros will interfere with the comments.)
 * It is guaranteed that every open block comment will eventually be closed, so “” outside of a line or block comment always starts a new comment.
 * Finally, implicit newline characters can be deleted by block comments. Please see the examples below for details.
 * After removing the comments from the source code, return the source code in the same format.
 */

public class RemoveComments {
    /**
     * Approach #1: Parsing [Accepted]
     * Intuition and Algorithm
     *
     * We need to parse the source line by line. Our state is that we either are in a block comment or not.
     *
     * If we start a block comment and we aren't in a block, then we will skip over the next two characters and change our state to be in a block.
     *
     * If we end a block comment and we are in a block, then we will skip over the next two characters and change our state to be not in a block.
     *
     * If we start a line comment and we aren't in a block, then we will ignore the rest of the line.
     *
     * If we aren't in a block comment (and it wasn't the start of a comment), we will record the character we are at.
     *
     * At the end of each line, if we aren't in a block, we will record the line.
     * Complexity Analysis
     *
     * Time Complexity: O(S)O(S), where SS is the total length of the source code.
     *
     * Space Complexity: O(S)O(S), the space used by recording the source code into ans.
     */

    class Solution {
        public List<String> removeComments(String[] source) {
            boolean inBlock = false;
            StringBuilder newline = new StringBuilder();
            List<String> ans = new ArrayList();
            for (String line: source) {
                int i = 0;
                char[] chars = line.toCharArray();
                if (!inBlock) newline = new StringBuilder();
                while (i < line.length()) {
                    if (!inBlock && i+1 < line.length() && chars[i] == '/' && chars[i+1] == '*') {
                        inBlock = true;
                        i++;
                    } else if (inBlock && i+1 < line.length() && chars[i] == '*' && chars[i+1] == '/') {
                        inBlock = false;
                        i++;
                    } else if (!inBlock && i+1 < line.length() && chars[i] == '/' && chars[i+1] == '/') {
                        break;
                    } else if (!inBlock) {
                        newline.append(chars[i]);
                    }
                    i++;
                }
                if (!inBlock && newline.length() > 0) {
                    ans.add(new String(newline));
                }
            }
            return ans;
        }
    }

    class Solution {
        public List<String> removeComments(String[] source) {
            List<String> res = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean inBlockComment = false;

            for (String s : source) {
                for (int i = 0; i < s.length(); i++) {
                    if (!inBlockComment) {
                        if (i + 1 < s.length() && s.charAt(i) == '/' && s.charAt(i + 1) == '/') break;
                        else if (i + 1 < s.length() && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                            inBlockComment = true;
                            i++;
                        } else {
                            sb.append(s.charAt(i));
                        }
                    } else {
                        if (i + 1 < s.length() && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                            inBlockComment = false;
                            i++;
                        }
                    }
                }

                if (!inBlockComment && sb.length() > 0) {
                    res.add(sb.toString());
                    sb = new StringBuilder();
                }
            }

            return res;
        }
    }
}
