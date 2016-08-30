/**
 * 275	H-Index II	31.6%	Medium
 * Total Accepted: 6250 Total Submissions: 19800 Difficulty: Medium Follow up
 * for H-Index: What if the citations array is sorted in ascending order? Could
 * you optimize your algorithm?
 * 
 * Hint:
 * 
 * Expected runtime complexity is in O(log n) and the input is sorted. Hide Tags
 * Binary Search Hide Similar Problems (M) H-Index Have you met this question in
 * a real interview? Yes
 * 
 * [����] 
˼·�����ֲ��ҡ��ο��ⷨ 
https://leetcode.com/discuss/56122/standard-binary-search 
�Լ���ʵ�ֵ�Ҳ�Ƕ��ֲ��ң�����Ϊû�������ֲ��ҵľ��裬д�ÿĿ����������޲����ű�Accept���������Ҳ���ÿ���yb��һ��ʼ������Ϊ�Ǵ���ġ�����������Ʊ�ⷨʱ���ò���⣬Ϊʲô���ÿ�����ʵ������Ҫ�����������أ�Ϊʲôreturn ����д��yb��ָ��˵��ʹ�ö��ֲ��ҽ���ʱҪ������ұ߽����ĺ��壬�ο��ⷨ��[left, right]��ʾ�д��жϵ����䣬[0, left -1]��ʾ��ȷ�ϲ�����Ŀ�꼯�ϵ����䣬��[right+1, N - 1]��ʾ��ȷ������Ŀ�꼯�ϵ����䡣 
���仰˵right��ʾ������Ŀ�꼯�ϵ�����±ꡣ��˵���������right + 1��Ŀ�꼯�ϵĵ�һ��Ԫ�ء�Ŀ�꼯����Ԫ�ص�������citations[i] >= Ŀ�꼯�ϴ�С�� 
 * 
 * @author cicean
 *
 */
public class HIndexII {

	public int hIndex(int[] citations) {
		if (citations == null || citations.length == 0)  
            return 0;  
        int N = citations.length;  
        int left = 0, right = N - 1;  
        while (left <= right) {  
            int mid = left + (right - left) / 2;  
            if (citations[mid] == N - mid)  
                return citations[mid];  
            else if (citations[mid] > N - mid)  
                right = mid - 1;  
            else  
                left = mid + 1;  
        }  
        return N - (right + 1);  
		
	}
	
	/**
	 * ��
ʱ�� O(logN) �ռ� O(1)

˼·
������������������У��������鳤ΪN���±�Ϊi����N - i�������ô������ڵ����±�Ϊi����������Ӧ�����ô�������������
�����λ�õ�������С������������˵��������Ч��Hָ�������һ������Hָ����������Hָ��һ�������ĺ��棨��Ϊ������ģ���
�������Ϳ��ѽ��ж��������ˡ�
����min = mid + 1��������citations[mid] < n - mid��ȷ���˳�ѭ��ʱmin�϶���ָ��һ����Ч��Hָ����
	 * @param args
	 */
	
	public int hIndex_2(int[] citations) {
        int n = citations.length;
        if(n == 0) return 0;
        int min = 0, max = citations.length - 1;
        while(min <= max){
            int mid = (min + max) / 2;
            // ����õ�����Ч��Hָ���������Hָ��һ�����ұ�
            if(citations[mid] < n - mid){
                min = mid + 1;
            // �������Hָ�������
            } else {
                max = mid - 1;
            }
        }
        // n - min��min���Hָ��
        return n - min;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
