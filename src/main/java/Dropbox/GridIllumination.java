package Dropbox;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by cicean on 10/13/2016.
 * 
 * 
 */


public class GridIllumination {

    public String[] checkIllumination(int N, int[][] lamps, int[][] queries) {

        //use map to store the lamp position
        // if use the int[][] to store the hole position of lamp it because
        // the lamp may small size the matrix became poor
        // so no need to use matrix can only use 4 map (row, col , dia, rdia)to store all lamp position

        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> dia = new HashMap<>();
        Map<Integer, Integer> rdia = new HashMap<>();

        //store lamp position
        Map<Integer, Set<Integer>> lampsSet = new HashMap<>();

        //init the for map of lamp position
        for (int[] lamp : lamps) {
            int x = lamp[0];
            int y = lamp[1];
            Set<Integer> tmp;

            if (lampsSet.containsKey(x)) tmp = lampsSet.get(x);
            else tmp = new HashSet<>();

            tmp.add(y);
            lampsSet.put(x, tmp);

            System.out.println("lamp x = " + x  + ", y = " + y);

            if (row.containsKey(x)) row.put(x, row.get(x) + 1);
            else row.put(x, 1);
            if (col.containsKey(y)) col.put(y, col.get(y) + 1);
            else col.put(y, 1);
            if (dia.containsKey(x - y)) dia.put(x - y, dia.get(x - y) + 1);
            else dia.put(x - y, 1);
            if (rdia.containsKey(x + y)) rdia.put(x + y, rdia.get(x + y) + 1);
            else rdia.put(x + y, 1);
            
        }
        
        String[] res = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];


            if (!row.containsKey(x) && !col.containsKey(y) && !dia.containsKey(x - y) && !rdia.containsKey(x + y)) {
                res[i] = "DARK";
                continue;
            }
            
            int rowtmp = 0;
            int coltmp = 0;
            int diatmp = 0;
            int rdiatmp = 0;
            
            if (row.containsKey(x)) rowtmp = row.get(x);
            if (col.containsKey(y)) coltmp = col.get(y);
            if (dia.containsKey(x - y)) diatmp = dia.get(x - y);
            if (rdia.containsKey(x + y)) rdiatmp = rdia.get(x + y);
            
            System.out.println("init row = " + rowtmp + ", col = " + coltmp + ", dia = " + diatmp + ", rdia = " + rdiatmp);

            //close the lamp nearby the query cell
            
            if (lampsSet.containsKey(x) && lampsSet.get(x).contains(y)) {
            	rdiatmp--;
            	diatmp--;
            	rowtmp--;
            	coltmp--;
            }
            
            if (x - 1 > 0 && y + 1 < N + 1 && lampsSet.containsKey(x - 1) && lampsSet.get(x - 1).contains(y + 1)) rdiatmp--;
            if (x + 1 < N + 1 && y - 1 > 0 && lampsSet.containsKey(x + 1) && lampsSet.get(x + 1).contains(y - 1)) rdiatmp--;

            if (x - 1 > 0 && y - 1 > 0 && lampsSet.containsKey(x - 1) && lampsSet.get(x - 1).contains(y - 1)) diatmp--;
            if (x + 1 < N + 1 && y + 1 < N + 1 && lampsSet.containsKey(x + 1) && lampsSet.get(x + 1).contains(y + 1)) diatmp--;

            if ( y - 1 > 0 && lampsSet.containsKey(x) && lampsSet.get(x).contains(y - 1)) rowtmp--;
            if (y + 1 < N + 1 && lampsSet.containsKey(x) && lampsSet.get(x).contains(y + 1)) rowtmp--;

            if (x - 1 > 0 && lampsSet.containsKey(x - 1) && lampsSet.get(x - 1).contains(y)) coltmp--;
            if (x + 1 < N + 1 && lampsSet.containsKey(x + 1) && lampsSet.get(x + 1).contains(y)) coltmp--;

            System.out.println("row = " + rowtmp + ", col = " + coltmp + ", dia = " + diatmp + ", rdia = " + rdiatmp);

            if (rowtmp == 0 && coltmp == 0 && diatmp == 0 && rdiatmp == 0)  res[i] = "DARK";
            else  res[i] = "LIGHT";

        }

        return res;

    }

    private boolean contains(int[] a ,int[] b) {
        return Arrays.equals(a, b);
    }



    
    public static void main(String[] args) {
		int[][] lamps = {{4, 3}, {4, 4}};
        int[][] queries = {{3, 4}, {7, 6}};
        int  N = 8;
        int[][] lamps2 = {{1, 6}, {5, 6}, {7, 3}, {3, 2}};
        int[][] queries2 = {{4, 4}, {6, 6}, {8, 1}, {3, 2}, {2, 3}};
        
        GridIllumination slt = new GridIllumination();
        String[] reStrings =slt.checkIllumination(N, lamps2, queries2);
        System.out.print(Arrays.deepToString(reStrings));

	}
}
