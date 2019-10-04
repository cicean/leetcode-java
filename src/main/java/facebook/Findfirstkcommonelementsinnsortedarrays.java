package facebook;

/**
 * Created by cicean on 9/7/2016.
 *
 * ar1[] = {1, 5, 10, 20, 40, 80}
 ar2[] = {6, 7, 20, 80, 100}
 ar3[] = {3, 4, 15, 20, 30, 70, 80, 120}
 Output: 20, 80
 *  题目是Find first k common elements in n sorted arrays.
 *
 */
public class Findfirstkcommonelementsinnsortedarrays {

    //bruce
    //Find first k common elements in n sorted arrays. visit 1point3acres.com for more.


        private int firstKCommonElements (int[][] arrays, int k) {
            int n = arrays.length;
            int[] pointers = new int[n];
            for (int i = 0; i < n; i++) {
                pointers[i] = 0;
            }
            for (int i = 0; i < n; i++) {
                while (pointers[i] < arrays[i].length) {
                    int num = arrays[i][pointers[i]];//不能在index变了之后，再用index
                    if (hasKCommonElements(arrays, pointers, i, k)) {
                        return num;
                    }
                }
            }
            return 0;
        }
        private boolean hasKCommonElements(int[][] arrays, int[] pointers, int idx, int k) {
            int cnt = 1;
            int num = arrays[idx][pointers[idx]];
            pointers[idx]++;
            for (int i = idx; i < pointers.length; i++) {
                while (pointers[i] < arrays[i].length && arrays[i][pointers[i]] <= num) {. from: 1point3acres.com/bbs
                    if (arrays[i][pointers[i]] == num) {
                        cnt++;
                    }
                    pointers[i]++;
                }
            }
            return cnt == k;
        }


}
