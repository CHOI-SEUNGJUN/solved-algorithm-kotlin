package programmers

/**
 * 프로그래머스 - 입국심사
 *
 * https://programmers.co.kr/learn/courses/30/lessons/43238
 */

fun main() {
    val answer = ImmigrationSolution().solution(2, intArrayOf(1, 2))
    println(answer)
}

class ImmigrationSolution {
    fun solution(n: Int, times: IntArray): Long {
        times.sort()

        var min = 1L
        var max: Long = times.last() * n.toLong()
        var answer = max

        while (min <= max) {
            val mid = (min + max) / 2
            var sum = 0L

            sum += times.sumOf { mid / it }

            if (sum >= n) {
                if (answer > mid) answer = mid
                max = mid - 1
            } else {
                min = mid + 1
            }
        }

        return answer
    }
}