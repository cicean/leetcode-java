/**
 * You are playing the following Nim Game with your friend: There is a heap of
 * stones on the table, each time one of you take turns to remove 1 to 3 stones.
 * The one who removes the last stone will be the winner. You will take the
 * first turn to remove the stones.
 * 
 * Both of you are very clever and have optimal strategies for the game. Write a
 * function to determine whether you can win the game given the number of stones
 * in the heap.
 * 
 * For example, if there are 4 stones in the heap, then you will never win the
 * game: no matter 1, 2, or 3 stones you remove, the last stone will always be
 * removed by your friend.
 * 
 * Hint:
 * 
 * If there are 5 stones in the heap, could you figure out a way to remove the
 * stones such that you will always be the winner? Credits: Special thanks to
 * @jianchao.li.fighter for adding this problem and creating all test cases.
 * 
 * @author cicean
 * 
 * Theorem: The first one who got the number that is multiple of 4 (i.e. n % 4 == 0) will lost, otherwise he/she will win.
Proof:

the base case: when n = 4, as suggested by the hint from the problem, no matter which number that that first player, the second player would always be able to pick the remaining number.

For 1* 4 < n < 2 * 4, (n = 5, 6, 7), the first player can reduce the initial number into 4 accordingly, which will leave the death number 4 to the second player. i.e. The numbers 5, 6, 7 are winning numbers for any player who got it first.

Now to the beginning of the next cycle, n = 8, no matter which number that the first player picks, it would always leave the winning numbers (5, 6, 7) to the second player. Therefore, 8 % 4 == 0, again is a death number.

Following the second case, for numbers between (2*4 = 8) and (3*4=12), which are 9, 10, 11, are winning numbers for the first player again, because the first player can always reduce the number into the death number 8.

Following the above theorem and proof, the solution could not be simpler:
 *
 */
public class NimGame {
	
	public boolean canWinNim(int n) {
	    if(n <= 0)
	        throw new IllegalArgumentException();
	    if(n < 4)
	        return true;
	    boolean[] res = new boolean[n + 1];
	    res[0] = true;
	    res[1] = true;
	    res[2] = true;
	    res[3] = true;
	    for(int i = 4 ; i <= n ; i++)
	        res[i] = !(res[i - 1] && res[i - 2] && res[i - 3]);
	    return res[n];
	}

	/**
	 * 博弈论中极为经典的尼姆游戏。有总数为n的石头，每个人可以拿1~m个石头，两个人交替拿，拿到最后一个的人获胜。究竟是先手有利，还是后手有利？

	 1个石子，先手全部拿走；
	 2个石子，先手全部拿走；
	 3个石子，先手全部拿走；
	 4个石子，后手面对的是先手的第1，2，3情况，后手必胜；
	 5个石子，先手拿走1个让后手面对第4种情况，后手必败；
	 6个石子，先手拿走2个让后手面对第4种情况，后手必败；
	 ……
	 容易看出来，只有当出现了4的倍数，先手无可奈何，其余情况先手都可以获胜。
	 （石子数量为4的倍数）后手的获胜策略十分简单，每次取石子的数量，与上一次先手取石子的数量和为4即可；
	 （石子数量不为4的倍数）先手的获胜策略也十分简单，每次都令取之后剩余的石子数量为4的倍数（4*0=0，直接拿光），他就处于后手的位置上，利用上一行的策略获胜。
	 * @param n
	 * @return
     */
	public boolean canWinNim_1(int n) {

		if(n <= 0)
		    throw new IllegalArgumentException();
		return !(n % 4 == 0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
