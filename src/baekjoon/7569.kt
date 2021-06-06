package baekjoon

import java.util.*

/**
 * 백준 #7569 - 토마토
 *
 * https://www.acmicpc.net/problem/7569
 */

fun main() {
    B7569Solution().solution()
}

class B7569Solution {

    val dx = arrayListOf(1, -1, 0, 0, 0, 0)
    val dy = arrayListOf(0, 0, 1, -1, 0, 0)
    val dz = arrayListOf(0, 0, 0, 0, 1, -1)

    fun solution() = with(System.`in`.bufferedReader()) {
        val (m, n, h) = readLine().split(" ").map { it.toInt() }

        val matrix = Array(h) { Array(n) { IntArray(m) { 0 } } }
        val visited = Array(h) { Array(n) { BooleanArray(m) { false } } }

        repeat(h) { H ->
            repeat(n) { N ->
                matrix[H][N] = readLine().split(" ").map { it.toInt() }.toIntArray()
            }
        }

        val queue: Queue<Triple<Int, Int, Int>> = LinkedList()

        for (i in 0 until h) {
            for (j in 0 until n) {
                for (k in 0 until m) {
                    if (matrix[i][j][k] == 1) {
                        queue.add(Triple(i, j, k))
                        visited[i][j][k] = true
                    } else if (matrix[i][j][k] == -1) {
                        visited[i][j][k] = true
                    }
                }
            }
        }

        var day = -1

        while (queue.isNotEmpty()) {
            val size = queue.size
            day++

            repeat(size) {
                val polled = queue.poll()
                val px = polled.first
                val py = polled.second
                val pz = polled.third

                (0..5).forEach {
                    if (px + dx[it] in 0 until h && py + dy[it] in 0 until n && pz + dz[it] in 0 until m) {
                        if (!visited[px + dx[it]][py + dy[it]][pz + dz[it]] &&
                            matrix[px + dx[it]][py + dy[it]][pz + dz[it]] == 0) {
                                visited[px + dx[it]][py + dy[it]][pz + dz[it]] = true
                                queue.offer(Triple(px + dx[it], py + dy[it], pz + dz[it]))
                        }
                    }
                }
            }

        }

        for (i in 0 until h) {
            for (j in 0 until n) {
                for (k in 0 until m) {
                    if (!visited[i][j][k]) {
                        day = -1
                        break
                    }
                }
            }
        }

        println(day)
    }
}