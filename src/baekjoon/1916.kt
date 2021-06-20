package baekjoon

/**
 * 백준 #1916 - 최소비용 구하기
 *
 * https://www.acmicpc.net/problem/1916
 */

import java.util.*

fun main() {
    B1916Solution().solution()
}

class B1916Solution {

    private lateinit var array: Array<MutableList<Pair<Int, Int>>>
    private lateinit var dist: IntArray
    private lateinit var visited: BooleanArray

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()

        array = Array(n) { mutableListOf() }
        dist = IntArray(n) { Int.MAX_VALUE }
        visited = BooleanArray(n) { false }

        repeat(m) {
            val st = StringTokenizer(readLine())
            array[st.nextToken().toInt() - 1].add(st.nextToken().toInt() - 1 to st.nextToken().toInt())
        }

        val (depart, arrival) = readLine().split(" ").map { it.toInt() }

        dijkstra(depart - 1)

        val bw = System.`out`.bufferedWriter()
        bw.write("${dist[arrival - 1]}")
        bw.close()
    }

    private fun dijkstra(start: Int) {
        dist[start] = 0
        val pq = PriorityQueue { o1: Pair<Int, Int>, o2: Pair<Int, Int> ->
            o2.second - o1.second
        }
        pq.offer(start to 0)

        while (pq.isNotEmpty()) {
            val polled = pq.poll()
            val current = polled.first
            val distance = polled.second

            if (dist[current] < distance) continue
            for (i in array[current].indices) {
                val next = array[current][i].first
                val nextDistance = distance + array[current][i].second

                if (nextDistance < dist[next]) {
                    dist[next] = nextDistance
                    pq.offer(next to nextDistance)
                }
            }
        }
    }
}