package baekjoon

/**
 * 백준 #2606 - 바이러스
 *
 * https://www.acmicpc.net/problem/2606
 */


import java.util.*

fun main() {
    B2606Solution().solution()
}

class B2606Solution {

    lateinit var matrix: Array<IntArray>
    lateinit var visited: BooleanArray
    var cnt = 0

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()

        matrix = Array(n+1) { IntArray(n+1) { 0 } }
        visited = BooleanArray(n+1) { false }

        repeat(m) {
            val (x,y) = readLine().split(" ").map { it.toInt() }
            matrix[x][y] = 1
            matrix[y][x] = 1
        }

        bfs()
        print(cnt)
    }

    fun bfs() {
        val queue: Queue<Int> = LinkedList()
        queue.offer(1)
        visited[1] = true

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            matrix[polled].forEachIndexed { index, adj ->
                if (adj == 1 && !visited[index]) {
                    visited[index] = true
                    queue.offer(index)
                    cnt++
                }
            }
        }
    }
}