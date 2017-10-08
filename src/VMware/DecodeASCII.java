package VMware;

import java.util.*;


/**
 * 给一个字母组成的String，挨个换成ASCII码连起来再倒序得到一串数字。要求写一个decode函数返回原String。
 * ASCII values  of alphabets: A – Z = 65 to 90, a – z = 97 to 122
 * 直接倒序然后两个两个数读，要是不是字母就说明那个字母是三位的，再多读一位就行。
 */

public class DecodeASCII {

  Map<String, String> asciitable = new HashMap<>();


  private String decodeASCII (String A) {

    StringBuilder sb = new StringBuilder();

    sb.reverse().toString();

    int i = 0;
    while (i < A.length()) {
      if (asciitable.containsKey(A.substring(i, i + 2))) {
        sb.append(asciitable.get(A.substring(i, i + 2)));
        i = i + 2;
      } else {
        sb.append(asciitable.get(A.substring(i, i + 3)));
        i = i + 3;
      }
    }

    return sb.toString();
  }



}
