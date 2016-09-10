package facebook;

/**
 * Created by cicean on 9/7/2016.
 */
public class outputAsOrder {

    public String outputAsOrder(String order, String input) {
        Map<Character, Integer> map = new HashMap<>();
        int[] arr = new int[input.length()];
        StringBuffer output = new StringBuffer();
        for(int i = 0; i < order.length() - 1; i++) {
            map.put(order.charAt(i), i);
        }
        for(int i = 0; i < input.length(); i++) {
            arr[i] = map.get(input.charAt(i));
        }
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++) {
            output.append(order.charAt(i));
        }
        return output.toString();
    }

    public String solution(String order, String input) {
        Map<Character, Integer> bucket = new HashMap<>();
        for(int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            Integer t = bucket.get(c);
            if(t == null) bucket.put(c, 1);
            else bucket.put(c, t + 1);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < order.length(); ++i) {
            char c = order.charAt(i);
            Integer t = bucket.get(c);
            for(int j = 0; t != null && j < t; ++j) {
                    sb.append(c);
            }
            bucket.remove(c);
        }
        for(int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if(bucket.containsKey(c)) sb.append(c);
        }
        return sb.toString();
    }

}
