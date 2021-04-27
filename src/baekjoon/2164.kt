package baekjoon

// 카드2 #2164
// https://www.acmicpc.net/problem/2164

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val cards: Queue<Int> = LinkedList()
    for (i in 1..n) {
        cards.offer(i)
    }

    while (cards.size > 1) {
        cards.poll()
        cards.offer(cards.poll())
    }

    println(cards.poll())
}