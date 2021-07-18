package programmers

import java.util.*

/**
 * 프로그래머스 - 이중우선순위큐
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42628
 */

fun main() {
    val answer = OverlapPriorityQueueSolution().solution(arrayOf(
        "I 16","D 1"
    ))
    println(answer.joinToString(" "))
}

class OverlapPriorityQueueSolution {
    fun solution(operations: Array<String>): IntArray {
        val pq = PriorityQueue<Int>()

        for (operation in operations) {
            val num = operation.substring(2).toInt()
            when (operation[0]) {
                'I' -> {
                    pq.offer(num)
                }
                'D' -> {
                    if (num == 1) { // 최댓값 삭제
                        if (pq.lastOrNull() != null) {
                            pq.remove(pq.last())
                        }
                    } else { // 최솟값 삭제
                        if (pq.firstOrNull() != null) {
                            pq.remove(pq.first())
                        }
                    }
                }
            }
        }
        val sortedList = pq.sorted()
        return intArrayOf(sortedList.lastOrNull() ?: 0, sortedList.firstOrNull() ?: 0)
    }
}