package TowSig;

/**
 * Created by cicean on 9/5/2016.
 * There are  students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature, i.e., if  is friend of  and  is friend of , then  is also friend of . A friend circle is a group of students who are directly or indirectly friends.
 You are given a   which consists of characters Y or N. If , then  and students are friends with each other, otherwise not. You have to print the total number of friend circles in the class.
 Input Format
 First line of the input contains an integer  - (size of the matrix), followed by N lines each having N characters.
 Output Format
 Print the maximum number of friend circles.
 Constraints

 Each element of matrix friends will be Y or N.
 Number of rows and columns will be equal in the matrix.
 , where
 = , where
 Sample Input#00
 4
 YYNN
 YYYN
 NYYN
 NNNY
 Sample Output
 2
 Explanation: There are two pairs of friends  and . So  is also a pair of friends by transitivity. So first friend circle contains , and second friend circle contains only student .
 Sample Input#01
 5
 YNNNN
 NYNNN
 NNYNN
 NNNYN
 NNNNY
 Sample Output#01
 5
 Explanation: No students are friends with each other. So each friend circle will contain of only one student .

 */
public class Friendcircles {

    static int friendCircles(String[] friends) {
        if (friends == null || friends.length - 1 == 0)  return 0;

        // number of circle
        int count = 0;

        boolean[] visited = new boolean[friends.length];
        for (int i = 0; i < friends.length; i++) {
            if (!visited[i]) {

                //if current person has not been visited before
                //then this is a new friends circle, mark current
                count++;
                visited[i] = true;

                //dfs to mark all friends in same circle
                dfs(friends, visited, i);
            }
        }

        return count;
    }

    //dfs mark the same circle
    static void dfs(String[] friends, boolean[] visited, int pos) {
        for (int i = 0; i < friends.length; i++) {
            if (!visited[i] && pos != i && friends[pos].charAt(i) == 'Y') {
                visited[i] = true;
                dfs(friends, visited, i);
            }
        }
    }

}
