package VMware;

import java.util.*;

/**
 *
 */

class Stack{
  //Private Declarations
  private int top;
  private String a[] = new String [100];
  //Public Declarations
  public Stack(){
    top = -1;
    a[0] = "\0";
  }
  public void push(String x){
    if (top != 99){
      a[++top] = x;
    }
    else{
      System.out.println("Stack overflow");
    }
  };
  public String pop(){
    if (top == -1){
      System.out.println("\nStack empty!");
      return "\n";
    }
    else{
      return a[top--];
    }
  };
  public int ret_top(){
    return top;
  };
}

public class PrefixToPostfix {





    public static void main(String[] args) {
      //Declaration
      Scanner in = new Scanner (System.in);
      Stack op = new Stack ();
      Stack sym = new Stack ();
      char ch;
      int i;
      String exp, str, str1, str2, str3;

      //Taking input from the user
      System.out.println("Enter the prefix expression : ");
      exp = in.next();

      for (i=0; i<exp.length(); i++){
        ch = exp.charAt(i);
        if((ch == '+')
            ||(ch == '-')
            ||(ch == '*')
            ||(ch == '/')
            ||(ch == '$')){
          str = Character.toString(ch);
          op.push(str);
        }
        else{
          str = Character.toString(ch);
          sym.push(str);
          if (sym.ret_top() == 1){
            str1 = sym.pop();
            str2 = sym.pop();
            str3 = op.pop();
            str1 = str2.concat(str1);
            str1 = str1.concat(str3);
            sym.push(str1);
          }
        }
      }

      //Output
      str = sym.pop();
      System.out.println("After conversion to postfix" + ": " + str);
      in.close();
    }


}
