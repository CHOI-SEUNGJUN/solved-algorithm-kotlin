package programmers

/**
 * 프로그래머스 - 위장
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42578
 */

fun main() {
    val answer = CamouflageSolution().solution(
        arrayOf(
            arrayOf("yellowhat1", "gear"),
            arrayOf("yellowhat2", "eye"),
            arrayOf("yellowhat3", "gear")
        )
    )
    println(answer)
}

class CamouflageSolution {

    fun solution(clothes: Array<Array<String>>) = clothes
        .groupBy { it[1] }.values
        .map { it.size + 1 }
        .reduce(Int::times)
        .minus(1)
}