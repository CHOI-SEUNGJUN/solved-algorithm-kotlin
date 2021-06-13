package baekjoon

/**
 * 백준 #1041 - 주사위
 *
 * https://www.acmicpc.net/problem/1041
 */

import java.util.*

fun main() {
    B1041Solution().solution()
}

class B1041Solution {

    private var n: Long = 0
    private var one: Long = 0
    private var two: Long = 0
    private var three: Long = 0

    fun solution() = with(System.`in`.bufferedReader()) {
        n = readLine().toInt().toLong()

        val dice = IntArray(6)

        val st = StringTokenizer(readLine())
        for (i in 0..5) {
            dice[i] = st.nextToken().toInt()
        }

        val minAF = Math.min(dice[0], dice[5]).toLong()
        val minBE = Math.min(dice[1], dice[4]).toLong()
        val minCD = Math.min(dice[2], dice[3]).toLong()

        one = Math.min(minAF, Math.min(minBE, minCD))
        two = Math.min(minAF + minBE, Math.min(minAF + minCD, minBE + minCD))
        three = minAF + minBE + minCD
        val sum = minSum(dice)

        val bw = System.`out`.bufferedWriter()

        bw.write("$sum")
        bw.close()
    }

    private fun minSum(dice: IntArray): Long {
        var sum: Long = 0

        if (n == 1L) {
            var max = Int.MIN_VALUE.toLong()
            for (i in 0..5) {
                max = Math.max(max, dice[i].toLong())
                sum += dice[i]
            }
            return sum - max
        }

        val oneSide = 4 * (n - 1) * (n - 2) + (n - 2) * (n - 2)
        val secondSide = 4 * (n - 1) + 4 * (n - 2)
        val thirdSide: Long = 4

        sum += one * oneSide + two * secondSide + three * thirdSide
        return sum
    }
}