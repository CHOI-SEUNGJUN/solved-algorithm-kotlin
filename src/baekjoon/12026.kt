package baekjoon

/**
 * 백준 #12026 - BOJ 거리
 *
 * https://www.acmicpc.net/problem/12026
 */

fun main() {
    B12026Solution().solution()
}

class B12026Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val max = 1_000_000_000
        val n = readLine().toInt()
        val chars = readLine().toMutableList()
            .map {
                when (it) {
                    'B' -> 0
                    'O' -> 1
                    'J' -> 2
                    else -> 0
                }
            }

        val dp = IntArray(n) { max }
        dp[0] = 0

        for (i in 0 until n) {
            for (j in i+1 until n) {
                if ((chars[i] + 1) % 3 == chars[j]) {
                    dp[j] = Math.min(dp[j], dp[i] + (j - i) * (j - i))
                }
            }
        }

        if (dp[n-1] == max) {
            println("-1")
        } else {
            println(dp[n-1])
        }
    }
}