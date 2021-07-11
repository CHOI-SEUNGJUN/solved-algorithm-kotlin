package programmers

/**
 * 프로그래머스 - 프린터
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42587
 */

import java.util.*

fun main() {
    val answer = PrinterSolution().solution(intArrayOf(1,1,9,1,1,1), 0)
    println(answer)
}

class PrinterSolution {
    fun solution(priorities: IntArray, location: Int): Int {
        val printed = priorities.map { Printer(it, false) }

        val queue: Queue<Int> = LinkedList()
        queue.addAll(priorities.indices)

        var printCnt = 0
        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            // if 더 높은 우선 순위가 있다면
            if (printed.any { !it.printed && it.priority > priorities[polled]}) {
                queue.offer(polled)
            } else {
                printed[polled].printed = true
                printCnt++
                if (polled == location) break
            }
        }
        return printCnt
    }

    data class Printer(val priority: Int, var printed: Boolean)
}