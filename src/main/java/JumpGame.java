/*
 55	Jump Game	27.1%	Medium
 Problem:    Jump Game
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/jump-game/
 Notes:
 Given an array of non-negative integers, you are initially positioned at the first index of the array.
 Each element in the array represents your maximum jump length at that position.
 Determine if you are able to reach the last index.
 For example:
 A = [2,3,1,1,4], return true.
 A = [3,2,1,0,4], return false.
 Solution: Updated solution: try every reachable index. 
           Thank to Wenxin Xing for kindly feedback and pointing out my big mistake:)
 */

public class JumpGame {
	
	public boolean canJump(int[] A) {
        int pos = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            if (pos >= i) {
                pos = Math.max(pos, i + A[i]);
            }
        }
        return pos >= n - 1;
    }
	 public static void main(String[] args) {
		 JumpGame slt = new JumpGame();
		 int[] A ={2,3,1,1,4};
			boolean res = slt.canJump(A);
			for(int i=0;i<A.length;i++){
				if(i==A.length-1){
					System.out.print(A[i]);
				}else{
				System.out.print(A[i]+",");
				}
			}
			System.out.println(res);
			int[] B ={3,2,1,0,4};
			boolean res2 = slt.canJump(B);
			System.out.println(res2);
		}

}
