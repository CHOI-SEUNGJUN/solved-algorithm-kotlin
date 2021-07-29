package programmers

/**
 * 프로그래머스 - 섬 연결하기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42861
 */

import java.util.*

fun main() {
    val answer = IslandConnectSolution().solution(
        n = 5,
        costs = arrayOf(
            intArrayOf(0,1,1),
            intArrayOf(3,4,1),
            intArrayOf(1,2,2),
            intArrayOf(2,3,4)
        )
    )
    println(answer)
}

class IslandConnectSolution {

    lateinit var cycle: IntArray

    fun solution(n: Int, costs: Array<IntArray>): Int {
        cycle = IntArray(n) { it }

        val pq = PriorityQueue<Island>(kotlin.Comparator { o1, o2 ->
            o1.bridgeCost - o2.bridgeCost
        })

        for (cost in costs) {
            pq.offer(Island(cost[0], cost[1], cost[2]))
        }

        var connectedIsland = 0
        var totalCost = 0

        while (pq.isNotEmpty() && connectedIsland < n) {
            val (depart, arrival, bridgeCost) = pq.poll()

            if (isSameParent(depart, arrival)) continue

            unionParent(depart, arrival)
            totalCost += bridgeCost
            connectedIsland++
        }

        return totalCost
    }

    private fun getParent(island: Int): Int {
        if (cycle[island] == island) return island
        cycle[island] = getParent(cycle[island])
        return cycle[island]
    }

    private fun unionParent(x: Int, y: Int) {
        cycle[getParent(x)] = getParent(y)
    }

    private fun isSameParent(x: Int, y: Int): Boolean
        = getParent(x) == getParent(y)

    data class Island(
        val depart: Int,
        val arrival: Int,
        val bridgeCost: Int
    )
}