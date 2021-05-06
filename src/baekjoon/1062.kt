package baekjoon

/**
 * 백준 #1062 - 가르침
 *
 * https://www.acmicpc.net/problem/1062
 */

import kotlin.math.max

fun main() {
    print(B1062().solution())
}

class B1062 {

    var visited = BooleanArray(26) { false }
    private var n: Int = 0
    private var k: Int = 0
    private var answer = 0
    private lateinit var words: Array<String>

    fun solution(): Int {
        val br = System.`in`.bufferedReader()
        br.readLine().split(" ").map { it.toInt() }.apply {
            n = this[0]
            k = this[1]
        }

        if (k < 5) return 0
        else if (k == 26) return n

        k -= 5

        words = Array(n) { "" }

        repeat(n) {
            words[it] = br.readLine()
        }

        visited['a' - 'a'] = true
        visited['c' - 'a'] = true
        visited['t' - 'a'] = true
        visited['i' - 'a'] = true
        visited['n' - 'a'] = true

        answer = dfs(0, 0)

        return answer
    }

    fun dfs(idx: Int, cnt: Int): Int {

        if (cnt == k) {
            var readCnt = 0

            for (i in words.indices) {
                var canRead = true

                val str = words[i]

                for (j in str.indices) {
                    if (visited[str[j] - 'a'] == false) {
                        canRead = false
                        break
                    }
                }

                if (canRead) readCnt++
            }

            answer = max(answer, readCnt)
        }

        for (i in idx until 26) {
            if (visited[i] == true) continue

            visited[i] = true
            dfs(i, cnt + 1)
            visited[i] = false
        }

        return answer
    }
}