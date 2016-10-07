package Microsoft;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by cicean on 10/2/2016.
 * 我有个服务器，不断接收付款的transaction，transaction里包含金额，
 * 然后写两个函数，一个功能是从stream里读一个transaction，
 * 另一个是获取最近十分钟的平均每单的交易额。我先问他最近十分钟精度是多少，他说随意，
 * 但我要说出这个assumption，我就说一秒吧，然后就用circular array做
 */

class StockTransaction {
    int timestamp;
    double profit;
    public StockTransaction(double profit, int trans_time) {
        this.profit =profit;
        this.timestamp = trans_time;
    }
}

public class AverageofStockTransaction {

    double sum;

    PriorityQueue<StockTransaction> queue = new PriorityQueue<>(new Comparator<StockTransaction>() {
        @Override
        public int compare(StockTransaction o1, StockTransaction o2) {
            return o1.timestamp - o2.timestamp;
        }
    });
    public AverageofStockTransaction() {
        this.sum = 0;
    }

    public void readNextTransaction(StockTransaction trade) {
        while (!queue.isEmpty()) {
           if(trade.timestamp - queue.peek().timestamp >= 600) {
               StockTransaction last =  queue.poll();
               sum -= last.profit;
           } else break;
        }

        queue.add(trade);
        sum += trade.profit;
    }

    public double getAvarage() {
        return queue.size() == 0 ? 0 : sum / queue.size();
    }

}
