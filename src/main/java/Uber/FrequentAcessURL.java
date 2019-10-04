package Uber;

import java.util.*;

public class FrequentAcessURL {
  //give ech
  private Node head = null;
  private int cap = 0;
  HashMap<String,Integer> vals;
  SortedMap<Integer,List<String>> sortedMap = new TreeMap<Integer, List<String>>(
      new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return o1 - o2;
        }
      });

  public void accessURL(String url) {
    if ( cap == 0) return;
    if (vals.containsKey(url)) {
      Integer count = vals.get(url);
      List<String> list = sortedMap.get(count);
      list.remove(url);
      list = sortedMap.get(count+1);
      if (list == null) {
        list = new ArrayList<String>();
        list.add(url);
        sortedMap.put(count+1, list);
      }
      vals.put(url, count+1);

    } else {
      List<String> list = sortedMap.get(1);
      if (list == null){
        list = new LinkedList<>();
      }
      list.add(url);
      sortedMap.put(1,list);
    }

  }



  public List<String> getFrequentURL(int N) {
      int count = 0;
      List<String> res = new ArrayList<>();
      for (Map.Entry<Integer, List<String>> entry : sortedMap.entrySet()) {
          entry.getValue().size()
      }
  }

  class Node {
    public int count = 0;
    public LinkedHashSet<Integer> keys = null;
    public Node prev = null, next = null;

    public Node(int count) {
      this.count = count;
      keys = new LinkedHashSet<Integer>();
      prev = next = null;
    }
  }

  private void increaseCount(String url) {

  }


}
