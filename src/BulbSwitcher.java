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

 题意：

 给定n，初始的时候灯都是灭的，让你进行进行开关灯。
 第一轮的时候把1的倍数反转（原来关就开，原来开就关），
 第二轮把2的倍数翻转，以此类推到第n轮。

 求最后灯打开的个数

 */
public class BulbSwitcher {

    /**
     *
     * 方法1：
     最简单的想法就是记录各个灯泡在每次变化后的状态，但是复杂度较高，为O(N*lgN)，不建议此种方法。
     方法2：
     我们可以很容易地想到，最后状态是on的灯泡代表的标号，说明只有奇数个约数。

     * 我们来分析下：对于素数，那么它仅有1和它本身，最后一定是关掉的。

     对一普通的，一定是关掉的，因子成对出现

     对于完全平方数，因为有一个倍数不成对出现，所以一定是打开的。比如4 => 1,4   | 2

     所以本题就是求1~n有几个完全平方数。

     那么，怎么求呢?从1开始到n，每个数测试一下？时间复杂度O(n)，太慢

     正确的是直接sqrt(n)，就可以算出来啦~
     */

    public class Solution {
        public int bulbSwitch(int n) {
            return (int)Math.sqrt(n);
        }
    }
}
