package programmers

/**
 * 프로그래머스 - 카펫
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42842
 */

fun main() {
    val answer = CarpetSolution().solution(10, 2)
    println(answer.joinToString(" "))
}

class CarpetSolution {
    fun solution(brown: Int, yellow: Int): IntArray {
        val answer = IntArray(2)
        val entire = brown + yellow

        for (vertical in 1..entire) {
            if (entire % vertical != 0) continue

            val horizontal = entire / vertical

            if (horizontal > vertical) continue

            if ((vertical - 2) * (horizontal - 2) == yellow) {
                answer[0] = vertical
                answer[1] = horizontal
                return answer
            }
        }
        return intArrayOf()
    }
}