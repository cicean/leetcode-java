/**
 * Created by cicean on 8/29/2016.
 * 319. Bulb Switcher  QuestionEditorial Solution  My Submissions
 Total Accepted: 26683 Total Submissions: 64485 Difficulty: Medium
 There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

 Example:

 Given n = 3.

 At first, the three bulbs are [off, off, off].
 After first round, the three bulbs are [on, on, on].
 After second round, the three bulbs are [on, off, on].
 After third round, the three bulbs are [on, off, off].

 So you should return 1, because there is only one bulb is on.
 Hide Tags Math Brainteaser

 ���⣺

 ����n����ʼ��ʱ��ƶ�����ģ�������н��п��صơ�
 ��һ�ֵ�ʱ���1�ı�����ת��ԭ���ؾͿ���ԭ�����͹أ���
 �ڶ��ְ�2�ı�����ת���Դ����Ƶ���n�֡�

 �����ƴ򿪵ĸ���

 */
public class BulbSwitcher {

    /**
     *
     * ����1��
     ��򵥵��뷨���Ǽ�¼����������ÿ�α仯���״̬�����Ǹ��ӶȽϸߣ�ΪO(N*lgN)����������ַ�����
     ����2��
     ���ǿ��Ժ����׵��뵽�����״̬��on�ĵ��ݴ���ı�ţ�˵��ֻ��������Լ����

     * �����������£�������������ô������1�����������һ���ǹص��ġ�

     ��һ��ͨ�ģ�һ���ǹص��ģ����ӳɶԳ���

     ������ȫƽ��������Ϊ��һ���������ɶԳ��֣�����һ���Ǵ򿪵ġ�����4 => 1,4   | 2

     ���Ա��������1~n�м�����ȫƽ������

     ��ô����ô����?��1��ʼ��n��ÿ��������һ�£�ʱ�临�Ӷ�O(n)��̫��

     ��ȷ����ֱ��sqrt(n)���Ϳ����������~
     */

    public class Solution {
        public int bulbSwitch(int n) {
            return (int)Math.sqrt(n);
        }
    }
}
