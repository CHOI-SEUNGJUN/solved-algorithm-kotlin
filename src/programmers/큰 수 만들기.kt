package programmers

/**
 * 프로그래머스 - 큰 수 만들기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42883
 */

fun main() {
    val answer = BiggestNumberSolution().solution(
        "4177252841", 4
    )
    println(answer)
}

class BiggestNumberSolution {
    fun solution(number: String, k: Int): String {
        val sb = StringBuilder()
        var index = 0
        var max = 0
        for (i in 0 until number.length - k) {
            max = 0
            for (j in index..(k + i)) {
                if (max < number[j] - '0') {
                    max = number[j] - '0'
                    index = j + 1
                }
            }
            sb.append(max)
        }
        return sb.toString()
    }
}