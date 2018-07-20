package Amazon;

import java.util.*;

public class MostFrequentWordinATextFile {



  public List<String> mostFrequentWord(String text, List<String> excludewords) {
    String[] words = text.toLowerCase().split("[^A-ZÃ…Ã„Ã–a-zÃ¥Ã¤Ã¶]+");

    Map<String, Integer> counterMap = new HashMap<>();

    for (String word : words) {
      int count = counterMap.getOrDefault(word, 0);
      counterMap.put(word, count + 1);
    }

    int max = Integer.MIN_VALUE;
    List<String> rs = new ArrayList<>();

    for (Map.Entry<String, Integer> entry : counterMap.entrySet()) {
      if (entry.getValue() > max) {
        rs.clear();
        rs.add(entry.getKey());
        max = entry.getValue();
      } else if (entry.getValue() == max){
        rs.add(entry.getKey());
      }
    }

    return rs;

  }

}
