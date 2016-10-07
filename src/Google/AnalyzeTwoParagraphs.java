package Google;

import java.text.DecimalFormat;
import java.util.*;


/**
 * Created by cicean on 9/27/2016.
 *
 */
public class AnalyzeTwoParagraphs {

    public void analyzeTwoParagraphs(String p1,String p2) {
        // I forgot that I can use the  regular expression to clean the pargraphs
        // you also can use a hashmap to parse the paragraphs by your custom Punctuation
        // you can see the code after the main code
        // current I use the [\pP¡®¡¯¡°¡±] to remove those Punctuation
        String[] token1 = p1.toLowerCase().replaceAll("[\\pP¡®¡¯¡°¡±]", "").split(" ");
        String[] token2 = p2.toLowerCase().replaceAll("[\\pP¡®¡¯¡°¡±]", "").split(" ");
        

        //HashSet to store all distinct  pattern of pargraphs one || two;
        Set<String> pattern1 = new HashSet<>();
        Set<String> pattern2 = new HashSet<>();

        //

        int countnoneduplicate = 0;

        for (int i = 1; i < token1.length; i++) {
            String tmp = token1[i - 1] + token1[i];
            pattern1.add(tmp);
        }

        for (int i = 1; i < token2.length; i++) {
            String tmp = token2[i - 1] + token2[i];
         // traversal the pargraphs2 and count the  totalofpattern
            if (!pattern1.contains(tmp) && !pattern2.contains(tmp)) countnoneduplicate++;
            pattern2.add(tmp);
            
        }
        
        System.out.println(countnoneduplicate + ", " + pattern1 + ", " + pattern2);

        double duplicate = pattern2.size() - countnoneduplicate;
        double total = pattern1.size() + countnoneduplicate;
        double ratio = duplicate / total * new Double(100);
        
        //use rounding off
        DecimalFormat df = new DecimalFormat("######0");
        System.out.println("the percentage of two pargraphs similarity is " + String.format("%s%%", df.format(ratio)));


    }

    /**
     * anther way to clean the pargraphs
     * field set the punctuation item we will deal with dring the word store to List
     */

    Set<Character> punctuations = new HashSet<Character>() {{
        add(',');
        add('.');
        add('?');
        add('_');
        add(';');
    }};



    public Set<String> analyzeParagraph(String[] tokens, int k) {
        Set<String> uniqueWords = new HashSet<>();
        for (String word : tokens) {
            word = word.toLowerCase();
            char lastChar = word.charAt(word.length() - 1);
            if (punctuations.contains(lastChar)) {
                word = word.substring(0, word.length() - 1);
            }
            uniqueWords.add(word);
        }

        List<String> words = new ArrayList<>(uniqueWords);
        System.out.println("The Paragraph distinct in each paragraph are " + uniqueWords);
        Collections.sort(words);
        if (k - 1 < words.size()) {
            System.out.println("The " + k + "th words is:" + words.get(k - 1));
        }

        return uniqueWords;
    }

    public static void main(String[] args) {
        AnalyzeTwoParagraphs slt = new AnalyzeTwoParagraphs();
        String p1 = "To be or not to be, that's the question.";
        String p2 = "A wise man is not one who knows all the answers. But one who knows how to find the answers. "
                + "A foolish man is one who knows neither.";
        String p3 = "To be or not to be, that's the question. A wise man is not one who knows all the answers.";
        String p4 = "To be or not to be";
        

        //slt.analyzeTwoParagraphs(p1, p2);
       // slt.analyzeTwoParagraphs(p2, p3);
        slt.analyzeTwoParagraphs(p1, p4);

    }
}

