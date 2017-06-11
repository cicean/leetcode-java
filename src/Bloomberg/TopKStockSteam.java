package Bloomberg;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by cicean on 10/10/2016.
 */

class Stock{

    String name;
    BigDecimal price;

    public Stock(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}

public class TopKStockSteam {

    

    Map<String, Integer> map = new HashMap<>();
    int count = 0;


    public TopKStockSteam(int k) {
        this.count = k;
    }

    public void updateStock(int timestamp, Stock stock) {

        map.put(stock.name, stock.price.intValue());

    }

    public void currentTopk(){
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < (count < list.size() ? count + 1 : list.size()); i++) {
            System.out.println(list.get(i).getKey() + ", " + list.get(i).getValue());
        }

        return;
    }
    
    public static void main(String[] args) {
		TopKStockSteam slt = new TopKStockSteam(3);
		Stock st1 = new Stock("GOOGLE", new BigDecimal(123));
		Stock st2 = new Stock("IBM", new BigDecimal(121));
		Stock st3 = new Stock("APPLE", new BigDecimal(89));
		Stock st4 = new Stock("GOOGLE", new BigDecimal(125));
		
		slt.updateStock(1, st1);
		slt.updateStock(2, st3);
		slt.updateStock(2, st2);
		slt.updateStock(2, st4);
		slt.currentTopk();
		
		
	}

    
}
