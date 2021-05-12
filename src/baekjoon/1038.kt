package baekjoon

/**
 * 백준 #1038 - 감소하는 수
 *
 * https://www.acmicpc.net/problem/1038
 */

import java.util.*

fun main() {
    B1038Solution().solution()
}

class B1038Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val descendingNumbers = LongArray(1_000_001) { 0L }

        val answer: Long

        if (n < 10) answer = n.toLong()
        else {
            val queue: Queue<Long> = LinkedList()
            (1..9).forEach {
                queue.offer(it.toLong())
                descendingNumbers[it] = it.toLong()
            }

            var index = 10

            while (index <= n && queue.isNotEmpty()) {
                val polled = queue.poll()

                val lastNum = polled % 10
                for (i in 0 until lastNum) {
                    queue.offer(polled * 10 + i)
                    descendingNumbers[index++] = polled * 10 + i
                }
            }

            answer = if (descendingNumbers[n] != 0L) descendingNumbers[n] else -1
        }

        val bw = System.`out`.bufferedWriter()
        bw.write("$answer")
        bw.close()
    }
}