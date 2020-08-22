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
     * tricky��ϣ��
     ���Ӷ�
     O(N) ʱ�� O(1) �ռ�

     ˼·
     ˼·�ܼ򵥣��ص�����������һ��map����cows���������Сtrick��ʡ��ʱ��ʡ�˿ռ�
     map�����property:����3���λ������������������secret��û���ϵ�3���������Ϊ100������secret����100��û���ϵ�3������3���λ���Ǹ���������guess��û���ϵ�3��
     ������ĳһ��ʱ�̣�secret��ǰ���ֺ�guess��ǰ���ֲ�ͬ���ټ���secret��ǰ����Ϊ3����Ϊs=3�����Ǿ͵ò鿴map[3]���������Ǹ�������0��
     �����������˵��secret�����ģ��䲻�ϵģ�3��������һ����cows����
     ����Ǹ�����˵��guess���ж���ģ�û���ϵģ�3��������˵���������ϣ�cows++
     �����0��˵�����߶������࣬cows����
     ����guessҲһ��

     ע��
     cows++��������map[x] < 0 ���� map[x] == 0
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
                    if (map[s] < 0)//guess���ж���ģ���ΪС��0ֻ������guess��ɵ�
                        cows++;
                    if (map[g] > 0)//secret���ж���ģ���Ϊ����0ֻ������secret��ɵ�
                        cows++;
                    map[s]++;
                    map[g]--;
                }
            }
            return bulls + "A" + cows + "B";
        }
    }

    /**
     * ������Ҫ�����¼һ������ܴ����Ľⷨ��
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
     * �ҹ��ؾصĽⷨ��
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

    class Solution2 {
        public String getHint(String secret, String guess) {
            int bulls = 0;
            int cows = 0;

            int[] secretDigitsArr = new int[10];
            int[] guessDigitsArr = new int[10];

            for (int i = 0; i < secret.length(); i++) {
                int secretCh = secret.charAt(i) - '0';
                int guessCh = guess.charAt(i) - '0';

                if (secretCh == guessCh) {
                    bulls++;
                }
                else {
                    secretDigitsArr[secretCh]++;
                    guessDigitsArr[guessCh]++;
                }
            }

            for (int i = 0; i < secretDigitsArr.length; i++) {
                int secretChCount = secretDigitsArr[i];
                int guessChCount = guessDigitsArr[i];
                cows += Math.min(secretChCount, guessChCount);
            }

            StringBuilder sb = new StringBuilder();
            sb.append(bulls);
            sb.append("A");
            sb.append(cows);
            sb.append("B");

            return sb.toString();
        }
    }

}
