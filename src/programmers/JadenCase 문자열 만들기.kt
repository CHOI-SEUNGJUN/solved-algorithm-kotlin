package programmers

/**
 * 프로그래머스 - JadenCase 문자열 만들기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/12951
 */

fun main() {
    val answer = JadenCaseSolution().solution("3 3 3 3 3 3 3")
    println(answer)
}

class JadenCaseSolution {
    fun solution(s: String): String = s.split(" ")
            .map { if (it.isNotBlank() && it[0].isLetter()) it[0].uppercase() + it.substring(1).lowercase() else it.lowercase() }
            .joinToString(" ")
}