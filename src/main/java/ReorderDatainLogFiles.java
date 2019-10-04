import java.util.*;

/**
 * 937. Reorder Data in Log Files
 * Easy
 *
 * 270
 *
 * 790
 *
 * Favorite
 *
 * Share
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 *
 * Constraints:
 *
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 * **/

class ReorderDatainLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, comp2);
        return logs;
    }

    Comparator<String> comp = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {

            String[] split1 = s1.split(" ", 2);
            String[] split2 = s2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        }
    };

    Comparator<String> comp2 = new Comparator<String>(){
        public int compare(String s1, String s2){
            int s1si = s1.indexOf(" ");
            int s2si = s2.indexOf(" ");
            char s1fc = s1.charAt(s1si+ 1);
            char s2fc = s2.charAt(s2si+1);
            if(s1fc <='9'){
                if(s2fc<='9') return 0;
                else return 1;
            }
            if(s2fc <= '9')
                return -1;
            int pre = s1.substring(s1si + 1).compareTo(s2.substring(s2si + 1));
            if(pre == 0)
                return s1.substring(0, s1si).compareTo(s2.substring(0, s2si));
            return pre;
        }
    };

    public String[] reorderLogFiles_1(String[] logs) {
        if(logs==null || logs.length==0) {
            return null;
        }

        String[] result = new String[logs.length];
        int idx = 0;

        for(int i=0; i<logs.length; i++) {
            String log = logs[i];
            char c = log.charAt(log.indexOf(' ') + 1);
            if(!Character.isDigit(c)) {
                result[idx++] = log;
                logs[i] = null;
            }
        }

        mergeSort(result, 0, idx-1);

        for(int i=0; i<logs.length; i++) {
            if(logs[i] != null) {
                result[idx++] = logs[i];
            }
        }
        return result;
    }

    private void mergeSort(String[] result, int left, int right) {
        if(left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(result, left, mid);
        mergeSort(result, mid+1, right);
        merge(result, left, mid, right);
    }

    private void merge(String[] result, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for(int i=0; i<n1; i++) {
            L[i] = result[left+i];
        }
        for(int i=0; i<n2; i++) {
            R[i] = result[mid+1+i];
        }

        int x = 0;
        int y = 0;
        int start = left;

        while(x<n1 && y<n2) {
            String s1 = L[x];
            String v1 = s1.substring(s1.indexOf(' ') + 1);
            String s2 = R[y];
            String v2 = s2.substring(s2.indexOf(' ') + 1);

            if(v1.compareTo(v2) < 0) {
                result[start] = s1;
                x++;
                start++;
            } else {
                result[start] = s2;
                y++;
                start++;
            }
        }

        while(x < n1) {
            result[start] = L[x];
            x++;
            start++;
        }
        while(y < n2) {
            result[start] = R[y];
            y++;
            start++;
        }
    }
}