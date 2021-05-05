package baekjoon

/**
 * 백준 #14719 - 빗물
 *
 * https://www.acmicpc.net/problem/14719
 */

fun main() {
    print(B14719().solution())
}

class B14719 {

    fun solution(): Int {
        val br = System.`in`.bufferedReader()
        val (h, w) = br.readLine().split(" ").map { it.toInt() }
        val matrix = Array(h) { IntArray(w) { 0 } }
        val filled = br.readLine().split(" ").map { it.toInt() }

        for (i in 0 until w) {
            for (j in 0 until filled[i]) {
                matrix[j][i] = 1
            }
        }

        var answer = 0

        for (i in 0 until h) {
            var isOpened = false
            var temp = 0
            for (j in 0 until w) {
                if (matrix[i][j] == 1) {
                    if (isOpened && temp != 0) {
                        answer += temp
                        temp = 0
                        isOpened = !isOpened
                    }
                    if (!isOpened) isOpened = !isOpened
                } else {
                    if (isOpened) {
                        temp++
                    }
                }
            }
        }

        return answer
    }
}