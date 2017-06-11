import java.util.*;

/*
 * 	227	Basic Calculator II	17.5%	Medium
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.

	Some examples:
	"3+2*2" = 7
	" 3/2 " = 1
	" 3+5 / 2 " = 5
	Note: Do not use the eval built-in library function.
 * 
 * Solution:
 * 1.1 pass两遍, 第一遍, 先解决乘除, 第二遍, 做加减.
 * 
 * 1.2
 * （1）连续的数字需要进行处理；
 * （2）乘除需要在后一个数字入栈后处理；
 * （3）最后栈内剩余为加减法，只需要一步步弹出即可，需要记住一次只弹出一个数字和符号，
 *     不能两个数字运算，在I中有说明，遗忘的话可以点击->  Basic Calculator I
 * （4）需要对最后一个数字进行特殊处理，因为前面的方法只有检测到有符号的时候才会将数字如栈。
：
 * 
 */
public class BasicCalculatorII {

	public int calculate_1(String s) {
        if(s==null || s.length()==0) return 0;
        
        LinkedList<Integer> list = new LinkedList<Integer>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int cur = c-'0';
                while(i+1<s.length() && Character.isDigit(s.charAt(i+1))) {
                    cur = cur * 10 + s.charAt(i+1) - '0';
                    ++i;
                }
                if(!list.isEmpty() && (list.peek() == 2 || list.peek()==3)) {
                    int op = list.pop();
                    int opl = list.pop();
                    int res = 0;
                    if(op==2) res = opl * cur;
                    else res = opl / cur;
                    list.push(res);
                } else {
                    list.push(cur);
                }               
            } else if(c==' ') continue;
            else {
                switch (c) {
                    case '+': list.push(0);
                    break;
                    case '-': list.push(1);
                    break;
                    case '*': list.push(2);
                    break;
                    case '/': list.push(3);
                    break;
                    default: return -1;
                }
            }
        }
        
        if(list.isEmpty()) return 0;
        Collections.reverse(list);
        
        int res = list.poll();
        
        while(!list.isEmpty()) {
        	int op = list.poll();
        	int opr = list.poll();
        	if(op==0) res += opr;
        	else res -= opr;
        }
        return res;
    }
	
	public int calculate_2(String s) {
        if(s == null || s.length() == 0)
            return 0;
        Stack<Integer> num = new Stack<Integer>();
        Stack<Character> symbol = new Stack<Character>();
        s += "#";
        boolean isNum = false;
        int count = 0;
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == ' ')
                continue;
            char temp = s.charAt(i);
            if(Character.isDigit(temp)){
                count = count * 10 + (temp - '0') ; // 连续的数字
                isNum = true;
            }else{
                if(isNum){  // 遇到符号 将前面数字压入
                    num.push(count);
                    count = 0;
                    isNum = false;
                    if(!symbol.isEmpty() && (symbol.peek() == '*' || symbol.peek() == '/')){
                        char tempSymbol = symbol.pop();
                        int b = num.pop();
                        int a = num.pop();
                        if(tempSymbol == '*'){
                            num.push(a*b);
                        }else{
                            num.push(a/b);
                        }
                    }
                }
                if(temp != '#'){ // 将符号压入
                    symbol.push(temp);
                }
            }
        }
        
        if(!symbol.isEmpty()){
            int rep = 0;
            while(!symbol.isEmpty()){  // 加减运算
                char tempSymbol = symbol.pop();
                int a = num.pop();
                if(tempSymbol == '+'){
                    rep+=a;
                }else{
                    rep-=a;
                }
            }
            num.push(rep + num.pop());
        }
        
        return num.pop();
    }
	
	//pocket gem
	/**
	 * 只有‘＋’和‘＊’，其实就是一样的。
	 * 我还写了一个小bug，就是每次见到sign之后，之前的number要清零，
	 * 还调试了一会儿。嗯，这个确实赖我。。。
	 * 然后又要支持‘－’，注意这不一样了，要考虑2+3*－4+5之类的情况。好不容易搞定了，又说要考虑括号。。。
	 * @param s
	 */
	
	public int calculate_pg(String s) {
		int res = 0;
        if (s == null) return res;
        int i = 0;
        int last = 0, last_result = 1;
        char last_operater = '+';
        int sign = 1;
        char[] arr = s.toCharArray();
        while (i < s.length()) {
            if (arr[i] == ' ') {
                i++;
                continue;
            }
            if (arr[i] == '+' || arr[i] == '-') {
                res += last_result * sign;
                sign = arr[i++] == '+' ? 1 : -1;
                last_result = 1;
                last_operater = '+';
            } else if (arr[i] == '/' || arr[i] == '*') {
                last_operater = arr[i];
                i++;
            }
            if (Character.isDigit(arr[i])) {
                last = 0;
                while (i < arr.length && Character.isDigit(arr[i])) {
                    last = last * 10 + arr[i++] - '0';
                }

                if (last_operater == '*') last_result *= last;
                else if (last_operater == '/') last_result /= last;
                else last_result = last;
            }
        }
        res += last_result * sign;
        return res;

	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicCalculatorII slt = new BasicCalculatorII();
		
		System.out.println(slt.calculate_1("3+2*2"));
		System.out.println(slt.calculate_1(" 3/2 "));
		System.out.println(slt.calculate_pg(" 3+5 / 2 "));
	}

}
