import java.util.*;

/**
 * 721. Accounts Merge
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Note:
 *
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 */

public class AccountsMerge {

    // dfs

    /**
     * Approach #1: Depth-First Search [Accepted]
     * Intuition
     *
     * Draw an edge between two emails if they occur in the same account. The problem comes down to finding connected components of this graph.
     *
     * Algorithm
     *
     * For each account, draw the edge from the first email to all other emails. Additionally, we'll remember a map from emails to names on the side. After finding each connected component using a depth-first search, we'll add that to our answer.
     * @param accounts
     * @return
     * Complexity Analysis
     *
     * Time Complexity: O(\sum a_i \log a_i)O(∑a
     * i
     * ​
     *  loga
     * i
     * ​
     *  ), where a_ia
     * i
     * ​
     *   is the length of accounts[i]. Without the log factor, this is the complexity to build the graph and search for each component. The log factor is for sorting each component at the end.
     *
     * Space Complexity: O(\sum a_i)O(∑a
     * i
     * ​
     *  ), the space used by our graph and our search.
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToUser = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();

        // dfs search connect node build graph
        for (List<String> account : accounts) {
            String username = "";
            for (String email : account) {
                //each root of the graph
                if (username == "") {
                    username = email;
                    continue;
                }
                //double side connect gragh
                graph.computeIfAbsent(email, x -> new ArrayList<>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(email);
                emailToUser.put(email, username);
            }
        }

        Set<String> seen = new HashSet<>();
        List<List<String>> ans = new ArrayList<>();
        for (String email : graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack<>();
                stack.push(email);
                List<String> commponent = new Stack<>();
                while (!stack.empty()) {
                    String node = stack.pop();
                    commponent.add(node);
                    for (String nei : graph.get(email)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(commponent);
                commponent.add(0, emailToUser.get(email));
                ans.add(commponent);
            }
        }

        return ans;
    }

    class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {

            int n = accounts.size();
            int[] f = new int[n];
            for (int i = 0; i < n; i++) f[i] = i;
            Map<String, Integer> a = new HashMap<>();
            Map<Integer, List<Integer>> b = new HashMap<>();
            List<List<String>> result = new ArrayList<>();

            for (int i = 0; i < n; i++) {

                List<String> account = accounts.get(i);

                for (int j = 1; j < account.size(); j++) {

                    String s = account.get(j);

                    if (!a.containsKey(s)) a.put(s, i);
                    else {
                        int x = i, y = a.get(s);
                        while (f[x] != x) x = f[x];
                        while (f[y] != y) y = f[y];
                        f[x] = y;
                    }
                }
            }

            for (int i = 0; i < n; i++) {

                int x = i;
                while (f[x] != x) x = f[x];
                b.putIfAbsent(x, new ArrayList<>());
                b.get(x).add(i);
            }

            Iterator<List<Integer>> it = b.values().iterator();

            while (it.hasNext()) {

                List<Integer> s = it.next();
                Set<String> c = new TreeSet<>();
                List<String> d = new ArrayList<>();
                d.add(accounts.get(s.get(0)).get(0));

                for (int x : s) {
                    List<String> e = accounts.get(x);
                    for (int i = 1; i < e.size(); i++) c.add(e.get(i));
                }

                for (String e : c) d.add(e);
                result.add(d);
            }

            return result;
        }
    }

    class Solution_Union_Find {
        public List<List<String>> accountsMerge(List<List<String>> acts) {
            Map<String, String> owner = new HashMap<>();
            Map<String, String> parents = new HashMap<>();
            Map<String, TreeSet<String>> unions = new HashMap<>();
            for (List<String> a : acts) {
                for (int i = 1; i < a.size(); i++) {
                    parents.put(a.get(i), a.get(i));
                    owner.put(a.get(i), a.get(0));
                }
            }
            for (List<String> a : acts) {
                String p = find(a.get(1), parents);
                for (int i = 2; i < a.size(); i++)
                    parents.put(find(a.get(i), parents), p);
            }
            for(List<String> a : acts) {
                String p = find(a.get(1), parents);
                if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
                for (int i = 1; i < a.size(); i++)
                    unions.get(p).add(a.get(i));
            }
            List<List<String>> res = new ArrayList<>();
            for (String p : unions.keySet()) {
                List<String> emails = new ArrayList(unions.get(p));
                emails.add(0, owner.get(p));
                res.add(emails);
            }
            return res;
        }
        private String find(String s, Map<String, String> p) {
            return p.get(s) == s ? s : find(p.get(s), p);
        }
    }

}
