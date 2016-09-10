package facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cicean on 9/4/2016.
 */
public class TaskSchedule {


    /**
     * 每种task都有冷却时间，比如task1执行后，要经过interval时间后才能再次执行，求总共所需时间。
     * 就是比如给你一串task，再给一个cooldown，执行每个task需要时间1，
     * 两个相同task之间必须至少相距cooldown的时间，问执行所有task总共需要多少时间。
     * 比如执行如下task：12323，假设cooldown是3。总共需要的时间应该是 1 2 3 _ _ 2 3，
     * 也就是7个单位的时间。再比如 1242353，
     * 假设cool down是4，那总共时间就是 1 2 4 _ _ _ 2 3 5 _ _ _ 3，也就是13个单位的时间。
     *
     * @param str
     * @param recover
     * @return
     */

    //O(n)
    public int schedule(int[] str, int recover) {
        if (str == null || str.length == 0) return 0;
        if (recover == 0) return str.length;
        int pos = 0;
        int time = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (; pos < str.length; pos++) {
            int cur = str[pos];
            if (!map.containsKey(cur)) {
                map.put(cur, time + recover + 1);
            } else {
                int lastApr = map.get(cur);
                if (time >= lastApr) {
                    map.put(cur, time);
                } else {
                    pos--;
                }
            }
            time++;
        }
        return time;
    }

    public static int getRunTime(char[] tasks, int waitTime) {
        if (tasks == null) {
            throw new NullPointerException();
        }

        LinkedHashMap<Character, Integer> charToExecTime = new LinkedHashMap<Character, Integer>();
        int time = 0;
        for (char c : tasks) {
            //compute the timing for each task
            Integer lastTime = charToExecTime.get(c);
            if (lastTime != null && lastTime + waitTime > time) {
                time = lastTime + waitTime;
            }
            charToExecTime.put(c, time);
            time++;

            //clear out the unnecessary tasks
            ArrayList<Character> remove = new ArrayList<Character>();
            for (Map.Entry<Character, Integer> entry : charToExecTime.entrySet()) {
                if (entry.getValue() >= time) {
                    remove.add(entry.getKey());
                } else {
                    break;
                }
            }
            for (Character character : remove) {
                charToExecTime.remove(character);
            }
        }
        return time;
    }

    public class Solution {
        public List<Integer> Printer(String[] tasks, int cooldown) {
            List<Integer> ret = new  LinkedList<Integer>();
            if (tasks == null || tasks.length == 0) {
                return ret;
            }

            HashMap<String, Integer> map = new HashMap<String, Integer>();
            int j = 0;
            for(int i = 0; i < tasks.length; i++) {
                while (map.containsKey(tasks[i]) && map.get(tasks[i]) + cooldown >= j) {
                    ret.add("_");
                    j++;
                }
                ret.add(tasks[i]);
                map.put(tasks[i], j);
                j++;
            }
            return ret;
        }
    }

    static class CoolDown{
        static String getStr(String s, int k) {
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                int idx = map.getOrDefault(c, -k);
                while(sb.length() - k < idx) {
                    sb.append("_");
                }
                sb.append(c);
                map.put(c,sb.length());
            }
            return sb.toString();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TaskSchedule sol = new TaskSchedule();
        System.out.println(sol.schedule(new int[]{1, 2, 3, 1, 2, 3}, 3));

    }


}
