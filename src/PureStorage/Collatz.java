package PureStorage;

import java.util.*;

public class Collatz {


    public static void collatz(int n) {
      System.out.print(n + " ");
      if (n == 1) return;
      else if (n % 2 == 0) collatz(n / 2);
      else collatz(3*n + 1);
    }

    public static void main(String[] args) {
      int n = Integer.parseInt(args[0]);
      collatz(n);
      System.out.println();
    }

  public static void collatz2(int n) {

    int counter = 0;
    int stepsTaken = 0;
    int largestNumber = 0;
    System.out.println();

    while ( n != 1 ){
      if ( ( n & 1 ) == 0 ) {
        System.out.print( (n = ( n / 2 )) + " " );
        stepsTaken++;
        counter++;
      }       else {
        System.out.print( (n = ( n * 3 ) + 1) + " " );
        stepsTaken++;
        counter++;
      }

      if ( n > largestNumber ){
        largestNumber = n;
      }

      if (counter == 9){
        counter = 0;
        System.out.print("\n");
      }
    }

    System.out.println();
    System.out.println("\nTerminated after " + stepsTaken + " steps.");
    System.out.println("The largest value was " + largestNumber + ".");
  }



}
