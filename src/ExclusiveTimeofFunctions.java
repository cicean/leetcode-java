import java.util.*;

/**
 * ns636. Exclusive Time of Functions
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

 Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

 A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

 Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

 Example 1:
 Input:
 n = 2
 logs =
 ["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
 Output:[3, 4]
 Explanation:
 Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1.
 Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
 Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time.
 So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
 Note:
 Input logs will be sorted by timestamp, NOT log id.
 Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
 Two functions won't start or end at the same time.
 Functions could be called recursively, and will always end.
 1 <= n <= 100

 */

public class ExclusiveTimeofFunctions {


  /**
   * Approach #1 Using Stack [Time Limit Exceeded]

   Before starting off with the solution, let's discuss a simple idea. Suppose we have three functions func_1func
   ​1
   ​​ , func_2func
   ​2
   ​​  and func_3func
   ​3
   ​​  such that func_1func
   ​1
   ​​  calls func_2func
   ​2
   ​​ and then func_2func
   ​2
   ​​  calls func_3func
   ​3
   ​​ . In this case, func_3func
   ​3
   ​​  starts at the end and ends first, func_2func
   ​2
   ​​  starts at 2nd position and ends at the 2nd last step. Similarly, func_1func
   ​1
   ​​ starts first and ends at the last position. Thus, we can conclude that the function which is entered at the end finishes first and the one which is entered first ends at the last position.

   From the above discussion, we can conclude that we can make use of a stackstack to solve the given problem. We can start by pushing the first function's id from the given logslogs list onto the array. We also keep a track of the current timetime. We also make use of a resres array, such that res[i]res[i] is to keep a track of the exclusive time spent by the Fucntion with function id ii till the current time.

   Now, we can move on to the next function in logslogs. The start/end time of the next function will obviously be larger than the start time of the function on the stackstack. We keep on incrementing the current timetime and the exclusive time for the function on the top of the stackstack till the current time becomes equal to the start/end time of the next function in the logslogs list.

   Thus, now, we've reached a point, where the control shifts from the last function to a new function, due to a function call(indicated by a start label for the next function), or the last function could exit(indicated by the end label for the next function). Thus, we can no longer continue with the same old function.

   If the next function includes a start label, we push this function on the top of the stackstack, since the last function would need to be revisited again in the future. On the other hand, if the next function includes an end label, it means the last function on the top of the stackstack is terminating.

   We also know that an end label indicates that this function executes till the end of the given time. Thus, we need to increment the current timetime and the exclusive time of the last function as well to account for this fact. Now, we can remove(pop) this function from the stackstack. We can continue this process for every function in the logslogs list.

   At the end, the resres array gives the exclusive times for each function.

   Summarizing the above process, we need to do the following:

   Push the function id of the first function in the logslogs list on the stackstack.

   Keep incrementing the exlusive time(along with the current time) corresponding to the function on the top of the stackstack(in the resres array), till the current time equals the start/end time corresponding to the next function in the logslogs list.

   If the next function has a 'start' label, push this function's id onto the stack. Otherwise, increment the last function's exclusive time(along with the current time), and pop the function id from the top of the stack.

   Repeat steps 2 and 3 till all the functions in the logslogs list have been considered.

   Return the resultant exlcusive time(resres).

   Complexity Analysis

   Time complexity : O(t)O(t). We increment the time till all the functions are done with the execution. Here, tt refers to the end time of the last function in the logslogs list.

   Space complexity : O(n)O(n). The stackstack can grow upto a depth of atmost n/2n/2. Here, nn refers to the number of elements in the given logslogs list.

   */

  public class Solution {
    public int[] exclusiveTime(int n, List< String > logs) {
      Stack < Integer > stack = new Stack < > ();
      int[] res = new int[n];
      String[] s = logs.get(0).split(":");
      stack.push(Integer.parseInt(s[0]));
      int i = 1, time = Integer.parseInt(s[2]);
      while (i < logs.size()) {
        s = logs.get(i).split(":");
        while (time < Integer.parseInt(s[2])) {
          res[stack.peek()]++;
          time++;
        }
        if (s[1].equals("start"))
          stack.push(Integer.parseInt(s[0]));
        else {
          res[stack.peek()]++;
          time++;
          stack.pop();
        }
        i++;
      }
      return res;
    }
  }

  /**
   *
   * Approach #2 Better Approach [Accepted]

   Algorithm

   In the last approach, for every function on the top of the stackstack, we incremented the current time and the exclusive time of this same function till the current time became equal to the start/end time of the next function.

   Instead of doing this incrementing step by step, we can directly use the difference between the next function's start/stop time and the current function's start/stop time. The rest of the process remains the same as in the last approach.

   The following animation illustrates the process.

   Complexity Analysis

   Time complexity : O(n)O(n). We iterate over the entire logslogs array just once. Here, nn refers to the number of elements in the logslogs list.

   Space complexity : The stackstack can grow upto a depth of atmost n/2n/2. Here, nn refers to the number of elements in the given logslogs list.

   */

  public class Solution2 {
    public int[] exclusiveTime(int n, List<String> logs) {
      int[] res = new int[n];
      Stack<Integer> stack = new Stack<>();
      int prevTime = 0;
      for (String log : logs) {
        String[] parts = log.split(":");
        if (!stack.isEmpty()) res[stack.peek()] +=  Integer.parseInt(parts[2]) - prevTime;
        prevTime = Integer.parseInt(parts[2]);
        if (parts[1].equals("start")) stack.push(Integer.parseInt(parts[0]));
        else {
          res[stack.pop()]++;
          prevTime++;
        }
      }
      return res;
    }
  }

}
