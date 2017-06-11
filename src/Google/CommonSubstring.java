package Google;

/**
 * Created by cicean on 9/15/2016.
 * Google Onsite
 */
public class CommonSubstring {

	//�������ַ������ҵ��ڶ����ڵ�һ���е�һ�γ��ֵ�λ��
    public int commonSubstringIndex(String A, String B) {
        int index = 0;
        
        if (A == null || B == null) return index;
        
        for (int i = 0; i < A.length(); i ++) {
            if (A.substring(i, i + B.length()).equals(B)) {
                return i;
            }
        }
        return index;
    }
    
    //��һ���ַ�����period���ַ���
    
    public static void main(String[] args) {
		CommonSubstring slt = new CommonSubstring();
		String A = "abcababa";
		String B = "aba";
		System.out.println(slt.commonSubstringIndex(A, B));
 	}

}
