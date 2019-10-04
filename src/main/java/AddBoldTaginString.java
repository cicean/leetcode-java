import java.util.*;

/**
 * 616. Add Bold Tag in String
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

 Example 1:
 Input:
 s = "abcxyz123"
 dict = ["abc","123"]
 Output:
 "<b>abc</b>xyz<b>123</b>"
 Example 2:
 Input:
 s = "aaabbcc"
 dict = ["aaa","aab","bc"]
 Output:
 "<b>aaabbc</b>c"
 Note:
 The given dict won't contain duplicates, and its length won't exceed 100.
 All the strings in input have length in range [1, 1000].
 */

public class AddBoldTaginString {
    /**
     * Approach #1 Brute Force [Time Limit Exceeded]

     Algorithm

     In order to proceed with the solution to the given problem, the first basic step is to identify the substrings of
     the given string ss which exist in the given dictionary dictdict. For doing this, firstly, we add the given words
     in the dictionary dictdict to a setset. Then, we iterate over the given string ss and consider every possible
     substring of ss to check if it exists in the given dictionary dictdict. If so, we add the start and end index to
     a listlist. Each element of dictdict takes the form: [i, j][i,j]. Here, ii and jj represent the start and the end
     index of the substring which matches with any word in the dictionary.

     By doing this, we are done with the first step. Now, the problem reduces mainly to identifying the sets of
     overlapping intervals from among the intervals present in the listlist and adding the tags appropriately at those locations.

     To do so, firstly, we sort the listlist based on the start indices. In case of equality of start indices, we sort
     the elements based on their end indices. After this, we start creating our resultant string, resres. The substring
     of ss from the beginning till the first start index needs to be added as such to resres. Then, we pick up the first
     starting index from the listlist. This index acts as the position to put the opening bold tag. In order to determine
     the end index(for placing the closing bold tag) by considering the merging required for consecutive or overlapping
     intervals, for the current range considered, we check if the start index of the next interval in listlist lies
     before or at the index one larger than end of the current interval. If so, it indicates that either there is an
     overlap(if the next start index lies before or at the current end index), or the existence of consecutive intervals
     (if the next start index lies at the current end index + 1).

     If any of these cases occur, we know that the next interval can be merged with the current one. Thus, we update the
     end index to point to the next interval's end index. We also update our current interval to the next interval and
     again check the overlapping or consecutive property of the new interval and its next one. We keep on updating the
     current interval till we reach an interval which doesn't satisfy any of these properties. The end index of the last
     interval found satisfying this property marks the position for the closing bold tag.

     Since, we had reached the end of the overlapping/consecutive intervals, the substring lying beyond the end index
     found last and the start index existing beyond this end index in the listlist doesn't exist in the dicitionary dictdict.
     Thus, we need to append this substring directly to the resultant string formed so far.

     We keep on continuing this process till the whole range till the end index of the last interval has been exhausted.
     The substring left beyond this point is also appended directly to the resres formed till now.

     Complexity Analysis

     Time complexity : O(s^3). Generating list of intervals will take O(s^3), where ss represents string length.

     Space complexity : O(s+d+s*l)O(s+d+s∗l). resres size grows upto ss and setset size will be equal to the size of
     dictdict. Here, dd refers to the size of dictdict.And listlist size can grow upto O(s*l)O(s∗l) in worst case, where ll refers to dictdict size.
     */

    public class Solution {
        public String addBoldTag(String s, String[] dict) {
            List < int[] > list = new ArrayList < > ();
            Set < String > set = new HashSet < > (Arrays.asList(dict));
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    if (set.contains(s.substring(i, j + 1)))
                        list.add(new int[] {i, j});
                }
            }
            if (list.size() == 0)
                return s;
            Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            int start, prev = 0, end = 0;
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                res.append(s.substring(prev, list.get(i)[0]));
                start = i;
                end = list.get(i)[1];
                while (i < list.size() - 1 && list.get(i + 1)[0] <= end + 1) {
                    end = Math.max(end, list.get(i + 1)[1]);
                    i++;
                }
                res.append("<b>" + s.substring(list.get(start)[0], end + 1) + "</b>");
                prev = end + 1;
            }
            res.append(s.substring(end + 1, s.length()));
            return res.toString();
        }
    }

    /**
     * Approach #2 Similar to Merge Interval Problem [Accepted]

     In the last approach, to identify the substrings of ss which exist in the dictionary dictdict, we checked every
     possible substring of ss to see if it exists in this. Instead of doing the identification in this manner, we can
     be a bit smarter. We can pick up every word of the dictionary. For every word dd of the dictionary chosen currently,
     say of length length_dlength
     ​d
     ​​ , it is obvious that the substrings in ss only with length length_dlength
     ​d
     ​​ , can match with the dd. Thus, instead of blindly checking for dd's match with every substring in ss, we check
     only the substrings with length length_dlength
     ​d
     ​​ . The matching substrings' indices are again added to the listlist similar to the last approach.

     The rest of the process remains the same as the last approach. The following animation illustrates the process for
     a clearer understanding.
     Complexity Analysis

     Time complexity : O(l*s*x)O(l∗s∗x). Generating list will take O(l*s*x)O(l∗s∗x), where xx is the average string length of dictdict.

     Space complexity : O(s+s*l)O(s+s∗l). resres size grows upto O(s)O(s) and listlist size can grow upto O(s*l)O(s∗l) in worst case.
     */

    public class Solution2 {
        public String addBoldTag(String s, String[] dict) {
            List < int[] > list = new ArrayList < > ();
            for (String d: dict) {
                for (int i = 0; i <= s.length() - d.length(); i++) {
                    if (s.substring(i, i + d.length()).equals(d))
                        list.add(new int[] {i, i + d.length() - 1});
                }
            }
            if (list.size() == 0)
                return s;
            Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            int start, prev = 0, end = 0;
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                res.append(s.substring(prev, list.get(i)[0]));
                start = i;
                end = list.get(i)[1];
                while (i < list.size() - 1 && list.get(i + 1)[0] <= end + 1) {
                    end = Math.max(end, list.get(i + 1)[1]);
                    i++;
                }
                res.append("<b>" + s.substring(list.get(start)[0], end + 1) + "</b>");
                prev = end + 1;
            }
            res.append(s.substring(end + 1, s.length()));
            return res.toString();
        }
    }

    /**
     * Approach #3 Using Boolean(Marking) Array[Accepted]

     This approach is inspired by @compton_scatter.

     Another idea could be to merge the process of identification of the substrings in ss matching with the words in dictdict. To do so, we make use of an array boldbold for marking the positions of the substrings in ss which are present in dictdict. A True value at bold[i]bold[i] indicates that the current character is a part of the substring which is present in dictdict.

     We identify the substrings in ss which are present in dictdict similar to the last approach, by considering only substrings of length length_dlength
     ​d
     ​​  for a dictionary word dd. Whenver such a substring is found with its beginning index as ii(and end index (i + length_d -1)(i+length
     ​d
     ​​ −1)), we mark all such positions in boldbold as True.

     Thus, in this way, whenever a overlapping or consecutive matching substrings exist in ss, a continuous sequence of True values is present in boldbold. Keeping this idea in mind, we traverse over the string ss and keep on putting the current character in the resultant string resres. At every step, we also check if the boldbold array contains the beginning or end of a continuous sequence of True values. At the beginnning of such a sequence, we put an opening bold tag and then keep on putting the characters of ss till we find a position corresponding to which the last sequence of continuous True values breaks(the first False value is found). We put a closing bold tag at such a position. After this, we again keep on putting the characters of ss in resres till we find the next True value and we keep on continuing the process in the same manner.

     The following animation illustrates the process for a better visualization of the process.

     Complexity Analysis

     Time complexity : O(l*s*x)O(l∗s∗x). Three nested loops are there to fill boldbold array.

     Space complexity : O(s)O(s). resres and boldbold size grows upto O(s)O(s).
     */
    public class Solution3 {
        public String addBoldTag(String s, String[] dict) {
            boolean[] bold = new boolean[s.length()];
            for (String d: dict) {
                for (int i = 0; i <= s.length() - d.length(); i++) {
                    if (s.substring(i, i + d.length()).equals(d)) {
                        for (int j = i; j < i + d.length(); j++)
                            bold[j] = true;
                    }
                }
            }
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < s.length();) {
                if (bold[i]) {
                    res.append("<b>");
                    while (i < s.length() && bold[i])
                        res.append(s.charAt(i++));
                    res.append("</b>");
                } else
                    res.append(s.charAt(i++));
            }
            return res.toString();
        }
    }

    class Solution4 {
        public String addBoldTag(String s, String[] dict) {
            if (dict.length == 0 || s == null) return s;
            int n = s.length();
            int[] mark = new int[n+1];
            for(String d : dict) {
                int i = -1;
                while((i = s.indexOf(d, i+1)) >= 0) {
                    mark[i]++;
                    mark[i + d.length()]--;
                }
            }
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            for(int i = 0; i <= n; i++) {
                int cur = sum + mark[i];
                if (cur > 0 && sum == 0) sb.append("<b>");
                if (cur == 0 && sum > 0) sb.append("</b>");
                if (i == n) break;
                sb.append(s.charAt(i));
                sum = cur;
            }
            return sb.toString();
        }
    }
}
