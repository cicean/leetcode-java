import java.util.*;

/**
 * 630. Course Schedule III
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

 Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.

 Example:
 Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 Output: 3
 Explanation:
 There're totally 4 courses, but you can take 3 courses at most:
 First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 Note:
 The integer 1 <= d, t, n <= 10,000.
 You can't take two courses simultaneously.
 */

public class CourseScheduleIII {

  /**
   * Approach #1 Brute Force [Time Limit Exceeded]

   Algorithm

   The most naive solution will be to consider every possible permutation of the given courses and to try to take as much courses as possible by taking the courses in a serial order in every permutation. We can find out the maximum number of courses that can be taken from out of values obtained from these permutations.

   Complexity Analysis

   Time complexity : O\big((n+1)!\big)O((n+1)!). A total of n!n! permutations are possible for the coursescourses array of length nn. For every permutation, we scan over the nn elements of the permutation to find the number of courses that can be taken in each case.

   Space complexity : O(n)O(n). Each permutation needs nn space.
   */

  /**
   * Approach #2 Using Recursion with memoization[Time Limit Exceeded]

   Algorithm

   Before we move on to the better approaches, let's discuss one basic idea to solve the given problem. Suppose, we are considering only two courses (a,x)(a,x) and (b,y)(b,y). Let's assume y>xy>x. Now, we'll look at the various relative values which aa and bb can take, and which course should be taken first in each of these cases. In all the cases, we assume that the course's duration is always lesser than its end day i.e. aa and bb.

   (a+b)≤x(a+b)≤x: In this case, we can take the courses in any order. Both the courses can be taken irrespective of the order in which the courses are taken.
   Courses

   (a+b)>x(a+b)>x, a>ba>b, (a+b)≤y(a+b)≤y: In this case, as is evident from the figure, both the courses can be taken only by taking course aa before bb.
   Courses

   (a+b)>x(a+b)>x, b>ab>a, (a+b)≤y(a+b)≤y: In this case also, both the courses can be taken only by taking course aa before bb.
   Courses

   (a+b)>y(a+b)>y: In this case, irrespective of the order in which we take the courses, only one course can be taken.
   Courses

   From the above example, we can conclude that it is always profitable to take the course with a smaller end day prior to a course with a larger end day. This is because, the course with a smaller duration, if can be taken, can surely be taken only if it is taken prior to a course with a larger end day.

   Based on this idea, firstly, we sort the given coursescourses array based on their end days. Then, we try to take the courses in a serial order from this sorted coursescourses array.

   In order to solve the given problem, we make use of a recursive function schedule(courses, i, time) which returns the maximum number of courses that can be taken starting from the i^{th}i
   ​th
   ​​  course(starting from 0), given the time aleady consumed by the other courses is timetime, i.e. the current time is timetime, given a coursescourses array as the schedule.

   Now, in each function call to schedule(courses, i, time), we try to include the current course in the taken courses. But, this can be done only if time + duration_i < end\_day_itime+duration
   ​i
   ​​ <end_day
   ​i
   ​​ . Here, duration_iduration
   ​i
   ​​  refers to the duration of the i^{th}
   ​​  course and end\_day_iend_day
   ​i
   ​​  refers to the end day of the i^{th}course.

   If the course can be taken, we increment the number of courses taken and obtain the number of courses that can be taken by passing the updated time and courses' index. i.e. we make the function call schedule(courses, i + 1, time + duration_i). Let's say, we store the number of courses that can be taken by taking the current course in takentaken variable.

   Further, for every current course, we also leave the current course, and find the number of courses that can be taken thereof. Now, we need not update the time, but we need to update the courses' index. Thus, we make the function call, schedule(courses, i + 1, time). Let's say, we store the count obtained in not\_takennot_taken variable.

   While returning the number of courses at the end of each function call, we return the maximum value out of takentaken and not\_takennot_taken.

   Thus, the function call schedule(courses, 0, 0) gives the required result.

   In order to remove this redundancy, we make use of a memoization array memomemo, such that memo[i][j]memo[i][j] is used to store the result of the function call schedule(courses, i, time). Thus, whenever the same function call is made again, we can return the result directly from the memomemo array. This helps to prune the search space to a great extent.

   Complexity Analysis

   Time complexity : O(n*d)O(n∗d). memomemo array of size nnxdd is filled once. Here, nn refers to the number of courses in the given coursescourses array and dd refers to the maximum value of the end day from all the end days in the coursescourses array.

   Space complexity : O(n*d)O(n∗d). memomemo array of size nnxdd is used.

   */

  public class Solution2 {
    public int scheduleCourse(int[][] courses) {
      Arrays.sort(courses, (a, b) -> a[1] - b[1]);
      Integer[][] memo = new Integer[courses.length][courses[courses.length - 1][1] + 1];
      return schedule(courses, 0, 0, memo);
    }
    public int schedule(int[][] courses, int i, int time, Integer[][] memo) {
      if (i == courses.length)
        return 0;
      if (memo[i][time] != null)
        return memo[i][time];
      int taken = 0;
      if (time + courses[i][0] <= courses[i][1])
        taken = 1 + schedule(courses, i + 1, time + courses[i][0], memo);
      int not_taken = schedule(courses, i + 1, time, memo);
      memo[i][time] = Math.max(taken, not_taken);
      return memo[i][time];
    }
  }

  /**
   * Approach #3 Iterative Solution [Time Limit Exceeded]

   For the current approach, the idea goes as follows. As discussed in the previous approaches, we need to sort the given coursescourses array based on the end days. Thus, we consider the courses in the ascending order of their end days. We keep a track of the current time in a timetime variable. Along with this, we also keep a track of the number of courses taken till now in countcount variable.

   For each course being considered currently(let's say i^{th}i
   ​th
   ​​  course), we try to take this course. But, to be able to do so, the current course should end before its corresponding end day i.e. time+durationi≤end\dayitime+durationi≤end\dayi. Here, duration_iduration
   ​i
   ​​  refers to the duration of the i^{th}i
   ​th
   ​​  course and end\_day_iend_day
   ​i
   ​​  refers to the end day of the i^{th}i
   ​th
   ​​  course.

   If this course can be taken, we update the current time to time + duration_itime+duration
   ​i
   ​​  and also increment the current countcount value to indicate that one more course has been taken.

   But, if we aren't able to take the current course i.e. time + duration_i > end\_day_itime+duration
   ​i
   ​​ >end_day
   ​i
   ​​ , we can try to take this course by removing some other course from amongst the courses that have already been taken. But, the current course can fit in by removing some other course, only if the duration of the course(j^{th}j
   ​th
   ​​ ) being removed duration_jduration
   ​j
   ​​  is larger than the current course's duration, duration_iduration
   ​i
   ​​  i.e. duration_j > duration_iduration
   ​j
   ​​ >duration
   ​i
   ​​ .

   We are sure of the fact that by removing the j^{th}j
   ​th
   ​​  course, we can fit in the current course, because, course_jcourse
   ​j
   ​​  was already fitting in the duration available till now. Since, duration_i < duration_jduration
   ​i
   ​​ <duration
   ​j
   ​​ , the current course can surely take its place. Thus, we look for a course from amongst the taken courses having a duration larger than the current course.

   But why are we doing this replacement? The answer to this question is as follows. By replacing the j^{th}j
   ​th
   ​​  course, with the i^{th}i
   ​th
   ​​  course of a relatively smaller duration, we can increase the time available for upcoming courses to be taken. An extra duration_j - duration_iduration
   ​j
   ​​ −duration
   ​i
   ​​  time can be made available by doing so.

   Now, for this saving in time to be maximum, the course taken for the replacement should be the one with the maximum duration. Thus, from amongst the courses that have been taken till now, we find the course having the maximum duration which should be more than the duration of the current course(which can't be taken).

   Let's say, this course be called as max\_imax_i. Thus, now, a saving of duration_{max\_i} - duration_iduration
   ​max_i
   ​​ −duration
   ​i
   ​​  can be achived, which could help later in fitting in more courses to be taken.

   If such a course, max\_imax_i, is found, we remove this course from the taken courses and consider the current course as taekn. We also mark this course with \text{-1}-1 to indicate that this course has not been taken and should not be considered in the future again for replacement.

   But, if such a course isn't found, we can't take the current course at any cost. Thus, we mark the current course with \text{-1}-1 to indicate that the current course has not been taken.

   At the end, the value of countcount gives the required result.

   The following animation illustrates the process.

   Complexity Analysis

   Time complexity : O(n^2). We iterate over the countcount array of size nn once. For every element currently considered, we could scan backwards till the first element, giving O(n^2) complexity. Sorting the countcount array takes O\big(nlog(n)\big)O(nlog(n)) time for countcount array.

   Space complexity : O(1)O(1). Constant extra space is used.
   */

  public class Solution3 {
    public int scheduleCourse(int[][] courses) {
      System.out.println(courses.length);
      Arrays.sort(courses, (a, b) -> a[1] - b[1]);
      int time = 0, count = 0;
      for (int i = 0; i < courses.length; i++) {
        if (time + courses[i][0] <= courses[i][1]) {
          time += courses[i][0];
          count++;
        } else {
          int max_i = i;
          for (int j = 0; j < i; j++) {
            if (courses[j][0] > courses[max_i][0])
              max_i = j;
          }
          if (courses[max_i][0] > courses[i][0]) {
            time += courses[i][0] - courses[max_i][0];
          }
          courses[max_i][0] = -1;
        }
      }
      return count;
    }
  }

  /**
   * Approach #4 Optimized Iterative [Accepted]

   In the last approach, we've seen that, in the case of current course which can't be taken direclty, i.e. for time + duration_i > end\_day_itime+duration
   ​i
   ​​ >end_day
   ​i
   ​​ , we need to traverse back in the coursescourses array till the beginning to find a course with the maximum duration which is larger than the current course's duration. This backward traversal also goes through the courses which aren't taken and thus, can't be replaced, and have been marked as \text{-1}-1.

   We can bring in some optimization here. For this, we should search among only those courses which have been taken(and not the ones which haven't been taken).

   To do so, as we iterate over the coursescourses array, we also keep on updating it, such that the first countcount number of elements in this array now correspond to only those countcount number of courses which have been taken till now.

   Thus, whenever we update the countcount to indicate that one more course has been taken, we also update the courses[count]courses[count] entry to reflect the current course that has just been taken.

   Whenever, we find a course for which time + duration_i > end\_day_itime+duration
   ​i
   ​​ >end_day
   ​i
   ​​ , we find a max_imax
   ​i
   ​​  course from only amongst these first countcount number of courses in the coursescourses array, which indicate the courses that have been taken till now.

   Also, instead of marking this max_i^{th}max
   ​i
   ​th
   ​​  course with a \text{-1}-1, we can simply replace this course with the current course. Thus, the first countcount courses still reflect the courses that have been taken till now.

   Complexity Analysis

   Time complexity : O(n*count)O(n∗count). We iterate over a total of nn elements of the coursescourses array. For every element, we can traverse backwards upto atmost countcount(final value) number of elements.

   Space complexity : O(1)O(1). Constant extra space is used.
   */

  public class Solution4 {
    public int scheduleCourse(int[][] courses) {
      System.out.println(courses.length);
      Arrays.sort(courses, (a, b) -> a[1] - b[1]);
      int time = 0, count = 0;
      for (int i = 0; i < courses.length; i++) {
        if (time + courses[i][0] <= courses[i][1]) {
          time += courses[i][0];
          courses[count++] = courses[i];
        } else {
          int max_i = i;
          for (int j = 0; j < count; j++) {
            if (courses[j][0] > courses[max_i][0])
              max_i = j;
          }
          if (courses[max_i][0] > courses[i][0]) {
            time += courses[i][0] - courses[max_i][0];
            courses[max_i] = courses[i];
          }
        }
      }
      return count;
    }
  }

  /**
   * Approach #5 Using Extra List [Accepted]

   Algorithm

   In the last approach, we updated the coursecourse array itself so that the first countcount elements indicate the countcount number of courses that have been taken till now. If it is required to retain the coursescourses array as such, we can do the same job by maintaining a separate list valid\_listvalid_list which is the list of those courses that have been taken till now.

   Thus, to find the max_imax
   ​i
   ​​  course, we need to search in this list only. Further, when replacing this max_i^{th}max
   ​i
   ​th
   ​​  course with the current course, we can replace this max_imax
   ​i
   ​​ course in the list with current course directly. The rest of the method remains the same as the last approach.

   Complexity Analysis

   Time complexity : O(n*m)O(n∗m). We iterate over a total of nn elements of the coursescourses array. For every element, we can traverse over atmost mm number of elements. Here, mm refers to the final length of the valid\_listvalid_list.

   Space complexity : O(n)O(n). The valid\_listvalid_list can contain atmost nn courses.

   */

  public class Solution5 {
    public int scheduleCourse(int[][] courses) {
      Arrays.sort(courses, (a, b) -> a[1] - b[1]);
      List< Integer > valid_list = new ArrayList < > ();
      int time = 0;
      for (int[] c: courses) {
        if (time + c[0] <= c[1]) {
          valid_list.add(c[0]);
          time += c[0];
        } else {
          int max_i=0;
          for(int i=1; i < valid_list.size(); i++) {
            if(valid_list.get(i) > valid_list.get(max_i))
              max_i = i;
          }
          if (valid_list.get(max_i) > c[0]) {
            time += c[0] - valid_list.get(max_i);
            valid_list.set(max_i, c[0]);
          }
        }
      }
      return valid_list.size();
    }
  }

  /**
   * Approach #6 Using Priority Queue [Accepted]

   Algorithm

   This approach is inspired by @stomach_ache

   In the last few approaches, we've seen that we needed to traverse over the courses which have been taken to find the course(with the maximum duration) which can be replaced by the current course(if it can't be taken directly). These traversals can be saved, if we make use of a Priority Queue, queuequeue(which is implemented as a Max-Heap) which contains the durations of all the courses that have been taken till now.

   The iteration over the sorted coursescourses remains the same as in the last approaches. Whenver the current course(i^{th}i
   ​th
   ​​  course) can be taken(time+durationi≤end_dayitime+durationi≤end_dayi), it is added to the queuequeue and the value of the current time is updated to time + duration_itime+duration
   ​i
   ​​ .

   If the current course can't be taken directly, as in the previous appraoches, we need to find a course whose duration duration_jduration
   ​j
   ​​  is maximum from amongst the courses taken till now. Now, since we are maintaing a Max-Heap, queuequeue, we can obtain this duration directly from this queuequeue. If the duration duration_j > duration_iduration
   ​j
   ​​ >duration
   ​i
   ​​ , we can replace the j^{th}j
   ​th
   ​​  course, with the current one.

   Thus, we remove the duration_jduration
   ​j
   ​​  from the queuequeue and add the current course's duration duration_iduration
   ​i
   ​​  to the queuequeue. We also need to make proper adjustments to the timetime to account for this replacement done.

   At the end, the number of elements in the queuequeue represent the number of courses that have been taken till now.

   Complexity Analysis

   Time complexity : O\big(nlog(n)\big)O(nlog(n)). At most nn elements are added to the queuequeue. Adding each element is followed by heapification, which takes O\big(log(n)\big)O(log(n)) time.

   Space complexity : O(n)O(n). The queuequeue containing the durations of the courses taken can have atmost nn elements

   */

  public class Solution6 {
    public int scheduleCourse(int[][] courses) {
      Arrays.sort(courses, (a, b) -> a[1] - b[1]);
      PriorityQueue < Integer > queue = new PriorityQueue < > ((a, b) -> b - a);
      int time = 0;
      for (int[] c: courses) {
        if (time + c[0] <= c[1]) {
          queue.offer(c[0]);
          time += c[0];
        } else if (!queue.isEmpty() && queue.peek() > c[0]) {
          time += c[0] - queue.poll();
          queue.offer(c[0]);
        }
      }
      return queue.size();
    }
  }

}
