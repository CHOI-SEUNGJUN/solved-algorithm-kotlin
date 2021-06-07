package baekjoon

/**
 * 백준 #1543 - 문서 검색
 *
 * https://www.acmicpc.net/problem/1543
 */

fun main() {
    B1543Solution().solution()
}

class B1543Solution {

    fun solution() = with(System.`in`.bufferedReader()) {
        var sentence = readLine()
        val word = readLine()

        var cnt = 0

        while (sentence.contains(word)) {
            cnt++
            val idx = sentence.indexOf(word)
            sentence = sentence.substring(idx + word.length)
        }

        println(cnt)
    }
}