package baekjoon

/**
 * 백준 #2615 - 오목
 *
 * https://www.acmicpc.net/problem/2615
 */

fun main() {
    B2615Solution().solution()
}

class B2615Solution {

    private val board = Array(19) { IntArray(19) }

    val dx = arrayListOf(1, 0, 1, -1)
    val dy = arrayListOf(0, 1, 1, 1)

    fun solution() = with(System.`in`.bufferedReader()) {
        repeat(19) {
            board[it] = readLine()
                .split(" ")
                .map { i -> i.toInt() }
                .toIntArray()
        }

        val bw = System.`out`.bufferedWriter()

        var isFoundWinner = false
        loop@ for (i in board.indices) {
            for (j in board.indices) {
                if (board[i][j] != 0) {
                    (0..3).forEach {
                        var cx = i
                        var cy = j
                        var cnt = 1

                        while (true) {
                            val nx = cx + dx[it]
                            val ny = cy + dy[it]

                            if (nx in board.indices && ny in board.indices
                                && board[nx][ny] == board[i][j]) {
                                cnt++
                                cx = nx
                                cy = ny

                                if (cnt == 5) {
                                    val mx = cx + dx[it]
                                    val my = cy + dy[it]
                                    if (mx in board.indices && my in board.indices
                                        && board[mx][my] == board[i][j]) {
                                        break
                                    } else {
                                        val px = i - dx[it]
                                        val py = j - dy[it]
                                        if ((px in board.indices && py in board.indices
                                                    && board[px][py] == board[i][j])) {
                                            break
                                        } else {
                                            isFoundWinner = true
                                            bw.write("${board[i][j]}")
                                            bw.newLine()
                                            bw.write("${i+1} ${j+1}")
                                            bw.flush()
                                            break
                                        }
                                    }
                                }
                            } else break
                        }
                    }
                    if (isFoundWinner) { break@loop }
                }
            }
        }

        if (!isFoundWinner) {
            bw.write("0")
        }

        bw.close()
    }
}