package programmers

import java.util.regex.Pattern

/**
 * 프로그래머스 - 불량 사용자
 *
 * https://programmers.co.kr/learn/courses/30/lessons/64064
 */

fun main() {
    val answer = BannedUserSolution().solution(
        arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
        arrayOf("fr*d*", "*rodo", "******", "******")
    )
    println(answer)
}

class BannedUserSolution {

    private var bannedIdSize = 0
    private val result = HashSet<HashSet<String>>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        bannedIdSize = banned_id.size

        val matchedIdList: MutableList<HashSet<String>> = mutableListOf()

        for (bannedId in banned_id) {
            val userSet = HashSet<String>()
            for (userId in user_id) {
                if (isMatchedId(userId, bannedId)) {
                    userSet.add(userId)
                }
            }
            matchedIdList.add(userSet)
        }

        dfs(0, matchedIdList, HashSet())

        return result.size
    }

    fun dfs(index: Int, matchedIdList: List<Set<String>>, candidateSet: HashSet<String>) {
        if (candidateSet.size == bannedIdSize) {
            result.add(HashSet(candidateSet))
            return
        }

        for (userId in matchedIdList[index]) {
            if (!candidateSet.contains(userId)) {
                candidateSet.add(userId)
                dfs(index + 1, matchedIdList, candidateSet)
                candidateSet.remove(userId)
            }
        }
    }

    private fun isMatchedId(userId: String?, bannedId: String): Boolean {
        val bannedPattern = bannedId.replace("*", ".")
        return Pattern.matches(bannedPattern, userId)
    }
}