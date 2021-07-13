package programmers

/**
 * 프로그래머스 - 표 편집
 *
 * https://programmers.co.kr/learn/courses/30/lessons/81303
 */

import java.util.*

fun main() {
    print(TableEditSolution().solution(8, 2, arrayOf("D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C")))
}

class TableEditSolution {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var current = k
        val stack = Stack<Int>()
        var tableSize = n
        for (i in cmd.indices) {
            when (cmd[i][0]) {
                'U' -> {
                    current -= cmd[i].substring(2).toInt()
                }
                'D' -> {
                    current += cmd[i].substring(2).toInt()
                }
                'C' -> {
                    stack.push(current)
                    tableSize -= 1
                    if (current == tableSize) current -= 1
                }
                'Z' -> {
                    val popped = stack.pop()
                    if (current >= popped) current += 1
                    tableSize += 1
                }
            }
        }

        val sb = StringBuilder()
        for (i in 0 until tableSize) {
            sb.append('O')
        }

        while (!stack.isEmpty()) {
            sb.insert(stack.pop(), 'X')
        }

        return sb.toString()
    }
}