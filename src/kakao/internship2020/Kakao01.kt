package kakao.internship2020

/**
 * 카카오 2020 인턴십 - (1) 키패드 누르기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/67256
 */

import kotlin.math.abs

fun main() {
    println(Kakao01Solution().solution(intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2), "left"))
}

class Kakao01Solution {

    fun solution(numbers: IntArray, hand: String): String {
        var answer = ""

        var leftPosition = 0 to 3
        var rightPosition = 2 to 3

        numbers.forEach { num ->
            when {
                num % 3 == 1 -> {
                    leftPosition = 0 to num / 3
                    answer += "L"
                }
                num % 3 == 2 ||  num == 0 -> {
                    val position = if (num == 0) 1 to 3 else 1 to num / 3

                    val leftDist = abs(position.second - leftPosition.second) + abs(position.first - leftPosition.first)
                    val rightDist = abs(position.second - rightPosition.second) + abs(position.first - rightPosition.first)

                    if (leftDist == rightDist) {
                        if (hand == "left") {
                            answer += "L"
                            leftPosition = position
                        } else {
                            answer += "R"
                            rightPosition = position
                        }
                    } else if (leftDist > rightDist) {
                        answer += "R"
                        rightPosition = position
                    } else {
                        answer += "L"
                        leftPosition = position
                    }
                }
                num % 3 == 0 -> {
                    rightPosition = 2 to num / 3 - 1
                    answer += "R"
                }
            }
        }

        return answer
    }
}