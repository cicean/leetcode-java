/**
 * Created by cicean on 8/31/2016.
 *
 */
public class RearrangeStringkDistanceApart {

    public class Solution {
        //�ȼ�¼str�е�char���������ڴ���������count[]���valid[]����¼���char��С���ֵ�λ�á�
        //ÿһ�ΰ�countֵ������ѡ������append���µ�string����
        //Solution with Two auxiliary array. O(N) time.
        public int selectedValue(int[] count, int[] valid, int i) {
            int select = Integer.MIN_VALUE;
            int val = -1;
            for (int j = 0; j < count.length; j++) {
                if (count[j] > 0 && i >= valid[j] && count[j] > select) {
                    select = count[j];
                    val = j;
                }
            }
            return val;
        }

        public String rearrangeString(String str, int k) {
            int[] count = new int[26];
            int[] valid = new int[26];
            //��ÿ�������˵�char�ĸ���������
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                //ѡ��ʣ����Ҫ���ִ��������������������ĸ������������Ӧ���ȷŵ���
                int curt = selectedValue(count, valid, i);
                //������������������ء���
                if (curt == -1) return "";
                //ѡ��ú�countҪ���٣�validҪ����һ��k distance֮��
                count[curt]--;
                valid[curt] = i + k;
                sb.append((char)('a' + curt));
            }

            return sb.toString();
        }
    }
}
