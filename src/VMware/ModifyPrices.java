package VMware;

import java.util.*;

/**
 * Michael is a shop owner who keeps n list, l, of the name and sale price each itme in inventory. The Store employees record the name and sale price of each item sold. Michael suspects his manager, Alex, of embezzling money and modifying the sale prices of some of the items.

 Complete the function so that it returns the number of incorrect sale prices recorded by Alex. It has 4 parameters.

 origItems
 An array of Strings. where each element is an item name
 origPrices
 An array of floating point numbers, where each element contains the original (correct) price of the item in the corresponding index of *origItems*
 items
 An array of string containing the name of the items with sales recorded by Alex
 prices
 An array of floating point numbers, where each element contains the sale price recorded by Alex for the item in the corresponding index of items.
 Constraints

 1 <= N <= 10
 1 <= M <= N
 1.00 <= origPrices , prices <= 100000.00, where 0 <= i < N, and 0 <= j < M
 Output Format Return the number of items whose sale prices were incorrectly recorded by Alex.

 Sample Input 1
 String[] origItems = {"rice","sugar","wheat","cheese"};
 float[] origPrices = {16.89F, 56.92F, 20.89F, 345.99F};
 String[] items = {"rice","cheese"};
 float[] origPrices = {15.99F, 400.79F};

 Sample Output 1
 2

 Sample Input 2
 String[] origItems = {"chocolate", "cheese", "tomato"} float[] origPrices = {15F, 300.90F, 23.44F} String[] items = {"chocolate", "cheese", "tomato"} float[] origPrices = {15F, 300.90F, 10F}

 Sample Output 2
 1 The price for tomato does not match the original price list, so we return 1 (the number of incorrectly recorded sale prices).
 */

public class ModifyPrices {

  public static void main(String[] args) {
    String[] origItems = { "rice", "sugar", "wheat", "cheese" };
    float[] origPrices = { 16.89F, 56.92F, 20.89F, 345.99F };
    String[] items = { "rice", "cheese" };
    float[] prices = { 15.99F, 400.79F };
    List<String> originalItems = new ArrayList(Arrays.asList(origItems));
    List<Float> originalPrices = addFloatsToArray(origPrices);
    List<String> actualItems = new ArrayList(Arrays.asList(items));
    List<Float> actualPrices = addFloatsToArray(prices);
    int count = 0;
    for (String aItems : actualItems) {
      int oi = originalItems.indexOf(aItems);
      float op = originalPrices.get(oi);
      int aii = actualItems.indexOf(aItems);
      if (op != actualPrices.get(aii)) {
        count++;
      }
    }
    System.out.println(count);

  }

  private static List addFloatsToArray(float[] a) {
    List<Float> l = new ArrayList<>();
    for (float f : a) {
      l.add(f);
    }
    return l;
  }

}
