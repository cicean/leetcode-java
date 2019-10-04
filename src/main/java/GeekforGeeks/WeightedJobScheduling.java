package GeekforGeeks;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by cicean on 9/29/2016.
 * Given N jobs where every job is represented by following three elements of it.

 Start Time
 Finish Time
 Profit or Value Associated
 Find the maximum profit subset of jobs such that no two jobs in the subset overlap.

 Example:

 Input: Number of Jobs n = 4
 Job Details {Start Time, Finish Time, Profit}
 Job 1:  {1, 2, 50}
 Job 2:  {3, 5, 20}
 Job 3:  {6, 19, 100}
 Job 4:  {2, 100, 200}
 Output: The maximum profit is 250.
 We can get the maximum profit by scheduling jobs 1 and 4.
 Note that there is longer schedules possible Jobs 1, 2 and 3
 but the profit with this schedule is 20+50+100 which is less than 250.
 A simple version of this problem is discussed here where every job has same profit or value. The Greedy Strategy for activity selection doesn¡¯t work here as the longer schedule may have smaller profit or value.

 The above problem can be solved using following recursive solution.

 1) First sort jobs according to finish time.
 2) Now apply following recursive process.
 // Here arr[] is array of n jobs
 findMaximumProfit(arr[], n)
 {
 a) if (n == 1) return arr[0];
 b) Return the maximum of following two profits.
 (i) Maximum profit by excluding current job, i.e.,
 findMaximumProfit(arr, n-1)
 (ii) Maximum profit by including the current job
 }

 How to find the profit including current job?
 The idea is to find the latest job before the current job (in
 sorted array) that doesn't conflict with current job 'arr[n-1]'.
 Once we find such a job, we recur for all jobs till that job and
 add profit of current job to result.
 In the above example, "job 1" is the latest non-conflicting
 for "job 4" and "job 2" is the latest non-conflicting for "job 3".

 */

class Job {
    public int start;
    public int finish;
    public int profit;

    public Job (int s, int f, int p) {
        this.start = s;
        this.finish = f;
        this.profit = p;
    }
}

public class WeightedJobScheduling {

    public int findMaxProfit(Job[] jobs) {

        Comparator<Job> comp = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.finish - o2.finish;
            }
        };

        if (jobs == null || jobs.length == 0) return 0;
        
        Arrays.sort(jobs, comp);
        
        for (Job job : jobs) {
        	System.out.println("start = " + job.start + ", end = " + job.finish);
        }
        
        int[]  dp = new int[jobs.length];
        dp[0] = jobs[0].profit;
        int res = dp[0];
        for (int i = 1; i < jobs.length; i++) {
        	dp[i] = Math.max(jobs[i].profit, dp[i - 1]);
            int j = 0;
            while (j < i) {
                if (jobs[j].finish <= jobs[i].start) {
                    dp[i] = Math.max(dp[i], dp[j] + jobs[i].profit);
                    System.out.println("i = " + i + ", j = " + j + ", profit = " + dp[i]);
                    
                } 
                j++;
            } 
            res = Math.max(res, dp[i]);
            System.out.println("res = " + res);
        } 

        return  res;
    }
    
    public static void main(String args[]){
        Job jobs[] = new Job[6];
        jobs[0] = new Job(1,3,5);
        jobs[1] = new Job(2,5,6);
        jobs[2] = new Job(4,6,5);
        jobs[3] = new Job(6,7,4);
        jobs[4] = new Job(5,8,11);
        jobs[5] = new Job(7,9,2);
        WeightedJobScheduling mp = new WeightedJobScheduling();
        System.out.println(mp.findMaxProfit(jobs));
    }

}
