package programmers

/**
 * 프로그래머스 - 디스크 컨트롤러
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42627
 */

import java.util.*

fun main() {
    val answer = DiskControllerSolution().solution(
        arrayOf(
            intArrayOf(0,3),
            intArrayOf(1,9),
            intArrayOf(2,6)
        )
    )
    println(answer)
}

class DiskControllerSolution {
    fun solution(jobs: Array<IntArray>): Int {
        val waiting = LinkedList<Task>()
        val pq = PriorityQueue<Task> { o1, o2 -> o1.work - o2.work }

        for (job in jobs) {
            waiting.offer(Task(job[0], job[1]))
        }

        waiting.sortWith(Comparator { o1, o2 -> o1.req - o2.req })

        var answer = 0
        var cnt = 0
        var time = waiting.peek().req

        while (cnt < jobs.size) {
            while (!waiting.isEmpty() && waiting.peek().req <= time) {
                pq.offer(waiting.pollFirst())
            }

            if (!pq.isEmpty()) {
                val job = pq.poll()
                time += job.work
                answer += (time - job.req)
                cnt++
            } else {
                time++
            }
        }

        return answer / cnt
    }

    data class Task(val req: Int, val work: Int)
}