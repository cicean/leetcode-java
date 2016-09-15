package Bloomberg;

/**
 * Created by cicean on 9/12/2016.
 */
public class codeCorrect {
    // 错误1
    char test[30];
    int elementSize = sizeof(test[30]); // 明显要写成 sizeof(test)/sizeof(test[0])；
    // 错误2，这个经过他提示在发现= = ||
    class Base{}
    class Child:Base{int a};
    void visit(Base* arr, int len) {
        for (int i = 0; i < len; i++) {
            Base a = arr; // 如果Pass进来的是Child实例，内存地址会有问题
        }
    }
}
