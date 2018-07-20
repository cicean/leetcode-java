import java.util.*;

/**
 * 859. Buddy Strings
 * DescriptionHintsSubmissionsDiscussSolution
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: true
 * Example 2:
 *
 * Input: A = "ab", B = "ab"
 * Output: false
 * Example 3:
 *
 * Input: A = "aa", B = "aa"
 * Output: true
 * Example 4:
 *
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 *
 * Input: A = "", B = "aa"
 * Output: false
 *
 *
 * Note:
 *
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist only of lowercase letters.
 */

public class BuddyString {
    public boolean buddyStrings(String A, String B) {
        if (A == null || B == null || A.length() != B.length()) return false;


        if (A.equals(B)) {
            List<Character>  list = new ArrayList<>();
            for (int i = 0; i < A.length(); i++) {
                if (list.contains(A.charAt(i))) {
                    return true;
                } else {
                    list.add(A.charAt(i));
                }
            }
            return false;
        }

        List<Integer>  list = new ArrayList<>();
        for (int i = 0; i < A.length(); ++i) if (A.charAt(i) != B.charAt(i)) list.add(i);
        return list.size() == 2 && A.charAt(list.get(0)) == B.charAt(list.get(1)) && A.charAt(list.get(1)) == B.charAt(list.get(0));
    }

    public boolean buddyStrings_1(String A, String B) {
        if (A == null || B == null || A.length() != B.length()) return false;
        if (A.equals(B)) {
            Set<Character> s = new HashSet<Character>();
            for (char c : A.toCharArray()) s.add(c);
            return s.size() < A.length();
        }
        List<Integer> dif = new ArrayList<>();
        for (int i = 0; i < A.length(); ++i) if (A.charAt(i) != B.charAt(i)) dif.add(i);
        return dif.size() == 2 && A.charAt(dif.get(0)) == B.charAt(dif.get(1)) && A.charAt(dif.get(1)) == B.charAt(dif.get(0));
    }

    public boolean buddyStrings_2(String A, String B) {
        if (A.equals(B)) {
            int[] count = new int[26];
            for (int i = 0; i < A.length(); ++i)
                count[A.charAt(i) - 'a']++;

            for (int c: count)
                if (c > 1) return true;
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }

            return (second != -1 && A.charAt(first) == B.charAt(second) &&
                A.charAt(second) == B.charAt(first));
        }
    }

}
