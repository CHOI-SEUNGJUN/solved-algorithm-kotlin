package baekjoon

/**
 * 백준 #14226 - 이모티콘
 *
 * https://www.acmicpc.net/problem/14226
 */

import java.util.*

fun main() {
    B14226Solution().solution()
}

class B14226Solution {

    val visited = BooleanArray(1001) { false }
    fun solution() = with(System.`in`.bufferedReader()) {
        val s = readLine().toInt()
        close()

        bfs(s)
    }

    fun bfs(target: Int) {

        var answer = 0
        val queue: Queue<Emoticon> = LinkedList()
        queue.offer(Emoticon(1, 0))

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            visited[polled.position] = true

            if (polled.position == target) {
                answer = polled.second
                break
            }

            if (polled.clipBoard != 0 && (polled.position + polled.clipBoard) in 2..1000 && !visited[polled.position + polled.clipBoard]) {
                queue.offer(
                    Emoticon(polled.position + polled.clipBoard, polled.second + 1, polled.clipBoard)
                )
            }

            if (polled.position - 1 in 2..1000 && !visited[polled.position - 1]) {
                queue.offer(
                    Emoticon(polled.position - 1, polled.second + 1, polled.clipBoard)
                )
            }

            queue.offer(
                Emoticon(polled.position, polled.second + 1, polled.position)
            )
        }

        val bw = System.`out`.bufferedWriter()
        bw.write("$answer")
        bw.close()
    }

    data class Emoticon(var position: Int, var second: Int, var clipBoard: Int = 0)
}