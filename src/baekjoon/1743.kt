package baekjoon

/**
 * 백준 #1743 - 음식물 피하기
 *
 * https://www.acmicpc.net/problem/1743
 */


import java.util.*

fun main() {
    B1743Solution().solution()
}

class B1743Solution {

    lateinit var dummy: Array<BooleanArray>
    lateinit var visited: Array<BooleanArray>
    var min = Int.MIN_VALUE
    val dx = arrayListOf(1,0,-1,0)
    val dy = arrayListOf(0,1,0,-1)

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = readLine().split(" ").map { it.toInt() }

        dummy = Array(n+1) { BooleanArray(m+1) { false } }
        visited = Array(n+1) { BooleanArray(m+1) { false } }

        repeat(k) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            dummy[x][y] = true
        }

        for (i in 1..n) {
            for (j in 1..m) {
                if (dummy[i][j] && !visited[i][j]) {
                    bfs(i, j)
                }
            }
        }

        print(min)

    }

    fun bfs(x: Int, y: Int) {
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(x to y)
        visited[x][y] = true
        var cnt = 1

        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            val px = polled.first
            val py = polled.second

            (0..3).forEach {
                val mx = px + dx[it]
                val my = py + dy[it]

                if (mx in 1 until dummy.size && my in 1 until dummy[0].size
                    && !visited[mx][my] && dummy[mx][my]) {
                    queue.offer(mx to my)
                    visited[mx][my] = true
                    cnt++
                }
            }
        }

        min = min.coerceAtLeast(cnt)
    }
}