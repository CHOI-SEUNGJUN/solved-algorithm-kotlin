package baekjoon

/**
 * 백준 #1012 - 유기농 배추
 *
 * https://www.acmicpc.net/problem/1012
 *
 * DFS
 */

import java.util.*

private val wormBw = System.`out`.bufferedWriter()

fun main() = with(System.`in`.bufferedReader()) {
    val case = readLine().toInt()

    repeat(case) {
        val (m, n, k) = readLine().split(" ").map { it.toInt() }
        val matrix = Array(m + 1) { Array(n + 1) { 0 to false } }
        val queue: Queue<Pair<Int, Int>> = LinkedList()

        repeat (k) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            matrix[x][y] = 1 to false
            queue.offer(x to y)
        }

        findWorm(matrix, queue)

    }
    wormBw.close()
}

private fun findWorm(matrix: Array<Array<Pair<Int, Boolean>>>, queue: Queue<Pair<Int, Int>>) {

    val dx = arrayListOf(1, 0, -1, 0)
    val dy = arrayListOf(0, 1, 0, -1)

    var wormCnt = 0

    while (queue.isNotEmpty()) {
        val polled = queue.poll()
        val x = polled.first
        val y = polled.second

        if (matrix[x][y].second) continue

        matrix[x][y] = 1 to true

        val stack = Stack<Pair<Int, Int>>()
        stack.push(x to y)

        while (stack.isNotEmpty()) {
            val popped = stack.pop()

            (0..3).forEach { i ->
                val moveX = popped.first + dx[i]
                val moveY = popped.second + dy[i]

                if (moveX in 0..matrix.size && moveY in 0..matrix[0].size) {
                    val movedPosition = matrix[moveX][moveY]

                    if (movedPosition.first == 1 && !movedPosition.second) {
                        matrix[moveX][moveY] = 1 to true
                        stack.push(moveX to moveY)
                    }
                }
            }
        }
        wormCnt++
    }

    wormBw.write("$wormCnt\n")
}