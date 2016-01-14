package com.bluecatcode.learning.codility.lessons.nr1;
import java.util.stream.IntStream;

import static org.valid4j.Assertive.ensure;
import static org.valid4j.Assertive.require;

/**
 * expected worst-case time complexity is O(log(N))
 * expected worst-case space complexity is O(1)
 */
public class Solution {

    public int solution(int number) {
        require(number > 0);

        IntStream chars = Integer.toBinaryString(number).chars();
        int count = (int) chars.filter(v -> v == '0').count();

        ensure(count >= 0);
        return count;
    }

}