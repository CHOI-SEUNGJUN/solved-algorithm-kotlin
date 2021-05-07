package kakao.internship2020

/**
 * 카카오 2020 인턴십 - (2) 수식 최대화
 *
 * https://programmers.co.kr/learn/courses/30/lessons/67257
 */

import kotlin.math.abs

fun main() {
    print(Kakao02Solution().solution("100-200*300-500+20"))
}

class Kakao02Solution {

    private val result = mutableListOf<Long>()
    private lateinit var visited: BooleanArray
    private lateinit var usedOperators: List<String>

    fun solution(expression: String): Long {
        val numbers = expression
            .split("[-*+]".toRegex())
            .map { it.toLong() }
            .toMutableList()

        val operators = expression.split("[\\d]".toRegex())
            .filter { it.isNotEmpty() }
            .toMutableList()

        usedOperators = operators.distinct()

        visited = BooleanArray(usedOperators.size)

        for (first in usedOperators.indices) {
            visited.fill(false)
            visited[first] = true
            dfs(usedOperators[first], numbers, operators)
        }

        return result.maxOrNull()!!
    }

    private fun dfs(operator: String, numbers: MutableList<Long>, operators: MutableList<String>) {
        val tempNumbers = mutableListOf<Long>().apply { addAll(numbers) }
        val tempOperators = mutableListOf<String>().apply { addAll(operators) }

        if (tempNumbers.size > 2) {
            repeat(tempOperators.count { it == operator }) {
                val index = tempOperators.indexOfFirst { it == operator }

                tempNumbers[index] = operator.calculationByOperator(tempNumbers[index], tempNumbers[index + 1])
                tempNumbers.removeAt(index + 1)
                tempOperators.removeAt(index)

                if (tempOperators.size == 1) {
                    result.add(abs(tempOperators[0].calculationByOperator(tempNumbers[0], tempNumbers[1])))
                    return
                }
            }
        } else {
            result.add(abs(tempOperators[0].calculationByOperator(tempNumbers[0], tempNumbers[1])))
            return
        }

        for (next in visited.indices) {
            if (visited[next]) continue
            visited[next] = true
            dfs(usedOperators[next], tempNumbers, tempOperators)
            visited[next] = false
        }
    }

    private fun String.calculationByOperator(a: Long, b: Long) = when(this) {
        "*" -> a * b
        "+" -> a + b
        "-" -> a - b
        else -> 0
    }


}