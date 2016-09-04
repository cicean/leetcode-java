/**
 * Created by cicean on 8/30/2016.
 * 338. Counting Bits  QuestionEditorial Solution  My Submissions
 Total Accepted: 41901
 Total Submissions: 71849
 Difficulty: Medium
 Given a non negative integer number num. For every numbers i in the range 0 �� i �� num calculate the number of 1's in their binary representation and return them as an array.

 Example:
 For num = 5 you should return [0,1,1,2,1,2].

 Follow up:

 It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 Space complexity should be O(n).
 Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 Hint:

 You should make use of what you have produced already.
 Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
 Or does the odd/even status of the number help you in calculating the number of 1s?
 Credits:
 Special thanks to @ syedee for adding this problem and creating all test cases.

 Hide Tags Dynamic Programming Bit Manipulation
 Hide Similar Problems (E) Number of 1 Bits

 */
public class CountingBits {

    /**
     * ˼·��

     ����һ

     ��һ�룬��һ����Ϊ2�������ݵ�ʱ��1�ĸ���Ϊ1������2��10) ��4(100)��8(1000)

     ����֮�����ǰһ�����е���+1 ���� 9(1001) = 1(1) + 8 (1) = 2

     ���ǰ�һ�����ֽ�ΪС���������2�������� + x
     */

    public class Solution {
        public int[] countBits(int num) {
            int[] res = new int[num+1];
            int pow2 = 1,before =1;
            for(int i=1;i<=num;i++){
                if (i == pow2){
                    before = res[i] = 1;
                    pow2 <<= 1;
                }
                else{
                    res[i] = res[before] + 1;
                    before += 1;
                }
            }
            return res;
        }
    }

    /**
     * �������룬һ���� * 2 ���ǰ����Ķ�����ȫ������һλ��Ҳ����˵ 1�ĸ�������ȵġ�

     ��ô���ǿ��������������������

     res[i /2] Ȼ�󿴿����λ�Ƿ�Ϊ1���ɣ�����*2һ����ż������߱���15��14����2����7������15ʱͨ��7����һλ����+1�õ���14����ֱ�����ƣ�

     ����res[i] = res[i >>1] + (i&1)

     dp
     */
    public class Solution2 {
        public int[] countBits(int num) {
            int[] res = new int[num+1];
            for(int i=1;i<=num;i++){
                res[i] = res[i >> 1] + (i & 1);
            }
            return res;
        }
    }
}
