package Dropbox;

import java.io.*;

/**
 * Created by cicean on 9/27/2018.
 */
public class FindByte {
    /**
     * find byte[] in a file.1point3acres�?
     Ҳ����strstrƥ��.. �����м�д��Ҳ���У���ô��fileҲ���д.. ���ץס����������hash code�ķ�����������ǰ��ģ������¼ӵ�byte�������hashcode..���һ���Ļ�����strstrƥ�����.. �����¸��Ӷ�

     �ж��ļ����Ƿ���һ���ַ������ַ������Ժ���/n��һ�п��ܡ�http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=235340&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D25%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


     �ж�һ�� byte[] array�Ƿ������һ��file����
     һ��ʼ���˺�û��Ч�ʵ�������
     ���������Ը�絽��ʾ�¡����뵽��hash��
     ����������Ը�����ʾ�¡�����rolling hash������ʵ�Ҹ�����֪��rolling hash���ֶ�����
     ��������Լ����rolling hash��interface��Ȼ�������á�e.
     ���͹��ˡ�
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
     KMP�㷨�����Ʒ��Rabin Karp O(N+M)
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
