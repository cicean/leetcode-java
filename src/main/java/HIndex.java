/**
 * 	274	H-Index	25.0%	Medium
 * @author cicean
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Hash Table Sort
Hide Similar Problems (M) H-Index II

[分析] 
扫描一遍citations，使用一个额外数组count[]计数每个引用数的文章数量，引用数大于N（文章总数）的视作N。
然后从后往前扫描count[]，同时累加已扫描部分记为sum，
sum表示引用数>=i的文章总数，因为count[]是从后往前扫描，发现的第一个i即为所求的h-index。 
 */
public class HIndex {
	
	 /**
	  * 满足这个index的最大值不会超过citations数组的len, 
	  * 因为如果超过了，就不可能有这么多的paper数。
	  * 所以brute force就是把所有可能的n个paper至少有n个citation的n记下来，
	  * 然后找出最大的n。
	  * @param args
	  * O(N^2)
	  */
	 
	 public int hIndex(int[] citations) {
	        int maxHIndex = 0;
	        for (int i = citations.length; i >= 0; i--) {
	            int citNum = i;
	            int count = 0;
	            for (int j = 0; j < citations.length; j++) {
	                if (citations[j] >= citNum) {
	                    count++;
	                }
	            }
	            if (count >= citNum) {
	                maxHIndex = Math.max(maxHIndex, citNum);
	            }
	        }
	        
	        return maxHIndex;
	    }
	 
	 /**
	  * 复杂度
时间 O(NlogN) 空间 O(1)

思路
先将数组排序，我们就可以知道对于某个引用数，有多少文献的引用数大于这个数。
对于引用数citations[i]，大于该引用数文献的数量是citations.length - i，
而当前的H-Index则是Math.min(citations[i], citations.length - i)，
我们将这个当前的H指数和全局最大的H指数来比较，得到最大H指数。
	  */
	 public int hIndex_2(int[] citations) {
	        // 排序
	        Arrays.sort(citations);
	        int h = 0;
	        for(int i = 0; i < citations.length; i++){
	            // 得到当前的H指数
	            int currH = Math.min(citations[i], citations.length - i);
	            if(currH > h){
	                h = currH;
	            }
	        }
	        return h;
	    }

	 /**
	  * Follow up是牺牲空间来换取时间，那就用另外一个数组来存当前index存在的文章数，然后从后往前相加，
	  * 如果满足条件就输出这个最大的index。O(N) O(N)
举个例子：

citations: 3, 0, 6, 1, 5
arr: 0 1 2 3 4 5
val: 1 1   1   2
那么从后向前扫的时候，记一个count,扫到arr(5)的时候，count=2 < 5, 不满足；扫arr(4),count＝2 < 4, 不满足；扫arr(3), count=2+1=3 == 3, 满足。
因为是从后向前扫的，所以当前的index就是满足条件的最大数。
也可以不对数组排序，我们额外使用一个大小为N+1的数组stats。stats[i]表示有多少文章被引用了i次，
这里如果一篇文章引用大于N次，我们就将其当为N次，因为H指数不会超过文章的总数。为了构建这个数组，
我们需要先将整个文献引用数组遍历一遍，对相应的格子加一。统计完后，我们从N向1开始遍历这个统计数组。
如果遍历到某一个引用次数时，大于或等于该引用次数的文章数量，大于引用次数本身时，我们可以认为这是H指数。
之所以不用再向下找，因为我们要取最大的H指数。那如何求大于或等于某个引用次数的文章数量呢？我们可以用一个变量，从高引用次的文章数累加下来。
因为我们知道，如果有x篇文章的引用大于等于3次，那引用大于等于2次的文章数量一定是x加上引用次数等于2次的文章数量。
	  */
	 public int hIndex_1b(int[] citations) {  
	        if (citations == null || citations.length == 0)  
	            return 0;  
	        int N = citations.length;  
	        int[] count = new int[N + 1]; // count[i] means number of articles which citations >= i  
	        for (int i = 0; i < N; i++) {  
	            if (citations[i] > N)  
	                count[N] += 1;  
	            else  
	                count[citations[i]] += 1;  
	        }  
	        int sum = 0;  
	        for (int i = N; i >= 0; i--) {  
	            sum += count[i];  
	            if (sum >= i)  
	                return i;  
	        }  
	        return 0;  
	    } 
	 
	
	 
	 /**
	  * 
	  * @param args
	  */
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
