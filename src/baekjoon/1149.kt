package baekjoon

/**
 * 백준 #1149 - RGB거리
 *
 * https://www.acmicpc.net/problem/1149
 */

fun main() {
    B1149Solution().solution()
}

class B1149Solution {

    private lateinit var cost: Array<IntArray>
    private lateinit var dp: Array<IntArray>

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        cost = Array(n) { IntArray(3) }
        dp = Array(n) { IntArray(3) }

        repeat(n) {
            val (r, g, b) = readLine().split(" ").map { it.toInt() }
            cost[it][RED] = r
            cost[it][GREEN] = g
            cost[it][BLUE] = b
        }

        dp[0][RED] = cost[0][RED]
        dp[0][GREEN] = cost[0][GREEN]
        dp[0][BLUE] = cost[0][BLUE]

        println(Math.min(findColor(n - 1, RED), Math.min(findColor(n - 1, GREEN), findColor(n - 1, BLUE))))
    }

    private fun findColor(n: Int, color: Int): Int {
        if (dp[n][color] == 0) {
            when (color) {
                RED -> {
                    dp[n][RED] = Math.min(findColor(n - 1, GREEN), findColor(n - 1, BLUE)) + cost[n][RED]
                }
                GREEN -> {
                    dp[n][GREEN] = Math.min(findColor(n - 1, RED), findColor(n - 1, BLUE)) + cost[n][GREEN]
                }
                else -> {
                    dp[n][BLUE] = Math.min(findColor(n - 1, RED), findColor(n - 1, GREEN)) + cost[n][BLUE]
                }
            }
        }

        return dp[n][color]
    }

    companion object {
        const val RED = 0
        const val GREEN = 1
        const val BLUE = 2
    }
}