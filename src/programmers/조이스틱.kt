package programmers

/**
 * 프로그래머스 - 조이스틱
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42860
 */

fun main() {
    val answer = JoyStickSolution().solution("JEROEN")
    println(answer) // 56
}

class JoyStickSolution {

    fun solution(name: String): Int {
        var answer = 0
        val length = name.length
        var moveCnt = length - 1

        for (i in name.indices) {
            answer += Math.min(name[i] - 'A', 'Z' - name[i] + 1)

            var nextAPosition = i + 1
            while (nextAPosition < length && name[nextAPosition] == 'A') {
                nextAPosition++
            }

            moveCnt = Math.min(moveCnt, i + length - nextAPosition + i)
        }

        answer += moveCnt
        return answer
    }
}