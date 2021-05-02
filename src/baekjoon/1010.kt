package baekjoon

/**
 * 백준 #1010 - 다리 놓기
 *
 * https://www.acmicpc.net/problem/1010
 */


private val combies = Array(31) { IntArray(31) { -1 } }

fun main() = with(System.`in`.bufferedReader()) {
    val c = readLine().toInt()

    for (i in combies.indices) {
        combies[i][0] = 1
        combies[i][i] = 1
    }

    val bw = System.`out`.bufferedWriter()

    repeat(c) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val answer = combination(m, n)
        bw.write("$answer\n")
    }
    bw.close()
}

private fun combination(n: Int, r: Int): Int {
    if (combies[n][r] != -1) return combies[n][r]

    combies[n][r] = combination(n - 1, r) + combination(n - 1, r - 1)
    return combies[n][r]
}