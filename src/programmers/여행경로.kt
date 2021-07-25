package programmers

/**
 * 프로그래머스 - 여행경로
 *
 * https://programmers.co.kr/learn/courses/30/lessons/43164
 */

import java.util.*

fun main() {
    val answer = TravelPathSolution().solution(
        arrayOf(
            arrayOf("ICN", "A"),
            arrayOf("ICN", "B"),
            arrayOf("B", "ICN")
        )
    )
    println(answer.joinToString(" "))
}

class TravelPathSolution {

    lateinit var tickets: Array<Array<String>>
    lateinit var visited: BooleanArray
    var pathList = LinkedList<String>()


    fun solution(tickets: Array<Array<String>>): Array<String> {
        this.tickets = tickets
        visited = BooleanArray(tickets.size) { false }

        tickets.sortBy { it[1] }

        findPath(depart = "ICN", tempPathList = LinkedList(), cnt = 0)
        return pathList.toTypedArray()
    }

    fun findPath(depart: String, tempPathList: LinkedList<String>, cnt: Int): Boolean {
        tempPathList.offer(depart)

        if (cnt == tickets.size) {
            println(tempPathList)
            pathList = tempPathList
            return true
        }

        for (i in tickets.indices) {
            val (tDepart, tArrival) = tickets[i]
            if (tDepart == depart && !visited[i]) {
                visited[i] = true
                if (findPath(tArrival, tempPathList, cnt + 1)) return true
                visited[i] = false
            }
        }

        tempPathList.removeLast()
        return false
    }
}