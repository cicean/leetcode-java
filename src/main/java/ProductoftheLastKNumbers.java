import java.util.*;

/**
 * 1352. Product of the Last K Numbers
 * Medium
 *
 * 138
 *
 * 8
 *
 * Add to List
 *
 * Share
 * Implement the class ProductOfNumbers that supports two methods:
 *
 * 1. add(int num)
 *
 * Adds the number num to the back of the current list of numbers.
 * 2. getProduct(int k)
 *
 * Returns the product of the last k numbers in the current list.
 * You can assume that always the current list has at least k numbers.
 * At any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
 *
 *
 *
 * Example:
 *
 * Input
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
 *
 * Output
 * [null,null,null,null,null,null,20,40,0,null,32]
 *
 * Explanation
 * ProductOfNumbers productOfNumbers = new ProductOfNumbers();
 * productOfNumbers.add(3);        // [3]
 * productOfNumbers.add(0);        // [3,0]
 * productOfNumbers.add(2);        // [3,0,2]
 * productOfNumbers.add(5);        // [3,0,2,5]
 * productOfNumbers.add(4);        // [3,0,2,5,4]
 * productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
 * productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
 * productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
 * productOfNumbers.add(8);        // [3,0,2,5,4,8]
 * productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32
 *
 *
 * Constraints:
 *
 * There will be at most 40000 operations considering both add and getProduct.
 * 0 <= num <= 100
 * 1 <= k <= 40000
 * Accepted
 * 8,414
 * Submissions
 * 21,758
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * LeetCode
 * Keep all prefix products of numbers in an array, then calculate the product of last K elements in O(1) complexity.
 * When a zero number is added, clean the array of prefix products.
 */
public class ProductoftheLastKNumbers {

    ArrayList<Integer> A;
    public ProductoftheLastKNumbers() {
        add(0);
    }

    public void add(int a) {
        if (a > 0)
            A.add(A.get(A.size() - 1) * a);
        else {
            A = new ArrayList();
            A.add(1);
        }
    }

    public int getProduct(int k) {
        int n = A.size();
        return k < n ? A.get(n - 1) / A.get(n - k - 1)  : 0;
    }
}
