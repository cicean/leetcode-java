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
     * ÿ��task������ȴʱ�䣬����task1ִ�к�Ҫ����intervalʱ�������ٴ�ִ�У����ܹ�����ʱ�䡣
     * ���Ǳ������һ��task���ٸ�һ��cooldown��ִ��ÿ��task��Ҫʱ��1��
     * ������ͬtask֮������������cooldown��ʱ�䣬��ִ������task�ܹ���Ҫ����ʱ�䡣
     * ����ִ������task��12323������cooldown��3���ܹ���Ҫ��ʱ��Ӧ���� 1 2 3 _ _ 2 3��
     * Ҳ����7����λ��ʱ�䡣�ٱ��� 1242353��
     * ����cool down��4�����ܹ�ʱ����� 1 2 4 _ _ _ 2 3 5 _ _ _ 3��Ҳ����13����λ��ʱ�䡣
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

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TaskSchedule sol = new TaskSchedule();
        System.out.println(sol.schedule(new int[]{1, 2, 3, 1, 2, 3}, 3));

    }


}
