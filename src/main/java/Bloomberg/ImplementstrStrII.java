package Bloomberg;

/**
 * Created by cicean on 9/20/2016.
 *  ͳ��һ���������ִ�����һ���ַ����г��ֵĴ���������strstr�� ��ͬ���Ǹ������ֵĴ���. fr
 *
 */
public class ImplementstrStrII {

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i + needle.length() <= haystack.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        ImplementstrStrII slt = new ImplementstrStrII();
        System.out.println(slt.strStr("ababacdaba", "aba"));

    }
}
