package programmers

/**
 * 프로그래머스 - 합승 택시 요금
 *
 * https://programmers.co.kr/learn/courses/30/lessons/72413
 */

import java.util.*

fun main() {
    val answer = TaxiFareSolution().solution(
        6,4,6,2,
        arrayOf(
            intArrayOf(4,1,10),
            intArrayOf(3,5,24),
            intArrayOf(5,6,2),
            intArrayOf(3,1,41),
            intArrayOf(5,1,24),
            intArrayOf(4,6,50),
            intArrayOf(2,4,66),
            intArrayOf(2,3,22),
            intArrayOf(1,6,25)
        )
    )
    println(answer)
}

class TaxiFareSolution {

    private val INF = 200 * 100_000 + 1
    lateinit var map: Array<LinkedList<Position>>
    private var n = -1

    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        this.n = n
        var answer = Int.MAX_VALUE
        map = Array(n + 1) { LinkedList() }

        for (fare in fares) {
            val (depart, arrival, cost) = fare
            map[depart].add(Position(arrival, cost))
            map[arrival].add(Position(depart, cost))
        }

        val distFromPositionA = findMinTaxiFareWithDijkstra(a)
        val distFromPositionB = findMinTaxiFareWithDijkstra(b)
        val distFromPositionStart = findMinTaxiFareWithDijkstra(s)

        for (i in 1..n) {
            answer = Math.min(answer, distFromPositionA[i] + distFromPositionB[i] + distFromPositionStart[i])
        }

        return answer
    }

    fun findMinTaxiFareWithDijkstra(start: Int): IntArray {
        val dist = IntArray(n + 1) { INF }
        dist[start] = 0
        val pq = PriorityQueue<Position>()
        pq.offer(Position(start, 0))

        while (!pq.isEmpty()) {
            val (idx, fare) = pq.poll()

            if (fare > dist[idx]) continue

            for (adj in map[idx]) {
                val nextFare = dist[idx] + adj.fare

                if (nextFare < dist[adj.idx]) {
                    dist[adj.idx] = nextFare
                    pq.offer(Position(adj.idx, nextFare))
                }
            }
        }

        return dist
    }

    data class Position(
        val idx: Int,
        val fare: Int) : Comparable<Position> {
        override operator fun compareTo(other: Position): Int {
            return fare - other.fare
        }
    }
}