package baekjoon

/**
 * 백준 #11052 - 카드 구매하기
 *
 * https://www.acmicpc.net/problem/11052
 */

fun main() {
    B11052Solution().solution()
}

class B11052Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val d = IntArray(n + 1)

        val arr = readLine().split(" ")
            .map { it.toInt() }
            .toMutableList()
            .apply {
                add(0, 0)
                toIntArray()
            }

        for (i in 1..n) {
            for (j in 1..i) {
                d[i] = Math.max(d[i], d[i - j] + arr[j])
            }
        }

        println(d[n])
    }
}