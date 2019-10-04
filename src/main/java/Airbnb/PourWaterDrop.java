package Airbnb;

import java.util.*;

/**
 * Created by cicean on 9/13/2018.
 */
public class PourWaterDrop {

    public char[][] simulateWaterDrop(int[] inputs, int V, int P) {
        int highMax = 0;
        for (int i = 0; i < inputs.length; i++) {
            highMax = Math.max(highMax, inputs[i]);
        }

        char[][] result = new char[highMax + 1][inputs.length];

        for (char[] cArray : result) {
            Arrays.fill(cArray, ' ');
        }

        for (int j = 0; j < inputs.length; j++) {
            for (int i = highMax - inputs[j]; i < highMax + 1; i++) {
                result[i][j] = '+';
            }
        }

        int[] waters = new int[inputs.length];
        int pourP;
        while (V > 0) {
            int left = P - 1;
            while (left >= 0) {
                if (inputs[left] + waters[left] > inputs[left + 1] + waters[left + 1]) {
                    break;
                }
                left--;
            }

            if (inputs[left + 1] + waters[left + 1] < inputs[P] + waters[P]) {
                pourP = left + 1;
                result[highMax - inputs[pourP] - waters[pourP] - 1][pourP] = 'w';
                waters[pourP]++;
                V--;
                continue;
            }

            int right = P + 1;
            while (right < inputs.length) {
                if (inputs[right] + waters[right] > inputs[right - 1] +
                        waters[right - 1]) {
                    break;
                }
                right++;
            }
            if (inputs[right - 1] + waters[right - 1] < inputs[P] +
                    waters[P]) {
                pourP = right - 1;
                result[highMax - inputs[pourP] - waters[pourP] - 1][pourP] = 'w';
                waters[pourP]++;
                V--;
                continue;
            }
            pourP = P;
            result[highMax - inputs[pourP] - waters[pourP] - 1][pourP] = 'w';
            waters[pourP]++;
            V--;
        }


        return result;
    }



    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java 8.");

        for (String string : strings) {
            System.out.println(string);
        }

        Solution s = new Solution();

        int[] inputs = {5,4,2,1,2,3,2,1,0,1,2,4};

        char[][] result = s.simulateWaterDrop(inputs,8,5);

        for (char[] c : result) {
            System.out.println(c);
        }

    }



    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java 8.");

        for (String string : strings) {
            System.out.println(string);
        }

        Solution s = new Solution();

        int[] inputs = {5,4,2,1,2,3,2,1,0,1,2,4};

        char[][] result = s.simulateWaterDrop(inputs,0,0);

        for (char[] c : result) {
            System.out.println(c);
        }

    }
}
