import java.util.*;

public class test {

   class Node {
        String name;
        int vote;
        List<Node> sub_org;

       public Node () {
           this.sub_org = new ArrayList<>();
       }

        public Node (String name, int vote) {
            this.name = name;
            this.vote = vote;
            this.sub_org = new ArrayList<>();
        }
    }

    public Node buildSearchTree(String[][] votes, String[][] subor) {
       Node root = new Node();
       buildhelp(votes, subor, root, 0, 0);
       return root;
    }

    private int getVote(String[][] votes, String[][] subor, String groupname) {
        for (int i = 0; i < votes.length; i++) {
            for (int j = 0; i< votes[0].length; j++) {
                if (votes[i][j] == groupname) {
                    return i;
                }
            }
        }
        return -1;
    }

    Map<String, Node> checkleader = new HashMap<>();
    public void buildhelp(String[][] votes, String[][] subor, Node node, int x, int y) {
         int vote =  getVote(votes, subor, subor[x][y]);
         if (vote != -1) {
             if (y == 0) {
                 if (checkleader.containsKey(subor[x][y])) {
                     node = checkleader.get(subor[x][y]);
                 } else {
                     node.name = subor[x][y];
                     node.vote = vote;
                 }
                 checkleader.put(subor[x][y], node);
                 buildhelp(votes,subor, node, x ,y + 1);
             }

             if (y > 0 && y < subor[x].length) {
                 Node subnode = new Node(subor[x][y], vote);
                 node.sub_org.add(subnode);
                 checkleader.put(subor[x][y], subnode);
                 buildhelp(votes,subor, node, x ,y + 1);
             }

             if (y >= subor[x].length && x < subor.length) {
                 buildhelp(votes,subor, node, x+1 ,0);
             }
         }
    }


}
