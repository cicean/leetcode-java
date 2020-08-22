/**
 * 929. Unique Email Addresses
 * Easy
 *
 * 743
 *
 * 177
 *
 * Add to List
 *
 * Share
 * Every email consists of a local name and a domain name, separated by the @ sign.
 *
 * For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.
 *
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 *
 * If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)
 *
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)
 *
 * It is possible to use both of these rules at the same time.
 *
 * Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?
 *
 *
 *
 * Example 1:
 *
 * Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 *
 *
 * Note:
 *
 * 1 <= emails[i].length <= 100
 * 1 <= emails.length <= 100
 * Each emails[i] contains exactly one '@' character.
 * All local and domain names are non-empty.
 * Local names do not start with a '+' character.
 * Accepted
 * 184,975
 * Submissions
 * 273,706
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * LeetCode
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 2
 */
public class UniqueEmailAddresses {

    /**
     * Approach 1: Canonical Form
     * Intuition and Algorithm
     *
     * For each email address, convert it to the canonical address that actually receives the mail. This involves a few steps:
     *
     * Separate the email address into a local part and the rest of the address.
     *
     * If the local part has a '+' character, remove it and everything beyond it from the local part.
     *
     * Remove all the zeros from the local part.
     *
     * The canonical address is local + rest.
     *
     * After, we can count the number of unique canonical addresses with a Set structure.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(\mathcal{C})O(C), where \mathcal{C}C is the total content of emails.
     * Space Complexity: O(\mathcal{C})O(C).
     */

    class Solution {
        public int numUniqueEmails(String[] emails) {
            Set<String> seen = new HashSet();
            for (String email : emails) {
                int i = email.indexOf('@');
                String local = email.substring(0, i);
                String rest = email.substring(i);
                if (local.contains("+")) {
                    local = local.substring(0, local.indexOf('+'));
                }
                // Note: one should escape the specific character '.',
                // since it is treated as a regex expression.
                local = local.replaceAll("\\.", "");
                seen.add(local + rest);
            }

            return seen.size();
        }
    }

    class Solution {
        public int numUniqueEmails(String[] emails) {
            Set<String> normalized = new HashSet<>(); // used to save simplified email address, cost O(n) sapce.
            for (String email : emails) {
                String[] parts = email.split("@"); // split into local and domain parts.
                String[] local = parts[0].split("\\+"); // split local by '+'.
                normalized.add(local[0].replace(".", "") + "@" + parts[1]); // remove all '.', and concatenate '@' and domain.
            }
            return normalized.size();
        }
    }

    class Solution {
        public int numUniqueEmails(String[] emails) {
            Set<String> set = new HashSet<>();
            for(String email: emails){
                set.add(parse(email));
            }
            return set.size();
        }

        private String parse(String email){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<email.length(); i++){
                char x = email.charAt(i);
                if(x=='@'){
                    sb.append(email.substring(i));
                    return sb.toString();
                }else if(x=='+'){ //can scan in reverse direction also to find @
                    while(email.charAt(i+1)!='@'){
                        i++;
                    }
                }else if(x=='.'){
                    continue;
                }else{
                    sb.append(x);
                }
            }
            return null;
        }
    }

}
