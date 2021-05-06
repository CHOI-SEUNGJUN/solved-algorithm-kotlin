package leetcode

fun main() {
    print(LeetCode17Solution().letterCombinations("2"))
}

class LeetCode17Solution {

    private val digitMap = HashMap<String, List<String>>()
    private val output = mutableListOf<String>()
    private var digits = ""

    fun initDigitMap() {
        digitMap["2"] = listOf("a", "b", "c")
        digitMap["3"] = listOf("d", "e", "f")
        digitMap["4"] = listOf("g", "h", "i")
        digitMap["5"] = listOf("j", "k", "l")
        digitMap["6"] = listOf("m", "n", "o")
        digitMap["7"] = listOf("p", "q", "r", "s")
        digitMap["8"] = listOf("t", "u", "v")
        digitMap["9"] = listOf("w", "x", "y", "z")
    }

    fun letterCombinations(digits: String): List<String> {
        this.digits = digits
        if (digits.isBlank()) return listOf()

        initDigitMap()
        dfs(0, "")
        return output
    }

    fun dfs(index: Int, letter: String) {
        if (index >= digits.length && letter.isNotBlank()) {
            output.add(letter)
            return
        }

        val num = (digits[index] - '0').toString()

        for (i in digitMap[num]!!.indices) {
            dfs(index + 1, letter + digitMap[num]!![i])
        }
    }
}