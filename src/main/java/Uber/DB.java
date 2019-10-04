package Uber;

import java.util.*;

public class DB {

  private Map<String, String> map;

  public DB() {
    map = new HashMap<>();
  }

  public String get(String key) {
    if (map.containsKey(key)) {
      return map.get(key);
    } else {
      return null;
    }
  }

  public void set (String key, String value) {
    if (!map.containsKey(key)) {
      map.put(key, value);
    } else {

    }
  }

  public void replicate(DB replicatedb) {

  }

}
