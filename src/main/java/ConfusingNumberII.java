/**
 * 1088. Confusing Number II
 * Hard
 *
 * 93
 *
 * 35
 *
 * Add to List
 *
 * Share
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
 *
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)
 *
 * Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
 *
 *
 *
 * Example 1:
 *
 * Input: 20
 * Output: 6
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 * Example 2:
 *
 * Input: 100
 * Output: 19
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 *
 * Note:
 *
 * 1 <= N <= 10^9
 * Accepted
 * 7,335
 * Submissions
 * 17,984
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 16
 * Confusing Number
 * Easy
 * Only 0, 1, 6, 8, 9 are the valid set of digits, do a backtracking to generate all the numbers containing this digits
 * and check they are valid.
 */
public class ConfusingNumberII {

    class Solution {
        static char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
        public int confusingNumberII(int N) {
            String num = Integer.toString(N);
            int res = findTotal(num);
            for (int len = 1; len <= num.length(); len++) {
                char[] curr = new char[len];
                res -= dfs(curr, num, 0, len - 1);
            }
            return res;
        }
        // count the # of numbers from "01689" that is less than N
        private int findTotal(String s) {
            if (s.length() == 0) return 1;
            char first = s.charAt(0);
            int res = count(first) * (int) (Math.pow(5, s.length() - 1));
            if (first == '0' || first == '1' || first == '6' || first == '8' || first == '9') {
                res += findTotal(s.substring(1));
            }
            return res;
        }
        // count the # of Strobogrammatic numbers
        private int dfs(char[] curr, String num, int left, int right) {
            int res = 0;
            if (left > right) {
                String s = new String(curr);
                if (s.length() < num.length() || s.compareTo(num) <= 0) {
                    res += 1;
                }
            } else {
                for (char[] p : pairs) {
                    curr[left] = p[0];
                    curr[right] = p[1];
                    if ((curr[0] == '0' && curr.length > 1) || (left == right && p[0] != p[1])) continue;
                    res += dfs(curr, num, left + 1, right - 1);
                }
            }
            return res;
        }
        // a helper function that counts the # of chars in "01689" less than given 'c'
        private int count(Character c) {
            int res = 0;
            for (char[] p : pairs) {
                if (p[0] < c) res += 1;
            }
            return res;
        }
    }

    class Solution {

        Map<Integer, Integer> map;
        int N;
        int res;
        public int confusingNumberII(int N) {

            map = new HashMap<>();
            map.put(0, 0);
            map.put(1, 1);
            map.put(6, 9);
            map.put(8, 8);
            map.put(9, 6);
            res = 0;
            if (N == 1000000000) {
                res++;
                N--;
            }

            this.N = N;
            search(0, 0);
            return res;
        }

        private void search(int p, int cur) {
            if (p > 9 || cur > N) {
                return;
            }
            if (isConfusing(cur)) {
                res++;
            }
            for (Integer d : map.keySet()) {
                if (p == 0 && d == 0) {
                    continue;
                }
                search(p + 1, cur * 10 + d);
            }
        }

        private boolean isConfusing(int n) {
            long rot = 0;
            int tmp = n;
            while (n > 0) {
                rot = rot * 10 + map.get(n % 10);
                n /= 10;
            }
            return rot != tmp;
        }
    }
}
