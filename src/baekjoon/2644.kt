package baekjoon

/**
 * 백준 #2644 - 촌수계산
 *
 * https://www.acmicpc.net/problem/2644
 */

fun main() {
    B2644Solution().solution()
}

class B2644Solution {

    private lateinit var matrix: Array<IntArray>
    private lateinit var dist: IntArray

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val (x, y) = readLine().split(" ").map { it.toInt() }

        matrix = Array(n+1) { IntArray(n+1) { 0 } }
        dist = IntArray(n+1) { -1 }

        repeat(readLine().toInt()) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            matrix[a][b] = 1
            matrix[b][a] = 1
        }

        dfs(x, 0, y)
        println(dist[y])
    }

    fun dfs(x: Int, d: Int, target: Int) {
        if (x == target) return

        for (i in matrix[x].indices) {
            if (matrix[x][i] == 1 && dist[i] == -1) {
                dist[i] = d + 1
                dfs(i, d+1, target)
            }
        }
    }

}