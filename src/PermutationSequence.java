import java.util.Scanner;

/*
 60	Permutation Sequence	22.8%	Medium
 Problem:    Permutation Sequence
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/permutation-sequence/
 Notes:
 The set [1,2,3,...,n] contains a total of n! unique permutations.
 By listing and labeling all of the permutations in order,
 We get the following sequence (ie, for n = 3):
 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 Given n and k, return the kth permutation sequence.
 Note: Given n will be between 1 and 9 inclusive.
 Solution: 1. Brute!
           2. combinatorial mathematics.
 */

public class PermutationSequence {
	public void nextPermutation(char[] num) {
        int last = num.length - 1;
        int i = last;
        while (i > 0 && num[i - 1] >= num [i]) --i;
        for (int l = i, r = last; l < r; ++l, --r) {
            num[l] = (char) (num[l] ^ num[r]);
            num[r] = (char) (num[l] ^ num[r]);
            num[l] = (char) (num[l] ^ num[r]);
        }
        if (i == 0) {
            return;
        }
        int j = i;
        while (j <= last && num[i-1] >= num[j]) ++j;
        num[i-1] = (char) (num[i-1] ^ num[j]);
        num[j] = (char) (num[i-1] ^ num[j]);
        num[i-1] = (char) (num[i-1] ^ num[j]);
    }
    public String getPermutation_1(int n, int k) {
        char[] num = new char[n];
        for (int i = 0; i < n; ++i) num[i] = (char) (i + '1');
        System.out.println(String.valueOf(num));
        while (--k != 0) {
            nextPermutation(num);
        }
        return String.valueOf(num);
    }
    public String getPermutation_2(int n, int k) {
        StringBuffer sb = new StringBuffer();
        StringBuffer res = new StringBuffer();
        int total = 1;
        for (int i = 1; i <= n; ++i) {
            total = total * i;
            sb.append(i);
        }
        k--;
        while(n != 0) {
            total = total / n;
            int idx = k / total;
            res.append(sb.charAt(idx));
            k = k % total;
            sb.deleteCharAt(idx);
            n--;
        }
        return res.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PermutationSequence slt = new PermutationSequence();
		Scanner sc =new Scanner(System.in);
		System.out.println("Please inpute a num");
		int n= sc.nextInt();
		System.out.println("Please inpute length of your Permutation Sequence ");
		int k = sc.nextInt();
		String s = slt.getPermutation_1(n, k);
		System.out.print(s);
		
	}

}
