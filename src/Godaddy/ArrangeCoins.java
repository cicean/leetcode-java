package Godaddy;

/**
 * Created by cicean on 10/14/2016.
 */
public class ArrangeCoins {

	public int[] arrangeCoins(long[] coints) {

		int[] res = new int[coints.length];

		for (int i = 0; i < coints.length; i++) {
			double m = (Math.sqrt(coints[i]* 8 + 1) - 1) / 2;
            coints[i] = new Double(m).intValue();
		}

		return res;

	}

	public static void main(String[] args) {
		Long long1 = new Long(1);
		Long long2 = new Long(3);
		Long long4 = new Long(11);
		Long long7 = new Long(35);
		Long long13 = new Long(92);
		Long[] coints = new Long[]{long1, long2, long4, long7, long13};
		
		ArrangeCoins slt = new ArrangeCoins();
		
		for (int i : slt.arrangeCoins(coints)) {
            System.out.print(i  + ", ");
        }
		
	}
}
