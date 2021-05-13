package baekjoon

/**
 * 백준 #2667 - 단지번호붙이기
 *
 * https://www.acmicpc.net/problem/2667
 */

fun main() {
    B2667Solution().solution()
}

class B2667Solution {

    lateinit var matrix: Array<IntArray>
    lateinit var visited: Array<BooleanArray>

    val dx = arrayListOf(-1, 1, 0, 0)
    val dy = arrayListOf(0, 0, -1, 1)

    val apartList = mutableListOf<Int>()

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        matrix = Array(n) { IntArray(n) { 0 } }
        visited = Array(n) { BooleanArray(n) { false } }

        repeat(n) { i ->
            matrix[i] = readLine().map { it - '0' }.toIntArray()
        }

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (!visited[i][j] && matrix[i][j] == 1) {
                    apartList.add(0, 0)
                    bfs(i, j)
                }
            }
        }

        apartList.sort()
        val bw = System.`out`.bufferedWriter()
        bw.write("${apartList.size}\n")
        apartList.forEach { bw.write("$it\n") }
        bw.flush()
        bw.close()
    }

    fun bfs(x: Int, y: Int) {
        visited[x][y] = true
        apartList[0]++

        (0..3).forEach {
            val mx = x + dx[it]
            val my = y + dy[it]

            if (mx in matrix.indices && my in matrix.indices && matrix[mx][my] == 1 && !visited[mx][my]) {
                bfs(mx, my)
            }
        }
    }
}