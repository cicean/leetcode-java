package VMware;

import java.util.*;

/**
 * There are n people standing in line to buy show tickets. Due to High Demand, The Venue sells tickets according to the following rules:

 The person at the head of the line can by exactly one ticket and must exit the line
 If a person needs to purchase additional tickets, they must re-enter the end of the line in wait to be sold the next ticket (assume exit and re-entry takes 0 seconds)
 Each ticket sale dates exactly one second.
 We express the initial line of n people as an array, tickets = [ticket(0),ticket(1),...,ticket(n-1)] where each tickers, denotes the number of tickets that person i wishes to buy. If Jesse is located at postion p. How many seconds will it take for him to purchase the tickets.

 For example if tickets = [1,2,5] and p=1 the first five
 */

public class BuyingShowTickets {


  private static Long calculateTimeTakenToGetAllTickets(int[] tickets, int p) {
    Long count = 0L;
    List<Integer> list = new ArrayList(Arrays.asList(tickets));
    for (int i = 1; i < tickets.length; i++) {
      list.add(tickets[i]);
    }
    final int size = list.size();
    boolean done = false;
    while (!done) {
      for (int j = 1; j < size; j++) {
        if (list.get(j) == 0) {
          continue;
        }
        if (list.get(p + 1) == 0) {
          done = true;
          break;
        }
        list.set(j, list.get(j) - 1);
        count++;
      }
    }
    return count;
  }



}
