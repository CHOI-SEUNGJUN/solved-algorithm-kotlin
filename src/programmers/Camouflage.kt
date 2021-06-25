package programmers

/**
 * 프로그래머스 - 위장
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42578
 */

import java.util.*

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

    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 1

        val clothesMap: MutableMap<String, Int> = HashMap()

        for (i in clothes.indices) {
            clothesMap[clothes[i][1]] = clothesMap.getOrDefault(clothes[i][1], 0) + 1
        }

        for (i in clothesMap.values) {
            answer *= i + 1
        }

        return answer - 1
    }
}