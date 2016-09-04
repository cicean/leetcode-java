/**
 * Created by cicean on 9/2/2016.
 * 385. Mini Parser  QuestionEditorial Solution  My Submissions
 Total Accepted: 3380 Total Submissions: 12264 Difficulty: Medium
 Given a nested list of integers represented as a string, implement a parser to deserialize it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Note: You may assume that the string is well-formed:

 String is non-empty.
 String does not contain white spaces.
 String contains only digits 0-9, [, - ,, ].
 Example 1:

 Given s = "324",

 You should return a NestedInteger object which contains a single integer 324.
 Example 2:

 Given s = "[123,[456,[789]]]",

 Return a NestedInteger object containing a nested list with 2 elements:

 1. An integer containing value 123.
 2. A nested list containing two elements:
 i.  An integer containing value 456.
 ii. A nested list with one element:
 a. An integer containing value 789.
 Hide Company Tags Airbnb
 Hide Tags Stack String
 Hide Similar Problems (M) Flatten Nested List Iterator
 给定一个字符串，要求把它解析成NestedInteger 类型
 */
public class MiniParser {

    /**
     * 由于没有空格，所以第一个字母不是'[‘的说明只有一个数字。直接返回NestedInteger(int(s))

     接下来，用一个栈来维护，对于左括号[的，当前的NestedInteger 进栈，对于右括号，当前数字放入NestedInteger 栈不为空则把栈顶的NestedInteger添加当前的NestedInteger，并且出栈。

     此外，注意‘-’的情况
     */

    /**
     * 我们实现一个迷你解析器用来把一个字符串解析成NestInteger类，
     * 关于这个嵌套链表类的题我们之前做过三道，
     * Nested List Weight Sum II，Flatten Nested List Iterator，
     * 和Nested List Weight Sum。应该对这个类并不陌生了，
     * 我们可以先用递归来做，
     * 思路是，首先判断s是否为空，为空直接返回，不为空的话看首字符是否为'['，
     * 是的话说明s为一个整数，我们直接返回结果。
     * 如果首字符是'['，且s长度小于等于2，说明没有内容，直接返回结果。
     * 反之如果s长度大于2，我们从i=1开始遍历，
     * 我们需要一个变量start来记录某一层的其实位置，
     * 用cnt来记录跟其实位置是否为同一深度，cnt=0表示同一深度，
     * 由于中间每段都是由逗号隔开，所以当我们判断当cnt为0，且当前字符是逗号或者已经到字符串末尾了，
     * 我们把start到当前位置之间的字符串取出来递归调用函数，把返回结果加入res中，
     * 然后start更新为i+1。如果遇到'['，计数器cnt自增1，若遇到']'，计数器cnt自减1。
     */

    //recursive
    public class Solution {
        public int helper(char[] cc, int idx, NestedInteger res){
            NestedInteger ret = null; // for storing the list element
            Integer num=null; // for storing the number element
            int sign = 1; // sign of the number
            while ( idx < cc.length && cc[idx] != ']') { // parsing between [ and ]
                char c = cc[idx++];
                if ( c == '[' ) { // start parsing a list by calling helper function
                    ret = new NestedInteger();
                    idx = helper(cc, idx, ret); // idx is the next index after ]
                } else if ( c == ',' ){  // time to add a element to the list
                    if (ret != null) { // it is a List element
                        res.add(ret);
                    } else { // it is a integer or null
                        if (num != null) res.add(new NestedInteger( sign*num ) );
                    }
                    ret = null;  // reset
                    num = null;
                    sign = 1;
                } else if ( c == '-' ){ // sign of the number
                    sign = -1;
                } else { // calculate the number
                    num = num == null ? (int)(c-'0'): (num*10) + (int)(c-'0');
                }
            }
            if (ret != null) { // add the last element or the element before ]
                res.add(ret);
            } else {
                if (num != null) res.add(new NestedInteger(sign*num));
            }
            return idx+1; // very important!!! return the next index
        }

        public NestedInteger deserialize(String s) {
            NestedInteger ret = new NestedInteger(); // a dummy root element
            helper(s.toCharArray(), 0, ret);
            return ret.getList().get(0); // return a element in the dummy root
        }
    }

    /**
     * 我们也可以使用迭代的方法来做，这样就需要使用栈来辅助，变量start记录起始位置，我们遍历字符串，如果遇到'['，我们给栈中加上一个空的NestInteger，如果遇到的字符数逗号或者']'，如果i>start，那么我们给栈顶元素调用add来新加一个NestInteger，初始化参数传入start到i之间的子字符串转为的整数，然后更新start=i+1，当遇到的']'时，如果此时栈中元素多于1个，那么我们将栈顶元素取出，加入新的栈顶元素中通过调用add函数
     */
    //iterative
    public NestedInteger deserialize(String s) {
        if (s.isEmpty())
            return null;
        if (s.charAt(0) != '[') // ERROR: special case
            return new NestedInteger(Integer.valueOf(s));

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger curr = null;
        int l = 0; // l shall point to the start of a number substring;
        // r shall point to the end+1 of a number substring
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            if (ch == '[') {
                if (curr != null) {
                    stack.push(curr);
                }
                curr = new NestedInteger();
                l = r+1;
            } else if (ch == ']') {
                String num = s.substring(l, r);
                if (!num.isEmpty())
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                if (!stack.isEmpty()) {
                    NestedInteger pop = stack.pop();
                    pop.add(curr);
                    curr = pop;
                }
                l = r+1;
            } else if (ch == ',') {
                if (s.charAt(r-1) != ']') {
                    String num = s.substring(l, r);
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                }
                l = r+1;
            }
        }

        return curr;
    }

}
