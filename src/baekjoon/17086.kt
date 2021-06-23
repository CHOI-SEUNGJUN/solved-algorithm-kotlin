package baekjoon

/**
 * 백준 #17086 - 아기상어 2
 *
 * https://www.acmicpc.net/problem/17086
 */

import java.util.*

fun main() {
    B17086Solution().solution()
}

class B17086Solution {

    private val dx = arrayListOf(1, 1, -1, -1, 0, 0, 1, -1)
    private val dy = arrayListOf(1, -1, 1, -1, 1, -1, 0, 0)

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        val array = Array(n) { IntArray(m) }
        val dist = Array(n) { IntArray(m) { Int.MAX_VALUE } }

        val queue: Queue<Pair<Int, Int>> = LinkedList()

        repeat(n) { i ->
            val st = StringTokenizer(readLine())
            repeat(m) { j ->
                array[i][j] = st.nextToken().toInt()
                if (array[i][j] == 1) {
                    queue.offer(i to j)
                    dist[i][j] = 0
                }
            }
        }

        var max = 0
        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            val x = polled.first
            val y = polled.second

            (0..7).forEach {
                val mx = x + dx[it]
                val my = y + dy[it]

                if (mx in 0 until n && my in 0 until m) {
                    if (dist[mx][my] > dist[x][y] + 1) {
                        dist[mx][my] = dist[x][y] + 1
                        queue.offer(mx to my)
                        max = Math.max(dist[mx][my], max)
                    }
                }
            }
        }

        println(max)
    }
}