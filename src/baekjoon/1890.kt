package baekjoon

/**
 * 백준 #1890 - 점프
 *
 * https://www.acmicpc.net/problem/1890
 */

fun main() {
    B1890Solution().solution()
}

class B1890Solution {

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val map = Array(n) { IntArray(n) }
        val dp = Array(n) { LongArray(n) { 0 } }

        repeat(n) { i ->
            map[i] = readLine()
                .split(" ")
                .map { it.toInt() }
                .toIntArray()
        }

        dp[0][0] = 1

        for (i in map.indices) {
            for (j in map.indices) {
                if (dp[i][j] == 0L || (i == n - 1 && j == n - 1)) continue

                val jumpable = map[i][j]
                val dx = i + jumpable
                val ry = j + jumpable

                if (dx in map.indices) dp[dx][j] = dp[dx][j] + dp[i][j]
                if (ry in map.indices) dp[i][ry] = dp[i][ry] + dp[i][j]
            }
        }

        val br = System.`out`.bufferedWriter()
        br.write("${dp[n-1][n-1]}")
        br.close()
    }
}