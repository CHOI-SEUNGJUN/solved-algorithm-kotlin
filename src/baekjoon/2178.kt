package baekjoon

// 미로탐색 #2178
// https://www.acmicpc.net/problem/2178

import java.util.*

val dx = arrayListOf(1,0,-1,0)
val dy = arrayListOf(0,1,0,-1)

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val matrix = Array(N + 1) { IntArray(M + 1) { 0 } }

    for (i in 1..N) {
        val col = readLine().split("")
            .filter { it.isNotBlank() }
            .map { it.toInt() }

        for (j in 0 until M) {
            matrix[i][j+1] = col[j]
        }
    }

    findRoute(N, M, matrix)
}

private fun findRoute(n: Int, m: Int, matrix: Array<IntArray>) {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    val visited = Array(n + 1) { BooleanArray(m + 1) { false } }
    val distance = Array(n + 1) { IntArray(m + 1) { 0 } }

    queue.add(1 to 1)
    visited[1][1] = true
    distance[1][1] = 1

    while (queue.isNotEmpty()) {
        val polled = queue.poll()
        val x = polled.first
        val y = polled.second

        (0..3).forEach { i ->
            val moveX = x + dx[i]
            val moveY = y + dy[i]

            if (moveX in 1..n && moveY in 1..m && matrix[moveX][moveY] == 1 && !visited[moveX][moveY]) {
                queue.offer(moveX to moveY)
                visited[moveX][moveY] = true
                distance[moveX][moveY] = distance[x][y] + 1
            }
        }
    }
    print(distance[n][m])
}