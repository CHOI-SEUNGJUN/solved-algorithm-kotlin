package programmers

/**
 * 프로그래머스 - 퍼즐 조각 채우기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/84021
 */

fun main() {
    val answer = PuzzleBlockFillSolution().solution(
        arrayOf(
            intArrayOf(1,1,0,0,1,0),
            intArrayOf(0,0,1,0,1,0),
            intArrayOf(0,1,1,0,0,1),
            intArrayOf(1,1,0,1,1,1),
            intArrayOf(1,0,0,0,1,0),
            intArrayOf(0,1,1,1,0,0)
        ),
        arrayOf(
            intArrayOf(1,0,0,1,1,0),
            intArrayOf(1,0,1,0,1,0),
            intArrayOf(0,1,1,0,1,1),
            intArrayOf(0,0,1,0,0,0),
            intArrayOf(1,1,0,1,1,0),
            intArrayOf(0,1,0,0,0,0)
        )
    )
    println(answer)
}

class PuzzleBlockFillSolution {

    private lateinit var gameBoard: Array<IntArray>
    private lateinit var table: Array<IntArray>

    private val dx = listOf(-1, 1, 0, 0)
    private val dy = listOf(0, 0, -1, 1)

    private val boardList = mutableListOf<Block>()
    private val tableList = mutableListOf<Block>()

    private val minXY = IntArray(2)
    private var blockCount = 0

    private lateinit var arrayForSearchingBlock: Array<IntArray>

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        gameBoard = game_board
        this.table = table

        for (i in gameBoard.indices) {
            for (j in gameBoard.indices) {
                if (gameBoard[i][j] == 0) { // isEmptyBlock
                    initSearchTempSet(i, j, Search.BOARD)
                    boardList.add(Block(arrayForSearchingBlock, blockCount, minXY[0], minXY[1]))
                }
                if (table[i][j] == 1) { // isBlock
                    initSearchTempSet(i, j, Search.TABLE)
                    tableList.add(Block(arrayForSearchingBlock, blockCount, minXY[0], minXY[1]))
                }
            }
        }

        boardList.sortByDescending { it.size }
        tableList.sortByDescending { it.size }

        var filledBlockCount = 0

        for (i in boardList.indices) {
            val emptyBlock = boardList[i]
            for (j in tableList.indices) {
                val candidateBlock = tableList[j]

                if (emptyBlock.size < candidateBlock.size) {
                    continue
                } else if (emptyBlock.size == candidateBlock.size) {
                    if (isFitBlock(emptyBlock.matrix, candidateBlock.matrix, emptyBlock.size)) {
                        filledBlockCount += emptyBlock.size
                        tableList.removeAt(j)
                        break
                    }
                } else {
                    break
                }
            }
        }

        return filledBlockCount
    }

    private fun searchBlock(i: Int, j: Int, mode: Search) {
        blockCount++
        if (mode == Search.BOARD) gameBoard[i][j] = 1 else table[i][j] = 0

        if (i < minXY[0]) { minXY[0] = i }
        if (j < minXY[1]) { minXY[1] = j }

        arrayForSearchingBlock[i][j] = 1

        repeat(4) { seq ->
            val x = i + dx[seq]
            val y = j + dy[seq]

            if (x in gameBoard.indices && y in gameBoard.indices) {
                if (mode == Search.BOARD && gameBoard[x][y] == 0) {
                    searchBlock(x, y, mode)
                }
                if (mode == Search.TABLE && table[x][y] == 1) {
                    searchBlock(x, y, mode)
                }
            }
        }
    }

    private fun initSearchTempSet(x: Int, y: Int, mode: Search) {
        arrayForSearchingBlock = Array(gameBoard.size) { IntArray(gameBoard.size) }
        minXY[0] = x
        minXY[1] = y
        blockCount = 0
        searchBlock(x, y, mode)
    }

    private fun isFitBlock(emptyBlock: Array<IntArray>, candidateBlock: Array<IntArray>, size: Int): Boolean {
        repeat(4) {
            val matrix = if (it == 0) candidateBlock else rotateBlock(candidateBlock, it)

            for (x in 0 until 6) {
                for (y in 0 until 6) {
                    var count = 0

                    loop@ for(i in 0 until 6) {
                        for (j in 0 until 6) {
                            if (x+i in 0..5 && y+j in 0..5) {
                                if (emptyBlock[i][j] == 1 && matrix[x+i][y+j] == 1) count++
                                if ((emptyBlock[i][j] == 1 && matrix[x+i][y+j] == 0) || (emptyBlock[i][j] == 0 && matrix[x+i][y+j] == 1)) {
                                    break@loop
                                }
                            }
                        }
                    }

                    if (count == size) return true
                }
            }
        }

        return false
    }

    private fun rotateBlock(originalBlock: Array<IntArray>, rotateCount: Int) : Array<IntArray> {
        val rotatedArray = Array(6) { IntArray(6) { 0 } }
        for (i in 0..5) {
            for (j in 0..5) {
                when (rotateCount) {
                    0 -> rotatedArray[i][j] = originalBlock[i][j]
                    1 -> rotatedArray[i][j] = originalBlock[5-j][i]
                    2 -> rotatedArray[i][j] = originalBlock[5-i][5-j]
                    3 -> rotatedArray[i][j] = originalBlock[j][5-i]
                }
            }
        }
        return rotatedArray
    }

    inner class Block(extractedMatrix: Array<IntArray>, val size: Int, x: Int, y: Int) {
        val matrix: Array<IntArray> = Array(6) { IntArray(6) }

        init {
            repeat(6) { i ->
                repeat(6) { j ->
                    if (x + i < gameBoard.size && y + j < gameBoard.size) {
                        matrix[i][j] = extractedMatrix[x + i][y + j]
                    }
                }
            }
        }
    }

    enum class Search {
        BOARD, TABLE
    }
}