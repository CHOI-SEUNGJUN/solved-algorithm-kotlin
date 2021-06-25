package programmers

/**
 * 프로그래머스 - 배달
 *
 * https://programmers.co.kr/learn/courses/30/lessons/12978
 */

fun main() {
    val answer = DeliverySolution().solution(N = 6,
        road = arrayOf(
            intArrayOf(1,2,1),
            intArrayOf(1,3,2),
            intArrayOf(2,3,2),
            intArrayOf(3,4,3),
            intArrayOf(3,5,2),
            intArrayOf(3,5,3),
            intArrayOf(5,6,1)
        ), k = 4)
    println(answer)
}

class DeliverySolution {

    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        val INF = 500001
        val matrix = Array(N) { IntArray(N) { INF } }

        road.forEach { r ->
            val (x, y, c) = r
            matrix[x-1][y-1] = Math.min(matrix[x-1][y-1], c)
            matrix[y-1][x-1] = matrix[x-1][y-1]
        }

        val dist = IntArray(N) { INF }
        val visited = BooleanArray(N) { false }

        for (i in 0 until N) {
            dist[i] = matrix[0][i]
        }
        dist[0] = 0
        visited[0] = true

        for (i in 0 until N - 2) {
            var min = INF
            var index = 0
            for (j in 0 until N) {
                if (dist[j] < min && !visited[j]) {
                    min = dist[j]
                    index = j
                }
            }

            val current = index
            visited[current] = true
            for (l in 0 until N) {
                if (!visited[l]) {
                    if (dist[current] + matrix[current][l] < dist[l]) {
                        dist[l] = dist[current] + matrix[current][l]
                    }
                }
            }
        }

        return dist.filter { it <= k }.count()
    }
}