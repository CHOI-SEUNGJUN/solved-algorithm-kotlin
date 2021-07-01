package programmers

/**
 * 프로그래머스 - 소수 만들기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/12977
 */

fun main() {
    val answer = PrimeSolution().solution(intArrayOf(1,2,3,4))
    println(answer)
}

class PrimeSolution() {
    fun solution(nums: IntArray): Int {
        var answer = 0

        for (i in nums.indices) {
            for (j in i+1 until nums.size) {
                for (k in j+1 until nums.size) {
                    val combinedNumber = nums[i] + nums[j] + nums[k]
                    if (isPrimeNumber(combinedNumber)) {
                        answer++
                    }
                }
            }
        }
        return answer
    }

    private fun isPrimeNumber(number: Int): Boolean {
        for (i in 2 until number) {
            if (number % i == 0) return false
        }
        return true
    }
}