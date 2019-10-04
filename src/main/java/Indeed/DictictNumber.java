package Indeed;

import java.util.*;

/**
 * Created by cicean on 9/26/2018.
 */
public class DictictNumber {

    public int[] distinctNumber(int[][] A) {

        int[] results = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int last = A[i][0];
            for (int j = 1; j < A[i].length; j++) {
                if (j != 1 || j != A[i].length - 1) {
                    if (A[i][j] != last && A[i][j] != A[i][j+1]) {
                        results[i] = A[i][j];
                        break;
                    }
                } else {
                    if (A[i][j] != last) {
                        results[i] = last;
                        break;
                    }
                }

                last = A[i][j];
            }
        }

        return results;
    }

    public List<Integer> distinctNumber(List<List<Integer>> list) {
        List<Integer> results = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return results;
        }

        for (int i = 0; i < list.size(); i++) {
            int last = list.get(i).get(0);
            for (int j = 1; j < list.get(i).size(); j++) {
                if (j > 1 && j < list.get(i).size() - 1) {
                    if (list.get(i).get(j) != last && list.get(i).get(j) != list.get(i).get(j + 1)) {
                        results.add(list.get(i).get(j));
                        break;
                    }
                } else {
                    if (j == 1) {
                        if (list.get(i).get(j) != last && list.get(i).get(j) == list.get(i).get(j+1)) {
                            //System.out.print("j = 1, number = " + last);
                            results.add(last);
                            break;
                        } else if (list.get(i).get(j) != last && last == list.get(i).get(j+1)) {
                            results.add(list.get(i).get(j));
                            break;
                        }
                    } else {
                        results.add(list.get(i).get(list.get(i).size() - 1));
                    }

                }

                last = list.get(i).get(j);
            }
        }

        return results;

    }


    /*
We are writing an IDE for object-oriented programmers, and working on the autocompletion feature. Because class names can be long, we want to allow users to only type the beginning of the initial words in a name -- so, instead of "GraphViewController", they could type "Gra" or "GraViC". (Each word in the name will always start with a capital letter.)

Given a list of class names and an input string, return all the possible autocompletions for that input string. (Autocompletions always start from the beginning, so "Data" does not match "DetailedDataView".)

Assume the inputs are small enough that you don't need to optimize your function.

class_names = [
  "GraphView",
  "DetailedDataView",
  "DataGraphView",
  "DataController",
  "GraphViewController",
  "MouseClickHandler",
  "MathCalculationHandler",
  "DataScienceView",
]

n* length(words)*n

autocomplete(class_names, "Data")
# Expected output: [DataController, DataGraphView, DataScienceView]

autocomplete(class_names, "GVi")
# Expected output: [GraphView, GraphViewController]

autocomplete(class_names, "MaC")
# Expected output: [MathCalculationHandler]

autocomplete(class_names, "MCHandler")
# Expected output: [MouseClickHandler, MathCalculationHandler]

autocomplete(class_names, "MoCHandler")
# Expected output: [MouseClickHandler]

autocomplete(class_names, "MathHandler")
# Expected output: []

autocomplete(class_names, "DataScienceViewController")
# Expected output: []

Trie


 */

    import java.io.*;
    import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

    class Solution {
        public static void main(String[] args) {

        }
    }

/*

class Solution {

  public List<Integer> distinctNumber(List<List<Integer>> list) {
    List<Integer> results = new ArrayList<>();
    if (list == null || list.size() == 0) {
      return results;
    }

    for (int i = 0; i < list.size(); i++) {
      int last = list.get(i).get(0);
      for (int j = 1; j < list.get(i).size(); j++) {
        if (j > 1 && j < list.get(i).size() - 1) {
          if (list.get(i).get(j) != last && list.get(i).get(j) != list.get(i).get(j + 1)) {
            results.add(list.get(i).get(j));
            break;
          }
        } else {
          if (j == 1) {
            if (list.get(i).get(j) != last && list.get(i).get(j) == list.get(i).get(j+1)) {
              //System.out.print("j = 1, number = " + last);
              results.add(last);
              break;
            } else if (list.get(i).get(j) != last && last == list.get(i).get(j+1)) {
              results.add(list.get(i).get(j));
              break;
            }
          } else {
            results.add(list.get(i).get(list.get(i).size() - 1));
          }

        }

        last = list.get(i).get(j);
      }
    }

    return results;

  }

  public

  public static void main(String[] args) {

    List<List<Integer>> inputLists = Arrays.asList(
      Arrays.asList(1, 2, 1),
      Arrays.asList(2, 2, 2, 2, 2, 6, 2),
      Arrays.asList(3, 1, 1, 1),
      Arrays.asList(9, 9, 4),
      Arrays.asList(1, 9, 1,1,1,1,1,1,1,1,1,1)
    );


    Solution object = new Solution();

    System.out.print("results = " + Arrays.toString(object.distinctNumber(inputLists).toArray()));

  }
}
}
