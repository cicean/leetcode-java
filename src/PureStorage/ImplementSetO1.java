package PureStorage;

import java.util.*;

public class ImplementSetO1 {


  }

    /* Java program to design a data structure that support folloiwng operations
   in Theta(n) time
   a) Insert
   b) Delete
   c) Search
   d) getRandom */

// class to represent the required data structure
class MyDS
{
  ArrayList<Integer> arr;   // A resizable array

  // A hash where keys are array elements and vlaues are
  // indexes in arr[]
  HashMap<Integer, Integer>  hash;

  // Constructor (creates arr[] and hash)
  public MyDS()
  {
    arr = new ArrayList<Integer>();
    hash = new HashMap<Integer, Integer>();
  }

  // A Theta(1) function to add an element to MyDS
  // data structure
  void add(int x)
  {
    // If ekement is already present, then noting to do
    if (hash.get(x) != null)
      return;

    // Else put element at the end of arr[]
    int s = arr.size();
    arr.add(x);

    // And put in hash also
    hash.put(x, s);
  }

  // A Theta(1) function to remove an element from MyDS
  // data structure
  void remove(int x)
  {
    // Check if element is present
    Integer index = hash.get(x);
    if (index == null)
      return;

    // If present, then remove element from hash
    hash.remove(x);

    // Swap element with last element so that remove from
    // arr[] can be done in O(1) time
    int size = arr.size();
    Integer last = arr.get(size-1);
    Collections.swap(arr, index,  size-1);

    // Remove last element (This is O(1))
    arr.remove(size-1);

    // Update hash table for new index of last element
    hash.put(last, index);
  }

  // Returns a random element from MyDS
  int getRandom()
  {
    // Find a random index from 0 to size - 1
    Random rand = new Random();  // Choose a different seed
    int index = rand.nextInt(arr.size());

    // Return element at randomly picked index
    return arr.get(index);
  }

  // Returns index of element if element is present, otherwise null
  Integer search(int x)
  {
    return hash.get(x);
  }
}

// Driver class
class Main {

  public static void main(String[] args) {
    MyDS ds = new MyDS();
    ds.add(10);
    ds.add(20);
    ds.add(30);
    ds.add(40);
    System.out.println(ds.search(30));
    ds.remove(20);
    ds.add(50);
    System.out.println(ds.search(50));
    System.out.println(ds.getRandom());
  }
}


