package baekjoon

/**
 * 백준 #11724 - 연결 요소의 개수
 *
 * https://www.acmicpc.net/problem/11724
 *
 * DFS
 */

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val solution = B11724(n)

    repeat(m) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        solution.addGraph(x, y)
    }

    print(solution.getConnectedComponentCnt())
}

class B11724(n: Int) {

    private val adjacent: Array<IntArray> = Array(n + 1) { IntArray(n + 1 ) { 0 } }
    private val visited = BooleanArray(n + 1) { false }

    private var cnt = 0

    fun addGraph(x: Int, y: Int) {
        adjacent[x][y] = 1
        adjacent[y][x] = 1
    }

    fun getConnectedComponentCnt(): Int {

        for (i in 1 until visited.size) {
            if (!visited[i]) {
                dfs(i)
                cnt++
            }
        }

        return cnt
    }

    private fun dfs(node: Int) {
        visited[node] = true

        for (i in 1 until visited.size) {
            if (adjacent[node][i] == 1 && !visited[i]) {
                dfs(i)
            }
        }
    }
}