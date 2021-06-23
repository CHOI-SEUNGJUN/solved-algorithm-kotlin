package baekjoon

/**
 * 백준 #15989 - 1, 2, 3 더하기 4
 *
 * https://www.acmicpc.net/problem/15989
 */

fun main() {
    B15989Solution().solution()
}

class B15989Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val dp = Array(10001) { IntArray(3) }
        dp[1][0] = 1
        dp[1][1] = 0
        dp[1][2] = 0
        dp[2][0] = 1
        dp[2][1] = 1
        dp[2][2] = 0
        dp[3][0] = 1
        dp[3][1] = 1
        dp[3][2] = 1

        for (i in 4..10000) {
            for (j in 0..2) {
                for (k in 0..j) {
                    dp[i][j] += dp[i-j-1][k]
                }
            }
        }

        val t = readLine().toInt()
        val bw = System.`out`.bufferedWriter()
        repeat(t) {
            val n = readLine().toInt()
            bw.write("${dp[n][0] + dp[n][1] + dp[n][2]}\n")
        }
        bw.close()
    }
}