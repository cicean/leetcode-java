package LimeBike;

import javafx.scene.shape.Circle;

import java.util.*;

public class BlockThePipe {
    /**
     * 输入是一堆圆的圆心和半径，问这些圆能否堵塞通道
     * 类似无向联通图问题，问是否存在从y1 到 y2 互相有交集的圆，相切或者包含即为联通.
     */

    class Circle {
        int x;
        int y;
        int r;
        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    public boolean isBlockThePipe(List<Circle> circles, int y1, int y2) {

        //build undirect graph
        Map<Circle, List<Circle>> graph = new HashMap<>();
        Queue<Circle> queueFromY1 = new LinkedList<>();
        Queue<Circle> queueFromY2 = new LinkedList<>();
        Set<Circle> start = new HashSet<>();
        Set<Circle> end = new HashSet<>();


        //双向bfs 看有没有交集
        for (Circle c1 : circles) {
            if (Math.abs(c1.y - y1) <= c1.r) {
                start.add(c1);
            }

            if (Math.abs(c1.y - y2) <= c1.r) {
                end.add(c1);
            }

            for (Circle c2 : circles) {

                if (Math.abs(c2.y - y1) <= c2.r) {
                    start.add(c2);
                }

                if (Math.abs(c2.y - y2) <= c2.r) {
                    end.add(c2);
                }

                if (!c1.equals(c2)) {
                     int o1o2 = (int)Math.sqrt(Math.pow(c1.x - c2.x,2) + Math.pow(c1.y - c2.y,2));
                     int d = c1.r + c2.r;
                     if (o1o2 <= d) {
                         graph.computeIfAbsent(c1, x-> new ArrayList<Circle>()).add(c2);
                         graph.computeIfAbsent(c2, x-> new ArrayList<Circle>()).add(c1);
                     }
                }
            }
        }

        if (start.size() == 0 || end.size() == 0) return false;

        queueFromY1.addAll(start);
        queueFromY2.addAll(end);
        start.clear();
        end.clear();

        while (!queueFromY1.isEmpty() || !queueFromY2.isEmpty()) {
            int startSize = queueFromY1.size();
            int endSize = queueFromY2.size();
            for (int i = 0; i < startSize; i ++) {
                Circle cur = queueFromY1.poll();
                if (!graph.containsKey(cur)) {
                    continue;
                }
                for (Circle neighbor : graph.get(cur)) {
                    if (start.contains(neighbor)) {//重复节点
                        continue;
                    } else if (end.contains(neighbor)) {//相交
                        return true;
                    } else {
                        start.add(neighbor);
                        queueFromY1.add(neighbor);
                    }
                }
            }

            for (int i = 0; i < endSize; i ++) {
                Circle cur = queueFromY2.poll();
                if (!graph.containsKey(cur)) {
                    continue;
                }
                for (Circle neighbor : graph.get(cur)) {
                    if (end.contains(neighbor)) {//重复节点
                        continue;
                    } else if (start.contains(neighbor)) {//相交
                        return true;
                    } else {
                        end.add(neighbor);
                        queueFromY2.add(neighbor);
                    }
                }
            }
        }

     return false;
    }
}
