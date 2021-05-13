package baekjoon

/**
 * 백준 #17070 - 파이프 옮기기 1 (by bfs)
 *
 * 시간 초과
 *
 * https://www.acmicpc.net/problem/2667
 */

import java.util.*

fun main() {
    B17070BFSSolution().solution()
}

class B17070BFSSolution {

    lateinit var matrix: Array<IntArray>
    var n = 0
    var answer = 0

    var dx = intArrayOf(0, 1, 1)
    var dy = intArrayOf(1, 0, 1)

    fun solution() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt()

        matrix = Array(n) { IntArray(n) { 0 } }

        repeat(n) { i ->
            matrix[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        bfs()

        val bw = System.`out`.bufferedWriter()
        bw.write("$answer")
        bw.close()
    }

    fun bfs() {
        val queue: Queue<Pipe> = LinkedList()
        queue.offer(Pipe(0 to 1, 0))

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            val px = polled.point.first
            val py = polled.point.second

            if (px == n-1 && py == n-1) {
                answer++
                continue
            }

            val direction = polled.direction

            for (i in 0..2) {
                if (direction == 0 && i == 1) {
                    continue
                } else if (direction == 1 && i == 0) {
                    continue
                }

                if (canMovePipe(px, py, i)) {
                    queue.offer(Pipe(px + dx[i] to py + dy[i], i))
                }
            }
        }
    }

    fun canMovePipe(x: Int, y: Int, direction: Int): Boolean {
        val mx = x + dx[direction]
        val my = y + dy[direction]

        if (mx !in matrix.indices || my !in matrix.indices) return false

        if (direction != 2) {
            if (matrix[mx][my] == 0) {
                return true
            }
        } else {
            if (matrix[mx][my] == 0 && matrix[mx - 1][my] == 0 && matrix[mx][my - 1] == 0) {
                return true
            }
        }

        return false
    }

    /**
     * @param direction: 가로(0) / 세로(1) / 대각선(2) 순서
     */
    data class Pipe(val point: Pair<Int, Int>, val direction: Int)
}