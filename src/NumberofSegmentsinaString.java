/**
 * 434. Number of Segments in a String DescriptionHintsSubmissionsDiscussSolution Discuss Pick One
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence
 * of non-space characters.
 *
 * Please note that the string does not contain any non-printable characters.
 *
 * Example:
 *
 * Input: "Hello, my name is John" Output: 5
 */

public class NumberofSegmentsinaString {

  /***
   * Time complexity:  O(n)
   * Space complexity: O(1)
   * @param s
   * @return
   */

  public int countSegments(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
        res++;
      }
    }
    return res;
  }

}
