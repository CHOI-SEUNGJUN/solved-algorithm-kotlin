package baekjoon

/**
 * 백준 #10844 - 쉬운 계단 수
 *
 * https://www.acmicpc.net/problem/10844
 */

fun main() {
    B10844Solution().solution()
}

class B10844Solution {

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val base = 1_000_000_000

        val dp = Array(n + 1) { LongArray(11) { 0L } }

        for (i in 1..9) {
            dp[1][i] = 1
        }

        for (i in 2..n) {
            dp[i][0] = dp[i - 1][1]
            for (j in 1..9) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % base
            }
        }

        var answer = 0L
        for (i in 0..9) {
            answer += dp[n][i]
        }

        val br = System.`out`.bufferedWriter()
        br.write("${answer % base}")
        br.close()
    }
}