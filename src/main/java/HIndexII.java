/**
 * 275	H-Index II	31.6%	Medium
 * Total Accepted: 6250 Total Submissions: 19800 Difficulty: Medium Follow up
 * for H-Index: What if the citations array is sorted in ascending order? Could
 * you optimize your algorithm?
 * 
 * Hint:
 * 
 * Expected runtime complexity is in O(log n) and the input is sorted. Hide Tags
 * Binary Search Hide Similar Problems (M) H-Index Have you met this question in
 * a real interview? Yes
 * 
 * [分析] 
思路：二分查找。参考解法 
https://leetcode.com/discuss/56122/standard-binary-search 
自己的实现倒也是二分查找，但因为没有理解二分查找的精髓，写得磕磕碰碰，修修补补才被Accept，代码因此也不好看，yb君一开始甚至以为是错误的。看讨论区高票解法时觉得不理解，为什么不用考虑我实现中需要的特殊情形呢，为什么return 那样写。yb君指点说，使用二分查找解题时要清楚左右边界代表的含义，参考解法中[left, right]表示尚待判断的区间，[0, left -1]表示已确认不属于目标集合的区间，而[right+1, N - 1]表示已确认属于目标集合的区间。 
换句话说right表示不属于目标集合的最大下标。因此迭代结束，right + 1是目标集合的第一个元素。目标集合中元素的性质是citations[i] >= 目标集合大小。 
 * 
 * @author cicean
 *
 */
public class HIndexII {

	public int hIndex(int[] citations) {
		if (citations == null || citations.length == 0)  
            return 0;  
        int N = citations.length;  
        int left = 0, right = N - 1;  
        while (left <= right) {  
            int mid = left + (right - left) / 2;  
            if (citations[mid] == N - mid)  
                return citations[mid];  
            else if (citations[mid] > N - mid)  
                right = mid - 1;  
            else  
                left = mid + 1;  
        }  
        return N - (right + 1);  
		
	}
	
	/**
	 * 度
时间 O(logN) 空间 O(1)

思路
在升序的引用数数组中，假设数组长为N，下标为i，则N - i就是引用次数大于等于下标为i的文献所对应的引用次数的文章数。
如果该位置的引用数小于文章数，则说明则是有效的H指数，如果一个数是H指数，那最大的H指数一定在它的后面（因为是升序的）。
根据这点就可已进行二分搜索了。
这里min = mid + 1的条件是citations[mid] < n - mid，确保退出循环时min肯定是指向一个有效的H指数。
	 * @param args
	 */
	
	public int hIndex_2(int[] citations) {
        int n = citations.length;
        if(n == 0) return 0;
        int min = 0, max = citations.length - 1;
        while(min <= max){
            int mid = (min + max) / 2;
            // 如果该点是有效的H指数，则最大H指数一定在右边
            if(citations[mid] < n - mid){
                min = mid + 1;
            // 否则最大H指数在左边
            } else {
                max = mid - 1;
            }
        }
        // n - min是min点的H指数
        return n - min;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
