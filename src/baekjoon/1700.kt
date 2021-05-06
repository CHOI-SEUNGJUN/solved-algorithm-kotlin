package baekjoon

/**
 * 백준 #1700 - 멀티탭 스케줄링
 *
 * https://www.acmicpc.net/problem/1700
 */

fun main() {
    B1700().solution()
}

class B1700 {

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        val plugs = br.readLine().split(" ").map { it.toInt() }
        val hole = BooleanArray(k+1) { false }

        val multiTap = arrayListOf<Int>()

        var cnt = 0

        if (n < k) {
            for (i in 0 until k) {
                if (hole[plugs[i]]) {
                    continue
                }

                if (multiTap.size < n) {
                    multiTap.add(plugs[i])
                    hole[plugs[i]] = true
                } else {
                    var lastIdx = 0
                    var changeIdx = 0

                    for (m in multiTap.indices) {
                        var tmpIdx = -1
                        for (j in i+1 until k) {
                            if (multiTap[m] == plugs[j]) {
                                tmpIdx = j
                                break
                            }
                        }

                        if (tmpIdx < 0) {
                            changeIdx = m
                            break
                        } else {
                            if (lastIdx < tmpIdx) {
                                lastIdx = tmpIdx
                                changeIdx = m
                            }
                        }
                    }

                    hole[multiTap[changeIdx]] = false
                    hole[plugs[i]] = true
                    multiTap[changeIdx] = plugs[i]
                    cnt++
                }
            }
        }

        val bw = System.`out`.bufferedWriter()
        bw.write("$cnt")
        bw.close()
    }

}