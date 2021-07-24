package programmers

/**
 * 프로그래머스 - 다리를 지나는 트럭
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42583
 */

import java.util.*

fun main() {
    val answer = BridgeTruckSolution().solution(
        2, 10, intArrayOf(7,4,5,6)
    )
    println(answer)
}

class BridgeTruckSolution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val queue: Queue<Int> = LinkedList()

        var weightSum = 0
        var time = 0

        for (truck in truck_weights) {
            while (true) {
                if (queue.isEmpty()) {
                    queue.add(truck)
                    weightSum += truck
                    time++
                    break
                } else if (queue.size == bridge_length) {
                    weightSum -= queue.poll()
                } else {
                    if (weightSum + truck <= weight) {
                        queue.add(truck)
                        weightSum += truck
                        time++
                        break
                    } else {
                        queue.add(0)
                        time++
                    }
                }
            }
        }

        return time + bridge_length
    }
}