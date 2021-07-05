package programmers

/**
 * 프로그래머스 - 순위
 *
 * https://programmers.co.kr/learn/courses/30/lessons/49191
 */

fun main() {
    val answer = RankSolution().solution(
        5, arrayOf(
            intArrayOf(4,3),
            intArrayOf(4,2),
            intArrayOf(3,2),
            intArrayOf(1,2),
            intArrayOf(2,5)
        )
    )
    println(answer)
}

class RankSolution {
    fun solution(n: Int, results: Array<IntArray>): Int {
        var answer = 0
        val gamers = Array(n + 1) { IntArray(n + 1) { INF } }

        for (i in gamers.indices) {
            gamers[i][i] = 0
            gamers[i][0] = 0
        }

        for (result in results) {
            val winner = result[0]
            val loser = result[1]
            gamers[winner][loser] = 1
        }

        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (gamers[i][j] > gamers[i][k] + gamers[k][j]) {
                        gamers[i][j] = gamers[i][k] + gamers[k][j]
                    }
                }
            }
        }

        for (i in 1..n) {
            var canFind = true
            for (j in 1..n) {
                if (i == j) continue
                if (gamers[i][j] == INF && gamers[j][i] == INF) {
                    canFind = false
                    break
                }
            }
            if (canFind) answer++
        }

        return answer
    }

    companion object {
        private const val INF = 1_000_000_000
    }
}