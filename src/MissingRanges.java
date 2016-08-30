import java.util.*;

/*
 * 	163	Missing Ranges 	24.1%	Medium
 * Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], return [��2��, ��4->49��, ��51->74��, ��76->99��]

[����]
һ������ɨ�輴�ɡ�

[ע������]
1�����һЩ���������ѯ�����Թ٣�����˵���array�Ǹ��յģ�����array���������ڵ�����Ԫ�أ���Ӧ�ķ���ֵ��ʲô
2�����Ը���һЩ����˼��test case��������ǲ���Ҫ���Ƹ����ķ�Χ��[0, 99]����start��end��ʾ���С������Ե�ʱ���������һ�£�
д��[0, 99]�Ĵ��룬Ȼ���������޸ģ����start��end�İ汾��
 */
public class MissingRanges {
	
	public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> ranges = new ArrayList<String>();
        int prev = start - 1;
        for (int i=0; i<=vals.length; ++i) {
            int curr = (i==vals.length) ? end + 1 : vals[i];
            if ( curr-prev>=2 ) {
                ranges.add(getRange(prev+1, curr-1));
            }
            prev = curr;
        }
        return ranges;
    }
 
    private String getRange(int from, int to) {
        return (from==to) ? String.valueOf(from) : from + "->"+ to;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int[] vals = {0, 1, 3, 50, 75};
       int start = 0;
       int end = 99;
       MissingRanges  slt = new MissingRanges();
       List<String> res = slt.findMissingRanges(vals, start, end);
       System.out.print(res);
       
	}

}
