package programmers

/**
 * 프로그래머스 - 기둥과 보 설치
 *
 * https://programmers.co.kr/learn/courses/30/lessons/60061
 */

fun main() {
    val answer = PostAndBridgeSolution().solution(
        n = 5,
        build_frame = arrayOf(
            intArrayOf(0,0,0,1),
            intArrayOf(2,0,0,1),
            intArrayOf(4,0,0,1),
            intArrayOf(0,1,1,1),
            intArrayOf(1,1,1,1),
            intArrayOf(2,1,1,1),
            intArrayOf(3,1,1,1),
            intArrayOf(2,0,0,0),
            intArrayOf(1,1,1,0),
            intArrayOf(2,2,0,1)
        )
    )
    println("answer : ")
    for (a in answer) {
        println(a.joinToString(","))
    }
}

class PostAndBridgeSolution {

    private lateinit var postFrame : Array<BooleanArray>
    private lateinit var bridgeFrame : Array<BooleanArray>

    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        postFrame = Array(n + 1) { BooleanArray(n + 1) }
        bridgeFrame = Array(n + 1) { BooleanArray(n + 1) }


        for ((x, y, a, b) in build_frame) {
            if (b == 1) { // build
                if (a == 0) { // post
                    if (canBuildPost(x, y)) {
                        postFrame[x][y] = true
                        continue
                    }
                } else { // bridge
                    if (canBuildBridge(x, y)) {
                        bridgeFrame[x][y] = true
                        continue
                    }
                }
            } else { // destroy
                if (a == 0) { // post
                    postFrame[x][y] = false
                    if (!canDestroy()) postFrame[x][y] = true
                } else { // bridge
                    bridgeFrame[x][y] = false
                    if (!canDestroy()) bridgeFrame[x][y] = true
                }
            }
        }

        val answerList = mutableListOf<IntArray>()
        repeat(n + 1) { i ->
            repeat(n + 1){ j ->
            if (postFrame[i][j]) answerList.add(intArrayOf(i, j, 0))
                if (bridgeFrame[i][j]) answerList.add(intArrayOf(i, j, 1))
            }
        }
        return answerList.toTypedArray()
    }

    private fun canBuildPost(x: Int, y: Int): Boolean {
        if (y == 0 || postFrame[x][y-1]) {
            return true
        }
        return x > 0 && bridgeFrame[x-1][y] || bridgeFrame[x][y]
    }

    private fun canBuildBridge(x: Int, y: Int): Boolean {
        if (y > 0 && (postFrame[x][y-1] || postFrame[x+1][y-1])) {
            return true
        }
        return x > 0 && bridgeFrame[x-1][y] && bridgeFrame[x+1][y]
    }

    private fun canDestroy(): Boolean {
        for (i in bridgeFrame.indices) {
            for (j in bridgeFrame.indices) {
                if (bridgeFrame[i][j]) {
                    if (!canBuildBridge(i, j)) return false
                }
                if (postFrame[i][j]) {
                    if (!canBuildPost(i, j)) return false
                }
            }
        }
        return true
    }
}