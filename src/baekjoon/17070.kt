package baekjoon

/**
 * 백준 #17070 - 파이프 옮기기 1 (by dfs)
 *
 * https://www.acmicpc.net/problem/2667
 */

fun main() {
    B17070Solution().solution()
}

class B17070Solution {

    lateinit var matrix: Array<IntArray>
    var n = 0
    var answer = 0

    fun solution() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt()

        matrix = Array(n) { IntArray(n) { 0 } }

        repeat(n) { i ->
            matrix[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        dfs(Pipe(0 to 1, 0))

        val bw = System.`out`.bufferedWriter()
        bw.write("$answer")
        bw.close()
    }

    fun dfs(pipe: Pipe) {
        val x = pipe.point.first
        val y = pipe.point.second

        if (x == n-1 && y == n-1) {
            answer++
            return
        }

        if (pipe.direction == 0 || pipe.direction == 2) {
            if (canMovePipe(x, y + 1)) {
                dfs(Pipe(x to y+1, 0))
            }
        }

        if (pipe.direction == 1 || pipe.direction == 2) {
            if (canMovePipe(x + 1, y)) {
                dfs(Pipe(x+1 to y, 1))
            }
        }

        if (canMovePipe(x, y+1) && canMovePipe(x+1, y) && canMovePipe(x+1, y+1)) {
            dfs(Pipe(x+1 to y+1, 2))
        }
    }

    fun canMovePipe(x: Int, y: Int): Boolean {
        if (x >= n || y >= n || matrix[x][y] == 1) return false
        return true
    }

    /**
     * @param direction: 가로(0) / 세로(1) / 대각선(2) 순서
     */
    data class Pipe(val point: Pair<Int, Int>, val direction: Int)
}