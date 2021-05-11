package baekjoon

/**
 * 백준 #12851 - 숨바꼭질2
 *
 * https://www.acmicpc.net/problem/12851
 */

import java.util.*

fun main() {
    B12851Solution().solution()
}

class B12851Solution {

    val visited = BooleanArray(100001) { false }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (start, target) = readLine().split(" ").map { it.toInt() }
        bfs(start, target)
    }

    fun bfs(start: Int, target: Int) {
        val queue: Queue<Pair<Int, Int>> = LinkedList() // position to depth
        queue.offer(start to 0)

        var minSearchDepth = Int.MAX_VALUE
        var cnt = 0

        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            val position = polled.first
            val depth = polled.second

            visited[position] = true

            if (position == target && minSearchDepth != Int.MAX_VALUE && depth == minSearchDepth) {
                cnt++
            }

            if (minSearchDepth == Int.MAX_VALUE && position == target) {
                minSearchDepth = depth
                cnt++
            }

            if (position - 1 in 0..100000 && !visited[position - 1]) {
                queue.offer(position - 1 to depth + 1)
            }
            if (position + 1 in 0..100000 && !visited[position + 1]) {
                queue.offer(position + 1 to depth + 1)
            }
            if (position * 2 in 0..100000 && !visited[position * 2]) {
                queue.offer(position * 2 to depth + 1)
            }
        }

        val bw = System.`out`.bufferedWriter()
        bw.write("$minSearchDepth\n$cnt")
        bw.flush()
        bw.close()
    }
}