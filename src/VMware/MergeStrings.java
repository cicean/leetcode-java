package VMware;

/**
 *
 */

public class MergeStrings {

  private void mergeTwoString (String A, String B) {

    String result = "";

    int commonlenth = Math.abs(A.length() - B.length());
    String extrachats = "";

    if (A.length() >= B.length()) {
      extrachats = A.substring(commonlenth, A.length());
    } else {
      extrachats = B.substring(commonlenth, B.length());
    }

    char[] temp = new char[2 * commonlenth];
    for (int i = 0; i < commonlenth; i++) {
      char a = A.charAt(i);
      char b = B.charAt(i);
      temp[2 * i] = a;
      temp[2 * i + 1] = b;
    }

    result = String.valueOf(temp);

    if (commonlenth != 0) {
       result = result + extrachats;
    }

    System.out.print(result);

  }

}
