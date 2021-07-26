package programmers

/**
 * 프로그래머스 - 체육복
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42862
 */

fun main() {
    val answer = GymSuitSolution().solution(
        n = 5,
        lost = intArrayOf(2,4),
        reserve = intArrayOf(3,5)
    )
    // answer : 5
    println(answer)
}

class GymSuitSolution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = n - lost.size

        for (i in lost.indices) {
            for (j in reserve.indices) {
                if (lost[i] == reserve[j]) {
                    answer++
                    lost[i] = -1
                    reserve[j] = -1
                    break
                }
            }
        }

        for (i in lost.indices) {
            for (j in reserve.indices) {
                if (lost[i] == reserve[j] + 1 || lost[i] == reserve[j] - 1) {
                    answer++
                    reserve[j] = -1
                    break
                }
            }
        }

        return answer
    }
}