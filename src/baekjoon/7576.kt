package baekjoon

/**
 * 백준 #7576 - 토마토
 *
 * https://www.acmicpc.net/problem/7576
 *
 * BFS
 */


import java.util.*
import kotlin.math.*

val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val (m, n) = readLine().split(" ").map { it.toInt() }

    print(B7576(n, m).solution())
}

class B7576(val n: Int, val m: Int) {
    private val matrix = Array(n) { IntArray(m) }
    private val queue: Queue<Pair<Int, Int>> = LinkedList()
    private val distance = Array(n) { IntArray(m) { -1 } }

    private val dx = arrayListOf(1, 0, -1, 0)
    private val dy = arrayListOf(0, 1, 0, -1)

    fun solution(): Int {
        repeat(n) { i ->
            matrix[i] = br.readLine().split(" ")
                .mapIndexed { index, s ->
                    if (s.toInt() == 1) {
                        queue.offer(i to index)
                        distance[i][index] = 0
                    }
                    s.toInt()
                }
                .toIntArray()
        }

        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            val x = polled.first
            val y = polled.second

            (0..3).forEach { i ->
                val moveX = x + dx[i]
                val moveY = y + dy[i]

                if (moveX in 0 until n && moveY in 0 until m) {

                    if (matrix[moveX][moveY] == 0 && distance[moveX][moveY] == -1) {
                        distance[moveX][moveY] = distance[x][y] + 1
                        queue.offer(moveX to moveY)
                    }
                }
            }
        }

        var result = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                result = max(result, distance[i][j])
            }
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (matrix[i][j] == 0 && distance[i][j] == -1) {
                    result = -1
                }
            }
        }

        return result
    }
}