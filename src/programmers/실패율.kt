package programmers

/**
 * 프로그래머스 - 실패율
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42889
 */

fun main() {
    val answer = FailureRateSolution().solution(5, intArrayOf(2, 1, 2, 6, 2, 4, 3, 3))
    println(answer.joinToString(" "))
}

class FailureRateSolution {
    fun solution(N: Int, stages: IntArray): IntArray {

        val rateList = mutableListOf<Stage>()

        for (i in 1..N) {
            val nonPasser = stages.filter { it == i }.count()
            val reachGamer = stages.filter { it >= i }.count()
            if (reachGamer == 0) {
                rateList.add(Stage(i, 0.0))
            } else {
                val rate = nonPasser / reachGamer.toDouble()
                rateList.add(Stage(i, rate))
            }
        }

        rateList.sortWith(
            Comparator { o1, o2 ->
                if (o1.rate == o2.rate) {
                    o1.level - o2.level
                } else {
                    if (o1.rate > o2.rate) {
                        -1
                    } else {
                        1
                    }
                }
        })

        return rateList
            .map { it.level }
            .toIntArray()
    }

    data class Stage(
        val level: Int,
        val rate: Double
    )
}