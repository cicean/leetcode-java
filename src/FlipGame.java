import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 293	Flip Game 	48.5%	Easy
 * Problem Description:

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
 
 * @author cicean
 *
 */
public class FlipGame {
	
	public List<String> generatePossibleNextMoves(String s) {
		
	    List<String> list = new ArrayList<String>();
	    for (int i=-1; (i = s.indexOf("++", i+1)) >= 0; ) {
	        list.add(s.substring(0, i) + "--" + s.substring(i+2));
	    }
	    return list;
	}
	
	

}
