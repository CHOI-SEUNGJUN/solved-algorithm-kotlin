package baekjoon

/**
 * 백준 #1806 - 부분합
 *
 * https://www.acmicpc.net/problem/1806
 */

fun main() {
    B1806Solution().solution()
}

class B1806Solution {

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, s) = readLine().split(" ").map { it.toInt() }
        val array = readLine().split(" ").map { it.toInt() }.toIntArray()

        var minLength = Int.MAX_VALUE

        var sum = 0
        var left = 0
        var right = 0
        while (true) {
            if (sum >= s) {
                sum -= array[left++]
                minLength = Math.min(minLength, right - left + 1)
            } else if (right == n) break
            else sum += array[right++]
        }

        val bw = System.`out`.bufferedWriter()
        if (minLength == Int.MAX_VALUE) {
            bw.write("0")
        } else {
            bw.write("$minLength")
        }
        bw.close()
    }
}