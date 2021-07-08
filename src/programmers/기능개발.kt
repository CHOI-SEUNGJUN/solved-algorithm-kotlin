package programmers

import java.util.*

/**
 * 프로그래머스 - 기능개발
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42586
 */

fun main() {
    val answer = FeatureDevelopSolution().solution(
        progresses = intArrayOf(93, 30, 55),
        speeds = intArrayOf(1, 30, 5)
    )
    println(answer.joinToString(" "))
}

class FeatureDevelopSolution {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val answerList = mutableListOf<Int>()
        var progressArray = progresses
        val queue: Queue<Int> = LinkedList()
        queue.addAll(progressArray.indices)

        while (queue.isNotEmpty()) {
            val peek = queue.peek()
            while (progressArray[peek] < 100) {
                progressArray = progressArray.zip(speeds) { p, s -> p + s }.toIntArray()
            }

            var cnt = 0
            for (i in peek until progressArray.size) {
                if (progressArray[i] >= 100) {
                    cnt++
                    queue.poll()
                } else break
            }

            answerList.add(cnt)
        }

        return answerList.toIntArray()
    }
}