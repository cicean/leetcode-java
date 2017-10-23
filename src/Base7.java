import java.util.*;

/**
 * 504. Base 7
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given an integer, return its base 7 string representation.

 Example 1:
 Input: 100
 Output: "202"
 Example 2:
 Input: -7
 Output: "-10"
 Note: The input will be in range of [-1e7, 1e7].
 */

public class Base7 {

  public String convertTo7(int num) {
    if (num < 0)
      return '-' + convertTo7(-num);
    if (num < 7)
      return num + "";
    return convertTo7(num / 7) + num % 7;
  }

}
