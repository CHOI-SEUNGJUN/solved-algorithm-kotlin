package kakao

// 카카오 2020 문자 압축 문제
// https://programmers.co.kr/learn/courses/30/lessons/60058

fun main() {
    val s = "abcabcdede"

    val a = (1..s.length / 2 + 1).map { i ->
        val chunk = s.chunked(i)
        var count = 1

        (chunk.subList(1, chunk.size)+"").mapIndexed { index, str ->
            if (str == chunk[index]) {
                count++
                ""
            } else {
                val value = "${if (count == 1) "" else count.toString()}${chunk[index]}"
                count = 1
                value
            }
        }.joinToString("").length
    }

    println("answer: ${a.min()!!}")
}