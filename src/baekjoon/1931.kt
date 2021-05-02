package baekjoon

/**
 * 백준 #1931 - 회의실 배정
 *
 * https://www.acmicpc.net/problem/1931
 *
 * Greedy Algorithm
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val timeTable = arrayListOf<Pair<Int, Int>>()

    repeat(n) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        timeTable.add(s to e)
    }

    val comparator = compareBy<Pair<Int, Int>> { it.second }
        .thenBy { it.first }

    timeTable.sortWith(comparator)

    var prevEndTime = 0
    var meetingCnt = 0

    for (i in timeTable.indices) {
        val time = timeTable[i]

        if (time.first >= prevEndTime) {
            meetingCnt++
            prevEndTime = time.second
        } else {
            continue
        }
    }

    val bw = System.`out`.bufferedWriter()
    bw.write("$meetingCnt")
    bw.close()
}
