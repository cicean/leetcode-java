package Airbnb;

import java.util.*;

public class BankSystem {

    Map<Integer, Double> accountBalance;
    Map<Integer, Map<Long, Double>> accountStatement;

    public BankSystem() {
        this.accountBalance = new HashMap<>();
        this.accountStatement = new HashMap<>();
    }

    public void deposite(int id, double amount, long timestamp) {
        accountBalance.put(id, accountBalance.getOrDefault(id, new Double(0)) + amount);
        accountStatement.computeIfAbsent(id, x -> new HashMap<Long, Double>()).put(timestamp, accountBalance.get(id) + amount);
    }

    public boolean withdraw(int id, int amount, long timestamp) {
        if (!accountBalance.containsKey(id) || accountBalance.get(id) < amount) {
            return false;
        }
        accountBalance.put(id, accountBalance.get(id) - amount);
        accountStatement.get(id).put(timestamp, accountBalance.get(id));
        return true;
    }

    public double[] check(int id, long startTime, long endTime) {
        if (!accountBalance.containsKey(id)) {
            return new double[0];
        }
        double[] res = new double[2];
        Map<Long, Double> statement = accountStatement.get(id);
        List<Long> timestamps = new ArrayList<>(statement.keySet());
        Collections.sort(timestamps);
        if (statement.containsKey(startTime)) {
            res[0] = statement.get(startTime);
        } else {
            int index = -(Collections.binarySearch(timestamps, startTime) + 1);
            res[0] = index == 0 ? 0 : statement.get(timestamps.get(index - 1));
        }
        if (statement.containsKey(endTime)) {
            res[1] = statement.get(endTime);
        } else {
            int index = -(Collections.binarySearch(timestamps, endTime) + 1);
            res[1] = index == 0 ? 0 : statement.get(timestamps.get(index - 1));
        }
        return res;
    }


}
