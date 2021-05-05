package baekjoon

/**
 * 백준 #2504 - 괄호의 값
 *
 * https://www.acmicpc.net/problem/2504
 */

import java.util.*

fun main() {
    val bw = System.`out`.bufferedWriter()
    bw.write("${B2504().solution()}")
    bw.close()
}

class B2504 {

    fun solution(): Int {
        val br = System.`in`.bufferedReader()
        val str = br.readLine()

        val stack = Stack<Char>()
        var temp = 1
        var answer = 0

        str.forEachIndexed { idx, ch ->
            when (ch) {
                '(' -> {
                    stack.push(ch)
                    temp *= 2
                }
                '[' -> {
                    stack.push(ch)
                    temp *= 3
                }
                ')' -> {
                    if (stack.isEmpty() || stack.peek() != '(') return 0
                    if (str[idx - 1] == '(') {
                        answer += temp
                    }
                    stack.pop()
                    temp /= 2
                }
                ']' -> {
                    if (stack.isEmpty() || stack.peek() != '[') return 0
                    if (str[idx - 1] == '[') {
                        answer += temp
                    }
                    stack.pop()
                    temp /= 3
                }
            }
        }

        if (stack.isNotEmpty()) return 0

        return answer
    }
}