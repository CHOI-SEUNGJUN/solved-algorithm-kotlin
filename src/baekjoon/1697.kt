package baekjoon

/**
 * 백준 #1697 - 숨바꼭질
 *
 * https://www.acmicpc.net/problem/1697
 *
 * BFS
 */

import java.util.*

private val MAX_VALUE = 100001
private val visited = BooleanArray(MAX_VALUE) { false }

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    val answer = findBrotherBFS(n, k)
    print(answer)
}

private fun findBrotherBFS(start: Int, target: Int): Int {
    val queue: Queue<Pair<Int, Int>> = LinkedList()

    queue.offer(start to 0)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()
        val position = polled.first
        val sec = polled.second

        if (position !in 0 until MAX_VALUE || visited[position]) continue

        if (position == target) {
            return sec
        } else {
            visited[position] = true
            queue.offer(position - 1 to sec + 1)
            queue.offer(position + 1 to sec + 1)
            queue.offer(position * 2 to sec + 1)
        }
    }

    return -1
}