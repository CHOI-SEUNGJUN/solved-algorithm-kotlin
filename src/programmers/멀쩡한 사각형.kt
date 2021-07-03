package programmers

/**
 * 프로그래머스 - 멀쩡한 사각형
 *
 * https://programmers.co.kr/learn/courses/30/lessons/62048
 */

fun main() {
    val answer = FineSquareSolution().solution(8, 12)
    println(answer)
}

class FineSquareSolution {
    fun solution(w: Int, h: Int): Long {
        return (w.toLong() * h.toLong()) - (w + h - getGCD(w, h)).toLong()
    }

    fun getGCD(w: Int, h: Int): Int {
        if (w % h == 0) return h
        else return getGCD(h, w % h)
    }
}