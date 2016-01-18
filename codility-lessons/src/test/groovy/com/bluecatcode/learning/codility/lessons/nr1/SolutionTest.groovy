package com.bluecatcode.learning.codility.lessons.nr1

import spock.lang.Specification
import spock.lang.Unroll

class SolutionTest extends Specification {

    @Unroll("input: #input, result: #result")
    def "Solution"() {
        given:
        def solution = new Solution()

        expect:
        result.equals(solution.solution(input))

        where:
        input | result
        9     | 2
        529   | 4
        20    | 1
        15    | 0
        16    | 0
        1041  | 5
    }
}
