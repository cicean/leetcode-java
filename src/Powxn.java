/*
 Pow(x,n).java	Power-n	6 months ago
 Problem:    Pow(x, n)
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/powx-n/
 Notes:
 Implement pow(x, n).
 
 Solution: recursion.
 */

public class Powxn {
	/**
	 * O(logn) time 
	 * ���Ӷ� ʱ��O(logn) �ռ�ջO(logn)
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n < 0) return ( (1/x) * myPow(1/x, -(n+1)) );
        
        double y = myPow(x, n/2);
        double z = y*y;
        int r = n % 2;
        
        return (r == 0) ? z : (z * x);
    }
	
	double myPow(double x, int n) {
		if (x == 0) return 0;
		if (x == 1) return 1;
		
	    if(n<0) return 1/x * myPow(1/x, -(n+1));
	    if(n==0) return 1;
	    if(n==2) return x*x;
	    if(n%2==0) return myPow( myPow(x, n/2), 2);
	    else return x*myPow( myPow(x, n/2), 2);
	}
	
	
	public double myPow(double x, int n) {
        if(n < 0){
        // nΪ�����ص���
            return 1/pow(x, -n);
        } else {
        // nΪ��ֱ�ӷ��ؽ��
            return pow(x, n);
        }
    }
    
    private double pow(double x, int n){
        // �ݹ���ֹ����
        if(n == 0){
            return 1.0;
        } 
        if(n == 1){
            return x;
        }
        // ������������ż�����ز�ͬ��ֵ
        if(n % 2 == 0){
            return val * val;
        } else {
            return val * val * x;
        }
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
