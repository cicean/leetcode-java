package Google;

/**
 * Created by cicean on 9/24/2016.
 * input:
 a. ��n�����У� ÿ������֮���з���ʱ�䣬
 b. ��������ʱ�䣬
 c. ����vacation array, ����ÿ������ÿ�ܵļ��ڡ�
 d. �ӵ�һ�����п�ʼ
 ��˼����ÿ��������Դ���һ�����У� Ȼ�������Ǹ����еļ��ڡ�
 ���и����ƣ� ���ǳ��������֮��ķ���ʱ�䲻�ܳ��������ķ���ʱ��
 output:
 ��x weeks �������ܵ����������ܺ�
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
