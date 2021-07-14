package programmers

/**
 * 프로그래머스 - 행렬 테두리 회전하기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/77485
 */

fun main() {
    val answer = MatrixRotateSolution().solution(
        6,6,
        arrayOf(
            intArrayOf(2,2,5,4),
            intArrayOf(3,3,6,6),
            intArrayOf(5,1,6,3)
        )
    )
    println(answer.joinToString(" "))
}

class MatrixRotateSolution {

    lateinit var matrix: Array<IntArray>

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        matrix = Array(rows) { IntArray(columns) }

        val answer = IntArray(queries.size)

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                matrix[i][j] = i * columns + j + 1
            }
        }

        for (i in queries.indices) {
            answer[i] = rotateMatrix(queries[i])
        }
        return answer
    }

    fun rotateMatrix(query: IntArray): Int {
        val (x1, y1, x2, y2) = query.map { it - 1 }

        val temp = matrix[x1][y1]
        var min = Int.MAX_VALUE

        for (i in x1 until x2) {
            matrix[i][y1] = matrix[i + 1][y1]
            min = min.coerceAtMost(matrix[i][y1])
        }
        for (i in y1 until y2) {
            matrix[x2][i] = matrix[x2][i + 1]
            min = min.coerceAtMost(matrix[x2][i])
        }
        for (i in x2 downTo x1 + 1) {
            matrix[i][y2] = matrix[i - 1][y2]
            min = min.coerceAtMost(matrix[i][y2])
        }
        for (i in y2 downTo y1 + 1) {
            matrix[x1][i] = matrix[x1][i - 1]
            min = min.coerceAtMost(matrix[x1][i])
        }

        matrix[x1][y1 + 1] = temp
        min = min.coerceAtMost(matrix[x1][y1 + 1])

        return min
    }
}