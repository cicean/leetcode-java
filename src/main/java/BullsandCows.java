import java.util.HashMap;
import java.util.Map;

/**
 * Created by cicean on 8/29/2016.
 * 299. Bulls and Cows
 Total Accepted: 40424 Total Submissions: 127686 Difficulty: Easy
 You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

 For example:

 Secret number:  "1807"
 Friend's guess: "7810"
 Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".

 Please note that both secret number and friend's guess may contain duplicate digits, for example:

 Secret number:  "1123"
 Friend's guess: "0111"
 In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
 You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

 Credits:
 Special thanks to @jeantimex for adding this problem and creating all test cases.

 Hide Tags Hash Table

 */
public class BullsandCows {

    /**
     * tricky哈希表法
     复杂度
     O(N) 时间 O(1) 空间

     思路
     思路很简单，重点是这里用了一个map来找cows，很巧妙的小trick，省了时间省了空间
     map满足的property:假设3这个位置是正数，正数代表secret有没配上的3，这个正数为100，代表secret里有100个没配上的3；假设3这个位置是负数，代表guess有没配上的3；
     假设在某一个时刻，secret当前数字和guess当前数字不同，再假设secret当前数字为3，记为s=3，我们就得查看map[3]是正数还是负数还是0？
     如果是正数：说明secret里多余的（配不上的）3又增加了一个，cows不变
     如果是负数：说明guess里有多余的（没配上的）3，二话不说，给他配上，cows++
     如果是0：说明两边都不多余，cows不变
     对于guess也一样

     注意
     cows++的条件是map[x] < 0 不是 map[x] == 0
     */

    public class Solution {
        public String getHint(String secret, String guess) {
            int[] map = new int[10];
            int bulls = 0, cows = 0;
            for (int i = 0; i < secret.length(); i++) {
                int s = secret.charAt(i) - '0';
                int g = guess.charAt(i) - '0';
                if (s == g)
                    bulls++;
                else {
                    if (map[s] < 0)//guess里有多余的，因为小于0只可能是guess造成的
                        cows++;
                    if (map[g] > 0)//secret里有多余的，因为大于0只可能是secret造成的
                        cows++;
                    map[s]++;
                    map[g]--;
                }
            }
            return bulls + "A" + cows + "B";
        }
    }

    /**
     * 这里主要是想记录一下这个很聪明的解法：
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int[] nums = new int[10];
        int countA = 0, countB = 0;

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0', g = guess.charAt(i) - '0';
            if (s == g) {
                countA++;
            } else {
                if (nums[s] < 0) countB++;
                if (nums[g] > 0) countB++;
                nums[s]++;
                nums[g]--;
            }
        }

        return countA + "A" + countB + "B";
    }

    /**
     * 我规规矩矩的解法：
     */

    public String getHint_1(String secret, String guess) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        boolean[] visited = new boolean[guess.length()];
        int bull = 0, cow = 0;

        for (char c : secret.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        int len = Math.min(secret.length(), guess.length());
        for (int i = 0;  i < len; i++) {
            char c1 = secret.charAt(i), c2 = guess.charAt(i);
            if (c1 == c2) {
                bull++;
                visited[i] = true;
                map.put(c2, map.get(c2) - 1);
                if (map.get(c2) == 0) map.remove(c2);
            }
        }

        for (int i = 0; i < secret.length(); i++) {
            char c = guess.charAt(i);
            if (!visited[i]) {
                if (map.containsKey(c)) {
                    cow++;
                    visited[i] = true;
                    map.put(c, map.get(c) - 1);
                    if (map.get(c) == 0) map.remove(c);
                }
            }
        }


        return bull + "A" + cow + "B";
    }

}
