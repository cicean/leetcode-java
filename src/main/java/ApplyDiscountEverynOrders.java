import java.util.*;

/**
 * 1357. Apply Discount Every n Orders
 * Medium
 *
 * 23
 *
 * 15
 *
 * Add to List
 *
 * Share
 * There is a sale in a supermarket, there will be a discount every n customer.
 * There are some products in the supermarket where the id of the i-th product is products[i] and the price per unit of this product is prices[i].
 * The system will count the number of customers and when the n-th customer arrive he/she will have a discount on the bill. (i.e if the cost is x the new cost is x - (discount * x) / 100). Then the system will start counting customers again.
 * The customer orders a certain amount of each product where product[i] is the id of the i-th product the customer ordered and amount[i] is the number of units the customer ordered of that product.
 *
 * Implement the Cashier class:
 *
 * Cashier(int n, int discount, int[] products, int[] prices) Initializes the object with n, the discount, the products and their prices.
 * double getBill(int[] product, int[] amount) returns the value of the bill and apply the discount if needed. Answers within 10^-5 of the actual value will be accepted as correct.
 *
 *
 * Example 1:
 *
 * Input
 * ["Cashier","getBill","getBill","getBill","getBill","getBill","getBill","getBill"]
 * [[3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]],[[1,2],[1,2]],[[3,7],[10,10]],[[1,2,3,4,5,6,7],[1,1,1,1,1,1,1]],[[4],[10]],[[7,3],[10,10]],[[7,5,3,1,6,4,2],[10,10,10,9,9,9,7]],[[2,3,5],[5,3,2]]]
 * Output
 * [null,500.0,4000.0,800.0,4000.0,4000.0,7350.0,2500.0]
 * Explanation
 * Cashier cashier = new Cashier(3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]);
 * cashier.getBill([1,2],[1,2]);                        // return 500.0, bill = 1 * 100 + 2 * 200 = 500.
 * cashier.getBill([3,7],[10,10]);                      // return 4000.0
 * cashier.getBill([1,2,3,4,5,6,7],[1,1,1,1,1,1,1]);    // return 800.0, The bill was 1600.0 but as this is the third customer, he has a discount of 50% which means his bill is only 1600 - 1600 * (50 / 100) = 800.
 * cashier.getBill([4],[10]);                           // return 4000.0
 * cashier.getBill([7,3],[10,10]);                      // return 4000.0
 * cashier.getBill([7,5,3,1,6,4,2],[10,10,10,9,9,9,7]); // return 7350.0, Bill was 14700.0 but as the system counted three more customers, he will have a 50% discount and the bill becomes 7350.0
 * cashier.getBill([2,3,5],[5,3,2]);                    // return 2500.0
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 * 0 <= discount <= 100
 * 1 <= products.length <= 200
 * 1 <= products[i] <= 200
 * There are not repeated elements in the array products.
 * prices.length == products.length
 * 1 <= prices[i] <= 1000
 * 1 <= product.length <= products.length
 * product[i] exists in products.
 * amount.length == product.length
 * 1 <= amount[i] <= 1000
 * At most 1000 calls will be made to getBill.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 * Accepted
 * 3,019
 * Submissions
 * 5,048
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Facebook
 * |
 * LeetCode
 * Keep track of the count of the customers.
 * Check if the count of the customers is divisible by n then apply the discount formula.
 */
public class ApplyDiscountEverynOrders {
    class Cashier {
        private int n;
        private int discount;
        private int[] productPrices;
        private int count;
        public Cashier(int n, int discount, int[] products, int[] prices) {
            this.n = n;
            this.discount =discount;
            productPrices = new int[201];
            for(int i = 0; i< products.length; i++){
                productPrices[products[i]] = prices[i];
            }
            this.count =0;
        }

        public double getBill(int[] product, int[] amount) {
            count++;
            int num = product.length;
            double res = 0;
            for(int i = 0; i<num; i++){
                res+=productPrices[product[i]]*amount[i];
            }
            if(count==n){
                count=0;
                res*=(100-discount);
                res/=100;
            }
            return res;
        }
    }

    class Cashier2 {

        private int cnt = 0, n, discount;
        private Map<Integer, Integer> price = new HashMap<>();
        public Cashier2(int n, int discount, int[] products, int[] prices) {
            this.n = n;
            this.discount = discount;
            for (int i = 0; i < products.length; ++i)
                price.put(products[i], prices[i]);
        }

        public double getBill(int[] product, int[] amount) {
            double total = 0.0d;
            for (int i = 0; i < product.length; ++i)
                total += price.get(product[i]) * amount[i];
            return total * (++cnt % n == 0 ? 1 - discount / 100d : 1);
        }
    }

    
}
