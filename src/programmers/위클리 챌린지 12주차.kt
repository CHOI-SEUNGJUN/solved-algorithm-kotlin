package programmers

/**
 * 프로그래머스 - 위클리 챌린지 12주차
 *
 * https://programmers.co.kr/learn/courses/30/lessons/87946
 */

fun main() {
    val answer = WeeklyChallenge12Solution().solution(
        80,
        arrayOf(
            intArrayOf(80, 20),
            intArrayOf(50, 40),
            intArrayOf(30, 10)
        )
    )
    println(answer)
}

class WeeklyChallenge12Solution {

    private lateinit var dungeonsVisited: BooleanArray
    private lateinit var dungeons: Array<IntArray>
    private var maxCnt = 0

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        this.dungeons = dungeons
        dungeonsVisited = BooleanArray(dungeons.size) { false }

        repeat(dungeons.size) {
            findMaxVisitingCount(k, it, 0)
        }

        return maxCnt
    }

    private fun findMaxVisitingCount(remain: Int, current: Int, experienceCnt: Int) {
        if (dungeons[current][0] > remain) return

        dungeonsVisited[current] = true
        maxCnt = Math.max(maxCnt, experienceCnt + 1)

        for (i in dungeons.indices) {
            if (dungeonsVisited[i].not()) {
                findMaxVisitingCount(remain - dungeons[current][1], i, experienceCnt + 1)
            }
        }

        dungeonsVisited[current] = false
    }
}