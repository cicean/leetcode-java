/*
 69	Sqrt(x)	23.1%	Medium
 Problem:    Sqrt(x)
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_69
 Notes:
 Implement int sqrt(int x).
 Compute and return the square root of x.
 Solution: 1. Binary search in range [0, x / 2 + 1].
           2. Newton iteration method. x(i+1) = (x(i) + n/x(i)) / 2.
 See my blog (http://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html) for more explanation (in Chinese).
 */

public class Sqrtx {

	/**
	 * ��Ȼ��Binary Search����� Follow up Implement double sqrt(double x, int p)
	 * ��������res, ��������res*res��xֱ��С�����pλ�������ͬ�� ͬ����Binary
	 * Search�������Ҫע�⾫ȷ�ȣ�Ϊ�˷�������������ֱ�ӽ���������ֱ����10��p�η��������߽�����������Ƿ���ȡ�
	 * 
	 * ���Ӷ� time: O(logn), space: O(1)
	 * 
	 * @param x
	 * @return
	 */
	public int sqrt(int x) {
		int left = 1, right = x / 2;
		if (x < 2)
			return x;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (x / mid == mid)
				return mid;
			if (x / mid > mid)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return right;
	}

	/**
	 * Ϊ�˷�����⣬�����Ա���Ϊ����

   ����x2 = n�Ľ⣬��f(x)=x2-n���൱�����f(x)=0�Ľ⣬����ͼ��ʾ��

   ����ȡx0�����x0���ǽ⣬��һ������(x0,f(x0))���������ߣ���x��Ľ���Ϊx1��

   ͬ���ĵ������x1���ǽ⣬��һ������(x1,f(x1))���������ߣ���x��Ľ���Ϊx2��

   �Դ����ơ�

   �������ķ�ʽ�õ���xi������������f(x)=0�Ľ⡣

   �ж�xi�Ƿ���f(x)=0�Ľ������ַ�����

   һ��ֱ�Ӽ���f(xi)��ֵ�ж��Ƿ�Ϊ0�������ж�ǰ��������xi��xi-1�Ƿ����޽ӽ���

 

����(xi, f(xi))���������߷���Ϊf(x) = f(xi) + f��(xi)(x - xi)������f'(x)Ϊf(x)�ĵ�����������Ϊ2x�������߷��̵���0���������xi+1=xi - f(xi) / f'(xi)��

��������xi+1=xi - (xi2 - n) / (2xi) = xi - xi / 2 + n / (2xi) = xi / 2 + n / 2xi = (xi + n/xi) / 2��
	 *ţ�ٵ�����Ҳͬ��������������η��̵Ľ⡣
	 *P.S. ���������������ƽ���������ҷ���ֵҲ�����͡������������������΢���޸ģ�
	 *�Ϳ���ͬ��������double�����޷���2����
	 * @param args
	 */
	public int squrt_1(int x) {
		 if (x == 0) return 0;
		 
		 double last = 0;
		 double res = 1;
		 while (res != last)
		 {
		          last = res;
		          res = (res + x / res) / 2;
		      }
	     return int(res);
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(sqrt(256));
		System.out.println(1l << 33);
	}
}
