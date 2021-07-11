package programmers

/**
 * 프로그래머스 - 튜플
 *
 * https://programmers.co.kr/learn/courses/30/lessons/64065
 */

fun main() {
    val answer = TupleSolution().solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")
    println(answer.joinToString(", "))
}

class TupleSolution {
    fun solution(s: String): IntArray {
        return s.substring(2 until s.length-2)
            .split("},{")
            .asSequence()
            .map { it.split(",").map { num -> num.toInt() } }
            .toList()
            .sortedBy { it.size }
            .fold(setOf<Int>()) { acc, list -> acc.union(list) }
            .toIntArray()
    }
}