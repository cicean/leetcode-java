/**
 * Created by cicean on 8/31/2016.
 *
 */
public class RearrangeStringkDistanceApart {

    public class Solution {
        //先记录str中的char及它出现在次数，存在count[]里，用valid[]来记录这个char最小出现的位置。
        //每一次把count值最大的数选出来，append到新的string后面
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
            //把每个出现了的char的个数记下来
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                //选出剩下需要出现次数最多又满足条件的字母，即是我们最应该先放的数
                int curt = selectedValue(count, valid, i);
                //如果不符合条件，返回“”
                if (curt == -1) return "";
                //选择好后，count要减少，valid要到下一个k distance之后
                count[curt]--;
                valid[curt] = i + k;
                sb.append((char)('a' + curt));
            }

            return sb.toString();
        }
    }
}
