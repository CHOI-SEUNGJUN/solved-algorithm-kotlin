package baekjoon

/**
 * 백준 #1495 - 기타리스트
 *
 * https://www.acmicpc.net/problem/1495
 */

fun main() {
    B1495Solution().solution()
}

class B1495Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, s, m) = readLine().split(" ").map { it.toInt() }

        val v = readLine().split(" ").map { it.toInt() }.toIntArray()

        val dp = Array(n+1) { BooleanArray(m+1) { false } }

        dp[0][s] = true
        for (i in 0 until n) {
            for (j in 0..m) {
                if (dp[i][j]) {
                    if (j + v[i] <= m) {
                        dp[i+1][j + v[i]] = true
                    }
                    if (j - v[i] >= 0) {
                        dp[i+1][j - v[i]] = true
                    }
                }
            }
        }

        var result = -1
        for (i in m downTo 0) {
            if (dp[n][i]) {
                result = i
                break
            }
        }
        println(result)
    }
}