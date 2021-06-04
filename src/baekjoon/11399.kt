package baekjoon

/**
 * 백준 #11399 - ATM
 *
 * https://www.acmicpc.net/problem/11399
 */

fun main() {
    B11399Solution().solution()
}

class B11399Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        var min = 0
        val n = readLine().toInt()

        val p = readLine().split(" ")
            .map { it.toInt() }
            .toMutableList()
            .apply { add(0, 0) }

        p.sort()

        for (i in 1..n) {
            p[i] = p[i - 1] + p[i]
            min += p[i]
        }

        println(min)
    }
}