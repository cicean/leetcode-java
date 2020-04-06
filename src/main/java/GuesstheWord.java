/**
 * 843. Guess the Word
 * Hard
 *
 * 440
 *
 * 463
 *
 * Add to List
 *
 * Share
 * This problem is an interactive problem new to the LeetCode platform.
 *
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 *
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 *
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
 *
 * Example 1:
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
 *
 * Explanation:
 *
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 *
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 * Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * Accepted
 * 37,487
 * Submissions
 * 82,629
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * 1337c0d3r
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Google
 * |
 * 22
 */
public class GuesstheWord {

    /**
     * Approach #1: Minimax with Heuristic [Accepted]
     * Intuition
     *
     * We can guess that having less words in the word list is generally better. If the data is random, we can reason this is often the case.
     *
     * Now let's use the strategy of making the guess that minimizes the maximum possible size of the resulting word list. If we started with NN words in our word list, we can iterate through all possibilities for what the secret could be.
     *
     * Algorithm
     *
     * Store H[i][j] as the number of matches of wordlist[i] and wordlist[j]. For each guess that hasn't been guessed before, do a minimax as described above, taking the guess that gives us the smallest group that might occur.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N^2 \log N)O(N
     * 2
     *  logN), where NN is the number of words, and assuming their length is O(1)O(1). Each call to solve is O(N^2)O(N
     * 2
     *  ), and the number of calls is bounded by O(\log N)O(logN).
     *
     * Space Complexity: O(N^2)O(N
     * 2
     *  ).
     *
     */

    /**
     * // This is the Master's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface Master {
     *     public int guess(String word) {}
     * }
     */

    class Solution {
        int[][] H;
        public void findSecretWord(String[] wordlist, Master master) {
            int N = wordlist.length;
            H = new int[N][N];
            for (int i = 0; i < N; ++i)
                for (int j = i; j < N; ++j) {
                    int match = 0;
                    for (int k = 0; k < 6; ++k)
                        if (wordlist[i].charAt(k) == wordlist[j].charAt(k))
                            match++;
                    H[i][j] = H[j][i] = match;
                }

            List<Integer> possible = new ArrayList();
            List<Integer> path = new ArrayList();
            for (int i = 0; i < N; ++i) possible.add(i);

            while (!possible.isEmpty()) {
                int guess = solve(possible, path);
                int matches = master.guess(wordlist[guess]);
                if (matches == wordlist[0].length()) return;
                List<Integer> possible2 = new ArrayList();
                for (Integer j: possible) if (H[guess][j] == matches) possible2.add(j);
                possible = possible2;
                path.add(guess);
            }

        }

        public int solve(List<Integer> possible, List<Integer> path) {
            if (possible.size() <= 2) return possible.get(0);
            List<Integer> ansgrp = possible;
            int ansguess = -1;

            for (int guess = 0; guess < H.length; ++guess) {
                if (!path.contains(guess)) {
                    ArrayList<Integer>[] groups = new ArrayList[7];
                    for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<Integer>();
                    for (Integer j: possible) if (j != guess) {
                        groups[H[guess][j]].add(j);
                    }

                    ArrayList<Integer> maxgroup = groups[0];
                    for (int i = 0; i < 7; ++i)
                        if (groups[i].size() > maxgroup.size())
                            maxgroup = groups[i];

                    if (maxgroup.size() < ansgrp.size()) {
                        ansgrp = maxgroup;
                        ansguess = guess;
                    }
                }
            }

            return ansguess;
        }
    }

    class Solution_2 {
        public void findSecretWord(String[] wordlist, Master master) {
            ArrayList<String> l = new ArrayList<>(Arrays.asList(wordlist));
            for(int i=0;i<10;i++){
                String word = l.get(0);
                int match = master.guess(word);
                if(match == word.length()) return;
                Iterator<String> iter = l.listIterator();
                while(iter.hasNext()){
                    String curr = iter.next();
                    if(getCount(word,curr)!=match) iter.remove();
                }
                Collections.sort(l);
            }
            return;
        }
        public int getCount(String a, String b) {
            int count=0;
            for(int i=0;i<a.length();i++){
                if(a.charAt(i)==b.charAt(i)) count++;
            }
            return count;
        }
    }
}
