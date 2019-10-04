import java.util.*;

/**
 * Created by cicean on 9/12/2018.
 * 751. IP to CIDR
 * Given a start IP address ip and a number of ips we need to cover n, return a representation of the range as a list (of smallest possible length) of CIDR blocks.

 A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length. For example: "123.45.67.89/20". That prefix length "20" represents the number of common prefix bits in the specified range.

 Example 1:
 Input: ip = "255.0.0.7", n = 10
 Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 Explanation:
 The initial ip address, when converted to binary, looks like this (spaces added for clarity):
 255.0.0.7 -> 11111111 00000000 00000000 00000111
 The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
 ie. just this one address.

 The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
 255.0.0.8 -> 11111111 00000000 00000000 00001000
 Addresses with common prefix of 29 bits are:
 11111111 00000000 00000000 00001000
 11111111 00000000 00000000 00001001
 11111111 00000000 00000000 00001010
 11111111 00000000 00000000 00001011
 11111111 00000000 00000000 00001100
 11111111 00000000 00000000 00001101
 11111111 00000000 00000000 00001110
 11111111 00000000 00000000 00001111

 The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
 ie. just 11111111 00000000 00000000 00010000.

 In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .

 There were other representations, such as:
 ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
 but our answer was the shortest possible.

 Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
 because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100
 that are outside the specified range.
 Note:
 ip will be a valid IPv4 address.
 Every implied address ip + x (for x < n) will be a valid IPv4 address.
 n will be an integer in the range [1, 1000].
 */
public class IPtoCIDR {

    /**
     * Approach #1: Direct [Accepted]
     Intuition

     This problem is about performing the steps directly as written. The tricky part is managing the bit manipulations involved.

     Let's ask the question: for a number n of ip addresses desired, and the starting address ip of that range, what is the CIDR block representing the most ip addresses in that range starting at ip? Evidently, this greedy approach will work, and we can keep repeating this until we are done, so let's just focus on creating one largest block.

     Algorithm

     We'll need to be able to convert ip addresses back and forth to integers (long). We can do this with some basic manipulations - see the code for more details.

     Then, with an ip address like 255.0.0.24 converted to start, it ends in the binary 00011000. There are some cases. If n >= 8, then we should use the entire block 255.0.0.24/29. Otherwise, we can only take a number of addresses equal to the largest power of 2 less than or equal to n.

     In a more general setting, we use the bit lengths of both n and start & -start (the lowest bit of start) to compute the mask which represents 2^{32 - \text{mask}}2
     32?mask
     ip addresses. Then, we adjust start and n appropriately.

     In Java and C++, we should be careful to use long data types to represent the converted ip addresses, since the number could exceed 2^{31}2
     31
     .
     * @param ip
     * @param n
     * @return
     */
    public List<String> ipToCIDR(String ip, int n) {
        long start = ipToLong(ip);
        List<String> ans = new ArrayList();
        while (n > 0) {
            int mask = Math.max(33 - bitLength(Long.lowestOneBit(start)),
                    33 - bitLength(n));
            ans.add(longToIP(start) + "/" + mask);
            start += 1 << (32 - mask);
            n -= 1 << (32 - mask);
        }
        return ans;
    }
    private long ipToLong(String ip) {
        long ans = 0;
        for (String x: ip.split("\\.")) {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }
    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
                x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }
    private int bitLength(long x) {
        if (x == 0) return 1;
        int ans = 0;
        while (x > 0) {
            x >>= 1;
            ans++;
        }
        return ans;
    }
}
