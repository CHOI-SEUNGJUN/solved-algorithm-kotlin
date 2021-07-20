package programmers

/**
 * 프로그래머스 - 순위 검색
 *
 * https://programmers.co.kr/learn/courses/30/lessons/72412
 */

fun main() {
    val answer = RankSearchSolution().solution(
        info = arrayOf("java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"),
        query = arrayOf("java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150")
    )
    println(answer.joinToString(","))
}

class RankSearchSolution {

    val hashMap = hashMapOf<String, MutableList<Int>>()

    fun solution(info: Array<String>, query: Array<String>): IntArray {

        // 지원자로부터의 해당되는 정보를 모두 mapping
        for (candidate in info) {
            setMapFromInfo("", candidate.split(" "), 0)
        }

        // 이분 탐색을 위한 정렬
        for (list in hashMap) {
            list.value.sort()
        }

        val answer = mutableListOf<Int>()

        // 쿼리 전처리
        for (q in query) {
            val transformedQuery = q.replace(" and ", "").split(" ")
            val condition = transformedQuery[0]
            val score = transformedQuery[1].toInt()
            answer.add(binarySearch(condition, score))
        }

        return answer.toIntArray()
    }

    fun setMapFromInfo(key: String, info: List<String>, depth: Int) {
        if (depth == 4) { // Score depth
            if (hashMap.containsKey(key)) {
                hashMap[key]?.add(info[4].toInt())
            } else {
                hashMap[key] = mutableListOf(info[4].toInt())
            }
            return
        }

        setMapFromInfo("$key-", info, depth + 1)
        setMapFromInfo("$key${info[depth]}", info, depth + 1)
    }

    fun binarySearch(condition: String, score: Int): Int {
        if (!hashMap.containsKey(condition)) return 0
        val candidates = hashMap[condition]!!

        var left = 0
        var right = candidates.size - 1

        while (left <= right) {
            val mid = (left + right) / 2

            if (candidates[mid] < score) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return candidates.size - left
    }
}