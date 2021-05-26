package programmers

/**
 * 프로그래머스 - 타겟 넘버
 *
 * https://programmers.co.kr/learn/courses/30/lessons/43165
 */

fun main() {
    println(TargetNumberSolution().solution(
        intArrayOf(1,1,1,1,1), 3
    ))
}

class TargetNumberSolution {

    private var answer = 0
    private lateinit var numbers: IntArray
    private var target: Int = 0

    fun solution(numbers: IntArray, target: Int): Int {
        this.numbers = numbers
        this.target = target

        dfs(0, 0)
        return answer
    }

    fun dfs(sum: Int, idx: Int) {
        if (idx >= numbers.size) {
            if (sum == target) answer++
            return
        }

        dfs(sum + numbers[idx], idx + 1)
        dfs(sum - numbers[idx], idx + 1)
    }
}