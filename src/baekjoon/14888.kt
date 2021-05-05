package baekjoon

/**
 * 백준 #14888 - 연산자 끼워넣기
 *
 * https://www.acmicpc.net/problem/14888
 *
 * DFS
 */

import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val array = readLine().split(" ").map { it.toInt() }
    val operators = readLine().split(" ").map { it.toInt() }.toMutableList()

    B14888(n, array, operators).solution()
}

class B14888(val n: Int, private val array: List<Int>, private val operators: MutableList<Int>) {

    var max = Int.MIN_VALUE
    var min = Int.MAX_VALUE

    fun solution() {
        dfs(array[0], 1)
        val bw = System.`out`.bufferedWriter()
        bw.write("$max\n")
        bw.write("$min")
        bw.close()
    }

    private fun dfs(num: Int, idx: Int) {
        if (idx == n) {
            max = max(max, num)
            min = min(min, num)
        }

        (0..3).forEach { i ->
            if (operators[i] > 0) {
                operators[i]--

                when (i) {
                    PLUS -> dfs(num + array[idx], idx + 1)
                    MINUS -> dfs(num - array[idx], idx + 1)
                    MULTIPLE -> dfs( num * array[idx], idx + 1)
                    DIVIDE -> dfs(num / array[idx], idx + 1)
                }

                operators[i]++
            }
        }
    }

    private companion object {
        const val PLUS = 0
        const val MINUS = 1
        const val MULTIPLE = 2
        const val DIVIDE = 3
    }
}