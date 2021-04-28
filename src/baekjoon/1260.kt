package baekjoon

// DFS와 BFS #1260
// https://www.acmicpc.net/problem/1260

import java.util.*

private lateinit var matrix: Array<IntArray>
private lateinit var dfsVisited: BooleanArray
private val dfsVisitedInOrder = arrayListOf<Int>()

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M, V) = readLine().split(" ").map { it.toInt() }
    matrix = Array(N + 1) { IntArray(N + 1) { 0 } }
    dfsVisited = BooleanArray(N + 1) { false }

    repeat(M) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        addGraph(x, y)
    }

    dfs(V)
    val bfsArray = bfs(V, N)

    val bw = System.`out`.bufferedWriter()
    dfsVisitedInOrder.forEach { bw.write("$it ") }
    bw.write("\n")
    bfsArray.forEach { bw.write("$it ") }
    bw.close()
}

private fun addGraph(x: Int, y: Int) {
    matrix[x][y] = 1
    matrix[y][x] = 1
}

private fun dfs(start: Int) {
    dfsVisited[start] = true
    dfsVisitedInOrder.add(start)

    for (i in matrix[start].indices) {
        if (matrix[start][i] == 1 && dfsVisited[i] == false) {
            dfs(i)
        }
    }
}

private fun bfs(start: Int, vertexCnt: Int): ArrayList<Int> {
    val visited = BooleanArray(vertexCnt + 1) { false }
    val queue: Queue<Int> = LinkedList()
    queue.offer(start)
    visited[start] = true

    val bfsVisited: ArrayList<Int> = arrayListOf()

    while (queue.isNotEmpty()) {
        val polled = queue.poll()
        bfsVisited.add(polled)

        for (i in matrix[polled].indices) {
            if (visited[i] == false && matrix[polled][i] == 1) {
                queue.offer(i)
                visited[i] = true
            }
        }
    }

    return bfsVisited
}