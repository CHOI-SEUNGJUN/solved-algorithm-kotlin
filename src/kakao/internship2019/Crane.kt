package kakao.internship2019

/**
 * 2019 카카오 개발자 겨울 인턴십 - 크레인 인형뽑기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/64061
 */

fun main() {
    println(CraneSolution().solution(
        arrayOf(
            intArrayOf(0,0,0,0,0),
            intArrayOf(0,0,1,0,3),
            intArrayOf(0,2,5,0,1),
            intArrayOf(4,2,4,4,2),
            intArrayOf(3,5,1,3,1)
        ),
        intArrayOf(1,5,3,5,1,2,1,4)
    ))
}

class CraneSolution {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        val box = arrayListOf<Int>()
        var last: Int? = 0

        var answer = 0

        for (move in moves) {
            for (i in board.indices) {
                if (board[i][move - 1] != 0) {
                    if (board[i][move - 1] == last) {
                        box.removeLast()
                        last = box.lastOrNull()
                        answer += 2
                    } else {
                        box.add(board[i][move - 1])
                        last = board[i][move - 1]
                    }
                    board[i][move - 1] = 0

                    break
                }
            }
        }

        return answer
    }
}