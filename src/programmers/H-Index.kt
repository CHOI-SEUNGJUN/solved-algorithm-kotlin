package programmers

/**
 * 프로그래머스 - H-Index
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42747
 */

fun main() {
    val answer = HIndexSolution().solution(intArrayOf(3,0,6,1,5))
    println(answer)
}

class HIndexSolution {
    fun solution(citations: IntArray): Int {
        citations.sort()
        val size = citations.size
        var hIndex = 0
        for ((idx, c) in citations.withIndex()) {
            if (size - idx <= c) {
                hIndex = size - idx
                break
            }
        }
        return hIndex
    }
}