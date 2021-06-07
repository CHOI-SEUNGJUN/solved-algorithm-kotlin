package baekjoon

/**
 * 백준 #11404 - 플로이드
 *
 * https://www.acmicpc.net/problem/11404
 */

fun main() {
    B11404Solution().solution()
}

class B11404Solution {

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()

        val matrix = Array(n) { IntArray(n) { 10_000_000 } }

        repeat(m) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            matrix[a-1][b-1] = matrix[a-1][b-1].coerceAtMost(c)
        }

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (j == i) continue

                for (k in 0 until n) {
                    if (k == j || k == i) continue

                    if (matrix[j][k] > matrix[j][i] + matrix[i][k]) {
                        matrix[j][k] = matrix[j][i] + matrix[i][k]
                    }
                }
            }
        }

        val bw = System.`out`.bufferedWriter()

        for (i in 0 until n) {
            for (j in 0 until n) {
                bw.write("${if (matrix[i][j] == 10_000_000) 0 else matrix[i][j]} ")
            }
            bw.newLine()
        }

        bw.flush()
        bw.close()
    }
}