import java.util.*;

/**
 * 535. Encode and Decode TinyURL
 Description
 Hints
 Submissions
 Discuss
 Solution
 Discuss  Pick One
 Note: This is a companion problem to the System Design problem: Design TinyURL.
 TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

 Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

 Seen this question in a real interview before?   Yes  No
 Companies
 Google Facebook Amazon Uber
 Related Topics
 Hash Table Math
 Similar Questions
 Design TinyURL
 */

public class EncodeandDecodeTinyURL {

  /**
   * Solution

   Approach #1 Using Simple Counter[Accepted]

   In order to encode the URL, we make use of a counter(ii), which is incremented for every new URL encountered.
   We put the URL along with its encoded count(ii) in a HashMap. This way we can retrieve it later at the time of decoding easily.
   Performance Analysis

   The range of URLs that can be decoded is limited by the range of \text{int}int.

   If excessively large number of URLs have to be encoded, after the range of \text{int}int is exceeded, integer overflow could lead to overwriting the previous URLs' encodings, leading to the performance degradation.

   The length of the URL isn't necessarily shorter than the incoming \text{longURL}longURL. It is only dependent on the relative order in which the URLs are encoded.

   One problem with this method is that it is very easy to predict the next code generated, since the pattern can be detected by generating a few encoded URLs.
   */

  public class Codec {
    Map<Integer, String> map = new HashMap<>();
    int i = 0;

    public String encode(String longUrl) {
      map.put(i, longUrl);
      return "http://tinyurl.com/" + i++;
    }

    public String decode(String shortUrl) {
      return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
  }

  /**
   * Approach #2 Variable-length Encoding[Accepted]

   Algorithm

   In this case, we make use of variable length encoding to encode the given URLs. For every \text{longURL}longURL,
   we choose a variable codelength for the input URL, which can be any length between 0 and 61. Further,
   instead of using only numbers as the Base System for encoding the URLSs, we make use of a set of integers and alphabets to be used for encoding.
   Performance Analysis

   The number of URLs that can be encoded is, again, dependent on the range of \text{int}int, since, the same countcount will be generated after overflow of integers.

   The length of the encoded URLs isn't necessarily short, but is to some extent dependent on the order in which the incoming \text{longURL}longURL's are encountered. For example, the codes generated will have the lengths in the following order: 1(62 times), 2(62 times) and so on.

   The performance is quite good, since the same code will be repeated only after the integer overflow limit, which is quite large.

   In this case also, the next code generated could be predicted by the use of some calculations.
   */

  public class Codec2 {

    private String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private HashMap<String, String> map = new HashMap<>();
    private int count = 1;

    public String getString() {
      int c = count;
      StringBuilder sb = new StringBuilder();
      while (c > 0) {
        c--;
        sb.append(chars.charAt(c % 62));
        c /= 62;
      }
      return sb.toString();
    }

    public String encode(String longUrl) {
      String key = getString();
      map.put(key, longUrl);
      count++;
      return "http://tinyurl.com/" + key;
    }

    public String decode(String shortUrl) {
      return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
  }

  /**
   * Approach #3 Using hashcode[Accepted]

   Algorithm

   In this method, we make use of an inbuilt function \text{hashCode()}hashCode() to determine a code for mapping every URL. Again, the mapping is stored in a HashMap for decoding.

   The hash code for a String object is computed(using int arithmetic) as −

   s[0]*31^{(n - 1)} + s[1]*31^{(n - 2)} + ... + s[n - 1]s[0]∗31
   ​(n−1)
   ​​ +s[1]∗31
   ​(n−2)
   ​​ +...+s[n−1] , where s[i] is the ith character of the string, n is the length of the string.

   Performance Analysis

   The number of URLs that can be encoded is limited by the range of \text{int}int, since \text{hashCode}hashCode uses integer calculations.

   The average length of the encoded URL isn't directly related to the incoming \text{longURL}longURL length.

   The \text{hashCode()}hashCode() doesn't generate unique codes for different string. This property of getting the same code for two different inputs is called collision. Thus, as the number of encoded URLs increases, the probability of collisions increases, which leads to failure.

   The following figure demonstrates the mapping of different objects to the same hashcode and the increasing probability of collisions with increasing number of objects.
   */

  public class Codec3 {
    Map<Integer, String> map = new HashMap<>();

    public String encode(String longUrl) {
      map.put(longUrl.hashCode(), longUrl);
      return "http://tinyurl.com/" + longUrl.hashCode();
    }

    public String decode(String shortUrl) {
      return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
  }




}
