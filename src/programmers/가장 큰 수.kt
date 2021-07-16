package programmers

/**
 * 프로그래머스 - 가장 큰 수
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42746
 */

fun main() {
    val answer = BigNumberSolution().solution(intArrayOf(3, 30, 34, 5, 9))
    println(answer)
}

class BigNumberSolution {
    fun solution(numbers: IntArray): String {
        val transformed = numbers
            .map { it.toString() }
            .sortedWith(Comparator { o1, o2 ->
                (o2 + o1).compareTo(o1 + o2)
            })

        return if (transformed.first() == "0") "0"
        else transformed.joinToString("")
    }
}