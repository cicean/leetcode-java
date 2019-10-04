package Bloomberg;

/**
 * Created by cicean on 9/11/2016.
 */
public class PrintX {

    void printX(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || n - i + 1 == j) {
                    if (j == n) {
                        System.out.println("X");
                    } else {
                        System.out.print("X");
                    }
                } else {
                    if (j == n) {
                        System.out.println(" ");
                    } else {
                        System.out.print(" ");
                    }
                }

            }
        }

    }
    
    public static void main(String[] args) {
		PrintX slt = new PrintX();
		slt.printX(6);
	}
}
