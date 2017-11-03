import java.util.*;

/**
 * 514. Freedom Trail
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.

 Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.

 Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.
 At the stage of rotating the ring to spell the key character key[i]:
 You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
 If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.
 Example:


 Input: ring = "godding", key = "gd"
 Output: 4
 Explanation:
 For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 Also, we need 1 more step for spelling.
 So the final output is 4.
 Note:
 Length of both ring and key will be in range 1 to 100.
 There are only lowercase letters in both strings and might be some duplcate characters in both strings.
 It's guaranteed that string key could always be spelled by rotating the string ring.
 */

public class FreedomTrail {

  //DP

  public class Solution {
    public int findRotateSteps(String ring, String key) {
      int n = ring.length();
      int m = key.length();
      int[][] dp = new int[m + 1][n];

      for (int i = m - 1; i >= 0; i--) {
        for (int j = 0; j < n; j++) {
          dp[i][j] = Integer.MAX_VALUE;
          for (int k = 0; k < n; k++) {
            if (ring.charAt(k) == key.charAt(i)) {
              int diff = Math.abs(j - k);
              int step = Math.min(diff, n - diff);
              dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
            }
          }
        }
      }

      return dp[0][0] + m;
    }
  }

  /**
   * Java Clear Solution, dfs+memoization
   Hi There! The key point in the problem is to make decision whether to move clockwise or anticlockwise.
   Actually to get optimal answer, we have to move clockwise for some characters of key and anti-clockwise
   for others. If apply brute force, then for each position in key we have two options,

   Search for the character clockwise
   Search for the character anti-clockwise
   To find optimal answer we need to try both options and get minimum of them.
   Thus, we obtain dfs solution for the problem. But, there are duplicate calculation for some positions.
   Therefore, we need to memorize states. The state is defined by position of thering and the index of
   character in the key. This way, we can avoid calculating number of steps for the same state.
   Code will clarify the idea more.
   */

  public class Solution2 {
    Map<String, Map<Integer, Integer>> memo;
    public int findRotateSteps(String ring, String key) {
      memo = new HashMap<>();
      return dfs(ring, key, 0);
    }

    private int findPos(String ring, char ch){ // find first occurrence clockwise
      return ring.indexOf(ch);
    }

    private int findBackPos(String ring, char ch){ //find first occurrence  anti-clockwise
      if(ring.charAt(0) == ch) return 0;
      for(int i = ring.length()-1;i>0;i--){
        if(ring.charAt(i) == ch) return i;
      }
      return 0;
    }

    private int dfs(String ring, String key, int i){
      if(i == key.length()) return 0;
      int res = 0;
      char ch = key.charAt(i);
      if(memo.containsKey(ring) && memo.get(ring).containsKey(i)) return memo.get(ring).get(i);
      int f = findPos(ring, ch);
      int b = findBackPos(ring, ch);
      int forward = 1+f+dfs(ring.substring(f)+ring.substring(0, f), key, i+1);
      int back = 1+ring.length()-b + dfs(ring.substring(b)+ring.substring(0, b),key, i+1);
      res = Math.min(forward, back);
      Map<Integer, Integer> ans = memo.getOrDefault(ring, new HashMap<>());
      ans.put(i, res);
      memo.put(ring, ans);
      return res;
    }
  }

}
