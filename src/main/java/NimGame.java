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
	 * �������м�Ϊ�������ķ��Ϸ��������Ϊn��ʯͷ��ÿ���˿�����1~m��ʯͷ�������˽����ã��õ����һ�����˻�ʤ���������������������Ǻ���������

	 1��ʯ�ӣ�����ȫ�����ߣ�
	 2��ʯ�ӣ�����ȫ�����ߣ�
	 3��ʯ�ӣ�����ȫ�����ߣ�
	 4��ʯ�ӣ�������Ե������ֵĵ�1��2��3��������ֱ�ʤ��
	 5��ʯ�ӣ���������1���ú�����Ե�4����������ֱذܣ�
	 6��ʯ�ӣ���������2���ú�����Ե�4����������ֱذܣ�
	 ����
	 ���׿�������ֻ�е�������4�ı����������޿��κΣ�����������ֶ����Ի�ʤ��
	 ��ʯ������Ϊ4�ı��������ֵĻ�ʤ����ʮ�ּ򵥣�ÿ��ȡʯ�ӵ�����������һ������ȡʯ�ӵ�������Ϊ4���ɣ�
	 ��ʯ��������Ϊ4�ı��������ֵĻ�ʤ����Ҳʮ�ּ򵥣�ÿ�ζ���ȡ֮��ʣ���ʯ������Ϊ4�ı�����4*0=0��ֱ���ù⣩�����ʹ��ں��ֵ�λ���ϣ�������һ�еĲ��Ի�ʤ��
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
