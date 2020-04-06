package AlgorithmTemplate;

public class MergeSort {

    public class Solution {

        public static void mergeSort(int[] a){
            int[] TR = new int[a.length];//用于存放归并结果

            int k=1;//起始，子序列长度为1
            while(k<a.length){
                mergePass(a, TR, k, a.length);//将原先无序的数据两两归并入TR
                k = 2*k;//子序列长度加倍
                mergePass(TR, a, k, a.length);//将TR中已经两两归并的有序序列再归并回数组a
                k = 2*k;//子序列长度加倍
            }
        }

        public static void mergePass(int[] SR, int [] TR,int s,int len){

            int i=0;
            while (i < len-2*s+1) {//8
                merge(SR,TR,i,i+s-1,i+2*s-1);//两两归并
                i=i+2*s;
            }

            //处理最后的尾数
            //i=8
            if(i< len-s+1){//9
                merge(SR, TR, i, i+s-1, len-1);//归并最后两个序列
            }else {
                for (int j = i; j < len; j++) {//若最后只剩下单个子序列
                    TR[j] = SR[j];
                }
            }
        }

        public static void merge(int[] SR,int[] TR,int i,int m,int n){
            int j,k,l;

            //i(0~4) j(5~8)
            for(j=m+1,k=i; i<=m && j<=n; k++){

                if(SR[i]<SR[j]){
                    TR[k] = SR[i++];
                }else{
                    TR[k] = SR[j++];
                }
            }


            if(i<=m){
                for (l = 0; l <= m-i ; l++) {
                    TR[k+l] = SR[i+l];
                }
            }

            if(j<=n){
                for (l = 0; l <= n-j; l++) {
                    TR[k+l] = SR[j+l];
                }
            }
        }

        public static void main(String[] args) {
            int[] a = {2,3,5,4,1,6,9,8,7,10,20,45,32,28,44,31,55,43,23,21,23,21,33,21};
            mergeSort(a);
            for(int i=0;i<a.length;i++)
                System.out.print(a[i]+" ");
        }
    }

    public class Solution_2 {
        /**
         * @param A an integer array
         * @return void
         */
        public void sortIntegers2(int[] A) {
            // use a shared temp array, the extra memory is O(n) at least
            int[] temp = new int[A.length];
            mergeSort(A, 0, A.length - 1, temp);
        }

        private void mergeSort(int[] A, int start, int end, int[] temp) {
            if (start >= end) {
                return;
            }

            int left = start, right = end;
            int mid = (start + end) / 2;

            mergeSort(A, start, mid, temp);
            mergeSort(A, mid+1, end, temp);
            merge(A, start, mid, end, temp);
        }

        private void merge(int[] A, int start, int mid, int end, int[] temp) {
            int left = start;
            int right = mid+1;
            int index = start;

            // merge two sorted subarrays in A to temp array
            while (left <= mid && right <= end) {
                if (A[left] < A[right]) {
                    temp[index++] = A[left++];
                } else {
                    temp[index++] = A[right++];
                }
            }
            while (left <= mid) {
                temp[index++] = A[left++];
            }
            while (right <= end) {
                temp[index++] = A[right++];
            }

            // copy temp back to A
            for (index = start; index <= end; index++) {
                A[index] = temp[index];
            }
        }
    }
}
