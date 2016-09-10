/*
 28	Implement strStr()	22.2%	Easy
 Problem:    Implement strStr()
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/implement-strstr/
 Notes:
 Implement strStr().
 Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
 Solution: 1. Check in the haystack one by one. If not equal to needle, reset the pointer.(TLE) �㷨ʱ�临�Ӷ�ΪO((n-m+1)*m)=O(n*m)���ռ临�Ӷ���O(1)
           2. Classice KMP solution.
           3. Simplified RK Soluiton. Thanks for [wenyuanhust, wenyuanhust@gmail.com]
 */


public class ImplementstrStr {

    /**
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int index = -1;
        if (haystack == null || needle == null) {
            return -1;
        }

        if (needle.length() != 0) {
            for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
                if (haystack.substring(i, i + needle.length()).equals(needle)) {
                    index = i;
                    break;
                }
            }
        } else {
            return 0;
        }

        return index;
    }

    /**
     * ���Ӷ�
     * ʱ�� O(N^2) �ռ� O(1)
     * <p>
     * ˼·
     * �����кܶ�߼��㷨������O(N)ʱ���ڽ�����⣬Ȼ�����Ѿ��������Եķ��롣
     * �����������г��ֵ����þ��ǿ�������ı���������Լ��߽������Ŀ��ǡ������ñ��������ɡ�
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr_1(String haystack, String needle) {
        int sLen = haystack.length(), tLen = needle.length();
        if (tLen == 0) return 0;
        if (haystack == null || needle == null || sLen == 0) return -1;
        int i = 0, j = 0;
        while (i < sLen) {
            for (j = 0; j < tLen && (i + j) < sLen && haystack.charAt(i + j) == needle.charAt(j); ++j) ;
            if (j == tLen) return i;
            ++i;
        }
        return (int) -1;
    }

    /**
     * P�㷨
     * ���Ӷ�
     * ʱ�� O(N+M) �ռ� O(M)
     * <p>
     * ˼·
     * KMP�㷨�ǽ�Ϊ�߼����㷨����ʹ��һ��next���飬��������¼��ģʽ��needle�����ǰ׺�ͺ�׺���ظ������ͬ����˫ָ�����ƥ�䣬��ʧ��ʱ���Ը�����������ҵ�Ӧ�ý�ģʽ�����λ�ƶ���λ������һЩ�ظ��ıȽϡ�����Ľⷨ��������ƪ���±ȽϺã�һƪ����ϸ����KMP�㷨��
     * <p>
     * �������Բ���next����ķ�������̫���ף�����һƪ�ǽ���next������ô�õ��ġ�
     * <p>
     * ����
     *
     * @param T
     * @param next
     */

    // kmp
    void getNext(String T, int[] next) {
        int i = 0, j = -1;
        next[0] = -1;
        int n = next.length;
        while (i < n - 1) {
            while (j > -1 && T.charAt(j) != T.charAt(i)) j = next[j];
            ++i;
            ++j;
            if (i < n - 1 && j < n - 1 && T.charAt(j) == T.charAt(i)) next[i] = next[j];
            else next[i] = j;
        }
    }


    public int strStr_2(String haystack, String needle) {
        int sLen = haystack.length(), tLen = needle.length();
        if (tLen == 0) return 0;
        if (haystack == null || needle == null || sLen == 0) return -1;
        int[] next = new int[tLen + 1];
        getNext(needle, next);
        int i = 0, j = 0;
        while (i < sLen) {
            while (j > -1 && needle.charAt(j) != haystack.charAt(i)) j = next[j];
            ++i;
            ++j;
            if (j == tLen) return i - j;
        }
        return -1;
    }


    public int strStr_3(String haystack, String needle) {
        int sLen = haystack.length(), tLen = needle.length();
        if (tLen == 0) return 0;
        if (haystack == null || needle == null || sLen == 0 || sLen < tLen) return -1;
        long fh = 0, fn = 0;
        int head = 0, tail = tLen - 1;
        for (int i = 0; i < tLen; ++i) {
            fh += haystack.charAt(i);
            fn += needle.charAt(i);
        }
        while (tail < sLen) {
            if (fn == fh) {
                int i = 0;
                while (i < tLen && needle.charAt(i) == haystack.charAt(i + head)) {
                    ++i;
                }
                if (i == tLen) return head;
            }
            if (tail == sLen - 1) return -1;
            fh -= haystack.charAt(head++);
            fh += haystack.charAt(++tail);
        }
        return -1;
    }

    //strstr��Ψһ������ǲ�����char array��
    // ������string����������ȻҲ����stringbuilder����
    // ������hackstack���һ��ƥ��needle��substring�ĵ�һ���ַ��±꣬
    //O(N^2) �ռ� O(1)
    public int strStr_fb(char[] haystack, char[] needle) {
        int sLen = haystack.length, tLen = needle.length;
        if (tLen == 0) return 0;
        if (haystack == null || needle == null || sLen == 0) return -1;
        int i = 0, j = 0;
        while (i < sLen) {
            for (j = 0; j < tLen && (i + j) < sLen && haystack[i + j] == needle[j]; ++j) ;
            if (j == tLen) return i;
            ++i;
        }
        return (int) -1;
    }

    //facebook follow up
    //������ǰѵ�һ��haystack�������һ����ά����
    public int strStr_fb(char[][] haystack, char[] needle) {
        if (haystack == null || needle == null ||
                haystack.length == 0 || haystack[0].length == 0
                || needle.length == 0) return (int) -1;
        int len = 0;
        for (int i = 0; i < haystack.length; i++) {
            len = Math.max(len,haystack[i].length);
        }
        boolean[][] visited = new boolean[haystack.length][len];
        int index = -1;
        for (int i = 0; i < haystack.length; ++i) {
            for (int j = 0; j < haystack[i].length; ++j) {
            	System.out.println("i = " + i + ", j = " + j);
            	index++;
                if (haystack[i][j] == needle[0] && existRe2(haystack, i, j, needle, 0, visited)) {
                    return index;
                }
                System.out.println(haystack[i][j] + "Index = " + index);
            }
        }

        return (int) -1;
    }


    public boolean existRe(char[][] board, int i, int j, char[] word, int cur, boolean[][] visited) {
        if (cur == word.length) return true;
        int m = board.length;
        if (i < 0 || i >=m) return false;
        int n = board[i].length;
        if (j < 0 || j >= n) return false;
        if (visited[i][j] == true || (board[i][j] != word[cur])) return false;
        visited[i][j] = true;
        if (existRe(board, i+1, j, word, cur+1,visited)) return true;
        if (existRe(board, i-1, j, word, cur+1,visited)) return true;
        if (existRe(board, i, j+1, word, cur+1,visited)) return true;
        if (existRe(board, i, j-1, word, cur+1,visited)) return true;
        visited[i][j] = false;
        return false;
    }
    
    public boolean existRe2(char[][] board, int i, int j, char[] word, int cur, boolean[][] visited) {
        if (cur == word.length) return true;
        int m = board.length;
        if (i < 0 || i >=m) return false;
        int n = board[i].length;
        if (j < 0 || j >= n) return false;
        if (visited[i][j] == true || (board[i][j] != word[cur])) return false;
        visited[i][j] = true;
        if (existRe(board, i, j+1, word, cur+1,visited)) return true;
        if (j == n - 1) {
        if (existRe(board, i + 1, 0 , word, cur+1,visited)) return true;
        }
        visited[i][j] = false;
        return false;
    }
    
    


    public static void main(String[] args) {
        ImplementstrStr slt = new ImplementstrStr();
        System.out.println(slt.strStr_2("abcdabc", "d"));
        char[][] haystack = new char[][] {{'a', 'b', 'c', 'c'}, {'c', 'b', 'd'}};
        char[] needle = new char[] {'b', 'd'};

        System.out.print(slt.strStr_fb(haystack,needle));
    }


}

/**
 *
 */
