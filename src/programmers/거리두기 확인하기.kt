package programmers

/**
 * 프로그래머스 - 거리두기 확인하기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/81302
 */

import java.util.*

fun main() {
    print(
        CoronaSolution().solution(arrayOf(
        arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
        arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
        arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
        arrayOf("OOOOO", "OOOOO", "OOOOO", "OOOOX", "OOOOP")
    )).joinToString(","))
}


class CoronaSolution {

    val dx = intArrayOf(0, 0, -1, 1)
    val dy = intArrayOf(-1, 1, 0, 0)

    fun solution(places: Array<Array<String>>): IntArray {
        val answer = ArrayList<Int>()
        for (place in places) {
            val queue: Queue<Pair<Int, Int>> = LinkedList()
            val matrix = Array(5) { CharArray(5) }

            for (i in 0..4) {
                val placeRowString = place[i]
                for (j in 0..4) {
                    matrix[i][j] = placeRowString[j]
                    if (matrix[i][j] == 'P') queue.offer(i to j)
                }
            }

            val menList = queue.toList()

            var flag = false
            while (queue.isNotEmpty()) {
                val polled = queue.poll()
                if (isFoundCoronaAbuser(matrix, polled, menList)) {
                    flag = true
                    break
                }
            }

            if (flag) {
                answer.add(0)
            } else {
                answer.add(1)
            }
        }

        return answer.toIntArray()
    }

    fun isFoundCoronaAbuser(matrix: Array<CharArray>, start: Pair<Int, Int>, men: List<Pair<Int, Int>>): Boolean {
        val dist = Array(5) { IntArray(5) { -1 } }

        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(start)
        val (x, y) = start

        dist[x][y] = 0

        while (!queue.isEmpty()) {
            val (nx, ny) = queue.poll()
            for (k in 0..3) {
                val mx = nx + dx[k]
                val my = ny + dy[k]
                if (mx in 0..4 && my in 0..4) {
                    if (dist[mx][my] < 0 && matrix[mx][my] != 'X') {
                        dist[mx][my] = dist[nx][ny] + 1
                        queue.offer(mx to my)
                    }
                }
            }
        }

        for (man in men) {
            if (dist[man.first][man.second] in 1..2) {
                return true
            }
        }

        return false
    }
}