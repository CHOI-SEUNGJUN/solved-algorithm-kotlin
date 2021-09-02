package baekjoon

import java.util.*

/**
 * 백준 #2206 - 벽 부수고 이동하기
 *
 * https://www.acmicpc.net/problem/2206
 */

fun main() {
    B2206Solution().solution()
}

class B2206Solution {

    private var n = 0
    private var m = 0

    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    private lateinit var matrix: Array<IntArray>
    private lateinit var visited: Array<Array<BooleanArray>>

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        this@B2206Solution.n = n
        this@B2206Solution.m = m

        matrix = Array(n) { IntArray(m) }
        visited = Array(n) { Array(m) { BooleanArray(2) } }

        repeat(n) { i ->
            val column = readLine()
            repeat(m) { j ->
                matrix[i][j] = column[j].digitToInt()
            }
        }

        findMinimumPath(0, 0)
    }

    private fun findMinimumPath(x: Int, y: Int) {
        val queue: Queue<Point> = LinkedList()

        queue.offer(Point(x, y, 0, 1))

        visited[x][y][0] = true
        visited[x][y][1] = true

        while (!queue.isEmpty()) {
            val (px, py, isDestroyBrick, distance) = queue.poll()

            if (px == n - 1 && py == m - 1) {
                println(distance)
                return
            }

            for (i in 0..3) {
                val nx = px + dx[i]
                val ny = py + dy[i]

                if (nx !in 0 until n || ny !in 0 until m) continue

                if (matrix[nx][ny] == 1) {
                    if (isDestroyBrick == 0 && !visited[nx][ny][1]) {
                        visited[nx][ny][1] = true
                        queue.offer(Point(nx, ny, 1, distance + 1))
                    }
                } else {
                    if (!visited[nx][ny][isDestroyBrick]) {
                        queue.offer(Point(nx, ny, isDestroyBrick, distance + 1))
                        visited[nx][ny][isDestroyBrick] = true
                    }
                }
            }
        }

        println(-1)
    }

    data class Point(val x: Int, val y: Int, val isDestroyBrick: Int, val distance: Int)
}