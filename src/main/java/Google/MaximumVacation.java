package Google;

/**
 * Created by cicean on 9/24/2016.
 * input:
 a. 有n个城市， 每个城市之间有飞行时间，
 b. 给个飞行时间，
 c. 给个vacation array, 代表每个城市每周的假期。
 d. 从第一个城市开始
 意思就是每个周你可以呆在一个城市， 然后享受那个城市的假期。
 还有个限制， 就是城市与城市之间的飞行时间不能超过给定的飞行时间
 output:
 求x weeks 你能享受到的最大假期总和
 */
public class MaximumVacation {

    public int maximumVaction(int[][] cityvacation, int[][] filght_times, int filghtlimit,  int weeks) {
    	
    	
    	int[][] dp = new int[cityvacation.length][cityvacation[0].length];
    	int max_vacation = Integer.MIN_VALUE;
    	for (int i = 0; i < dp.length; i++) {
    		if (filght_times[0][i] <= filghtlimit) {
    			dp[i][0] = cityvacation[i][0];
                max_vacation = Math.max(max_vacation,dp[i][0]);
    		} else {
    			dp[i][0] = Integer.MIN_VALUE;
    		}

    	}
    	
    	for (int j = 1; j <= weeks;j++) {
            int tempmax = Integer.MIN_VALUE;
            for (int i = 0; i < cityvacation.length; i++) {
            	int citymax = Integer.MIN_VALUE;
            	for (int f = 0; f < cityvacation.length; f++) {
            		if (filght_times[i][f] <= filghtlimit) {
            			citymax = Math.max(citymax,cityvacation[i][j] + dp[f][j - 1]);
                    }
            	}
            	dp[i][j] = citymax;
                tempmax = Math.max(max_vacation,dp[i][j]);
            }
            max_vacation = tempmax;
        }
    	
        return max_vacation;
    	
    }
    
    public static void main(String[] args) {
		int[][] cityvacation = {{4, 2, 6, 2},
				{1, 2, 3, 4},
				{4, 6, 7, 2}};
		int[][] cityflighttimes = {{0, 2, 4},
				{2, 0, 4},
				{4, 4, 0}};
		
		MaximumVacation slt = new MaximumVacation();
		System.out.println(slt.maximumVaction(cityvacation, cityflighttimes, 4, 3));
		
	}
}
