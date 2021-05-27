package kakao.blind2021

/**
 * 2021 KAKAO BLIND RECRUITMENT - 메뉴 리뉴얼
 *
 * https://programmers.co.kr/learn/courses/30/lessons/72411
 */

import java.util.*

fun main() {
    println(MenuRenewalSolution().solution(arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"), intArrayOf(2,3,4)).joinToString(" "))
}

class MenuRenewalSolution {

    private var map = hashMapOf<String, Int>()
    private var maxCnt = 0

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val pq: PriorityQueue<String> = PriorityQueue()

        for (i in course.indices) {
            map = hashMapOf()
            maxCnt = 0

            for (j in orders.indices) {
                find(0, "", course[i], 0, orders[j])
            }

            for (m in map.keys) {
                if (map[m]!! == maxCnt && maxCnt > 1) {
                    pq.offer(m)
                }
            }
        }

        val answer = Array(pq.size) { "" }
        var k = 0
        while(pq.isNotEmpty()) {
            answer[k++] = pq.poll()
        }

        return answer
    }

    fun find(cnt: Int, str: String, target: Int, idx: Int, order: String) {
        if (cnt == target) {
            val chars = str.toCharArray()
            chars.sort()
            val menu = chars.joinToString("")
            map[menu] = (map[menu] ?: 0) + 1
            maxCnt = Math.max(maxCnt, map[menu]!!)
            return
        }

        for (i in idx until order.length) {
            val char = order[i]
            find(cnt + 1, str + char, target, i + 1, order)
        }
    }
}