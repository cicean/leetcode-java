package Bloomberg;

/**
 * Created by cicean on 9/12/2016.
 */
public class codeCorrect {
    // ����1
    char test[30];
    int elementSize = sizeof(test[30]); // ����Ҫд�� sizeof(test)/sizeof(test[0])��
    // ����2�������������ʾ�ڷ���= = ||
    class Base{}
    class Child:Base{int a};
    void visit(Base* arr, int len) {
        for (int i = 0; i < len; i++) {
            Base a = arr; // ���Pass��������Childʵ�����ڴ��ַ��������
        }
    }
}
