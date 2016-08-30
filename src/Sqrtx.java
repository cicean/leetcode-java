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
	 * 显然用Binary Search解决。 Follow up Implement double sqrt(double x, int p)
	 * 如果结果是res, 必须满足res*res与x直到小数点后p位结果都相同。 同样用Binary
	 * Search解决，但要注意精确度，为了符合条件，可以直接将两个结果分别乘以10的p次方，看两者结果整数部分是否相等。
	 * 
	 * 复杂度 time: O(logn), space: O(1)
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
	 * 为了方便理解，就先以本题为例：

   计算x2 = n的解，令f(x)=x2-n，相当于求解f(x)=0的解，如左图所示。

   首先取x0，如果x0不是解，做一个经过(x0,f(x0))这个点的切线，与x轴的交点为x1。

   同样的道理，如果x1不是解，做一个经过(x1,f(x1))这个点的切线，与x轴的交点为x2。

   以此类推。

   以这样的方式得到的xi会无限趋近于f(x)=0的解。

   判断xi是否是f(x)=0的解有两种方法：

   一是直接计算f(xi)的值判断是否为0，二是判断前后两个解xi和xi-1是否无限接近。

 

经过(xi, f(xi))这个点的切线方程为f(x) = f(xi) + f’(xi)(x - xi)，其中f'(x)为f(x)的导数，本题中为2x。令切线方程等于0，即可求出xi+1=xi - f(xi) / f'(xi)。

继续化简，xi+1=xi - (xi2 - n) / (2xi) = xi - xi / 2 + n / (2xi) = xi / 2 + n / 2xi = (xi + n/xi) / 2。
	 *牛顿迭代法也同样可以用于求解多次方程的解。
	 *P.S. 本题是求解整数的平方根，并且返回值也是整型。在上述代码基础上稍微做修改，
	 *就可以同样适用于double（仅限方法2）。
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
