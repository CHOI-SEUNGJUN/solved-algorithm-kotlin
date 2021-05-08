package kakao.internship2020

/**
 * 카카오 2020 인턴십 - (3) 보석 쇼핑
 *
 * https://programmers.co.kr/learn/courses/30/lessons/67258
 */

fun main() {
    val answer = Kakao03Solution().solution(arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB"))
    answer.forEach { println(it) }
}

class Kakao03Solution {

    fun solution(gems: Array<String>): IntArray {
        val answer = IntArray(2)
        val shouldBuySize = gems.distinct().size

        var dist = Int.MAX_VALUE
        var left = 0
        var right = 0

        val map: HashMap<String, Int> = hashMapOf()

        while (true) {
            if (map.size == shouldBuySize) {
                map[gems[left]] = (map[gems[left]] ?: 0) - 1

                if (map[gems[left]]!! <= 0) {
                    map.remove(gems[left])
                }
                left++
            } else if (right == gems.size) {
                break
            } else {
                map[gems[right]] = (map[gems[right]] ?: 0) + 1
                right++
            }

            if (map.size == shouldBuySize) {
                if (right - left < dist) {
                    dist = right - left
                    answer[0] = left + 1
                    answer[1] = right
                }
            }
        }

        return answer
    }
}