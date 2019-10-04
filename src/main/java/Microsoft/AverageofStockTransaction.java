package Microsoft;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by cicean on 10/2/2016.
 * ���и������������Ͻ��ո����transaction��transaction�������
 * Ȼ��д����������һ�������Ǵ�stream���һ��transaction��
 * ��һ���ǻ�ȡ���ʮ���ӵ�ƽ��ÿ���Ľ��׶�����������ʮ���Ӿ����Ƕ��٣���˵���⣬
 * ����Ҫ˵�����assumption���Ҿ�˵һ��ɣ�Ȼ�����circular array��
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
