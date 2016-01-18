package com.bluecatcode.learning.codility.lessons.nr1;

import static java.lang.Math.abs;

/**
 * expected worst-case time complexity is O(log(N))
 * expected worst-case space complexity is O(1)
 */
public class Solution {

    private static final int BASE_2 = 2;

    public int solution(int number) {
        System.out.println("#" + Integer.toBinaryString(number));

        int n = abs(number);

        int maxCount = 0;
        int zeroCount = 0;
        boolean wasOneEncountered = false;
        do {
            int digit = n % BASE_2;
            if (digit == 0) {
                if (wasOneEncountered) {
                    zeroCount++;
                }
            } else {
                wasOneEncountered = true;
                maxCount = max(maxCount, zeroCount);
                zeroCount = 0;
            }
        } while ((n /= BASE_2) > 0);

        return maxCount;
    }

    public static int max(int first, int second) {
        return first > second ? first: second;
    }
}