package baekjoon

/**
 * 백준 #15486 - 퇴사 2
 *
 * https://www.acmicpc.net/problem/15486
 */

import java.util.*

fun main() {
    B15486Solution().solution()
}

class B15486Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val t = IntArray(n+2)
        val p = IntArray(n+2)
        val dp = IntArray(n+2) { 0 }

        repeat(n) {
            val st = StringTokenizer(readLine())
            t[it+1] = st.nextToken().toInt()
            p[it+1] = st.nextToken().toInt()
        }

        var max = Int.MIN_VALUE

        for (i in 1..n+1) {
            max = Math.max(max, dp[i])

            if (i + t[i] <= n+1) {
                dp[i + t[i]] = Math.max(max + p[i], dp[i + t[i]])
            }
        }

        println(dp[n+1])
    }
}