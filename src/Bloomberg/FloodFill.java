package Bloomberg;

/**
 * Created by cicean on 10/9/2016.
 * http://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/
 */
public class FloodFill {

    public static void main(String args[]){

        int[][] matrix = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };

        int rows = matrix.length;
        int columns = matrix[0].length;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the row and columns");
        int r =sc.nextInt();
        int c = sc.nextInt();
        int current = matrix[r][c];
        System.out.println("This is the current value" + current);
        System.out.println("Enter the value to be filled");
        int val = sc.nextInt();

        floodFill(r,c,current,matrix,val);

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    static void floodFill(int row,int col,int current,int[][] matrix,int val){
        if(row>=0 && row<matrix.length && col>=0 && col<matrix[0].length){
            if(matrix[row][col]==current){
                matrix[row][col]=val;
                floodFill(row+1, col,current,matrix,val);
                floodFill(row,col+1,current,matrix,val);
                floodFill(row-1,col,current,matrix,val);
                floodFill(row,col-1,current,matrix,val);
            }
            return;
        }

    }

}
