import java.util.*;

/**
 * 1452. People Whose List of Favorite Companies Is Not a Subset of Another List
 * Medium
 *
 * 84
 *
 * 109
 *
 * Add to List
 *
 * Share
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the ith person (indexed from 0).
 *
 * Return the indices of people whose list of favorite companies is not a subset of any other list of favorites companies. You must return the indices in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
 * Output: [0,1,4]
 * Explanation:
 * Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] corresponding to the person with index 0.
 * Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and favoriteCompanies[1]=["google","microsoft"].
 * Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
 * Example 2:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * Output: [0,1]
 * Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"], therefore, the answer is [0,1].
 * Example 3:
 *
 * Input: favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
 * Output: [0,1,2,3]
 *
 *
 * Constraints:
 *
 * 1 <= favoriteCompanies.length <= 100
 * 1 <= favoriteCompanies[i].length <= 500
 * 1 <= favoriteCompanies[i][j].length <= 20
 * All strings in favoriteCompanies[i] are distinct.
 * All lists of favorite companies are distinct, that is, If we sort alphabetically each list then
 * favoriteCompanies[i] != favoriteCompanies[j].
 * All strings consist of lowercase English letters only.
 * Accepted
 * 9,810
 * Submissions
 * 18,792
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
 * LeetCode
 * Use hashing to convert company names in numbers and then for each list check if this is a subset of any other list.
 * In order to check if a list is a subset of another list, use two pointers technique
 * to get a linear solution for this task. The total complexity will be O(n^2 * m)
 * where n is the number of lists and m is the maximum number of elements in a list.
 */
public class PeopleWhoseListofFavoriteCompaniesIsNotaSubsetofAnotherList {

    /**
     *
     */

    class Solution {
        public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {

            Map<String, BitSet> companyFavoriteByPeople = getCompanyFavoriteByPeople(favoriteCompanies);

            List<Integer> peopleWithUniqueCompanyList = new ArrayList<>();
            for(int i = 0; i < favoriteCompanies.size(); i++){
                //get set of people which list contains the first company in the list
                BitSet intersected = (BitSet) companyFavoriteByPeople.get(favoriteCompanies.get(i).get(0)).clone();
                for(int j = 1; j < favoriteCompanies.get(i).size(); j++){
                    BitSet peopleIds = companyFavoriteByPeople.get(favoriteCompanies.get(i).get(j));
                    intersected.and(peopleIds);
                }

                if(intersected.cardinality() <= 1) peopleWithUniqueCompanyList.add(i);
            }

            return peopleWithUniqueCompanyList;
        }

        private Map<String, BitSet> getCompanyFavoriteByPeople(List<List<String>> favoriteCompanies){
            Map<String, BitSet> companyFavoriteByPeople = new HashMap<>();
            for(int i = 0; i < favoriteCompanies.size(); i++){
                for(String company: favoriteCompanies.get(i)){
                    BitSet peopleIds = companyFavoriteByPeople.getOrDefault(company, new BitSet(favoriteCompanies.size()));
                    peopleIds.set(i);
                    companyFavoriteByPeople.put(company, peopleIds);
                }
            }
            return companyFavoriteByPeople;
        }
    }

    /**
     *
     */

    class Solution2 {
        public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
            List<Integer> res = new ArrayList();

            int n = favoriteCompanies.size();
            Set<String>[] sets = new HashSet[n];

            for(int i = 0;i < n;i++){
                sets[i] = new HashSet(favoriteCompanies.get(i));
            }

            outer:
            for(int i = 0;i < n;i++){
                for(int j = 0;j < n;j++){
                    if(i != j && sets[j].size() > sets[i].size() && sets[j].containsAll(sets[i])){
                        continue outer;
                    }
                }
                res.add(i);
            }

            return res;
        }
    }
}
