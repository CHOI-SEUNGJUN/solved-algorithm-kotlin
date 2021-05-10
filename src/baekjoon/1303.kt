package baekjoon

/**
 * 백준 #1303 - 전쟁 - 전투
 *
 * https://www.acmicpc.net/problem/1303
 */

import java.util.*

fun main() {
    B1303Solution().solution()
}

class B1303Solution {

    lateinit var array: Array<Array<String>>
    lateinit var visited: Array<BooleanArray>

    val dx = arrayListOf(1,0,-1,0)
    val dy = arrayListOf(0,1,0,-1)

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() }

        array = Array(m) { Array(n) { "" } }
        visited = Array(m) { BooleanArray(n) { false } }

        for (i in 0 until m) {
            val line = br.readLine()
            for (j in 0 until n) {
                array[i][j] = line[j].toString()
            }
        }

        var myTeamCnt = 0
        var otherTeamCnt = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (!visited[i][j]) {
                    val cnt = bfs(i, j)
                    if (array[i][j] == "W") myTeamCnt += cnt * cnt
                    else otherTeamCnt += cnt * cnt
                }
            }
        }

        print("$myTeamCnt $otherTeamCnt")
    }

    private fun bfs(x: Int, y: Int): Int {
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(x to y)
        visited[x][y] = true

        val myTeam = array[x][y]

        var cnt = 0

        while (queue.isNotEmpty()) {
            val polled = queue.poll()
            val qx = polled.first
            val qy = polled.second

            cnt++

            (0..3).forEach {
                val mx = qx + dx[it]
                val my = qy + dy[it]

                if (mx in array.indices && my in array[0].indices && !visited[mx][my] && myTeam == array[mx][my]) {
                    queue.offer(mx to my)
                    visited[mx][my] = true
                }
            }
        }

        return cnt
    }
}