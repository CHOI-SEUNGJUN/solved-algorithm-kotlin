package baekjoon

/**
 * 백준 #11058 - 크리보드
 *
 * https://www.acmicpc.net/problem/11058
 */

fun main() {
    B11058Solution().solution()
}

class B11058Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        if (n <= 6) {
            println(n)
            return
        }

        val dp = LongArray(n+1) { -1 }
        (1..6).forEach { dp[it] = it.toLong() }

        for (i in 7..n) {
            for (j in 3 until i) {
                dp[i] = Math.max(dp[i], (j-1) * dp[i-j])
            }
        }

        println(dp[n])
    }
}