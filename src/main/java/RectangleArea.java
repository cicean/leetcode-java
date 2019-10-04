

/*
 * 223	Rectangle Area	25.5%	Easy
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * Rectangle Area
 * Assume that the total area is never beyond the maximum possible value of int.
 *
 * Analysis
 * This problem can be converted as a overlap internal problem. 
 * On the x-axis, there are (A,C) and (E,G); 
 * on the y-axis, there are (F,H) and (B,D).
 * If they do not have overlap, the total area is the sum of 2 rectangle areas. 
 * If they have overlap, the total area should minus the overlap area.
 * 
 * 计算二维平面上两个直线矩形的覆盖面积。
 * 矩形通过其左下角和右上角的坐标进行定义。
 * 假设总面积不会超过int的最大值。
 * 
 * 解题思路：
 * 1.1简单计算几何。根据容斥原理：S(M ∪ N) = S(M) + S(N) - S(M ∩ N)
 * 题目可以转化为计算矩形相交部分的面积
 * S(M) = (C - A) * (D - B)
 * S(N) = (G - E) * (H - F)
 * S(M ∩ N) = max(min(C, G) - max(A, E), 0) * max(min(D, H) - max(B, F), 0)
 * 
 * 2.1 求出两个区域的面积, 然后减去overlapping的区域, 即为所求. 
 * 
 */
public class RectangleArea {

	public int computeArea_1(int A, int B, int C, int D, int E, int F, int G, int H) {
	    if(C<E||G<A )
	        return (G-E)*(H-F) + (C-A)*(D-B);
	 
	    if(D<F || H<B)
	        return (G-E)*(H-F) + (C-A)*(D-B);
	 
	    int right = Math.min(C,G);
	    int left = Math.max(A,E);
	    int top = Math.min(H,D);
	    int bottom = Math.max(F,B);
	 
	    return (G-E)*(H-F) + (C-A)*(D-B) - (right-left)*(top-bottom);
	}
	
	public int computeArea_2(int A, int B, int C, int D, int E, int F, int G, int H) {  
        int area1 = (C-A) * (D-B);  
        int area2 = (G-E) * (H-F);  
          
        int overlapRegion = overlap(A, B, C, D, E, F, G, H);  
        return area1 + area2 - overlapRegion;  
    }  
      
    private int overlap(int A, int B, int C, int D, int E, int F, int G, int H) {  
        int h1 = Math.max(A, E);  
        int h2 = Math.min(C, G);  
        int h = h2 - h1;  
          
        int v1 = Math.max(B, F);  
        int v2 = Math.min(D, H);  
        int v = v2 - v1;  
          
        if(h<=0 || v<=0) return 0;  
        else return h*v;  
    }  
 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int A = -3;
       int B = 0;
       int C = 3;
       int D = 4;
       int E = 0;
       int F = -1;
       int G = 9;
       int H =2;
       RectangleArea slt = new RectangleArea();
       
       System.out.println(slt.computeArea_1(A, B, C, D, E, F, G, H));
       
       
	}

}
