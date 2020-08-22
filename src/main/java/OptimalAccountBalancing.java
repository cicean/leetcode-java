import java.util.*;

/**
 * 465. Optimal Account Balancing
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

 Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

 Note:

 A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 Example 1:

 Input:
 [[0,1,10], [2,0,5]]

 Output:
 2

 Explanation:
 Person #0 gave person #1 $10.
 Person #2 gave person #0 $5.

 Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 Example 2:

 Input:
 [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

 Output:
 1

 Explanation:
 Person #0 gave person #1 $10.
 Person #1 gave person #0 $1.
 Person #1 gave person #2 $5.
 Person #2 gave person #0 $5.

 Therefore, person #1 only need to give person #0 $4, and all debt is settled.

 */

public class OptimalAccountBalancing {

  /**
   * Easy java solution, with explanation
   Hi there! Guys in the problem, construct an isolated system. It mean the total amount of money in the system keeps constant. Thus, what matters is the amount of extra money each person have after all transactions complete. For example, if id1 gave id2 5$, then after that transaction id1's money decreased to 5$, on the contrary id2's money increased to 5$. That way, we know how did change account of each person. For imagination let's consider the following input [[1,2,3],[2,3,5], [4,1,6]]:

   id|  trans |  total |
   ---------------------
   1 | -3 + 6 |   +3   |
   ---------------------
   2 | +3 - 5 |   -2   |
   ----------------------
   3 |    +5  |   +5   |
   ----------------------
   4 |    -6  |   -6   |
   ----------------------
   Now, we have some negative account changes and positive account changes. By the way it is not hard to see that they compensate each other. Now, our task is to balance the accounts, by performing minimal amount of transactions. For instance we can balance these accounts, by performing the following transactions: [1,2,2], [3,4,5], [1,4,1]. After that, all accounts become balanced, i.e 0 extra money in total. But we have performed 3 transactions. Can we do better? May be. The number of transactions depend on the order of pairs taking part in each transaction. Consequently, the next question is, 'how to know which set of pairs give minimum number of transactions?'. One solution idea is just, brute force through all pairs and just take the minimum number of transactions. Another idea is just take some random combinations of pairs and take the minimum number of trans so far.

   P.S: May be there are other elegant and exact solutions and this solution doesn't pretend to the best one, but it is quite reasonable. The more random shuffles you do, the more probability of hitting the answer. For that test cases 1000 is enough, may be less...
   */

  public class Solution {
    public int minTransfers(int[][] transactions) {
      if(transactions == null || transactions.length == 0) return 0;
      Map<Integer, Integer> acc = new HashMap<>();
      for(int i = 0;i<transactions.length;i++){
        int id1 = transactions[i][0];
        int id2 = transactions[i][1];
        int m = transactions[i][2];
        acc.put(id1, acc.getOrDefault(id1, 0)-m);
        acc.put(id2, acc.getOrDefault(id2, 0)+m);
      }
      List<Integer> negs = new ArrayList<>();
      List<Integer> poss = new ArrayList<>();
      for(Integer key:acc.keySet()){
        int m = acc.get(key);
        if(m == 0) continue;
        if(m<0) negs.add(-m);
        else poss.add(m);
      }
      int ans = Integer.MAX_VALUE;
      Stack<Integer> stNeg = new Stack<>(), stPos = new Stack<>();
      for(int i =0;i<1000;i++){
        for(Integer num:negs) stNeg.push(num);
        for(Integer num:poss) stPos.push(num);
        int cur = 0;
        while(!stNeg.isEmpty()){
          int n = stNeg.pop();
          int p = stPos.pop();
          cur++;
          if(n == p) continue;
          if(n>p){
            stNeg.push(n-p);
          } else {
            stPos.push(p-n);
          }
        }
        ans = Math.min(ans, cur);
        Collections.shuffle(negs);
        Collections.shuffle(poss);
      }
      return ans;
    }

    //solution
/*
1. Let compute each person's, balance by making postive and negative debt.
2. Collect positive and  negative debts (make negative val to postivie) in a separrate list and try to balance equal values of pos and negative by marking it as a transactions. Also, remove them from the list.
3. Now, recursively try to settle with all combination of pos and neg values, and maintain min transactions.


*/
    class Solution2 {
      public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int[] t : transactions){
          int x = t[0];
          int y = t[1];
          int z = t[2];

          map.put(x, map.getOrDefault(x, 0)-z);
          map.put(y, map.getOrDefault(y, 0)+z);


        }

        List<Integer> pos_l = new ArrayList<>();
        List<Integer> neg_l = new ArrayList<>();

        for(int k : map.keySet()){

          if(map.get(k) > 0){
            pos_l.add(map.get(k));
          }
          else if(map.get(k) < 0){
            neg_l.add(map.get(k));
          }
        }



        int cur = 0;

        for(int i = 0 ; i < pos_l.size() ; i++){
          for(int j = 0 ; j < neg_l.size(); j++){
            if(pos_l.get(i) + neg_l.get(j) == 0){
              pos_l.remove(i);
              neg_l.remove(j);
              cur++;
              i--;
              break;
            }

          }
        }

        int[] pos = new int[pos_l.size()];
        int[] neg = new int[neg_l.size()];

        for(int i = 0 ; i < pos_l.size(); i++){
          pos[i] = pos_l.get(i);
        }

        for(int i = 0 ; i < neg_l.size(); i++){
          neg[i] = -neg_l.get(i); // mul with minus
        }
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;

        dfs(pos, neg, cur, min, 0);

        return min[0];
      }

      //n_idx - neg_idx, neg array has postive value not negative, we will try to balance both arrays.
      public void dfs(int[] pos, int[] neg, int cur, int[] min, int n_idx){


        if(n_idx >= neg.length){
          min[0] = Math.min(min[0], cur);
          return ;

        }

        for(int i = 0 ; i < pos.length ; i++){ // try with all postive values
          if(neg[n_idx] <= pos[i] ){ // if current negative index is less or equal to current postive, we can make one transactions.
            pos[i] = pos[i] - neg[n_idx]; // update current postive value
            dfs(pos, neg, cur+1, min, n_idx+1);
            pos[i] = pos[i] + neg[n_idx]; //  restore it back
          }
          else if(pos[i] > 0){
            int tmp = pos[i];
            neg[n_idx] -= pos[i];
            pos[i] = 0;
            dfs(pos, neg, cur+1, min, n_idx);
            neg[n_idx] += tmp;
            pos[i] = tmp;
          }


        }


      }
    }
  }


}
