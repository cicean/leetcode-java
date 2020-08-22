/**
 * 604. Design Compressed String Iterator
 * Easy
 *
 * 232
 *
 * 93
 *
 * Add to List
 *
 * Share
 * Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.
 *
 * The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.
 *
 * next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
 * hasNext() - Judge whether there is any letter needs to be uncompressed.
 *
 * Note:
 * Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.
 *
 * Example:
 *
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 *
 * iterator.next(); // return 'L'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 't'
 * iterator.next(); // return 'C'
 * iterator.next(); // return 'o'
 * iterator.next(); // return 'd'
 * iterator.hasNext(); // return true
 * iterator.next(); // return 'e'
 * iterator.hasNext(); // return false
 * iterator.next(); // return ' '
 * Accepted
 * 17,203
 * Submissions
 * 46,887
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * nikhiltiware
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 * LRU Cache
 * Medium
 * String Compression
 * Easy
 */
public class DesignCompressedStringIterator {
    /**
     * Solution
     * Approach #1 Uncompressing the String [Time Limit Exceeded]
     * Algorithm
     *
     * In this approach, we make use of precomputation. We already form the uncompressed string and append the uncompressed letters for each compressed letter in the compressedStringcompressedString to the resres stringbuilder. To find the uncompressed strings to be stored in resres, we traverse over the given compressedStringcompressedString. Whenver we find an alphabet, we find the number following it by making use of decimal mathematics. Thus, we get the two elements(alphabet and the count) required for forming the current constituent of the uncompressed string.
     *
     * Now, we'll look at how the next() and hasNext() operations are performed:
     *
     * next(): We start off by checking if the compressed string has more uncompressed letters pending. If not, hasNext() returns a False value and next() returns a ' '. Otherwise, we return the letter pointed by ptrptr, which indicates the next letter to be returned. Before returning the letter, we also update the ptrptr to point to the next letter in resres.
     *
     * hasNext(): If the pointer ptrptr reaches beyond the end of resres array, it indicates that no more uncompressed letters are left beyond the current index pointed by ptrptr. Thus, we return a False in this case. Otherwise, we return a True value.
     *
     *
     * Performance Analysis
     *
     * We precompute the elements of the uncompressed string. Thus, the space required in this case is O(m)O(m), where mm refers to the length of the uncompressed string.
     *
     * The time required for precomputation is O(m)O(m) since we need to generate the uncompressed string of length mm.
     *
     * Once the precomputation has been done, the time required for performing next() and hasNext() is O(1)O(1) for both.
     *
     * This approach can be easily extended to include previous(), last() and find() operations. All these operations require the use an index only and thus, take O(1)O(1) time. Operations like hasPrevious() can also be easily included.
     *
     * Since, once the precomputation has been done, next() requires O(1)O(1) time, this approach is useful if next() operation needs to be performed a large number of times. However, if hasNext() is performed most of the times, this approach isn't much advantageous since precomputation needs to be done anyhow.
     *
     * A potential problem with this approach could arise if the length of the uncompressed string is very large. In such a case, the size of the complete uncompressed string could become so large that it can't fit in the memory limits, leading to memory overflow.
     */
    public class StringIterator {
        StringBuilder res=new StringBuilder();
        int ptr=0;
        public StringIterator(String s) {
            int i = 0;
            while (i < s.length()) {
                char ch = s.charAt(i++);
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                for (int j = 0; j < num; j++)
                    res.append(ch);
            }
        }
        public char next() {
            if (!hasNext())
                return ' ';
            return res.charAt(ptr++);
        }
        public boolean hasNext() {
            return ptr!=res.length();
        }
    }
     /** Approach #2 Pre-Computation [Accepted]
     * Algorithm
     *
     * In this approach, firstly, we split the given compressedStringcompressedString based on numbers(0-9) and store the values(alphabets) obtained in charschars array. We also split the compressedStringcompressedString based on the alphabets(a-z, A-Z) and store the numbers(in the form of a string) in a numsnums array(after converting the strings obtained into integers). We do the splitting by making use of regular expression matching.
     *
     * A regular expression is a special sequence of letters that helps you match or find other strings or sets of strings, using a specialized syntax held in a pattern. They can be used to search, edit, or manipulate text and data.
     *
     * This splitting using regex is done as a precomputation step. Now we'll look at how the next() and hasNext() operations are implemented.
     *
     * next(): Every time the next() operation is performed, firstly we check if there are any more letters to be uncompressed. We check it by making use of hasNext() function. If there aren't any more letters left, we return a ' '. We make use of a pointer ptrptr to keep a track of the letter in the compressedStringcompressedString that needs to be returned next. If there are more letters left in the uncompressed string, we return the current letter pointed to by ptrptr. But, before returning this letter, we also decrement the nums[ptr]nums[ptr] entry to indicate that the current letter is pending in the uncompressed string by one lesser count. On decrementing this entry, if it becomes zero, it indicates that no more instances of the current letter exist in the uncompressed string. Thus, we update the pointer ptrptr to point to the next letter.
     *
     * hasNext(): For performing hasNext() operation, we simply need to check if the ptrptr has already reached beyong the end of charschars array. If so, it indicates that no more compressed letters exist in the compressedStringcompressedString. Hence, we return a False value in this case. Otherwise, more compressed letters exist. Hence, we return a True value in this case.
     *
     *
     * Performance Analysis
     *
     * The space required for storing the results of the precomputation is O(n)O(n), where nn refers to the length of the compressed string. The numsnums and charschars array contain a total of nn elements.
     *
     * The precomputation step requires O(n)O(n) time. Thus, if hasNext() operation is performed most of the times, this precomputation turns out to be non-advantageous.
     *
     * Once the precomputation has been done, hasNext() and next() requires O(1)O(1) time.
     *
     * This approach can be extended to include the previous() and hasPrevious() operations, but that would require making some simple modifications to the current implementation.
     */

    public class StringIterator {
        int ptr = 0;
        String[] chars;int[] nums;
        public StringIterator(String compressedString) {
            nums = Arrays.stream(compressedString.substring(1).split("[a-zA-Z]+")).mapToInt(Integer::parseInt).toArray();;
            chars = compressedString.split("[0-9]+");
        }
        public char next() {
            if (!hasNext())
                return ' ';
            nums[ptr]--;
            char res=chars[ptr].charAt(0);
            if(nums[ptr]==0)
                ptr++;
            return res;
        }
        public boolean hasNext() {
            return ptr != chars.length;
        }
    }
     /** Approach #3 Demand-Computation [Accepted]
     * Algorithm
     *
     * In this approach, we don't make use of regex for finding the individual components of the given compressedStringcompressedString. We do not perform any form of precomputation. Whenever an operation needs to be performed, the required results are generated from the scratch. Thus, the operations are performed only on demand.
     *
     * Let's look at the implementation of the required operations:
     *
     * next(): We make use of a global pointer ptrptr to keep a track of which compressed letter in the compressedStringcompressedString needs to be processed next. We also make use of a global variable numnum to keep a track of the number of instances of the current letter which are still pending. Whenever next() operation needs to be performed, firstly, we check if there are more uncompressed letters left in the compressedStringcompressedString. If not, we return a ' '. Otherwise, we check if there are more instances of the current letter still pending. If so, we directly decrement the count of instances indicated by numsnums and return the current letter. But, if there aren't more instances pending for the current letter, we update the ptrptr to point to the next letter in the compressedStringcompressedString. We also update the numnum by obtaining the count for the next letter from the compressedStringcompressedString. This number is obtained by making use of decimal arithmetic.
     *
     * hasNext(): If the pointer ptrptr has reached beyond the last index of the compressedStringcompressedString and numnum becomes, it indicates that no more uncompressed letters exist in the compressed string. Hence, we return a False in this case. Otherwise, a True value is returned indicating that more compressed letters exist in the compressedStringcompressedString.
     *
     *  **Performance Analysis**
     * Since no precomputation is done, constant space is required in this case.
     *
     * The time required to perform next() operation is O(1)O(1).
     *
     * The time required for hasNext() operation is O(1)O(1).
     *
     * Since no precomputations are done, and hasNext() requires only O(1)O(1) time, this solution is advantageous if hasNext() operation is performed most of the times.
     *
     * This approach can be extended to include previous() and hasPrevious() operationsm, but this will require the use of some additional variables.
     */


     public class StringIterator {
         String res;
         int ptr = 0, num = 0;
         char ch = ' ';
         public StringIterator(String s) {
             res = s;
         }
         public char next() {
             if (!hasNext())
                 return ' ';
             if (num == 0) {
                 ch = res.charAt(ptr++);
                 while (ptr < res.length() && Character.isDigit(res.charAt(ptr))) {
                     num = num * 10 + res.charAt(ptr++) - '0';
                 }
             }
             num--;
             return ch;
         }
         public boolean hasNext() {
             return ptr != res.length() || num != 0;
         }
     }

}
