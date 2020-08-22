import java.util.ArrayList;
import java.util.List;

/*
 * 248	Strobogrammatic Number III 	20.5%	Hard
 * 
 * LeetCode: Strobogrammatic Number III
AUG 9 2015

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,

Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:

Because the range might be a large number, the low and high numbers are represented as string.
 * 
 */
public class StrobogrammaticNumberIII {
	
	private char[] validNumbers = new char[]{'0', '1', '6', '8', '9'};
    private char[] singleable = new char[]{'0', '1', '8'};

    public int strobogrammaticInRange(String low, String high) {
    	assert low != null && high != null;
    	
    	int ll = low.length();
    	int hl = high.length();
    	int ret = 0;
    	
    	if (ll > hl || (ll == hl && low.compareTo(high) > 0)) {
    		return 0;
    	}
    	
    	List<String> list = findStrobogrammatic(ll);
    	
    	if (ll == hl) {
    		for (String s : list) {
    			if (s.compareTo(low) >= 0 && s.compareTo(high) <= 0) {
    				ret++;
    			}
    			
    			if (s.compareTo(high) > 0) {
    				break;
    			}
    		}
    		
    	} else {
    		for (int i = list.size() - 1; i >= 0; i--) {
    			String s = list.get(i);
    			if (s.compareTo(low) >= 0) {
    				ret++;
    			}
    			
    			if (s.compareTo(low) < 0) {
    				break;
    			}
    		}
    		
    		list = findStrobogrammatic(hl);
    		
    		for (String s : list) {
    			if (s.compareTo(high)  <= 0) {
    				ret++;
    			}
    			
    			if (s.compareTo(high) > 0) {
    				break;
    			}
    		}
    		
    		for (int i = ll + 1; i < hl; i++) {
    			ret += findStrobogrammatic(i).size();
    			
    		}
    		
    	}
    	//System.out.println(list);
    	return ret;
    	
    }
    
    public List<String> findStrobogrammatic(int n) {
        assert n > 0;
        List<String> result = new ArrayList<>();

        if (n == 1) {
            for (char c : singleable) {
                result.add(String.valueOf(c));
            }
            return result;
        }

        if (n % 2 == 0) {
            helper(n, new StringBuilder(), result);
        } else {
            helper(n - 1, new StringBuilder(), result);
            List<String> tmp = new ArrayList<>();
            for (String s : result) {
                for (char c : singleable) {
                    tmp.add(new StringBuilder(s).insert(s.length() / 2, c).toString());
                }
            }
            result = tmp;
        }
        return result;
    }

    private void helper(int n, StringBuilder sb, List<String> result) {
        if (sb.length() > n) return;

        if (sb.length() == n) {
            if (sb.length() > 0 && sb.charAt(0) != '0') {
                result.add(sb.toString());
            }
            return;
        }

        for (char c : validNumbers) {
            StringBuilder tmp = new StringBuilder(sb);
            String s = "" + c + findMatch(c);
            tmp.insert(tmp.length() / 2, s);
            helper(n, tmp, result);
        }
    }

    private char findMatch(char c) {
        switch (c) {
            case '1':
                return '1';
            case '6':
                return '9';
            case '9':
                return '6';
            case '8':
                return '8';
            case '0':
                return '0';
            default:
                return 0;
        }
    }


    class Solution {
        private static final char[][] outlines = {
                {'0', '0'},
                {'1', '1'},
                {'6', '9'},
                {'8', '8'},
                {'9', '6'}
        };

        public int strobogrammaticInRange(String low, String high) {
            int[][] low_memory = new int[low.length() + 1][2], full_memory = new int[high.length() + 1][3];
            int[][] high_memory = new int[high.length() + 1][2];
            preprocess(high.length(), full_memory);
            int high_less = less(high, false, true, 0, high.length()-1, high_memory, full_memory);
            int low_less = less(low, true, true, 0, low.length()-1, low_memory, full_memory);
            int result = (high_less + full_memory[high.length()-1][2]) - (low_less + full_memory[low.length()-1][2]);
            return result < 0 ? 0 : result;
        }

        private void preprocess(int n, int[][] memory) {
            for (int i = n; i > 0; i--) {
                full(i, true, memory);
                full(i, false, memory);
            }
            int acc = 0;
            for (int i = 1; i <= n; i++) {
                memory[i][2] = memory[i][0] + memory[i-1][2];
            }
        }

        private int full(int n, boolean first, int[][] memory) {
            if (n == 1) {
                memory[n][0] = 3;
                memory[n][1] = 3;
                return 3; // 0, 1, 8
            }

            if (n == 2) {
                memory[n][0] = 4;
                memory[n][1] = 5;
                return memory[n][first ? 0 : 1]; //11, 88, 69, 96
            }

            if (memory[n][first ? 0 : 1] != 0) {
                return memory[n][first ? 0 : 1];
            }

            if (first) {
                int result = 4 * full(n-2, false, memory);
                memory[n][0] = result;
                return result;
            } else {
                int result = 5 * full(n-2, false, memory);
                memory[n][1] = result;
                return result;
            }
        }

        private int less(String val, boolean strict, boolean first, int l, int r, int[][] memory, int[][] full_memory) { // <
            if (l == r) {
                int result = 0;
                char cur = val.charAt(l);
                if (cur > '0' || (cur == '0' && !strict)) {
                    result += 1;
                }
                if (cur > '1' || (cur == '1' && !strict)) {
                    result += 1;
                }
                if (cur > '8' || (cur == '8' && !strict)) {
                    result += 1;
                }
                memory[l][first ? 0 : 1] = result;
                return result;
            }

            if (l == r-1) {
                int result = 0;
                for (char[] outline : outlines) {
                    if (outline[0] == '0' && first) {
                        continue;
                    }
                    if (outline[0] < val.charAt(l) || (outline[0] == val.charAt(l) && (outline[1] < val.charAt(r) || (outline[1] == val.charAt(r) && !strict)))) {
                        result += 1;
                    } else {
                        break;
                    }
                }
                memory[l][first ? 0 : 1] = result;
                return result;
            }

            int result = 0;
            for (char[] outline : outlines) {
                if (outline[0] == '0' && first) {
                    continue;
                }
                if (outline[0] < val.charAt(l)) { // strictly less than, can use full
                    result += full_memory[r - l -1][1];
                } else if (outline[0] == val.charAt(l)) {
                    result += less(val, outline[1] > val.charAt(r) || (strict && outline[1] == val.charAt(r)), false,l+1, r-1, memory, full_memory);            }  else {
                    break; // strictly larger, not acceptable
                }
            }
            memory[l][first ? 0 : 1] = result;
            return result;
        }
    }


    class Solution {
        private static char[][] PAIRS = {{'0','0'}, {'1', '1'}, {'6', '9'}, {'8','8'}, {'9', '6'}};

        public int strobogrammaticInRange(String low, String high) {
            int count = 0;
            for (int i = low.length(); i <= high.length(); i++) {
                count += dfs (low, high, new char[i], 0, i-1);
            }
            return count;
        }

        private int dfs (String low, String high, char[] c, int l, int r) {
            if (l > r) {
                String s = new String (c);
                if (s.length() == low.length() && s.compareTo(low) < 0 ||
                        s.length() == high.length() && s.compareTo(high) > 0 ) {
                    return 0;
                }
                return 1;
            }
            int count = 0;

            for (char[] pair: PAIRS) {
                c[l] = pair[0];
                c[r] = pair[1];
                if (c.length != 1 && c[0] == '0') {
                    continue;
                }
                if (l == r && pair[0] != pair[1]) {
                    continue;
                }
                count += dfs (low, high, c, l+1, r-1);
            }
            return count;
        }
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StrobogrammaticNumberIII slt = new StrobogrammaticNumberIII();
		String low = "50";
		String high = "100";
		System.out.println(slt.strobogrammaticInRange(low,high));
	}

    /**
     * Basic Idea:
     *
     * return all valid nums under upper (inclusive) - all valid nums under low (exclusive).
     *
     * Suppose upper has length len. The numbers of valid nums of len_i's < len, can be very efficiently computed using recursion or Math.pow();.
     *
     * For valid nums with len, construct them all and aggressively discard them if they are higher than upper (pruning). After all, char array comparison is cheap
     * : if(compareCharArray(chs, upper, i) > 0) break;
     */
    public class Solution {
        private static char[][] pairs = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
        public int strobogrammaticInRange(String low, String high) {
            if(low.length()>high.length() || low.length()==high.length() && high.compareTo(low)<0) return 0;
            return strobogrammaticInRangeFrom0(high, true) - strobogrammaticInRangeFrom0(low, false);
        }
        private int strobogrammaticInRangeFrom0(String num, boolean inclusive){
            int len = num.length();
            if(len == 1){
                if(num.charAt(0) == '0')        return inclusive ? 1 : 0;       // 0?
                else if(num.charAt(0) == '1')   return inclusive ? 2 : 1;       // 0,1?
                else if(num.charAt(0) < '8')    return 2;                       // 0,1
                else if(num.charAt(0) == '8')   return inclusive ? 3 : 2;       // 0,1,8?
                else                            return 3;                       // 0,1,8
            }
            int sum = 0;
            for(int i = 1; i < len; i++)
                sum += strobogrammaticDigit(i, true);
            sum += strobogrammaticInRangeSameDigits(new char[len], 0, len - 1, num.toCharArray(),inclusive);
            return sum;
        }
        private int strobogrammaticInRangeSameDigits(char[] chs, int i, int j, char[] upper, boolean inclusive){
            int sum = 0;
            if(i > j){
                if( inclusive && compareCharArray(upper, chs, chs.length-1 ) >= 0 || !inclusive && compareCharArray(upper, chs, chs.length-1) > 0 )    return 1;
                else    return 0;
            }
            for(char[] pair: pairs){
                if(i == 0 && pair[0] == '0' || i==j && (pair[0] == '6' || pair[0] == '9') )     continue;
                chs[i] = pair[0];
                chs[j] = pair[1];
                if(compareCharArray(chs, upper, i) > 0)     break;
                sum += strobogrammaticInRangeSameDigits(chs, i+1, j-1, upper, inclusive);
            }
            return sum;
        }
        private int strobogrammaticDigit(int digit, boolean outside){
            if(digit == 0)      return 1;
            if(digit == 1)      return 3;
            return outside? strobogrammaticDigit(digit-2, false)*4: strobogrammaticDigit(digit-2, false)*5;
        }
        private int compareCharArray(char[] arr1, char[] arr2, int idx){
            for(int i = 0; i <= idx; i++)
                if(arr1[i] == arr2[i])          continue;
                else if(arr1[i] > arr2[i])      return 1;
                else                            return -1;
            return 0;
        }
    }

}
