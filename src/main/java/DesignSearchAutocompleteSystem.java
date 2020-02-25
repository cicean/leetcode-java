import java.util.*;

/**
 * 642. Design Search Autocomplete System
 * DescriptionHintsSubmissionsDiscussSolution
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Your job is to implement the following functions:
 *
 * The constructor function:
 *
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.
 *
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 *
 * List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 *
 *
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 *
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 *
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 *
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 *
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 *
 * Note:
 * The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
 * The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
 * Please use double-quote instead of single-quote when you write test cases even for a character input.
 * Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */

public class DesignSearchAutocompleteSystem {

    private Trie root;

    class Node {

        private String sentence;
        private int times;

        public Node (String st, int t) {
            this.sentence = st;
            this.times = t;
        }
    }

    class Trie {
        int times;
        Trie[] branches = new Trie[27];
    }

    private int int_(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    private void insert(Trie t, String s, int times) {
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[this.int_(s.charAt(i))] == null) {
                t.branches[this.int_(s.charAt(i))] = new Trie();
            }
            t = t.branches[this.int_(s.charAt(i))];
        }
        t.times += times;
    }

    private List<Node> lookup(Trie t, String s) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[this.int_(s.charAt(i))] == null) {
                t.branches[this.int_(s.charAt(i))] = new Trie();
            }
            t = t.branches[this.int_(s.charAt(i))];
        }
        traverse(s,t,list);
        return list;
    }

    private void traverse(String s, Trie t, List<Node> list) {
        if (t.times > 0) {
            list.add((new Node(s, t.times)));
        }

        for (char i = 'a'; i <= 'z'; i++) {
            if (t.branches[i - 'a'] != null) {
                traverse(s + i, t.branches[i - 'a'], list);
            }

            if (t.branches[26] != null) {
                traverse(s + ' ', t.branches[26], list);
            }
        }
    }


    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }

    }

    String cur_sent = "";
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            insert(root, cur_sent, 1);
            cur_sent = "";
        } else {
            cur_sent += c;
            List<Node> list = lookup(root, cur_sent);
            Collections.sort(list,
                (a,b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).sentence);
            }
        }

        return res;
    }


    /**
     * Approach 3: Using Trie
     * A Trie is a special data structure used to store strings that can be visualized like a tree. It consists of nodes and edges. Each node consists of at max 26 children and edges connect each parent node to its children. These 26 pointers are nothing but pointers for each of the 26 letters of the English alphabet A separate edge is maintained for every edge.
     *
     * Strings are stored in a top to bottom manner on the basis of their prefix in a trie. All prefixes of length 1 are stored at until level 1, all prefixes of length 2 are sorted at until level 2 and so on.
     *
     * A Trie data structure is very commonly used for representing the words stored in a dictionary. Each level represents one character of the word being formed. A word available in the dictionary can be read off from the Trie by starting from the root and going till the leaf.
     *
     * By doing a small modification to this structure, we can also include an entry, timestimes, for the number of times the current word has been previously typed. This entry can be stored in the leaf node corresponding to the particular word.
     *
     * Now, for implementing the AutoComplete function, we need to consider each character of the every word given in sentencessentences array, and add an entry corresponding to each such character at one level of the trie. At the leaf node of every word, we can update the timestimes section of the node with the corresponding number of times this word has been typed.
     *
     * The following figure shows a trie structure for the words "A","to", "tea", "ted", "ten", "i", "in", and "inn", occuring 15, 7, 3, 4, 12, 11, 5 and 9 times respectively.
     *
     * Trie
     *
     * Similarly, to implement the input(c) function, for every input character cc, we need to add this character to the word being formed currently, i.e. to \text{cur\_sent}cur_sent. Then, we need to traverse in the current trie till all the characters in the current word, \text{cur\_sent}cur_sent, have been exhausted.
     *
     * From this point onwards, we traverse all the branches possible in the Trie, put the sentences/words formed by these branches to a listlist along with their corresponding number of occurences, and find the best 3 out of them similar to the last approach. The following animation shows a typical illustration.
     * Performance Analysis
     *
     * AutocompleteSystem() takes O(k*l)O(k∗l) time. We need to iterate over ll sentences each of average length kk, to create the trie for the given set of sentencessentences.
     *
     * input() takes O\big(p+q+m \log m\big)O(p+q+mlogm) time. Here, pp refers to the length of the sentence formed till now, \text{cur\_sent}cur_sent. qq refers to the number of nodes in the trie considering the sentence formed till now as the root node. Again, we need to sort the listlist of length mm indicating the options available for the hot sentences, which takes O\big(m \log m\big)O(mlogm) time.
     */
    class AutocompleteSystem {
        private Trie root;
        private String cur_sent = "";

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new Trie();
            for (int i = 0; i < sentences.length; i++) {
                insert(root, sentences[i], times[i]);
            }
        }

        private int toInt(char c) {
            return c == ' ' ? 26 : c - 'a';
        }

        private void insert(Trie t, String s, int times) {
            for (int i = 0; i < s.length(); i++) {
                if (t.branches[toInt(s.charAt(i))] == null) {
                    t.branches[toInt(s.charAt(i))] = new Trie();
                }
                t = t.branches[toInt(s.charAt(i))];
            }
            t.times += times;
        }

        private List<Node> lookup(Trie t, String s) {
            List<Node> list = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                if (t.branches[toInt(s.charAt(i))] == null) {
                    return new ArrayList<>();
                }
                t = t.branches[toInt(s.charAt(i))];
            }
            traverse(s, t, list);
            return list;
        }

        private void traverse(String s, Trie t, List<Node> list) {
            if (t.times > 0) list.add(new Node(s, t.times));
            for (char i = 'a'; i <= 'z'; i++) {
                if (t.branches[i - 'a'] != null) {
                    traverse(s + i, t.branches[i - 'a'], list);
                }
            }
            if (t.branches[26] != null) {
                traverse(s + ' ', t.branches[26], list);
            }
        }

        public List<String> input(char c) {
            List<String> res = new ArrayList<>();
            if (c == '#') {
                insert(root, cur_sent, 1);
                cur_sent = "";
            } else {
                cur_sent += c;
                List<Node> list = lookup(root, cur_sent);
                Collections.sort(
                        list,
                        (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
                for (int i = 0; i < Math.min(3, list.size()); i++) res.add(list.get(i).sentence);
            }
            return res;
        }
    }

    /**
     * Approach 1: Brute Force
     * In this solution, we make use of a HashMap mapmap which stores entries in the form (sentence_i, times_i)(sentence
     * i
     * ​
     *  ,times
     * i
     * ​
     *  ). Here, times_itimes
     * i
     * ​
     *   refers to the number of times the sentence_isentence
     * i
     * ​
     *   has been typed earlier.
     *
     * AutocompleteSystem: We pick up each sentence from sentencessentences and their corresponding times from the timestimes, and make their entries in the mapmap appropriately.
     *
     * input(c): We make use of a current sentence tracker variable, \text{cur\_sent}cur_sent, which is used to store the sentence entered till now as the input. For cc as the current input, firstly, we append this cc to \text{cur\_sent}cur_sent and then iterate over all the keys of mapmap to check if a key exists whose initial characters match with \text{cur\_sent}cur_sent. We add all such keys to a listlist. Then, we sort this listlist as per our requirements, and obtain the first three values from this listlist.
     * Performance Analysis
     *
     * AutocompleteSystem() takes O(k*l)O(k∗l) time. This is because, putting an entry in a hashMap takes O(1)O(1) time. But, to create a hash value for a sentence of average length kk, it will be scanned atleast once. We need to put ll such entries in the mapmap.
     *
     * input() takes O\big(n+m \log m\big)O(n+mlogm) time. We need to iterate over the list of sentences, in mapmap, entered till now(say with a count nn), taking O(n)O(n) time, to populate the listlist used for finding the hot sentences. Then, we need to sort the listlist of length mm, taking O\big(m \log m\big)O(mlogm) time.
     */

    class AutocompleteSystem_1 {
        private HashMap<String, Integer> map = new HashMap<>();
        private String cur_sent = "";

        public AutocompleteSystem_1(String[] sentences, int[] times) {
            for (int i = 0; i < sentences.length; i++) map.put(sentences[i], times[i]);
        }

        public List<String> input(char c) {
            List<String> res = new ArrayList<>();
            if (c == '#') {
                map.put(cur_sent, map.getOrDefault(cur_sent, 0) + 1);
                cur_sent = "";
            } else {
                List<Node> list = new ArrayList<>();
                cur_sent += c;
                for (String key : map.keySet())
                    if (key.indexOf(cur_sent) == 0) {
                        list.add(new Node(key, map.get(key)));
                    }
                Collections.sort(
                        list,
                        (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
                for (int i = 0; i < Math.min(3, list.size()); i++) res.add(list.get(i).sentence);
            }
            return res;
        }
    }

    /**
     * Approach 2: Using One level Indexing
     * This method is almost the same as that of the last approach except that instead of making use of simply a HashMap to store the sentences along with their number of occurences, we make use of a Two level HashMap.
     *
     * Thus, we make use of an array arrarr of HashMapsEach element of this array, arrarr, is used to refer to one of the alphabets possible. Each element is a HashMap itself, which stores the sentences and their number of occurences similar to the last approach. e.g. arr[0]arr[0] is used to refer to a HashMap which stores the sentences starting with an 'a'.
     *
     * The process of adding the data in AutocompleteSystem and retrieving the data remains the same as in the last approach, except the one level indexing using arrarr which needs to be done prior to accessing the required HashMap.
     * Performance Analysis
     *
     * AutocompleteSystem() takes O(k*l+26)O(k∗l+26) time. Putting an entry in a hashMap takes O(1)O(1) time. But, to create a hash value for a sentence of average length kk, it will be scanned atleast once. We need to put ll such entries in the mapmap.
     *
     * input() takes O\big(s+m \log m\big)O(s+mlogm) time. We need to iterate only over one hashmap corresponding to the sentences starting with the first character of the current sentence, to populate the listlist for finding the hot sentences. Here, ss refers to the size of this corresponding hashmap. Then, we need to sort the listlist of length mm, taking O\big(m \log m\big)O(mlogm) time.
     */


    class AutocompleteSystem_2 {
        private HashMap<String, Integer>[] arr;
        private String cur_sent = "";

        public AutocompleteSystem_2(String[] sentences, int[] times) {
            arr = new HashMap[26];
            for (int i = 0; i < 26; i++) arr[i] = new HashMap<String, Integer>();
            for (int i = 0; i < sentences.length; i++)
                arr[sentences[i].charAt(0) - 'a'].put(sentences[i], times[i]);
        }

        public List<String> input(char c) {
            List<String> res = new ArrayList<>();
            if (c == '#') {
                arr[cur_sent.charAt(0) - 'a'].put(
                        cur_sent, arr[cur_sent.charAt(0) - 'a'].getOrDefault(cur_sent, 0) + 1);
                cur_sent = "";
            } else {
                List<Node> list = new ArrayList<>();
                cur_sent += c;
                for (String key : arr[cur_sent.charAt(0) - 'a'].keySet()) {
                    if (key.indexOf(cur_sent) == 0) {
                        list.add(new Node(key, arr[cur_sent.charAt(0) - 'a'].get(key)));
                    }
                }
                Collections.sort(
                        list,
                        (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
                for (int i = 0; i < Math.min(3, list.size()); i++) res.add(list.get(i).sentence);
            }
            return res;
        }
    }

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

    class AutocompleteSystem_4 {

        class TrieNode{
            ArrayList<String> topWords= new ArrayList<>();
            TrieNode[] children = new TrieNode[27];
            TrieNode parent = null;

            public TrieNode(TrieNode parent){
                this.parent = parent;
            }

        }

        HashMap<String, Integer> countMap;
        TrieNode root;
        StringBuilder sb = new StringBuilder();
        TrieNode node;

        public AutocompleteSystem_4(String[] sentences, int[] times) {
            countMap = new HashMap<>();
            for(int i = 0; i < sentences.length; i++){
                countMap.put(sentences[i], times[i]);
            }
            root = new TrieNode(null);
            node = root;
            buildTree();
        }

        public List<String> input(char c) {
            if(c != '#'){
                int index = c == ' ' ? 26 : c - 'a';
                if(node.children[index] == null){
                    node.children[index] = new TrieNode(node);
                }
                node = node.children[index];
                sb.append(c);
                return node.topWords;
            }
            else{
                String str = sb.toString();
                countMap.put(str, countMap.getOrDefault(str, 0) + 1);
                updateTopWords(node, str);
                node = root;
                sb = new StringBuilder();
                return new ArrayList<>();
            }
        }

        public void buildTree(){
            for(String str : countMap.keySet()){
                TrieNode node = root;
                for(int i = 0; i < str.length(); i++){
                    char c = str.charAt(i);
                    int index = c == ' ' ? 26 : c - 'a';
                    if(node.children[index] == null){
                        node.children[index] = new TrieNode(node);
                    }
                    node = node.children[index];
                }
                updateTopWords(node, str);
            }
        }

        public void updateTopWords(TrieNode node, String str){
            while(node != root){
                if(!node.topWords.contains(str)){
                    node.topWords.add(str);
                }
                Collections.sort(node.topWords, (s1, s2) -> {
                    int count1 = countMap.get(s1);
                    int count2 = countMap.get(s2);
                    if(count1 != count2) return count2 - count1;
                    else{
                        return s1.compareTo(s2);
                    }
                });
                if(node.topWords.size() > 3){
                    node.topWords.remove(3);
                }

                node = node.parent;
            }
        }
    }





}
