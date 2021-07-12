package programmers

/**
 * 프로그래머스 - 단어 변환
 *
 * https://programmers.co.kr/learn/courses/30/lessons/43163
 */

fun main() {
    val answer = WordConverterSolution().solution(
        begin = "hit",
        target = "hhh",
        arrayOf("hti", "hth", "hhh")
    )
    println(answer)
}

class WordConverterSolution {
    private lateinit var visited: BooleanArray
    private lateinit var target: String
    private lateinit var words: Array<CharArray>

    var minValue = Int.MAX_VALUE

    fun solution(begin: String, target: String, words: Array<String>): Int {
        visited = BooleanArray(words.size) { false }
        this.target = target
        this.words = words.map { it.toCharArray() }.toTypedArray()

        bfs(begin, 0)

        return if (minValue == Int.MAX_VALUE) 0 else minValue
    }

    fun bfs(currentWord: String, cnt: Int): Int {
        if (target == currentWord) return cnt

        val currentChars = currentWord.toCharArray()
        loop@ for ((index, word) in words.withIndex()) {
            var diffCnt = 0
            for (i in word.indices) {
                if (currentChars[i] != word[i]) diffCnt++
                if (diffCnt == 2) continue@loop
            }

            if (diffCnt == 1 && !visited[index]) {
                visited[index] = true
                minValue = Math.min(bfs(words[index].concatToString(), cnt + 1), minValue)
                visited[index] = false
            }
        }

        return Int.MAX_VALUE
    }
}