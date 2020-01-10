/*
 * 240	Search a 2D Matrix II	28.3%	Medium
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

Hide Tags Divide and Conquer Binary Search
Hide Similar Problems (M) Search a 2D Matrix

 */
public class Searcha2DMatrixII {


	/**
	 * Complexity Analysis
	 *
	 * Time complexity : \mathcal{O}(lg(n!))O(lg(n!))
	 *
	 * It's not super obvious how \mathcal{O}(lg(n!))O(lg(n!)) time complexity arises from this algorithm, so let's analyze it step-by-step. The asymptotically-largest amount of work performed is in the main loop, which runs for min(m, n)min(m,n) iterations, where mm denotes the number of rows and nn denotes the number of columns. On each iteration, we perform two binary searches on array slices of length m-im−i and n-in−i. Therefore, each iteration of the loop runs in \mathcal{O}(lg(m-i)+lg(n-i))O(lg(m−i)+lg(n−i)) time, where ii denotes the current iteration. We can simplify this to \mathcal{O}(2\cdot lg(n-i))=\mathcal{O}(lg(n-i))O(2⋅lg(n−i))=O(lg(n−i)) by seeing that, in the worst case, n\approx mn≈m. To see why, consider what happens when n \ll mn≪m (without loss of generality); nn will dominate mm in the asymptotic analysis. By summing the runtimes of all iterations, we get the following expression:
	 *
	 * (1) \quad \mathcal{O}(lg(n) + lg(n-1) + lg(n-2) + \ldots + lg(1))(1)O(lg(n)+lg(n−1)+lg(n−2)+…+lg(1))
	 *
	 * Then, we can leverage the log multiplication rule (lg(a)+lg(b)=lg(ab)lg(a)+lg(b)=lg(ab)) to rewrite the complexity as:
	 *
	 * \begin{aligned} (2) \quad \mathcal{O}(lg(n) + lg(n-1) + lg(n-2) + \ldots + lg(1)) &= \mathcal{O}(lg(n \cdot (n-1) \cdot (n-2) \cdot \ldots \cdot 1)) \\ &= \mathcal{O}(lg(1 \cdot \ldots \cdot (n-2) \cdot (n-1) \cdot n)) \\ &= \mathcal{O}(lg(n!)) \end{aligned}
	 * (2)O(lg(n)+lg(n−1)+lg(n−2)+…+lg(1))
	 * ​
	 *
	 * =O(lg(n⋅(n−1)⋅(n−2)⋅…⋅1))
	 * =O(lg(1⋅…⋅(n−2)⋅(n−1)⋅n))
	 * =O(lg(n!))
	 * ​
	 *
	 *
	 * Because this time complexity is fairly uncommon, it is worth thinking about its relation to the usual analyses. For one, lg(n!) = \mathcal{O}(nlgn)lg(n!)=O(nlgn). To see why, recall step 1 from the analysis above; there are nn terms, each no greater than lg(n)lg(n). Therefore, the asymptotic runtime is certainly no worse than that of an \mathcal{O}(nlgn)O(nlgn) algorithm.
	 *
	 * Space complexity : \mathcal{O}(1)O(1)
	 *
	 * Because our binary search implementation does not literally slice out copies of rows and columns from matrix, we can avoid allocating greater-than-constant memory.
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
        	return false;
        } 
        int i = 0;
        int j = matrix[0].length -1;
        
        while(i <matrix.length && j>=0) {
        	if (matrix[i][j] == target) {
        		return true;
        	} else if (matrix[i][j] < target) {
        		i++;
        	} else {
        		j--;
        	}
        }
        
        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Searcha2DMatrixII  slt = new Searcha2DMatrixII();
		int[][] matrix ={{1,   4,  7, 11, 15},
				{2,   5,  8, 12, 19},
				{3,   6,  9, 16, 22},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}
				};
		int target = 26;
		System.out.println(slt.searchMatrix(matrix, target));
	}
	

}
