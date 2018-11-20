package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 */
public class MenuOrder {

    public List<List<Double>> getOrder(double[] prices, double target)
    {
        List<List<Double>> res = new ArrayList();
        if (prices == null || prices.length == 0 || target <= 0)
            return res;

        Arrays.sort(prices);

        search(prices, 0, new ArrayList(), target, res);
        return res;

    }

    public void search(double[] prices, int start, ArrayList<Double> line, double target, List<List<Double>> res)
    {
        if (target < 0.01)
        {
            res.add(new ArrayList(line));
            return;
        }

        for(int i = start; i < prices.length; i++)
        {
            if (prices[i] > target)
                break;
            line.add(prices[i]);
            search(prices, i, line, target - prices[i], res);
            line.remove(line.size() -1);
        }
    }

    private void search(List<List<Double>> res, int[] centsPrices, int start,
                        int centsTarget,
                        List<Double> curCombo, double[] prices) {
        if (centsTarget == 0) {
            res.add(new ArrayList<>(curCombo));
            return;
        }
        for (int i = start; i < centsPrices.length; i++) {
            if (i > start && centsPrices[i] == centsPrices[i - 1]) {
                continue;
            }
            if (centsPrices[i] > centsTarget) {
                break;
            }
            curCombo.add(prices[i]);
            search(res, centsPrices, i + 1, centsTarget - centsPrices[i],
                    curCombo, prices);
            curCombo.remove(curCombo.size() - 1);
        }
    }

    public List<List<Double>> getCombos(double[] prices, double target) {
        List<List<Double>> res = new ArrayList<>();
        if (prices == null || prices.length == 0 || target <= 0) {
            return res;
        }
        int centsTarget = (int) Math.round(target * 100);
        Arrays.sort(prices);
        int[] centsPrices = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            centsPrices[i] = (int) Math.round(prices[i] * 100);
        }
        search(res, centsPrices, 0, centsTarget, new ArrayList<>(), prices);
        return res;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MenuOrder mo = new MenuOrder();
        double[] prices = new double[1];
        prices[0] = 2.15;

        List<List<Double>> res = mo.getOrder(prices, 15.05);
        System.out.println(res);
    }
}
