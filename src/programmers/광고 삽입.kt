package programmers

/**
 * 프로그래머스 - 광고 삽입
 *
 * https://programmers.co.kr/learn/courses/30/lessons/72414
 */

fun main() {
    val answer = InsertAdSolution().solution(
        "02:03:55"	, "00:14:15",
        arrayOf(
            "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
        )
    )
    println(answer)
}

class InsertAdSolution {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        if (play_time == adv_time) return "00:00:00"

        val playTime = getSecTime(play_time)
        val advTime = getSecTime(adv_time)

        val viewers = LongArray(playTime + 1) { 0L }

        for (log in logs) {
            val (startTime, endTime) = log.split("-").map { getSecTime(it) }

            viewers[startTime]++
            viewers[endTime]--
        }

        for (i in 1 until viewers.size) {
            viewers[i] += viewers[i - 1]
        }

        for (i in 1 until viewers.size) {
            viewers[i] += viewers[i - 1]
        }

        var cumulativeViewers = 0L
        var maxStartTime = 0

        for (i in 0..(playTime - advTime)) {
            val pointView = viewers[i + advTime - 1] - viewers.getOrElse(i - 1) { 0L }
            if (pointView > cumulativeViewers) {
                cumulativeViewers = pointView
                maxStartTime = i
            }
        }

        return getFormatTime(maxStartTime)
    }

    private fun getSecTime(time: String) = time.split(":")
        .fold(0) { acc, s ->
        acc * 60 + s.toInt()
    }

    private fun getFormatTime(time: Int) = String.format("%02d:%02d:%02d", time / 3600, time / 60 % 60, time % 60)
}