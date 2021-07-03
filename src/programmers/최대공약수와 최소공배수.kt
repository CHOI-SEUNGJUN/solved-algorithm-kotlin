package programmers

/**
 * 프로그래머스 - 최대공약수와 최소공배수
 *
 * https://programmers.co.kr/learn/courses/30/lessons/12940
 */

fun main() {
    val answer = GCDLCMSolution().solution(3, 12)
    println(answer.joinToString(" "))
}

class GCDLCMSolution {
    fun solution(n: Int, m: Int): IntArray {
        return intArrayOf(getGCD(n, m), getLCM(n, m))
    }

    fun getGCD(n: Int, m: Int): Int {
        if (n % m == 0) return m
        else return getGCD(m, n % m)
    }

    fun getLCM(n: Int, m: Int): Int {
        return (n * m) / getGCD(n, m)
    }
}