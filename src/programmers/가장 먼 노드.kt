package programmers

/**
 * 프로그래머스 - 가장 먼 노드
 *
 * https://programmers.co.kr/learn/courses/30/lessons/49189
 */

import java.util.*

fun main() {
    val answer = FarNodeSolution().solution(
        n = 6,
        edge = arrayOf(
            intArrayOf(3,6),
            intArrayOf(4,3),
            intArrayOf(3,2),
            intArrayOf(1,3),
            intArrayOf(1,2),
            intArrayOf(2,4),
            intArrayOf(5,2)
        )
    )
    println(answer)
}

class FarNodeSolution {

    fun solution(n: Int, edge: Array<IntArray>): Int {
        val matrix = Array(n) { BooleanArray(n) { false } }
        val dist = IntArray(n) { INF }
        dist[0] = 0

        edge.forEach {
            val x = it[0] - 1
            val y = it[1] - 1
            matrix[x][y] = true
            matrix[y][x] = true
        }

        val queue: Queue<Int> = LinkedList()
        queue.offer(0)

        var max = Int.MIN_VALUE

        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            for (i in matrix[polled].indices) {
                if (matrix[polled][i] && dist[i] == INF) {
                    dist[i] = dist[polled] + 1
                    max = Math.max(max, dist[i])
                    queue.offer(i)
                }
            }
        }

        return dist.count { it == max }
    }

    private companion object {
        const val INF = 1_000_000
    }
}