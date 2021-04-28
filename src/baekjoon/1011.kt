package baekjoon

// Fly me to the Alpha Centauri #1011 - 수학
// https://www.acmicpc.net/problem/1011

import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val case = readLine().toInt()

    val bw = System.`out`.bufferedWriter()

    repeat(case) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        bw.write("${getLeastMove(x, y)}\n")
    }
    bw.close()
}

private fun getLeastMove(x: Int, y:Int): Int {
    val dist= y - x
    val sq = sqrt(dist.toDouble()).toInt()

    return when {
        dist == sq * sq -> 2 * (sq - 1) + 1
        dist > sq * sq + sq -> 2 * sq + 1
        else -> 2 * sq
    }
}