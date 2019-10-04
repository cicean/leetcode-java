package facebook;

/**
 * Created by cicean on 9/7/2016.
 * 一个笑呵呵的三哥，出了道题：自然string comparator。不知道的搜下。
 * 就是string 比较的时候考虑里面数字的大小，比如 abc9 < abc123 abc > ab9
 */
public class Naturalstringcomparator {

    class NaturalComparator implements Comparator<String> {
            /**
             * return negative num if l < r 0 if l == r positive if l > r
             */
        public static void main(String[] args) {
            NaturalComparator nc = new NaturalComparator();
            System.out.println(nc.compare("abc8b", "abc7a"));
        }
        public  int compare(String l, String r) {
            int i = 0;
            int j = 0;
            while (i < l.length() && j < r.length()) {
                if (l.charAt(i) == r.charAt(j) && !Character.isDigit(l.charAt(i))) {
                    i++;
                    j++;
                } else {
                    if (Character.isDigit(l.charAt(i)) && Character.isDigit(r.charAt(j))) {
                        int num1 = 0;
                        int num2 = 0;
                        while (i < l.length() && Character.isDigit(l.charAt(i))) {
                            num1 = num1 * 10 + (l.charAt(i) - '0');
                            i++;
                        }
                        while (j < r.length() && Character.isDigit(r.charAt(j))) {
                            num2 = num2 * 10 + (r.charAt(j) - '0');
                            j++;
                        }
                        if (num1 < num2) {
                            return -1;
                        } else if (num1 > num2){
                            return 1;
                        }
                    } else {
                        if (Character.isDigit(l.charAt(i))) {
                            return -1;
                        } else if (Character.isDigit(r.charAt(j))) {
                            return 1;
                        } else {
                            if (l.charAt(i) < r.charAt(j)) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }

                    }
                }
            }
            if (i == l.length() && j == r.length()) {
                return 0;
            }
            if (i == l.length()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    class NaturalComparator implements Comparator<String> {


        /**
         * return negative num if l < r; 0 if l == r; positive if l > r
         */
        public int compare(String l, String r) {
            if (l.isEmpty() && !r.isEmpty())
                return -1;
            if (!l.isEmpty() && r.isEmpty())
            return 1;
            if (l.isEmpty() && r.isEmpty())
            return 0;

            int ll = 0;
            while (ll < l.length() && Character.isDigit(l.charAt(ll))) {
                ll++;
            }
            int rr = 0;
            while (rr < r.length() && Character.isDigit(r.charAt(rr))) {
                rr++;
            }

            if (ll == 0 && rr > 0)
                return 1;
            else if (ll > 0 && rr == 0)
                return -1;
            else if (ll > 0 && rr > 0) {
                int lll = Integer.parseInt(l.substring(0, ll));
                int rrr = Integer.parseInt(r.substring(0, rr));
                if (lll != rrr) {
                    return lll - rrr;
                } else {
                    return compare(l.substring(ll), r.substring(rr));
                }
            } else {
                if (l.charAt(0) != r.charAt(0)) {
                    return l.charAt(0) - r.charAt(0);
                } else {
                    return compare(l.substring(1), r.substring(1));
                }
            }
        }
    }

    public int compare(String l, String r) {
        int b1 = 0, b2 =0;
//find break point
        while(b1 < l.length() && !Character.isDigit( l.charAt(b1) ) )
            b1++;
        while(b2 < r.length() && !Character.isDigit( r.charAt(b2) ) )
            b2++;

        int cmp = l.substring(0,b1).compareTo(r.substring(0,b2));
        if (cmp == 0) {
            cmp = Integer.parseInt(l.substring(b1)) - Integer.parseInt(r.substring(b2))
        }
        return cmp;
    }


    public class Solution {
        public static void main(String[] args) {
            System.out.println(new NaturalComparator().compare("abc9", "abc123"));
            System.out.println(new NaturalComparator().compare("abc", "ab9"));
            System.out.println(new NaturalComparator().compare("abc9", "abc001"));. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
            return;
        }
    }

}
