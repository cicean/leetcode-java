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
 ����һ���ַ�����Ҫ�����������NestedInteger ����
 */
public class MiniParser {

    /**
     * ����û�пո����Ե�һ����ĸ����'[����˵��ֻ��һ�����֡�ֱ�ӷ���NestedInteger(int(s))

     ����������һ��ջ��ά��������������[�ģ���ǰ��NestedInteger ��ջ�����������ţ���ǰ���ַ���NestedInteger ջ��Ϊ�����ջ����NestedInteger��ӵ�ǰ��NestedInteger�����ҳ�ջ��

     ���⣬ע�⡮-�������
     */

    /**
     * ����ʵ��һ�����������������һ���ַ���������NestInteger�࣬
     * �������Ƕ���������������֮ǰ����������
     * Nested List Weight Sum II��Flatten Nested List Iterator��
     * ��Nested List Weight Sum��Ӧ�ö�����ಢ��İ���ˣ�
     * ���ǿ������õݹ�������
     * ˼·�ǣ������ж�s�Ƿ�Ϊ�գ�Ϊ��ֱ�ӷ��أ���Ϊ�յĻ������ַ��Ƿ�Ϊ'['��
     * �ǵĻ�˵��sΪһ������������ֱ�ӷ��ؽ����
     * ������ַ���'['����s����С�ڵ���2��˵��û�����ݣ�ֱ�ӷ��ؽ����
     * ��֮���s���ȴ���2�����Ǵ�i=1��ʼ������
     * ������Ҫһ������start����¼ĳһ�����ʵλ�ã�
     * ��cnt����¼����ʵλ���Ƿ�Ϊͬһ��ȣ�cnt=0��ʾͬһ��ȣ�
     * �����м�ÿ�ζ����ɶ��Ÿ��������Ե������жϵ�cntΪ0���ҵ�ǰ�ַ��Ƕ��Ż����Ѿ����ַ���ĩβ�ˣ�
     * ���ǰ�start����ǰλ��֮����ַ���ȡ�����ݹ���ú������ѷ��ؽ������res�У�
     * Ȼ��start����Ϊi+1���������'['��������cnt����1��������']'��������cnt�Լ�1��
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
     * ����Ҳ����ʹ�õ����ķ�����������������Ҫʹ��ջ������������start��¼��ʼλ�ã����Ǳ����ַ������������'['�����Ǹ�ջ�м���һ���յ�NestInteger������������ַ������Ż���']'�����i>start����ô���Ǹ�ջ��Ԫ�ص���add���¼�һ��NestInteger����ʼ����������start��i֮������ַ���תΪ��������Ȼ�����start=i+1����������']'ʱ�������ʱջ��Ԫ�ض���1������ô���ǽ�ջ��Ԫ��ȡ���������µ�ջ��Ԫ����ͨ������add����
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
