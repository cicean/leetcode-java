import java.util.*;

/**
 * Created by cicean on 9/20/2016.
 * 401. Binary Watch  QuestionEditorial Solution  My Submissions
 Total Accepted: 2671 Total Submissions: 5787 Difficulty: Easy
 A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

 Each LED represents a zero or one, with the least significant bit on the right.


 For example, the above binary watch reads "3:25".

 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

 Example:

 Input: n = 1
 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 Note:
 The order of output does not matter.
 The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 Hide Company Tags Google
 Hide Tags Backtracking Bit Manipulation
 Hide Similar Problems (M) Letter Combinations of a Phone Number (E) Number of 1 Bits

 */
public class BinaryWatch {

    List<String> rlt = new ArrayList();
    public List<String> readBinaryWatch2(int num) {
        dfs(0, 0, 0, num, 0, new boolean[10]);
        return rlt;
    }

    public void dfs(int hh, int mm, int level, int max_level, int index, boolean[] used){
        if(level == max_level){
            if(hh <= 11 && mm <= 59){
                if(mm<=9) rlt.add(hh +":0" + mm);
                else rlt.add(hh + ":" + mm);
            }
            return;
        }
        for(int i=index; i<10; i++){
            if(!used[i]){
                used[i] = true;
                if(i<=3) dfs(hh + (int)Math.pow(2,i), mm, level+1, max_level, i+1, used);
                else dfs(hh, mm + (int)Math.pow(2,i-4), level+1, max_level, i+1, used);
                used[i] = false;
            }
        }
    }

}
