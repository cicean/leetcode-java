import java.util.*;



/*
 * 
 * A city's skyline is the outer contour of the silhouette formed by 
 * all the buildings in that city when viewed from a distance. 
 * Now suppose you aregiven the locations and height of all the buildings as shown 
 * on a cityscape photo (Figure A), write a program tooutput the skyline formed 
 * by these buildings collectively (Figure B).
 * 
 * BuildingsSkyline Contour
 * The geometric information of each building is represented by a triplet of integers[Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, andHi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX,0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * The output is a list of "key points" (red dots in Figure B) in the format of[ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *  Notes:
 *  The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 *  The input list is already sorted in ascending order by the left x position Li.
 *  The output list must be sorted by the x position.
 *  There must be no consecutive horizontal lines of equal height in the output skyline. For instance,[...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such:[...[2 3], [4 5], [12 7], ...]
 * 
 * This problem is essentially a problem of processing 2*n edges. 
 * Each edge has a x-axis value and a height value. 
 * The key part is how to use the height heap to process each edge.
 * 1.1 
 * 2.1 把每一个building拆成两个edge，一个入一个出。所有的edge加入到一个list中。
 * 再对这个list进行排序，排序顺序为：如果两个边的position不一样，那么按pos排，否则根据edge是入还是出来排。
 * 根据position从前到后扫描每一个edge，将edge根据是入还是出来将当前height加入或者移除heap。
 * 再得到当前最高点来决定是否加入最终结果。非常巧妙，值得思考。
 */

public class TheSkylineProblem {

	public List<int[]> getSkyline_1(int[][] buildings) {  
        List<int[]> res = new ArrayList<int[]>();  
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>(){  
     
            public int compare(Integer a, Integer b) {  
                return b - a;  
            }  
        });  
        List<int[]> bl = new ArrayList<int[]>();  
        for(int i=0; i<buildings.length; i++) {  
            int[] b = buildings[i];      
            bl.add(new int[]{b[0], b[2]});  
            bl.add(new int[]{b[1], -b[2]});  
        }  
          
        Collections.sort(bl, new Comparator<int[]>() {  
           
            public int compare(int[] a, int[] b) {  
                if(a[0]!=b[0]) return a[0] - b[0];  
                else return b[1] - a[1];  
            }  
        });  
        int pre = 0, cur = 0;  
        for(int i=0; i<bl.size(); i++) {  
            int[] b = bl.get(i);  
            if(b[1]>0) {  
                maxHeap.add(b[1]);  
                cur = maxHeap.peek();  
            } else {  
                maxHeap.remove(-b[1]);  
                cur = (maxHeap.peek()==null) ? 0 : maxHeap.peek();  
            }  
            if(cur!=pre) {  
                res.add(new int[]{b[0], cur});  
                pre = cur;  
            }  
        }         
        return res;       
    }  
	
	public List<int[]> getSkyline_2(int[][] buildings) {
		List<int[]> result = new ArrayList<int[]>();
	 
		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return result;
		}
	 
		List<Edge> edges = new ArrayList<Edge>();
	 
		// add all left/right edges
		for (int[] building : buildings) {
			Edge startEdge = new Edge(building[0], building[2], true);
			edges.add(startEdge);
			Edge endEdge = new Edge(building[1], building[2], false);
			edges.add(endEdge);
		}
	 
		// sort edges 
		Collections.sort(edges, new Comparator<Edge>() {
			public int compare(Edge a, Edge b) {
				if (a.x != b.x)
					return Integer.compare(a.x, b.x);
	 
				if (a.isStart && b.isStart) {
					return Integer.compare(b.height, a.height);
				}
	 
				if (!a.isStart && !b.isStart) {
					return Integer.compare(a.height, b.height);
				}
	 
				return a.isStart ? -1 : 1;
			}
		});
	 
		// process edges
		PriorityQueue<Integer> heightHeap = new PriorityQueue<Integer>(10, Collections.reverseOrder());
	 
		for (Edge edge : edges) {
			if (edge.isStart) {
				if (heightHeap.isEmpty() || edge.height > heightHeap.peek()) {
					result.add(new int[] { edge.x, edge.height });
				}
				heightHeap.add(edge.height);
			} else {
				heightHeap.remove(edge.height);
	 
				if(heightHeap.isEmpty()){
					result.add(new int[] {edge.x, 0});
				}else if(edge.height > heightHeap.peek()){
					result.add(new int[]{edge.x, heightHeap.peek()});
				}
			}
		}
	 
		return result;
	}
	
	public void print(List<int[]> result){
		
		for(int i = 0; i<result.size();i++){
			if (i == 0){
			System.out.print("[");
			for(int j=0; j<result.get(i).length; j++){
				if(j == result.get(i).length-1){
					System.out.print(result.get(i)[j]+"]");
				}else{
		System.out.print("["+result.get(i)[j]+",");}
			}
			
			}else if (i == result.size()-1 ){
			
				for(int j=0; j<result.get(i).length; j++){
					if(j == result.get(i).length-1){
						System.out.print(result.get(i)[j]+"]");
					}else{
			System.out.print("["+result.get(i)[j]+",");}
					
				}
				System.out.print("]");
				
			}else{
				for(int j=0; j<result.get(i).length; j++){
					if(j == result.get(i).length-1){
						System.out.print(result.get(i)[j]+"]");
					}else{
			System.out.print("["+result.get(i)[j]+",");}
					
				}
			}
			
			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] buildings ={{2,9,10},
							{3,7,15},
							{5,12,12},
							{15,20,20},
							{19,24,8}};
		TheSkylineProblem slt = new TheSkylineProblem();
		List<int[]> res = slt.getSkyline_1(buildings);
		slt.print(slt.getSkyline_1(buildings));
	}

}

class Edge {
	int x;
	int height;
	boolean isStart;
 
	public Edge(int x, int height, boolean isStart) {
		this.x = x;
		this.height = height;
		this.isStart = isStart;
	}
}

