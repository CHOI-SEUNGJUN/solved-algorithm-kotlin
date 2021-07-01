package programmers

/**
 * 프로그래머스 - 네트워크
 *
 * https://programmers.co.kr/learn/courses/30/lessons/43162
 */

import java.util.*

fun main() {
    val answer = NetworkSolution().solution(3, arrayOf(
        intArrayOf(1,1,0),
        intArrayOf(1,1,0),
        intArrayOf(0,0,1)
    ))
    println(answer)
}

class NetworkSolution {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        val visited = BooleanArray(n) { false }
        var cnt = 0

        for (i in computers.indices) {
            if (visited[i]) continue
            cnt++
            visited[i] = true

            val queue: Queue<Int> = LinkedList()
            queue.add(i)

            while (queue.isNotEmpty()) {
                val polled = queue.poll()
                computers[polled].forEachIndexed { index, i ->
                    if (i == 1 && !visited[index]) {
                        visited[index] = true
                        queue.offer(index)
                    }
                }
            }
        }

        return cnt
    }
}