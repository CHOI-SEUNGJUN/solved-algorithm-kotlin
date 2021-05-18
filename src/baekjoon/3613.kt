package baekjoon

/**
 * 백준 #3613 - Java vs C++
 *
 * https://www.acmicpc.net/problem/3613
 */

fun main() {
    B3613Solution().solution()
}

class B3613Solution {
    fun solution() = with(System.`in`.bufferedReader()) {
        val word = readLine()

        val answer = StringBuilder()

        if (word.first().isUpperCase() || word.first() == '_' || word.last() == '_' || word.contains("__")) {
            answer.append("Error!")
        } else {
            if (word.contains("_")) {
                if (word.contains("[A-Z]".toRegex())) {
                    answer.append("Error!")
                } else {
                    val split = word.split("_")

                    answer.append(split[0])
                    for (i in 1 until split.size) {
                        val nextWord = split[i]
                        answer.append(nextWord[0].toUpperCase())
                        for (j in 1 until nextWord.length) {
                            if (nextWord.isNotBlank()) {
                                answer.append(nextWord[j])
                            }
                        }
                    }
                }
            } else if (word.contains("[A-Z]".toRegex())) {
                for (w in word) {
                    if (w.isUpperCase()) {
                        answer.append("_")
                        answer.append(w.toLowerCase())
                    } else {
                        answer.append(w)
                    }
                }
            } else {
                answer.append(word)
            }
        }



        val br = System.`out`.bufferedWriter()
        br.write("$answer")
        br.close()
    }
}