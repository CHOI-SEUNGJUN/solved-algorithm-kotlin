package programmers

/**
 * 프로그래머스 - 숫자 문자열과 영단어
 *
 * https://programmers.co.kr/learn/courses/30/lessons/81301
 */

fun main() {
    print(DigitCharSolution().solution("23four5six7"))
}

class DigitCharSolution {

    private val map = hashMapOf(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun solution(s: String): Int {
        var answer = ""
        var temp = ""
        s.forEach {
            if (it.isDigit()) {
                answer += it.toString()
            } else {
                temp += it.toString()
                if (map.containsKey(temp)) {
                    answer += map[temp]
                    temp = ""
                }
            }
        }

        return answer.toInt()
    }
}