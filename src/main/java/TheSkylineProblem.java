import java.util.*;

/**
 * 218. The Skyline Problem
 * Hard
 *
 * 1821
 *
 * 101
 *
 * Add to List
 *
 * Share
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 *
 * Buildings Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 * Accepted
 * 123,061
 * Submissions
 * 364,631
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 * Contributor
 * stellari
 * 0 ~ 6 months6 months ~ 1 year1 year ~ 2 years
 *
 * Amazon
 * |
 * 6
 *
 * Microsoft
 * |
 * 5
 *
 * Google
 * |
 * 4
 *
 * Apple
 * |
 * 2
 * Falling Squares
 * Hard
 */

/*
 * 
 * A city's skyline is the outer contour of the silhouette formed by 
 * all the buildings in that city when viewed from a distance. 
 * Now suppose you aregiven the locations and height of all the buildings as shown 
 * on a cityscape photo (Figure A), write a program tooutput the skyline formed 
 * by these buildings collectively (Figure B).
 * 
 * BuildingsSkyline Contour
 * The geometric information of each building is represented by a triplet of integers[Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, andHi is its height. It is guaranteed that 0 �� Li, Ri �� INT_MAX,0 < Hi �� INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
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
 * 2.1 ��ÿһ��building�������edge��һ����һ���������е�edge���뵽һ��list�С�
 * �ٶ����list������������˳��Ϊ����������ߵ�position��һ������ô��pos�ţ��������edge���뻹�ǳ����š�
 * ����position��ǰ����ɨ��ÿһ��edge����edge�������뻹�ǳ�������ǰheight��������Ƴ�heap��
 * �ٵõ���ǰ��ߵ��������Ƿ�������ս�����ǳ����ֵ��˼����
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

	class Solution {
		static class Building {
			int loc;
			int height;
			int type;

			public Building(int loc, int height, int type) {
				this.loc = loc;
				this.height = height;
				this.type = type;
			}
		}

		public List<List<Integer>> getSkyline(int[][] buildings) {
			List<List<Integer>> res = new ArrayList<>();
			//默认输入已经按照起点排序
			//按照高度降序，同高度根据起点升序
			PriorityQueue<int[]> heightHeap = new PriorityQueue<>(new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					return a[2] == b[2] ? a[0] - b[0] : b[2] - a[2];
				}
			});

			//默认起点，PRE保存前面能看见的最高建筑和他的终点起点
			int[] pre = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
			for (int[] b : buildings) {
				// 当出现断点情况，需要清空之前建筑群
				while (!heightHeap.isEmpty() && b[0] > pre[1]) {
					//获取之前最高建筑
					int[] curHighest = heightHeap.poll();
					//如果最高的终点在PRE之前，说明已经处理
					if (curHighest[1] <= pre[1]) continue;
					//如果遇到PRE之后的点，加入结果并更新PRE
					res.add(Arrays.asList(pre[1], curHighest[2]));
					pre = curHighest;
				}

				//当前建筑比之前建筑高
				if (b[2] > pre[2]) {
					if (b[0] == pre[0]) {
						//同起点情况下，矮建筑必然被挡住，直接删除
						res.remove(res.size() - 1);
					}
					res.add(Arrays.asList(b[0], b[2])); //未被之后遮挡前先加入结果
					if (b[1] < pre[1]) {
						heightHeap.offer(pre); //如果终点小于前终点，将前值入堆
					}
					pre = b;//更新前值因为发现了更高的
				} else if (b[2] == pre[2]) { //同高度继续延伸END
					pre[1] = b[1];
				} else if (b[1] > pre[1]) {
					heightHeap.offer(b); //矮建筑直接入堆
				}
			}


			while (!heightHeap.isEmpty()) {
				//如果堆不为空，重复之前操作
				int[] cur = heightHeap.poll();
				if (cur[1] <= pre[1]) continue;
				res.add(Arrays.asList(pre[1], cur[2]));
				pre = cur;
			}
			//最后有剩余
			if (pre[2] > 0) {
				res.add(Arrays.asList(pre[1], 0));
			}
			return res;
		}
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

