package programmers

/**
 * 프로그래머스 - 삼각 달팽이
 *
 * https://programmers.co.kr/learn/courses/30/lessons/68645
 */

fun main() {
    val answer = TriangleSnailSolution().solution(6)
    println(answer.joinToString(" "))
}

class TriangleSnailSolution {
    fun solution(n: Int): IntArray {
        val matrix = Array(n) { IntArray(it + 1) { -1 } }
        val max = (n * (n + 1)) / 2

        var i = 0
        var j = 0
        var current = 1

        matrix[0][0] = 1

        while (current < max) {
            // downside
            while (i + 1 < n && current < max && matrix[i + 1][j] < 0) {
                matrix[++i][j] = ++current
            }

            // straight
            while (j + 1 < n && current < max && matrix[i][j + 1] < 0) {
                matrix[i][++j] = ++current
            }

            // upside
            while (i - 1 > 0 && current < max && matrix[i - 1][j - 1] < 0) {
                matrix[--i][--j] = ++current
            }
        }

        val answer = mutableListOf<Int>()
        for (m in matrix) {
            answer.addAll(m.toTypedArray())
        }

        return answer.toIntArray()
    }
}