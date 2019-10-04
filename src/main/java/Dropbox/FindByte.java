package Dropbox;

import java.io.*;

/**
 * Created by cicean on 9/27/2018.
 */
public class FindByte {
    /**
     * find byte[] in a file.1point3acres缃?
     也就是strstr匹配.. 反正中间写得也不行，怎么读file也随便写.. 最后抓住机会题了用hash code的方法，减掉最前面的，加上新加的byte算出来的hashcode..如果一样的话，再strstr匹配就行.. 问了下复杂度

     判断文件里是否含有一个字符串。字符串可以含有/n等一切可能。http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=235340&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D25%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


     判断一个 byte[] array是否存在在一个file里面
     一开始用了很没有效率到方法。
     后来在面试哥哥到提示下。才想到用hash。
     最后又在面试哥哥的提示下。用了rolling hash。（其实我根本不知道rolling hash这种东西）
     哥哥让我自己设计rolling hash的interface。然后拿来用。e.
     最后就过了。
     */

    /**
     * O(N^2)
     * @param source
     * @param target
     * @return
     */

    public int strStr(String filename, byte[] target) {
        String s = target.toString();// s = new String(target);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileInputStream in = new FileInputStream(filename);
        int fileLength = (int) path.size();
        char[] chars = new char[fileLength];
        int i = 0;
        int data;
        while ((data = in.read()) != -1) {
            chars[i] = (char) data; // data actually being a byte
            ++i;
        }
        inputStream.close();

        String text = new String(chars);

        System.out.println(Arrays.toString(chars));


    }

    public int strStr(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }

        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int j = 0;
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }
            // finished loop, target found
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     KMP算法的替代品：Rabin Karp O(N+M)
     * @param source
     * @param target
     * @return
     */
    public int strStr2(String source, String target) {
        if(target == null) {
            return -1;
        }
        int m = target.length();
        if(m == 0 && source != null) {
            return 0;
        }

        if(source == null) {
            return -1;
        }
        int n = source.length();
        if(n == 0) {
            return -1;
        }

        // mod could be any big integer
        // just make sure mod * 33 wont exceed max value of int.
        int mod = Integer.MAX_VALUE / 33;
        int hash_target = 0;

        // 33 could be something else like 26 or whatever you want
        for (int i = 0; i < m; ++i) {
            hash_target = (hash_target * 33 + target.charAt(i) - 'a') % mod;
            if (hash_target < 0) {
                hash_target += mod;
            }
        }

        int m33 = 1;
        for (int i = 0; i < m - 1; ++i) {
            m33 = m33 * 33 % mod;
        }

        int value = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= m) {
                value = (value - m33 * (source.charAt(i - m) - 'a')) % mod;
            }

            value = (value * 33 + source.charAt(i) - 'a') % mod;
            if (value < 0) {
                value += mod;
            }

            if (i >= m - 1 && value == hash_target) {
                // you have to double check by directly compare the string
                if (target.equals(source.substring(i - m + 1, i + 1))) {
                    return i - m + 1;
                }
            }
        }
        return -1;
    }

}
