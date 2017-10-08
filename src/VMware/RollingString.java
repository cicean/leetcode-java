package VMware;

public class RollingString {

  private String rollingString(String A, int k, String[] ops) {
    char[] tmp = A.toCharArray();
    for (int i = 0; i < k; i++) {
      String op = ops[i];
      int m = op.charAt(0) - '0';
      int n = op.charAt(1) - '0';
      int o = 0;
      if (op.charAt(2) == 'R') {
        o = 1;
      } else {
        o = -1;
      }
      while (m <= n) {
        tmp[m] = (char)(tmp[m] + o);

        if (tmp[m] - 'a' < 0) {
          tmp[m] = 'z';
        }
        if (tmp[m] - 'z' > 0) {
          tmp[m] = 'a';
        }

      }

    }

    return tmp.toString();
  }

}
