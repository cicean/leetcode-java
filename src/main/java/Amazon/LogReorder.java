package Amazon;

import java.util.*;

/**
 * give the log size (lines of the list)
 * log list (id with )
 */

public class LogReorder {

    public List<String> logReoder(int logFileSize, List<String> logLines) {
        List<String> alphList = new ArrayList<>();
        List<String> numList =  new ArrayList<>();
        Comparator<String> compare = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String idx1 = o1.toLowerCase().split("\\s+")[0];
                String idx2 = o2.toLowerCase().split("\\s+")[0];
                String content1 = o1.substring(idx1.length()).replaceAll("\\s+", "").toLowerCase();
                String content2 = o2.substring(idx2.length()).replaceAll("\\s+", "").toLowerCase();
                System.out.println("idx1 and content1: " + idx1 + ", " + content1);
                System.out.println("idx1 and content1: " + idx2 + ", " + content2);

                String[] words1 = o1.substring(idx1.length()).toLowerCase().split("\\s+");
                String[] words2 = o2.substring(idx2.length()).toLowerCase().split("\\s+");
                System.out.println("idx1 and content1: " + idx1 + ", " + content1);
                System.out.println("idx1 and content1: " + idx2 + ", " + content2);
                int res = 0;
                for (int i = 0; i < (words1.length >= words2.length ? words2.length : words1.length); i++) {
                    res = content1.compareTo(content2);
                    if (res == 0) {
                        continue;
                    } else {
                        break;
                    }
                }
                int res = content1.compareTo(content2);

                if (res == 0) {
                    res = idx1.compareTo(idx2);
                }
                return res;
            }
        };

        for (int i = 0; i < logFileSize; i++) {
            logLines.subList()
        }

        for (String logline : logLines) {
            char c = logline.split("\\s+")[1].charAt(0);
            if (Character.isDigit(c)) {
                numList.add(logline);
            } else {
                alphList.add(logline);
            }
        }

        alphList.sort(compare);
        alphList.addAll(numList);

        return alphList;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<String> test = new ArrayList<>();
        test.add("a1 9 2 3 1");
        test.add("q1 Act car");
        test.add("zo4 4 7");
        test.add("ab1 off KEY dog");
        test.add("a8 act zoo");

        LogReorder obj = new LogReorder();

        List<String> res = obj.logReoder(5, test);

        for (String line : res) {
            System.out.println(line);
        }

    }

}
