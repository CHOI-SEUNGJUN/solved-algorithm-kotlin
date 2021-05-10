package kakao.internship2020

/**
 * 카카오 2020 인턴십 - (4) 경주로 건설
 *
 * https://programmers.co.kr/learn/courses/30/lessons/67259
 */

import java.util.*

fun main() {
    println(Kakao04Solution().solution(arrayOf(intArrayOf(0,0,0),intArrayOf(0,0,0),intArrayOf(0,0,0))))
}

class Kakao04Solution {

    val dx = arrayListOf(-1, 1, 0, 0)
    val dy = arrayListOf(0, 0, -1, 1)

    fun solution(board: Array<IntArray>): Int {
        val n = board.size
        var minCost = Int.MAX_VALUE

        val queue: Queue<Car> = LinkedList()

        /* direct 순서 : 상 하 좌 우 */
        if (board[0][1] == 0) {
            queue.offer(Car(0, 1, 100, 3))
            board[0][1] = 100
        }
        if (board[1][0] == 0) {
            queue.offer(Car(1, 0, 100, 1))
            board[1][0] = 100
        }

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            if (polled.x == n - 1 && polled.y == n - 1) {
                if (polled.cost < minCost) {
                    minCost = polled.cost
                }
                continue
            }

            (0..3).forEach { dir ->
                val mx = polled.x + dx[dir]
                val my = polled.y + dy[dir]

                if (mx in board.indices && my in board.indices && board[mx][my] != 1) {
                    var tempCost = polled.cost

                    tempCost += if (polled.direct == dir) 100 else 600

                    if (board[mx][my] == 0 || board[mx][my] >= tempCost) {
                        queue.offer(Car(mx, my, tempCost, dir))
                        board[mx][my] = tempCost
                    }
                }
            }
        }

        return minCost
    }

    data class Car(val x: Int, val y: Int, val cost: Int, val direct: Int)
}