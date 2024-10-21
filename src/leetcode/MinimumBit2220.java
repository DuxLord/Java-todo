package leetcode;

public class MinimumBit2220 {
    public static void main(String[] args) {
        int flips = minBitFlips(5, 20);
    }

    public static int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        System.out.println(xor);
        int count = 0;

        while (xor != 0) {
            System.out.println("i"+xor);
            count += xor & 1;
            xor >>= 1;
            System.out.println("f"+xor);
        }
        return count;
    }
}
