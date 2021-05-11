package baekjoon

/**
 * 백준 #16953 - A -> B
 *
 * https://www.acmicpc.net/problem/16953
 */

import java.util.*

fun main() {
    B16953Solution().solution()
}

class B16953Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (start, target) = readLine().split(" ").map { it.toInt() }
        bfs(start.toLong(), target.toLong())
    }

    fun bfs(start: Long, target: Long) {
        val queue: Queue<Pair<Long, Int>> = LinkedList()
        queue.offer(start to 1)

        var answer = -1

        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            val num = polled.first
            val depth = polled.second

            if (num == target) {
                answer = depth
                break
            }

            // 2배
            if (num * 2 <= target) {
                queue.offer(num * 2 to depth + 1)
            }

            // 1 붙이기
            val plusRightOneNum = (num.toString() + "1").toLong()
            if (plusRightOneNum <= target) {
                queue.offer(plusRightOneNum to depth + 1)
            }
        }

        print(answer)
    }
}