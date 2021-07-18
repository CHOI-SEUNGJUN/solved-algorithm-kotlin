package programmers

/**
 * 프로그래머스 - 예상 대진표
 *
 * https://programmers.co.kr/learn/courses/30/lessons/12985
 */

fun main() {
    val answer = EstimatedTournament().solution(
        n = 8,
        a = 4,
        b = 5
    )
    println(answer)
}

class EstimatedTournament {
    fun solution(n: Int, a: Int, b: Int): Int {
        var A = a
        var B = b

        var answer = 1

        while (isNotRoundMatch(A, B)) {
            A = getNextRoundNumber(A)
            B = getNextRoundNumber(B)
            answer++
        }

        return answer
    }

    fun isRoundMatch(a: Int, b: Int)
        = Math.abs(a - b) == 1 && getNextRoundNumber(a) == getNextRoundNumber(b)

    fun isNotRoundMatch(a: Int, b: Int) = !isRoundMatch(a, b)

    fun getNextRoundNumber(number: Int)
            = if (number % 2 == 0) number / 2 else (number / 2) + 1
}