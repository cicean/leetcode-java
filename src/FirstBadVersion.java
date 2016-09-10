/**
 * You are a product manager and currently leading a team to develop a new
 * product. Unfortunately, the latest version of your product fails the quality
 * check. Since each version is developed based on the previous version, all the
 * versions after a bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first
 * bad one, which causes all the following ones to be bad.
 * 
 * You are given an API bool isBadVersion(version) which will return whether
 * version is bad. Implement a function to find the first bad version. You
 * should minimize the number of calls to the API.
 * 
 * Credits: Special thanks to @jianchao.li.fighter for adding this problem and
 * creating all test cases.
 * 
 * Hide Tags Binary Search Hide Similar Problems (M) Search for a Range (M)
 * Search Insert Position
 * 
 * @author cicean
 *
 */
public class FirstBadVersion {

	public class Solution extends VersionControl {
		public int firstBadVersion(int n) {
			int min = 1, max = n, mid = 0;
			while(min <= max){
				mid = min + (max - min) / 2;
				if(isBadVersion(mid)){
					max = mid - 1;
				} else {
					min = mid + 1;
				}
			}
			return min;
		}
	}

	//ʱ�� O(logN) �ռ� O(1)
	public int firstBadVersion(int n) {
		
		if (n == 1) {
			return 1;
		}
		
		int left = 1;
		int right = n;
		
		while (left + 1 < right) {
			int mid = left + (right - left)/2;
			if (VersionControl.isBadVersion(mid)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		if (VersionControl.isBadVersion(left)) {
			return left;
		}
		
		return right;
		
	}

	/**
	 * facebook follow up
	 * 1. ��2^nֱ��Խ�磬Ȼ���� [2^(n-1), 2^n] ��Χ�ڶ������ұ߽硣
	 2. �����������֡�
	 һ������ 3*logn
	 * @param args
     */



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class VersionControl {
	public static boolean isBadVersion(int k){
		return true;
	};
}
